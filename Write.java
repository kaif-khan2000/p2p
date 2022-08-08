

import java.net.*;
import java.io.*;

class Write extends Thread {
	private PrintWriter out;
	private Console in;
	public Write(Socket server){
        	System.out.println("Writing object initialized");
		try{
		    this.out = new PrintWriter(server.getOutputStream(),true);
		    this.in = System.console();
        	} catch(IOException i) {
        		System.out.println(i);
        	}
	}
	public void run(){
		try{
			while(true){
				String msg = in.readLine(">>");
				out.println(msg);
				if(msg.equals("close")){
					System.out.println("Server has closed the connection");
					break;
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
