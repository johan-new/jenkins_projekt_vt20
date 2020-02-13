package cizero.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import cizero.domain.ContactBook;

public class TestContact {

	Contact contact;
	
	@BeforeEach
	void reset() {
		contact = new Contact("Erik", "Sandstrom", "07300000", "erik.sandstrom@email.com");
	}
	
	@Test
	void TestEquals() {
		Contact contact1 = new Contact("Erik", "Sandstrom", "07300000", "erik.sandstrom@email.com");
		Contact contact2 = new Contact("Erik", "Sandstrom", "07300000", "erik.sandstrom@email.com");
		assertTrue(contact1.equals(contact2));
	}
	
	@Test
	void TestNotEquals() {
		Contact contact1 = new Contact("Erik", "Sandstrom", "07300000", "erik.sandstrom@email.com");
		Contact contact2 = new Contact("Erik", "Petterson", "07300001", "erik.petterson@email.com");
		assertFalse(contact1.equals(contact2));
	}
}
