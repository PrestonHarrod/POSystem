package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Class that represents an item in the store
 */
public class Item {

	private TaxCategory taxCategory;
	/**
	 * Item number
	 */
	private String number;
	/**
	 * description of item
	 */
	private String description;
	/**
	 * list of items
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * UPC codes for items
	 */
	private TreeMap<String, UPC> uPCs;
	/**
	 * item prices
	 */
	private TreeSet<Price> prices;
	private Boolean isAdded;
	

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}

	public TreeMap<String, UPC> getUPCs() {
		return this.uPCs;
	}


	public TreeSet<Price> getPrices() {
		return this.prices;
	}

	public TaxCategory getTaxCategory() {
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory) {
		this.taxCategory = taxCategory;
	}

	/**
	 * Item constructor
	 */
	public Item() {
		this.description = "";
		this.number = "";
		this.taxCategory = new TaxCategory();
		this.prices = new TreeSet<Price>();
		this.saleLineItems = new ArrayList<SaleLineItem>();
		this.uPCs = new TreeMap<String, UPC>();
	}

	/**
	 * Item constructor
	 * @param number
	 * @param description
	 * @return 
	 */
	public Item(String number, String description) {
		this();
		this.number = number;
		this.description = description;
		
	}
	/**
	 * Add a price to an item
	 * @param price
	 */
	public void addPrice(Price price) {
		prices.add(price);
	}

	/**
	 * Remove an price from an item
	 * @param price
	 */
	public void removePrice(Price price) {
		prices.remove(price);
	}

	/**
	 * Add an UPC code to an item
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		uPCs.put(upc.getUPC(), upc);
	}

	/**
	 * Remove an UPC from an item
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		uPCs.remove(upc.getUPC());
	}

	/**
	 * Get an items price at a date
	 * @param date
	 */
	public Price getPriceForDate(LocalDate date) {
		Price x = new Price();
		for (Price price : this.getPrices())
		{
			if (price.getEffectiveDate().isBefore(date))
			{
				x = price;
			}
		}
		return x;
	}

	/**
	 * Get a tax rate for a date
	 * @param date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) {
		BigDecimal x = new BigDecimal("0");
		for (TaxRate taxRate : this.taxCategory.getTaxRates())
		{
			if (taxRate.isEffective(date))
			{
				x = taxRate.getTaxRate();
			}
		}
		
		return x;
	}

	/**
	 * calculate the price for items on a specific day
	 * @param date
	 * @param quantity
	 */
	public BigDecimal calcAmountForDateQty(LocalDate date, int quantity) {
		BigDecimal x = new BigDecimal("0");
		x = x.add(this.getPriceForDate(date).getPrice());
		x = x.multiply(new BigDecimal(quantity));
		return x;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		String s = this.getNumber() + " " + this.getDescription();
		return s;
	}

}