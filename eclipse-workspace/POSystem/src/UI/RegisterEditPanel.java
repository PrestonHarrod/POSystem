package UI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import PD.CashDrawer;
import PD.Register;
import PD.Store;
import PD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class RegisterEditPanel extends JPanel {
	private JTextField textField_1;
	private Register reg;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, Boolean isAdd) {
		setLayout(null);
		
		textField_1 = new JTextField(register.getNumber());
		textField_1.setBounds(156, 113, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRegisterEdit = new JLabel("Register Edit");
		lblRegisterEdit.setBounds(183, 90, 96, 13);
		add(lblRegisterEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = new String(textField_1.getText());
				reg = new Register(s);
				register.setNumber(reg.getNumber());
				if (isAdd) {
					store.addRegister(reg);
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(90, 217, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(249, 217, 85, 21);
		add(btnCancel);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(87, 116, 46, 13);
		add(lblNumber);
		
		

	}
}
