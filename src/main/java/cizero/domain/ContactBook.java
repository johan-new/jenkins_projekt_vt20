package cizero.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cizero.storage.ContactNotAddedException;
import cizero.storage.ContactNotRemovedException;
import cizero.storage.DbHandler;

public class ContactBook {
	private List<Contact> contacts = new ArrayList<>();
	private DbHandler db;

	public ContactBook() throws ClassNotFoundException, SQLException {
		db = DbHandler.getInstance();
		readContactsFromDB();
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Contact> readContactsFromDB() throws SQLException {
		contacts = db.readDb();
		return contacts;
	}

	public void addContact(Contact contact) throws SQLException, ContactNotAddedException {
		contacts.add(contact);
		db.addContact(contact);
	}

	public void addContact(ArrayList<Contact> contacts) throws SQLException, ContactNotAddedException {
		for (Contact contact : contacts) {
			this.contacts.add(contact);
		}
		db.addContact(contacts);
	}

	public boolean removeContact(Contact contact) throws ContactNotRemovedException, SQLException {
//		System.out.println(contact.hashCode());
//		for(Contact c : contacts) {
//			System.out.println(c.hashCode());
//		}
		contacts.remove(contact);
		return db.removeContact(contact);
	}

	public boolean removeContact(ArrayList<Contact> contacts) throws ContactNotRemovedException, SQLException {
		for (Contact contact : contacts) {
			this.contacts.remove(contact);
		}
		return db.removeContact(contacts);
	}

}
