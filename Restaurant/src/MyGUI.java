// imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * MyGUI - threaded class that maintains our user interface
 * instantiated from main thread in restaurant
 * @author The Dude
 */
public class MyGUI extends Thread {
	// window size constants
	final static int FRAME_WIDTH = 1300;
	final static int FRAME_HEIGHT = 480;
	final static int DIALOG_WIDTH = 450;
	final static int DIALOG_HEIGHT = 520;
	
	// window status
	static boolean open = false;
	   
	public void run() {
		// create our GUI
		GUI myGUI = new GUI();
		myGUI.setTitle("Restaurant");
		myGUI.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGUI.setVisible(true);
		
		// set open for signal()
		open = true;
		System.out.println("GUI started successfully.");
	}
	   
	/**
	 *  DrawGUI - creates and maintains our interface
	 * @author The Dude
	 */
	static class GUI extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;
		// initialize interface
		static JTextField txtSpeed;
		static JLabel lblProfit, lblSpeed;
		static JButton btnOrder, btnSpeed, btnOpen;
		static TextArea txtGrill, txtFryer, txtOven, txtDrink, txtShake, txtService, txtCashier;
		static JPanel panelInterface, panelGrill, panelFryer, panelOven, panelDrink, panelShake, panelService, panelCashier;
		GUI() {
			// initialize interface controls
			getContentPane().setLayout(new BorderLayout());
			panelInterface = new JPanel();
			panelGrill = setJPanel("Grill");
			panelFryer = setJPanel("Fryer");
			panelOven = setJPanel("Oven");
			panelDrink = setJPanel("Drink fountain");
			panelShake = setJPanel("Shake machine");
			panelService = setJPanel("Service counter");
			panelCashier = setJPanel("Cashier");
	   
			// set textareas and panels
			txtGrill = setTextArea("");
			panelGrill.add(txtGrill);
			txtFryer = setTextArea("");
			panelFryer.add(txtFryer);
			txtOven = setTextArea("");
			panelOven.add(txtOven);
			txtDrink = setTextArea("");
			panelDrink.add(txtDrink);
			txtShake = setTextArea("");
			panelShake.add(txtShake);
			txtService = setTextArea("");
			panelService.add(txtService);	   
			txtCashier = setTextArea("");
			panelCashier.add(txtCashier);

			// set buttons
			btnOrder = new JButton("Make an order");
			btnSpeed = new JButton("Change speed multipler(1-50)");
			btnOpen = new JButton("Open restaurant");
			btnOrder.setEnabled(false);
			btnOrder.addActionListener(this);
			btnSpeed.addActionListener(this);
			btnOpen.addActionListener(this);
			
			// set labels
			lblProfit = new JLabel(String.format("Profit $%.2f", Restaurant.profit));
			lblSpeed = new JLabel(String.format("Current speed: %dx", Restaurant.speedMultiplier));
			txtSpeed = new JTextField(Integer.toString(Restaurant.speedMultiplier), 2);
			
			// insert panels into panels
			panelInterface.add(panelService);
			panelInterface.add(panelGrill);
			panelInterface.add(panelFryer);
			panelInterface.add(panelOven);
			panelInterface.add(panelDrink);
			panelInterface.add(panelShake);
			panelInterface.add(panelCashier);
			panelInterface.add(btnOpen);
			panelInterface.add(btnSpeed);
			panelInterface.add(txtSpeed);
			panelInterface.add(lblSpeed);
			panelInterface.add(btnOrder);
			panelInterface.add(lblProfit);
			add(panelInterface);
		}
	
		/**
		 * setJPanel(txt) - uniformly sets JPanels
		 * @param txt (String) caption for panel
		 * @return (JPanel) reference to new object
		 */
		JPanel setJPanel(String txt) {
			JPanel jp = new JPanel();
			jp.setBorder(BorderFactory.createTitledBorder(txt));
			return jp;
		}
		 
		/**
		 * setTextArea(txt) - uniformly sets TextAreas
		 * @param txt (String) text for textbox
		 * @return (TextArea) reference to new object
		 */
		TextArea setTextArea(String txt) {
			TextArea ta = new TextArea(txt, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
			ta.setSize(new Dimension(400, 100));
			ta.setPreferredSize(new Dimension(400, 100));
			ta.setMinimumSize(new Dimension(400, 100));
			ta.setEditable(false);
			return ta;
		}
		   
		/**
		 * actionPerformed(e) - listener for buttons
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOrder) {
				btnOrder.setEnabled(false);
				OrderUI Dialog = new OrderUI();
				Dialog.setTitle("Place your order");
				Dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
				Dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				Dialog.setVisible(true);
			} else if(e.getSource() == btnSpeed) {
				// check for proper input and alter speed
				if(isNumeric(txtSpeed.getText()) && Integer.parseInt(txtSpeed.getText()) <= 50 && Integer.parseInt(txtSpeed.getText()) > 0) {
					Restaurant.speedMultiplier = Integer.parseInt(txtSpeed.getText());
				}
			} else if(e.getSource() == btnOpen) {
				// flip open status
				Restaurant.open = !Restaurant.open;
				// button status checks
				if(Restaurant.open) btnOpen.setText("Close restaurant");
				else btnOpen.setText("Open restaraunt");
				if(Restaurant.yourOrder != 0 && !Restaurant.open) btnOrder.setEnabled(false);
				else btnOrder.setEnabled(true);
			}
		}
		/**
		 * isNumeric(txt) - returns if a String is numeric
		 * @param str (String) string to test
		 * @return (Boolean) true if numeric
		 */
		public static boolean isNumeric(String txt) {  
			try {
				double d = Double.parseDouble(txt);
				return true;
			} catch(NumberFormatException nfe) {
				return false;  
			}
		}


		/**
		 *  signal(service, message) - updates our interface with messages from the restaurant
		 *  originally, this was designed to be where the graphics update happens, but unfortunately
		 *  my artist skills were not great. If I were to make this into a full-fledged game, I would
		 *  do my visual updates through this.
		 * @param service (Service) Status code for which part of the restaurant to update
		 * @param message (String) Text to be displayed in updated textbox
		 */
		static void signal(int service, String message) {
			// null check
			if(message==null) return;
			// make sure the Restaurant is open
			if(!Restaurant.open) {
				System.out.printf(message);
			}
			TextArea ta = null;
			// determine which service needs updating
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
					ta = txtCashier;
					break;
				case Service.SERVICE:
					ta = txtService;
					break;
				case Service.MISC:
					// special case
					JOptionPane.showMessageDialog(null, message);
					break;
			}
			if(ta != null) {
				// update and scroll our textbox
				ta.setText(ta.getText() + message);
				ta.setCaretPosition(ta.getText().length()-1);
				
				// update labels
				lblProfit.setText(String.format("Profit $%.2f", Restaurant.profit));
				lblSpeed.setText(String.format("Current speed: %dx", Restaurant.speedMultiplier));
			} else if(service != Service.MISC) System.out.printf(message);
		}
	}

	/**
 	* OrderUI - handles the user-end of submitting orders
 	* Instantiated in GUI
 	* @author The Dude
 	*/ 
	static class OrderUI extends JDialog implements ActionListener, ListSelectionListener {
		private static final long serialVersionUID = 1L;
		
		// initialize interface elements
		JButton btnAdd, btnRemove, btnConfirm;
		JList<String> jlPrice, jlMenu, jlOrder;
		JPanel panelInterface, panelMenu, panelOrder;
		
		// constructor
		OrderUI() {
			// set interface controls
			getContentPane().setLayout(new BorderLayout());
			
			// set JPanels
			panelInterface = setJPanel("Build your order");
			panelMenu = setJPanel("Menu");
			panelOrder = setJPanel("Order");

			// set JButtons
			btnAdd = setJButton("Add item", false);
			btnRemove = setJButton("Remove item", false);
			btnConfirm = setJButton("Confirm order", false);
			
			// set JLists
			jlOrder = setJList(null);
			
			// populate jlMenu with restaurant menu
			jlPrice = setJList(new Dimension(400, 50));
			jlMenu = setJList(null);
			DefaultListModel<String> dlm= new DefaultListModel<String>();
			DefaultListModel<String> dlmPrice = new DefaultListModel<String>();
			for(int i = 1; i < Restaurant.menu.countNodes(); i++){
				dlm.addElement(Restaurant.menu.menuToFoodItem(i).getName());
				dlmPrice.addElement("$" + Double.toString(Restaurant.menu.menuToFoodItem(i).getCost()));
			}
			jlPrice.setModel(dlmPrice);
			jlPrice.setEnabled(false);
			jlPrice.setBackground(Color.BLUE);
			jlMenu.setModel(dlm);
			
			// add panels to panels
			panelMenu.add(jlPrice);
			panelMenu.add(jlMenu);	
			panelMenu.add(btnAdd);
			panelOrder.add(jlOrder);
			panelOrder.add(btnRemove);
			panelInterface.add(panelMenu);
			panelInterface.add(panelOrder);
			panelInterface.add(btnConfirm);
			
			// add main panel to JDialog
			add(panelInterface);
		}
		
		/**
		 * 
		 * @return (JList<String>) reference to new object
		 *  setJList() - uniformly sets JLists
		 */
		JList<String> setJList(Dimension d) {
			JList<String> jl = new JList<String>();
			jl.addListSelectionListener(this);
			if(d != null) jl.setSize(d);
			else jl.setSize(new Dimension(400, 200));
			jl.setModel(new DefaultListModel<String>());
			return jl;
		}
	
		/**
		 * setJPanel(txt, enabled) - uniformly sets JPanels
		 * @param txt (String) caption for button
		 * @param enabled (Boolean) set status
		 * @return (JButton) reference to new object
		 */
		JButton setJButton(String txt, boolean enabled) {
			JButton btn = new JButton(txt);
			btn.addActionListener(this);
			btn.setEnabled(false);
			return btn;
		}
		
		/**
		 * setJPanel(txt) - uniformly sets JPanels
		 * @param txt (String) caption for panel
		 * @return (JPanel) reference to new object
		 */
		JPanel setJPanel(String txt) {
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(200, 420));
			jp.setBorder(BorderFactory.createTitledBorder(txt));
			return jp;
		}
		/**
		 * actionPerformed(e) - listener for buttons
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnAdd) {
				// check for selection
				if (jlMenu.getSelectedIndices().length > 0) {
		            int[] selectedIndices = jlMenu.getSelectedIndices();
		            // add jlMenu's selected items to jlOrder
		            DefaultListModel<String> dlm = (DefaultListModel<String>) jlOrder.getModel();
		            for (int i = 0; i < selectedIndices.length; i++) {
		                // length check
		                if(dlm.getSize() >= Menu.MAX_ORDER_ITEMS) {
		                	GUI.signal(Service.MISC, "You've reached maximum order size. Remove something first.");
		    				btnAdd.setEnabled(false);
		    				return;
		                }
		                dlm.addElement(jlMenu.getModel().getElementAt(selectedIndices[i]));
		            }
		        }
				btnRemove.setEnabled(true);
				btnConfirm.setEnabled(true);
			} else if(e.getSource() == btnRemove) {
				if (jlOrder.getSelectedIndices().length > 0) {
		            int[] selectedIndices = jlOrder.getSelectedIndices();
		            // remove selected items from jlOrder
		            DefaultListModel<String> dlm = (DefaultListModel<String>) jlOrder.getModel();
		            for (int i = selectedIndices.length - 1; i >= 0; i--) {
		                dlm.removeElementAt(selectedIndices[i]);
		            }
		            btnAdd.setEnabled(true);
		        }
				// button check
				if(jlOrder.getModel().getSize() == 0) {
					btnRemove.setEnabled(false);
					btnConfirm.setEnabled(false);
				}
			} else if(e.getSource() == btnConfirm) {
				Customer c = new Customer();
				// generate your order
				c.ProduceOrder(Restaurant.menu, jlOrder.getModel());
				// set so the loop can differentiate between you and other customers
				Restaurant.yourOrder = c.getOrderNumber();

				String stringOrder = "Your order:\n" + c.PrintOrder();
				Restaurant.serviceLine.enqueue(c);
				if(!Restaurant.serviceLine.isEmpty()) {
					// don't be greedy
					stringOrder += String.format("But you'll have to wait your turn. There is %d customer(s) in front of you.\n", Restaurant.serviceLine.size());
				}
				// let user know their order
				JOptionPane.showMessageDialog(null, stringOrder);
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}

		/**
		 * valueChanged(e) - listener for listboxes
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getSource() == jlMenu) {
				if(jlMenu.getSelectedIndices().length > 0) {
					btnAdd.setEnabled(true);
				} else btnAdd.setEnabled(false);
			}
		}
	}
}