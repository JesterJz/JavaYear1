package MultiThread;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame implements ActionListener{
	
	private JButton close;
	public JTextArea user;
	private ServerSocket server;
	public Hashtable<String, ClientConnect> listUser;
	
	public Server(){
		super("Chat Chit : Server");
		setTitle("Chat - Server");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					//gá»Ÿi tin nháº¯n tá»›i táº¥t cáº£ client
					server.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		});
		setSize(400, 400);
		addItem();
		setVisible(true);
	}
	
	private void addItem() {
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(new JLabel("Tr\u1EA1ng Th\u00E1i Sever:"),BorderLayout.NORTH);
		getContentPane().add(new JPanel(),BorderLayout.EAST);
		getContentPane().add(new JPanel(),BorderLayout.WEST);
		
		user = new JTextArea(10,20);
		user.setEditable(false);
		getContentPane().add(new JScrollPane(user),BorderLayout.CENTER);
		
		close = new JButton("Close Server");
		close.addActionListener(this);
		getContentPane().add(close,BorderLayout.SOUTH);

		user.append("Máy chủ đã mở\n");
	}
	
	private void go(){
		try {
			listUser = new Hashtable<String, ClientConnect>();
			server = new ServerSocket(2207);
			user.append("Bắt đầu phục vụ\n");
			while(true){
				Socket client = server.accept();
				new ClientConnect(this,client);
			}
		} catch (IOException e) {
			user.append("Không thể khởi động Sever\n");
		}
	}
	
	public static void main(String[] args) {
		new Server().go();
	}

	public void actionPerformed(ActionEvent e) {
			try {
				server.close();
			} catch (IOException e1) {
				user.append("Không thể khởi động Sever\n");
			}
			System.exit(0);
	}
	
	public void sendAll(String from, String msg){
		Enumeration e = listUser.keys();
		String name=null;
		while(e. hasMoreElements()){
			name=(String) e.nextElement();
			System.out.println(name);
			if(name.compareTo(from)!=0) listUser.get(name).sendMSG("3",msg);
		}
	}
	public void sendAllUpdate(String from){
		Enumeration e = listUser.keys();
		String name=null;
		while(e. hasMoreElements()){
			name=(String) e.nextElement();
			System.out.println(name);
			if(name.compareTo(from)!=0) listUser.get(name).sendMSG("4",getAllName());
		}
	}
	
	public String getAllName(){
		Enumeration e = listUser.keys();
		String name="";
		while(e. hasMoreElements()){
			name+=(String) e.nextElement()+"\n";
		}
		return name;
	}

}
