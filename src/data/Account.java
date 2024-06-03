package data;

import java.util.ArrayList;

public class Account {
	private final int id;
	private final int userId;
	private double balance;
	ArrayList<Transaction> transactions;
	
	public Account(int id, int userId, double balance,ArrayList<Transaction> transactions) {
		
		this.id = id;
		this.userId = userId;
		this.balance = balance;
		this.transactions = transactions;
	}
	public int getID() {
		return this.id;
	}
	public int getUserId() {
		return this.userId;
	}
	public double getBalance() {
		return balance;
	}
	
	public ArrayList<Transaction> getTransactions(){
		return transactions;
	}
	
	@Override
	public String toString() {
		return "Account id: " + id + " Balance: " + balance + "₺";
	}
}
