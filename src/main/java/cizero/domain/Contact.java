package cizero.domain;

public class Contact {

  private String fName;
  private String lName;
  private String teleNr;
  private String email;
  
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
}
