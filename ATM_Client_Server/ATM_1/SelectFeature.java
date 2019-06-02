package ATM_1;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectFeature extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectFeature() {
		setLayout(null);
		
		JLabel lblQuit = new JLabel("Tho\u00E1t");
		lblQuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuit.setBounds(530, 438, 160, 145);
		add(lblQuit);
		
		JLabel lblTiengViet = new JLabel("Ki\u1EC3m tra Ti\u1EC1n");
		lblTiengViet.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiengViet.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTiengViet.setBounds(530, 293, 160, 145);
		add(lblTiengViet);
		
		JLabel lblChangeMk = new JLabel("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		lblChangeMk.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeMk.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChangeMk.setBounds(530, 137, 160, 145);
		add(lblChangeMk);
		
		JLabel lblKTMoney = new JLabel("R\u00FAt Ti\u1EC1n");
		lblKTMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblKTMoney.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKTMoney.setBounds(530, 0, 160, 145);
		add(lblKTMoney);
		
		JLabel lblChuyenKhoang = new JLabel("Chuy\u1EC3n Kho\u1EA3n");
		lblChuyenKhoang.setHorizontalAlignment(SwingConstants.CENTER);
		lblChuyenKhoang.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChuyenKhoang.setBounds(10, 438, 160, 145);
		add(lblChuyenKhoang);

	}

}
