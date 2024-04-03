package TEST;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import PD.CashDrawer;
import PD.Cashier;
import PD.Item;
import PD.Person;
import PD.Price;
import PD.Register;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;
import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;
import PD.UPC;

public class StoreTest {

	private Store store;
		
	public StoreTest()
	{
		store = new Store();
		store.setName("Jennys Store");
		store.setNumber("1");
		
		TaxCategory produce = new TaxCategory("Produce");
		produce.addTaxRate(new TaxRate(LocalDate.parse("02.11.2019", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.7")));
		produce.addTaxRate(new TaxRate(LocalDate.parse("14.06.19", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.6")));
		produce.addTaxRate(new TaxRate(LocalDate.parse("11.05.19", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.5")));
	
		TaxCategory chips = new TaxCategory("Chips");
		chips.addTaxRate(new TaxRate(LocalDate.parse("02.11.19", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.1")));
		chips.addTaxRate(new TaxRate(LocalDate.parse("14.06.19", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.15")));
		chips.addTaxRate(new TaxRate(LocalDate.parse("11.05.19", DateTimeFormatter.ofPattern("dd.MM.yy")), new BigDecimal("0.2")));

		
		Item lettuce = new Item("1", "lettuce");
		lettuce.setTaxCategory(produce);
		lettuce.addPrice(new Price("1.25", "02.11.19"));
		lettuce.addPrice(new Price("1.50", "05.05.19"));
		lettuce.addPrice(new Price("1.6", "01.05.19"));
		lettuce.addUPC(new UPC("12345"));
		
		Item doritos = new Item("2", "doritos");
		doritos.setTaxCategory(chips);
		doritos.addPrice(new Price("2.00", "01.11.19"));
		doritos.addPrice(new Price("2.10", "05.11.19"));
		doritos.addPrice(new Price("2.15", "03.05.19"));
		doritos.addUPC(new UPC("357621"));
		
		Item apple = new Item("3", "apple");
		apple.setTaxCategory(produce);
		apple.addPrice(new Price("0.5", "15.05.19"));
		apple.addPrice(new Price("0.6", "11.08.19"));
		apple.addPrice(new Price("0.7", "11.07.19"));
		apple.addUPC(new UPC("1489753"));
		
		this.store.addItem(lettuce);
		this.store.addItem(doritos);
		this.store.addItem(apple);
		
		Person person1 = new Person("Joe", "500 South ST", "Atlanta", "Georgia", "12345", "123456789", "76868");
		Person person2 = new Person("Hunter", "600 North ST", "Seattle", "Washington", "45678", "2334445555", "83726");
		Cashier cashier1 = new Cashier("1", person1, "password");
		Cashier cashier2 = new Cashier("2", person2, "password2");
		
		this.store.addCashier(cashier1);
		this.store.addCashier(cashier2);
		
		Register register1 = new Register("00001");
		Register register2 = new Register("00002");
		
		this.store.addRegister(register1);
		this.store.addRegister(register2);
	}
	
	public StoreTest(Store store)
	{
		this();
		this.store.setName(store.getName());
		this.store.setNumber(store.getNumber());
	}
	
	public void itemTest()
	{
	
		
		System.out.print("______________________ITEM TEST_______________________" + "\n");
		
		for (Item item : this.store.getItems().values())
		{
			for (UPC upc : item.getUPCs().values())
			{
				System.out.print(upc.toString() + " ");
				System.out.print(item.toString() + " ");
				System.out.print(item.getPriceForDate(LocalDate.now()) + " ");
				System.out.print(item.getTaxRateForDate(LocalDate.now()) + "\n");
			}
		}
	}
	
	public void cashierTest() {
		
		
		System.out.print("___________________CASHIER TEST________________________" + "\n");
		for (Cashier cashier : store.getCashiers().values())
		{
			System.out.print(cashier.toString() + "\n");
		}
		
	}
	
	public void registerTest()
	{
		
		System.out.print("___________________REGISTER TEST___________________" + "\n");
		for (Register register : store.getRegisters().values())
		{
			System.out.print(register.toString() + "\n");
			
		}
	}
	
	public void SessionTest()
	{
		
		
		Session session1 = new Session(this.store.findCashierForNumber("1"), this.store.findRegisterByNumber("00001"));
		Sale sale = new Sale("y");
		SaleLineItem sli1 = new SaleLineItem(sale, store.findItemForNumber("1"), 2);
		SaleLineItem sli2 = new SaleLineItem(sale, store.findItemForNumber("2"), 1);
		
		sale.addSaleLineItem(sli1);
		sale.addSaleLineItem(sli2);
		session1.addSale(sale);
		store.addSession(session1);
		
		System.out.print("__________________SESSION TEST___________________" + "\n");
		
		for (Session session : store.getSessions())
		{
			System.out.print(session.toString() + " Total: " + sale.calcTotal() + "\n");
			for (Sale s : session.getSales())
			{
				System.out.print(s.toString() + "\n");
			}
			for (SaleLineItem si : sale.getSaleLineItems())
			{
				Item item = si.getItem();
				for (UPC upc : item.getUPCs().values())
				{
					System.out.print(upc.toString() + " ");
					System.out.print(item.getDescription() + " ");
					System.out.print(si.getQuantity() + " ");
					System.out.print(item.calcAmountForDateQty(LocalDate.now(), si.getQuantity()) + " ");
					System.out.print(item.getTaxRateForDate(LocalDate.now()) + "\n");
				}
			}
		}
	}

}
