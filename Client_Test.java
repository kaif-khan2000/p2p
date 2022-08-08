import java.util.*;
import java.io.*;
class Client_Test {
	public static void main(String[] args){
		Console console = System.console();
		String ip = console.readLine("Enter ip: ");
		Client c = new Client(ip,5454); 
	}
}
