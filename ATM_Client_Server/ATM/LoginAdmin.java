package ATM;


import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField passwordField;
	ResultSet rs;
	ResultSetMetaData rsm;

	Connection conn;
	Statement stmt;
	
	public void ConnectDB()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://SCD040220\\SQLEXPRESS;databaseName=AdminAcc;user=sa;password=jester");
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
					LoginAdmin frame = new LoginAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginAdmin() {
		
		setBounds(100, 100, 389, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtm = new JLabel("Card Admin");
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtm.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		lblAtm.setBounds(10, 11, 351, 84);
		contentPane.add(lblAtm);
		
		JLabel lblUser = new JLabel("UserName:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(20, 106, 77, 29);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(20, 146, 77, 29);
		contentPane.add(lblPassword);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUser.setBounds(107, 106, 227, 26);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("password");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(107, 146, 227, 26);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ConnectDB();
					stmt = conn.createStatement();
					if (tfUser.getText().equals("")||new String(passwordField.getPassword()).equals("")) 
					{
	                    JOptionPane.showMessageDialog(LoginAdmin.this, "UserName Hoặc PassWord còn trống", "Invalid", JOptionPane.ERROR_MESSAGE);
	                    return;
					}
					 rs = stmt.executeQuery("Select * From AccAd WHERE UserName='"+tfUser.getText()+"'AND PassWord='"+passwordField.getText()+"'");
					rsm = rs.getMetaData();
					if (rs != null) 
					{
                        if (rs.next()) 
                        {
                                JOptionPane.showMessageDialog(LoginAdmin.this, "Bạn đã đăng nhập thành công.", "Login Sucessful", JOptionPane.INFORMATION_MESSAGE);
                                // Login successful DO SOMETHING HERE
                                new InformationAcc().setVisible(true);
                               
                        }
                        else 
                        {
                            JOptionPane.showMessageDialog(LoginAdmin.this, "Bạn sai UserName Hoặc PassWord", "Login failed", JOptionPane.ERROR_MESSAGE);
                        }
					}
				}
				catch (Exception e1) 
				{
					e1.printStackTrace();
					// TODO: handle exception
				}
				
			}
		
		});
		btnLogin.setBounds(247, 184, 89, 29);
		contentPane.add(btnLogin);
	}
}
