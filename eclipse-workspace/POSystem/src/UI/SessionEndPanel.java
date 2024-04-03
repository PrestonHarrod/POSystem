package UI;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import PD.*;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class SessionEndPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	BigDecimal total;

	/**
	 * Create the panel.
	 */
	public SessionEndPanel(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		
		total = new BigDecimal("0");
		for (Sale s : session.getSales())
		{
			total = total.add(s.calcTotal());
		}
		BigDecimal x = new BigDecimal(session.getSales().size());
		textField = new JTextField(x.toString());
		textField.setBounds(132, 113, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(total.toString());
		textField_1.setBounds(132, 136, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.setEnabled(false);
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel pp = new LoginPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pp);
				currentFrame.revalidate();
			}
		});
		btnEndSession.setBounds(238, 158, 98, 21);
		add(btnEndSession);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal x = new BigDecimal("0");
				x = session.calcCashCountDiff(new BigDecimal(textField_2.getText()));
				textField_3.setText(x.toString());
				session.setEndingCash(new BigDecimal(textField_3.getText()));
				btnEndSession.setEnabled(true);
			}
		});
		textField_2.setBounds(132, 159, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(132, 182, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEndSession = new JLabel("End Session");
		lblEndSession.setBounds(160, 31, 68, 13);
		add(lblEndSession);
		
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(37, 56, 46, 13);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(37, 83, 46, 13);
		add(lblRegister);
		
		JLabel lblOfSales = new JLabel("# Of Sales");
		lblOfSales.setBounds(37, 116, 62, 13);
		add(lblOfSales);
		
		JLabel lblTotalSales = new JLabel("Total Sales");
		lblTotalSales.setBounds(37, 139, 62, 13);
		add(lblTotalSales);
		
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff.");
		lblCashCountDiff.setBounds(37, 185, 89, 13);
		add(lblCashCountDiff);
		
		JLabel lblEnterCash = new JLabel("Enter Cash");
		lblEnterCash.setBounds(37, 162, 62, 13);
		add(lblEnterCash);
		
		
		JLabel lblNewLabel = new JLabel(session.getCashier().toString());
		lblNewLabel.setBounds(106, 56, 46, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(session.getRegister().toString());
		lblNewLabel_1.setBounds(106, 83, 46, 13);
		add(lblNewLabel_1);
		
		

	}
}
