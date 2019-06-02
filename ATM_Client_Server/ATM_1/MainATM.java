package ATM_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ATMCustomer.Password;
import ATMCustomer.Welcomepanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class MainATM extends JFrame {

	public JPanel contentPane;
	public static JPanel Main_Panel;
	private JPasswordField tfPassword;
	public static JButton button_7;
	public static JButton button_8;
	private String chuoi;
	private JButton button_6;
	private JButton button_5;
	private JButton button_4;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainATM frame = new MainATM();
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
	public MainATM() {
		setTitle("Simulate ATM System");
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

		panelPassword();

		JButton button_1 = new JButton("");
		button_1.setBounds(0, 0, 148, 145);
		contentPane.add(button_1);

		JButton button_2 = new JButton("");
		button_2.setBounds(0, 146, 148, 145);
		contentPane.add(button_2);

		JButton button_3 = new JButton("");
		button_3.setBounds(0, 292, 148, 145);
		contentPane.add(button_3);

		button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chuoi = button_4.getText();
				if (chuoi == "Transfers") {
					showPanel(new TransFund());
					button_7.setText("");
					button_8.setText("Quit");
					button_6.setText("");
					button_5.setText("");
					button_4.setText("");
				}
			}
		});
		button_4.setBounds(0, 438, 148, 145);
		contentPane.add(button_4);

		button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chuoi = button_5.getText();
				if (chuoi == "Withdrawal") {
					showPanel(new Withdrawal());
					button_7.setText("");
					button_8.setText("Quit");
					button_6.setText("");
					button_5.setText("");
					button_4.setText("");
				}
			}
		});
		button_5.setBounds(849, 0, 148, 145);
		contentPane.add(button_5);

		button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chuoi = button_6.getText();
				if (chuoi == "Change PIN") {
					showPanel(new ChangePIN());
					button_7.setText("");
					button_8.setText("Quit");
					button_6.setText("");
					button_5.setText("");
					button_4.setText("");
				}
			}
		});
		button_6.setBounds(849, 146, 148, 145);
		contentPane.add(button_6);

		button_7 = new JButton();
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chuoi = button_7.getText();
				if (chuoi == "Tiếng Việt") {
					showPanel(new SelectFeature());
					button_7.setText("Current money");
					button_8.setText("Quit");
					button_6.setText("Change PIN");
					button_5.setText("Withdrawal");
					button_4.setText("Transfers");
				}
				if (chuoi == "Current money") {

					try {
						ConnectionServer.OutGui.writeBytes("Current money" + "\n");
						JOptionPane.showMessageDialog(null, "So du trong tai khoan ban la :\n"+ConnectionServer.Input.readLine());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (chuoi == "Yes") {
					showPanel(new Language());
					button_7.setText("Tiếng Việt");
					button_8.setText("English");
				}
				if (chuoi == "Change") {
					showPanel(new QuestionCustomer());
					button_7.setText("Yes");
					button_8.setText("No");
				}
			}
		});
		button_7.setBounds(849, 292, 148, 145);
		contentPane.add(button_7);

		button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chuoi = button_8.getText();
				if (chuoi == "English") {
					showPanel(new SelectFeatureEN());
					button_7.setText("Current money");
					button_8.setText("Quit");
					button_6.setText("Change PIN");
					button_5.setText("Withdrawal");
					button_4.setText("Transfers");
				}
				if (chuoi == "Quit") {
					showPanel(new QuestionCustomer());
					button_7.setText("Yes");
					button_8.setText("No");
					button_6.setText("");
					button_5.setText("");
					button_4.setText("");
				}
				if (chuoi == "No") {
					JOptionPane.showMessageDialog(null, "Mời Bạn Nhận Lại Thẻ");
					setVisible(false);
					Customer_Login obj = new Customer_Login();
					obj.setVisible(true);
				}
			}
		});
		button_8.setBounds(849, 438, 148, 145);
		contentPane.add(button_8);
	}

	private void panelPassword() {
		JPanel panel = new JPanel();
		Main_Panel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(235, 342, 229, 230);
		panel.add(panel_1);

		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn1.getText());
			}
		});
		btn1.setBounds(10, 4, 70, 54);
		panel_1.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn2.getText());
			}
		});
		btn2.setBounds(80, 4, 70, 54);
		panel_1.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn3.getText());
			}
		});
		btn3.setBounds(150, 4, 70, 54);
		panel_1.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn4.getText());
			}
		});
		btn4.setBounds(10, 58, 70, 54);
		panel_1.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn5.getText());
			}
		});
		btn5.setBounds(80, 58, 70, 54);
		panel_1.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn6.getText());
			}
		});
		btn6.setBounds(150, 58, 70, 54);
		panel_1.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn7.getText());
			}
		});
		btn7.setBounds(10, 112, 70, 54);
		panel_1.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn8.getText());
			}
		});
		btn8.setBounds(80, 112, 70, 54);
		panel_1.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn9.getText());
			}
		});
		btn9.setBounds(150, 112, 70, 54);
		panel_1.add(btn9);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(tfPassword.getText() + btn0.getText());
			}
		});
		btn0.setBounds(80, 165, 70, 54);
		panel_1.add(btn0);

		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText("");
			}
		});
		btnClear.setBounds(10, 165, 70, 54);
		panel_1.add(btnClear);

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Chua Nhap Ma PIN");
				} else {
					try {
						ConnectionServer.OutGui.writeBytes("Password" + "\n");
						ConnectionServer.OutGui.writeBytes(tfPassword.getText() + "\n");
						if (Integer.parseInt(ConnectionServer.Input.readLine()) == 1) {
							JOptionPane.showMessageDialog(null, "Login Succesfull");
							showPanel(new Language());
							button_7.setText("Tiếng Việt");
							button_8.setText("English");
						} else {
							JOptionPane.showMessageDialog(null, "Sai Mat Khau");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					showPanel(new Language());
					button_7.setText("Tiếng Việt");
					button_8.setText("English");
				}
			}
		});
		btnEnter.setBounds(150, 165, 70, 54);
		panel_1.add(btnEnter);

		label = new JLabel("Hello ");
		try {
			label.setText("Hello " + ConnectionServer.Input.readLine());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe Script", Font.BOLD, 20));
		label.setBounds(212, 49, 276, 60);
		panel.add(label);

		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPassword.setBounds(235, 120, 229, 40);
		panel.add(tfPassword);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Chua Nhap Ma PIN");
				} else {
					try {
						ConnectionServer.OutGui.writeBytes("Password" + "\n");
						ConnectionServer.OutGui.writeBytes(tfPassword.getText() + "\n");
						if (Integer.parseInt(ConnectionServer.Input.readLine()) == 1) {
							JOptionPane.showMessageDialog(null, "Login Succesfull");
							showPanel(new Language());
							button_7.setText("Tiếng Việt");
							button_8.setText("English");
						} else {
							JOptionPane.showMessageDialog(null, "Sai Mat Khau");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnOk.setBounds(411, 194, 77, 40);
		panel.add(btnOk);
	}

	public static void showPanel(JPanel panel) {
		JPanel child = panel;
		Main_Panel.removeAll();
		Main_Panel.add(child);
		Main_Panel.validate();
	}
}
