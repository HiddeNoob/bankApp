package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import data.Account;
import data.User;
import sqlConnection.SQLDataBase;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.List;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.Choice;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable accountsPane;
	private JTable transactions;
	TableModel accountTable;
	private String[] accountTableTitle = new String[] {"id","balance"};
	private JLabel balanceText;
	private User currentUser;
	private SQLDataBase db;
	private JLabel welcomeText;
	public Menu(User user,SQLDataBase dbLink) {
		this.db = dbLink;
		this.currentUser = user;
		accountTable = new DefaultTableModel(convertAccountsToList(currentUser),accountTableTitle) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		setPreferredSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(20, 20));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		welcomeText = new JLabel("Hoşgeldin " + currentUser.getName() + "!");
		panel_1.add(welcomeText);
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		panel_1.add(horizontalStrut);
		
		balanceText = new JLabel("Mevcut Bakiyeniz " + currentUser.getTotalMoney() + "₺");
		panel_1.add(balanceText);
		balanceText.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JPanel Buttons = new JPanel();
		panel.add(Buttons, BorderLayout.CENTER);
		FlowLayout fl_Buttons = (FlowLayout) Buttons.getLayout();
		
		JButton buttonWithdraw = new JButton("Para Çek");
		buttonWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					Account selectedAccount = currentUser.getAccounts().get(accountsPane.getSelectedRow());
					String input = JOptionPane.showInputDialog(buttonWithdraw, "Seçilen Hesap No: " + selectedAccount.getAccountId() + "\nHesap Bakiyesi: " + selectedAccount.getBalance() + "\nÇekilecek tutar");
					double dInput = Double.parseDouble(input);
					if(dInput <= 0) {
						JOptionPane.showMessageDialog(null, "Geçerli bir sayı giriniz");
					}
					else if(dInput > selectedAccount.getBalance()) {
						JOptionPane.showMessageDialog(null, "Bakiyenizden fazla para çekemezsiniz");
					}
					else {
						db.updateAccountBalance(selectedAccount.getAccountId(),selectedAccount.getBalance() - dInput);
						fetchAppWithDatabase();
					}
				}catch(Exception e1){
					if(e1 instanceof java.lang.IndexOutOfBoundsException) {
						JOptionPane.showMessageDialog(null, "Lütfen listeden hesap seçiniz");
					}
					else if(e1 instanceof java.lang.NumberFormatException){
						JOptionPane.showMessageDialog(null, "Geçerli bir sayı giriniz");
					}
				}
				
			}
		});
		buttonWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Buttons.add(buttonWithdraw);
		
		JButton buttonDeposit = new JButton("Para Yatır");
		buttonDeposit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		buttonDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Account selectedAccount = currentUser.getAccounts().get(accountsPane.getSelectedRow());
					String input = JOptionPane.showInputDialog(buttonWithdraw, "Seçilen Hesap No: " + selectedAccount.getAccountId() + "\nHesap Bakiyesi: " + selectedAccount.getBalance() + "\nYatırılacak tutar");
					double dInput = Double.parseDouble(input);
					if(dInput <= 0) {
						JOptionPane.showMessageDialog(null, "Geçerli bir sayı giriniz");
					}
					else {
						db.updateAccountBalance(selectedAccount.getAccountId(),selectedAccount.getBalance() + dInput);
						fetchAppWithDatabase();
					}
				}catch(Exception e1){
					if(e1 instanceof java.lang.IndexOutOfBoundsException) {
						JOptionPane.showMessageDialog(null, "Lütfen listeden hesap seçiniz");
					}
					else if(e1 instanceof java.lang.NumberFormatException){
						JOptionPane.showMessageDialog(null, "Geçerli bir sayı giriniz");
					}
				}
			}
		});
		Buttons.add(buttonDeposit);
		
		JButton buttonSendMoney = new JButton("EFT ile para gönder");
		buttonSendMoney.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Buttons.add(buttonSendMoney);
		
		JButton buttonCreateAccount = new JButton("Hesap Oluştur");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.createAccount(currentUser.getId() , 0 );
				fetchAppWithDatabase();
			}
		});
		buttonCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Buttons.add(buttonCreateAccount);
		
		JButton buttonDeleteAccount = new JButton("Hesap Sil");
		buttonDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(accountsPane.getSelectedRows().length != 0) { // kullanıcı hesap seçmediyse hata oluştur
					if(accountsPane.getSelectedRows().length == currentUser.getAccounts().size()) {
						JOptionPane.showMessageDialog(null, "Tüm hesaplarınızı silemezsiniz");
					}
					else {
						int silinenHesaplardakiPara = 0;
						for(int x : accountsPane.getSelectedRows()) {
							db.deleteAccount(currentUser.getAccounts().get(x).getAccountId()); // listede x. elemanda bulunan hesabı sil
							silinenHesaplardakiPara += currentUser.getAccounts().get(x).getBalance();
						}
						db.updateAccountBalance(db.getUserAccounts(currentUser.getId()).get(0).getAccountId(), silinenHesaplardakiPara + db.getUserAccounts(currentUser.getId()).get(0).getBalance()); // kalan hesaplarda ilk hesaba silinen hesaplardaki bakiyeyi aktar
						fetchAppWithDatabase();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Lütfen listeden silenecek hesap(lar) seçiniz");
				}
			}
		});
		buttonDeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Buttons.add(buttonDeleteAccount);
		
		JButton buttonSettings = new JButton("Ayarlar");
		buttonSettings.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Buttons.add(buttonSettings);
		
		JLabel lblNewLabel_2 = new JLabel("Hesaplarınız");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2, BorderLayout.SOUTH);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 29));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.2);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane leftPane = new JScrollPane();
		splitPane.setLeftComponent(leftPane);
		
		accountsPane = new JTable();
		accountsPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		accountsPane.setForeground(SystemColor.activeCaptionText);
		accountsPane.setDragEnabled(false);
		accountsPane.setRowHeight(32);
	
		accountsPane.setShowGrid(false);
		accountsPane.setShowVerticalLines(false);
		accountsPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		accountsPane.setModel(accountTable);
		leftPane.setViewportView(accountsPane);
		
		JScrollPane rightPane = new JScrollPane();
		splitPane.setRightComponent(rightPane);
		
		transactions = new JTable();
		transactions.setFont(new Font("Tahoma", Font.PLAIN, 29));
		rightPane.setViewportView(transactions);
	}
	
	private String[][] convertAccountsToList(User user){
		String [][] test = new String[user.getAccounts().size()][2];
		for(int i = 0 ; i < user.getAccounts().size() ; i++) {
			test[i][0] = user.getAccounts().get(i).getAccountId() + ""; // int i hızlıca stringe çevirmek için boş string ile toplama yaptım
			test[i][1] = user.getAccounts().get(i).getBalance() + ""; 
		}
		return test;
	}
	
	private void fetchAppWithDatabase() {
		currentUser = db.findUser(currentUser.getId());
		accountTable = new DefaultTableModel(convertAccountsToList(currentUser),accountTableTitle) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		balanceText.setText("Mevcut Bakiyeniz " + currentUser.getTotalMoney() + "₺");

		accountsPane.setModel(accountTable);
	}
}
