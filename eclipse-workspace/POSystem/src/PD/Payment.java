package PD;

import java.math.BigDecimal;

/**
 * Class that represents a payment in the store
 */
public abstract class Payment {

	/**
	 * amount of the payment
	 */
	private BigDecimal amount;
	/**
	 * amount of money tendered
	 */
	private BigDecimal amtTendered;
	private boolean isCash;

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmtTendered() {
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered) {
		this.amtTendered = amtTendered;
	}
	
	public void setIsCash(boolean a)
	{
		isCash = a;
	}
	/**
	 * check if payment is cash
	 */
	public abstract Boolean countsAsCash();

}