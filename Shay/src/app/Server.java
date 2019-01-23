package app;

import java.io.*; 
import java.util.*; 
import java.net.*; 
  
// Server class 
public class Server  
{ 
  
    // Vector to store active clients 
    static Vector<ClientThread> ar = new Vector<>(); 
    
    public Server(int port) throws IOException {
        // server is listening on port 1234 
        ServerSocket ss = new ServerSocket(port); 
          
        Socket s; 
          
        // running infinite loop for getting 
        // client request 
        while (true)  
        { 
            // Accept the incoming request 
            s = ss.accept(); 
  
            System.out.println("New client request received : " + s); 
              
            // obtain input and output streams 
            DataInputStream in = new DataInputStream(s.getInputStream()); 
            DataOutputStream out = new DataOutputStream(s.getOutputStream()); 
              
            System.out.println("Creating a new handler for this client..."); 
  
            // Create a new handler object for handling this request. 
            ClientThread mtch = new ClientThread(s, in, out, in.readUTF()); 
  
            // Create a new Thread with this object. 
            Thread t = new Thread(mtch); 
              
            System.out.println("Adding this client to active client list"); 
  
            // add this client to active clients list 
            ar.add(mtch); 
  
            // start the thread. 
            t.start();
        } 
    }
  
    public static void main(String[] args) throws IOException  
    { 
    	Server serv = new Server(5000);
    } 
} 