package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class DoiMk extends JFrame {

	private JPanel contentPane;

	ResultSet rs;
	ResultSetMetaData rsm;
	Connection conn;
	Statement stmt;
	
	private JPasswordField pfMkCu;
	private JPasswordField pfMkNew;
	private JPasswordField pfRetype;

	private String c;
	
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMk frame = new DoiMk();
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
	public DoiMk() {
		setTitle("Change PIN");
		setBounds(100, 100, 290, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMkCu = new JLabel("Current");
		lblMkCu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMkCu.setBounds(10, 49, 101, 25);
		contentPane.add(lblMkCu);
		
		JLabel lblMkNew = new JLabel("New Password");
		lblMkNew.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMkNew.setBounds(10, 85, 101, 25);
		contentPane.add(lblMkNew);
		
		JLabel lblRetype = new JLabel("Retype New");
		lblRetype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRetype.setBounds(10, 122, 101, 25);
		contentPane.add(lblRetype);
		
		pfMkCu = new JPasswordField();
		pfMkCu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pfMkCu.setBounds(121, 50, 143, 25);
		contentPane.add(pfMkCu);
		
		pfMkNew = new JPasswordField();
		pfMkNew.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pfMkNew.setBounds(121, 86, 143, 25);
		contentPane.add(pfMkNew);
		
		pfRetype = new JPasswordField();
		pfRetype.setFont(new Font("Times New Roman", Font.BOLD, 14));
		pfRetype.setBounds(121, 123, 143, 25);
		contentPane.add(pfRetype);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ConnectDB();
					stmt=conn.createStatement();
					rs = stmt.executeQuery("select * from DaTaATM WHERE ID='"+c+"'");
					rsm = rs.getMetaData();
					int Pass = 0;
					while(rs.next()) 
					{
						 Pass=rs.getInt("Pass");
					}
					int cu=Integer.parseInt(pfMkCu.getText());
					int moi=Integer.parseInt(pfMkNew.getText());
					int lai=Integer.parseInt(pfRetype.getText());
					if(cu != Pass)
					{
						JOptionPane.showMessageDialog(null, "Mat khau cu khong dung");
					}
					if(moi == lai && cu != moi && cu == Pass) 
					{
						int n = stmt.executeUpdate("Update DaTaATM set Pass = '"+Integer.parseInt(pfMkNew.getText())+"' WHERE ID='"+c+"'");
						if(n>0)
						{
							JOptionPane.showMessageDialog(null, "doi mat khau thanh cong");
							setVisible(false);
						}
						else
							JOptionPane.showMessageDialog(null, "doi mat khau that bai");
					}
					if(moi !=  lai)
					{
						JOptionPane.showMessageDialog(null, "mat khau moi va mat khau nhap lai khac nhau");
					}
					if( cu == moi)
					{
						JOptionPane.showMessageDialog(null, "mat khau cu và mat khau moi cua ban giong nhau");
					}
//					conn.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		btnOk.setBounds(175, 159, 89, 34);
		contentPane.add(btnOk);
	}
	public void ID(String text)
	{
		c=text;
	}
}
