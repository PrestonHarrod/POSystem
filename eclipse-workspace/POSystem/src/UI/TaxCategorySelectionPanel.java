package UI;

import javax.swing.DefaultListModel;
import javax.swing.event.*;
import javax.swing.*;

import PD.Store;
import PD.TaxCategory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaxCategorySelectionPanel extends JPanel {
	
	DefaultListModel<TaxCategory> listModel;
	JList<TaxCategory> taxList;
	/**
	 * Create the panel.
	 */
	public TaxCategorySelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblTaxCategories = new JLabel("Tax Categories");
		lblTaxCategories.setBounds(190, 59, 79, 13);
		add(lblTaxCategories);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel taxEditPanel = new TaxCategoryEditPanel(currentFrame, store, (TaxCategory) new TaxCategory(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(52, 206, 85, 21);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel taxEditPanel = new TaxCategoryEditPanel(currentFrame, store, (TaxCategory)taxList.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(179, 206, 85, 21);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory((TaxCategory)taxList.getSelectedValue());
				TaxCategorySelectionPanel selectionPanel = new TaxCategorySelectionPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(selectionPanel);
				currentFrame.revalidate();
			}
		});
		btnDelete.setBounds(309, 206, 85, 21);
		add(btnDelete);
		
		listModel = new DefaultListModel<TaxCategory>();
		for (TaxCategory tc : store.getTaxCategories().values())
		{
			listModel.addElement(tc);
		}
		
		taxList = new JList<TaxCategory>(listModel);
		taxList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (taxList.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);
				}
				if (taxList.getSelectedValue() == null || store.isTaxUsed((TaxCategory) taxList.getSelectedValue()))
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		taxList.setBounds(76, 86, 292, 97);
		add(taxList);
		setLayout(null);
		
		
	}
}
