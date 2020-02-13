package cizero.storage;

import java.sql.*;
import java.util.ArrayList;
import cizero.domain.*;


/**
* <h1>DBHandler</h1>
* DbHandler - Handles database connectivity, input/output and CRUD.
*
* Uses Singelton pattern to avoid several connections.
*
* The add/remove methods return as boolean value
* as an indicator if the query has succeeded.
*
* @author  Johan Nyberg
* @version 1.0
* @since   2019-02-05
*/


public class DbHandler {

	private static DbHandler instance;
	private static String dbURL;
	private Connection c;
	private Statement s;

	/**
	 * Uses a deault password for running tests
	 *
	 */

	private DbHandler() throws SQLException, ClassNotFoundException{
		this("my-secret-pw");
	}

	/**
	 * Constructor. Uses a default password for running tests
	 * private due to Singelton pattern
	 *
	 * @param dbPassword password to database, clear text
	 */
	
	private DbHandler(String dbPassword) throws SQLException, ClassNotFoundException{

		dbURL = "localhost:3306/dbContacts?allowPublicKeyRetrieval=true&password="
				+ dbPassword + "&useSSL=false&user=root&serverTimezone=UTC";

		//establish connection
		Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://" + dbURL);

		initilizeDB();

	}

	/**
	 * Thread safe Singelton Pattern
	 *
	 * @param passw password to database, clear text
	 */
	public static synchronized DbHandler getInstance(String passw)  throws SQLException, ClassNotFoundException{
		if (instance == null) {
			System.out.println("NEW");
			instance = new DbHandler(passw);
			}
		
		return instance;
	}

	public static synchronized DbHandler getInstance()  throws SQLException, ClassNotFoundException{
		return getInstance("my-secret-pw");
	}

	/**
	 *	Ensures the ability to add/remove contacts even if the database is
	 *	tampered with during runtime
	 **/

	public void initilizeDB() throws SQLException {
		s = c.createStatement();
		s.executeUpdate("CREATE DATABASE IF NOT EXISTS dbContacts");
		s.executeUpdate("CREATE TABLE IF NOT EXISTS tblContactbook " +
				"(fldFName varchar(30) NOT NULL,fldLName varchar(30) NOT NULL," +
				" fldTNr varchar(20) NOT NULL, fldEmail varchar(20) NOT NULL, " +
				"PRIMARY KEY (fldFName,fldLName,fldTNr,fldEmail))");

		c.setCatalog("dbContacts");
	}


	/**
	* Closes Connection and Statement
	*/

	public void closeConnection()  throws SQLException{
			s.close();
			c.close();
	}


	/**
	 * Returns database as an ArrayList of Contact-objects
	 */
	 public ArrayList<Contact> readDb() throws SQLException{

		initilizeDB();
		 
		ArrayList<Contact> returnContacts = new ArrayList<Contact>();

		ResultSet resultSet;

		  System.out.println("Reading database....");
		  s = c.createStatement();
		  s.executeQuery("SELECT * FROM tblContactbook");
		  resultSet = s.getResultSet();

		  while(resultSet.next()) { //läser in värden från databasen
			  returnContacts.add(new Contact(
					  				resultSet.getString("fldFName"),
					  				resultSet.getString("fldLName"),
					  				resultSet.getString("fldTNr"),
					  				resultSet.getString("fldEmail")));
		  }
			
		 return returnContacts;
	}

	/**
	 * Deletes table
	*/

	public boolean dropDb() throws SQLException{
		c.setCatalog("dbContacts");
		Statement st = c.createStatement();
		st.executeUpdate("DROP TABLE IF EXISTS tblContactbook");

		return true; //will never reach here if exceptions is thrown as above
	}

	/**
	 * Adds a contact to the database
	 */

	public boolean addContact(Contact contact) throws ContactNotAddedException, SQLException{
		initilizeDB();

		boolean isAdded = false;

		ResultSet resultSet;
		StringBuilder query = new StringBuilder("INSERT INTO tblContactbook VALUES (");

		query.append("'" 	+ contact.getFirstName() + "',"
									+ "'" + contact.getLastName() + "','"
									+ contact.getTeleNr() + "',"
									+ "'" + contact.getEmail() + "')");

		System.out.println("Adding contact...");
		s = c.createStatement();

		isAdded = s.executeUpdate(query.toString()) > 0;
		//if 0 rows were affected isAdded will be set to false ^
		
		if (!isAdded) {
			throw new ContactNotAddedException("Kund ej lägga till kontakt");
		}
		
		return isAdded;
	}


	public boolean addContact(ArrayList<Contact> Contacts) throws ContactNotAddedException, SQLException{
		boolean areAdded = true;

		for (int i = 0; i < Contacts.size(); i++) {
			if (!addContact(Contacts.get(i)))
			{
				areAdded = false;
			}
		}
		
		if (!areAdded) {
			throw new ContactNotAddedException("Kund ej lägga till kontakt");
		}

		return areAdded;
	}

	/**
	 * Removes a contact
	 */

	public boolean removeContact(Contact contact) throws ContactNotRemovedException, SQLException{
		initilizeDB();

		boolean isRemoved = false;

		ResultSet resultSet;
		StringBuilder query = new StringBuilder("DELETE FROM tblContactbook WHERE fldFName='"
												+ contact.getFirstName() + "' AND fldLName='"
												+ contact.getLastName() + "' AND fldTNr='"
												+ contact.getTeleNr() + "' AND fldEmail='"
												+ contact.getEmail() + "'");

		System.out.println("Removing contact...");
		s = c.createStatement();

		isRemoved = s.executeUpdate(query.toString()) > 0;
		//if 0 rows were affected isRemoved will be set to false ^

		if (!isRemoved) {
			throw new ContactNotRemovedException("Kund ej ta bort kontakt");
		}
		
		return isRemoved;
	}

	public boolean removeContact(ArrayList<Contact> Contacts)  throws ContactNotRemovedException, SQLException{

		boolean areRemoved = true;

		for (int i = 0; i < Contacts.size(); i++) {
			if (!removeContact(Contacts.get(i)))
			{
				areRemoved = false;
			}
		}
		
		if (!areRemoved) {
			throw new ContactNotRemovedException("Kund ej ta bort kontakt");
		}

		return areRemoved;
	}


}
