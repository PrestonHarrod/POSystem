package PD;

import java.math.BigDecimal;

/**
 * Cash drawer of a register
 */
public class CashDrawer {

	/**
	 * Amount of cash in the register
	 */
	private BigDecimal cashAmount;
	/**
	 * Position of the register; opened or closed
	 */
	private int position;

	public BigDecimal getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Add cash to the register
	 * @param cash
	 */
	public void addCash(BigDecimal cash) {
		cashAmount.add(cash);
	}

	/**
	 * Remove cash from the register
	 * @param cash
	 */
	public void removeCash(BigDecimal cash) {
		cashAmount.subtract(cash);
	}

	/**
	 * return string of the class
	 */
	public String toString() {
		String s = Integer.toString(this.getPosition());
		if (s == "1")
		{
			s = "open";
		}
		else
		{
			s = "closed";
		}
		s = s + " " + cashAmount;
		return s;
	}

	/**
	 * CashDrawer constuctor
	 */
	public CashDrawer() {
		this.position = 0;
		this.cashAmount = new BigDecimal(0);
	}

}