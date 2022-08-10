import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ServerGui extends Thread  {
    private static ServerSocket server;
    public static int n = 5;
    public Socket client1;
    public static ServerGui client[] = new ServerGui[n];
    static {
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println("UserError: " + e);
        }
    }


    public static String ip[] = new String[100];
    
    public static int fetchIndex(String ipAddress){
        for(int i=0;i<n;i++){
            if(ip[i]!=null || ip[i]!=""){
                if(ip[i].equals(ipAddress)){
                    return i;
                }
            }
        }
        return -1;
    }
    public static void sendMessage(String ip,String message) {
		int index = fetchIndex(ip);
		if (index == -1) {
			System.out.println("IP is not connected.");
			return;
		}
		PrintWriter out = null;
        try{
		    out = new PrintWriter(client[index].client1.getOutputStream(),true);
        } catch(IOException i) {
        		System.out.println(i);
        }
        out.println(message);
	}
    
    //GUI declarations.
    public static JFrame f = new JFrame("Server");
    public static JComboBox menu = new JComboBox(ip);
    public static JButton b = new JButton("Send");
    public static JTextArea textArea = new JTextArea(20,30);
    public static JScrollPane scrollPane = new JScrollPane(textArea);
    public static JPanel messageBox = new JPanel();
    public static JPanel sendBox = new JPanel();
    public static JTextField textField = new JTextField(20);
    public static JPanel menu_send_box = new JPanel();
    public static void setup() {
        menu.setBounds(50, 50,90,20);    
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        
        b.addActionListener(l -> {
            String ip1 = (String)menu.getSelectedItem();
            textArea.append("You to ["+ip1+"] : "+textField.getText()+"\n");
            sendMessage(ip1,textField.getText());
            textField.setText("");
        });
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER){
                    String ip1 = (String)menu.getSelectedItem();
                    textArea.append("You to ["+ip1+"] : "+textField.getText()+"\n");
                    sendMessage(ip1,textField.getText());
                    textField.setText("");
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        messageBox.setLayout(new BorderLayout());
        messageBox.add(scrollPane, BorderLayout.NORTH);
        
        sendBox.setLayout(new BorderLayout());
        textArea.setEditable(false);
        menu_send_box.setLayout(new BorderLayout());
        sendBox.add(textField,BorderLayout.WEST);
        sendBox.add(b,BorderLayout.EAST);
        menu_send_box.add(menu, BorderLayout.NORTH);
        menu_send_box.add(sendBox, BorderLayout.SOUTH);
        messageBox.add(menu_send_box, BorderLayout.SOUTH);
        b.setBounds(130, 100, 100, 40);
        
        f.getContentPane().add(messageBox, BorderLayout.NORTH);
        
        //f.add(b);
        f.setVisible(true);
        
    }

    public static void print_gui(String s){
        textArea.append(s+"\n");
    }

    public static void updateMenu(){
        menu.removeAllItems();
        for(int i=0;i<ip.length;i++){
            if(ip[i]!=null || ip[i]!=""){
                menu.addItem(ip[i]);
            }
        }
    }

    public ServerGui() {

    }

    

    public void run() {
        try {
            int index = Integer.parseInt(Thread.currentThread().getName().replace("Thread-",""));
            while(true){
                
                System.out.println(Thread.currentThread().getName()+" Waiting for client to join");
                client1 = server.accept();
                System.out.println("Client joined");
                ip[index] = client1.getInetAddress().toString().replace("/","");
                updateMenu();
                //PrintWriter out = new PrintWriter(client1.getOutputStream(), true);
                //out.println("Hello Client");
                //out.close();
                Read thread = new Read(client1,textArea);
                thread.start();
                thread.join();
                client1.close();
                ip[index] = "";
                updateMenu();
            }
        } catch (IOException i) {
            System.out.println("UserError:" + i);
        } catch(Exception e){
            System.out.println("UserError:" + e);
        }       
    }

    public static void main(String[] args) {
        setup();
        
        for(int i=0;i<n;i++){
            client[i] = new ServerGui();
            client[i].setName("Thread-"+i);
            client[i].start();
            while(ip[i]==null || ip[i].equals("")){
                try{
                    Thread.sleep(100);
                } catch(Exception e){
                    System.out.println("UserError:" + e);
                }
            }
            print_gui("["+ip[i]+"] has joined the chat");
        }
    }
}
