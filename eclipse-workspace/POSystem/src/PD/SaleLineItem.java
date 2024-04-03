package PD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class that represents a sale of line items
 */
public class SaleLineItem {
	/**
	 * Item
	 */
	private Item item;
	/**
	 * quantity of items
	 */
	private int quantity;
	private Sale sale;

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SaleLineItem() {
		this.quantity = 0;
		this.sale = new Sale();
		this.item = new Item();
	}

	/**
	 * 
	 * @param sale
	 * @param item
	 * @param quantity
	 */
	public SaleLineItem(Sale sale, Item item, int quantity) {
		this();
		this.sale = sale;
		this.item = item;
		this.quantity = quantity;
		
	}

	/**
	 * Calculate sub total
	 */
	public BigDecimal calcSubTotal() {
		return item.calcAmountForDateQty(LocalDate.now(), this.quantity);
	}

	/**
	 * calculate tax
	 */
	public BigDecimal calcTax() {
		Price p = new Price();
		p = item.getPriceForDate(LocalDate.now());
		BigDecimal result = new BigDecimal("0.00");
		result = item.getTaxRateForDate(LocalDate.now());
		result = result.multiply(p.calcAmountForQty(this.quantity));
		return result;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		return item.toString() + " " + this.getQuantity() + " " + item.getPriceForDate(LocalDate.now());
	}

}