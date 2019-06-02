package ATM;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ATMScreenEng extends JFrame {

	private JPanel contentPane;
	private String c;
	
	ResultSet rs;
	ResultSetMetaData rsm;
	Connection conn;
	
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
	Statement stmt;
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
					ATMScreenEng frame = new ATMScreenEng();
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
	public ATMScreenEng() {
		setTitle("ATM Sreen English");
		setBounds(100, 100, 792, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectAFeature = new JLabel("Select A Feature");
		lblSelectAFeature.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectAFeature.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAFeature.setBounds(262, 125, 219, 74);
		contentPane.add(lblSelectAFeature);
		
		JLabel lblRutTien = new JLabel("Withdrawal");
		lblRutTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRutTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblRutTien.setBounds(515, 6, 117, 104);
		contentPane.add(lblRutTien);
		
		JLabel lblDoiMk = new JLabel("Change PIN");
		lblDoiMk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoiMk.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMk.setBounds(515, 110, 117, 104);
		contentPane.add(lblDoiMk);
		
		JLabel lblTienDu = new JLabel("Check account");
		lblTienDu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTienDu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTienDu.setBounds(515, 215, 117, 104);
		contentPane.add(lblTienDu);
		
		JLabel lblCKhoan = new JLabel("Fund transfers");
		lblCKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblCKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCKhoan.setBounds(140, 322, 158, 99);
		contentPane.add(lblCKhoan);
		
		JLabel lblQuit = new JLabel("Quit");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuit.setBounds(515, 322, 117, 104);
		contentPane.add(lblQuit);
		
		JButton btnRutTien = new JButton("");
		btnRutTien.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				RutTien obj = new RutTien();
				obj.ID(c);
				obj.setVisible(true);
				
			}
		});
		btnRutTien.setBounds(642, 6, 124, 104);
		contentPane.add(btnRutTien);
		
		JButton btnDoiMk = new JButton("");
		btnDoiMk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				DoiMk obj = new DoiMk();
				obj.ID(c);
				obj.setVisible(true);
				
			}
		});
		btnDoiMk.setBounds(642, 110, 124, 104);
		contentPane.add(btnDoiMk);
		
		JButton btnTienDu = new JButton("");
		btnTienDu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ConnectDB();
					stmt=conn.createStatement();
					rs=stmt.executeQuery("select * from DaTaATM WHERE ID = '"+c+"'");
					rsm=rs.getMetaData();
					while(rs.next())
					{
						long money=rs.getLong("Tien");
						JOptionPane.showMessageDialog(null, "The balance in your account is :" +money+ "VND");
					}
					
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		btnTienDu.setBounds(642, 215, 124, 104);
		contentPane.add(btnTienDu);
		
		
		JButton btnQuit = new JButton("");
		btnQuit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnQuit.setBounds(642, 322, 124, 104);
		contentPane.add(btnQuit);
		
		
		JButton btnChuyenKhoan = new JButton("");
		btnChuyenKhoan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ChuyenKhoan obj=new ChuyenKhoan();
				obj.setVisible(true);
				obj.ID(c);
			}
		});
		btnChuyenKhoan.setBounds(10, 322, 124, 104);
		contentPane.add(btnChuyenKhoan);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(10, 6, 124, 104);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(10, 110, 124, 104);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(10, 215, 124, 104);
		contentPane.add(btnNewButton_4);
	}
	public void GETID(String t) 
	{
		c=t;
	}
}
