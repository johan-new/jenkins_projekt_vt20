package cizero.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cizero.storage.ContactNotAddedException;
import cizero.storage.ContactNotRemovedException;
import cizero.storage.DbHandler;

/**
 * <h1><i>ContactBook</i></h1>
 * <p>
 * Serves as a connection between DBHandler and the GUI. 
 * Includes the ArrayList contacts which stores contacts to
 * be accessed by the GUI.
 * 
 * @author Erik Sandstrom
 *
 */
public class ContactBook {
	private List<Contact> contacts = new ArrayList<>();
	private DbHandler db;

	/**
	 * <h1><i>Constructor.</i></h1>
	 * <p>
	 * @param password
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ContactBook(String password) throws ClassNotFoundException, SQLException {
		db = DbHandler.getInstance(password);
		readContactsFromDB();
	}

	/**
	 * <h1><i>getContacts</i></h1>
	 * <p>
	 * @return A list of contacts.
	 */
	public List<Contact> getContacts() {
		return contacts;
	}
	
	/**
	 * <h1><i>readContactsFromDB</i></h1>
	 * <p>
	 * Reads rows from the database via readDb() and adds them to contacts.
	 * @return a list of contacts
	 * @throws SQLException
	 */
	public List<Contact> readContactsFromDB() throws SQLException {
		contacts = db.readDb();
		return contacts;
	}

	/**
	 * <h1><i>addContact</i></h1>
	 * <p>
	 * Adds contacts to list and database.
	 * @param contact
	 * @throws SQLException
	 * @throws ContactNotAddedException
	 * @throws ContactIsEmptyException 
	 */
	public void addContact(Contact contact) throws SQLException, ContactNotAddedException, ContactIsEmptyException {
		if(contact.isEmpty()) {
			throw new ContactIsEmptyException("Kunde ej lägga till kontakt eftersom inga fält har fyllts i.");
		} else {
			if(alreadyExists(contact)) {
				throw new ContactNotAddedException("Kunde ej lägga till kontakt eftersom den redan finns.");
			} else {
				contacts.add(contact);
				db.addContact(contact);
			}
		}
	}
	
	/**
	 * <h1><i>alreadyExists</i></h1>
	 * <p>
	 * Checks wether a contact has already been added.
	 * @param contact
	 * @return
	 */
	public boolean alreadyExists(Contact contact) {
		for(Contact c : contacts) {
			if(c.equals(contact)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 	<h1><i>removeContact</i></h1>
	 * <p>
	 * Removes contact from list and database.
	 * @param contact
	 * @return A boolean.
	 * @throws ContactNotRemovedException
	 * @throws SQLException
	 * @throws ContactIsEmptyException 
	 */
	public boolean removeContact(Contact contact) throws ContactNotRemovedException, SQLException, ContactIsEmptyException {
		if(contact.isEmpty()) {
			throw new ContactIsEmptyException("Kunde ej ta bort kontakt eftersom inga fält har fyllts i.");
		} else {
			contacts.remove(contact);
			return db.removeContact(contact);
		}
	}

	/**
	 * <h1><i>removeAllContacts</i></h1>
	 * Clears the list of contacts, drops the database table and
	 * creates a new one.
	 * @return A boolean.
	 * @throws SQLException
	 */
	public boolean removeAllContacts() throws SQLException {
		contacts.clear();
		return db.dropDb();
	}
	
	/**
	 * <h1><i>closeConnection</i></h1>
	 * <p>
	 * Calls the closeConnection method in DBHandler.
	 */
	public void closeConnection() {
		try {
			db.closeConnection();
		} catch (SQLException e) {
			System.out.println("Gick inte att stänga connection.");
			e.printStackTrace();
		}
	}
	
	//Unused
	public boolean removeContact(ArrayList<Contact> contacts) throws ContactNotRemovedException, SQLException {
		for (Contact contact : contacts) {
			this.contacts.remove(contact);
		}
		return db.removeContact(contacts);
	}
	
	//Unused
	public void addContact(ArrayList<Contact> contacts) throws SQLException, ContactNotAddedException {
		for (Contact contact : contacts) {
			this.contacts.add(contact);
		}
		db.addContact(contacts);
	}

}
