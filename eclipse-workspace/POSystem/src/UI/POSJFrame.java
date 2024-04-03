package UI;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PD.Store;

public class POSJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void open(Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSJFrame frame = new POSJFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public POSJFrame(Store store) {
		JFrame currentFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Maintain");
		menuBar.add(menu);
		
		JMenu pos = new JMenu("POS");
		menuBar.add(pos);
		
		JMenu reports = new JMenu("Reports");
		menuBar.add(reports);
		
		JMenuItem login = new JMenuItem("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new LoginPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		pos.add(login);
		
		JMenuItem storeEdit = new JMenuItem("Store");
		storeEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		
		menu.add(storeEdit);
		
		JMenuItem taxCategoryEdit = new JMenuItem("Tax Category");
		taxCategoryEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategorySelectionPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		menu.add(taxCategoryEdit);
		
		JMenuItem registerEdit = new JMenuItem("Register");
		registerEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterSelectionPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		menu.add(registerEdit);
		
		JMenuItem cashierEdit = new JMenuItem("Cashier");
		cashierEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierSelectionPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		menu.add(cashierEdit);
		
		JMenuItem itemEdit = new JMenuItem("Item");
		itemEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemSelectionScreen(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		menu.add(itemEdit);
		
		JMenuItem item = new JMenuItem("Items");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		reports.add(item);
		
		JMenuItem sales = new JMenuItem("Sales");
		sales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new SalesReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		reports.add(sales);
		
		JMenuItem cashiers = new JMenuItem("Cashiers");
		cashiers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		reports.add(cashiers);
		
		
		contentPane = new POSHomePanel(store);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}
	

}
