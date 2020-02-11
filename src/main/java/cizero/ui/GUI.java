package cizero.ui;

import cizero.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {

  private ContactBook contactBook;
  private ArrayList<Contact> tempContacts;

  private ArrayList<Contact> contactBookTest = new ArrayList<>();

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
  private JButton removeBtn = new JButton("Radera kontakt");
  private JButton showBtn = new JButton("visa kontakter");

  private JPanel textPanel = new JPanel();
  private JTextArea textArea = new JTextArea();

  private JPanel formPanel = new JPanel();


  public GUI(){

     Contact pontus = new Contact("Pontus", "Eriksson", "987654", "joi@gjoij.coe");
     Contact kalle = new Contact("Kalle", "Persson", "982000", "kalle@gjoij.coe");
     Contact pontusPersson = new Contact("Pontus", "Persson", "687654", "pp@gjoij.coe");
     contactBookTest.add(pontus);
     contactBookTest.add(kalle);
     contactBookTest.add(pontusPersson);

    setLayout(new BorderLayout());

    textPanel.setLayout(new BorderLayout());
    textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

    add(textPanel, BorderLayout.CENTER);
    add(formPanel, BorderLayout.WEST);


    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem darkMode = new JMenuItem("Dark mode");
    JMenuItem lightMode = new JMenuItem("Light mode");
    fileMenu.add(darkMode);
    fileMenu.add(lightMode);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);

    darkMode.addActionListener(e -> {
      darkMode();
    });

    searchBtn.addActionListener(e -> {
      findContact(fNameField.getText());
    });

    lightMode.addActionListener(e -> {
      defaultMode();
    });

    addBtn.addActionListener(e -> {
      contactBook.addContact(new Contact(fNameField.getText(), lNameField.getText(), phoneField.getTex(), mailField.getText()));
    });


    setSize(600, 400);
    setLayout();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //darkMode();


  }





  public void findContact(String firstName){
    tempContacts = new ArrayList<>();
    for(Contact contact : contactBookTest){
      if (contact.getFirstName().equals(firstName)){
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


  public void darkMode(){
    textArea.setBackground(new Color(17,1,1));
    textArea.setForeground(new Color(166, 225, 250));
    formPanel.setBackground(new Color(80, 59, 49));
    fNameLabel.setForeground(new Color(229, 83, 129));
    lNameLabel.setForeground(new Color(229, 83, 129));
    phoneLabel.setForeground(new Color(229, 83, 129));
    mailLabel.setForeground(new Color(229, 83, 129));
    addBtn.setBackground(new Color(17,1,1));
    searchBtn.setBackground(new Color(17,1,1));
    addBtn.setForeground(new Color(166, 225, 250));
    searchBtn.setForeground(new Color(166, 225, 250));
    fNameField.setBackground(new Color(17, 1, 1));
    lNameField.setBackground(new Color(17, 1, 1));
    phoneField.setBackground(new Color(17, 1, 1));
    mailField.setBackground(new Color(17, 1, 1));
    fNameField.setForeground(new Color(229, 83, 129));
    lNameField.setForeground(new Color(229, 83, 129));
    phoneField.setForeground(new Color(229, 83, 129));
    mailField.setForeground(new Color(229, 83, 129));

    textArea.setText("\n\n\n" +
"                     ____              _             _  \n"  +
"                    /  __ \\            | |            | |  \n"    +
"                    | /  \\/___  _ __ | |_ __ ____| |_ ___ \n"+
"                    | |    / _ \\| '_ \\| __/ _` |/ __| __/ __| \n"+
"                    | \\__/\\ (_) | | | | || (_| | (__| |_\\__ \\ \n"+
"                    \\____/\\___/ |_| |_|\\__\\__,_|\\___|\\__|");
  };


    public void defaultMode(){
        textArea.setBackground(UIManager.getColor("TextArea.background"));
        textArea.setForeground(UIManager.getColor("TextArea.foreground"));
        formPanel.setBackground(UIManager.getColor("FormPanel.background"));
        fNameLabel.setForeground(UIManager.getColor("Label.foreground"));
        lNameLabel.setForeground(UIManager.getColor("Label.foreground"));
        phoneLabel.setForeground(UIManager.getColor("Label.foreground"));
        mailLabel.setForeground(UIManager.getColor("Label.foreground"));
        addBtn.setBackground(UIManager.getColor("Button.background"));
        searchBtn.setBackground(UIManager.getColor("Button.background"));
        addBtn.setForeground(UIManager.getColor("Button.foreground"));
        searchBtn.setForeground(UIManager.getColor("Button.foreground"));
        fNameField.setBackground(UIManager.getColor("TextField.background"));
        lNameField.setBackground(UIManager.getColor("TextField.background"));
        phoneField.setBackground(UIManager.getColor("TextField.background"));
        mailField.setBackground(UIManager.getColor("TextField.background"));
        fNameField.setForeground(UIManager.getColor("TextField.foreground"));
        lNameField.setForeground(UIManager.getColor("TextField.foreground"));
        phoneField.setForeground(UIManager.getColor("TextField.foreground"));
        mailField.setForeground(UIManager.getColor("TextField.foreground"));

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


  }


}
