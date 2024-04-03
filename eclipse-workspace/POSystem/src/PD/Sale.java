package PD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class that represents a sale in the store
 */
public class Sale {
	/**
	 * list of payments
	 */
	private ArrayList<Payment> payments;
	/**
	 * list of saleLineItems
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * time of the sale
	 */
	private LocalDateTime dateTime;
	/**
	 * if the sale has tax or not
	 */
	private Boolean taxFree;

	public ArrayList<Payment> getPayments() {
		return this.payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}

	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree() {
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree) {
		this.taxFree = taxFree;
	}

	/**
	 * Sale constructor
	 */
	public Sale() {
		this.taxFree = null;
		this.payments = new ArrayList<Payment>();
		this.saleLineItems = new ArrayList<SaleLineItem>();
		this.dateTime = LocalDateTime.now();
	}

	/**
	 * Sale constructor
	 * @param taxFreeStr
	 */
	public Sale(String taxFreeStr) {
		this();
		if (taxFreeStr.toLowerCase().equals("y"))
		{
			taxFree = true;
		}
		else
		{
			taxFree = false;
		}
	}

	/**
	 * Add a payment to the sale
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	/**
	 * Remove a payment from the sale
	 * @param payment
	 */
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}

	/**
	 * Add a SaleLineItem to the store
	 * @param sli
	 */
	public void addSaleLineItem(SaleLineItem sli) {
		saleLineItems.add(sli);
	}

	/**
	 * Remove a salelineitem from the store
	 * @param sli
	 */
	public void removeSaleLineItem(SaleLineItem sli) {
		saleLineItems.remove(sli);
	}

	/**
	 * calculate the total
	 */
	public BigDecimal calcTotal() {
		BigDecimal result = new BigDecimal("0");
		// BigDecimal x = new BigDecimal("0");
		result = result.add(this.calcSubTotal());
		result = result.add(this.calcTax());
		return result.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * calculate sub total
	 */
	public BigDecimal calcSubTotal() {
		BigDecimal x = new BigDecimal("0");
		for (SaleLineItem s : saleLineItems)
		{
			x = x.add(s.calcSubTotal());
		}
		return x.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * calculate tax on the sale
	 */
	public BigDecimal calcTax() {
		BigDecimal x = new BigDecimal("0.00");
		// BigDecimal y = new BigDecimal("0.00");
		if (this.getTaxFree() == true)
		{
			return x;
		}
		else 
		{
			for (SaleLineItem sli : this.getSaleLineItems())
			{
				x = x.add(sli.calcTax());
				break;
			}
		}
		
		return x.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * get the total amount of payments
	 */
	public BigDecimal getTotalPayments() {
		BigDecimal result = new BigDecimal("0");
		for (Payment p : this.getPayments())
		{
			result = result.add(p.getAmount());
		}
		return result.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * checks if payment is enough money
	 */
	public Boolean isPaymentEnough() {
		boolean result = false;
		if (this.getTotalPayments().compareTo(this.calcTotal()) >= 0)
		{
			result = true;
		}
		return result;
	}

	/**
	 * calculate amount
	 * @param amtTendered
	 */
	public BigDecimal calcAmount(BigDecimal amtTendered) {
		BigDecimal result = new BigDecimal("0");
		result = this.getTotalPayments();
		result = result.add(amtTendered);
		if (result.compareTo(this.calcTotal()) <= 0)
		{
			result = amtTendered;
		}
		else
		{
			result = calcTotal().subtract(getTotalPayments());
		}
		return result.setScale(2, RoundingMode.HALF_UP);
		
	}

	/**
	 * calculate amount charged
	 */
	public BigDecimal calcChange() 
	{
		BigDecimal a = new BigDecimal("0");
		
		a = this.calcAmtTendered().subtract(this.calcTotal());
		/*
		for (Payment p : this.getPayments())
		{
			if (p.getAmtTendered().equals(new BigDecimal("0")))
			{
				break;
			}
			else 
			{
				a = a.add(p.getAmtTendered().subtract(p.getAmount()));
			}
		}
		*/
		return a.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * calculate amount tendered
	 */
	public BigDecimal calcAmtTendered() {
		BigDecimal result = new BigDecimal("0");
		for (Payment p : this.getPayments())
		{
			result = result.add(p.getAmtTendered());
		}
		return result.setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * return string of class
	 */
	public String toString() {
		return "SubTotal: " + this.calcSubTotal() + " Tax: " + this.calcTax().toString() + " Total: " + this.calcTotal() + " Payment: " + this.getTotalPayments() + " Change: " + this.calcChange();
	}

}