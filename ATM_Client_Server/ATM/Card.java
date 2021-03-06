package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;


public class Card extends JFrame {

	public static Card conection;
	//public static Card connec;
	private JPanel contentPane;
	private JTextField tfID;
	private JPasswordField passwordField;
	ResultSet rs;
	ResultSetMetaData rsm;

	Connection conn;
	Statement stmt;
	
	public void ConnectDB()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://SCD040220\\SQLEXPRESS;databaseName=ATM;user=sa;password=jester");
			System.out.println(" Success");
			} 
		catch (Exception e) 
			{
				// TODO: handle exception
				System.out.println("Error"+e.getMessage());
			}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Card frame = new Card();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Card() {
		setTitle("Login");
		
		setBounds(100, 100, 437, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtm = new JLabel("ATM Card");
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtm.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		lblAtm.setBounds(10, 26, 414, 69);
		contentPane.add(lblAtm);
		
		JLabel lblIdCard = new JLabel("ID Card");
		lblIdCard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdCard.setBounds(10, 106, 70, 30);
		contentPane.add(lblIdCard);
		
		tfID = new JTextField();
		tfID.setEnabled(false);
		tfID.setEditable(false);
		tfID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfID.setBounds(88, 106, 244, 31);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 147, 70, 32);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(88, 148, 244, 31);
		contentPane.add(passwordField);
		
		JButton btnKeyPad = new JButton("");
		btnKeyPad.setIcon(new ImageIcon("C:\\Users\\Administrator\\Pictures\\tia.png"));
		btnKeyPad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				KeyPad obj = new KeyPad();
				obj.setLocationRelativeTo(null);
				obj.setVisible(true);
			}
		});
		
		
		btnKeyPad.setBounds(342, 147, 44, 31);
		contentPane.add(btnKeyPad);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(159, 190, 103, 37);
		contentPane.add(btnSignIn);
		btnSignIn.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				try 
				{
					ConnectDB();
					stmt = conn.createStatement();
					if (new String(passwordField.getPassword()).equals("")) 
					{
	                    JOptionPane.showMessageDialog(Card.this, "PassWord của bạn còn trống", "Invalid", JOptionPane.ERROR_MESSAGE);
	                    return;
					}
					 rs = stmt.executeQuery("Select * From DaTaATM WHERE ID ='"+tfID.getText()+"' AND pass='"+passwordField.getText()+"'");
					rsm = rs.getMetaData();
					if (rs != null) {
                        if (rs.next()) {
                        	
            				
                                JOptionPane.showMessageDialog(Card.this, "Bạn đã đăng nhập thành công.", "Login Sucessful", JOptionPane.INFORMATION_MESSAGE);
                                // Login successful DO SOMETHING HERE
                                ATMScreen obj = new ATMScreen();
                                obj.GETID(tfID.getText());
                                obj.setVisible(true);
                 	}
                        else 
                        {
                            JOptionPane.showMessageDialog(Card.this, "Bạn sai ID Hoặc Mật Khẩu", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                        }
									}
				}
				 catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
				
			}
		});
		conection = this;
		//connec = this;
			}
	
		
		//initComponents();
        
	


//	private void initComponents() 
//	{
//		 
//	}
	public void Set_ID(String t)
	{
		tfID.setText(t);
	}
	 public void Set_Text(String text)
	    {
		 passwordField.setText(passwordField.getText()+text);
	    }
}
