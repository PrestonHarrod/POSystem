package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import PD.*;

public class CashierReportPanel extends JPanel {
	
	private JTextField textField_1;
	CalendarPanel calendarPanel;
	/**
	 * Create the panel.
	 */
	public CashierReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(39, 85, 383, 121);
		add(textArea);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal x = new BigDecimal("0.00");
				BigDecimal total = new BigDecimal("0.00");
				String str = "";
				str = str + "Item Report For: \n";
				str += "Num " + "\t" + "Name \t Count \t Total \n";
				for (Cashier cashier : store.getCashiers().values())
				{
					str += cashier.getNumber() + "\t" + cashier.getPerson().getName() + "\t" + cashier.getSalesNum(calendarPanel.getSelectedDate()) + "\t" + cashier.getSalesForDate(calendarPanel.getSelectedDate()) + "\n";
					total = total.add(new BigDecimal(cashier.getSalesForDate(calendarPanel.getSelectedDate())));
					//x = x.add(new BigDecimal(cashier.getSalesForDate(calendarPanel.getSelectedDate())));
				}
				str += "\n" + "Total: " + total; 
				textArea.setText(str);
			}
		});
		btnGenerate.setBounds(52, 198, 85, 21);
		add(btnGenerate);
		
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				POSHomePanel pp = new POSHomePanel(store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(pp);
				currentFrame.revalidate();
			}
		});
		btnClose.setBounds(196, 198, 85, 21);
		add(btnClose);
		
		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setBounds(175, 10, 68, 13);
		add(lblCashierReport);
		
		calendarPanel = new CalendarPanel();
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent arg0) {
				calendarPanel.setVisible(false);
				textArea.setVisible(true);
				textField_1.setVisible(true);
				btnGenerate.setVisible(true);
				btnClose.setVisible(true);
				textField_1.setText(calendarPanel.getSelectedDate().toString() );
				
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
				btnGenerate.setVisible(false);
				btnClose.setVisible(false);
			}
		});
		button.setBounds(212, 47, 48, 21);
		add(button);

	}
}
