

import java.net.*;
import java.io.*;

public class Server extends Thread{
	private Socket client1 = null;
	private ServerSocket server = null;
	private PrintWriter out;
	private int port;
	public Server(int port){

		this.port = port;
		/*
		try{
			server = new ServerSocket(port);
			System.out.println("Waiting for client to join");

			client1 = server.accept();
			System.out.println("Client joined");
			this.out = new PrintWriter(client1.getOutputStream(),true);
		} catch(IOException i) {
			System.out.println("UserError:"+i);
		}
		
		Read thread = new Read(client1);
		thread.start();
		*/

	}
	
	public void run() {
		while(true){
			//new code
			try{
				server = new ServerSocket(port);
				System.out.println("Waiting for client to join");

				client1 = server.accept();
				System.out.println("Client joined");
				this.out = new PrintWriter(client1.getOutputStream(),true);
			} catch(IOException i) {
				System.out.println("UserError:"+i);
			}
		
			Read thread = new Read(client1);
			thread.start();
			try { thread.join(); }
			catch(Exception e) { System.out.println("userError:"+e); }
		}
	}
	public void write(String msg){
		try{
			out.println(msg);			
		} catch (Exception i) {
			System.out.println("UserError: "+i);
		}
	}
	
	public String getIP(){
		String ip="";
		boolean flag = true;
	        while(flag){
			try{
	       	       		ip = client1.getInetAddress().toString().replace("/","");
				flag = false;
			} catch (Exception e) {
				flag = true;
			}
		}
		return ip;


	}

	public boolean isConnected() {
		boolean val;
		try{	
			val = client1.isConnected();
		} catch (Exception e){
			return false;
		}
		return false;
	}


}



