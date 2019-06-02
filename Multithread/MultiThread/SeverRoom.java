package MultiThread;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SeverRoom extends JFrame {

	private JPanel contentPane;
	private ServerSocket server;
	private JTextArea txtTrangThai;
	public Vector<Socket> listUser;
	public Vector<String> NameUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			SeverRoom frame = new SeverRoom();
			frame.setVisible(true);
			frame.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public SeverRoom() {

		// thiet lap frame

		setTitle("Chat - Sever");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblTrangThai = new JLabel("Tr\u1EA1ng th\u00E1i Sever :");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblTrangThai, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtTrangThai = new JTextArea();
		txtTrangThai.setEditable(false);
		scrollPane.setViewportView(txtTrangThai);
		txtTrangThai.append("Máy chủ đã mở \n");

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnCloseSever = new JButton("Close Sever");
		panel_2.add(btnCloseSever);
		btnCloseSever.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.close();
				} catch (IOException e1) {
					txtTrangThai.append("Không thể khởi động Sever\n");
				}
				System.exit(0);

			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					server.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void go() {
		try {
			server = new ServerSocket(6501);
			txtTrangThai.append("Ready");
			while (true) {
				Socket s = server.accept();
				listUser.addElement(s);
				new ConnectClient(this,s);
			}

		} catch (IOException e) {
			txtTrangThai.append("Error");
		}

	}

}
