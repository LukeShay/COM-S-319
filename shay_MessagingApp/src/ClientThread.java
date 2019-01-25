

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class used for creating a thread for each of the clients that are using the
 * messaging program.
 * 
 * @author Robert Shay
 *
 */
public class ClientThread implements Runnable {
	Scanner scn = new Scanner(System.in);
	private String name;
	final DataInputStream in;
	final DataOutputStream out;
	Socket s;
	boolean connected;
	private int num;

	public ClientThread(Socket s, DataInputStream in, DataOutputStream out, String name, int num) {
		this.name = name;
		this.out = out;
		this.in = in;
		this.s = s;
		connected = true;
		this.num = num;
	}

	public void run() {
		String message = "";

		while (connected) {
			try {
				message = in.readUTF();
				System.out.println(name + ": " + message);

				if (message.equals("quit")) {
					// Tells all connected users that the client that is connected to this thread
					// has left.
					send("Server: " + name + " has left.");
					System.out.println(name + " has left.");

					// Removes this ClientThread from the vector in the server class.
					Server.ar.remove(this);

					// Closes all resources.
					this.connected = false;
					this.s.close();
					this.in.close();
					this.out.close();
					break;
				}
				else send(name + ": " + message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sends messages to all other clients.
	 * 
	 * @param msg
	 *            Message to be sent.
	 * @throws IOException
	 */
	private void send(String msg) throws IOException {
		for (ClientThread ct : Server.ar)
			if (this.num != ct.num) ct.out.writeUTF(msg);
	}
}
