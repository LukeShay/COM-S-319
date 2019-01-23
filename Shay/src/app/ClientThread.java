package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {
	Scanner scn = new Scanner(System.in);
	private String name;
	final DataInputStream in;
	final DataOutputStream out;
	Socket s;
	boolean connected;

	public ClientThread(Socket s, DataInputStream in, DataOutputStream out, String name) {
		this.name = name;
		this.out = out;
		this.in = in;
		this.s = s;
		connected = true;
	}

	public void run() {
		while (true) {
			try {
				String message = "";
				message = in.readUTF();
				System.out.println(name + ": " + message);

				if (message.equals("quit")) {
					this.connected = false;
					this.s.close();
					break;
				}

				for (ClientThread ct : Server.ar) {
					ct.out.writeUTF(name + ": " + message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
