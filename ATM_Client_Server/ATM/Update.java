package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Update extends JFrame {

	private JPanel contentPane;
	public static Update conection3;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfMoney;
	private JPasswordField passwordField;
	
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
	public static void main(String[] args) 
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
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
	public Update() {
		setTitle("Update");
		
		setBounds(100, 100, 312, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblID.setBounds(10, 35, 77, 27);
		contentPane.add(lblID);
		
		tfID = new JTextField();
		tfID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfID.setEnabled(false);
		tfID.setEditable(false);
		tfID.setBounds(107, 36, 179, 27);
		contentPane.add(tfID);
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(10, 73, 77, 27);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfName.setBounds(107, 74, 179, 27);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPassword.setBounds(10, 111, 77, 28);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD, 14));
		passwordField.setEnabled(false);
		passwordField.setEditable(false);
		passwordField.setText("************");
		passwordField.setBounds(107, 112, 179, 27);
		contentPane.add(passwordField);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMoney.setBounds(10, 150, 77, 27);
		contentPane.add(lblMoney);
		
		tfMoney = new JTextField();
		tfMoney.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfMoney.setBounds(107, 150, 179, 27);
		contentPane.add(tfMoney);
		//tfMoney.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try {
					ConnectDB();
					stmt = conn.createStatement();
					int n = stmt.executeUpdate("Update DaTaATM set Name = '"+tfName.getText()+"' , Tien='"+tfMoney.getText()+"' WHERE ID='"+tfID.getText()+"'");
					if (n>0) 
						JOptionPane.showMessageDialog(null, "Succes");
					else 
						JOptionPane.showMessageDialog(null, "Fail");
					conn.close();
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnUpdate.setBounds(107, 188, 83, 33);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Update().setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCancel.setBounds(203, 188, 83, 33);
		contentPane.add(btnCancel);
		conection3=this;
	}
	public void Set__Text(String t,String c, String b)
	{
		
		tfID.setText(t);
		tfName.setText(c);
		tfMoney.setText(b);
		
	}
}
