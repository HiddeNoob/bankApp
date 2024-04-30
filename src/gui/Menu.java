package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Account;
import data.Transaction;
import data.User;
import sqlConnection.SQLDataBase;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Frame;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable accountsPane;
	private TableModel accountTable;
	private TableModel transactionTable;
	private String[] accountTableTitle = new String[] {"Hesap Kodu","Bakiye"};
	private String[] transactionTableTitle = new String[] {"İşlem Kodu","Açıklama Metni","Tutar","Tarih"};
	private JLabel balanceText;
	private User currentUser;
	private SQLDataBase db;
	private JLabel welcomeText;
	private JTable transactionPane;
	public Menu(User user,SQLDataBase dbLink) {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		this.db = dbLink;
		this.currentUser = user;
		
		// tableları hazırlama işlemleri
		accountTable = new DefaultTableModel(convertAccountsToList(currentUser),accountTableTitle) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		transactionTable = new DefaultTableModel(convertTransactionsToList(currentUser.getAccounts().get(0)),transactionTableTitle) {
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
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel leftPaneUpper = new JPanel();
		splitPane.setLeftComponent(leftPaneUpper);
		leftPaneUpper.setLayout(new BorderLayout(0, 0));
		
		JScrollPane leftPane = new JScrollPane();
		leftPaneUpper.add(leftPane);
		
		accountsPane = new JTable();
		accountsPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				transactionTable = new DefaultTableModel(convertTransactionsToList(currentUser.getAccounts().get(accountsPane.getSelectedRow())),transactionTableTitle) {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};
				transactionPane.setModel(transactionTable);
			}
		});
		accountsPane.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		accountsPane.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		accountsPane.setForeground(SystemColor.activeCaptionText);
		accountsPane.setDragEnabled(false);
		accountsPane.setRowHeight(32);
		
			accountsPane.setShowGrid(false);
			accountsPane.setShowVerticalLines(false);
			accountsPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
			accountsPane.setModel(accountTable);
			leftPane.setViewportView(accountsPane);
			
			JLabel leftPaneTopText = new JLabel("Hesaplarınız");
			leftPaneTopText.setFont(new Font("Tahoma", Font.PLAIN, 24));
			leftPaneTopText.setHorizontalAlignment(SwingConstants.CENTER);
			leftPaneUpper.add(leftPaneTopText, BorderLayout.NORTH);
			
			JPanel panel_2 = new JPanel();
			splitPane.setRightComponent(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JLabel rightPaneTopText = new JLabel("İşlem Geçmişi");
			rightPaneTopText.setFont(new Font("Tahoma", Font.PLAIN, 24));
			rightPaneTopText.setHorizontalAlignment(SwingConstants.CENTER);
			panel_2.add(rightPaneTopText, BorderLayout.NORTH);
			
			JScrollPane scrollPane = new JScrollPane();
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			transactionPane = new JTable();
			transactionPane.setRowHeight(26);
			transactionPane.setFillsViewportHeight(true);
			transactionPane.setShowGrid(false);
			transactionPane.setRowSelectionAllowed(false);
			transactionPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
			transactionPane.setModel(transactionTable);
			scrollPane.setViewportView(transactionPane);
	}
	
	private String[][] convertAccountsToList(User user){
		String [][] test = new String[user.getAccounts().size()][accountTableTitle.length];
		
		for(int i = 0 ; i < user.getAccounts().size() ; i++) {
			test[i][0] = user.getAccounts().get(i).getAccountId() + ""; // int i hızlıca stringe çevirmek için boş string ile toplama yaptım
			test[i][1] = user.getAccounts().get(i).getBalance() + ""; 
		}
		return test;
	}
	
	private String[][] convertTransactionsToList(Account account){
		String [][] test = new String[account.getTransactions().size()][transactionTableTitle.length];
		for(int i = 0; i < account.getTransactions().size(); i++) {
			test[i][0] = account.getTransactions().get(i).getId() + "";
			test[i][1] = createDescriptionForTransactionColumn(account.getTransactions().get(i),account);
			test[i][2] = account.getTransactions().get(i).getAmount() + "";
			test[i][3] = account.getTransactions().get(i).getDate().toString() + "";
		}
		return test;
		
		
	}
	private String createDescriptionForTransactionColumn(Transaction t,Account account) {
		if(t.getReceiverId() == t.getSenderId()) {  // kullanıcı ya çekim yada yatırım yapmıştır
			if(t.getAmount() > 0) {
				return "Para Yatırma";
			}
			else {
				return "Para Çekme";
			}
		}
		else {
			if(t.getSenderId() == account.getAccountId()) {
				return "Gönderilen Para";
			}
			else {
				return "Gelen Para";
			}
		}
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
