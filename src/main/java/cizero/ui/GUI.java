package cizero.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import cizero.domain.Contact;
import cizero.domain.ContactBook;
import cizero.storage.ContactNotAddedException;
import cizero.storage.ContactNotRemovedException;

public class GUI extends JFrame {

  private ContactBook contactBook;
  private ArrayList<Contact> tempContacts;
  private List<Contact> contacts = new ArrayList<>();

  private JTextField fNameField = new JTextField(10);
  private JTextField lNameField = new JTextField(10);
  private JTextField phoneField = new JTextField(10);
  private JTextField mailField = new JTextField(10);
  private JLabel fNameLabel = new JLabel("Förnamn");
  private JLabel lNameLabel = new JLabel("Efternamn");
  private JLabel phoneLabel = new JLabel("telenummer");
  private JLabel mailLabel = new JLabel("mail");

  private JButton addBtn = new JButton("Ny kontakt");
  private JButton searchBtn = new JButton("Sök kontakt");
  private JButton removeBtn = new JButton("Radera");
  private JButton showBtn = new JButton("Visa alla");

  private JPanel textPanel = new JPanel();
  private JTextArea textArea = new JTextArea();

  private JPanel formPanel = new JPanel();

  JMenuBar menuBar;
  JMenu fileMenu;
  JMenuItem darkMode;
  JMenuItem lightMode;
  JMenuItem pinkMode;
  JMenuItem avsluta;
  JMenuItem clear;
  JMenuItem randomMode;

  Color background1;
  Color background2;
  Color foreground1;
  Color foreground2;


  public GUI(String password){

	 try {
		contactBook = new ContactBook(password);
	} catch (ClassNotFoundException e2) {
		textArea.setText("Lyckades inte upprätta koppling med databas");
	} catch (SQLException e2) {
		textArea.setText("Lyckades inte upprätta koppling till databas");
	}


   try{
	 contacts = contactBook.getContacts();
 } catch(Exception e){
   contacts = new ArrayList<>();
   textArea.setText("Lyckades inte upprätta koppling till databasen");
 }

    setLayout(new BorderLayout());

    textPanel.setLayout(new BorderLayout());
    textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

    add(textPanel, BorderLayout.CENTER);
    add(formPanel, BorderLayout.WEST);


    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    darkMode = new JMenuItem("Dark mode");
    lightMode = new JMenuItem("Standard mode");
    pinkMode = new JMenuItem("Pink mode");
    clear = new JMenuItem("Clear");
    avsluta = new JMenuItem("Avsluta");
    randomMode = new JMenuItem("Random mode");
    fileMenu.add(darkMode);
    fileMenu.add(lightMode);
    fileMenu.add(pinkMode);
    fileMenu.add(randomMode);
    fileMenu.add(clear);
    fileMenu.add(avsluta);
    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    randomMode.addActionListener(e->{
      randomMode();
    });

    clear.addActionListener(e->{
      textArea.setText("");
    });

    avsluta.addActionListener(e->{
      System.exit(0);
    });

    pinkMode.addActionListener(e -> {
      pinkMode();
    });

    darkMode.addActionListener(e -> {
      darkMode();
    });

    searchBtn.addActionListener(e -> {
      findContact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText());
      clearForm();
    });

    lightMode.addActionListener(e -> {
      defaultMode();
    });

    addBtn.addActionListener(e -> {
          try {
    		contactBook.addContact(new Contact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText()));
    	} catch (SQLException e1) {
      //  textArea.setText("SQLException");
    		textArea.setText("Kontakten finns redan i kontaktboken");
    	} catch (ContactNotAddedException e1) {
      //  textArea.setText("ContactNotAddedException");
    		textArea.setText("Kontakten finns redan i kontaktboken");
    	}
        clearForm();
      });

    removeBtn.addActionListener(e -> {
      boolean isRemoved;
    	try {
    		isRemoved = contactBook.removeContact(new Contact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText()));
        textArea.setText("Kontakten borttagen");
    	} catch (ContactNotRemovedException e1) {
        textArea.setText("Det gick inte att ta bort kontakten");
    		isRemoved = false;
    	} catch (SQLException e1) {
    		isRemoved = false;
    		textArea.setText("Det gick inte att ta bort kontakten");
    	}

      clearForm();
    });

    showBtn.addActionListener(e -> {
      String contactText = "";
      for(Contact contact: contacts){
        contactText += "==============================\n" + contact.getFirstName() + " " +
        contact.getLastName() + "\nTelenr: " + contact.getTeleNr() + "\nMail: " + contact.getEmail() + "\n\n";
        textArea.setText(contactText);
      }
    });


    setSize(600, 400);
    setLayout();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //darkMode();


  }


  public void clearForm(){
    fNameField.setText(""); lNameField.setText(""); phoneField.setText(""); mailField.setText("");
  }


  public void findContact(String firstName, String lastName, String telenr, String mail){
    tempContacts = new ArrayList<>();
    for(Contact contact : contacts){
      if(contact.getFirstName().equalsIgnoreCase(firstName) || contact.getLastName().equalsIgnoreCase(lastName) ||
        contact.getTeleNr().equalsIgnoreCase(telenr) || contact.getEmail().equalsIgnoreCase(mail)){
        tempContacts.add(contact);
      }
    }
    String contactText = "";
    for(Contact contact: tempContacts){
      contactText += "==============================\n" + contact.getFirstName() + " " +
      contact.getLastName() + "\nTelenr: " + contact.getTeleNr() + "\nMail: " + contact.getEmail() + "\n\n";
    }
    if(tempContacts.size() > 0){
    textArea.setText(contactText);
  }
    else{
      textArea.setText("Kontakten hittades inte");
    }
  }


  public JTextArea getTextArea(){
    return textArea;
  }

  public JButton getSearchBtn(){
    return searchBtn;
  }

  public JButton getAddBtn(){
    return addBtn;
  }

  public List<Contact> getContacts(){
    return contacts;
  }

  public JTextField getFNameField(){
    return fNameField;
  }

  public JTextField getLNameField(){
    return lNameField;
  }

  public JTextField getPhoneField(){
    return phoneField;
  }

  public JTextField getMailField(){
    return mailField;
  }



  public void darkMode(){
    background1 = new Color(17,1,1);
    background2 = new Color(80, 59, 49);
    foreground1 = new Color(166, 255, 250);
    foreground2 = new Color(229,83,129);


    textArea.setText("\n\n\n" +
"                     ____              _             _  \n"  +
"                    /  __ \\            | |            | |  \n"    +
"                    | /  \\/___  _ __ | |_ __ ____| |_ ___ \n"+
"                    | |    / _ \\| '_ \\| __/ _` |/ __| __/ __| \n"+
"                    | \\__/\\ (_) | | | | || (_| | (__| |_\\__ \\ \n"+
"                    \\____/\\___/ |_| |_|\\__\\__,_|\\___|\\__|");

    setMode();
  };


    public void defaultMode(){
      background2 = UIManager.getColor("FormPanel.background");
      background1 = UIManager.getColor("TextArea.background");
      foreground1 = UIManager.getColor("TextArea.foreground");
      foreground2 = UIManager.getColor("Label.foreground");

      setMode();

    }

  public void randomMode(){
    background2 = new Color((int)Math.floor(Math.random() *100), (int)Math.floor(Math.random() *100), (int)Math.floor(Math.random() *100) );
    background1 = new Color((int)Math.floor(Math.random() *100), (int)Math.floor(Math.random() *100), (int)Math.floor(Math.random() *100));
    foreground1 = new Color((int)Math.floor(Math.random() *100) + 155, (int)Math.floor(Math.random() *155) + 100, (int)Math.floor(Math.random() *155) +100);
    foreground2 = new Color((int)Math.floor(Math.random() *155) + 100, (int)Math.floor(Math.random() *155) + 100, (int)Math.floor(Math.random() *155) + 100);
    setMode();
  }


  public void pinkMode(){
    background2 = new Color(252,102,99);
    background1 = new Color(243, 156, 107);
    foreground1 = new Color(38, 20, 71);
    foreground2 = new Color(38, 20, 71);
    setMode();

  }

  public void setMode(){
    textArea.setBackground(background1);
    textArea.setForeground(foreground1);
    formPanel.setBackground(background2);
    fNameLabel.setForeground(foreground2);
    lNameLabel.setForeground(foreground2);
    phoneLabel.setForeground(foreground2);
    mailLabel.setForeground(foreground2);

    addBtn.setBackground(background1);
    searchBtn.setBackground(background1);
    addBtn.setForeground(foreground1);
    searchBtn.setForeground(foreground1);
    removeBtn.setBackground(background1);
    showBtn.setBackground(background1);
    removeBtn.setForeground(foreground1);
    showBtn.setForeground(foreground1);

    fNameField.setBackground(background1);
    lNameField.setBackground(background1);
    phoneField.setBackground(background1);
    mailField.setBackground(background1);
    fNameField.setForeground(foreground2);
    lNameField.setForeground(foreground2);
    phoneField.setForeground(foreground2);
    mailField.setForeground(foreground2);

    menuBar.setBackground(background1);
    menuBar.setForeground(foreground1);
    fileMenu.setBackground(background1);
    fileMenu.setForeground(foreground1);

    darkMode.setBackground(background1);
    darkMode.setForeground(foreground1);
    lightMode.setForeground(foreground1);
    lightMode.setBackground(background1);
    pinkMode.setBackground(background1);
    pinkMode.setForeground(foreground1);
    avsluta.setForeground(foreground1);
    avsluta.setBackground(background1);
    clear.setBackground(background1);
    clear.setForeground(foreground1);
    randomMode.setBackground(background1);
    randomMode.setForeground(foreground1);

  }




  public void setLayout(){

        formPanel.setPreferredSize(new Dimension(250, 100));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(fNameLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(fNameField, gc);

        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(lNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        formPanel.add(lNameField, gc);


        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(phoneLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        formPanel.add(phoneField, gc);

        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(mailLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        formPanel.add(mailField, gc);

        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(addBtn, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        formPanel.add(searchBtn, gc);

        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        formPanel.add(showBtn, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        formPanel.add(removeBtn, gc);


  }


}
