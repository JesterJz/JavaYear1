package ATMCustomer;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcomepanel extends JPanel {

	private JButton button;

	/**
	 * Create the panel.
	 */
	public Welcomepanel() {
		setLayout(null);
		
		JLabel label = new JLabel("Welcome to ATM");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe Script", Font.ITALIC, 28));
		label.setBounds(161, 172, 378, 119);
		add(label);
		
		button = new JButton("Customer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	Main obj = new Main();
				//obj.showPanel2();
				//obj.setVisible(true);
			}
		});
		button.setBounds(161, 331, 89, 34);
		add(button);
		
		JButton button_1 = new JButton("ADMIN");
		button_1.setBounds(450, 337, 89, 34);
		add(button_1);
	}

}
