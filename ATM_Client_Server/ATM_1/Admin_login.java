package ATM_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;

public class Admin_login extends JFrame {

	private JPanel contentPane;
	private JPasswordField tfPassword;
	private JTextField tfUserName;
	private JLabel lblUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Admin_login frame = new Admin_login();
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
	public Admin_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);

		JLabel lblImage = new JLabel("Thanh Ho\u00E0i");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(3, 3, 479, 577);
		panel.add(lblImage);
		lblImage.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 40));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setIcon(new ImageIcon("C:\\Users\\Administrator\\Pictures\\Jz.png"));
		lblImage.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImage.setHorizontalTextPosition((int) CENTER_ALIGNMENT);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GREEN, 2));
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 30));
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setBounds(170, 140, 157, 45);
		panel_1.add(lblLogin);

		lblUserName = new JLabel("UserName :");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setBounds(21, 227, 133, 36);
		panel_1.add(lblUserName);

		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUserName.setBounds(164, 228, 256, 36);
		panel_1.add(tfUserName);
		tfUserName.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOk.setBackground(Color.RED);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfUserName.getText().equals("") || tfPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ban Chua Nhap");
				}
				try {
					ConnectionServer.OutGui.writeBytes("ADMIN" + "\n");
					ConnectionServer.OutGui.writeBytes(tfUserName.getText() + "\n");
					ConnectionServer.OutGui.writeBytes(tfPassword.getText() + "\n");
					if (ConnectionServer.Input.readLine().equals("Right")) {
						InforAcc obj = new InforAcc();
						obj.setVisible(true);
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Sai Password or User", "Login Admin",
								JOptionPane.WARNING_MESSAGE);

					}
				} catch (Exception e2) {
					e2.getStackTrace();
				}
			}
		});

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(21, 274, 133, 36);
		panel_1.add(lblPassword);

		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPassword.setColumns(10);
		tfPassword.setBounds(164, 275, 256, 36);
		panel_1.add(tfPassword);
		btnOk.setBounds(372, 337, 89, 36);
		panel_1.add(btnOk);

	}
}
