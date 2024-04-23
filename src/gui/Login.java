package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.User;
import sqlConnection.SQLDataBase;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private User loggedInUser; 
	public Login(SQLDataBase db) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
		
		JLabel lblNewLabel = new JLabel("T.C No");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setToolTipText("turkiye cumhuriyeti kimlik no");
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(50);
		contentPane.add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.CENTER, 12, 12);
		panel_1.setLayout(fl_panel_1);
		
		JLabel lblSifre = new JLabel("Şifre");
		lblSifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblSifre.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		panel_1.add(lblSifre);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(12);
		panel_1.add(passwordField);
		
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		contentPane.add(verticalStrut_1);
		
		JButton btnNewButton = new JButton("Giriş Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				User kullanici = null;
				try {
					username = usernameField.getText();
					password = passwordField.getText();
					kullanici = db.findUser(username,password);
		
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(kullanici == null) {
					JOptionPane.showInternalMessageDialog(null, "Kimlik No ile şifre eşleşmedi veya böyle bir kullanıcı yok");	
				}
				else {
					dispose();
					Menu menu = new Menu(kullanici);
					menu.setVisible(true);
					
				}
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(btnNewButton);
	}


}
