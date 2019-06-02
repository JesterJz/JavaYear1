package ATM_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer_Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Customer_Login frame = new Customer_Login();
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
	public Customer_Login() {
		setTitle("Simulate ATM System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelImage = new JPanel();
		panelImage.setBounds(0, 0, 488, 582);
		contentPane.add(panelImage);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 102));
		panel_1.setBounds(486, 0, 498, 582);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLogin = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(170, 190, 157, 45);
		panel_1.add(lblLogin);

		JLabel lblID = new JLabel("ID :");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(Color.WHITE);
		lblID.setBounds(10, 278, 133, 36);
		panel_1.add(lblID);

		tfID = new JTextField();
		tfID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfID.setBounds(163, 278, 256, 36);
		panel_1.add(tfID);
		tfID.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ban Chua Nhap");
				}
				try {
					ConnectionServer.OutGui.writeBytes("ID"+"\n");
					ConnectionServer.OutGui.writeBytes(tfID.getText() + "\n");
				} catch (Exception e2) {
					e2.getStackTrace();
				}
				MainATM obj = new MainATM();
				obj.setVisible(true);
				setVisible(false);
			}
		});
		btnOk.setBounds(372, 337, 89, 36);
		panel_1.add(btnOk);
	}

}
