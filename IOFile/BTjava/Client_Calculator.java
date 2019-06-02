package BTjava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class Client_Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static Socket skClient;
	private static DataOutputStream OutGui;
	private static DataInputStream Input;
	private String SoHang1;
	private String SoHang2;
	private String ToanTu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			skClient = new Socket("localhost", 6501);
			System.out.println("Ok Connect");
			OutGui = new DataOutputStream(skClient.getOutputStream());
			Input = new DataInputStream(skClient.getInputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Client_Calculator frame = new Client_Calculator();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client_Calculator() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(10, 11, 333, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(textField.getText() + btn1.getText());
			}
		});
		
		JLabel lblShow = new JLabel("");
		lblShow.setBounds(193, 49, 150, 28);
		contentPane.add(lblShow);
		btn1.setBounds(10, 49, 51, 46);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(textField.getText() + btn2.getText());
			}
		});
		btn2.setBounds(71, 49, 51, 46);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn3.getText());
			}
		});
		btn3.setBounds(132, 49, 51, 46);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn4.getText());
			}
		});
		btn4.setBounds(10, 106, 51, 46);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn5.getText());
			}
		});
		btn5.setBounds(71, 106, 51, 46);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn6.getText());
			}
		});
		btn6.setBounds(132, 106, 51, 46);
		contentPane.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn7.getText());
			}
		});
		btn7.setBounds(10, 163, 51, 46);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn8.getText());
			}
		});
		btn8.setBounds(71, 163, 51, 46);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn9.getText());
			}
		});
		btn9.setBounds(132, 163, 51, 46);
		contentPane.add(btn9);

		JButton btn_Cham = new JButton(".");
		btn_Cham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn_Cham.getText());
			}
		});
		btn_Cham.setBounds(10, 220, 51, 46);
		contentPane.add(btn_Cham);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn0.getText());
			}
		});
		btn0.setBounds(71, 220, 51, 46);
		contentPane.add(btn0);

		JButton btnClean = new JButton("C");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnClean.setBounds(132, 220, 51, 46);
		contentPane.add(btnClean);

		JButton btn_cong = new JButton("+");
		btn_cong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoHang1 = textField.getText();
				ToanTu = btn_cong.getText();
				lblShow.setText(SoHang1+" "+ToanTu+" ");
				textField.setText("");
				try {
					OutGui.writeBytes(SoHang1 + "\n");
					OutGui.writeBytes(ToanTu + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_cong.setBounds(273, 154, 70, 55);
		contentPane.add(btn_cong);

		JButton btn_tru = new JButton("-");
		btn_tru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoHang1 = textField.getText();
				ToanTu = btn_tru.getText();
				lblShow.setText(SoHang1+" "+ToanTu+" ");
				textField.setText("");
				try {
					OutGui.writeBytes(SoHang1 + "\n");
					OutGui.writeBytes(ToanTu + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_tru.setBounds(193, 154, 70, 55);
		contentPane.add(btn_tru);

		JButton btn_nhan = new JButton("*");
		btn_nhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoHang1 = textField.getText();
				ToanTu = btn_nhan.getText();
				lblShow.setText(SoHang1+" "+ToanTu+" ");
				textField.setText("");
				try {
					OutGui.writeBytes(SoHang1 + "\n");
					OutGui.writeBytes(ToanTu+"\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_nhan.setBounds(193, 88, 70, 55);
		contentPane.add(btn_nhan);

		JButton btn_chia = new JButton("/");
		btn_chia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoHang1 = textField.getText();
				ToanTu = btn_chia.getText();
				lblShow.setText(SoHang1+" "+ToanTu+" ");
				textField.setText("");
				try {
					OutGui.writeBytes(SoHang1 + "\n");
					OutGui.writeBytes(ToanTu+"\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_chia.setBounds(273, 88, 70, 55);
		contentPane.add(btn_chia);

		JButton btn_bang = new JButton("=");
		btn_bang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoHang2 = textField.getText();
				lblShow.setText(lblShow.getText()+SoHang2+" =");
				try {
					OutGui.writeBytes(SoHang2 + "\n");
					String KetQua = Input.readLine();
					textField.setText(KetQua);
					lblShow.setText(lblShow.getText()+" "+KetQua);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_bang.setBounds(193, 220, 150, 46);
		contentPane.add(btn_bang);
		
		JLabel lblChiTinhDuoc = new JLabel("Chi tinh duoc toi da 2 toan hang");
		lblChiTinhDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTinhDuoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChiTinhDuoc.setBounds(10, 277, 360, 24);
		contentPane.add(lblChiTinhDuoc);
	}
}
