package cizero.domain;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import cizero.ui.GUI;

public class Main {

	public static void main(String[] args) {
		GUI gui;
		String password;
		final JPasswordField pf = new JPasswordField();
		String meddelande = "Ange lösenord";

		while (true){
		// password = JOptionPane.showConfirmDialog(null, pf, meddelande, JOptionPane.OK_CANCEL_OPTION,
		// JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(pf.getPassword()) : "";
		password = new String(System.console().readPassword("%s>", "Ange lösenordet till din MySqlDatabas"));
			try{
			gui = new GUI(password);
			break;
		}
		catch(ClassNotFoundException e){
			System.out.println("Problem att koppla upp mot databasen");
			e.printStackTrace();
			break;
		}
		catch(SQLException e1){
			meddelande = "Fel lösenord";
			e1.printStackTrace();
		}
		}
	}
	}
