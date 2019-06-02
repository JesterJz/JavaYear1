package ATM_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ATM.Update;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InforAcc extends JFrame {
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPass;
	private JTextField tfMoney;
	private JTable table;
	private Connection conn;
	private Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsm;
	Object[] Column = { "ID", "Họ và Tên", "Số dư", "Password" };
	DefaultTableModel model = new DefaultTableModel(Column, 0);
	private JTextField tfSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InforAcc frame = new InforAcc();
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
	public InforAcc() {
		setTitle("Information Account Bank");
		Dimension fullscreem = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, fullscreem.width, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_left = new JPanel();
		panel_left.setBackground(new Color(0, 0, 128));
		panel_left.setBounds(0, 0, 265, 702);
		contentPane.add(panel_left);
		panel_left.setLayout(null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connecDB();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from account WHERE Name LIKE '%" + tfSearch.getText()
							+ "%'OR ID LIKE '%" + tfSearch.getText() + "%'");
					rsm = rs.getMetaData();
					table.setModel(model);
					model.setRowCount(0);
					while (rs.next()) {
						int ID = rs.getInt("ID");
						String Name = rs.getString("Name");
						int Pass = rs.getInt("Pass");
						long Money = rs.getLong("Money");
						Object[] row = new Object[] { ID, Name, Pass, Money };
						model.addRow(row);
					}
					table.setModel(model);
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		btnSearch.setBounds(166, 117, 89, 23);
		panel_left.add(btnSearch);

		tfSearch = new JTextField();
//		tfSearch.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				String Query = tfSearch.getText().toLowerCase();
//				fitter(Query);
//			}
//		});
		tfSearch.setBounds(90, 83, 165, 23);
		panel_left.add(tfSearch);
		tfSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setBounds(10, 83, 70, 23);
		panel_left.add(lblSearch);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseClickedUpdate(e);
			}
		});
		btnUpdate.setBounds(10, 198, 245, 46);
		panel_left.add(btnUpdate);

		JButton btnCreat = new JButton("Creat");
		btnCreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, "Hay dien noi dung can tạo vao cac o tren");
					ConnectionServer.OutGui.writeBytes("Creat" + "\n");
					ConnectionServer.OutGui.writeBytes(tfName.getText() + "\n");
					ConnectionServer.OutGui.writeBytes(tfMoney.getText() + "\n");
					ConnectionServer.OutGui.writeBytes(tfPass.getText() + "\n");
					int ID = Integer.parseInt(ConnectionServer.Input.readLine());
					if (ConnectionServer.Input.readLine().equals("succes")) {
						JOptionPane.showMessageDialog(null, "Them thanh cong");
						LoadData();
					} else {
						JOptionPane.showMessageDialog(null, "Them that bai");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreat.setBounds(10, 255, 245, 46);
		panel_left.add(btnCreat);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Chon cot can Delete");
					} else {
						String ID = String.valueOf(model.getValueAt(row, 0));
						ConnectionServer.OutGui.writeBytes("Delete" + "\n");
						ConnectionServer.OutGui.writeBytes(ID + "\n");
						if (ConnectionServer.Input.readLine().equals("succes")) {
							JOptionPane.showMessageDialog(null, "Delete thanh cong");
							LoadData();
						} else {
							JOptionPane.showMessageDialog(null, "Delete that bai");
						}
					}
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});
		btnDelete.setBounds(10, 312, 245, 46);
		panel_left.add(btnDelete);

		JPanel panel_right = new JPanel();
		panel_right.setBounds(264, 0, 1086, 702);
		contentPane.add(panel_right);
		panel_right.setLayout(null);

		JPanel panel_TT = new JPanel();
		panel_TT.setBackground(new Color(0, 0, 128));
		panel_TT.setBounds(10, 11, 1066, 43);
		panel_right.add(panel_TT);
		panel_TT.setLayout(new BorderLayout(0, 0));

		JLabel lblThongTin = new JLabel("Th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		lblThongTin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblThongTin.setForeground(new Color(255, 255, 255));
		lblThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		panel_TT.add(lblThongTin, BorderLayout.CENTER);

		JPanel panel_Show = new JPanel();
		panel_Show.setToolTipText("");
		panel_Show.setBounds(10, 65, 1066, 159);
		panel_right.add(panel_Show);
		panel_Show.setLayout(null);

		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(182, 38, 98, 20);
		panel_Show.add(lblID);

		tfID = new JTextField();
		tfID.setEnabled(false);
		tfID.setBounds(290, 38, 151, 23);
		panel_Show.add(tfID);
		tfID.setColumns(10);

		JLabel lblName = new JLabel("H\u1ECD V\u00E0 T\u00EAn");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(182, 100, 98, 20);
		panel_Show.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(290, 100, 151, 23);
		panel_Show.add(tfName);

		JLabel lblMoney = new JLabel("S\u1ED1 d\u01B0");
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMoney.setBounds(624, 38, 98, 18);
		panel_Show.add(lblMoney);

		tfMoney = new JTextField();
		tfMoney.setColumns(10);
		tfMoney.setBounds(732, 39, 151, 23);
		panel_Show.add(tfMoney);

		JLabel lblPass = new JLabel("M\u1EADt Kh\u1EA9u");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPass.setBounds(624, 100, 98, 18);
		panel_Show.add(lblPass);

		tfPass = new JTextField();
		tfPass.setColumns(10);
		tfPass.setBounds(732, 100, 151, 23);
		panel_Show.add(tfPass);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 1066, 468);
		panel_right.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		table.getColumnModel().getColumn(2).setPreferredWidth(217);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		scrollPane.setViewportView(table);
		LoadData();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int Row = table.getSelectedRow();
				tfID.setText(String.valueOf(model.getValueAt(Row, 0)));
				tfName.setText(String.valueOf(model.getValueAt(Row, 1)));
				tfMoney.setText(String.valueOf(model.getValueAt(Row, 2)));
				tfPass.setText(String.valueOf(model.getValueAt(Row, 3)));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

//	public void fitter(String Query) {
//		TableRowSorter tr = new TableRowSorter(model);
//		table.setRowSorter(tr);
//
//		tr.setRowFilter(RowFilter.regexFilter(Query));
//	}
	public void mouseClickedUpdate(ActionEvent e) {
		try {
			int Row = table.getSelectedRow();

			if (Row < 0) {
				JOptionPane.showMessageDialog(null, "ban chua chon doi tuong can Update");
			} else {
				String ID = String.valueOf(model.getValueAt(Row, 0));
				String Name = String.valueOf(model.getValueAt(Row, 1));
				String Money = String.valueOf(model.getValueAt(Row, 2));
				String Pass = String.valueOf(model.getValueAt(Row, 3));
				ConnectionServer.OutGui.writeBytes("Update" + "\n");
				ConnectionServer.OutGui.writeBytes(ID + "\n");
				ConnectionServer.OutGui.writeBytes(Name + "\n");
				ConnectionServer.OutGui.writeBytes(Money + "\n");
				ConnectionServer.OutGui.writeBytes(Pass + "\n");
			}
			if (ConnectionServer.Input.readLine().equals("succes")) {
				JOptionPane.showMessageDialog(null, "Cap nhat thanh cong");
				LoadData();
			} else {
				JOptionPane.showMessageDialog(null, "Cap nhat that bai");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "ban chua chon doi tuong can Update");
			e2.printStackTrace();
		}

	}

	public void LoadData() {
		try {
			connecDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from account");
			rsm = rs.getMetaData();
			table.setModel(model);
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String Name = rs.getString("Name");
				int Pass = rs.getInt("Pass");
				long Money = rs.getLong("Money");
				Object[] row = new Object[] { ID, Name, Pass, Money };
				model.addRow(row);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void connecDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountatm", "root", "");
			System.out.println("Connect SQL Success");
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
	}
}
