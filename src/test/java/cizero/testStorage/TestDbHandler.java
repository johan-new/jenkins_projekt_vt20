import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import cizero.domain.Contact;

import org.junit.jupiter.api.Test;

import cizero.storage.DbHandler;

class TestDbHandler {
	
	//TODO
	// Test singleton-pattern?
	// I prep / clean up the tests to achieve loose coupling (instead of controlling order of execution)

	DbHandler dbh = new DbHandler();

	
	@Test
	void testReadDB() {
		System.out.println("testReadDB");
		assertNotNull(dbh.readDb());
	}
	
	@Test
	void testAddContact() {
		assertTrue(dbh.addContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));
		
		//clean-up
		dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de"));
	}
	
	@Test
	void testRemoveContact() {
		//prep for test
		dbh.addContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de"));
		
		//first time removal should suceed
		assertTrue(dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));
		//but not the second
		assertFalse(dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));

	}
	
	@Test 
	void testAddManyContacts(){

		assertTrue(addManyContacts());
		
		//clean-up
		removeManyContacts();
	}
	
	@Test
	void testRemoveManyContacts(){
		//prep test
		addManyContacts();
		
		assertTrue(removeManyContacts());
		
	}
	
	boolean addManyContacts() {
		ArrayList<Contact> Contacts = new ArrayList<Contact>();
		Contacts.add(new Contact("Johann Sebastian","Bach","070345345","sebbe_cool@mail.com"));
		Contacts.add(new Contact("Nine","Simone","070335345","jazz-nina@mail.com"));
		return dbh.addContact(Contacts);
	}
	
	boolean removeManyContacts() {
		ArrayList<Contact> Contacts = new ArrayList<Contact>();
		Contacts.add(new Contact("Johann Sebastian","Bach","070345345","sebbe_cool@mail.com"));
		Contacts.add(new Contact("Nine","Simone","070335345","jazz-nina@mail.com"));
		return dbh.removeContact(Contacts);
	}

}
