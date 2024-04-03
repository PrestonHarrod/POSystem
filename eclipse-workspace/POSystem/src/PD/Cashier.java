package PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Class that represents a cashier in the store
 */
public class Cashier {
	/**
	 * cashier number
	 */
	private String number;
	/**
	 * Person
	 */
	private Person person;
	/**
	 * session that the cashier is in
	 */
	private ArrayList<Session> sessions;
	/**
	 * cashier password
	 */
	private String password;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ArrayList<Session> getSessions() {
		return this.sessions;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Cashier constuctor
	 */
	public Cashier() {
		this.number = "";
		this.password = "";
		this.person = new Person();
		this.sessions = new ArrayList<Session>();
	}

	/**
	 * cashier constructor
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password) {
		this();
		this.number = number;
		this.person = person;
		this.password = password;
	}

	/**
	 * add a session to the cashier
	 * @param session
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	/**
	 * remove a session from the cashier
	 * @param session
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}

	/**
	 * check if the cashier is authorized
	 * @param password
	 */
	public Boolean isAuthorized(String password) {
		// TODO - implement Cashier.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * return string of class
	 */
	public String toString() {
		return person.getName();
	}

	public String getSalesForDate(LocalDate selectedDate) {
		BigDecimal total = new BigDecimal("0");
		for (Session session : this.getSessions())
		{
			if (session.getStartDateTime().toLocalDate().compareTo(selectedDate) == 0)
			{
				for (Sale s : session.getSales())
				{
					total = total.add(s.calcTotal());
				}
			}
		}
		return total.toString();
	}

	public String getSalesNum(LocalDate selectedDate) {
		BigDecimal total = new BigDecimal("0");
		for (Session session : this.getSessions())
		{
			if (session.getStartDateTime().toLocalDate().compareTo(selectedDate) == 0)
			{
				for (Sale s : session.getSales())
				{
					total = total.add(new BigDecimal(1));
				}
			}
		}
		return total.toString();
	}

}