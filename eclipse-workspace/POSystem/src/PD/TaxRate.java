package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class that represents a tax rate
 */
public class TaxRate implements Comparable<TaxRate>{
	/**
	 * rate of tax
	 */
	private BigDecimal taxRate;
	/**
	 * date of the tax
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * tax rate constructor
	 */
	public TaxRate() {
		this.effectiveDate = LocalDate.now();
		this.taxRate = new BigDecimal(0);
	}

	/**
	 * tax rate constructor
	 * @param effectiveDate
	 * @param rate
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate) {
		this.effectiveDate = effectiveDate;
		this.taxRate = rate;
	}

	/**
	 * check if the tax rate is effective
	 * @param date
	 */
	public boolean isEffective(LocalDate date) {
		boolean a = false;
		if (effectiveDate.isBefore(date)) {
			a = true;
		}
		return a;
	}

	/**
	 * compare tax rates
	 * @param taxRate
	 */
	public int compareTo(TaxRate taxRate) {
		int result = 0;
		
		if (this.getEffectiveDate().isBefore(taxRate.getEffectiveDate()))
		{
			result = -1;
		}
		else if (this.getEffectiveDate().isAfter(taxRate.getEffectiveDate()))
		{
			result = 1;
		}
		return result;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		return taxRate.toString();
	}


}