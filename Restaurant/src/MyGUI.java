// MyGUI.java
//Daniel Draper
//This program calculates the EF level of a 3-second gust of wind and displays it via GUI

//imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyGUI extends Thread {
	   // window size constants
	   final static int FRAME_WIDTH = 600;
	   final static int FRAME_HEIGHT = 400;
	   final static int DIALOG_WIDTH = 400;
	   final static int DIALOG_HEIGHT = 300;
	   // EF calculation constants
	   static boolean open = false;

	   // static variables, used at different parts of the program
	   private static JTextField txtSpeed;
	   
	   public void run() {
		   // create our GUI
		   GUI myGUI = new GUI();
		   myGUI.setTitle("EF Calculator");
		   myGUI.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		   myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   myGUI.setVisible(true);
		   open = true;
		   System.out.println("GUI started successfully.");
	   	}
	   
	   // DrawGUI class
	   // Creates and maintains our interface
	   static class GUI extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;
		// Interactables for our interface
		   private JLabel lblSpeed, lblFeedback;
		   static JButton btnOrder, btnSpeed, btnOpen;
		   static TextArea txtGrill, txtFryer, txtOven, txtDrink, txtShake, txtService;
		   static JPanel panelInterface, panelGrill, panelFryer, panelOven, panelDrink, panelShake, panelService;
		   GUI() {
			   // initialize interface controls
			   panelInterface = new JPanel();
			   panelGrill = new JPanel();
			   panelFryer = new JPanel();
			   panelOven = new JPanel();
			   panelDrink = new JPanel();
			   panelShake = new JPanel();
			   panelService = new JPanel();
			   
			   panelGrill.setBorder(BorderFactory.createTitledBorder("Grill"));
			   panelFryer.setBorder(BorderFactory.createTitledBorder("Grill"));
			   panelOven.setBorder(BorderFactory.createTitledBorder("Grill"));
			   panelDrink.setBorder(BorderFactory.createTitledBorder("Grill"));
			   panelShake.setBorder(BorderFactory.createTitledBorder("Grill"));
			   panelService.setBorder(BorderFactory.createTitledBorder("Grill"));
			   
			   txtGrill = setTextArea("");
			   txtFryer = setTextArea("");
			   txtOven = setTextArea("");
			   txtDrink = setTextArea("");
			   txtShake = setTextArea("");
			   txtService = setTextArea("");
			   
			   
			   btnOrder = new JButton();
			   btnSpeed = new JButton();
			   btnOpen = new JButton();
			   
			   btnOrder.addActionListener(this);
			   btnSpeed.addActionListener(this);
			   btnOpen.addActionListener(this);
			   
			   lblSpeed = new JLabel("Please enter 3-second wind gust speed in MPH:");
			   lblFeedback = new JLabel("");
			   txtSpeed = new JTextField("250");
			   panelInterface.add(lblSpeed);
			   panelInterface.add(txtSpeed);
			   panelInterface.add(lblFeedback);
			   add(panelInterface);
		   }
		   
		   
		   TextArea setTextArea(String txt) {
			  TextArea ta = new TextArea(txt);
			  ta.setPreferredSize(new Dimension(400, 100));
			  ta.setMinimumSize(new Dimension(400, 100));
			  return ta;
		   }
		   
		   public void actionPerformed(ActionEvent e) {
			   if(e.getSource() == btnOrder) {
				   
			   } else if(e.getSource() == btnSpeed) {
				   if(isNumeric(btnSpeed.getText()) && Integer.parseInt(btnSpeed.getText()) <= 50 && Integer.parseInt(btnSpeed.getText()) > 0) {
					   Restaurant.speedMultiplier = Integer.parseInt(btnSpeed.getText());
				   }
			   } else if(e.getSource() == btnOpen) {
				   btnOrder.setEnabled(false);
				   OrderUI Dialog = new OrderUI();
				   Dialog.setTitle("EF Calculator");
				   Dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
				   Dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				   Dialog.setVisible(true);
			   }
				   
		   }
		   
		   public static boolean isNumeric(String str) {  
		     try {  
		       double d = Double.parseDouble(str);
		       return true;
		     }
		     catch(NumberFormatException nfe) {
		       return false;  
		     }
		   }



		static void signal(int service, String message) {
			if(message==null) return;
			if(!Restaurant.open) {
				System.out.printf(message);
			}
			TextArea ta;
			switch(service) {
				case Service.GRILL:
					ta = txtGrill;
					break;
				case Service.FRYER:
					ta = txtFryer;
					break;
				case Service.OVEN:
					ta = txtOven;
					break;
				case Service.DRINK:
					ta = txtDrink;
					break;
				case Service.SHAKE:
					ta = txtShake;
					break;
				case Service.CASHIER:
					ta = txtService;
					break;
				case Service.SERVICE:
					ta = txtService;
					break;
				case Service.MISC:
					JOptionPane.showMessageDialog(null, message);
					break;
				default:
					break;
			}
				ta.setCaretPosition(ta.getText().length()-1);
			}

		}
			   
		   }
	   // OrderUI class
	   // Draws our graph
	   // JPanel extends JComponent by default, so no need for JComponent when we use JPanel

		class OrderUI extends JDialog implements ActionListener {
			   private static final long serialVersionUID = 1L;
			// Interactables for our interface
			   private JButton btnOrder, btnSpeed, btnOpen;
			   private JLabel lblSpeed, lblFeedback;
			   static JPanel panelInterface, panelGrill, panelFryer, panelOven, panelDrink, panelShake, panelService;
			   OrderUI() {
				   // initialize interface controls
				   /*
				   panelInterface = new JPanel();
				   panelGrill = new JPanel();
				   panelGrill = new JPanel();
				   panelFryer = new JPanel();
				   panelOven = new JPanel();
				   panelDrink = new JPanel();
				   panelShake = new JPanel();
				   panelService = new JPanel();
				   
				   btnOrder = new JButton();
				   btnSpeed = new JButton();
				   btnOpen = new JButton();
				   
				   btnOrder.addActionListener(this);
				   btnSpeed.addActionListener(this);
				   btnOpen.addActionListener(this);
				   
				   lblSpeed = new JLabel("Please enter 3-second wind gust speed in MPH:");
				   lblFeedback = new JLabel("");
				   txtSpeed = new JTextField("250");
				   panelInterface.add(lblSpeed);
				   panelInterface.add(txtSpeed);
				   panelInterface.add(btnCalculate);
				   panelInterface.add(lblFeedback);
				   add(panelInterface);*/
			   }
			   
			   
			   public void actionPerformed(ActionEvent e) {
				   
			   }
	   }
}