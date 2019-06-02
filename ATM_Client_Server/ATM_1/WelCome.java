package ATM_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class WelCome extends JFrame {

	private JPanel contentPane;
	int i = 9110;
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
					WelCome frame = new WelCome();
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

	public WelCome() {
		setTitle("Simulate ATM System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelCome = new JLabel("Welcome to ATM");
		lblWelCome.setForeground(Color.RED);
		lblWelCome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelCome.setFont(new Font("Segoe Script", Font.ITALIC, 28));
		lblWelCome.setBounds(303, 165, 378, 119);
		contentPane.add(lblWelCome);

		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ConnectionServer obj1 = new ConnectionServer();
				obj1.Connec(i);
				i++;
				Customer_Login obj = new Customer_Login();
				obj.setVisible(true);
			}
		});
		btnCustomer.setBounds(303, 295, 89, 40);
		contentPane.add(btnCustomer);

		JButton btnADMIN = new JButton("ADMIN");
		btnADMIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ConnectionServer obj1 = new ConnectionServer();
				obj1.Connec(i++);
				i++;
				Admin_login obj = new Admin_login();
				obj.setVisible(true);
			}
		});
		btnADMIN.setBounds(592, 295, 89, 40);
		contentPane.add(btnADMIN);
	}

}
