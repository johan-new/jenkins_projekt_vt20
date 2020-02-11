package cizero.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cizero.storage.DbHandler;

public class ContactBook {
	private List<Contact> contacts = new ArrayList<>();
	private DbHandler db;
	
	public ContactBook() {
		db = DbHandler.getInstance();
		readContactsFromDB();
	}
	
	public List<Contact> getContacts(){
		return contacts;
	}
	
	public List<Contact> readContactsFromDB() {
		contacts = db.readDb();
		return contacts;
	}
	
	public void addContact(Contact contact) throws SQLException{
		contacts.add(contact);
		db.addContact(contact);
	}
	
	public void addContact(ArrayList<Contact> contacts) throws SQLException{
		for(Contact contact : contacts) {
			this.contacts.add(contact);
		}
		db.addContact(contacts);
	}
	
	public boolean removeContact(Contact contact) {
		System.out.println(contact.hashCode());
		for(Contact c : contacts) {
			System.out.println(c.hashCode());
		}
		db.removeContact(contact);
		return contacts.remove(contact);
	}
	
	public boolean removeContact(ArrayList<Contact> contacts) {
		for(Contact contact : contacts) {
			this.contacts.remove(contact);
		}
		return db.removeContact(contacts);
	}
	
}
