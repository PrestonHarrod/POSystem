package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import PD.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class PriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	PromoPrice promoPrice;

	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, Store store, Item item, Price price, Boolean isAdd) {
		setLayout(null);
		JLabel lblEditPrice = new JLabel("Edit Price");
		lblEditPrice.setBounds(169, 63, 46, 13);
		add(lblEditPrice);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(66, 101, 46, 13);
		add(lblPrice);
		
		textField = new JTextField(price.getPrice().toString());
		textField.setBounds(119, 98, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(price.getEffectiveDate().toString());
		textField_1.setBounds(119, 139, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(43, 192, 46, 13);
		add(lblEndDate);
		lblEndDate.setVisible(false);
		
		
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPromoPrice.isSelected())
				{
					lblEndDate.setVisible(true);
					textField_2.setVisible(true);
				}
				else
				{
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
				}
			}
		});
		chckbxPromoPrice.setBounds(42, 59, 95, 21);
		add(chckbxPromoPrice);
		
		if (!isAdd)
		{
			chckbxPromoPrice.setEnabled(false);
		}
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 189, 96, 19);
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPromoPrice.isSelected() && isAdd)
				{
					PromoPrice pro = new PromoPrice(textField.getText(), textField_1.getText(), textField_2.getText());
					item.addPrice(pro);
					pro.setItem(item);
					ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, true);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(pep);
					currentFrame.revalidate();
				}
				else if (!chckbxPromoPrice.isSelected() && isAdd)
				{
					Price pr = new Price(textField.getText(), textField_1.getText());
					item.addPrice(pr);
					pr.setItem(item);
					ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, true);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(pep);
					currentFrame.revalidate();
				}
				else if (!isAdd && price instanceof PromoPrice)
				{
					price.setPrice(new BigDecimal(textField.getText()));
					price.setItem(item);
					ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, false);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(pep);
					currentFrame.revalidate();
				}
				else
				{
					price.setPrice(new BigDecimal(textField.getText()));
					price.setItem(item);
					ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, false);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(pep);
					currentFrame.revalidate();
				}

			}
		});
		btnSave.setBounds(255, 122, 85, 21);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel pep = new ItemEditPanel(currentFrame, store, item, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pep);
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(255, 170, 85, 21);
		add(btnCancel);
		
		JLabel lblEffectiveDate_1 = new JLabel("Effective Date");
		lblEffectiveDate_1.setBounds(42, 142, 70, 13);
		add(lblEffectiveDate_1);
		
		if (price instanceof PromoPrice)
		{
			chckbxPromoPrice.setSelected(true);
			chckbxPromoPrice.setEnabled(false);
			lblEndDate.setVisible(true);
			textField_2.setVisible(true);
			textField_2.setText(((PromoPrice) price).getEndDate().toString());
		}
		
		
		

	}
}
