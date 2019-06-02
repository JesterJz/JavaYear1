package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;

public class Telephone extends Frame implements ActionListener {
	public Button keysArray[];
	public Panel keyPad;
	public TextField displayField;
	public boolean foundKey;
	public Label bottomLabel;

	public Telephone() {
		displayField = new TextField(20);
		keyPad = new Panel();
		keysArray = new Button[12];
		Label bottomLabel = new Label("Click each button above to dial your number");
		displayField.setEditable(false);
		setBackground(Color.DARK_GRAY);

//Set Frame Layout 
		setLayout(new BorderLayout());
		keyPad.setLayout(new GridLayout(4, 3, 10, 10));
//Creating Buttons
		for (int i = 0; i <= 9; i++)
			keysArray[i] = new Button(String.valueOf(i));
		keysArray[10] = new Button("*");
		keysArray[11] = new Button("#");

//adding buttons
		for (int i = 1; i <= 9; i++) // adds buttons 1-9 to Panel
			keyPad.add(keysArray[i]);
		keyPad.add(keysArray[10]);// adds * button to Panel
		keyPad.add(keysArray[0]);// adds 0 button to Panel
		keyPad.add(keysArray[11]);// adds # button to Panel

		add(displayField, BorderLayout.NORTH); // adds text field to top of Frame
		add(keyPad, BorderLayout.CENTER);// adds the keypad
		add(bottomLabel, BorderLayout.SOUTH);// adds the display at the bottom
		for (int i = 0; i < keysArray.length; i++)
			keysArray[i].addActionListener(this);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		 initComponents();
		
		
	}// end of Telephone method

	private void initComponents() 
	{
		
		
	}

	public void actionPerformed(ActionEvent e) {
		foundKey = false;
//Search fo the key pressed
		for (int i = 0; i < keysArray.length && !foundKey; i++)
			if (e.getSource() == keysArray[i])// key match found
			{
				displayField.setText(displayField.getText() + keysArray[i].getLabel());
			}
	}

	public static void main(String args[]) {
//Create a new instance of the Telephone object
		Telephone teleFrame = new Telephone();
//Set frame attributes
		teleFrame.setBounds(50, 100, 260, 300);
		teleFrame.setTitle("Telephone");
		teleFrame.setVisible(true);
	}
}