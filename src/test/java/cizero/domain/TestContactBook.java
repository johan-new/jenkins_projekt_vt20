package cizero.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import cizero.domain.ContactBook;
import cizero.storage.ContactNotAddedException;
import cizero.storage.ContactNotRemovedException;

class TestContactBook {

	ContactBook cb;

	@BeforeEach
	void reset() {
		try {
			cb = new ContactBook("my-secret-pw");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail("ContactBook could not get instance of DBHandler.");
		}

	}

	@Test
	void testAddContact() {
		assertTrue(addContact());
		// removes contact from database before next test
		removeContact();
	}

	@Test
	void testRemoveContact() {
		addContact();
		assertTrue(removeContact());
	}

	boolean addContact() {
		try {
			cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
		} catch (SQLException | ContactNotAddedException | ContactIsEmptyException e) {
			e.printStackTrace();
			fail("Contact could not be added.");
		}
		return true;
	}

	boolean removeContact() {
		try {
			cb.removeContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
		} catch (ContactNotRemovedException | SQLException | ContactIsEmptyException e) {
			e.printStackTrace();
			fail("Contact could not be removed");
		}
		return true;
	}

}
