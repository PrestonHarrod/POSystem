package UI;

import PD.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	JList<TaxRate> rates;
	DefaultListModel<TaxRate> listModel;
	TaxRate r;
	TaxCategory tax;
	
	

	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		tax = new TaxCategory();
		
		JLabel lblTaxCategoryEdit = new JLabel("Tax Category Edit");
		lblTaxCategoryEdit.setBounds(175, 21, 96, 13);
		add(lblTaxCategoryEdit);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(53, 101, 46, 13);
		add(lblName);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(112, 98, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(14, 151, 85, 21);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAdd) {
					taxCategory.setCategory(textField.getText());
					store.addTaxCategory(taxCategory);
				}
				else
				{
					taxCategory.setCategory(textField.getText());
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(112, 151, 85, 21);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, (TaxRate) new TaxRate(LocalDate.now(), new BigDecimal("0")), taxCategory, store, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(226, 151, 85, 21);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, rates.getSelectedValue(), taxCategory, store, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(327, 151, 85, 21);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate(rates.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, taxCategory, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setBounds(286, 191, 85, 21);
		add(btnDelete);
		
		DefaultListModel<TaxRate> listModel = new DefaultListModel<TaxRate>();
		for (TaxRate rate : taxCategory.getTaxRates())
		{
			listModel.addElement(rate);
		}
		rates = new JList<TaxRate>(listModel);
		rates.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (rates.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);
				}
				if (rates.getSelectedValue() == null)
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		rates.setBounds(245, 44, 167, 97);
		add(rates);
		

	}
}
