package cardealersystem;

import java.lang.Thread;            // We will extend Java's base Thread class
import java.net.Socket;
import java.io.ObjectInputStream;   // For reading Java objects off of the wire
import java.io.ObjectOutputStream;  // For writing Java objects to the wire


public class DataProcessThread extends Thread
{
    private final Socket socket;

    public DataProcessThread(Socket _socket)
    {
        socket = _socket;
    }

    public void run()
    {
        try {
            // Print incoming message
            System.out.println("** New connection from " + socket.getInetAddress() + ":" + socket.getPort() + " **");

            // set up I/O streams with the client
            final ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Loop to read messages
            Message msg = null;
            boolean isAuthenticated = false;

            // load users into memory
            Users.seedUsers();

            //load cars and parts data into memory
            Inventory.seedCars();
            Inventory.seedParts();

            // seed purchase lists to memory
            SeedPurchases.seed();

            // infinity loop until exit signal shows
            while (true) {

                // server authentication part
                while (!isAuthenticated) {
                    msg = (Message) input.readObject();
                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.content);

                    // get query string from client to process
                    String userInfo = msg.content;
                    String[] parts = userInfo.split(";");

                    String username = parts[0];
                    String pw = parts[1];

                    // query user info against pre existing user list
                    User u = Users.findUser(username);

                    // authenticate user
                    if (u != null && u.authenticate(pw)) {

                        // if user gets authenticated, send signal to client
                        isAuthenticated = true;
                        output.writeObject(new Message("authenticated"));
                        break;
                    } else {
                        output.writeObject(new Message( "not authenticated"));
                    }
                }

                // receive action code from client
                msg = (Message) input.readObject();
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.content);

                // break out of infinity loop if client flag exit signal
                if (msg.content.equalsIgnoreCase("exit")) {
                    output.writeObject(new Message("Server exits."));
                    break;
                }


                String request = msg.content;

                // print out request code to console for debug purpose
                System.out.println("Request code is: " + request);

                // spawn new instance of request handler to process request code
                RequestHandler handler = new RequestHandler(request);

                // after request code is processed, produce response string, also print out console for debug
                String response = handler.produceResponse();
                System.out.println("Response is: " + response);

                // push data back to the client
                output.writeObject(new Message(response));
            }

            // Close and cleanup
            System.out.println("** Closing connection with " + socket.getInetAddress() + ":" + socket.getPort() + " **");
            socket.close();

        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    }
}

