package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class ChatClient extends JFrame implements ActionListener{

    static JTextArea content; // Khai bÃ¡o ná»™i dung nháº­p liá»‡u
    static JButton send; // Khai bÃ¡o nÃºt gá»­i tin
    static JTextField nhap, toName; // Khai bÃ¡o ná»™i dung nháº­p liá»‡u
    static String chuoi=""; // Táº¡o chuá»—i chÆ°a ná»™i dung nháº­n
    static String temp="",addrr=""; // Táº¡o chuá»—i táº¡m
    static Socket s, sB; // Khai bÃ¡o socket phÃ­a client 
    static PrintWriter gui; // Khai bÃ¡o luá»“n gá»­i tin
    public static void main(String[] args) {
                
          try
          {
            String ip = JOptionPane.showInputDialog(null, "Nháº­p IP mÃ¡y chá»§"); // há»�p thoáº¡i thÃ´ng bÃ¡o yÃªu cáº§u nháº­p ip server
             new ChatClient();  // Khá»Ÿi táº¡o giao diá»‡n
              sB = new Socket(ip,2222); // Káº¿t ná»‘i dá»±a trÃªn ip vá»«a nháº­p vÃ  cá»•ng máº·c Ä‘á»‹nh   
            BufferedReader nhan = new BufferedReader(
                  new InputStreamReader(sB.getInputStream())); // Luá»“ng nháº­n tin
           while((chuoi = nhan.readLine())!= null) // Kiá»ƒm tra xem cÃ³ tin Ä‘á»ƒ nháº­n hay khÃ´ng?
         {
                temp+=chuoi+"\n"; // Cá»™ng chuá»—i tin chat vÃ o biáº¿n táº¡m
                content.setText(temp); // Ä�Æ°a ná»™i dung lÃªn giao diá»‡n
             content.setVisible(false); // Cáº­p nháº­t láº¡i giao diá»‡n
             content.setVisible(true); // Cáº­p nháº­t láº¡i giao diá»‡n
         }
          }
           catch (Exception e) { // Xá»­ lÃ½ ngoáº¡i lá»‡
             e.printStackTrace();                  
          }      
    }
     public ChatClient() { 
            setSize(600,600); // Ä�á»‹nh nghÄ©a kÃ­ch thÆ°á»›c client
            setTitle("Client"); // TiÃªu Ä‘á»�
            Font f = new Font("Arial",Font.BOLD,20);  // Font chá»¯
           
            content = new JTextArea(); // Ná»™i dung chat
            content.setFont(f); // Ä�á»‹nh nghÄ©a font cho ná»™i dung
            content.setBackground(Color.cyan); // Ä�áº·t hÃ¬nh ná»�n
            JScrollPane sp = new JScrollPane(content); // Cho phÃ©p scrolling khi ná»™i dung quÃ¡ dÃ i
            content.setEditable(false); // KhÃ´ng cho phÃ©p chá»‰nh sá»­a ná»™i dung
            send = new JButton("Gá»­i"); // Táº¡o nÃºt gá»­i tin
            nhap = new JTextField(30); // Táº¡o khung nháº­p tÃªn vá»›i chiá»�u dÃ i tá»‘i Ä‘a
            nhap.setFont(f); // Ä�á»‹nh nghÄ©a font cho khung
            toName = new JTextField("Nháº­p tÃªn báº¡n"); // Ä�á»‹nh nghÄ©a ná»™i dung máº·c Ä‘á»‹nh cho khung
            toName.setFont(f); // Ä�áº·t font cho khung
            toName.setBackground(Color.pink); // Ä�áº·t ná»�n cho khung
           
           
            add(toName, BorderLayout.PAGE_START); // Bá»‘ trÃ­ toName á»Ÿ trá»‹ trÃ­ báº¯t Ä‘áº§u
            add(sp, BorderLayout.CENTER); // sp á»Ÿ vá»‹ trÃ­ giá»¯a
            add(nhap, BorderLayout.PAGE_END); // nhap á»Ÿ vá»‹ trÃ­ cuá»‘i
            nhap.addActionListener(this); // Ä�á»‹nh nghÄ©a sá»± kiá»‡n cho khung nháº­p

            setVisible(true); // cáº­p nháº­t giao diá»‡n
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ThoÃ¡t khi Ä‘Ã³ng chÆ°Æ¡ng trÃ¬nh
        }
@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nhap)) // Báº¯t sá»± kiá»‡n khi ngÆ°á»�i dÃ¹ng gá»­i
        {
            try
            {      gui = new PrintWriter(sB.getOutputStream(),true); // luá»“n gá»­i tin
                   gui.println(toName.getText()+": "+nhap.getText()); // Gá»­i tin vÃ  kÃ¨m tÃªn
                   temp+=toName.getText()+": "+nhap.getText()+"\n"; // ThÃªm tin gá»­i vÃ o biáº¿n táº¡m
                   nhap.setText(""); // XÃ³a ná»™i dung nháº­p liá»‡u
                   nhap.requestFocus(); // Focus con trá»� vÃ o khung nháº­p liá»‡u
                   content.setText(temp); // Ä�Æ°a lÃªn giao diá»‡n
                   content.setVisible(false); // Cáº­p nháº­t láº¡i giao diá»‡n
                   content.setVisible(true); // Cáº­p nháº­t láº¡i giao diá»‡n                                            
            }
            catch (Exception r) { // Xá»­ lÃ½ ngoáº¡i lá»‡ khi xáº£y ra lá»—i
                r.printStackTrace();
            }
        }
       
    }
       

}
