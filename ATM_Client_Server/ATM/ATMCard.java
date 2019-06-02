package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ATMCard extends JFrame {

	
	private JPanel contentPane;
	private JTextField tfIDCard;
	
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
					ATMCard frame = new ATMCard();
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
	public ATMCard() {
		setTitle("ATM Card ");
		
		setBounds(100, 100, 313, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtm = new JLabel("ATM Card");
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtm.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 28));
		lblAtm.setBounds(10, 11, 278, 59);
		contentPane.add(lblAtm);
		
		JLabel lblIdCard = new JLabel("So TK(ID Card)");
		lblIdCard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdCard.setBounds(10, 81, 101, 28);
		contentPane.add(lblIdCard);
		
		tfIDCard = new JTextField();
		tfIDCard.setBounds(121, 81, 167, 28);
		contentPane.add(tfIDCard);
		tfIDCard.setColumns(10);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOk.setBounds(116, 127, 65, 36);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if (new String(tfIDCard.getText()).equals("")) 
					{
			            JOptionPane.showMessageDialog(ATMCard.this, " còn trống", "Invalid", JOptionPane.ERROR_MESSAGE);
			            return;
					}
					ConnectDB();
					stmt=conn.createStatement();
					rs=stmt.executeQuery(" SELECT * FROM DaTaATM WHERE ID='"+tfIDCard.getText()+"'");
					rsm=rs.getMetaData();
					
					String Name=null;
					while(rs.next())
					{
						Name = rs.getString("Name");
					}
					ChoiceLanguage obj = new ChoiceLanguage();	
					obj.Set_ID(tfIDCard.getText());
					obj.setTextNameKh(Name);
					obj.setVisible(true);
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
				
				
				
				
				
			}
		});
		
	}
	
}
