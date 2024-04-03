package UI;

import javax.swing.*;

import PD.Store;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame frame, Store store) {
		setLayout(null);
		
		JLabel lblEditStore = new JLabel("Edit Store");
		lblEditStore.setBounds(182, 44, 46, 13);
		add(lblEditStore);
		
		JButton btnEdit = new JButton("Save");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				POSHomePanel home = new POSHomePanel(store);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(home);
				frame.revalidate();
			}
		});
		btnEdit.setBounds(92, 132, 85, 21);
		add(btnEdit);
		
		textField = new JTextField(store.getName());
		textField.setBounds(198, 79, 147, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(119, 82, 46, 13);
		add(lblName);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POSHomePanel home = new POSHomePanel(store);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(home);
				frame.revalidate();
			}
		});
		btnCancel.setBounds(219, 132, 85, 21);
		add(btnCancel);

	}
}
