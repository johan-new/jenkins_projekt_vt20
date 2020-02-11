package cizero.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import cizero.domain.ContactBook;

class TestContactBook {

	ContactBook cb;

	@BeforeEach
	void reset() {
		cb = new ContactBook();
	}

	@Test
	void testAddContact() {
		try {
			cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
		} catch (SQLException e) {
			fail("hej");
		}
	}

	@Test
	void testRemoveContact() {
		try {
			cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));

		} catch (SQLException e) {
			fail("Could not add contact");
		}
		assertTrue(cb.removeContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com")));
	}

}
