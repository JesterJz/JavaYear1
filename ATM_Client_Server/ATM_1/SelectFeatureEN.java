package ATM_1;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectFeatureEN extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectFeatureEN() {
setLayout(null);
		
		JLabel lblQuit = new JLabel("Quit");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuit.setBounds(530, 438, 160, 145);
		add(lblQuit);
		
		JLabel lblTiengViet = new JLabel("Current money");
		lblTiengViet.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiengViet.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTiengViet.setBounds(530, 293, 160, 145);
		add(lblTiengViet);
		
		JLabel lblChangeMk = new JLabel("Change PIN");
		lblChangeMk.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeMk.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChangeMk.setBounds(530, 137, 160, 145);
		add(lblChangeMk);
		
		JLabel lblKTMoney = new JLabel("Withdrawal");
		lblKTMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblKTMoney.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKTMoney.setBounds(530, 0, 160, 145);
		add(lblKTMoney);
		
		JLabel lblChuyenKhoang = new JLabel("Trans Fund");
		lblChuyenKhoang.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenKhoang.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChuyenKhoang.setBounds(10, 438, 160, 145);
		add(lblChuyenKhoang);

	}

}
