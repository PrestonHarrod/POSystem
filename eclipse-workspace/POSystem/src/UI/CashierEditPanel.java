package UI;

import javax.swing.*;

import PD.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(38, 58, 46, 13);
		add(lblName);
		
		textField = new JTextField(cashier.getPerson().getName());
		textField.setBounds(88, 55, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(26, 92, 46, 13);
		add(lblNumber);
		
		textField_1 = new JTextField(cashier.getNumber());
		textField_1.setBounds(88, 89, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(26, 123, 46, 13);
		add(lblAddress);
		
		textField_2 = new JTextField(cashier.getPerson().getAddress());
		textField_2.setBounds(88, 120, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(26, 168, 46, 13);
		add(lblPhone);
		
		textField_3 = new JTextField(cashier.getPerson().getPhone());
		textField_3.setBounds(88, 165, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(26, 146, 46, 13);
		add(lblCity);
		
		textField_4 = new JTextField(cashier.getPerson().getCity());
		textField_4.setBounds(88, 143, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setBounds(201, 74, 46, 13);
		add(lblSsn);
		
		textField_5 = new JTextField(cashier.getPerson().getSSN());
		textField_5.setBounds(242, 71, 96, 19);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(194, 123, 46, 13);
		add(lblState);
		
		textField_6 = new JTextField(cashier.getPerson().getState());
		textField_6.setBounds(225, 120, 46, 19);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(274, 123, 46, 13);
		add(lblZip);
		
		textField_7 = new JTextField(cashier.getPerson().getZip());
		textField_7.setBounds(296, 120, 96, 19);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 191, 46, 13);
		add(lblPassword);
		
		textField_9 = new JTextField(cashier.getPassword());
		textField_9.setBounds(88, 188, 96, 19);
		add(textField_9);
		textField_9.setColumns(10);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person p = new Person(textField.getText(), textField_2.getText(), textField_4.getText(), textField_6.getText(), textField_7.getText(), textField_3.getText(), textField_5.getText());
				Cashier c = new Cashier(textField_1.getText(), p, textField_9.getText());
				if (isAdd)
				{
					store.addCashier(c);
				}
				else
				{
					cashier.setPerson(p);
					cashier.setNumber(textField_1.getText());
					cashier.setPassword(textField_9.getText());
				}
				CashierSelectionPanel cep = new CashierSelectionPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cep);
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(242, 178, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashierSelectionPanel cep = new CashierSelectionPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cep);
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(242, 213, 85, 21);
		add(btnCancel);
		

	}
}
