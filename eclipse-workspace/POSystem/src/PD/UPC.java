package PD;

/**
 * Class that represents UPC code for an item
 */
public class UPC {
	/**
	 * uPC code
	 */
	private String uPC;
	/**
	 * item
	 */
	private Item item;

	public String getUPC() {
		return this.uPC;
	}

	public void setUPC(String uPC) {
		this.uPC = uPC;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public UPC() {
		this.uPC = "";
		this.item = new Item();
	}

	/**
	 * 
	 * @param upc
	 */
	public UPC(String upc) {
		this();
		this.uPC = upc;
	}

	public String toString() {
		return uPC;
	}

}