package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {
    
    public static final int port = 8080;
    public static final String address = "127.0.0.1";

    private static ServerSocket server = null;
    private static Socket client;
    private static PrintWriter out;
    private static BufferedReader in;

    public static final void start() {

        try {

            server = new ServerSocket(port);
            client = server.accept();
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String inString = in.readLine();
            out.println("Recieved: " + inString);

        } catch (IOException e) {
            
            e.printStackTrace();

        }

    }



    public static final void stop() {

        // 

    }

}
