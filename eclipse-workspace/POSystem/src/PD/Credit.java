package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents credit as payment
 */
public class Credit extends AuthorizedPayment{

	/**
	 * card type
	 */
	private String cardType;
	/**
	 * account number
	 */
	private String acctNumber;
	/**
	 * expiration date of card
	 */
	private LocalDate expireDate;

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAcctNumber() {
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public LocalDate getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public Credit() {
		this.cardType = "";
		this.acctNumber = "";
		this.expireDate = LocalDate.now();
		super.setAmount(new BigDecimal(0));
		super.setAmtTendered(new BigDecimal(0));
		super.setAuthorizationCode("");
		super.setIsCash(false);
	}

	/**
	 * 
	 * @param cardType
	 * @param acctNumber
	 * @param expireDate
	 */
	public Credit(String cardType, String acctNumber, String expireDate, BigDecimal amount) {
		this();
		this.cardType = cardType;
		this.acctNumber = acctNumber;
		this.expireDate = LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("MM/d/yyyy"));
		super.setAmount(amount);
	}

	/**
	 * check if user is authorized
	 */
	public Boolean isAuthorized() {
		return true;
	}
	
	public Boolean countsAsCash()
	{
		return false;
	}

	/**
	 * return string of class
	 */
	public String toString() {
		// TODO - implement Credit.toString
		throw new UnsupportedOperationException();
	}

}