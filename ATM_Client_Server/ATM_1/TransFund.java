package ATM_1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TransFund extends JPanel {
	public JTextField tfTranfer;
	public JTextField tfIDNhan;
	String IDNhan, Money;

	/**
	 * Create the panel.
	 */
	public TransFund() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Chuy\u1EC3n Ti\u1EC1n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 128, 408, 97);
		add(lblNewLabel);

		JLabel lblIdNhan = new JLabel("ID T\u00E0i kho\u1EA3n Nh\u1EADn :");
		lblIdNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdNhan.setBounds(120, 314, 152, 23);
		add(lblIdNhan);

		JLabel lblSoTienChuyen = new JLabel("S\u1ED1 Ti\u1EC1n Chuy\u1EC3n :");
		lblSoTienChuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoTienChuyen.setBounds(120, 280, 152, 23);
		add(lblSoTienChuyen);

		tfTranfer = new JTextField("");
		tfTranfer.setBounds(282, 280, 201, 25);
		add(tfTranfer);
		tfTranfer.setColumns(10);

		tfIDNhan = new JTextField("");
		tfIDNhan.setBounds(282, 314, 201, 25);
		add(tfIDNhan);
		tfIDNhan.setColumns(10);

		JLabel lblQuit = new JLabel("Quit");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuit.setBounds(530, 427, 160, 145);
		add(lblQuit);

		JButton btnChuyen = new JButton("Chuy\u1EC3n");
		btnChuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIDNhan.getText().equals("") || tfTranfer.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay nhap day du cac thong tin");
				}
				try {
					ConnectionServer.OutGui.writeBytes("Transfer" + "\n");
					ConnectionServer.OutGui.writeBytes(tfIDNhan.getText() + "\n");
					ConnectionServer.OutGui.writeBytes(tfTranfer.getText() + "\n");
					if (ConnectionServer.Input.readLine().equals(0)) {
						JOptionPane.showConfirmDialog(null, "Tai khoan khong du tien chuyen.", "Jester",
								JOptionPane.OK_OPTION);
					} else {
						if (ConnectionServer.Input.readLine().equals("succes")) {
							JOptionPane.showMessageDialog(null, "Chuyen thanh cong");
							MainATM.showPanel(new QuestionCustomer());
							MainATM.button_7.setText("Yes");
							MainATM.button_8.setText("No");
						} else {
							int q = JOptionPane.showConfirmDialog(null, "Chuyen that bai.\nBan co muon thuc hien lai.",
									"Jester", JOptionPane.YES_NO_OPTION);
							if (q == JOptionPane.YES_OPTION) {
								tfTranfer.setText("");
							} else if (q == JOptionPane.NO_OPTION) {
								MainATM.showPanel(new QuestionCustomer());
								MainATM.button_7.setText("Yes");
								MainATM.button_8.setText("No");
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChuyen.setBounds(465, 372, 89, 25);
		add(btnChuyen);
	}

}
