package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.*;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import PD.*;

public class SalesReportPanel extends JPanel {

	private JTextField textField_1;
	CalendarPanel calendarPanel;
	/**
	 * Create the panel.
	 */
	public SalesReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		setLayout(null);
		this.setVisible(true);
		JPanel currentPanel = this;
		JLabel lblItemReports = new JLabel("Sales Reports");
		lblItemReports.setBounds(167, 10, 60, 13);
		add(lblItemReports);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(39, 85, 293, 121);
		add(textArea);
		
		JButton btnGenereate = new JButton("Genereate");
		btnGenereate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "Sales Report:     # Sold        Cash      Check      Credit" + "\n";
				int x = 0;
				
				for (Session s : store.getSessions())
				{
					LocalDate j = LocalDate.parse(textField_1.getText());
					if (s.getStartDateTime().toLocalDate().compareTo(j) == 0)
					{
						for (Sale sale: s.getSales())
						{
							BigDecimal cash = new BigDecimal("0");
							BigDecimal check = new BigDecimal("0");
							BigDecimal credit = new BigDecimal("0");
							str += sale.getDateTime().toLocalDate().toString() + sale.calcTotal();
							for (SaleLineItem sli : sale.getSaleLineItems())
							{
								x += sli.getQuantity();
							}
							for (Payment p : sale.getPayments())
							{
								if (p instanceof Cash)
								{
									cash = cash.add(p.getAmount());
								}
								if (p instanceof Credit)
								{
									credit = credit.add(p.getAmount());
								}
								if (p instanceof Check)
								{
									check = check.add(p.getAmount());
								}
							}
							str += "       " + x + "          " + cash.toString() + "          " + check.toString() + "          " + credit.toString() + "\n";
						}
					}
					else
					{
						str += "";
					}
				}
				textArea.setText(str);
			}
		});
		btnGenereate.setBounds(78, 216, 85, 21);
		add(btnGenereate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POSHomePanel home = new POSHomePanel(store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(home);
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(202, 216, 85, 21);
		add(btnCancel);
		
		
		calendarPanel = new CalendarPanel();
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent arg0) {
				calendarPanel.setVisible(false);
				textArea.setVisible(true);
				textField_1.setVisible(true);
				btnGenereate.setVisible(true);
				btnCancel.setVisible(true);
				textField_1.setText(calendarPanel.getSelectedDate().toString());
				
			}
			public void yearMonthChanged(YearMonthChangeEvent arg0) {
			}
		});
		calendarPanel.setVisible(false);
		calendarPanel.setBounds(58, 10, 279, 318);
		add(calendarPanel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 48, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarPanel.setVisible(true);
				textArea.setVisible(false);
				textField_1.setVisible(false);
				btnGenereate.setVisible(false);
				btnCancel.setVisible(false);
			}
		});
		button.setBounds(212, 47, 48, 21);
		add(button);

	}

}
