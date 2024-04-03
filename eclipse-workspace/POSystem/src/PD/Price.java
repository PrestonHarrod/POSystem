package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a price of an item
 */
public class Price implements Comparable<Price>{
	/**
	 * price of an item
	 */
	private BigDecimal price;
	/**
	 * effective date of the price
	 */
	private LocalDate effectiveDate;
	/**
	 * item
	 */
	private Item item;

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * price constructor
	 */
	public Price() {
		this.price = new BigDecimal("0");
		this.effectiveDate = LocalDate.now();
		this.item = new Item();
	}

	/**
	 * price constructor
	 * @param price
	 * @param effectiveDate
	 */
	public Price(String price, String effectiveDate) {
		this.price = new BigDecimal(price);
		this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
		
	}

	/**
	 * check if the price is effective
	 * @param date
	 */
	public Boolean isEffective(LocalDate date) {
		boolean a = false;
		if (effectiveDate == date)
		{
			a = true;
		}
		return a;
	}

	/**
	 * calculate total amount for quantity
	 * @param quantity
	 */
	public BigDecimal calcAmountForQty(int quantity) {
		BigDecimal x = new BigDecimal("0");
		x = x.add(getPrice());
		x = x.multiply(new BigDecimal(quantity));
		return x;
	}

	/**
	 * compare item prices
	 * @param price
	 */
	public int compareTo(Price price) {
		int result = 0;
		
		if (this.getEffectiveDate().isBefore(price.getEffectiveDate()))
		{
			result = -1;
		}
		else if (this.getEffectiveDate().isAfter(price.getEffectiveDate()))
		{
			result = 1;
		}
		return result;
		
	}

	/**
	 * return string of class
	 */
	public String toString() {
		return price.toString();
	}

}