
import java.net.*;
import java.io.*;

public class Client {

	private Socket server = null;
	

	public Client(String adress, int port){
		try {
			server = new Socket(adress,port);
			System.out.println("Connected to server");
		} catch (UnknownHostException i ){
			System.out.println(i);
		} catch (IOException i){
			System.out.println(i);
		}

		Read thread = new Read(server);
		thread.start();
		Write thread2 = new Write(server);
		thread2.start();
		
		try{
			thread.join();
			thread2.join();

			
		} catch(InterruptedException i){
			System.out.println(i);
		}
	}
}


