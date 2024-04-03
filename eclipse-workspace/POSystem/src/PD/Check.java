package PD;

import java.math.BigDecimal;

/**
 * Class that represents a check
 */
public class Check extends AuthorizedPayment{

	/**
	 * Routing number of check
	 */
	private String routingNumber;
	/**
	 * account number of check
	 */
	private String accountNumber;
	/**
	 * check number
	 */
	private String checkNumber;

	public String getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Check() {
		this.accountNumber = "";
		this.checkNumber = "";
		this.routingNumber = "";
		super.setAmount(new BigDecimal(0));
		super.setAmtTendered(new BigDecimal(0));
		super.setAuthorizationCode("");
		super.setIsCash(true);
	}

	/**
	 * 
	 * @param amount
	 * @param accountNumber
	 */
	public Check(String amount, String accountNumber, String checkNumber, String routingNumber) {
		this();
		BigDecimal x = new BigDecimal(amount);
		super.setAmount(x);
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
		this.routingNumber = routingNumber;
	}

	/**
	 * check if payment is authorized
	 */
	public Boolean isAuthorized() {
		return true;
	}
	
	public Boolean countsAsCash()
	{
		return true;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		// TODO - implement Check.toString
		throw new UnsupportedOperationException();
	}

}