

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * Program for the client to connect to the messaging server.
 * 
 * @author Robert Shay
 *
 */
public class Client {
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private Scanner input = null;
	private boolean connected = false;
	private Thread receiveMsg;
	private Thread sendMsg;

	public Client(String address, int port) throws IOException {
		// Initializes need resources.
		socket = new Socket(address, port);
		input = new Scanner(System.in);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());

		// Asks user for name
		System.out.print("Enter your Name: (After giving input “your name”, need to press Enter) ");
		out.writeUTF(input.nextLine());

		// Gives instructions for the user.
		System.out.print(
				"To send a message, press enter. The message will be sent to all other users. When you are finished, type \"quit\" to leave.\n");

		// Initializes the thread that is used to send messages to the server.
		sendMsg = new Thread(new Runnable() {
			String message = "";

			public void run() {
				while (true) {
					message = input.nextLine();
					try {
						// Only sends the message if it is not empty.
						if (!message.trim().equals("")) out.writeUTF(message);

						// If statement that closes the socket and exits the program if the user wants
						// to quit.
						if (message.toLowerCase().equals("quit")) {
							socket.close();
							System.exit(0);
						}
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Initializes the thread that is used to receive all messages.
		receiveMsg = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						// Receives messages then stores it in the message variable.
						String message = in.readUTF();
						System.out.println(message);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Starts the threads.
		sendMsg.start();
		receiveMsg.start();
	}

	public static void main(String args[]) throws IOException {
		Client client = new Client("LocalHost", 5000);
	}
}
