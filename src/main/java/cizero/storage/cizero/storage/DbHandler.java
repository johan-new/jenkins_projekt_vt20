package cizero.storage;

import java.sql.*;
import java.util.ArrayList;
import cizero.domain.*;

/* DbHandler - Handles database connectivity and translates
 * input/output
 * 
 * Singleton Pattern
 * 
 * */

public class DbHandler {
	
	private static DbHandler instance;
	private static String dbURL;
	private Connection c;
	private Statement s;
	
	/*private*/ public DbHandler() {
		dbURL = "localhost:3306/dbContacts?allowPublicKeyRetrieval=true&password=my-secret-pw&useSSL=false&user=root";
		//establish connection
		
		try {
			System.out.println("alfa");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("beta");
	        c = DriverManager.getConnection("jdbc:mysql://" + dbURL);
	        c.setCatalog("dbContacts");
	        System.out.println("gamma");
	        //Statement s = c.createStatement();
	        //s.execute("CREATE TABLE IF NOT EXISTS contactbook (fldFName VARCHAR(30), fldLName VARCHAR(30), fldTNr VARCHAR(20), fldEmail VARCHAR(20)");
	        //CREATE DATABASE x
	        // USE DATABASE x
	        //ALTER TABLE contactbook ADD PRIMARY KEY(fldFname,fldLName,fldTnr,fldEmail);
	        readDb();
	        System.out.println("delta");
		
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
		 
		ArrayList<String> fName = new ArrayList<String>();
		ArrayList<String> lName = new ArrayList<String>();
		ArrayList<String> tNr = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		 
		ResultSet resultSet;
		
		  try {
			  System.out.println("readDB");
			  s = c.createStatement();
			  s.executeQuery("SELECT * FROM tblContactbook");
			  resultSet = s.getResultSet();
			  
			  while(resultSet.next()) { //läser in värden från databasen
				  System.out.println(resultSet.getString("fldEmail"));
				  
				  fName.add(resultSet.getString("fldFName"));
				  lName.add(resultSet.getString("fldLName"));
				  tNr.add(resultSet.getString("fldTNr"));
				  email.add(resultSet.getString("fldEmail"));

			  }
			  
			  if (fName.size() == lName.size() && 
					  lName.size() == tNr.size() && 
					  tNr.size() == email.size()) {
				  //consistency check as above. Database do not permit null values
				  System.out.println("Constiency check");
				  
				  for (int i = 0; i < fName.size(); i++) { //bulding the arraylist with contacts 
					  returnContacts.add(new Contact(fName.get(i), lName.get(i), tNr.get(i), email.get(i)));
					  System.out.println("added contactobject " + i);
				  }
			  }
			  
			  
		     //System.out.println(resultSet.getString(0));
			//while (resultSet.next()){
			//	System.out.println(resultSet.getString("fldFName") + resultSet.getString("fldLName")
			//						+ resultSet.getString("fldTNr") + resultSet.getString("fldEmail") + "\n");

		 } catch (SQLException e){
			 System.out.println("Cacthar rad 71");
			 e.printStackTrace();
		 }


		 return returnContacts;
	}
	

}
