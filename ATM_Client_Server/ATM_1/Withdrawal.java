package ATM_1;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class Withdrawal extends JPanel {
	private JTextField tfMoney;
	private JPanel panel;
	private JTextField txtVnd;

	/**
	 * Create the panel.
	 */
	public Withdrawal() {
		setBackground(Color.YELLOW);
		setLayout(null);
		
		txtVnd = new JTextField();
		txtVnd.setEnabled(false);
		txtVnd.setText("VND");
		txtVnd.setBounds(450, 149, 32, 28);
		add(txtVnd);
		txtVnd.setColumns(10);

		JLabel lbltiltle = new JLabel("Nh\u1EADp s\u1ED1 ti\u1EC1n c\u1EA7n r\u00FAt");
		lbltiltle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbltiltle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltiltle.setBounds(182, 61, 336, 57);
		add(lbltiltle);

		tfMoney = new JTextField();
		tfMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMoney.setBounds(217, 149, 235, 28);
		add(tfMoney);
		tfMoney.setColumns(10);

		JButton btn500 = new JButton("500,000 VND");
		btn500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText("500000");
			}
		});
		btn500.setBounds(95, 269, 123, 45);
		add(btn500);

		JButton btn1tr = new JButton("1,000,000 VND");
		btn1tr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText("1000000");
			}
		});
		btn1tr.setBounds(95, 325, 123, 45);
		add(btn1tr);

		JButton btn2tr = new JButton("2,000,000 VND");
		btn2tr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText("2000000");
			}
		});
		btn2tr.setBounds(481, 269, 123, 45);
		add(btn2tr);

		JButton btn3tr = new JButton("3,000,000 VND");
		btn3tr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText("3000000");
			}
		});
		btn3tr.setBounds(481, 325, 123, 45);
		add(btn3tr);

		JButton btnSoKhac = new JButton("S\u1ED1 kh\u00E1c");
		btnSoKhac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		btnSoKhac.setBounds(301, 269, 98, 45);
		add(btnSoKhac);

		PanelKey();

		JLabel lblQuit = new JLabel("Quit ->");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuit.setBounds(530, 427, 160, 145);
		add(lblQuit);

		JButton btnWithdrawal = new JButton("R\u00FAt");
		btnWithdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfMoney.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ban chua nhap so tien can rut.");
				}
				try {
					ConnectionServer.OutGui.writeBytes("Withdrawal" + "\n");
					ConnectionServer.OutGui.writeBytes(tfMoney.getText() + "\n");
					if (ConnectionServer.Input.readLine().equals(0)) {
						JOptionPane.showConfirmDialog(null, "Tai khoan khong du tien de rut.", "Jester",
								JOptionPane.OK_OPTION);
					} else {
						if (ConnectionServer.Input.readLine().equals("succes")) {
							JOptionPane.showMessageDialog(null, "Giao dich dang duoc thuc hien hay cho va nhan tien.");
							MainATM.showPanel(new QuestionCustomer());
							MainATM.button_7.setText("Yes");
							MainATM.button_8.setText("No");
						} else {
							int q = JOptionPane.showConfirmDialog(null, "Yeu cau that bai.\nBan co muon thuc hien lai.",
									"Jester", JOptionPane.YES_NO_OPTION);
							if (q == JOptionPane.YES_OPTION) {
								tfMoney.setText("");
							} else if (q == JOptionPane.NO_OPTION) {
								MainATM.showPanel(new QuestionCustomer());
								MainATM.button_7.setText("Yes");
								MainATM.button_8.setText("No");
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnWithdrawal.setForeground(Color.RED);
		btnWithdrawal.setBounds(301, 213, 98, 45);
		add(btnWithdrawal);

	}

	private void PanelKey() {
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setLayout(null);
		panel.setBounds(235, 313, 229, 270);
		panel.setVisible(false);
		add(panel);

		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_1.getText());
			}
		});
		button_1.setBounds(10, 4, 70, 54);
		panel.add(button_1);

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_2.getText());
			}
		});
		button_2.setBounds(80, 4, 70, 54);
		panel.add(button_2);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_3.getText());
			}
		});
		button_3.setBounds(150, 4, 70, 54);
		panel.add(button_3);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_4.getText());
			}
		});
		button_4.setBounds(10, 58, 70, 54);
		panel.add(button_4);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_5.getText());
			}
		});
		button_5.setBounds(80, 58, 70, 54);
		panel.add(button_5);

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_6.getText());
			}
		});
		button_6.setBounds(150, 58, 70, 54);
		panel.add(button_6);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_7.getText());
			}
		});
		button_7.setBounds(10, 112, 70, 54);
		panel.add(button_7);

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_8.getText());
			}
		});
		button_8.setBounds(80, 112, 70, 54);
		panel.add(button_8);

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_9.getText());
			}
		});
		button_9.setBounds(150, 112, 70, 54);
		panel.add(button_9);

		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText(tfMoney.getText() + button_0.getText());
			}
		});
		button_0.setBounds(80, 165, 70, 54);
		panel.add(button_0);

		JButton button_C = new JButton("C");
		button_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMoney.setText("");
			}
		});
		button_C.setBounds(10, 216, 210, 54);
		panel.add(button_C);
	}
}
