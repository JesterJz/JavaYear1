package ATM;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InformationAcc extends JFrame {

	private JPanel contentPane;

	ResultSet rs;
	ResultSetMetaData rsm;
	Connection conn;
	Statement stmt;
	private JTable TBAcc;
	private JTextField tfSearch;
	Object[] colHeader = { "Id", "Name", "Tien" };
	DefaultTableModel model = new DefaultTableModel(colHeader, 0);

	public void ConnectDB() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection("jdbc:sqlserver://SCD040220\\SQLEXPRESS;databaseName=ATM;user=sa;password=jester");
			System.out.println(" Success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformationAcc frame = new InformationAcc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void fitter(String Query) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		TBAcc.setRowSorter(tr);

		tr.setRowFilter(RowFilter.regexFilter(Query));
	}

	/**
	 * Create the frame.
	 */
	public InformationAcc() {
		setTitle("InforAcc - Jester");

		setBounds(100, 100, 838, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNhap = new JLabel("Nh\u1EADp t\u1EEB kh\u00F3a");
		lblNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNhap.setBounds(10, 26, 118, 30);
		contentPane.add(lblNhap);

		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String Query = tfSearch.getText().toLowerCase();
				fitter(Query);
			}
		});
		tfSearch.setBounds(136, 26, 220, 30);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ConnectDB();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from DaTaATM WHERE Name LIKE '%" + tfSearch.getText()
							+ "%' OR ID LIKE '%" + tfSearch.getText() + "%'");
					rsm = rs.getMetaData();
					TBAcc.setModel(model);
					if (conn != null) {
						model.setRowCount(0);
						while (rs.next()) {
							int id = rs.getInt("ID");
							String name = rs.getString("Name");
							// int Pass= rs.getInt("Pass");
							long money = rs.getLong("Tien");
							Object[] row = new Object[] { id, name, money };
							model.addRow(row);

						}
						conn.close();
					}
				}

				catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(366, 24, 104, 30);
		contentPane.add(btnSearch);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAcc obj = new CreateAcc();
				obj.setLocation(800, 100);
				obj.setVisible(true);
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(480, 24, 104, 30);
		contentPane.add(btnCreate);

		JButton btnReaload = new JButton("Reload");
		btnReaload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ConnectDB();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from DaTaATM");
					rsm = rs.getMetaData();
					TBAcc.setModel(model);
					if (conn != null) {
						model.setRowCount(0);
						while (rs.next()) {
							int id = rs.getInt("Id");
							String name = rs.getString("Name");
							// int Pass= rs.getInt("Pass");
							long money = rs.getLong("Tien");
							Object[] row = new Object[] { id, name, money };
							model.addRow(row);
						}

					}
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

		});
		btnReaload.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReaload.setBounds(594, 24, 104, 30);
		contentPane.add(btnReaload);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseClicked(e);
			}
		});

		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(708, 23, 104, 30);
		contentPane.add(btnUpdate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 802, 348);
		contentPane.add(scrollPane);

		TBAcc = new JTable();
		scrollPane.setViewportView(TBAcc);
		TBAcc.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		JScrollPane scroll = JTable.createScrollPaneForTable(jtable);   
//        this.add(scroll);
	}

	public void mouseClicked(ActionEvent e) {
		try {

			int Row = TBAcc.getSelectedRow();
			String a = String.valueOf(model.getValueAt(Row, 0));
			String b = String.valueOf(model.getValueAt(Row, 1));
			String c = String.valueOf(model.getValueAt(Row, 2));
			// JOptionPane.showMessageDialog(null, a);
			new Update().setVisible(true);
			Update.conection3.Set__Text(a, b, c);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Báº¡n chÆ°a chá»�n tÃ i khoáº£n Ä‘á»ƒ Update");// TODO: handle
																								// exception
			e2.printStackTrace();
			// new Update().setVisible(false);

		}

	}

}
