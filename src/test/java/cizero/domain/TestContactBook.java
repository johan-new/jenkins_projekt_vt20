package cizero.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Class could not be found.");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Class could not be found.");
		}
	}

	@Test
	void testAddContact() {
		try {
			try {
				cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
			} catch (ContactNotAddedException e) {
				e.printStackTrace();
				fail("Contact could not be added.");
			}
		} catch (SQLException e) {
			fail("Contact could not be added.");
		}
	}

	@Test
	void testRemoveContact() {
		try {
			try {
				cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
			} catch (ContactNotAddedException e) {
				e.printStackTrace();
				fail("Contact could not be added.");
			}

		} catch (SQLException e) {
			fail("Connection");
		}
		try {
			assertTrue(cb.removeContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com")));
		} catch (ContactNotRemovedException e) {
			fail("Contact could not be removed.");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("");
			e.printStackTrace();
		}
	}

}
