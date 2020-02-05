package storage;
import java.sql.*;
import java.util.ArrayList;

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
	
	private DbHandler() {
		dbURL = "localhost:3306/dbtest?allowPublicKeyRetrieval=true&password=my-secret-pw&useSSL=false&user=root";
		//establish connection
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        c = DriverManager.getConnection("jdbc:mysql://" + dbURL);
	        //Statement s = c.createStatement();
	        //s.execute("CREATE TABLE IF NOT EXISTS contactbook (fldFName VARCHAR(30), fldLName VARCHAR(30), fldTNr INT, fldEmail VARCHAR(20)");
	        //CREATE DATABASE x
	        // USE DATABASE x
	        //ALTER TABLE contactbook ADD PRIMARY KEY(fldFname,fldLName,fldTnr,fldEmail);
	        
		
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
	}

	
	// Thread-safe
	public static synchronized DbHandler getInstance() {
		if (instance == null) {
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

	}
	

}
