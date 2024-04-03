package UI;

import PD.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class SaleEntryPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	DefaultListModel<SaleLineItem> listModel;
	Sale sale;
	Boolean paymentAdded;

	/**
	 * Create the panel.
	 */
	public SaleEntryPanel(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		sale = new Sale();
		sale.setTaxFree(false);
		SaleEntryPanel currentPanel = this;
		currentPanel.revalidate();
		paymentAdded = false;
		
		JLabel lblPleaseEnterValid = new JLabel("Please Enter Valid UPC");
		lblPleaseEnterValid.setVisible(false);
		lblPleaseEnterValid.setBounds(106, 37, 140, 13);
		add(lblPleaseEnterValid);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.setEnabled(false);
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionEndPanel pp = new SessionEndPanel(currentFrame, store, session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pp);
				currentFrame.revalidate();
			}
		});
		btnEndSession.setBounds(106, 191, 99, 21);
		add(btnEndSession);
		
		for (Sale s : session.getSales())
		{
			btnEndSession.setEnabled(true);
		}
		
		
		textField_3 = new JTextField(sale.calcTax().toString());
		textField_3.setBounds(297, 109, 96, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(sale.calcTotal().toString());
		textField_4.setBounds(297, 132, 96, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAmtTendered = new JLabel("Amt Tendered");
		lblAmtTendered.setBounds(240, 158, 66, 13);
		add(lblAmtTendered);
		
		textField_5 = new JTextField(sale.calcAmtTendered().toString());
		textField_5.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_5.setText(sale.calcAmtTendered().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_5.setBounds(320, 155, 96, 19);
		add(textField_5);
		textField_5.setColumns(10);
		
		listModel = new DefaultListModel<SaleLineItem>();
		JList<SaleLineItem> list = new JList<SaleLineItem>(listModel);
		list.setBounds(10, 86, 220, 64);
		add(list);
		
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(0, 10, 46, 13);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(0, 33, 46, 13);
		add(lblRegister);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(0, 63, 46, 13);
		add(lblItem);
		
		textField_1 = new JTextField("1");
		textField_1.setBounds(218, 60, 52, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			boolean isUsed = false;
			Item item = store.findItemForUPC(textField.getText());
			if (item == null)
			{
				lblPleaseEnterValid.setVisible(true);
			}
			else
			{
				lblPleaseEnterValid.setVisible(false);
				for (SaleLineItem sl : sale.getSaleLineItems())
				{
					if (sl.getItem().equals(item))
					{
						isUsed = true;
					}
					if (isUsed)
					{
						int x = sl.getQuantity();
						int y = Integer.parseInt(textField_1.getText());
						sl.setQuantity(x + y);
						listModel.removeElement(sl);
						listModel.addElement(sl);
					}
				}
				if (!isUsed)
				{
					SaleLineItem sli = new SaleLineItem(sale, item, Integer.parseInt(textField_1.getText()));
					sale.addSaleLineItem(sli);
					listModel.addElement(sli);
				}
				textField_2.setText(sale.calcSubTotal().toString());
				if (sale.getTaxFree() == false)
				{
					textField_3.setText(sale.calcTax().toString());
				}	
				textField_4.setText(sale.calcTotal().toString());
				textField.setText("");
			}			
			}
		});
		textField.setBounds(32, 60, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(159, 63, 59, 13);
		add(lblQuantity);
		
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(172, 10, 46, 13);
		add(lblSale);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setBounds(240, 89, 46, 13);
		add(lblSubTotal);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(260, 112, 46, 13);
		add(lblTax);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(260, 135, 46, 13);
		add(lblTotal);
		
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentAdded = true;
				PaymentPanel pp = new PaymentPanel(currentFrame, store, session, sale, currentPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pp);
				currentFrame.revalidate();
				
			}
		});
		btnPayment.setBounds(20, 160, 85, 21);
		add(btnPayment);
		
		JButton btnCompleteSale= new JButton("Complete Sale");
		if (sale.isPaymentEnough())
		{
			btnCompleteSale.setEnabled(true);
		}
		else
		{
			btnCompleteSale.setEnabled(false);
		}
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					session.addSale(sale);
					SaleEntryPanel sep = new SaleEntryPanel(currentFrame, store, session);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(sep);
					currentFrame.revalidate();
			}
		});
		btnCompleteSale.setBounds(106, 160, 99, 21);
		add(btnCompleteSale);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleEntryPanel sep = new SaleEntryPanel(currentFrame, store, session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(sep);
				currentFrame.revalidate();
				currentFrame.repaint();
			}
		});
		btnCancelSale.setBounds(20, 191, 85, 21);
		add(btnCancelSale);
		
		textField_2 = new JTextField(sale.calcSubTotal().toString());
		textField_2.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_2.setText(sale.calcSubTotal().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_2.setBounds(296, 86, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(260, 181, 46, 13);
		add(lblChange);
		
		textField_6 = new JTextField(sale.calcChange().toString());
		textField_6.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_6.setText(sale.calcChange().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
				
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_6.setBounds(320, 178, 96, 19);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(session.getCashier().toString());
		lblNewLabel.setBounds(56, 10, 46, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(session.getRegister().toString());
		lblNewLabel_1.setBounds(59, 33, 46, 13);
		add(lblNewLabel_1);
		
		JCheckBox chckbxTaxFree = new JCheckBox("Tax Free");
		chckbxTaxFree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTaxFree.isSelected())
				{
					sale.setTaxFree(true);
					textField_3.setText(sale.calcTax().toString());
					if (paymentAdded)
						textField_6.setText(sale.calcChange().toString());
					textField_2.setText(sale.calcSubTotal().toString());
					textField_4.setText(sale.calcTotal().toString());
				}
				else
				{
					sale.setTaxFree(false);
					textField_3.setText(sale.calcTax().toString());
					if (paymentAdded)
						textField_6.setText(sale.calcChange().toString());
					textField_2.setText(sale.calcSubTotal().toString());
					textField_4.setText(sale.calcTotal().toString());
				}
			}
		});
		chckbxTaxFree.setBounds(297, 25, 95, 21);
		add(chckbxTaxFree);
		
		
	}
}
