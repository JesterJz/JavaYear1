
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTree;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

public class JF extends JFrame {

	private JPanel contentPane;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JF frame = new JF();
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
	public JF() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(250, 12, 412, 348);
		contentPane.add(textArea);

		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(432, 372, 89, 23);
		contentPane.add(btnOpen);
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jfc = new JFileChooser();
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						String path = jfc.getSelectedFile().getAbsolutePath();
						System.out.println(path);
						// doc du lieu trong file da chon
//						FileInputStream fis = new FileInputStream(path);
//						File file = new File("E://HD_1.txt");
//						FileOutputStream fos = new FileOutputStream(path);
//						FileWriter write = new FileWriter(path);
//						
						FileReader fr = new FileReader(path);
						BufferedReader br = new BufferedReader(fr);
						String s ;
						while((s=br.readLine())!=null)
						{
							
							textArea.append(s+"\n");
						}
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(573, 372, 89, 23);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					JFileChooser jfc = new JFileChooser();
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						String path = jfc.getSelectedFile().getPath();
						System.out.println(path);
						FileWriter fw = new FileWriter(path);
						BufferedWriter bw = new BufferedWriter(fw);
						String data = textArea.getText();
						bw.write(data);
						bw.close();
						fw.close();
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 226, 348);
		contentPane.add(scrollPane);

	}

}
