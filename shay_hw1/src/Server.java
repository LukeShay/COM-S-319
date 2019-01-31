

import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Server class. This class is used to listen into the given port for messaging.
 * 
 * @author Robert Shay
 *
 */
public class Server {

	// Vector to store active clients
	public static Vector<ClientThread> ar = new Vector<>();
	private ClientThread mtch;

	/**
	 * Server object. Used to start a server that listens to the given port.
	 * 
	 * @param port
	 *            Port that is being used for the messaging.
	 * @throws IOException
	 */
	public Server(int port) throws IOException {
		ServerSocket ss = new ServerSocket(port);
		Socket s;

		// Loop for receiving client requests
		while (true) {
			// Accept the incoming request
			s = ss.accept();

			System.out.println("\nNew client accepted...");

			// Create data input and output streams.
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			System.out.println("Data stream created...\n");

			// Stores the name of the client.
			String name = in.readUTF();

			// Create a new client thread for this client.
			mtch = new ClientThread(s, in, out, name);
			Thread t = new Thread(mtch);

			// add this client to the vector
			ar.add(mtch);

			// start the thread.
			t.start();

			// Prints to the server that the user has joined.
			System.out.println(name + " has joined.");

			// Notifies the other clients that there is a new user.
			notifyClients(name);
		}
	}

	/**
	 * Notifys the clients that a new user has joined.
	 * 
	 * @param name
	 *            Name of the new user.
	 * @throws IOException
	 */
	private void notifyClients(String name) throws IOException {
		for (ClientThread ct : ar)
			if(ct != mtch)
			ct.out.writeUTF("Server: " + name + " has joined.");
	}

	public static void main(String[] args) throws IOException {
		Server serv = new Server(5000);
	}
}