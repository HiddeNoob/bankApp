package gui;


import java.awt.EventQueue;

import javax.swing.JOptionPane;
import sqlConnection.SQLDataBase;

public class App{

	private static SQLDataBase db;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					db = new SQLDataBase("root","admin");
					Login frame = new Login(db);
					frame.setVisible(true);
				} catch (Exception e) {
					
					if(e instanceof com.mysql.cj.jdbc.exceptions.CommunicationsException) {
						JOptionPane.showInternalMessageDialog(null, "Sunucuya Bağlanılamadı Daha Sonra Tekrar Deneyin");
					}

				}
				
			}
		});
	}
}

