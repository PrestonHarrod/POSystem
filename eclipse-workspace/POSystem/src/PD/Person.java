package PD;

/**
 * Class that represents a person in the store
 */
public class Person {
	/**
	 * persons name
	 */
	private String name;
	/**
	 * persons address
	 */
	private String address;
	/**
	 * persons city
	 */
	private String city;
	/**
	 * persons state of residence
	 */
	private String state;
	/**
	 * persons zip code
	 */
	private String zip;
	/**
	 * persons phone number
	 */
	private String phone;
	/**
	 * persons social security number
	 */
	private String sSN;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSSN() {
		return this.sSN;
	}

	public void setSSN(String sSN) {
		this.sSN = sSN;
	}

	/**
	 * Person constructor
	 */
	public Person() {
		this.address = "";
		this.city = "";
		this.name = "";
		this.phone = "";
		this.sSN = "";
		this.state = "";
		this.zip = "";
	}

	/**
	 * Person constructor
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param sSN
	 * @param cashier
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String sSN) {
		this();
		this.address = address;
		this.city = city;
		this.name = name;
		this.phone = phone;
		this.sSN = sSN;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * string of class
	 */
	public String toString() {
		// TODO - implement Person.toString
		throw new UnsupportedOperationException();
	}

}