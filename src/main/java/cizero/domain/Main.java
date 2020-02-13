package cizero.domain;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import cizero.ui.GUI;

public class Main {

	public static void main(String[] args) {
		GUI gui;
		if(args == null) {
			final String password;
			if (System.console() == null) {
				final JPasswordField pf = new JPasswordField();
				password = JOptionPane.showConfirmDialog(null, pf, "Ange lösenord", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ? new String(pf.getPassword()) : "";
			} else {
				password = new String(System.console().readPassword("%s", "Ange lösenord"));
			}
			
			gui = new GUI(password);
		} else {
			gui = new GUI(args);
		}
		
	}

}
