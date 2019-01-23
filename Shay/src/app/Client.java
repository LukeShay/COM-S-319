package app;

// A Java program for a Client 
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private Scanner input = null;
	private boolean connected = false;
	private Thread receiveMsg;
	private Thread sendMsg;

	public Client(String address, int port) throws IOException {
		socket = new Socket(address, port);

		input = new Scanner(System.in);

		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());

		sendMsg = new Thread(new Runnable() {
			String message = "";

			public void run() {
				while (true) {
					try {
						message = input.nextLine();
						out.writeUTF(message);
	
						if (message.toLowerCase().equals("quit")) {
							System.exit(0);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		receiveMsg = new Thread(new Runnable() {
			public void run() {
				while (connected) {
					try {
						String message = in.readUTF();
						System.out.println(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		sendMsg.start();
		receiveMsg.start();
	}

	public static void main(String args[]) throws IOException {
		Client client = new Client("LocalHost", 5000);
	}
}
