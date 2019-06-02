package ATM_1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class QuestionCustomer extends JPanel {

	/**
	 * Create the panel.
	 */
	public QuestionCustomer() {
		setLayout(null);
		
		JLabel lblBnCMun = new JLabel("B\u1EA1n C\u00F3 Mu\u1ED1n Ti\u1EBFp T\u1EE5c Giao D\u1ECBch :");
		lblBnCMun.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBnCMun.setHorizontalAlignment(SwingConstants.CENTER);
		lblBnCMun.setBounds(169, 261, 361, 61);
		add(lblBnCMun);
		
		JLabel lblNo = new JLabel("Kh\u00F4ng");
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNo.setBounds(530, 427, 160, 145);
		add(lblNo);
		
		JLabel lblYes = new JLabel("C\u00D3");
		lblYes.setHorizontalAlignment(SwingConstants.CENTER);
		lblYes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblYes.setBounds(530, 271, 160, 145);
		add(lblYes);

	}

}
