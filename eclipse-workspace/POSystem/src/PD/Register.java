package PD;

/**
 * Class that represents a register at the store
 */
public class Register {

	/**
	 * Register number
	 */
	private String number;
	/**
	 * cash drawer of the register
	 */
	private CashDrawer cashDrawer;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public CashDrawer getCashDrawer() {
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	/**
	 * Register constuctor
	 */
	public Register() {
		this.number = "";
		this.cashDrawer = new CashDrawer();
	}

	/**
	 * Register constructor
	 * @param number
	 */
	public Register(String number) {
		this();
		this.number = number;
	}

	/**
	 * Return string of class
	 */
	public String toString() {
		return this.getNumber();
	}

}