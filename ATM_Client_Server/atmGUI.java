import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.print.DocFlavor.READER;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;


public class atmGUI extends JFrame implements ActionListener{
    JPanel btnkeyboard,p2,p3,p4,p5,pThe,pNhanThe,pLoaiThe, pGT_a,pGT_b,pGT_c,pGT_d;
    JButton[] n;
    JLabel tba,tbb,tbc,xn;
    JButton enter,cancel,clear,admin;
    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,layThe,napThe;
    JPasswordField pin,pin1,pin2,pin3;
    JTextField loaiThe, guiTien, taiKhoan, guiTien2;
    JTextField ma,ten,mapin,mathe;
    JComboBox gt;
    Font font;
    String temp = "";
    boolean kt = true, ad = false;
    int chon = -1, khoa = 1, phim = 0, chon2 = 0;
    JLabel tc,td,te,tf,tg,th,ti,tm,mthe;
    JPanel menu;
	atmGUI()
	{
	    getContentPane().setLayout(null);
		setSize(700,700);
		setTitle("chuong trinh mo phong atm");
		font = new Font ("Time New Romances", Font.BOLD ,29 );
		tba = new JLabel("ngan hang abc xin chao quy khach");
		tba.setFont(font); tba.setForeground(Color.yellow); 
		tbb = new JLabel("chua co the");
		tbc = new JLabel(""); tbc.setFont(font); tbc.setForeground(Color.yellow); 
		btn1 = new JButton(); 
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
		btn5 = new JButton();
		btn6 = new JButton();
		btn7 = new JButton();
		btn8 = new JButton();
          
		
		
		n = new JButton[12];
		for (int i=0;i<10;i++)
			n[i] = new JButton(""+i);
		
		
		btnkeyboard = new JPanel(new GridLayout(4,3));
		for (int i=0;i<10;i++)
		btnkeyboard.add(n[i]);
		btnkeyboard.add(new JLabel());
		btnkeyboard.setSize(250,200);
	    btnkeyboard.setLocation(190,460);
	    
	    p2 = new JPanel(new GridLayout(4,1));
	    p2.add(btn1); p2.add(btn2); p2.add(btn3); p2.add(btn4);
	    p2.setSize(100,400);
	    p2.setLocation(0, 30);
	    
	    
	    p3 = new JPanel(new GridLayout(4,1));
	    p3.add(btn5); p3.add(btn6); p3.add(btn7); p3.add(btn8);
	    p3.setSize(100,400);
	    p3.setLocation(600, 30);
	 
	    
	    p4 = new JPanel(new GridLayout(4,1));
	    p4.setSize(500,440);
	    p4.setLocation(100, 5);
	    p4.add(tba);
	    p4.add(tbc);
	    p4.setBackground(Color.blue);
	    
	    
	    p5 = new JPanel(new GridLayout(4,1));
	    enter = new JButton("ENTER"); enter.setEnabled(false);
	    cancel = new JButton("CANCEL"); cancel.setEnabled(false);
	    clear = new JButton("CLEAR"); clear.setEnabled(false);
	    admin = new JButton("admin"); admin.setEnabled(false);
	    p5.add(enter);
	    p5.add(cancel);
	    p5.add(admin);
	    p5.add(clear);
	    p5.setSize(80,200);
	    p5.setLocation(440,460);
	    p5.setBackground(Color.red);
	    
	    
	    pNhanThe = new JPanel(new GridLayout(1,2));
	    pNhanThe.setSize(180,30);
	    pNhanThe.setLocation(520,490);
	    layThe = new JButton("lay the"); layThe.setVisible(false);
	    napThe = new JButton("nap the");
	    pNhanThe.add(layThe);
	    pNhanThe.add(napThe);
	    
	    loaiThe = new JTextField(10); loaiThe.setText("nhap ma the"); loaiThe.setBackground(Color.pink);
	    pLoaiThe = new JPanel();
	    pLoaiThe.setSize(180,30);
	    pLoaiThe.setLocation(520,520);
	    pLoaiThe.add(loaiThe);
	    layThe.addActionListener(this);
	    napThe.addActionListener(this);
	    
	    for (int i=0;i<10;i++)
			n[i].addActionListener(this);
	    enter.addActionListener(this);
	    clear.addActionListener(this);
	    cancel.addActionListener(this);
		admin.addActionListener(this);
		
	    pThe = new JPanel();
	    pThe.add(tbb);
	    pThe.setSize(200,20);
	    pThe.setLocation(500,470);
	    pThe.setBackground(Color.cyan);
	    
	    btn1.addActionListener(this);
	    btn2.addActionListener(this);
	    btn3.addActionListener(this);
	    btn4.addActionListener(this);
	    btn5.addActionListener(this);
	    btn6.addActionListener(this);
	    btn7.addActionListener(this);
	    btn8.addActionListener(this);
	    
	
	    tc = new JLabel("<html><p>Ä�á»•i mÃ£</p> PIN</html>"); tc.setForeground(Color.yellow); tc.setFont(font);
	    td = new JLabel("<html><p>Sá»‘ dÆ° </p>tÃ i khoáº£n</html>"); td.setHorizontalAlignment(JLabel.RIGHT); td.setForeground(Color.yellow); td.setFont(font);
	    te = new JLabel("<html><p>In thÃ´ng tin</p>cÃ¡ nhÃ¢n</html>"); te.setForeground(Color.yellow); te.setFont(font);
	    tf = new JLabel("<html><p>Gá»­i tiá»�n vÃ o</p>tÃ i khoáº£n</html>");tf.setHorizontalAlignment(JLabel.RIGHT);  tf.setForeground(Color.yellow); tf.setFont(font);
	    tg = new JLabel("<html><p>In lá»‹ch sá»­</p>giao dá»‹ch</html>"); tg.setForeground(Color.yellow); tg.setFont(font);
	    th = new JLabel("Chuyá»ƒn khoáº£n"); th.setHorizontalAlignment(JLabel.RIGHT); th.setForeground(Color.yellow); th.setFont(font);
	    ti = new JLabel("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>"); ti.setForeground(Color.yellow); ti.setFont(font);
	    tm = new JLabel("RÃºt tiá»�n");tm.setHorizontalAlignment(JLabel.RIGHT);  tm.setForeground(Color.yellow); tm.setFont(font);
	    xn = new JLabel("XÃ¡c nháº­n"); xn.setFont(font); xn.setForeground(Color.yellow); 
	    mthe = new JLabel("MÃ£ tháº»: "); mthe.setHorizontalAlignment(JLabel.RIGHT); mthe.setForeground(Color.yellow); mthe.setFont(font);
	    ma = new JTextField(); ma.setFont(font); ma.setBackground(Color.pink);
	    ten = new JTextField(); ten.setFont(font); ten.setBackground(Color.pink);
	    gt = new JComboBox(); gt.setFont(font); gt.setBackground(Color.pink);
	    gt.addItem("Nam"); gt.addItem("Nu");
	    mapin = new JTextField(); mapin.setFont(font); mapin.setBackground(Color.pink);
	    mathe = new JTextField(); mathe.setFont(font); mathe.setBackground(Color.pink);
	    pin = new JPasswordField(4); 
	    

	    pin1 = new JPasswordField();pin1.setFont(new Font("Time New Romances",Font.BOLD,40));pin1.setEditable(false);
	    pin2 = new JPasswordField();pin2.setFont(new Font("Time New Romances",Font.BOLD,40));pin2.setEditable(false);
	    pin3 = new JPasswordField();pin3.setFont(new Font("Time New Romances",Font.BOLD,40));pin3.setEditable(false);
	    guiTien = new JTextField(); guiTien.setFont(font);
	    taiKhoan = new JTextField(); taiKhoan.setFont(font);
	    guiTien2 = new JTextField(); guiTien2.setFont(font);guiTien2.setEditable(false);
	    
	    menu = new JPanel(new GridLayout(4,2)); 
	    menu.add(tc);menu.add(td);
	    menu.add(te);menu.add(tf);
	    menu.add(tg);menu.add(th);
	    menu.add(ti);menu.add(tm);
	    menu.setSize(500,440);
	    menu.setLocation(100, 5);
	    menu.setBackground(Color.blue);
	    
	    getContentPane().add(btnkeyboard);
	    getContentPane().add(p2);
	    getContentPane().add(p3);
	    getContentPane().add(p4);
	    getContentPane().add(p5);
	    getContentPane().add(pThe);
	    getContentPane().add(pNhanThe);
	    getContentPane().add(pLoaiThe);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmExe user = new atmExe();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(layThe))
		{
			if (ad)
			{
		    menu.setVisible(false);
		    remove(menu);
			p4 = new JPanel(new GridLayout(4,1));
		    p4.setSize(500,440);
		    p4.setLocation(100, 5);
			p4.add(tba);
			p4.add(tbc);
			p4.setBackground(Color.blue);
			getContentPane().add(p4);
			kt = false;
			loaiThe.setText("Nháº­p mÃ£ tháº»");
			loaiThe.setEditable(true);
			tbc.setText("");
			enter.setEnabled(false);
		    cancel.setEnabled(false);
		    clear.setEnabled(false);
		    admin.setEnabled(false);
			napThe.setVisible(true);
			layThe.setVisible(false);
			tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
			tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
		    pin.setVisible(false);
		    temp = "";
			p4.remove(pin);
			btn5.setText("");
			btn6.setText("");
			btn4.setText("");
			btn8.setText("");
			chon = -1;
			}
			else
			{
				kt = false;
				loaiThe.setText("Nháº­p mÃ£ tháº»");
				loaiThe.setEditable(true);
				tbc.setText("");
				enter.setEnabled(false);
			    cancel.setEnabled(false);
			    clear.setEnabled(false);
			    admin.setEnabled(false);
				napThe.setVisible(true);
				layThe.setVisible(false);
				tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
				tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
			    pin.setVisible(false);
			    temp = "";
				p4.remove(pin);
				btn5.setText("");
				btn6.setText("");
				btn4.setText("");
				btn8.setText("");
				chon = -1;
			}
		    
		} else if (e.getSource().equals(napThe))
		{
			if ( loaiThe.getText().toString().compareTo("")==0 || loaiThe.getText().toString().compareTo("Nháº­p mÃ£ tháº»")==0) 
				{
				   loaiThe.setText("");
				   loaiThe.requestFocus();
				}		
			else
			{
			if (loaiThe.getText().toString().compareTo("AAA") == 0)
			{
			ad = true; 
			kt = true;
			admin.setEnabled(true);
			p4.setVisible(false);
			remove(p4);
			admin("<html><p>Xin chÃ o quáº£n trá»‹ viÃªn ATM</p><html>");
			napThe.setVisible(false);
		   	layThe.setVisible(true);
		   	ma.setText("");
			ten.setText("");
			mapin.setText("");
			mathe.setText("");
			chon = 90;
			chon = -1;
			}
			else 
		     {
			 ad = false;
			 kt = true;
			 loaiThe.setEditable(false);
			 admin.setEnabled(true);
			 enter.setEnabled(true);
			 clear.setEnabled(true);
			 cancel.setEnabled(true);
			 tbc.setText("Nháº­p mÃ£ PIN");
			 napThe.setVisible(false);
		   	 layThe.setVisible(false);
			 tbb.setText(">>>Ä�Ã£ cÃ³ tháº»<<<");
			 tba.setText("<html><p>Xin chÃ o quÃ½ khÃ¡ch Há»‡ thá»‘ng yÃªu cáº§u mÃ£ PIN </p></html>");
			 pin = new JPasswordField(4); pin.setEditable(false); pin.setFont(new Font("Time New Romances",Font.BOLD,40));
			 p4.add(pin);
			 btn5.setText("Nháº­p");
			 btn6.setText("XÃ³a");
			 btn4.setText("<html><p>Há»§y bá»�</p> giao dá»‹ch<html>");
			 temp = "";
			 chon = 0;
		     }
			}
		} else if (e.getSource().equals(n[0]))
		{
			if (kt)
			{
			 temp+=0;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
					if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[1]))
		{
			if (kt)
			{
			 temp+=1;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[2]))
		{
			if (kt)
			{
			 temp+=2;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[3]))
		{
			if (kt)
			{
			 temp+=3;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[4]))
		{
			if (kt)
			{
			 temp+=4;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[5]))
		{
			if (kt)
			{
			 temp+=5;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[6]))
		{
			if (kt)
			{
			 temp+=6;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[7]))
		{
			if (kt)
			{
			 temp+=7;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[8]))
		{
			if (kt)
			{
			 temp+=8;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[9]))
		{
			if (kt)
			{
			 temp+=9;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(enter))
		{
			
			if (kt)
			{
				if (chon == 0)
				{
					if (pin.getText().toString().length() == 4)
					{
					 if (atmExe.checkPin(pin.getText(),loaiThe.getText()))
					 {
						khoa = 1;
						chon = 1;
						p4.setVisible(false);
						remove(p4);
						  tc.setText("<html><p>Ä�á»•i mÃ£</p> PIN</html>");
						    td.setText("<html><p>Sá»‘ dÆ° </p>tÃ i khoáº£n</html>");
						    te.setText("<html><p>In thÃ´ng tin</p>cÃ¡ nhÃ¢n</html>");
						    tf.setText("<html><p>Gá»­i tiá»�n vÃ o</p>tÃ i khoáº£n</html>");
						    tg.setText("<html><p>In lá»‹ch sá»­</p>giao dá»‹ch</html>");
						    th.setText("Chuyá»ƒn khoáº£n");
						    ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
						    tm.setText("RÃºt tiá»�n");
						menu = new JPanel(new GridLayout(4,2)); 
						menu.add(tc);menu.add(td);
						menu.add(te);menu.add(tf);
						menu.add(tg);menu.add(th);
						menu.add(ti);menu.add(tm);
						menu.setSize(500,440);
						menu.setLocation(100, 5);
						menu.setBackground(Color.blue);
						
						getContentPane().add(menu);
						btn4.setText("");
						btn5.setText("");
						btn6.setText("");
						guiTien.setText("");
						taiKhoan.setText("");
						try
						{
						ResultSet dl = atmExe.getDataAcc();
						dl.next(); tbb.setText(dl.getString("HOTEN").toString());
						}
						catch (Exception loi) {
							loi.printStackTrace();
						}
					 }else{
						if (khoa == 4) 
						{
							btn4.setEnabled(false);	
							cancel.setEnabled(false);
						}
						tbc.setText("Sai mÃ£ PIN");
						if (khoa > 3) tbc.setText("<html><p>Báº¡n Ä‘Ã£ nháº­p sai quÃ¡ 4 láº§n</p>há»‡ thá»‘ng sáº½ giá»¯ tháº»</html>");
						pin.setText("");
						temp="";
						khoa++;
					 }
				   } else
					   {
					       tbc.setText("MÃ£ PIN tá»‘i Ä‘a 4 kÃ­ tá»±");
					       temp="";
					   }
				}
			}
			
		}else if (e.getSource().equals(cancel))
		{
			
			if (kt)
			{ 
				pin1.setText("");
				pin2.setText("");
				pin3.setText("");
				if (chon == 1 || chon == 2 || chon == 3 || chon == 4 || chon == 5 || chon == 6 || chon == 7)
				{
					temp="";
					chon = -1;
					btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					menu.setVisible(false);
					remove(menu);					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
					tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
				    temp = "";
					btn5.setText("");
					btn6.setText("");
					btn4.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
				}
				else 
			 {
				kt = false;
				tbc.setText("");
				enter.setEnabled(false);
			    cancel.setEnabled(false);
			    clear.setEnabled(false);
			    admin.setEnabled(false);
				napThe.setVisible(false);
				layThe.setVisible(true);
				tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
				tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
			    pin.setVisible(false);
			    temp = "";
				p4.remove(pin);
				btn5.setText("");
				btn6.setText("");
				btn4.setText("");
				layThe.setVisible(true);
			 }
			}
			
		}
		else if (e.getSource().equals(clear))
		{
			if (kt)
			{
				pin.setText("");
				temp="";
				tbc.setText("Nháº­p mÃ£ PIN");
			    if (chon == 2)
			    {
			    	pin1.setText("");
			    	pin2.setText("");
			    	pin3.setText("");
			    }
			    if (chon == 4)
			    {
			    	guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);
					temp="";
			    }
			    if (chon == 5)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);

					taiKhoan.setVisible(false);
					taiKhoan.setText("");
					taiKhoan.setVisible(true);
					temp="";
				}
				 if (chon == 7)
					{
					    guiTien2.setVisible(false);
						guiTien2.setText("");
						guiTien2.setVisible(true);
						temp="";
					}
			}
		}else if (e.getSource().equals(btn1))
		{
			if (kt)
			{ 
				if (chon == 1 || chon == 10)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("Giao dá»‹ch khÃ¡c");
					th.setText("XÃ¡c nháº­n");
					tm.setText("PIN cÅ©: ");
					td.setText("PIN má»›i: ");
					te.setText("Nháº­p láº¡i PIN má»›i: ");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(th);
				    menu.add(tm);menu.add(pin1);
				    menu.add(td);menu.add(pin2);
				    menu.add(te);menu.add(pin3);	    
				    menu.setSize(500,440);
				    menu.setLocation(100, 5);
				    menu.setBackground(Color.blue);
				    getContentPane().add(menu);
				    btn6.setText("");
				    btn7.setText("");
				    btn8.setText("");
				    chon = 2;
				} else
				if (chon == 2)
				{
					btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					 pin1.setText("");
					 pin2.setText("");
					 pin3.setText("");
					menu.setVisible(false);
					remove(menu);
					    tc.setText("<html><p>Ä�á»•i mÃ£</p> PIN</html>");
					    td.setText("<html><p>Sá»‘ dÆ° </p>tÃ i khoáº£n</html>");
					    te.setText("<html><p>In thÃ´ng tin</p>cÃ¡ nhÃ¢n</html>");
					    tf.setText("<html><p>Gá»­i tiá»�n vÃ o</p>tÃ i khoáº£n</html>");
					    tg.setText("<html><p>In lá»‹ch sá»­</p>giao dá»‹ch</html>");
					    th.setText("Chuyá»ƒn khoáº£n");
					    ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					    tm.setText("RÃºt tiá»�n");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					btn4.setText("");
					btn5.setText("");
					btn6.setText("");
					chon = 1;
				}
				if (chon == 3 || chon == 4 || chon == 5 || chon == 6)
				{
					btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					 pin1.setText("");
					 pin2.setText("");
					 pin3.setText("");
					menu.setVisible(false);
					remove(menu);
					    tc.setText("<html><p>Ä�á»•i mÃ£</p> PIN</html>");
					    td.setText("<html><p>Sá»‘ dÆ° </p>tÃ i khoáº£n</html>");
					    te.setText("<html><p>In thÃ´ng tin</p>cÃ¡ nhÃ¢n</html>");
					    tf.setText("<html><p>Gá»­i tiá»�n vÃ o</p>tÃ i khoáº£n</html>");
					    tg.setText("<html><p>In lá»‹ch sá»­</p>giao dá»‹ch</html>");
					    th.setText("Chuyá»ƒn khoáº£n");
					    ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					    tm.setText("RÃºt tiá»�n");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					btn4.setText("");
					btn5.setText("");
					btn6.setText("");
					chon = 1;
				}
				
			if (chon == 8)	
			{
				guiTien.setText("");
				menu.setVisible(false);
				remove(menu);
				tc.setText("<html><p>Giao dá»‹ch</p>khÃ¡c</html>");
				th.setText("XÃ¡c nháº­n");
				tf.setText("Nháº­p sá»‘ tiá»�n cáº§n gá»­i");
				ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
				tm.setText("XÃ³a");
				pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
				pGT_a.add(tc);pGT_a.add(th);
				pGT_b = new JPanel(new GridLayout(1,1)); pGT_b.setBackground(Color.blue);
				pGT_b.add(tf);
				pGT_c = new JPanel(new GridLayout(1,1)); pGT_c.setBackground(Color.blue);
				pGT_c.add(guiTien); guiTien.setEditable(false);
				pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
				pGT_d.add(ti); pGT_d.add(tm);
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(pGT_a);
				menu.add(pGT_b);
				menu.add(pGT_c);
				menu.add(pGT_d);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 4;
				temp="";
			 }
				
				if (chon == 9)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dá»‹ch</p>khÃ¡c</html>");
					th.setText("XÃ¡c nháº­n");
					tf.setText("Tiá»�n gá»­i: ");
					td.setText("TÃ i khoáº£n nháº­n");
					ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					tm.setText("XÃ³a");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,2)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);pGT_b.add(guiTien);guiTien.setEditable(false);
					pGT_c = new JPanel(new GridLayout(1,2)); pGT_c.setBackground(Color.blue);
					pGT_c.add(td); pGT_c.add(taiKhoan);taiKhoan.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 5;
					temp="";
				}	
			if (chon == 99 || chon == 7)
			{
				guiTien2.setText("");
				phim = 0;
				menu.setVisible(false);
				remove(menu);
				xn.setVisible(false);
				tc.setText("Giao dá»‹ch khÃ¡c");
				td.setText("100.000 VNÄ�");
				tf.setText("200.000 VNÄ�");	
				th.setText("500.000 VNÄ�");
				ti.setText("Há»§y bá»� giao dá»‹ch");
				tm.setText("Nháº­p sá»‘ khÃ¡c");
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(tc);menu.add(td);
				menu.add(guiTien2);menu.add(tf);
				menu.add(xn);menu.add(th);
				menu.add(ti);menu.add(tm);
				guiTien2.setBackground(Color.blue);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 6;
			}
		}
			
				
		}
		else if (e.getSource().equals(btn2))
		{
			if (kt)
			{
				if (chon == 1)
				{			 
					
					try
					{
					ResultSet kq =  atmExe.getDataAcc();
					kq.next();
					JOptionPane.showMessageDialog(null,"MÃ£ khÃ¡ch hÃ ng: "+kq.getString("MKH")+"\n" +
							"Há»� tÃªn: "+kq.getString("HOTEN")+"\nGiá»›i tÃ­nh: "+kq.getString("GIOITINH")+
							"\nSá»‘ dÆ° tÃ i khoáº£n: "+kq.getString("TIEN")+"\nMÃ£ PIN: "+kq.getString("PIN")+"\nMÃ£ tháº»: "+kq.getString("MATHE")+"","ThÃ´ng tin cÃ¡ nhÃ¢n",JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception loi) {
						
					}
				}
				if (chon == 7)
				{
					phim = 1;
				}
				
			}
		}
		else if (e.getSource().equals(btn3))
		{
			if (kt)
			{ 
				if (chon == 1)
				{
					try
					{
				    Calendar cal = Calendar.getInstance();	
					String tg = cal.get(Calendar.HOUR_OF_DAY) +" giá»� "+ cal.get(Calendar.MINUTE)+ " phÃºt ";
					ResultSet kq =  atmExe.getDataHis();
					kq.next();
					JOptionPane.showMessageDialog(null,"MÃ£ khÃ¡ch hÃ ng: "+kq.getString("MKH")+"\n" +
							"Há»� tÃªn: "+kq.getString("HOTEN")+"\nHÃ´m nay: "+kq.getString("NGAYGD")+
							"\nThá»�i gian xuáº¥t phiáº¿u: "+tg+"\nTiá»�n rÃºt: "+kq.getString("TIENRUT")+
							"\nTiá»�n gá»­i: "+kq.getString("TIENGUI")+
							"\nTiá»�n chuyá»ƒn: "+kq.getString("TIENCHUYEN")+"","Lá»‹ch sá»­ giao dá»‹ch gáº§n nháº¥t",JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception loi) {
						
					}
				}
				
				if (chon == 7 || chon == 99)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong(guiTien2.getText().toString())))
						{
							thongBao("<html><p>Xin vui lÃ²ng chá»� trong giÃ¢y lÃ¡t.... Má»�i quÃ½ khÃ¡ch nháº­n tiá»�n á»Ÿ khe bÃªn dÆ°á»›i</p></html>", 7);
							
						}else
						{
							thongBao(7);
							guiTien2.setText("4");
						}
					}
					catch (Exception loi) {
						thongBao("<html><p>QuÃ½ khÃ¡ch vui lÃ²ng kiá»ƒm tra nháº­p liá»‡u</p></html>",7);
					}
				}
				
			}
		}
		else if (e.getSource().equals(btn4))
		{
			
			if (kt)
			{
				if (chon == 0)
				{
					temp="";
					 btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
					tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
				    pin.setVisible(false);
				    temp = "";
					p4.remove(pin);
					btn5.setText("");
					btn6.setText("");
					btn4.setText("");
					layThe.setVisible(true);
				}
				if (chon == 1)
				{
					chon = -1;
					temp="";
					 btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					menu.setVisible(false);
					remove(menu);
					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
					tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
				    temp = "";
					btn5.setText("");
					btn6.setText("");
					btn4.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
				}
                if (chon == 3 || chon == 4 || chon == 5 || chon == 6 || chon == 7)
                {
                	temp="";
                	chon = -1;
                	btn1.setText("");
					 btn2.setText("");
					 btn3.setText("");
					 btn4.setText("");
					 btn5.setText("");
					 btn6.setText("");
					 btn7.setText("");
					 btn8.setText("");
					menu.setVisible(false);
					remove(menu);					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>ChÆ°a cÃ³ tháº»<<<");
					tba.setText("<html><p>CÃ¡m Æ¡n quÃ½ khÃ¡ch Ä‘Ã£ sá»­ dá»¥ng</p><p>dá»‹ch vá»¥ cá»§a chÃºng tÃ´i</p>Xin vui lÃ²ng Ä‘á»ƒ tháº» vÃ o....</html>");
				    temp = "";
					btn5.setText("");
					btn6.setText("");
					btn4.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
                }
                if (chon == 90)
				{
                 if (ma.getText().toString().compareTo("") == 0 || ma.getText().toString().compareTo("Nháº­p vÃ o") == 0) ma.setText("Nháº­p vÃ o");
                 else
                 {
                	if (mapin.getText().toString().length() == 4)
                	{     		
                		  try
                		  { 
                		   if (ten.getText().toString().compareTo("") == 0 || ten.getText().toString().compareTo("Nháº­p vÃ o") == 0) ten.setText("Nháº­p vÃ o");
                		   else  
                		   {  
                			if (mathe.getText().toString().length() == 3)
                			{	
                			  if (atmExe.ThemKH(ma.getText(), ten.getText(), gt.getSelectedItem().toString(), mapin.getText(), mathe.getText())){
                				ma.setText("Ä�Ã£ thÃªm");
            					ten.setText("Ä�Ã£ thÃªm");
            					mapin.setText("Ä�Ã£ thÃªm");          					
            					mathe.setText("Ä�Ã£ thÃªm");
                			  }else {
                				if (atmExe.LOI == 10) mathe.setText(atmExe.getLoi());
                				if (atmExe.LOI == 11) ma.setText(atmExe.getLoi());
                				if (atmExe.LOI == 12) ma.setText(atmExe.getLoi());
                				if (atmExe.LOI == 13) mapin.setText(atmExe.getLoi());
                				if (atmExe.LOI == 14) ma.setText(atmExe.getLoi());
                			  }
                			}else mathe.setText("Tá»‘i Ä‘a 3 kÃ­ tá»±");
                		   }
                		  }
                		  catch (Exception loi) {
						  }                  	
                	}
                	else 
                		{
                		  mapin.setText("Nháº­p 4 sá»‘");
                		  mapin.requestFocus();
                		}
                 }
				}
                
			}
		}
		else if (e.getSource().equals(btn5))
		{
			
			if (kt)
			{ 
				if (chon == 0)
			 {		
				if (pin.getText().toString().length() == 4)
				{
			      if (atmExe.checkPin(pin.getText(),loaiThe.getText()))
				  {
					khoa = 1;
					chon = 1;
					p4.setVisible(false);
					remove(p4);
					tc.setText("<html><p>Ä�á»•i mÃ£</p> PIN</html>");
					td.setText("<html><p>Sá»‘ dÆ° </p>tÃ i khoáº£n</html>");
					te.setText("<html><p>In thÃ´ng tin</p>cÃ¡ nhÃ¢n</html>");
					tf.setText("<html><p>Gá»­i tiá»�n vÃ o</p>tÃ i khoáº£n</html>");
					tg.setText("<html><p>In lá»‹ch sá»­</p>giao dá»‹ch</html>");
					th.setText("Chuyá»ƒn khoáº£n");
					ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					tm.setText("RÃºt tiá»�n");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					btn4.setText("");
					btn5.setText("");
					btn6.setText("");
					guiTien.setText("");
					taiKhoan.setText("");
					try
					{
					ResultSet dl = atmExe.getDataAcc();
					dl.next(); tbb.setText(dl.getString("HOTEN").toString());
					}
					catch (Exception loi) {
						loi.printStackTrace();
					}
				   } else
				  {
					if (khoa == 4) 
					{		
						btn4.setEnabled(false);	
						cancel.setEnabled(false);
					}
					tbc.setText("Sai mÃ£ PIN");
					if (khoa > 3) tbc.setText("<html><p>Báº¡n Ä‘Ã£ nháº­p sai quÃ¡ 4 láº§n</p>há»‡ thá»‘ng sáº½ giá»¯ tháº»</html>");
					khoa++;
					pin.setText("");
					temp="";
				  }
				} 
				else
				   {
				       tbc.setText("MÃ£ PIN tá»‘i Ä‘a 4 kÃ­ tá»±");
				       temp="";
				   }	
			 }
				else
			if (chon == 2)
				{
				  if (pin.getText().toString() == "" || pin2.getText().toString() =="" || pin3.getText().toString() == "")
					thongBao("Kiá»ƒm tra láº¡i khung nháº­p liá»‡u",10);
				  else  if ( pin2.getText().toString().compareTo(pin3.getText().toString()) == 0) 
					{
						if (pin2.getText().toString().length() == 4)
						{
						   if (atmExe.changePin(pin1.getText().toString(), pin2.getText().toString()))
						   thongBao("Ä�Ã£ thay Ä‘á»•i MÃ£ PIN",10);
							else  thongBao(10);
					    }
						else thongBao("MÃ£ PIN tá»‘i Ä‘a 4 kÃ­ tá»±",10);
					}
					else thongBao("MÃ£ PIN má»›i khÃ´ng trÃ¹ng khá»›p",10);   
					
				}
			else
			if (chon == 1)
			{
				menu.setVisible(false);
				remove(menu);
				tc.setText("<html><p>Giao dá»‹ch</p>khÃ¡c</html>");
				try
				{
					ResultSet kq = atmExe.getDataAcc();
					kq.next();
					td.setText("<html><p>Sá»‘ dÆ°:</p>"+kq.getString("TIEN")+" VNÄ�</html>");
				}
				catch (Exception loi) {
					loi.printStackTrace();
				}
				te.setText("");
				tf.setText("");
				tg.setText("");
				th.setText("");
				ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
				tm.setText("");
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(tc);menu.add(td);
				menu.add(te);menu.add(tf);
				menu.add(tg);menu.add(th);
				menu.add(ti);menu.add(tm);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 3;
			  } else
			if (chon == 4)
			{
				try
				{
				   long tem = Long.parseLong(guiTien.getText().toString());
				   if (tem > 50000000) {
					   guiTien.setVisible(false);
					   guiTien.setText("");
					   guiTien.setVisible(true);
					   temp="";
					   thongBao("MÃ¡y chá»‰ nháº­n má»—i láº§n tá»‘i Ä‘a 50 triá»‡u",8);
				   }
				   else
				   try
				   {
					if (atmExe.sendMoney(tem)) {
						   guiTien.setVisible(false);
						   guiTien.setText("");
						   guiTien.setVisible(true);
						   temp="";
						   thongBao("<html><p>Giao dá»‹ch thÃ nh cÃ´ng, Vui lÃ²ng</p>kiá»ƒm tra sá»‘ dÆ° tÃ i khoáº£n</html>",8);
					}
					else {
						   guiTien.setVisible(false);
						   guiTien.setText("");
						   guiTien.setVisible(true);
						   temp="";
						   thongBao(8);
						   
					}
				   }
				   catch (Exception loi) {
					loi.printStackTrace();
			  	   }
				}
				catch (Exception loi) {
					guiTien.setVisible(false);
					   guiTien.setText("");
					   guiTien.setVisible(true);
					   temp="";
					   thongBao("Xin vui lÃ²ng nháº­p thÃ´ng tin",8);
				}
			}
			
			else
			if (chon == 6)
			{
				try
				{
					if (atmExe.getMonney(Long.parseLong("100000")))
					{
						thongBao("<html><p>Xin vui lÃ²ng chá»� trong giÃ¢y lÃ¡t.... Má»�i quÃ½ khÃ¡ch nháº­n tiá»�n á»Ÿ khe bÃªn dÆ°á»›i</p></html>",99);
					}else
					{
						thongBao(99);
					}
				}
				catch (Exception loi) {
					thongBao(99);
				}
			}
			else
			if (chon == 5)
			{
				try
				{
				   long tem = Long.parseLong(guiTien.getText().toString());
				   ResultSet kq = atmExe.getDataAcc();
				   kq.next();
				   if (tem > 20000000) {
					   thongBao("Limited 20 milion",9);
					   temp="";
				   }else
					   if (taiKhoan.getText().toString().compareTo(kq.getString("MKH").toString()) == 0)
					   {
						   thongBao("<html><p>QuÃ½ khÃ¡ch khÃ´ng thá»ƒ gá»­i cho chÃ­nh tÃ i khoáº£n cá»§a quÃ½ khÃ¡ch, Vui lÃ²ng kiá»ƒm tra láº¡i</p></html>", 9);						
					   }		   
				   else
				   try
				   {
					if (atmExe.moveMoney(taiKhoan.getText().toString(), tem)) {
						thongBao("<html><p>Giao dá»‹ch thÃ nh cÃ´ng, quÃ½ khÃ¡ch vui lÃ²ng kiá»ƒm tra láº¡i sá»‘ dÆ°</p></html>", 9);						
						temp="";
					}
					else {
						guiTien.setVisible(false);
						guiTien.setText("");
						guiTien.setVisible(true);
						taiKhoan.setVisible(false);
						taiKhoan.setText("");
						taiKhoan.setVisible(true);
						thongBao(9);
						temp="";
					}
				   }
				   catch (Exception loi) {
					loi.printStackTrace();
					thongBao(9);
			  	   }
				}
				catch (Exception loi) {
					thongBao("Vui lÃ²ng kiá»ƒm tra nháº­p liá»‡u", 9);
					temp="";
				}
			}
			else
				if (chon == 90)
				{
					try
					{
					  if (ma.getText().toString().compareTo("") != 0)
					  {
						  if (ma.getText().toString().compareTo("KhÃ´ng tá»“n táº¡i") != 0)
						  {
					      if (ma.getText().toString().compareTo("Nháº­p mÃ£ cáº§n xÃ³a") != 0)
							{
							  if (atmExe.XoaKH(ma.getText()))
							   {
								ma.setText("Ä�Ã£ xÃ³a");
							   } else
							   {
								ma.setText("KhÃ´ng tá»“n táº¡i");
							   }
							}
						  }
						  else ma.setText("Nháº­p mÃ£ cáº§n xÃ³a");
					  }else ma.setText("Nháº­p mÃ£ cáº§n xÃ³a");
					}
					catch (Exception loi) {
						loi.printStackTrace();
					}
				}
		  }
		}
		else if (e.getSource().equals(btn6))
		{
			if (kt)
			{ 
				if (chon == 0)
				{
					pin.setText("");
					temp="";
					tbc.setText("Nháº­p mÃ£ PIN");
				}
				if (chon == 2)
				{
					phim = 1; temp="";
				}
				if (chon == 1)
				{
				    guiTien.setText("");
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dá»‹ch</p>khÃ¡c</html>");
					th.setText("XÃ¡c nháº­n");
					tf.setText("Nháº­p sá»‘ tiá»�n cáº§n gá»­i");
					ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					tm.setText("XÃ³a");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,1)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);
					pGT_c = new JPanel(new GridLayout(1,1)); pGT_c.setBackground(Color.blue);
					pGT_c.add(guiTien); guiTien.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 4;
					temp="";
				}
				if (chon == 5) {
					phim = 1;
					temp="";
				}
				if (chon == 6)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong("200000")))
						{
							thongBao("<html><p>Xin vui lÃ²ng chá»� trong giÃ¢y lÃ¡t.... Má»�i quÃ½ khÃ¡ch nháº­n tiá»�n á»Ÿ khe bÃªn dÆ°á»›i</p></html>",99);
						}else
						{
							thongBao(99);
						}
					}
					catch (Exception loi) {
						thongBao(99);
					}
				}
				if (chon == 90)
				{
					ma.setText("");
					ten.setText("");					
					mapin.setText("");
					mathe.setText("");
				}
			}
		}
		else if (e.getSource().equals(btn7))
		{
			if (kt)
			{ 
				if (chon == 2)
				{
					phim = 
						2;temp="";
				}
				if (chon == 4)
				{
					temp="";
				}
				if (chon == 1)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dá»‹ch</p>khÃ¡c</html>");
					th.setText("XÃ¡c nháº­n");
					tf.setText("Tiá»�n gá»­i: ");
					td.setText("TÃ i khoáº£n nháº­n");
					ti.setText("<html><p>Há»§y bá»� </p>giao dá»‹ch</html>");
					tm.setText("XÃ³a");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,2)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);pGT_b.add(guiTien);guiTien.setEditable(false);
					pGT_c = new JPanel(new GridLayout(1,2)); pGT_c.setBackground(Color.blue);
					pGT_c.add(td); pGT_c.add(taiKhoan);taiKhoan.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 5;
					temp="";
				}
				if (chon == 5){
					phim = 2;
				    temp="";
				}
				if (chon == 6)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong("500000")))
						{
							thongBao("<html><p>Xin vui lÃ²ng chá»� trong giÃ¢y lÃ¡t.... Má»�i quÃ½ khÃ¡ch nháº­n tiá»�n á»Ÿ khe bÃªn dÆ°á»›i</p></html>",99);
						}else
						{
							thongBao(99);
						}
					}
					catch (Exception loi) {
						thongBao(99);
					}
				}
			}
		}
		else if (e.getSource().equals(btn8))
		{
			if (kt)
			{
				if (chon == 2)
				{
					phim = 3;temp="";
				}

				if (chon == 4)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);
					temp="";
				}
				if (chon == 5)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);

					taiKhoan.setVisible(false);
					taiKhoan.setText("");
					taiKhoan.setVisible(true);
					temp="";
				}
				if (chon == 1)
				{
					guiTien2.setText("");
					phim = 0;
					menu.setVisible(false);
					remove(menu);
					xn.setVisible(false);
					tc.setText("Giao dá»‹ch khÃ¡c");
					td.setText("100.000 VNÄ�");
					tf.setText("200.000 VNÄ�");	
					th.setText("500.000 VNÄ�");
					ti.setText("Há»§y bá»� giao dá»‹ch");
					tm.setText("Nháº­p sá»‘ khÃ¡c");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(guiTien2);menu.add(tf);
					menu.add(xn);menu.add(th);
					menu.add(ti);menu.add(tm);
					guiTien2.setBackground(Color.blue);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 6;
				}else
				if (chon == 6)
				{
					guiTien2.setBackground(Color.pink);
		         	xn.setVisible(true);
		         	tc.setText("Trá»Ÿ vá»�");
		         	td.setText("");
					tf.setText("");	
					th.setText("");
					tm.setText("XÃ³a");
					temp="";
					chon = 7;
				}else
					if (chon == 7)
					{
						guiTien2.setText("");
						temp="";
					}
					else if (chon == 90)
					{
						try
						{
						  ResultSet kq = atmExe.getDataAccFull();
						  kq.last();
						  int hang = kq.getRow();
						  String[] cot = {"MÃ£","Há»� tÃªn","Giá»›i tÃ­nh","PIN","Tiá»�n","MÃ£ Tháº»"};
						  Object[][] giaTri = new Object[hang][6];
						  kq.beforeFirst();
						  int i=0;
						  while(kq.next())
						  {
							  giaTri[i][0] = kq.getString("MKH");
							  giaTri[i][1] = kq.getString("HOTEN");
							  giaTri[i][2] = kq.getString("GIOITINH");
							  giaTri[i][3] = kq.getString("PIN");
							  giaTri[i][4] = kq.getString("TIEN");
							  giaTri[i][5] = kq.getString("MATHE");
							  i++;
						  }
						  JTable bang = new JTable(giaTri,cot);
						  JScrollPane js = new JScrollPane(bang);
						  
						  menu.setVisible(false);
						  remove(menu);
						  menu = new JPanel(); 
						  menu.add(js);
						  menu.setSize(500,440);
						  menu.setLocation(100, 5);
						  menu.setBackground(Color.blue);
						  getContentPane().add(menu);
						  btn8.setText("Trá»Ÿ vá»�");
						  btn5.setText("");
						  btn6.setText("");
						  btn4.setText("");
						  chon = 900;
						}
						catch (Exception loi) {
							loi.printStackTrace();
						}
					}
					else if (chon == 900)
					{
						 chon = 90;
					     td.setText("Giá»›i tÃ­nh: ");
					     tf.setText("MÃ£ PIN: ");
					     th.setText("MÃ£ khÃ¡ch hÃ ng: ");
					     tm.setText("Há»� tÃªn: ");
						 menu.setVisible(false);
						 remove(menu);
						 menu = new JPanel(new GridLayout(5,2));
						 menu.setSize(500,440);
						 menu.setLocation(100, 5);
						 menu.add(th); menu.add(ma);
						 menu.add(tm); menu.add(ten);
						 menu.add(td); menu.add(gt);
						 menu.add(tf); menu.add(mapin);
						 menu.add(mthe); menu.add(mathe);
						 menu.setBackground(Color.blue);  
						 btn4.setText("<html><p>ThÃªm khÃ¡ch hÃ ng</p></html>");
						 btn5.setText("<html><p>XÃ³a khÃ¡ch hÃ ng</p></html>");
						 btn6.setText("<html><p>XÃ³a dá»¯ liá»‡u nháº­p</p></html>");
						 btn8.setText("<html><p>Danh sÃ¡ch khÃ¡ch hÃ ng</p></html>");
						 getContentPane().add(menu);
					}
			}
		}else if (e.getSource().equals(admin))
		{
			if (ad)
			{
				 chon = 90;
			     td.setText("Giá»›i tÃ­nh: ");
			     tf.setText("MÃ£ PIN: ");
			     th.setText("MÃ£ khÃ¡ch hÃ ng: ");
			     tm.setText("Há»� tÃªn: ");
				 menu.setVisible(false);
				 remove(menu);
				 menu = new JPanel(new GridLayout(5,2));
				 menu.setSize(500,440);
				 menu.setLocation(100, 5);
				 menu.add(th); menu.add(ma);
				 menu.add(tm); menu.add(ten);
				 menu.add(td); menu.add(gt);
				 menu.add(tf); menu.add(mapin);
				 menu.add(mthe); menu.add(mathe);
				 menu.setBackground(Color.blue);  
				 btn4.setText("<html><p>ThÃªm khÃ¡ch hÃ ng</p></html>");
				 btn5.setText("<html><p>XÃ³a khÃ¡ch hÃ ng</p></html>");
				 btn6.setText("<html><p>XÃ³a dá»¯ liá»‡u nháº­p</p></html>");
				 btn8.setText("<html><p>Danh sÃ¡ch khÃ¡ch hÃ ng</p></html>");
				 getContentPane().add(menu);
				
			}
			
		}
		
	}

	public void thongBao(int c)
	{
		   temp="";
		   tc.setText("Trá»Ÿ vá»�");
		   te.setText(atmExe.getLoi());
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(3,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(tc);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
		   chon = c;
	}
	public void thongBao(String tb, int c)
	{
		   temp="";
		   tc.setText("Trá»Ÿ vá»�");
		   te.setText(tb);
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(3,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(tc);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
		   chon = c;
	}
	public void admin(String tb)
	{
		   temp="";
		   te.setText(tb);
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(2,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
	}
}
