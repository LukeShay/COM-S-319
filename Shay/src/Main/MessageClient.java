package Main;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessageClient {
	public static void main(String[] args) throws Exception {
		MessageClient client = new MessageClient();
		client.run();
	}
	
	public void run() throws Exception {
		Scanner scan = new Scanner(System.in);
		Socket s = new Socket("localhost", 1234);
		PrintStream ps = new PrintStream(s.getOutputStream());
		
		ps.println(scan.nextLine());
		
		InputStreamReader isr = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		String message = br.readLine();
		System.out.println(message);		
	}
}
