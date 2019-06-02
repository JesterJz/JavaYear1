package ATM_1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ChangePIN extends JPanel {
	private JPasswordField tfpassMKC;
	private JPasswordField tfpassMKM;
	private JPasswordField tfpassNhapLai;

	/**
	 * Create the panel.
	 */
	public ChangePIN() {
		setLayout(null);

		JLabel lblChangePin = new JLabel("Change Pin");
		lblChangePin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblChangePin.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePin.setBounds(204, 82, 292, 95);
		add(lblChangePin);

		JLabel lblmkM = new JLabel("M\u1EADt Kh\u1EA9u M\u1EDBi :");
		lblmkM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmkM.setBounds(101, 280, 186, 23);
		add(lblmkM);

		tfpassMKC = new JPasswordField();
		tfpassMKC.setBounds(316, 248, 186, 23);
		add(tfpassMKC);

		JLabel lblmkC = new JLabel("M\u1EADt Kh\u1EABu C\u0169 :");
		lblmkC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmkC.setBounds(101, 246, 186, 23);
		add(lblmkC);

		tfpassMKM = new JPasswordField();
		tfpassMKM.setBounds(316, 280, 186, 23);
		add(tfpassMKM);

		JLabel lblNhaplai = new JLabel("Nh\u1EADp L\u1EA1i M\u1EADt Kh\u1EABu :");
		lblNhaplai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhaplai.setBounds(101, 314, 186, 23);
		add(lblNhaplai);

		tfpassNhapLai = new JPasswordField();
		tfpassNhapLai.setBounds(316, 314, 186, 23);
		add(tfpassNhapLai);

		JLabel lblQuit = new JLabel("Tho\u00E1t ->");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuit.setBounds(530, 427, 160, 145);
		add(lblQuit);

		JButton btnChange = new JButton("\u0110\u1ED5i");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfpassMKC.getText().equals("") || tfpassMKM.getText().equals("")
						|| tfpassNhapLai.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay nhap day du thong tin");
				}
				else if (tfpassMKC.getText().equals(tfpassMKM.getText())) {
					JOptionPane.showMessageDialog(null, "Mat khau cu va mat khau moi giong nhau.");
				}
				else if (!(tfpassMKM.getText().equals(tfpassNhapLai.getText()))) {
					JOptionPane.showMessageDialog(null, "Mat khau moi va mat khau nhap lai khac nhau");
				}
				else
				{
					try {
						ConnectionServer.OutGui.writeBytes("ChangePIN" + "\n");
						ConnectionServer.OutGui.writeBytes(tfpassMKC.getText() + "\n");
						ConnectionServer.OutGui.writeBytes(tfpassMKM.getText()+ "\n");
						String tmp =ConnectionServer.Input.readLine();
						if (tmp == "1") {
							JOptionPane.showMessageDialog(null, "Mat khau cu khong dung");
						}
						else
						{
							if (ConnectionServer.Input.readLine().equals("succes")) {
								JOptionPane.showMessageDialog(null, "Doi mat khau thanh cong");
								MainATM.showPanel(new QuestionCustomer());
								MainATM.button_7.setText("Yes");
								MainATM.button_8.setText("No");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Doi mat khau that bai");
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}

			}
		});
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChange.setBounds(490, 348, 89, 35);
		add(btnChange);

	}
}
