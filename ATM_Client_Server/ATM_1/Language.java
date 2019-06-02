package ATM_1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Language extends JPanel {

	/**
	 * Create the panel.
	 */
	public Language() {
		setLayout(null);
		
		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnglish.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnglish.setBounds(560, 438, 130, 145);
		add(lblEnglish);
		
		JLabel lblTingVit = new JLabel("Ti\u1EBFng Vi\u1EC7t");
		lblTingVit.setHorizontalAlignment(SwingConstants.CENTER);
		lblTingVit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTingVit.setBounds(560, 282, 130, 145);
		add(lblTingVit);

	}

}
