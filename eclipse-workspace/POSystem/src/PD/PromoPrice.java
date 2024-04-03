package PD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents promotional prices
 */
public class PromoPrice extends Price implements Comparable<Price>{

	/**
	 * end date of the promotional price
	 */
	private LocalDate endDate;

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public PromoPrice() {
		this.endDate = LocalDate.now();
	}

	/**
	 * 
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(String price, String effectiveDate, String endDate) {
		super(price, effectiveDate);
		this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy"));
	}

	/**
	 * check if the promo price is effective
	 * @param date
	 */
	public Boolean isEffective(LocalDate date) {
		// TODO - implement PromoPrice.toString
		throw new UnsupportedOperationException();
	}

	/**
	 * compare prices
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
		return super.toString();
	}

}