package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RutTien extends JFrame {

	private JPanel contentPane;
	private JTextField tfTienRut;
	private String c;
	
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
					RutTien frame = new RutTien();
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
	public RutTien() {
		setTitle("Withdrawal");
		setBounds(100, 100, 404, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGT = new JLabel("Nhap so tien ban muon rut");
		lblGT.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGT.setHorizontalAlignment(SwingConstants.CENTER);
		lblGT.setBounds(10, 11, 367, 80);
		contentPane.add(lblGT);
		
		tfTienRut = new JTextField();
		tfTienRut.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfTienRut.setBounds(92, 102, 205, 28);
		contentPane.add(tfTienRut);
		tfTienRut.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					
					ConnectDB();
					long money = 0;
					stmt=conn.createStatement();
					rs = stmt.executeQuery("select * from DaTaATM WHERE ID='"+c+"'");
					rsm = rs.getMetaData();
					while(rs.next()) 
					{
						 money =rs.getLong("Tien");
					}
					
					int s=Integer.parseInt(tfTienRut.getText());
					if(s<=money)
						{
						 long q = money - s;
						 int n = stmt.executeUpdate("Update DaTaATM set Tien = '"+q+"' WHERE ID = '"+c+"'");
						 if(n>0)
						 {
						 JOptionPane.showMessageDialog(null, "So tien con lai la : '"+q+"'");
						 setVisible(false);
						 }
						 else
						 {
							 JOptionPane.showMessageDialog(null, "Fail");
						 }
						 conn.close();
						}
					else 
					{
						JOptionPane.showMessageDialog(null, "so tien trong tai khoan cua bạn khong du");
					}
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
				
				
			}
		});
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnOk.setBounds(149, 144, 89, 45);
		contentPane.add(btnOk);
		
		
		
	}
	
	public void ID(String text)
	{
		c=text;
	}
}
