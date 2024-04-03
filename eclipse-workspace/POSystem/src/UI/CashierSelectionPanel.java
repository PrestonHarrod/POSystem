package UI;

import javax.swing.*;
import PD.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CashierSelectionPanel extends JPanel {
	JList<Cashier> list_1;
	/**
	 * Create the panel.
	 */
	public CashierSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(true);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashierEditPanel cep = new CashierEditPanel(currentFrame, store, new Cashier(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cep);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(53, 190, 85, 21);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashierEditPanel cep = new CashierEditPanel(currentFrame, store, list_1.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cep);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(148, 190, 85, 21);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier(list_1.getSelectedValue());
				CashierSelectionPanel cep = new CashierSelectionPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cep);
				currentFrame.revalidate();
			}
		});
		btnDelete.setBounds(243, 190, 85, 21);
		add(btnDelete);
		
		
		DefaultListModel<Cashier> listModel = new DefaultListModel<Cashier>();
		for (Cashier c : store.getCashiers().values())
		{
			listModel.addElement(c);
		}
		
		list_1 = new JList<Cashier>(listModel);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list_1.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);
				}
				if (list_1.getSelectedValue() == null)
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		list_1.setBounds(63, 75, 259, 94);
		add(list_1);
		
	}
}
