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
			cb = new ContactBook("MysqlP4ssw0rd!1");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			cb.removeContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
		} catch (ContactNotRemovedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testAddContact() {
		try {
			try {
				cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
			} catch (ContactNotAddedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			fail("hej");
		}
	}

	@Test
	void testRemoveContact() {
		try {
			try {
				cb.addContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com"));
			} catch (ContactNotAddedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			fail("Could not add contact");
		}
		try {
			assertTrue(cb.removeContact(new Contact("Emil", "Rosén", "07300000", "emil.rosen@out.com")));
		} catch (ContactNotRemovedException e) {
			fail("Contact could not be removed or does not exist");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("fuck");
			e.printStackTrace();
		}
	}

}
