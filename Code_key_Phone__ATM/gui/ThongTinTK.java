package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ThongTinTK extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private DefaultTableModel model = new DefaultTableModel();
	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsm;
	private JTable tbTK;
//	Object[] colHeader = {"ID","Name","Pass","Tien"};
//	DefaultTableModel model = new DefaultTableModel(colHeader,0);
	
	//tao ham connect DB
	public void connectDB() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://SCD040220\\SQLEXPRESS;databaseName=ATM;user=sa;password=jester");
			System.out.println("Connect Sucess");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongTinTK frame = new ThongTinTK();
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
	public ThongTinTK() {
		
		String []colsName1 = {"M� h�ng", "T�n h�ng"};
		model.setColumnIdentifiers(colsName1);
		tbTK.setModel(model);
		
		JScrollPane scroll = JTable.createScrollPaneForTable(tbTK);
		this.add(scroll);
		this.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp t\u1EEB kh\u00F3a");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 32, 128, 36);
		contentPane.add(lblNewLabel);
		
		tfSearch = new JTextField();
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSearch.setBounds(134, 33, 229, 36);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnTim = new JButton("T\u00ECm ki\u1EBFm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(373, 38, 102, 29);
		contentPane.add(btnTim);
		
		JButton btnNewTK = new JButton("T\u1EA1o m\u1EDBi TK");
		btnNewTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TaoTaiKhoan obj = new TaoTaiKhoan();
//				obj.setVisible(true);
			}
			
		});
		btnNewTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewTK.setBounds(485, 37, 102, 30);
		contentPane.add(btnNewTK);
		
		JButton btnNewButton_1 = new JButton("C\u1EADp nh\u1EADt TK");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(597, 38, 109, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connectDB();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from DaTaATM");
					rsm = rs.getMetaData();
					
					
					String []colsName = {"ID","Name","Pass","Tien"};
					model.setColumnIdentifiers(colsName);
					
					while(rs.next()) {
						
						String id =rs.getString("ID");
						String name =rs.getString("Name");
						int Pass= rs.getInt("Pass");
						int money =rs.getInt("Tien");
						Object row[] = new Object[]{id,name,Pass,money};
						model.addRow(row);
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		tbTK = new JTable();
		tbTK.setBounds(20, 81, 792, 343);
		contentPane.add(tbTK);
		btnAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAll.setBounds(716, 39, 96, 26);
		contentPane.add(btnAll);
	}
}