package ATMCustomer;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Password extends JPanel {
	private JTextField tfPassword;

	/**
	 * Create the panel.
	 */
	public Password() {
		setLayout(null);
		
		JLabel lblPassword = new JLabel("Hello ");
		lblPassword.setBounds(212, 49, 276, 60);
		lblPassword.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(236, 143, 228, 40);
		add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnOK = new JButton("Ok");
		btnOK.setBounds(411, 204, 77, 40);
		add(btnOK);
		
		JPanel panel = new JPanel();
		panel.setBounds(235, 290, 229, 230);
		add(panel);
		panel.setLayout(null);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(btn1.getText());
			}
		});
		btn1.setBounds(10, 4, 70, 54);
		panel.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.setBounds(80, 4, 70, 54);
		panel.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setBounds(150, 4, 70, 54);
		panel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setBounds(10, 58, 70, 54);
		panel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setBounds(80, 58, 70, 54);
		panel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setBounds(150, 58, 70, 54);
		panel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.setBounds(10, 112, 70, 54);
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setBounds(80, 112, 70, 54);
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setBounds(150, 112, 70, 54);
		panel.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.setBounds(80, 165, 70, 54);
		panel.add(btn0);
		
		JButton btnClear = new JButton("C");
		btnClear.setBounds(10, 165, 70, 54);
		panel.add(btnClear);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(150, 165, 70, 54);
		panel.add(btnEnter);

	}

}
