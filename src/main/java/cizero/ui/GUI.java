package cizero.ui;

import javax.swing.*;
import java.awt.*;
import java.utils.*;

public class GUI extends JFrame {

  private ContactBook contacts;
  private JTextArea textArea = new JTextArea();
  private JLabel bigLabel = new JLabel();
  private JTextField fNameField = new JTextField();
  private JTextField lNameField = new JTextField();
  private JTextField phoneField = new JTextField();
  private JTextField mailField = new JTextField();
  private JLabel fNameLabel = new JLabel("Förnamn");
  private JLabel eNameLabel = new JLabel("Efternamn");
  private JLabel phoneLabel = new JLabel("telenummer");
  private JLabel mailLabel = new JLabel("mail");


  public GUI(){

    add(bigLabel);
    add(textArea);

    setVisible(true);
    setSize(500,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE;


  }


}
