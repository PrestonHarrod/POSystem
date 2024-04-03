package UI;

import PD.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PaymentPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	Payment p;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	DefaultComboBoxModel<String> rList;
	ArrayList<String> cards;

	/**
	 * Create the panel.
	 */
	public PaymentPanel(JFrame currentFrame, Store store, Session session, Sale sale, SaleEntryPanel currentPanel) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(187, 10, 237, 199);
		add(panel);
		panel.setVisible(false);
		panel.setLayout(null);
		
		cards = new ArrayList<String>();
		cards.add("Visa");
		cards.add("MasterCard");
		cards.add("American Express");
		
		JPanel checkPanel = new JPanel();
		checkPanel.setVisible(false);
		checkPanel.setBounds(187, 10, 237, 199);
		add(checkPanel);
		checkPanel.setLayout(null);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setVisible(false);
		creditPanel.setBounds(187, 10, 237, 199);
		add(creditPanel);
		creditPanel.setLayout(null);
		
		
		JButton btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.revalidate();
				currentFrame.repaint();
			}
		});
		btnCompletePayment.setEnabled(false);
		btnCompletePayment.setBounds(177, 208, 124, 21);
		add(btnCompletePayment);
		
		textField_1 = new JTextField(sale.calcAmtTendered().toString());
		textField_1.setBounds(87, 106, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setBounds(87, 27, 46, 13);
		add(lblPayment);
		
		JLabel lblPaymentDue = new JLabel("Payment Due");
		lblPaymentDue.setBounds(10, 71, 67, 13);
		add(lblPaymentDue);
		
		textField = new JTextField(sale.calcTotal().toString());
		textField.setBounds(87, 68, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAmtTendered = new JLabel("Amt Tendered");
		lblAmtTendered.setBounds(10, 109, 67, 13);
		add(lblAmtTendered);
		
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				checkPanel.setVisible(false);
				creditPanel.setVisible(false);
			}
		});
		btnCash.setBounds(45, 146, 85, 21);
		add(btnCash);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditPanel.setVisible(true);
				panel.setVisible(false);
				checkPanel.setVisible(false);
				
			}
		});
		btnCredit.setBounds(45, 177, 85, 21);
		add(btnCredit);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				checkPanel.setVisible(true);
				creditPanel.setVisible(false);
			}
		});
		btnCheck.setBounds(45, 208, 85, 21);
		add(btnCheck);
		
		
		
		JLabel lblEnterCheck = new JLabel("Enter Check");
		lblEnterCheck.setBounds(91, 5, 100, 13);
		checkPanel.add(lblEnterCheck);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 29, 46, 13);
		checkPanel.add(lblAmount);
		
		textField_3 = new JTextField();
		textField_3.setBounds(78, 28, 96, 19);
		checkPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblRoutingNumber = new JLabel("Routing Number");
		lblRoutingNumber.setBounds(0, 60, 86, 13);
		checkPanel.add(lblRoutingNumber);
		
		textField_4 = new JTextField();
		textField_4.setBounds(78, 57, 96, 19);
		checkPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAccNumber = new JLabel("Acc Number");
		lblAccNumber.setBounds(0, 83, 70, 13);
		checkPanel.add(lblAccNumber);
		
		textField_5 = new JTextField();
		textField_5.setBounds(78, 80, 96, 19);
		checkPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblCheckNum = new JLabel("Check Num");
		lblCheckNum.setBounds(0, 106, 70, 13);
		checkPanel.add(lblCheckNum);
		
		textField_6 = new JTextField();
		textField_6.setBounds(78, 103, 96, 19);
		checkPanel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check c = new Check();
				c.setRoutingNumber(textField_4.getText());
				c.setAccountNumber(textField_5.getText());
				c.setCheckNumber(textField_6.getText());
				c.setAmtTendered(new BigDecimal(textField_3.getText()));
				sale.addPayment(c);
				c.setAmount(sale.calcAmount(c.getAmtTendered()));
				textField_1.setText(sale.calcAmtTendered().toString());
				if (sale.isPaymentEnough())
				{
					btnCompletePayment.setEnabled(true);
				}
				checkPanel.setVisible(false);
			}
		});
		btnSave_1.setBounds(1, 141, 85, 21);
		checkPanel.add(btnSave_1);
		
		JButton btnCancel_2= new JButton("Cancel");
		btnCancel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel_2.setBounds(99, 141, 85, 21);
		checkPanel.add(btnCancel_2);
		
		
		
		
		
		
		
		
		JLabel lblEnterCashPayment = new JLabel("Enter Cash Payment");
		lblEnterCashPayment.setBounds(65, 10, 98, 13);
		panel.add(lblEnterCashPayment);
		
		JLabel lblAmountTendered = new JLabel("Amount Tendered");
		lblAmountTendered.setBounds(0, 33, 82, 13);
		panel.add(lblAmountTendered);
		
		textField_2 = new JTextField(sale.calcAmtTendered().toString());
		textField_2.setBounds(110, 30, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cash c = new Cash();
				c.setAmtTendered(new BigDecimal(textField_2.getText()));
				sale.addPayment(c);
				c.setAmount(sale.calcAmount(c.getAmtTendered()));
				textField_1.setText(sale.calcAmtTendered().toString());
				if (sale.isPaymentEnough())
				{
					btnCompletePayment.setEnabled(true);
				}
				panel.setVisible(false);
			}
		});
		btnSave.setBounds(10, 74, 85, 21);
		panel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
			}
		});
		btnCancel.setBounds(110, 74, 85, 21);
		panel.add(btnCancel);
		
		
		
		
		
		JLabel lblEnterCheck1 = new JLabel("Enter Credit");
		lblEnterCheck1.setBounds(91, 5, 100, 13);
		creditPanel.add(lblEnterCheck1);
		
		JLabel lblAmount1 = new JLabel("Amount");
		lblAmount1.setBounds(10, 29, 46, 13);
		creditPanel.add(lblAmount1);
		
		textField_7 = new JTextField();
		textField_7.setBounds(78, 28, 96, 19);
		creditPanel.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblRoutingNumber1 = new JLabel("Card Type");
		lblRoutingNumber1.setBounds(0, 60, 86, 13);
		creditPanel.add(lblRoutingNumber1);
		
		
		JLabel lblAccNumber1 = new JLabel("Acc Number");
		lblAccNumber1.setBounds(0, 83, 70, 13);
		creditPanel.add(lblAccNumber1);
		
		textField_9 = new JTextField();
		textField_9.setBounds(78, 80, 96, 19);
		creditPanel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblCheckNum1 = new JLabel("Expire Date");
		lblCheckNum1.setBounds(0, 106, 70, 13);
		creditPanel.add(lblCheckNum1);
		
		textField_10 = new JTextField();
		textField_10.setBounds(78, 103, 96, 19);
		creditPanel.add(textField_10);
		textField_10.setColumns(10);
		
		rList = new DefaultComboBoxModel<String>();
		for (String r : cards)
		{
			rList.addElement(r);
		}
		JComboBox<String> comboBox_1 = new JComboBox<String>(rList);
		comboBox_1.setBounds(78, 57, 96, 19);
		creditPanel.add(comboBox_1);
		
		JButton btnSave_2 = new JButton("Save");
		btnSave_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credit c = new Credit();
				c.setAcctNumber(textField_9.getText());
				String s = (String) comboBox_1.getSelectedItem();
				c.setCardType(s);
				c.setExpireDate(LocalDate.parse(textField_10.getText(), DateTimeFormatter.ofPattern("M/d/yy")));
				c.setAmtTendered(new BigDecimal(textField_7.getText()));
				sale.addPayment(c);
				c.setAmount(sale.calcAmount(c.getAmtTendered()));
				textField_1.setText(sale.calcAmtTendered().toString());
				if (sale.isPaymentEnough())
				{
					btnCompletePayment.setEnabled(true);
				}
				creditPanel.setVisible(false);
			}
		});
		btnSave_2.setBounds(1, 141, 85, 21);
		creditPanel.add(btnSave_2);
		
		JButton btnCancel_3 = new JButton("Cancel");
		btnCancel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditPanel.setVisible(false);
			}
		});
		btnCancel_3.setBounds(99, 141, 85, 21);
		creditPanel.add(btnCancel_3);
		
		

	}
}
