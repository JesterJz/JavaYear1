package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



public class ChoiceLanguage extends JFrame {
	
	//public static ChoiceLanguage conection1;
	private JPanel contentPane;
	private String c;
	
	
	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ChoiceLanguage frame = new ChoiceLanguage();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public ChoiceLanguage()
	{
		
		initComponents();
		//conection1 = this;
		
	}
	
	public void initComponents() {
		
		setTitle("ATM Choice Language - Jester");
		setBounds(100, 100, 792, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiengViet = new JLabel("Ti\u1EBFng Vi\u1EC7t");
		lblTiengViet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTiengViet.setBounds(498, 215, 113, 104);
		contentPane.add(lblTiengViet);
		
		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEnglish.setBounds(498, 331, 113, 95);
		contentPane.add(lblEnglish);
		
		JLabel lblNhapNN = new JLabel("M\u1EDDi B\u1EA1n Ch\u1ECDn Ng\u00F4n Ng\u1EEF");
		lblNhapNN.setVerticalAlignment(SwingConstants.TOP);
		lblNhapNN.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapNN.setBounds(142, 155, 488, 59);
		lblNhapNN.setFont(new Font("Times New Roman", Font.ITALIC, 40));
		contentPane.add(lblNhapNN);
		
		lblNameKH = new JLabel();
		lblNameKH.setFont(new Font("Times New Roman", Font.ITALIC, 40));
		lblNameKH.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameKH.setBounds(142, 20, 488, 126);
		contentPane.add(lblNameKH);
		
		JButton btnTiengViet = new JButton("");
		btnTiengViet.setBounds(642, 215, 124, 104);
		contentPane.add(btnTiengViet);
		btnTiengViet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
//				new Card().setVisible(true);
				Card obj = new Card();
				obj.Set_ID(c);
				obj.setVisible(true);
				ATMCard obj1 = new ATMCard();
				obj1.setVisible(false);
				
				
				
			}
		});
		
		JButton btnEnglish = new JButton("");
		btnEnglish.setBounds(642, 322, 124, 104);
		contentPane.add(btnEnglish);
		btnEnglish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardEng obj = new CardEng();
				obj.Set_ID(c);
				obj.setVisible(true);
				
			}
		});
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(642, 110, 124, 104);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(642, 6, 124, 104);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(6, 6, 124, 104);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(6, 110, 124, 104);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(6, 215, 124, 104);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(6, 322, 124, 104);
		contentPane.add(btnNewButton_5);
		
		
	}
	public void Set_ID(String t)
	{		
		c=t;
	}
	public void setTextNameKh(String string)
	{
		lblNameKH.setText(" Hello "+string);
	}
	private JLabel lblNameKH;
}
