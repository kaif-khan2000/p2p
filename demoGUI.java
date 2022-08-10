import javax.swing.*;
import java.awt.*;
public class demoGUI {
    public static JFrame f = new JFrame();
    public static void main(String[] args) {
        String ip[] = {"192.168.134.152","192.168.134.153","192.168.134.154","192.168.134.155"};
        JComboBox menu = new JComboBox(ip);
        menu.setBounds(50, 50,90,20);    
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new FlowLayout());
        
        JButton b = new JButton("Send");
        
        JTextArea textArea = new JTextArea(20,30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.append("Hello world");
        textArea.setEditable(false);
        textArea.append("New string");
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel messageBox = new JPanel();
        JPanel sendBox = new JPanel();
        sendBox.setLayout(new FlowLayout());
        messageBox.setLayout(new BorderLayout());
        messageBox.add(scrollPane, BorderLayout.NORTH);

        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Serif",Font.PLAIN,20));
        sendBox.add(menu, BorderLayout.NORTH);
        sendBox.add(textField,BorderLayout.SOUTH);

        sendBox.add(b,BorderLayout.SOUTH);
        messageBox.add(sendBox, BorderLayout.SOUTH);
        b.addActionListener(l -> {
            String ip1 = (String)menu.getSelectedItem();
            textArea.append("["+ip1+"] : "+textField.getText()+"\n");
            textField.setText("");
        });

        f.getContentPane().add(messageBox, BorderLayout.NORTH);
        
        b.setBounds(130, 100, 100, 40);
        
        //f.add(b);
        f.setVisible(true);
        
    }
}
