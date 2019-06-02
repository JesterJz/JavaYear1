package MultiThread;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;

public class ClientSoN extends JFrame {

	private JPanel contentPane;
	private JTextField tfTinNhan;
	String Out;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSoN frame = new ClientSoN();
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
	public ClientSoN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfTinNhan = new JTextField();
		tfTinNhan.setBounds(82, 295, 193, 20);
		contentPane.add(tfTinNhan);
		tfTinNhan.setColumns(10);
		
		JButton btnTinh = new JButton("Send");
		btnTinh.setBounds(285, 294, 89, 23);
		contentPane.add(btnTinh);
		
		JTextArea txtTinNhan = new JTextArea();
		txtTinNhan.setBounds(10, 11, 364, 276);
		contentPane.add(txtTinNhan);
		btnTinh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Socket skClient = new Socket("localhost", 6501);
					System.out.println("Ok");
					Out=tfTinNhan.getText();
					DataOutputStream outN = new DataOutputStream(skClient.getOutputStream());
					outN.writeBytes(Out+"\n");
					DataInputStream InputN= new DataInputStream(skClient.getInputStream());
					String InPut = InputN.readLine();
					txtTinNhan.append("Toi"+InPut+"\n");
					outN.close();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
			}
				
				
			}
		});
	}
}