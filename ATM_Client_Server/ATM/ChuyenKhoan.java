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
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ChuyenKhoan extends JFrame {

	private JPanel contentPane;

	ResultSet rs;
	ResultSetMetaData rsm;
	Connection conn;
	Statement stmt;
	private JTextField tfTienChuyen;
	private JTextField tfTKChuyen;
	private JPasswordField tfpMK;

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
					ChuyenKhoan frame = new ChuyenKhoan();
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
	public ChuyenKhoan() {
		setTitle("Fund transfers");
		setBounds(100, 100, 325, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTienChuyen = new JLabel("Money needs to transfer");
		lblTienChuyen.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTienChuyen.setBounds(10, 44, 164, 25);
		contentPane.add(lblTienChuyen);
		
		tfTienChuyen = new JTextField();
		tfTienChuyen.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfTienChuyen.setBounds(184, 44, 115, 25);
		contentPane.add(tfTienChuyen);
		tfTienChuyen.setColumns(50);
		
		JLabel lblTKChuyen = new JLabel("Account number(ID)");
		lblTKChuyen.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTKChuyen.setBounds(10, 80, 146, 25);
		contentPane.add(lblTKChuyen);
		
		tfTKChuyen = new JTextField();
		tfTKChuyen.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfTKChuyen.setBounds(184, 80, 115, 25);
		contentPane.add(tfTKChuyen);
		tfTKChuyen.setColumns(10);
		
		tfpMK = new JPasswordField();
		tfpMK.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfpMK.setBounds(184, 117, 115, 25);
		contentPane.add(tfpMK);
		
		JLabel lblMK = new JLabel("Password For You");
		lblMK.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMK.setBounds(10, 117, 146, 25);
		contentPane.add(lblMK);
		
		JButton btnFundtransfers = new JButton("Fund transfers");
		btnFundtransfers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ConnectDB();
					stmt=conn.createStatement();
					rs=stmt.executeQuery("select * from DaTaATM WHERE ID='"+c+"'");
					rsm=rs.getMetaData();
					int Pass=0;
					long money=0;
					
					while(rs.next())
					{
						Pass=rs.getInt("Pass");
						 money =rs.getLong("Tien");
					}
					if(Integer.parseInt(tfpMK.getText())>1000000000)
						JOptionPane.showMessageDialog(null, "Only transfer less than 1 billion each time");
					if(Integer.parseInt(tfpMK.getText())==Pass)
					{
						if(Integer.parseInt(tfTienChuyen.getText())<money)
						{
						money=money-Integer.parseInt(tfTienChuyen.getText());
						int n = stmt.executeUpdate("UPDATE DaTaATM SET Tien='"+money+"' WHERE ID = '"+c+"'");
						int m =stmt.executeUpdate("UPDATE DaTaATM SET Tien= Tien +'"+Integer.parseInt(tfTienChuyen.getText())+"' WHERE ID = '"+Integer.parseInt(tfTKChuyen.getText())+"'");
							if(m>0&&n>0)
								{
								JOptionPane.showMessageDialog(null, "chuyen thang cong");
								setVisible(false);
								}
							else
								{
							JOptionPane.showMessageDialog(null, "chuyen that bai");
								}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sai mat khau");
					}
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		btnFundtransfers.setBounds(69, 154, 110, 36);
		contentPane.add(btnFundtransfers);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(189, 154, 110, 36);
		contentPane.add(btnCancel);
		
		JLabel lblOnlyTransferLess = new JLabel("Only transfer less than 1 billion each time");
		lblOnlyTransferLess.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnlyTransferLess.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOnlyTransferLess.setBounds(13, 11, 282, 22);
		contentPane.add(lblOnlyTransferLess);
	}
	public void ID(String text)
	{
		c=text;
	}
}
