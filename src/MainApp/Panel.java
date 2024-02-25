package MainApp;

import java.util.Scanner;

import SQLConnection.SQLDataBase;
import userData.Account;
import userData.User;

public class Panel {
	
	static private Scanner stdin = new Scanner(System.in);
	private static SQLDataBase sql = new SQLDataBase("root","admin");
	public static void login() {
		System.out.print("Enter Your Turkish ID: ");
		String tc_no = getInputString();
		System.out.print("Enter Your Password: ");
		String password = getInputString();
		User user = sql.findUser(tc_no, password);
		if(user == null) {
			System.out.println("ID and password didn't match try again.");
		}
		else {
			userPanel(user);
		}
	}
	private static void userPanel(User user)
	{
		do {
			System.out.println("Welcome " + user.getName() + " " + user.getSurname() + "\nYour Total Money is " + user.getTotalMoney() + "₺");
			System.out.print("1- Deposit\n2- Withdraw\n3- Transaction\n4- Account Information\n5- Settings\n6- Exit\n>>> ");
			byte choice = (byte) getInputBetween(1, 6);
			switch(choice) {
				case 1:
					if(user.getAccounts().size() == 0)
						System.out.println("You don't have a account to deposit money");
					else {
							System.out.println("Select Account to deposit");
							for (int i = 0; i < user.getAccounts().size(); i++) {
								System.out.println((i + 1) + "- " + user.getAccounts().get(i));
							}
							choice = (byte) getInputBetween(1, user.getAccounts().size());
							System.out.println("enter amount to deposit (max amount is 100.000₺)");
							double amount = getInputBetween(0, 100000);
							user.getAccounts().get(choice-1).addBalance(amount);
					}	
					break;
				case 2:
					if(user.getAccounts().size() == 0)
						System.out.println("You don't have a account to withdraw money");
					else {
							System.out.println("Select Account to withdraw");
							for (int i = 0; i < user.getAccounts().size(); i++) {
								System.out.println((i + 1) + "- " + user.getAccounts().get(i));
							}
							choice =(byte) getInputBetween(1, user.getAccounts().size());
							System.out.println("enter amount to withdraw");
							double amount = getInputBetween(0, user.getAccounts().get(choice-1).getBalance());
							user.getAccounts().get(choice-1).addBalance(-amount);
					}
					break;
			}
		} while(true);
		
	}
	public static void register() {
		System.out.print("enter turkish id: ");
		String tc_no = getInputString();
		if(sql.findUser(tc_no) == null) { // this is new user for bank
			System.out.print("enter your name: ");
			String name = getInputString();
			System.out.print("enter your surname: ");
			String surname = getInputString();
			System.out.print("enter your password: ");
			String password = getInputString();
			sql.createUser(name, surname, tc_no, password);
		}
		else {
			System.out.println("you're already registered for bank");
		}
	}
	public static byte getInputByte() {
		return stdin.nextByte();
	}
	public static String getInputString() {
		return stdin.next();
	}
	public static int getInputInteger() {
		return stdin.nextInt();
	}
	public static double getInputDouble() {
		return stdin.nextDouble();
	}
	public static double getInputBetween(double a , double b) { // a and b included
		do {
			double x = getInputDouble();
			if(x >= a && x <= b) {
				return x;
			}
			else{
				System.out.println("wrong chooice try again");
			}
		}while(true);
	}
} 
