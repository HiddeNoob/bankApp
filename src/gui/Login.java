package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.awt.BorderLayout;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private User loggedInUser; 
	public Login(SQLDataBase db) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 212);
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
		usernameField.setColumns(12);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSifre = new JLabel("Şifre");
		panel_2.add(lblSifre);
		lblSifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblSifre.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(passwordField);
		passwordField.setColumns(12);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton register = new JButton("Kayıt Ol");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register registerPanel = new Register(db);
				registerPanel.setVisible(true);
			}
		});
		panel_3.add(register);
		register.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton login = new JButton("Giriş Yap");
		panel_3.add(login);
		login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		login.addActionListener(new ActionListener() {
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
					Menu menu = new Menu(kullanici,db);

					menu.setVisible(true);
					
				}
			}
		});
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
	}


}
