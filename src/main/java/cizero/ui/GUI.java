package cizero.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

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

  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenu modes;
  private JMenuItem darkMode;
  private JMenuItem lightMode;
  private JMenuItem pinkMode;
  private JMenuItem avsluta;
  private JMenuItem clear;
  private JMenuItem randomMode;
  private JMenuItem removeAll;
  private JMenuItem insaneMode;


  private Color background1;
  private Color background2;
  private Color foreground1;
  private Color foreground2;

  private boolean insane = false;

  /**Konstruktorn skapar en ny ContactBook och hämtar dess kontakter, skapar en JMenuBar,
  *lägger till lyssnare till alla knappar och menyalternativ. Placerar också ut formpanel (med buttons och text fields)
  och textpanel (som innehåller den JTextArea som kontaktinformationen visas på)**/

  public GUI(String password) throws SQLException, ClassNotFoundException{

		contactBook = new ContactBook(password);


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

    createMenuBar();

    removeAll.addActionListener(e -> {
      removeAllContacts();
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

    randomMode.addActionListener(e->{
      randomMode();
    });


    lightMode.addActionListener(e -> {
      defaultMode();
    });

    insaneMode.addActionListener(e -> {
      if(insane){
        insane=false;
        normalComponents();
        defaultMode();
        textArea.setText("");
      }
      else {
      insaneColors();
      insaneComponents();
      insane = true;
    }

    });

    searchBtn.addActionListener(e -> {
      if(insane){
        insaneColors();
        String text = "\n\n      Va?\n\n             Vad?\n VA VA VA???\n\n\n\n                         va? " +
        "\n\n\nVAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA??????????\n\n\n                             va?";
        textArea.setText(text);
      }else {
      findContact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText());
      clearForm();
    }
    });

    addBtn.addActionListener(e -> {
      if(insane){
        insaneColors();
        String text = "";
        for(int i=0; i<100; i++){
          text += "ALL WORK AND NO PLAY MAKES JACK A DULL BOY\n";
        }
        textArea.setText(text);
      }else{
      addContact();
    }
    });

    removeBtn.addActionListener(e -> {
      if(insane){
        String text = "";
        insaneColors();
        for(int i=0; i<100; i++){
          text += "HA HA HA HA HA HA HA HA HA HA HA HA HA HA HA HA\n";
        }
        textArea.setText(text);
      }else{
        removeContact();
      }

    });

    showBtn.addActionListener(e -> {
      if(insane){
        showInsaneContacts();

      }
      else{
      showContacts();
    }
    });

    setSize(600, 400);
    setLayout();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);


  }



/**Metoder som kallas vid knapptryck:

/** Kallar på metoden addContact() i klassen ContactBook genom att hämta alla persondata ifrån JTextFields i Guit.
skriver ut felmeddelande om kontakten redan finns och inte läggs till **/
    public void addContact(){
      try {
      contactBook.addContact(new Contact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText()));
      textArea.setText("Kontakten tillagd");
    } catch (SQLException e1) {
      textArea.setText("Kontakten finns redan i kontaktboken");
    } catch (ContactNotAddedException e1) {
      textArea.setText("Kontakten finns redan i kontaktboken");
    }
      clearForm();
    }

  public void removeContact(){

    try {
      contactBook.removeContact(new Contact(fNameField.getText(), lNameField.getText(), phoneField.getText(), mailField.getText()));
      textArea.setText("Kontakten borttagen");
    } catch (ContactNotRemovedException e1) {
      textArea.setText("Det gick inte att ta bort kontakten");
        e1.printStackTrace();
    } catch (SQLException e1) {
      textArea.setText("Det gick inte att ta bort kontakten");
        e1.printStackTrace();
    }

    clearForm();

  }


  public void removeAllContacts(){
    try{
    if(JOptionPane.showConfirmDialog(this, "Du kommer att radera alla dina kontakter. Fortsätt?",
        "Varning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
        contactBook.removeAllContacts();
        textArea.setText("Kontakterna raderades");
      }
    }
    catch(SQLException e){
      textArea.setText("Problem att koppla till databasen");
    }
  }


  public void showContacts(){
    String contactText = "";
    for(Contact contact: contacts){
      contactText += "==============================\n" + contact.getFirstName() + " " +
      contact.getLastName() + "\nTelenr: " + contact.getTeleNr() + "\nMail: " + contact.getEmail() + "\n\n";
      textArea.setText(contactText);
    }

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




/** Metod för att ta bort alla kontakter utan att behöva besvara en dialogruta
   skapad av test-anleningar**/
    public void removeAllContactsHidden(){
      try{
          contactBook.removeAllContacts();
      }
      catch(SQLException e){
        textArea.setText("Problem att koppla till databasen");
      }
    }



  /**Sätter upp alla element som krävs för att skapa en JMenuBar och lägger till dem till JFramen**/

  public void createMenuBar(){
    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    modes = new JMenu("Modes");
    darkMode = new JMenuItem("Dark mode");
    lightMode = new JMenuItem("Standard mode");
    pinkMode = new JMenuItem("Pink mode");
    randomMode = new JMenuItem("Random mode");
    insaneMode = new JMenuItem("Insane mode");
    clear = new JMenuItem("Clear");
    avsluta = new JMenuItem("Avsluta");
    removeAll = new JMenuItem("Radera alla");

    fileMenu.add(modes);
    modes.add(darkMode);
    modes.add(randomMode);
    modes.add(pinkMode);
    modes.add(lightMode);
    modes.add(insaneMode);
    fileMenu.add(clear);
    fileMenu.add(avsluta);
    fileMenu.add(removeAll);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);
  }



  /**Hjälpmetod som kallas varje gång texArean som är kontaktfönstret skall rensas **/
  public void clearForm(){
    fNameField.setText(""); lNameField.setText(""); phoneField.setText(""); mailField.setText("");
  }


  //Getters
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

  public JMenuItem getClearMenuItem(){
    return clear;
  }


  //Color modes. darkMode(), randomMode() och pinkMode() definierar fyra färger

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

  //här sätts färgerna på de faktiskta komponenterna
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
    insaneMode.setBackground(background1);
    insaneMode.setForeground(foreground1);
    modes.setBackground(background2);
    modes.setForeground(foreground2);
    removeAll.setBackground(background1);
    removeAll.setForeground(foreground1);

  }
  //Skapar en helt slumpässig färg
  public Color randomColor(){
    return new Color((int)Math.floor(Math.random() *255), (int)Math.floor(Math.random() *255), (int)Math.floor(Math.random() *255));
  }

  //Sätter en slumpässig färg till varje enskild komponent
  public void insaneColors(){
    textArea.setBackground(randomColor());
    textArea.setForeground(randomColor());
    formPanel.setBackground(randomColor());
    fNameLabel.setForeground(randomColor());
    lNameLabel.setForeground(randomColor());
    phoneLabel.setForeground(randomColor());
    mailLabel.setForeground(randomColor());

    addBtn.setBackground(randomColor());
    searchBtn.setBackground(randomColor());
    addBtn.setForeground(randomColor());
    searchBtn.setForeground(randomColor());
    removeBtn.setBackground(randomColor());
    showBtn.setBackground(randomColor());
    removeBtn.setForeground(randomColor());
    showBtn.setForeground(randomColor());

    fNameField.setBackground(randomColor());
    lNameField.setBackground(randomColor());
    phoneField.setBackground(randomColor());
    mailField.setBackground(randomColor());
    fNameField.setForeground(randomColor());
    lNameField.setForeground(randomColor());
    phoneField.setForeground(randomColor());
    mailField.setForeground(randomColor());

    menuBar.setBackground(randomColor());
    menuBar.setForeground(randomColor());
    fileMenu.setBackground(randomColor());
    fileMenu.setForeground(randomColor());

    darkMode.setBackground(randomColor());
    darkMode.setForeground(randomColor());
    lightMode.setForeground(randomColor());
    lightMode.setBackground(randomColor());
    pinkMode.setBackground(randomColor());
    pinkMode.setForeground(randomColor());
    insaneMode.setBackground(randomColor());
    insaneMode.setForeground(randomColor());
    avsluta.setForeground(randomColor());
    avsluta.setBackground(randomColor());
    clear.setBackground(randomColor());
    clear.setForeground(randomColor());
    randomMode.setBackground(randomColor());
    randomMode.setForeground(randomColor());
    modes.setBackground(randomColor());
    modes.setForeground(randomColor());
    removeAll.setBackground(randomColor());
    removeAll.setForeground(randomColor());

  }
  public void insaneComponents(){
    addBtn.setText("#%¤)=%()");
    removeBtn.setText("HAHHAHAHA");
    showBtn.setText("?????????");
    searchBtn.setText("INSANE!!!");
    fNameLabel.setText("nManrÖf");
    lNameLabel.setText("NmANreTfE");
    phoneLabel.setText("remmunELET");
    mailLabel.setText("LAiM");
    darkMode.setText("-----");
    lightMode.setText("-----");
    randomMode.setText("-----");
    modes.setText("-----");
    removeAll.setText("-----");
    clear.setText("-----");
    avsluta.setText("-----");
    pinkMode.setText("-----");
    insaneMode.setText("TRYCK INTE OBS! TRYCK INTE!");

  }

  public void normalComponents(){
    addBtn.setText("Ny kontakt");
    removeBtn.setText("Radera");
    showBtn.setText("Visa all");
    searchBtn.setText("Sök kontakt");
    fNameLabel.setText("Förnamn");
    lNameLabel.setText("Efternamn");
    phoneLabel.setText("Telenummer");
    mailLabel.setText("Mail");
    darkMode.setText("Dark mode");
    lightMode.setText("Standard mode");
    randomMode.setText("Random mode");
    modes.setText("Modes");
    removeAll.setText("Radera alla");
    clear.setText("Clear");
    avsluta.setText("Avsluta");
    pinkMode.setText("Pink mode");
    insaneMode.setText("Insane mode");
  }

  public void showInsaneContacts(){
    String contactText = "";
    contactText += "==============================\n" + "Insane Insanesson\nTelenr:9999\nMail:@@@@@\n\n"+
    "==============================\n" + "Insane In The Brain\nTelenr:SAKNAS!!!!!\nMail:insane@insane.insane\n\n";
    textArea.setText(contactText);

  }




  //Sätter Layouten för alla komponenter
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
