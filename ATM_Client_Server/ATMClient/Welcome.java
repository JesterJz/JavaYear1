package ATMClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ATMCustomer.Password;
import ATMCustomer.Welcomepanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class Welcome extends JFrame {

	public JPanel contentPane;
	public JPanel Main_Panel;

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
					Welcome frame = new Welcome();
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
	public Welcome() {
		setTitle("Simulate ATM System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 621);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Main_Panel = new JPanel();
		Main_Panel.setBounds(148, 0, 700, 583);
		contentPane.add(Main_Panel);
		Main_Panel.setLayout(new BorderLayout(0, 0));
		Password obj = new Password();
		Main_Panel.add(obj);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_1.setBounds(0, 0, 148, 145);
		contentPane.add(button_1);

		JButton button_2 = new JButton("");
		button_2.setBounds(0, 146, 148, 145);
		contentPane.add(button_2);

		JButton button_3 = new JButton("");
		button_3.setBounds(0, 292, 148, 145);
		contentPane.add(button_3);

		JButton button_4 = new JButton("");
		button_4.setBounds(0, 438, 148, 145);
		contentPane.add(button_4);

		JButton button_5 = new JButton("");
		button_5.setBounds(849, 0, 148, 145);
		contentPane.add(button_5);

		JButton button_6 = new JButton("");
		button_6.setBounds(849, 146, 148, 145);
		contentPane.add(button_6);

		JButton button_7 = new JButton("");
		button_7.setBounds(849, 292, 148, 145);
		contentPane.add(button_7);

		JButton button_8 = new JButton("");
		button_8.setBounds(849, 438, 148, 145);
		contentPane.add(button_8);
	}

	public void showPanel1() {
		Main_Panel.removeAll();
		Main_Panel.add(new Welcomepanel());
		Main_Panel.revalidate();
	}

}
