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
public class DataProcessThread extends Thread
{
    private final Socket socket;                   // The socket that we'll be talking over

    /**
     * Constructor that sets up the socket we'll chat over
     *
     * @param _socket The socket passed in from the server
     *
     */
    public DataProcessThread(Socket _socket)
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

            while (true) {
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

                if (msg.content.equalsIgnoreCase("exit")) {
                    output.writeObject(new Message("Server exits."));
                    break;
                }


                String request = msg.content;

                RequestHandler handler = new RequestHandler(request);

                String response = handler.produceResponse();

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

    }  //-- end run()

} //-- end class DataProcessThread

