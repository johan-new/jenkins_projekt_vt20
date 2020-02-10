package cizero.domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import cizero.domain.Contact;
import cizero.storage.DbHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestContactBook {
	
	ContactBook cb;

	@BeforeEach
	void reset() {
		cb = new ContactBook();
	}
	
	@Test
	void testAddContact() {
		assertTrue(cb.addContact(new Contact("fName", "lName", "07300000", "email")));
		cb.removeContact(new Contact("fName", "lName", "07300000", "email"));
	}
	
	@Test
	void testRemoveContact() {
		cb.addContact(new Contact("fName", "lName", "07300000", "email"));
		assertTrue(cb.removeContact(new Contact("fName", "lName", "07300000", "email")));
		assertFalse(cb.removeContact(new Contact("fName", "lName", "07300000", "email")));
	}
	
}
