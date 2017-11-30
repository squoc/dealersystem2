package cardealersystem;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static final int SERVER_PORT = 8765;

    public static void main(String[] args) {

        try {
            // This is basically just listens for new client connections
            final ServerSocket serverSock = new ServerSocket(SERVER_PORT);
            System.out.println("Waiting for connection on port " + SERVER_PORT);

            // A simple infinite loop to accept connections
            Socket sock = null;
            DataProcessThread thread = null;
            while (true) {
                sock = serverSock.accept();     // Accept an incoming connection
                thread = new DataProcessThread(sock);  // Create a thread to handle this connection
                thread.start();                 // Fork the thread
            }                                   // Loop to work on new connections while this
                                                // the accepted connection is handled

        }
        catch(Exception e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    }  //-- end main(String[])
}
