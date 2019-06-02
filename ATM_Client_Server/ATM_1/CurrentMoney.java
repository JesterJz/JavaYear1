package ATM_1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.xml.soap.Text;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JTextField;

public class CurrentMoney extends JPanel {
	private static JTextField textField;
	static String t;
	/**
	 * Create the panel.
	 */
	public CurrentMoney() {
		setLayout(null);

		JLabel lblSDTrong = new JLabel("S\u1ED1 d\u01B0 trong t\u00E0i kho\u1EA3n c\u1EE7a b\u1EA1n l\u00E0 :");
		lblSDTrong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSDTrong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSDTrong.setBounds(174, 172, 351, 69);
		add(lblSDTrong);

		textField = new JTextField(t);
		textField.setBounds(231, 325, 237, 20);
		add(textField);
		textField.setColumns(10);

	}

	public static void setTxtSoTienCua(String txt) {
		t=txt;
	}
}
