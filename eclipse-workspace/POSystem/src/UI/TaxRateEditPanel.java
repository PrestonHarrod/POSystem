package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class TaxRateEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, TaxRate rate, TaxCategory taxCategory, Store store, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblTaxRateEdit = new JLabel("Tax Rate Edit");
		lblTaxRateEdit.setBounds(177, 45, 61, 13);
		add(lblTaxRateEdit);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(87, 94, 46, 13);
		add(lblRate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(87, 133, 46, 13);
		add(lblDate);
		
		textField = new JTextField(rate.getTaxRate().toString());
		
		textField.setBounds(142, 91, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(rate.getEffectiveDate().toString());
		textField_1.setBounds(142, 130, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isAdd)
				{
					TaxRate r = new TaxRate();
					r.setEffectiveDate(LocalDate.parse(textField_1.getText()));
					r.setTaxRate(new BigDecimal(textField.getText()));
					taxCategory.addTaxRate(r);
				}
				else
				{
					rate.setTaxRate(new BigDecimal(textField.getText()));
					rate.setEffectiveDate(LocalDate.parse(textField_1.getText()));
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, taxCategory, true));
				currentFrame.getContentPane().revalidate();
		
			}
		});
		btnSave.setBounds(48, 179, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, taxCategory, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(177, 179, 85, 21);
		add(btnCancel);

	}
}
