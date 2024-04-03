package UI;

import javax.swing.*;

import PD.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpcEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UpcEditPanel(JFrame currentFrame, Store store, Item item, UPC upc, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblEditUpc = new JLabel("Edit UPC");
		lblEditUpc.setBounds(172, 51, 46, 13);
		add(lblEditUpc);
		
		JLabel lblUpcNumber = new JLabel("UPC Number");
		lblUpcNumber.setBounds(68, 108, 67, 13);
		add(lblUpcNumber);
		
		textField = new JTextField(upc.toString());
		textField.setBounds(161, 105, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAdd)
				{
					UPC aUPC = new UPC(textField.getText());
					item.addUPC(aUPC);
					store.addUPC(aUPC);
					ItemEditPanel panel = new ItemEditPanel(currentFrame, store, item, false);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(panel);
					currentFrame.revalidate();
				}
				else
				{
					upc.setUPC(textField.getText());
					ItemEditPanel panel = new ItemEditPanel(currentFrame, store, item, false);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(panel);
					currentFrame.revalidate();
				}
			}
		});
		btnSave.setBounds(50, 171, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel panel = new ItemEditPanel(currentFrame, store, item, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(212, 171, 85, 21);
		add(btnCancel);

	}

}
