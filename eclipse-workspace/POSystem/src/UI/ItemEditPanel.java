package UI;

import javax.swing.*;

import PD.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ItemEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	JList<UPC> upcs;
	JList<Price> prices; 
	DefaultListModel<UPC> listModel;
	DefaultListModel<Price> listModel_2;
	DefaultComboBoxModel<TaxCategory> tcList;

	

	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblItemEdit = new JLabel("Item Edit");
		lblItemEdit.setBounds(93, 11, 46, 13);
		add(lblItemEdit);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(22, 35, 46, 13);
		add(lblNumber);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(85, 34, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 58, 60, 13);
		add(lblDescription);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(78, 55, 115, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTaxCategory = new JLabel("Tax Category");
		lblTaxCategory.setBounds(10, 81, 79, 13);
		add(lblTaxCategory);
		
		tcList = new DefaultComboBoxModel<TaxCategory>();
		for (TaxCategory tc : store.getTaxCategories().values())
		{
			tcList.addElement(tc);
		}
		
		JComboBox<TaxCategory> comboBox = new JComboBox<TaxCategory>(tcList);
		comboBox.setSelectedItem((TaxCategory) item.getTaxCategory()); 
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
			}
		});
		comboBox.setBounds(105, 84, 105, 21);
		add(comboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAdd)
				{
					item.setNumber(textField.getText());
					item.setDescription(textField_1.getText());
					store.addItem(item);
					item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
				}
				else
				{
					item.setNumber(textField.getText());
					item.setDescription(textField_1.getText());
					item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
				}
				ItemSelectionScreen isc = new ItemSelectionScreen(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(isc);
				currentFrame.revalidate();
			}
		});
		btnSave.setBounds(0, 115, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSelectionScreen isc = new ItemSelectionScreen(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(isc);
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(112, 115, 85, 21);
		add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpcEditPanel upc = new UpcEditPanel(currentFrame, store, item, new UPC(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upc);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(234, 108, 85, 21);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpcEditPanel upc = new UpcEditPanel(currentFrame, store, item, (UPC) upcs.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upc);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(331, 112, 85, 21);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeUPC(upcs.getSelectedValue());
				item.removeUPC(upcs.getSelectedValue());
				ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pep);
				currentFrame.revalidate();
			}
		});
		btnDelete.setBounds(341, 143, 85, 21);
		add(btnDelete);
		
		listModel = new DefaultListModel<UPC>();
		for (UPC upc : item.getUPCs().values())
		{
			listModel.addElement(upc);
		}
		upcs = new JList<UPC>(listModel);
		upcs.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (upcs.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);
				}
				if (upcs.getSelectedValue() == null)
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		upcs.setBounds(253, 10, 147, 85);
		add(upcs);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.setEnabled(true);
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel pep = new PriceEditPanel(currentFrame, store, item, new Price(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pep);
				currentFrame.revalidate();
			}
		});
		btnAdd_1.setBounds(191, 146, 85, 21);
		add(btnAdd_1);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.setEnabled(false);
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel pep = new PriceEditPanel(currentFrame, store, item, prices.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pep);
				currentFrame.revalidate();
			}
		});
		btnEdit_1.setBounds(191, 177, 85, 21);
		add(btnEdit_1);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setEnabled(false);
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removePrice(prices.getSelectedValue());
				ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pep);
				currentFrame.revalidate();
			}
		});
		btnDelete_1.setBounds(191, 209, 85, 21);
		add(btnDelete_1);
		
		listModel_2 = new DefaultListModel<Price>();
		for (Price price : item.getPrices())
		{
			listModel_2.addElement(price);
		}
		prices = new JList<Price>(listModel_2);
		prices.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (prices.getSelectedValue() != null)
				{
					btnEdit_1.setEnabled(true);
				}
				if (prices.getSelectedValue() == null)
				{
					btnDelete_1.setEnabled(false);
				}
				else
				{
					btnDelete_1.setEnabled(true);
				}
				
			}
		});
		prices.setBounds(20, 146, 161, 63);
		add(prices);
		

	}
}
