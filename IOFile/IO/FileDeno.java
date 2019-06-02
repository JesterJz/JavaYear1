package IO;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JFileChooser;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class FileDeno extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private DefaultTreeModel treeModel;
	private FileSystemView fileSystemView;
	private Desktop desktop;
	private File currentFile;
	private ListSelectionListener listSelectionListener;
	private JTree TreeFile;
	private JPanel CopyRight;
	private JProgressBar progressBar;
	private JLabel lblCopyright;
	private JTable table;
	private FileTableModel fileTableModel;
	private boolean cellSizesSet = false;
	private int rowIconPadding = 6;
	private DefaultMutableTreeNode node;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception weTried) {
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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

//				    new SwingApplication(); //Create and show the GUI.
				try {
					FileDeno frame = new FileDeno();
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
	public FileDeno() {
		setBackground(new Color(204, 153, 153));
		setTitle("File Explorer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);

		fileSystemView = FileSystemView.getFileSystemView();
		desktop = Desktop.getDesktop();

		listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = table.getSelectionModel().getLeadSelectionIndex();
				currentFile = ((FileTableModel) table.getModel()).getFile(row);
			}
		};
		table.getSelectionModel().addListSelectionListener(listSelectionListener);

		// show the file system roots.
		File[] roots = fileSystemView.getRoots();
		for (File fileSystemRoot : roots) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
			root.add(node);
			File[] files = fileSystemView.getFiles(fileSystemRoot, true);
			for (File file : files) {
				if (file.isDirectory()) {
					node.add(new DefaultMutableTreeNode(file));
				}
			}

		}

		TreeFile = new JTree(treeModel);
		TreeFile.setBorder(null);
		TreeFile.setBackground(new Color(255, 255, 255));
		TreeFile.setRootVisible(false);
		TreeFile.setCellRenderer(new FileTreeCellRenderer());
		TreeFile.expandRow(0);
		TreeFile.addTreeSelectionListener(

				new TreeSelectionListener() {

					@Override

					public void valueChanged(TreeSelectionEvent e) {
						// TreeFile.setEnabled(false);
						progressBar.setVisible(true);
						progressBar.setIndeterminate(true);
						 node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
						File file = (File) node.getUserObject();
						if (file.isDirectory()) {
							File[] files = fileSystemView.getFiles(file, true);
							if (node.isLeaf()) {
								for (File child : files) {
									if (child.isDirectory()) {
										node.add(new DefaultMutableTreeNode(child));
									}
									if (child.isFile()) {
										node.add(new DefaultMutableTreeNode(child));
									}
								}
							}
							done();
							setTableData(files);
						}

					}

					private void setTableData(final File[] files) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								if (fileTableModel == null) {
									fileTableModel = new FileTableModel();
									table.setModel(fileTableModel);
								}
								fileTableModel.setFiles(files);
								if (!cellSizesSet) {
									Icon icon = fileSystemView.getSystemIcon(files[0]);
									table.setRowHeight(icon.getIconHeight() + rowIconPadding);
									table.getColumnModel().getColumn(0).setPreferredWidth(40);
									table.getColumnModel().getColumn(1).setPreferredWidth(285);
									table.getColumnModel().getColumn(1).setMinWidth(23);
									table.getColumnModel().getColumn(3).setPreferredWidth(228);
									cellSizesSet = true;
								}
							}

						});
					}

					public void done() {
						progressBar.setIndeterminate(false);
						progressBar.setVisible(false);

					}
				});

		JScrollPane scroll_Tree = new JScrollPane(TreeFile);
		contentPane.add(scroll_Tree, BorderLayout.WEST);

		CopyRight = new JPanel();
		contentPane.add(CopyRight, BorderLayout.SOUTH);
		CopyRight.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		CopyRight.add(progressBar, BorderLayout.WEST);

		lblCopyright = new JLabel("***** Thanh  Hoài *****");
		lblCopyright.setForeground(new Color(255, 51, 51));

		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		CopyRight.add(lblCopyright, BorderLayout.CENTER);

		JPanel TableModel = new JPanel();
		contentPane.add(TableModel, BorderLayout.CENTER);
		TableModel.setLayout(new BorderLayout(0, 0));

		JScrollPane Table_scroll = new JScrollPane();
		TableModel.add(Table_scroll, BorderLayout.CENTER);
		Table_scroll.setViewportView(table);

		JPanel panel_Button = new JPanel();
		TableModel.add(panel_Button, BorderLayout.SOUTH);

		JButton btnEditSave = new JButton("Edit");
		btnEditSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (currentFile.isFile()) {

						System.out.println("Edit File:  " + currentFile);
						desktop.open(currentFile);
					} else {
						JOptionPane.showMessageDialog(null, "Không thể open . Hãy chọn 1 File.(NOT FOLDER)");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//				int returnValue = jfc.showOpenDialog(null);
//				if (returnValue == JFileChooser.APPROVE_OPTION) {
//					File selectedFile = jfc.getSelectedFile();

				try {
					if (currentFile.exists()) {
						FileInputStream fis = new FileInputStream(currentFile);
						File fileN = new File("C:\\Users\\Administrator\\Desktop\\Test\\q.txt");
						FileOutputStream fos = new FileOutputStream(fileN);
						byte[] arr = new byte[1024];
						while ((fis.read(arr)) != -1) {
							fos.write(arr);
						}
						fos.flush();
						fis.close();
						fos.close();
						
						
						node.add(new DefaultMutableTreeNode(fileN));
						if (currentFile.delete()) {
							JOptionPane.showMessageDialog(null, "Xoa file nguon thanh cong");
						} else {
							JOptionPane.showMessageDialog(null, "xoa file nguon that bai");
						}
					} else {
						System.out.println("file nguồn không tồn tại");

					}
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
			// }
		});
		panel_Button.add(btnCopy);
		panel_Button.add(btnEditSave);

		JButton btnOpen = new JButton("Open");
		panel_Button.add(btnOpen);
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					System.out.println("Open: " + currentFile);
//					desktop.open(currentFile);
					ReadFile t1 = new ReadFile();
//					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//					int returnValue = jfc.showOpenDialog(null);
//					if (returnValue == JFileChooser.APPROVE_OPTION) {
//						File selectedFile = jfc.getSelectedFile();
//						System.out.println(selectedFile.getAbsolutePath());
//					FileWriter fw = new FileWriter(path);
//					BufferedWriter bw = new BufferedWriter(fw);
//					String data = textArea.getText();
//					bw.write(data);
//					bw.close();
//					fw.close();
					if (currentFile != null) {
//						FileInputStream fis = new FileInputStream(currentFile);
//						int data = fis.read();
//						while (fis.read() != -1) {
//							t1.gettextarea(String.valueOf((char) data));
//							data = fis.read();
//						JFileChooser jfc = new JFileChooser();
//						int returnValue = jfc.showOpenDialog(null);
//						if (returnValue == JFileChooser.APPROVE_OPTION) {
//							String path = jfc.getSelectedFile().getAbsolutePath();
						System.out.println(currentFile);
						FileReader fr = new FileReader(currentFile);
						BufferedReader br = new BufferedReader(fr);
						String s;
						while ((s = br.readLine()) != null) {

							t1.gettextarea(s + "\n");
						}

						t1.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "You not selection path");
					}

					// }

				} catch (Exception e1) {

					System.out.println(e1.getMessage());
				}

			}
		});

		JButton btnDelete = new JButton("Delete");
		panel_Button.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					System.out.println("Delete: " + currentFile);
//					if (currentFile.delete()) {
//						JOptionPane.showMessageDialog(null, "File Delected Successfully");
//					} else {
//						JOptionPane.showMessageDialog(null, "Failed Delete The File");
//					}
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
//						JOptionPane.showMessageDialog(null,"Path File Deteted : "+selectedFile);
						if (selectedFile.delete()) {
							JOptionPane.showMessageDialog(null, "File deleted successfully");
						} else {
							JOptionPane.showMessageDialog(null, "Failed to delete the file");
						}
					}
				} catch (Exception e2) {
					e2.getStackTrace();
				}

			}
		});

		JButton btnCancel = new JButton("Cancel");
		panel_Button.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
	}
}

class FileTableModel extends AbstractTableModel {

	private File[] files;
	private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
	private String[] columns = { "Icon", "File", "Size", "Date modified", "Type" };

	/**
	 * 
	 */
	FileTableModel() {
		this(new File[0]);
	}

	FileTableModel(File[] files) {
		this.files = files;
	}

	@Override
	public Object getValueAt(int row, int column) {
		File file = files[row];
		switch (column) {
		case 0:
			return fileSystemView.getSystemIcon(file);
		case 1:
			return fileSystemView.getSystemDisplayName(file);
		case 2:
			return file.length();
		case 3:
			return file.lastModified();
		case 4:
			if (file.isDirectory()) {
				return "Folder";
			} else {
				return "File";
			}

		default:
			System.err.println("Logic Error");
		}
		return "";
	}

	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return files.length;
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return ImageIcon.class;
		case 2:
			return Long.class;
		case 3:
			return Date.class;
		case 4:
			return String.class;
		}
		return String.class;
	}

	public String getColumnName(int column) {
		return columns[column];
	}

	public File getFile(int row) {
		return files[row];
	}

	public void setFiles(File[] files) {
		this.files = files;
		fireTableDataChanged();
	}
}
//new file
/*
 * try{ File file = new File("C:/myfile.txt"); if(file.createNewFile())
 * System.out.println("Success!"); else System.out.println
 * ("Error, file already exists."); } catch(IOException ioe) {
 * ioe.printStackTrace(); }
 */
