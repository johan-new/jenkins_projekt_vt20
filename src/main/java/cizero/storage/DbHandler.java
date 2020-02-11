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


public class DbHandler {

	private static DbHandler instance;
	private static String dbURL;
	private Connection c;
	private Statement s;

	/*private*/ public DbHandler() {
		dbURL = "localhost:3306/dbContacts?allowPublicKeyRetrieval=true&password=Heroma11&useSSL=false&user=root&serverTimezone=UTC";
		//MysqlP4ssw0rd!1
		//establish connection

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://" + dbURL);
	        c.setCatalog("dbContacts");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	// Thread-safe
	public static synchronized DbHandler getInstance() {
		if (instance == null) {
			System.out.println("NEW");
			instance = new DbHandler();
		}
			return instance;
	}


	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	 public ArrayList<Contact> readDb(){
		ArrayList<Contact> returnContacts = new ArrayList<Contact>();

		ResultSet resultSet;

		  try {
			  System.out.println("readDB");
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

		 } catch (SQLException e){
			 e.printStackTrace();
			 return null;
		 }

		 return returnContacts;
	}


	public boolean addContact(Contact contact) {
		boolean isAdded = false;

		ResultSet resultSet;
		StringBuilder query = new StringBuilder("INSERT INTO tblContactbook VALUES (");

		query.append("'" 	+ contact.getFirstName() + "',"
									+ "'" + contact.getLastName() + "','"
									+ contact.getTeleNr() + "',"
									+ "'" + contact.getEmail() + "')");
		try {
			System.out.println("Adding contact...");
			s = c.createStatement();

			isAdded = s.executeUpdate(query.toString()) > 0;
			//if 0 rows were affected isAdded will be set to false ^

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return isAdded;
	}


	public boolean addContact(ArrayList<Contact> Contacts) {
		boolean areAdded = true;

		for (int i = 0; i < Contacts.size(); i++) {
			if (!addContact(Contacts.get(i)))
			{
				areAdded = false;
			}
		}

		return areAdded;
	}


	public boolean removeContact(Contact contact) {
		boolean isRemoved = false;

		ResultSet resultSet;
		StringBuilder query = new StringBuilder("DELETE FROM tblContactbook WHERE fldFName='"
												+ contact.getFirstName() + "' AND fldLName='"
												+ contact.getLastName() + "' AND fldTNr='"
												+ contact.getTeleNr() + "' AND fldEmail='"
												+ contact.getEmail() + "'");

		try {
			System.out.println("Removing contact...");
			s = c.createStatement();

			isRemoved = s.executeUpdate(query.toString()) > 0;
			//if 0 rows were affected isRemoved will be set to false ^
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return isRemoved;

	}

	public boolean removeContact(ArrayList<Contact> Contacts) {

		boolean areRemoved = true;

		for (int i = 0; i < Contacts.size(); i++) {
			if (!removeContact(Contacts.get(i)))
			{
				areRemoved = false;
			}
		}

		return areRemoved;
	}


}
