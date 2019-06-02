package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CreateAcc extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfMoney;
	private JPasswordField passwordField;

	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsm;
	int id=0;
	

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
					CreateAcc frame = new CreateAcc();
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
	public CreateAcc() {
		setTitle("Create Account ");
		setBounds(100, 100, 312, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		get_ID();
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblID.setBounds(10, 35, 77, 27);
		contentPane.add(lblID);
		
		tfID = new JTextField();
		tfID.setText(String.valueOf(id));
		tfID.setEnabled(false);
		tfID.setEditable(false);
		tfID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfID.setBounds(107, 36, 179, 27);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(10, 73, 77, 27);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfName.setBounds(107, 74, 179, 27);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(10, 111, 77, 28);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(107, 112, 179, 27);
		contentPane.add(passwordField);
		
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMoney.setBounds(10, 150, 77, 27);
		contentPane.add(lblMoney);
		
		tfMoney = new JTextField();
		tfMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMoney.setBounds(107, 150, 179, 27);
		contentPane.add(tfMoney);
		tfMoney.setColumns(10);
		
	
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try 
				{				
					int n = stmt.executeUpdate("INSERT dbo.DaTaATM ( ID, Name, Pass, Tien ) VALUES ('"+id+"','"+tfName.getText()+"','"+passwordField.getText()+"','"+tfMoney.getText()+"')");
					if (n>0) JOptionPane.showMessageDialog(null, "Succes");
					else JOptionPane.showMessageDialog(null, "Fail");
					conn.close();
				}
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
				
			}
		});
		
		btnCreate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCreate.setBounds(107, 188, 83, 33);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 new CreateAcc().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCancel.setBounds(203, 188, 83, 33);
		contentPane.add(btnCancel);
		
		
		
		JLabel lblText = new JLabel("<html><p>ID , Password and Money</p> only contain nthe number</html>");
		lblText.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblText.setBounds(10, 225, 276, 33);
		contentPane.add(lblText);
	}
	public void get_ID()
	{
		try {
			ConnectDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select COUNT(*) AS total FROM DaTaATM");
			rsm = rs.getMetaData();
			while(rs.next())
				{
				id =rs.getInt("total");
				id++;
				}
			} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
