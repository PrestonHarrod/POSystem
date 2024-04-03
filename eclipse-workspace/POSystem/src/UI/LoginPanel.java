package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.*;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
	private JTextField textField;
	DefaultComboBoxModel<Cashier> tcList;
	DefaultComboBoxModel<Register> rList;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblIncorrectPassword = new JLabel("Incorrect password - Try Again");
		lblIncorrectPassword.setVisible(false);
		lblIncorrectPassword.setBackground(Color.RED);
		lblIncorrectPassword.setBounds(93, 196, 189, 13);
		add(lblIncorrectPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(183, 20, 46, 13);
		add(lblLogin);
		
		tcList = new DefaultComboBoxModel<Cashier>();
		for (Cashier tc : store.getCashiers().values())
		{
			tcList.addElement(tc);
		}
		JComboBox<Cashier> comboBox = new JComboBox<Cashier>(tcList);
		comboBox.setBounds(160, 78, 80, 21);
		add(comboBox);
		
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(93, 82, 46, 13);
		add(lblCashier);
		
		JLabel lblRegisterNumber = new JLabel("Register Number");
		lblRegisterNumber.setBounds(45, 115, 80, 13);
		add(lblRegisterNumber);
		
		rList = new DefaultComboBoxModel<Register>();
		for (Register r : store.getRegisters().values())
		{
			rList.addElement(r);
		}
		JComboBox<Register> comboBox_1 = new JComboBox<Register>(rList);
		comboBox_1.setBounds(160, 107, 80, 21);
		add(comboBox_1);
		
		JLabel lblStartingCash = new JLabel("Starting Cash");
		lblStartingCash.setBounds(27, 141, 84, 13);
		add(lblStartingCash);
		
		textField = new JTextField();
		textField.setBounds(144, 138, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 170, 74, 13);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 167, 85, 19);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier c = new Cashier();
				c = (Cashier) comboBox.getSelectedItem();
				String st = passwordField.getText();
				String sr = c.getPassword();
				if (st.compareTo(sr) < 0 | st.compareTo(sr) > 0)
				{
					lblIncorrectPassword.setVisible(true);
				}
				else {
					CashDrawer cash = new CashDrawer();
					cash.setCashAmount(new BigDecimal(textField.getText()));
					Register r = new Register();
					r = (Register) comboBox_1.getSelectedItem();
					r.setCashDrawer(cash);
					Session s = new Session((Cashier) comboBox.getSelectedItem(), r);
					s.setStartingCash(r.getCashDrawer().getCashAmount());
					c.addSession(s);
					SaleEntryPanel upc = new SaleEntryPanel(currentFrame, store, s);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(upc);
					currentFrame.revalidate();
				}
				
				
			}
		});
		btnLogin.setBounds(64, 208, 85, 21);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(193, 208, 85, 21);
		add(btnCancel);
		
		

	}
}
