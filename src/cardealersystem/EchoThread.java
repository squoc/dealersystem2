package cardealersystem;

import java.lang.Thread;            // We will extend Java's base Thread class
import java.net.Socket;
import java.io.ObjectInputStream;   // For reading Java objects off of the wire
import java.io.ObjectOutputStream;  // For writing Java objects to the wire

/**
 * A simple server thread.  This class just echoes the messages sent
 * over the socket until the socket is closed.
 *
 */
public class EchoThread extends Thread
{
    private final Socket socket;                   // The socket that we'll be talking over

    /**
     * Constructor that sets up the socket we'll chat over
     *
     * @param _socket The socket passed in from the server
     *
     */
    public EchoThread(Socket _socket)
    {
        socket = _socket;
    }


    /**
     * run() is basically the main method of a thread.  This thread
     * simply reads Message objects off of the socket.
     *
     */
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

            do {
                while (!isAuthenticated) {
                    msg = (Message) input.readObject();
                    System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.content);

                    String userInfo = msg.content;
                    String[] parts = userInfo.split(";");

                    String username = parts[0];
                    String pw = parts[1];

                    User u = Users.findUser(username);


                    if (u != null && u.authenticate(pw)) {
                        isAuthenticated = true;
                        output.writeObject(new Message("authenticated"));
                        break;
                    } else {
                        output.writeObject(new Message( "not authenticated"));
                    }
                }

                msg = (Message) input.readObject();
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] " + msg.content);

                String request = msg.content;
                String response = "response from server";

                if (request.equals("car inventory check")) {
                    // get car inventory
                    response = "query some car inventory.";
                }
                else if (request.equals(("part inventory check"))) {
                    // get part inventory
                    response = "query some part";
                }

                // simply echo back to client for now, but need to write real data back to client
                // output.writeObject(new Message(data));
                output.writeObject(new Message(response));

            } while (!msg.content.toUpperCase().equals("EXIT"));

            // Close and cleanup
            System.out.println("** Closing connection with " + socket.getInetAddress() + ":" + socket.getPort() + " **");
            socket.close();

        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    }  //-- end run()

} //-- end class EchoThread

