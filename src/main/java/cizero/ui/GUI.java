package cizero.ui;

import cizero.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {

  private ContactBook contacts;
  private JTextArea textArea = new JTextArea();
  private JPanel bigPanel = new JPanel();
  private JTextField fNameField = new JTextField("", 15);
  private JTextField lNameField = new JTextField("", 15);
  private JTextField phoneField = new JTextField("", 15);
  private JTextField mailField = new JTextField("", 15);
  private JLabel fNameLabel = new JLabel("Förnamn");
  private JLabel lNameLabel = new JLabel("Efternamn");
  private JLabel phoneLabel = new JLabel("telenummer");
  private JLabel mailLabel = new JLabel("mail");


  public GUI(){

    setLayout(new GridLayout(1,2));

    add(bigPanel);
    add(textArea);
    bigPanel.add(fNameField);
    bigPanel.add(lNameField);
    bigPanel.add(phoneField);
    bigPanel.add(mailField);

    setVisible(true);
    setSize(500,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);


  }


}
