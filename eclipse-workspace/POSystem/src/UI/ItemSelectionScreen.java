package UI;

import javax.swing.*;

import PD.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ItemSelectionScreen extends JPanel {
	JList<Item> list;
	DefaultListModel<Item> listModel;
	/**
	 * Create the panel.
	 */
	public ItemSelectionScreen(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblItemSelection = new JLabel("Item Selection");
		lblItemSelection.setBounds(177, 23, 66, 13);
		add(lblItemSelection);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel taxEditPanel = new ItemEditPanel(currentFrame, store, (Item) new Item(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(45, 179, 85, 21);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel taxEditPanel = new ItemEditPanel(currentFrame, store, (Item) list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(140, 179, 85, 21);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem(list.getSelectedValue());
				ItemSelectionScreen isp = new ItemSelectionScreen(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(isp);
				currentFrame.revalidate();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(235, 179, 85, 21);
		add(btnDelete);
		
		
		listModel = new DefaultListModel<Item>();
		for (Item item : store.getItems().values())
		{
			listModel.addElement(item);
		}
		list = new JList<Item>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);
				}
				if (list.getSelectedValue() == null)
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
				
			}
		});
		list.setBounds(76, 42, 255, 127);
		add(list);
		
		
	}

}
