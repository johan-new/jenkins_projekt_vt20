package cizero.storage;

import java.sql.*;
import java.util.ArrayList;
import cizero.domain.*;


/**
* <h1>DBHandler</h1>
* DbHandler - Handles database connectivity and translates
* input/output / CRUD
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

//TODO
//support for no db/tbl
//mysql passw in constructor


public class DbHandler {

	private static DbHandler instance;
	private static String dbURL;
	private Connection c;
	private Statement s;
	
	public DbHandler() throws SQLException, ClassNotFoundException{
		this("my-secret-pw");
	}
	
	/*private*/ public DbHandler(String dbPassword) throws SQLException, ClassNotFoundException{
		
		dbURL = "localhost:3306/dbContacts?allowPublicKeyRetrieval=true&password="
				+ dbPassword + "&useSSL=false&user=root&serverTimezone=UTC";
		
		//MysqlP4ssw0rd!1

		//establish connection

		Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://" + dbURL);
        //CREATE DATABASE IF NOT EXISTS dbContacts
        c.setCatalog("dbContacts");

        //CREATE TABLE IF NOT EXISTS tblContactbook (....);



	}
	


	// Thread-safe
	public static synchronized DbHandler getInstance()  throws SQLException, ClassNotFoundException{
		if (instance == null) {
			System.out.println("NEW");
			instance = new DbHandler("my-secret-pw");
			}
		
		return instance;
	}


	public void closeConnection()  throws SQLException{
			c.close();
	}


	 public ArrayList<Contact> readDb() throws SQLException{
		 
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


	public boolean addContact(Contact contact) throws ContactNotAddedException, SQLException{
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


	public boolean removeContact(Contact contact) throws ContactNotRemovedException, SQLException{
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
