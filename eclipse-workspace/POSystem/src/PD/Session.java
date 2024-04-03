package PD;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class that represents when the POS system is in use
 */
public class Session {

	private ArrayList<Sale> sales;
	/**
	 * cashier that is in the session
	 */
	private Cashier cashier;
	/**
	 * Register that is being used during the session
	 */
	private Register register;
	/**
	 * Start time of the session
	 */
	private LocalDateTime startDateTime;
	/**
	 * End time of the session
	 */
	//private LocalDateTime endDateTime;
	
	private BigDecimal startingCash;
	private BigDecimal endingCash;

	public Cashier getCashier() {
		return this.cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public Register getRegister() {
		return this.register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public ArrayList<Sale> getSales() {
		return this.sales;
	}

	public LocalDateTime getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public BigDecimal getStartingCash() {
		return startingCash;
	}

	public void setStartingCash(BigDecimal startingCash) {
		this.startingCash = startingCash;
	}
	
	public BigDecimal getEndingCash() {
		return endingCash;
	}

	public void setEndingCash(BigDecimal endingCash) {
		this.endingCash = endingCash;
	}
	
	public Session()
	{
		this.cashier = new Cashier();
		this.register = new Register();
		this.sales = new ArrayList<Sale>();
		this.startDateTime = LocalDateTime.now();
	}

	/**
	 * Session constructor
	 * @param cashier
	 * @param register
	 */
	public Session(Cashier cashier, Register register) {
		this();
		this.cashier = cashier;
		this.register = register;
	}

	/**
	 * Add a sale to the session
	 * @param sale
	 */
	public void addSale(Sale sale) {
		sales.add(sale);
	}

	/**
	 * Remove a sale from the session
	 * @param sale
	 */
	public void removeSale(Sale sale) {
		sales.remove(sale);
	}

	/**
	 * Calculate the cash differential
	 * @param cash
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash) {
		BigDecimal x = new BigDecimal("0");
		x = x.add(this.getRegister().getCashDrawer().getCashAmount());
		for (Sale s : this.getSales())
		{
			for (Payment p : s.getPayments())
			{
				if (p instanceof Cash || p instanceof Check)
				{
					x = x.add(p.getAmount());
				}
			}
		}
		x = x.subtract(cash);
		return x;
	}

	/**
	 * Return string of class
	 */
	public String toString() {
		String s = "";
		BigDecimal result = new BigDecimal("0");
		
		for (Sale sale : this.getSales())
		{
			result = result.add(sale.getTotalPayments());
		}
		s = result.toString();
		return "Session: Cashier: " + cashier.toString() + " Register: " + register.getNumber() + " Date: " + this.startDateTime.toString() + " Total: " + s + "\n";
	}



}