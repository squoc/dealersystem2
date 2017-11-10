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

            do {
                msg = new Message(readSomeText());
                output.writeObject(msg);

                resp = (Message) input.readObject();
                System.out.println("\nServer says: " + resp.content + "\n");

            } while (!msg.content.toUpperCase().equals("EXIT"));

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private static String readSomeText() {
        try {
            System.out.println("Enter a line of text, or type \"EXIT\" to quit.");
            System.out.print(" > ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            return "";
        }

    }
}
