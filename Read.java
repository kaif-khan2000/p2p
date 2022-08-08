
import java.net.*;
import java.io.*;
import java.util.*;

class Read extends Thread {
	
	private BufferedReader in = null;
	public Read(Socket server){
		System.out.println("Reading object initializing");
		try{
			this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		}catch(IOException i){
			System.out.println(i);
		}
	}
	public void run(){
		try{
			while(true){
				System.out.println("reading");
				String msg = in.readLine();
				System.out.println("done reading");
				if(msg.equals("close")){
					System.out.println("Server has closed the connection");
					break;
				}
				System.out.println("server: "+msg);
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
