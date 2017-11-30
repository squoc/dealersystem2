package cardealersystem;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {

        boolean isAuthenticated = false;

        try {
            String hostname = "localhost";

            Socket connection = new Socket(hostname, SocketServer.SERVER_PORT);
            System.out.println("Connected to server on port " + SocketServer.SERVER_PORT);

            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());

            Message msg = null, resp = null;

            do {

                while (!isAuthenticated) {
                    String username = askUsername();
                    String pw = askPassword();
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

                displayMenu();

                String option = readSomeText();

                int opt = Integer.parseInt(option);
                switch (opt) {
                    case 1:
                        msg = new Message("car inventory check");
                        break;
                    case 2:
                        msg = new Message("part inventory check");
                        break;
                    case 3:
                        msg = new Message("show monthly sale");
                        break;
                    case 4:
                        System.out.println("Bye.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        continue;
                }

                output.writeObject(msg);

                // simply echo the message for now
                // have to prepare data from the server
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

    public static void displayMenu() {
        System.out.println("1. Check car inventory");
        System.out.println("2. Check part inventory");
        System.out.println("3. Show monthly total sale");
        System.out.println("4. Exit");
    }

    private static String readSomeText() {
        try {
            System.out.println("Enter request, or type \"EXIT\" to quit.");
            System.out.print(" > ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    private static String askUsername() {
        try {
            System.out.println("Please enter username: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }

    private static String askPassword() {
        try {
            System.out.println("Please enter password: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }
}
