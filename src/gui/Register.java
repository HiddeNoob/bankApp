package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import sqlConnection.SQLDataBase;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputAd;
	private JTextField inputTCNo;
	private JTextField inputSoyad;
	private JTextField inputSifre;
	private JTextField inputSifre2;


	public Register(SQLDataBase db) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblNewLabel = new JLabel("T.C No:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 36, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 69, SpringLayout.WEST, panel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(lblNewLabel);
		
		inputTCNo = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, inputTCNo, 38, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, inputTCNo, 138, SpringLayout.WEST, panel);
		panel.add(inputTCNo);
		inputTCNo.setColumns(14);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ad");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1, 64, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1, 111, SpringLayout.WEST, panel);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(lblNewLabel_1_1_1);
		
		inputAd = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, inputAd, 66, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, inputAd, 138, SpringLayout.WEST, panel);
		panel.add(inputAd);
		inputAd.setColumns(14);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Soyad");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_1, 92, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1, 82, SpringLayout.WEST, panel);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(lblNewLabel_1_1_1_1);
		
		inputSoyad = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, inputSoyad, 94, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, inputSoyad, 138, SpringLayout.WEST, panel);
		inputSoyad.setColumns(14);
		panel.add(inputSoyad);
		
		JLabel lblNewLabel_1 = new JLabel("Şifre");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 120, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 95, SpringLayout.WEST, panel);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(lblNewLabel_1);
		
		inputSifre = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, inputSifre, 122, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, inputSifre, 138, SpringLayout.WEST, panel);
		inputSifre.setColumns(14);
		panel.add(inputSifre);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tekrar Şifre");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1, 148, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1, 32, SpringLayout.WEST, panel);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel.add(lblNewLabel_1_1);
		
		inputSifre2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, inputSifre2, 150, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, inputSifre2, 138, SpringLayout.WEST, panel);
		inputSifre2.setColumns(14);
		panel.add(inputSifre2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnIptalEt = new JButton("İptal Et");
		btnIptalEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnIptalEt.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!inputSifre.getText().equals(inputSifre2.getText())) {
					JOptionPane.showInternalMessageDialog(null, "Yazdığınız Şifreler Eşleşmiyor Tekrar Deneyin");	
				}
				else if(inputAd.getText().equals("") || inputSoyad.getText().equals("") || inputTCNo.getText().equals("") || inputSifre.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null, "Lütfen Tabloyu Tam Doldurunuz");
				}
				else if(inputTCNo.getText().length() != 11) {
					JOptionPane.showInternalMessageDialog(null, "Uygun bir T.C kimlik numarası giriniz ");
					System.out.println(inputAd.getText());
				}
				else if(!isSuitableForName(inputAd.getText())) {
					JOptionPane.showInternalMessageDialog(null, "İsminiz italik karakterlerden oluşmalıdır");
				}
				else if(!isSuitableForName(inputSoyad.getText())){
					JOptionPane.showInternalMessageDialog(null, "Soyisminiz italik karakterlerden oluşmalıdır");
				}
				else { 
					try {
						db.createUser(inputAd.getText(),  inputSoyad.getText() , inputTCNo.getText(), inputSifre.getText());
						JOptionPane.showInternalMessageDialog(null,"Hesabınız başarıyla oluşturuldu");
						db.createAccount(db.findUser(inputTCNo.getText()).getId(), 0);
						dispose();
					} catch (Exception e2) {
						if(e2 instanceof java.sql.SQLIntegrityConstraintViolationException)
						{
							JOptionPane.showInternalMessageDialog(null,"Bu kullanıcı zaten mevcut");
						}
			
						
					}
				}
			} 
		});
		btnNewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.add(btnNewButton);
		panel_1.add(btnIptalEt);
		
		
	}

	
	static public boolean isSuitableForName(String s) {
		for(char c : s.toCharArray()) {
			if(!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) // eğer isim yada soy isim italik karakterden başka karakter içeriyorsa false döndür yoksa true döndür
				return false;
		}
		return true;
	}
}
