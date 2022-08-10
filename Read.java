// reads from others and print it to console

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;


class Read extends Thread {
	
	private BufferedReader in = null;
	private Socket user;
	public String ip;
	public JTextArea textArea;
	public Read(Socket server, JTextArea textArea) {
		this.user = server;
		this.textArea = textArea;
		this.ip = server.getInetAddress().toString().replace("/","");
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
				String msg = in.readLine();
				if(msg.equals("close")){
					System.out.println("["+ip+"] has closed the connection");
					textArea.append("["+ip+"] has closed the connection\n");
					
					break;
				}
				System.out.println("\b\b["+ip+"]: "+msg);
				textArea.append("["+ip+"]: "+msg+"\n");
				System.out.print(">>");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
