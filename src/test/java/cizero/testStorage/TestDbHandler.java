package cizero.testStorage;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import cizero.domain.Contact;

import cizero.storage.ContactNotAddedException;
import cizero.storage.ContactNotRemovedException;
import org.junit.jupiter.api.Test;

import cizero.storage.DbHandler;

class TestDbHandler {
	
	//TODO
	// Test singleton-pattern?
	// I prep / clean up the tests to achieve loose coupling (instead of controlling order of execution)

	DbHandler dbh;

	{
		try {
			dbh = new DbHandler("my-secret-pw");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}


	@Test
	void testReadDB() {
		System.out.println("testReadDB");
		try {
			assertNotNull(dbh.readDb());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddContact() {
		try {
			assertTrue(dbh.addContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));
			//clean-up
			dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de"));
		} catch (ContactNotAddedException | SQLException | ContactNotRemovedException e) {
			e.printStackTrace();
		}


	}
	
	@Test
	void testRemoveContact() {
		try {
			//prep for test
			dbh.addContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de"));

			//first time removal should suceed
			assertTrue(dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));
			//but not the second
			assertFalse(dbh.removeContact(new Contact("Hans","Gretchen","07000001", "hans@mail.de")));

		} catch (ContactNotAddedException | SQLException | ContactNotRemovedException e) {
			e.printStackTrace();
		}
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
		try {
			return dbh.addContact(Contacts);
		} catch (ContactNotAddedException | SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	boolean removeManyContacts() {
		ArrayList<Contact> Contacts = new ArrayList<Contact>();
		Contacts.add(new Contact("Johann Sebastian","Bach","070345345","sebbe_cool@mail.com"));
		Contacts.add(new Contact("Nine","Simone","070335345","jazz-nina@mail.com"));
		try {
			return dbh.removeContact(Contacts);
		} catch (ContactNotRemovedException | SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
