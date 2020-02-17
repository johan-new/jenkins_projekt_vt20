package cizero.ui;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import cizero.domain.*;
import java.util.*;
import java.sql.SQLException;

public class TestGUI {

  private GUI gui;
  private Contact contact;


  // @BeforeEach
  //   public void createGuiAndCleanDatabase() throws SQLException, ClassNotFoundException{
  //     gui = new GUI("my-secret-password");
  //     gui.removeAllContactsHidden();
  //     gui = new GUI("my-secret-password");
  //   }


  // @Test
  // public void testIfAddBtnAddsContactToContacts(){
  //   gui.getFNameField().setText("P");
  //   gui.getLNameField().setText("S");
  //   gui.getPhoneField().setText("2");
  //   gui.getMailField().setText("p@s");
  //   gui.getAddBtn().doClick();
  //   System.out.println(gui.getContacts().size());
  //   assertTrue(gui.getContacts().size() == 1);
  //
  // }






  // @Test
  // public void testIfAddBtnCreatesContactWithCorrectInformationFromTextFields(){
  //   contact = new Contact("Pelle", "Svanslös", "234", "pelle@gmail.com");
  //   gui.getFNameField().setText("Pelle");
  //   gui.getLNameField().setText("Svanslös");
  //   gui.getPhoneField().setText("234");
  //   gui.getMailField().setText("pelle@gmail.com");
  //   gui.getAddBtn().doClick();
  //   Contact contact2 = gui.getContacts().get(0);
  //   assertEquals(contact, contact2);
  // }

  // @Test
  // public void testSearchBtnFirstName(){
  //   gui.getFNameField().setText("Pelle");
  //   gui.getLNameField().setText("Svanslös");
  //   gui.getPhoneField().setText("234");
  //   gui.getMailField().setText("pelle@gmail.com");
  //   gui.getAddBtn().doClick();
  //   gui.clearForm();
  //   gui.getFNameField().setText("Pelle");
  //   gui.getSearchBtn().doClick();
  //   Scanner sc = new Scanner(gui.getTextArea().getText());
  //   sc.nextLine();
  //   assertEquals(sc.nextLine(), "Pelle Svanslös");
  //   sc.close();
  //
  // }
  //
  // @Test
  // public void testSearchBtnFirstNameIgnoresCase(){
  //   gui.getFNameField().setText("Pelle");
  //   gui.getLNameField().setText("Svanslös");
  //   gui.getPhoneField().setText("234");
  //   gui.getMailField().setText("pelle@gmail.com");
  //   gui.getAddBtn().doClick();
  //   gui.clearForm();
  //   gui.getFNameField().setText("pElLe");
  //   gui.getSearchBtn().doClick();
  //   Scanner sc = new Scanner(gui.getTextArea().getText());
  //   sc.nextLine();
  //   assertEquals(sc.nextLine(), "Pelle Svanslös");
  //   sc.close();
  //
  // }
  //
  // @Test
  // public void testClearMenuItemClearsTextArea(){
  //   gui.getTextArea().setText("text in textarea");
  //   gui.getClearMenuItem().doClick();
  //   assertEquals(gui.getTextArea().getText(), "");
  // }









}
