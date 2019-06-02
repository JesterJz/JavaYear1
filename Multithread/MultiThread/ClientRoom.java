package MultiThread;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRoom extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel;
	private JTextField tfName;
	private JTextField tfNameChat;
	private JTextField tfMessage;
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			ClientRoom frame = new ClientRoom();
			frame.setVisible(true);
			frame.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public ClientRoom() {
		setTitle("Chat - Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblName = new JLabel("T\u00EAn \u0110\u0103ng Nh\u1EADp :");
		panel.add(lblName);

		tfName = new JTextField();
		tfName.setBackground(Color.WHITE);
		panel.add(tfName);
		tfName.setColumns(30);

		JButton btnLogin = new JButton("Login");
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkLogin(tfName.getText()))
				{
					panel.setVisible(false);
					panel_1.setVisible(true);
					tfNameChat.setText(tfName.getText());
					tfNameChat.setVisible(false);
				//	this.setTitle(tfName.getText());
				}
					
			}
		});

		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					dos.close();
					dis.close();
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblTenChat = new JLabel("T\u00EAn Chat :");
		lblTenChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenChat.setBounds(35, 11, 89, 22);
		panel_1.add(lblTenChat);

		tfNameChat = new JTextField();
		tfNameChat.setEditable(false);
		tfNameChat.setBounds(134, 12, 209, 20);
		panel_1.add(tfNameChat);
		tfNameChat.setColumns(10);

		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.setBounds(353, 11, 89, 23);
		panel_1.add(btnCancel_1);

		JTextArea txtMessage = new JTextArea();
		txtMessage.setEditable(false);
		txtMessage.setBounds(10, 44, 383, 257);
		panel_1.add(txtMessage);

		JButton btnDelete = new JButton("X\u00F3a");
		btnDelete.setBounds(341, 323, 72, 23);
		panel_1.add(btnDelete);

		JButton btnsend = new JButton("G\u1EEDi");
		btnsend.setBounds(254, 323, 78, 23);
		panel_1.add(btnsend);

		tfMessage = new JTextField();
		tfMessage.setBounds(10, 324, 234, 20);
		panel_1.add(tfMessage);
		tfMessage.setColumns(10);
	}

	/* Soket */
	private void go() {
		try {
			client = new Socket("localhost", 6501);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());

			// client.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối, có thể room chưa mở.", "Message Dialog",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	private boolean checkLogin(String nick) {
		if (nick.compareTo("") == 0)
			return false;
		else if (nick.compareTo("0") == 0) {
			return false;
		} else {
			return true;
		}
	}

}
