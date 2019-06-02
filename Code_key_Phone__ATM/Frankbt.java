import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Frankbt extends JFrame implements ActionListener{
	JLabel lblDangki;
	JLabel lblEmail;
	JTextField tfEmail;
	JLabel lblPass;
	JPasswordField tfPass;
	JRadioButton rdoNam;
	JRadioButton rdoNu;
	JComboBox cboHome;//bang xo xuong nhu ngay thang nam
	//tai checkbox
	JLabel lblHobby;
	JCheckBox cbgame;
	JCheckBox cblol;
	JButton btnDangKi;
	ButtonGroup bg;//nhom cac radiobutton lai

public Frankbt()
{
	lblDangki = new JLabel();
	lblDangki.setText("dang ki:");
	lblEmail = new JLabel();
	lblEmail.setText("email");
	tfEmail = new JTextField(10);
	lblPass = new JLabel();
	lblPass.setText("mat khau");
	tfPass = new JPasswordField(10);
	rdoNam = new JRadioButton();
	rdoNam.setText("nam");
	rdoNu =  new JRadioButton();
	rdoNu.setText("Nu");
	bg = new ButtonGroup();
	bg.add(rdoNam);
	bg.add(rdoNu);
	cboHome = new JComboBox();
	cboHome.addItem("Quang Nam");// them item vao
	cboHome.addItem("ha noi");
	lblHobby = new JLabel("so thich: ");
	cbgame = new JCheckBox("pubg");
	cblol = new  JCheckBox("lmht");
	btnDangKi = new JButton("Dang ki");
	btnDangKi.addActionListener(this);
	setSize(450,350);
	Container cont = this.getContentPane();
	setLayout(new FlowLayout());
	cont.add(lblDangki);
	cont.add(lblEmail);
	cont.add(tfEmail);
	cont.add(lblPass);
	cont.add(tfPass);
	cont.add(rdoNam);
	cont.add(rdoNu);
	cont.add(cboHome);
	cont.add(lblHobby);
	cont.add(cbgame);
	cont.add(cblol);
	cont.add(btnDangKi);
	setVisible(true);
}
	public static void main(String[] args) {
		new Frankbt();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String gt = null;
		if(rdoNam.isSelected()==true)
			gt = rdoNam.getText();
		if(rdoNu.isSelected()==true)
			gt = rdoNu.getText();
		String hoppy = null;
		if(cbgame.isSelected()==true)
			hoppy+=cbgame.getText();
			if(cblol.isSelected()==true)
			hoppy+=cblol.getText();
			JOptionPane.showConfirmDialog(null, "email la:  "+tfEmail.getText()
														+"\npassword: "+tfPass.getText()
														+"\ngioi tinh: "+gt+"\ntinh thanh: "
														+cboHome.getSelectedItem()
														+"\n so thich :"+hoppy.getBytes());
	}	
}