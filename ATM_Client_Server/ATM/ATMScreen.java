package ATM;

import java.awt.BorderLayout;
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

public class ATMScreen extends JFrame {

	private JPanel contentPane;
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
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMScreen frame = new ATMScreen();
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
	public ATMScreen() {
		setTitle("ATM Screen");
		setBounds(100, 100, 792, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lua Chon Tinh Nang");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(253, 49, 269, 104);
		contentPane.add(lblNewLabel);
		
		JLabel lblRutTien = new JLabel("Rut Tien");
		lblRutTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRutTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRutTien.setBounds(515, 6, 117, 104);
		contentPane.add(lblRutTien);
		
		JLabel lblDoiMk = new JLabel("Doi Mat Khau");
		lblDoiMk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoiMk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoiMk.setBounds(515, 110, 117, 104);
		contentPane.add(lblDoiMk);
		
		JLabel lblTienDu = new JLabel("Kiem Tra Tai Khoan");
		lblTienDu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTienDu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTienDu.setBounds(419, 215, 213, 104);
		contentPane.add(lblTienDu);
		
		JLabel lblCKhoan = new JLabel("Chuyen Khoan");
		lblCKhoan.setHorizontalAlignment(SwingConstants.LEFT);
		lblCKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCKhoan.setBounds(140, 322, 142, 99);
		contentPane.add(lblCKhoan);
		
		JLabel lblQuit = new JLabel("Thoat");
		lblQuit.setHorizontalAlignment(SwingConstants.RIGHT);
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
						JOptionPane.showMessageDialog(null, "so du trong tai khoan la :"+money+"VND");
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
				obj.ID(c);
				obj.setVisible(true);
			}
		});
		btnChuyenKhoan.setBounds(6, 322, 124, 104);
		contentPane.add(btnChuyenKhoan);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 6, 124, 104);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(6, 110, 124, 104);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(6, 215, 124, 104);
		contentPane.add(btnNewButton_4);
	}
	public void GETID(String t) 
	{
		c=t;
	}
}
