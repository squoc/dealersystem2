package cardealersystem;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {

        try {
            String hostname = "localhost";

            Socket connection = new Socket(hostname, SocketServer.SERVER_PORT);
            System.out.println("Connected to server on port " + SocketServer.SERVER_PORT);

            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());

            Message msg = null, resp = null;

            // authenticated flag
            boolean isAuthenticated = false;

            do {

                // perform infinity loop until user gets authenticated.
                while (!isAuthenticated) {
                    System.out.println("Please enter username. Type EXIT to exit.");
                    String username = ClientInputCollector.getInput();

                    if (username.equalsIgnoreCase("exit"))
                        System.exit(0);

                    System.out.println("Please enter password: ");
                    String pw = ClientInputCollector.getInput();


                    String loginInfo = username + ";" + pw;

                    msg = new Message((loginInfo));
                    output.writeObject(msg);

                    resp = (Message) input.readObject();

                    if (resp.content.equals("authenticated")) {
                        // flip the flag if got authenticated signal from server
                        isAuthenticated = true;
                        System.out.println("User authenticated.");
                        break;
                    } else if (resp.content.equals("not authenticated")) {
                        System.out.println("Unauthorized access. Please try again.");
                        continue;
                    }
                }

                // display main menu
                ClientDisplay.displayMainMenu();

                // collect input from user
                String option = ClientInputCollector.getInput();

                // switch user's choice to get appropriate sub menu
                switch (option) {
                    case "1":

                        // display car module menu
                        ClientDisplay.displayCarSubMenu();
                        String carOption = ClientInputCollector.getInput();

                        // assign each action with a action code so the server could distinguish and perform the corresponding action

                        switch (carOption) {
                            case "1":
                                // 101 is get all cars in inventory
                                msg = new Message("101;");
                                break;

                            case "2":
                                System.out.println("Please input the make: ");
                                String make = ClientInputCollector.getInput();
                                // 102 is query cars by make
                                // also send query string to the server to process the request
                                msg = new Message("102;" + make);
                                break;

                            case "3":
                                System.out.println("Please input the VIN: ");
                                String vin = ClientInputCollector.getInput();

                                // 103 is query car by VIN number
                                // send vin query string as well
                                msg = new Message("103;" + vin);
                                break;

                            case "4":
                                System.out.println("Please input new car data by order of VIN,make,color,numberOfDoors,numberOfSeats,price,mileage,horsepower, separated by comma: ");
                                String carData = ClientInputCollector.getInput();

                                // 104 is add new car to inventory
                                // send new car data
                                msg = new Message("104;" + carData);
                                break;

                            default:
                                continue;
                        }
                        break;

                    case "2":
                        // display sub menu for part module
                        ClientDisplay.displayPartSubMenu();
                        String partOption = ClientInputCollector.getInput();

                        switch (partOption) {
                            case "1":
                                // 201 is query all parts in inventory
                                msg = new Message("201;");
                                break;

                            case "2":
                                System.out.println("Please input part ID: ");
                                String partID = ClientInputCollector.getInput();

                                // 202 is query part by part ID
                                msg = new Message("202;" + partID);
                                break;

                            case "3":
                                System.out.println("Please int put new part data by order of partID,description,price,origin separated by comma: ");
                                String partData = ClientInputCollector.getInput();

                                // 203 is add new part to inventory
                                msg = new Message("203;" + partData);
                                break;

                            default:
                                continue;
                        }
                        break;

                    case "3":
                        // display sub menu for sale module
                        ClientDisplay.displaySaleSubMenu();
                        String saleOption = ClientInputCollector.getInput();

                        switch (saleOption) {
                            case "1":
                                // 301 is query all sale orders and value
                                msg = new Message("301;");
                                break;

                            default:
                                continue;
                        }
                        break;

                    case "4":
                        // if user chooses to exit app
                        System.out.println("Bye.");
                        System.out.println("Client exits.");
                        msg = new Message("EXIT");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }

                // send request code to the server for processing
                output.writeObject(msg);

                // receive response from the server, simply display it to user
                resp = (Message) input.readObject();
                System.out.println(resp.content);

            } while (!msg.content.toUpperCase().equals("EXIT"));

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);

        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
