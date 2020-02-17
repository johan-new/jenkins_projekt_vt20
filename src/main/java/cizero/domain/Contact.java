package cizero.domain;

/**
 * <h1><i>Contact</i></h1>
 * <p>
 * An object which stores personal data.
 * @author Erik Sandstrom
 *
 */
public class Contact {

  private String fName;
  private String lName;
  private String teleNr;
  private String email;
  
  /**
   * <h1><i>Constructor</i></h1>
   * <p>
   * @param fName
   * @param lName
   * @param teleNr
   * @param eMail
   */
  public Contact(String fName, String lName, String teleNr, String eMail) {
	  this.fName = fName;
	  this.lName = lName;
	  this.teleNr = teleNr;
	  this.email = eMail;
  }
  
  public String getFirstName() {
	  return fName;
  }
  
  public void setFirstName(String fName) {
	  this.fName = fName;
  }
  
  public String getLastName() {
	  return lName;
  }
  
  public void setLastName(String lName) {
	  this.lName = lName;
  }
  
  public String getTeleNr() {
	  return teleNr;
  }
  
  public void setTeleNr(String teleNr) {
	  this.teleNr = teleNr;
  }
  
  public String getEmail() {
	  return email;
  }
  
  public void setEmail(String email) {
	  this.email = email;
  }
  
  /**
   * <h1><i>toString</i></h1>
   * return A String of the contact's first and last name.
   */
  @Override
  public String toString() {
	  return fName + " " + lName;
  }
  
  /**
   * <h1><i>equals</i></h1>
   * Compares contacts.
   * @return true if the contacts values are the same, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
	Contact c = (Contact) o;
	//System.out.println(fName + " );
	//System.out.println(c.fName + " " + c.lName + " " + c.teleNr + " " + c.email);
	return (this.fName.equals(c.fName) && this.lName.equals(c.lName) && this.teleNr.equals(c.teleNr) && this.email.equals(c.email));  
  }
  
  /**
   * <h1><i>isEmpty</i></h1>
   * @return True if all variables are empty
   */
  public boolean isEmpty() {
	  return fName.isEmpty() && lName.isEmpty() && teleNr.isEmpty() && email.isEmpty();
  }
  
}
