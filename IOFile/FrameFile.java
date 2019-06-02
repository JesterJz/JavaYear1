import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class FrameFile extends JFrame {

	private JPanel contentPane;
	private JTree tree;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

//		    new SwingApplication(); //Create and show the GUI.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameFile frame = new FrameFile();
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

	public FrameFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 156, 444);
		contentPane.add(scrollPane);

		tree = new JTree();
		DefaultMutableTreeNode top;
		tree.setModel(new DefaultTreeModel(top = new DefaultMutableTreeNode("JTree")));
		File[] roots = File.listRoots();

		for (int k = 0; k < roots.length; k++) {
			// in thu muc goc
			System.out.println(roots[k].getAbsolutePath());
			// tao doi tuong file dai dien 1 duong dan
			File driver = new File(roots[k].getPath() + "\\");
			// tra ve mang duong dan cac tap tin va thu muc con cua thu muc hien tai
			
		}
		File file = new File("D:\\GiThub_Jz");
		CheckFile(file);
		


		scrollPane.setViewportView(tree);
	}
	public void CheckFile(File file)
	{
		if(file.isHidden())
		{
			
		}
		else
		{
			System.out.println(file.getAbsolutePath());
			File[] dir = file.listFiles();
			for (int i = 0; i < dir.length; i++) {
				if(dir[i].isDirectory())
				{
					 
			                CheckFile(dir[i]);
					 
				}
				else
				{
					System.out.println("---------"+dir[i]);
				}
			}
		}
		
		
	}
}
