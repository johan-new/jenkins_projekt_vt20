package cizero.domain;

import java.util.ArrayList;
import java.util.List;
import cizero.storage.DbHandler;

public class ContactBook {
	private List<Contact> contacts = new ArrayList<>();
	private DbHandler db;
	
	public ContactBook() {
		db = DbHandler.getInstance();
	}
	
	public void addContact(String fName, String lName, String teleNr, String email) {
		contacts.add(new Contact(fName, lName, teleNr, email));
	}
	
	public void update() {
		
	}
}
