package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class etxt extends JFrame {
	 private Connection conn;
	 private DefaultTableModel tableModel = new DefaultTableModel();
	
	 private JTable table  = new JTable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					etxt frame = new etxt();
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
	public etxt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		String []colsName = {"Mã hàng", "Tên hàng"};
        tableModel.setColumnIdentifiers(colsName);
        
        
		
		JScrollPane scroll = JTable.createScrollPaneForTable(table);   
        this.add(scroll);
        this.setVisible(true);
		getContentPane().add(table, BorderLayout.SOUTH);
		
		
		connectSQL();
		updateData(view());
		
	}
	public void updateData(ResultSet result){
        String []colsName = {"ID","Name","PassWord","Tien"};
        tableModel.setColumnIdentifiers(colsName); // ??t tiêu ?? cho b?ng theo các giá tr? c?a m?ng colsName
 
        try {
            while(result.next())
            { 
                Object rows[] = new Object[4];
                rows[0] = result.getString(1); 
                rows[1] = result.getString(2); 
                rows[2] = result.getInt(3);
                rows[3] = result.getInt(4);
                tableModel.addRow(rows); 
            }
            table.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
	 public ResultSet view(){
	        ResultSet result = null;
	        String sql = "SELECT * FROM DaTaATM";
	        try {
	            Statement statement = (Statement) conn.createStatement();
	            return statement.executeQuery(sql);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 public void connectSQL(){
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
}
