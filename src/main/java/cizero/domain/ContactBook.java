package cizero.domain;

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
	
	public boolean addContact(Contact contact) {
		contacts.add(contact);
		return db.addContact(contact);
	}
	
	public boolean addContact(ArrayList<Contact> contacts) {
		for(Contact contact : contacts) {
			this.contacts.add(contact);
		}
		return db.addContact(contacts);
	}
	
	public boolean removeContact(Contact contact) {
		contacts.remove(contact);
		return db.removeContact(contact);
	}
	
	public boolean removeContact(ArrayList<Contact> contacts) {
		for(Contact contact : contacts) {
			this.contacts.remove(contact);
		}
		return db.removeContact(contacts);
	}
	
}
