package Main;

import java.io.*;
import java.net.*;

public class MessageServer {
	public static void main(String[] args) throws Exception {
		MessageServer server = new MessageServer();
		server.run();
	}
	
	public void run() throws Exception {
		ServerSocket ss = new ServerSocket(1234);
		Socket s = ss.accept();
		
		InputStreamReader isr = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String message = br.readLine();

		PrintStream ps = new PrintStream(s.getOutputStream());
		ps.println(message);
	}
}
