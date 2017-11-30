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

            boolean isAuthenticated = false;

            do {

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
                        isAuthenticated = true;
                        System.out.println("User authenticated.");
                        break;
                    } else if (resp.content.equals("not authenticated")) {
                        System.out.println("Unauthorized access. Please try again.");
                        continue;
                    }
                }

                ClientDisplay.displayMainMenu();

                String option = ClientInputCollector.getInput();

                switch (option) {
                    case "1":
                        ClientDisplay.displayCarSubMenu();
                        String carOption = ClientInputCollector.getInput();

                        switch (carOption) {
                            case "1":
                                msg = new Message("Get all cars");
                                break;
                            case "2":
                                System.out.println("Please input the make: ");
                                String make = ClientInputCollector.getInput();
                                msg = new Message("Find cars by make:" + make);
                                break;
                            case "3":
                                System.out.println("Please input the VIN: ");
                                String vin = ClientInputCollector.getInput();
                                msg = new Message("Find car by vin:" + vin);
                                break;
                            case "4":
                                System.out.println("Please input new car data by order of VIN, make, price, horsepower, mileage separated by comma: ");
                                String carData = ClientInputCollector.getInput();
                                msg = new Message(carData);
                                break;
                            default:
                                continue;
                        }
                        break;

                    case "2":
                        ClientDisplay.displayPartSubMenu();
                        String partOption = ClientInputCollector.getInput();

                        switch (partOption) {
                            case "1":
                                msg = new Message("Get all parts");
                                break;
                            case "2":
                                System.out.println("Please input part ID: ");
                                String partID = ClientInputCollector.getInput();
                                msg = new Message("Find part by id:" + partID);
                                break;
                            case "3":
                                System.out.println("Please int put new part data by order of partID, description, price, origin separated by comma: ");
                                String partData = ClientInputCollector.getInput();
                                msg = new Message(partData);
                                break;
                            default:
                                continue;
                        }
                        break;

                    case "3":
                        ClientDisplay.displaySaleSubMenu();
                        String saleOption = ClientInputCollector.getInput();

                        switch (saleOption) {
                            case "1":
                                msg = new Message("Get all monthly sales");
                                break;
                            default:
                                continue;
                        }
                        break;

                    case "4":
                        System.out.println("Bye.");
                        System.out.println("Client exits.");
                        msg = new Message("EXIT");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }

                output.writeObject(msg);

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
