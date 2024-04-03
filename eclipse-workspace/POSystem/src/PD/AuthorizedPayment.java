package PD;

/**
 * Class that represents authorized forms of payment
 */
public abstract class AuthorizedPayment extends Payment{

	/**
	 * authorization code for payemnt
	 */
	private String authorizationCode;
	private boolean isCash;

	public String getAuthorizationCode() {
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	public void setIsCash(boolean a)
	{
		this.isCash = a;
	}

	/**
	 * check if payment is authorized
	 */
	public Boolean isAuthorized() {
		return true;
	}

	/**
	 * check if payment counts as cash
	 */
	public abstract Boolean countsAsCash();

}