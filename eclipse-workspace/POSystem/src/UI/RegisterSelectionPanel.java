package UI;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import PD.Register;
import PD.Store;
import PD.TaxCategory;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterSelectionPanel extends JPanel {
	JList<Register> list;
	/**
	 * Create the panel.
	 */
	public RegisterSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		DefaultListModel<Register> listModel = new DefaultListModel<Register>();
		for (Register register : store.getRegisters().values())
		{
			listModel.addElement(register);
		}
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				RegisterEditPanel registerPanel = new RegisterEditPanel(currentFrame, store, reg, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(76, 212, 80, 21);
		add(btnAdd);
		
		JLabel lblSelectRegister = new JLabel("Select Register");
		lblSelectRegister.setBounds(175, 52, 106, 13);
		add(lblSelectRegister);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterEditPanel registerPanel = new RegisterEditPanel(currentFrame, store, (Register)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerPanel);
				currentFrame.revalidate();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister(list.getSelectedValue());
				RegisterSelectionPanel reg = new RegisterSelectionPanel(currentFrame, store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(reg);
				currentFrame.revalidate();
			}
		});
		btnDelete.setBounds(290, 212, 85, 21);
		add(btnDelete);
		
		list = new JList<Register>(listModel);
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
		list.setBounds(120, 91, 216, 96);
		add(list);
		
		btnEdit.setBounds(175, 212, 85, 21);
		add(btnEdit);

	}
}
