

import java.net.*;
import java.io.*;
//import threads.*;
public class Server {
	private Socket client1 = null;
	private ServerSocket server = null;
	private DataInputStream in = null;

	public Server(int port){
		try{
			server = new ServerSocket(port);
			System.out.println("Waiting for client to join");

			client1 = server.accept();
			System.out.println("Client joined");
		} catch(IOException i) {
			System.out.println("UserError:"+i);
		}

		Read thread = new Read(client1);
		thread.start();
		Write thread2 = new Write(client1);
		thread2.start();

		try{
			thread.join();
			thread2.join();
		} catch(InterruptedException i){
			System.out.println("UserError"+i);
		}


	}

}



