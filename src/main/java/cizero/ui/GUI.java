package cizero.ui;

import cizero.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {

  private ContactBook contactBook;
  private ArrayList<Contact> tempContacts;
  //private JTextArea textArea = new JTextArea();
//  private JPanel bigPanel = new JPanel();
  private JTextField fNameField = new JTextField(10);
  private JTextField lNameField = new JTextField(10);
  private JTextField phoneField = new JTextField(10);
  private JTextField mailField = new JTextField(10);
  private JLabel fNameLabel = new JLabel("Förnamn");
  private JLabel lNameLabel = new JLabel("Efternamn");
  private JLabel phoneLabel = new JLabel("telenummer");
  private JLabel mailLabel = new JLabel("mail");

//  private JPanel btnPanel = new JPanel();
  private JButton addBtn = new JButton("Ny kontakt");
  private JButton searchBtn = new JButton("Sök kontakt");
  private JButton removeBtn = new JButton("Radera kontakt");
  private JButton showBtn = new JButton("visa kontakter");

  private JPanel textPanel = new JPanel();
  private JTextArea textArea = new JTextArea();

  private JPanel formPanel = new JPanel();



  public GUI(){

    setLayout(new BorderLayout());

    textPanel.setLayout(new BorderLayout());
    textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

    add(textPanel, BorderLayout.CENTER);
    add(formPanel, BorderLayout.WEST);

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    setJMenuBar(menuBar);
    setSize(600, 500);

    setLayout();

    setVisible(true);

    setDefaultCloseOperation(EXIT_ON_CLOSE);


  }


  public void findContact(String firstName){
    tempContacts = new ArrayList<>();
    for(Contact contact : contactBook){
      if (contact.getFirstName().equals(firstName)){
        tempContacts.add(contact);
      }
    }
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
