package ATM;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class KeyPadE extends JFrame {

	private JPanel contentPane;
	private Button btnKey[];
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyPadE frame = new KeyPadE();
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
	public KeyPadE() {
		setTitle("KeyPad English");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		btnKey = new Button[12];
		panel.setLayout(new GridLayout(4, 3, 10, 10));
		for(int i=0;i<=9;i++)
		{
			btnKey[i]= new Button(""+i);
		}
		btnKey[10]= new Button("*");
		btnKey[11]= new Button("#");
		for(int i=1;i<=9;i++)
		{
			panel.add(btnKey[i]);
		}
		panel.add(btnKey[10]);
		panel.add(btnKey[0]);
		panel.add(btnKey[11]);
		
		initComponents();
		}

	private void initComponents() 
	{
		for(int i=0;i<btnKey.length;i++)
		{
			btnKey[i].addActionListener(new ActionListener() 
			{
			
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					jButton1ActionPerformed(e);
				}	
			});
		}
		}
	protected void jButton1ActionPerformed(ActionEvent e) 
	{
		String c = null;
	for (int i = 0; i <  btnKey.length; i++)
	{
			if (e.getSource() == btnKey[i])// key match found
			{
				c=btnKey[i].getLabel();
				
			}
	}
			
		CardEng.conection2.Set_Text(c);
		
	


	}
}


