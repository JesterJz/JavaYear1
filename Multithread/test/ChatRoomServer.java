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
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;
public class ChatRoomServer extends JFrame implements ActionListener{


   
static JTextArea content; // Khai bÃ¡o Ã´ nháº­p ná»™i dung chat
    static JButton send; // Khai bÃ¡o nÃºt gá»­i ná»™i dung
    static JTextField nhap, toName; // Khai bÃ¡o Ã´ nháº­p tÃªn 
    static String chuoi=""; // Chuá»—i ná»™i dung tin chat giá»¯a 2 mÃ¡y
    static String temp=""; // Chuá»—i táº¡m chá»©a thÃ´ng tin xuáº¥t lÃªn giao diá»‡n
    static ServerSocket serA; // Táº¡o socket server 
    static Socket s, sA; // Táº¡o socket káº¿t ná»‘i
    static PrintWriter gui; // Khai bÃ¡o biáº¿n gá»­i tin
    public static void main(String[] args)
    {
          new ChatRoomServer();  // HÃ m táº¡o Ä‘á»‘i tÆ°á»£ng giao diá»‡n chat Ä‘Ã£ Ä‘á»‹nh nghÄ©a phÃ­a dÆ°á»›i
        
          try
          {
              serA = new ServerSocket(2222); // Táº¡o cá»•ng káº¿t ná»‘i vá»›i server á»Ÿ cá»•ng 2222
              s = serA.accept(); // Cháº¥p nháº­n khi cÃ³ káº¿t ná»‘i 
              while(true) // VÃ²ng láº·p liÃªn tá»¥c khi nháº­n Ä‘Æ°á»£c yÃªu cáº§u tá»« phÃ­a client gá»­i vá»�
              {                         
               BufferedReader nhan = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));  // Luá»“ng nháº­n tin tá»« Client sáº½ Ä‘Æ°á»£c láº¥y qua biáº¿n nÃ y          
                  while((chuoi = nhan.readLine())!= null) // Kiá»ƒm tra xem cÃ³ tin Ä‘á»ƒ nháº­n tá»« Client hay khÃ´ng
                  {
                      temp+=chuoi+"\n"; // Khi tin má»›i Ä‘Æ°á»£c gá»­i vá»� sáº½ Ä‘Æ°á»£c tá»± Ä‘á»™ng xuá»‘ng hÃ ng vÃ  gÃ¡n vÃ o biáº¿n táº¡m
                      content.setText(temp); // Ä�Æ°a dá»¯ liá»‡u chat lÃªn giao diá»‡n
                      content.setVisible(false); // Cáº­p nháº­t láº¡i giao diá»‡n
                      content.setVisible(true);// Cáº­p nháº­t láº¡i giao diá»‡n
                  }
                
              }         
          }
          catch (Exception e) {     // Xá»­ lÃ½ ngoáº¡i lá»‡ khi gáº·p lá»—i
             e.printStackTrace();                  
          }
         
    }
 public ChatRoomServer() {
        setSize(600,600); // Ä�á»‹nh nghÄ©a kÃ­ch thÆ°á»›c giao diá»‡n chat
        setTitle("Server"); // TiÃªu Ä‘á»� giao diá»‡n
        Font f = new Font("Arial",Font.BOLD,20);  // Ä�á»‹nh nghÄ©a Font chá»¯ ná»™i dung   
       
        content = new JTextArea(); // Táº¡o má»›i thÃ nh pháº§n tá»« khai bÃ¡o phÃ­a trÃªn
        content.setFont(f); // Ä�áº·t font Ä‘Ã£ Ä‘á»‹nh nghÄ©a vÃ  Ã´ ná»™i dung chat
        content.setBackground(Color.cyan); // Ä�á»‹nh nghÄ©a Ã´ ná»�n ná»™i dung
        content.setEditable(false); // Ä�áº·t cháº¿ Ä‘á»™ khÃ´ng cÃ³ phÃ©p chá»‰nh sá»­a trÃªn Ã´
        JScrollPane sp = new JScrollPane(content); // Ä�á»‹nh nghÄ©a cho phÃ©p scrolling trÃªn Ã´ ná»™i dung náº¿u ná»™i dung quÃ¡ dÃ i
        send = new JButton("Gá»­i"); // Ä�á»‹nh nghÄ©a nÃºt gá»­i tin
        nhap = new JTextField(30); // Ä�á»‹nh nghÄ©a Ä‘á»™ dÃ i kÃ­ tá»± nháº­p vÃ o khung tÃªn
        nhap.setFont(f); // Ä�áº·t font Ä‘Ã£ khai bÃ¡o phÃ­a trÃªn cho khung tÃªn
        toName = new JTextField("Nháº­p tÃªn báº¡n");  // Ä�áº·t ná»™i dung yÃªu cáº§u máº·c Ä‘á»‹nh
        toName.setFont(f); // Ä�á»‹nh nghÄ©a font cho toName
        toName.setBackground(Color.pink); // Ä�á»‹nh nghÄ©a ná»�n cho toName
       
        add(toName, BorderLayout.PAGE_START); // Chia bá»‘ cá»¥c cho toName náº±m á»Ÿ vá»‹ trÃ­ Ä‘áº§u tiÃªn
        add(sp, BorderLayout.CENTER); // Chia bá»‘ cá»¥c cho sp náº±m á»Ÿ vá»‹ trÃ­ giá»¯a
        add(nhap, BorderLayout.PAGE_END); // Chia bá»‘ cá»¥c cho khung nháº­p liá»‡u á»Ÿ vá»‹ trÃ­ cuá»‘i
        nhap.addActionListener(this); // GÃ¡n sá»± kiá»‡n khi ngÆ°á»�i dÃ¹ng nháº¥n gá»­i
       
        setVisible(true); // Cáº­p nháº­t láº¡i giao diá»‡n
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ThoÃ¡t giao diá»‡n khi Ä‘Ã³ng
    }
 @Override
    public void actionPerformed(ActionEvent e) { // Khai bÃ¡o sá»­ lÃ½ sá»± kiá»‡n
        if (e.getSource().equals(nhap))
        {
            try
            {                                 
               gui = new PrintWriter(s.getOutputStream(),true); // Táº¡o má»›i Ä‘á»‘i tÆ°á»£ng gá»­i tin
               temp+=toName.getText()+": "+nhap.getText()+"\n"; // Cá»™ng dá»“n chuá»—i tin nháº¯n vÃ o biáº¿n táº¡m
               gui.println(toName.getText()+": "+nhap.getText()); // Gá»­i tin qua máº¡ng kÃ¨m tÃªn
               content.setText(temp); // Cáº­p nháº­t láº¡i giao diá»‡n hiá»ƒn thá»‹ ná»™i dung tin
               nhap.setText(""); // XÃ³a trÆ°á»�ng nháº­p liá»‡u
               nhap.requestFocus(); // Focus con chá»� ngay Ã´ nháº­p liá»‡u
               content.setVisible(false); // Cáº­p nháº­t láº¡i giao diá»‡n
               content.setVisible(true); // Cáº­p nháº­t láº¡i giao diá»‡n                                
            }
            catch (Exception r) { // Xá»­ lÃ½ ngoáº¡i lá»‡ khi xáº£y ra lá»—i
                r.printStackTrace();
            }
        }
       
    }
}