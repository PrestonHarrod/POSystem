package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Class that represents a tax category
 */
public class TaxCategory {
	/**
	 * category of tax
	 */
	private String category;
	/**
	 * list of tax rates
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates() {
		return this.taxRates;
	}

	/**
	 * tax category constuctor
	 */
	public TaxCategory() {
		this.category = "";
		this.taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * tax category constructor
	 * @param category
	 */
	public TaxCategory(String category) {
		this();
		this.category = category;
	}

	/**
	 * get tax rate for the date
	 * @param date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) {
		BigDecimal x = new BigDecimal("0");
		for (TaxRate taxRate : this.getTaxRates())
		{
			if (taxRate.getEffectiveDate() == date)
			{
				x = taxRate.getTaxRate();
			}
		}
		return x;
	}

	/**
	 * Add a tax rate
	 * @param taxRate
	 */
	public void addTaxRate(TaxRate taxRate) {
		taxRates.add(taxRate);
	}

	/**
	 * remove a tax rate
	 * @param taxRate
	 */
	public void removeTaxRate(TaxRate taxRate) {
		taxRates.remove(taxRate);
	}
	
	/**
	 * return string of class
	 */
	public String toString() {
		return category;
	}

}