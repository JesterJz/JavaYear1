package ATM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class KeyPad extends JFrame {

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
					KeyPad frame = new KeyPad();
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
	public KeyPad() {
		setTitle("KeyPad - Jester");
		
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
			
		Card.conection.Set_Text(c);
		
	
//		if (e.getSource() instanceof JButton){
//		JOptionPane.showMessageDialog(null, );
////		
//			// Card.conection.Set_Text(((JButton)e.getSource()).getText());
//   //     }
		}

}
