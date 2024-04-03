package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import PD.Store;

public class POSHomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSHomePanel(Store store) {

		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setBounds(119, 135, 216, 16);
		add(lblNewLabel);
	}

}
