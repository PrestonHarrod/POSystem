package PD;

import java.time.LocalDate;
import java.util.*;
import DM.*;

/**
 * Class that represents a store
 */
public class Store {
	/**
	 * Store number
	 */
	private String number;
	/**
	 * Store name
	 */
	private String name;
	/**
	 * Tax categories for the store
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	/**
	 * Collections of items the store has
	 */
	private TreeMap<String, Item> items;
	/**
	 * Collection of cashiers at the store
	 */
	private TreeMap<String, Cashier> cashiers;
	/**
	 * Collection of registers at the store
	 */
	private TreeMap<String, Register> registers;
	/**
	 * Collection of sessions at the store
	 */
	private ArrayList<Session> sessions;
	/**
	 * Collection of UPC codes
	 */
	private TreeMap<String, UPC> upcs;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMap<String, TaxCategory> getTaxCategories() {
		return this.taxCategories;
	}

	public TreeMap<String, Item> getItems() {
		return this.items;
	}

	public TreeMap<String, Cashier> getCashiers() {
		return this.cashiers;
	}

	public TreeMap<String, Register> getRegisters() {
		return this.registers;
	}

	public ArrayList<Session> getSessions() {
		return this.sessions;
	}

	public TreeMap<String, UPC> getUpcs() {
		return this.upcs;
	}

	/**
	 * Store constructor
	 */
	public Store() {
		this.number = "";
		this.name = "";
		this.items = new TreeMap<String, Item>();
		this.cashiers = new TreeMap<String, Cashier>();
		this.registers = new TreeMap<String, Register>();
		this.upcs = new TreeMap<String, UPC>();
		this.sessions = new ArrayList<Session>();
		this.taxCategories = new TreeMap<String, TaxCategory>();
	}

	/**
	 * Store constructor to set initial values
	 * @param number
	 * @param name
	 */
	public Store(String number, String name) {
		this();
		this.number = number;
		this.name = name;
	}

	/**
	 * Add item to the store
	 * @param item
	 */
	public void addItem(Item item) {
		items.put(item.getNumber(), item);
	}

	/**
	 * Delete item from the store
	 * @param item
	 */
	public void removeItem(Item item) {
		items.remove(item.getNumber());
	}

	/**
	 * Add UPC to the store
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		upcs.put(upc.getUPC(), upc);
	}

	/**
	 * Remove UPC from the store
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		upcs.remove(upc.getUPC());
	}

	/**
	 * Add register to the store
	 * @param register
	 */
	public void addRegister(Register register) {
		registers.put(register.getNumber(), register);
	}

	/**
	 * Remove register from the store
	 * @param register
	 */
	public void removeRegister(Register register) {
		registers.remove(register.getNumber());
	}

	/**
	 * Add cashier to the store
	 * @param cashier
	 */
	public void addCashier(Cashier cashier) {
		this.cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * remove cashier from the store
	 * @param cashier
	 */
	public void removeCashier(Cashier cashier) {
		this.cashiers.remove(cashier.getNumber());
	}

	/**
	 * Add Tax Category to the store
	 * @param taxCategory
	 */
	public void addTaxCategory(TaxCategory taxCategory) {
		this.taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Remove Tax Category from the store
	 * @param taxCategory
	 */
	public void removeTaxCategory(TaxCategory taxCategory) {
		this.taxCategories.remove(taxCategory.getCategory());
	}

	/**
	 * Add session to the store
	 * @param session
	 */
	public void addSession(Session session) {
		this.sessions.add(session);
	}

	/**
	 * Remove session from the store
	 * @param session
	 */
	public void removeSession(Session session) {
		this.sessions.remove(session);
	}

	/**
	 * Find a register by its number
	 * @param number
	 */
	public Register findRegisterByNumber(String number) {
		return registers.get(number);

	}

	/**
	 * Find an item by its UPC
	 * @param upc
	 */
	public Item findItemForUPC(String upc) {
		Item item = null;
		for (UPC u : this.getUpcs().values())
		{
			if (upc.equals(u.getUPC()))
			{
				item = u.getItem();
			}
		}
		return item;
		
	}

	/**
	 * Find an item by its number
	 * @param number
	 */
	public Item findItemForNumber(String number) {
		return items.get(number);

	}

	/**
	 * Find a cashier by its number
	 * @param number
	 */
	public Cashier findCashierForNumber(String number) {
		return cashiers.get(number);
		
	}

	/**
	 * Find a tax category by its name
	 * @param category
	 */
	public TaxCategory findTaxCategoryByName(String category) {
		 return taxCategories.get(category);
		
	}
	
	public void loadStore()
	{
		GetData.loadData(this);
	}
	
	public Boolean isTaxUsed(TaxCategory taxCategory)
	{
		Boolean result = false;
		for (Item i : this.getItems().values())
		{
			if (i.getTaxCategory().getCategory() == taxCategory.getCategory())
			{
				result = true;
			}
		}
		return result;
	}
	/**
	 * Return string of object
	 */
	public String toString() {
		String x = name + "\n" + "===============" + "\n";
		for (Cashier cashier : this.getCashiers().values())
		{
			x += cashier.toString() + "\n";
		}
		x += "=============" + "\n" + "Registers" + "\n" + "==============" + "\n";
		for (Register register : this.getRegisters().values())
		{
			x += register.toString() + "\n";
		}
		x += "=============" + "\n" + "Items" + "\n" + "==============" + "\n";
		for (Item item : this.getItems().values())
		{
			for (UPC upc : item.getUPCs().values())
			{
				x += upc.toString() + " ";
				x += item.toString() + " Price: ";
				x += item.getPriceForDate(LocalDate.now()) + " Tax Rate: ";
				x += item.getTaxRateForDate(LocalDate.now()) + "\n";
			}
		}
		x += "=============" + "\n" + "Sessions" + "\n" + "==============" + "\n";
		for (Session session : this.getSessions())
		{
			x += session.toString();
			for (Sale sale : session.getSales())
			{
				x += "Sale: " + sale + "\n";
				
			for (SaleLineItem si : sale.getSaleLineItems())
			{
				Item item = si.getItem();
				for (UPC upc : item.getUPCs().values())
				{
					x += item.getNumber() + " ";
					x += item.getDescription() + " ";
					x += si.getQuantity() + " @$";
					x += item.getPriceForDate(LocalDate.now()) + " $";
					x += item.calcAmountForDateQty(LocalDate.now(), si.getQuantity()) + " ";
					x += item.getPriceForDate(LocalDate.now()).getEffectiveDate().toString() + "\n";
				}
			}
			
			}
		}
		return x;
	}
}