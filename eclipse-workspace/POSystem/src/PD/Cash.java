package PD;

import java.math.BigDecimal;

/**
 * Class represents cash as a form of payment
 */
public class Cash extends Payment{

	/**
	 * cash consturctor
	 */
	public Cash() {
		super.setAmount(new BigDecimal(0));
		super.setAmtTendered(new BigDecimal(0));
	}

	/**
	 * cash constructor
	 * @param amount
	 * @param amtTendered
	 */
	public Cash(String amount, BigDecimal amtTendered) {
		this();
		super.setAmount(new BigDecimal(amount));
		super.setAmtTendered(amtTendered);
	}

	/**
	 * check if money is cash
	 */
	public Boolean countsAsCash() {
		return true;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		// TODO - implement Cash.toString
		throw new UnsupportedOperationException();
	}

}