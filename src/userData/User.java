package userData;

import java.util.ArrayList;

import SQLConnection.SQLDataBase;

public class User {
	
	private final int id;
	private String name;
	private String surname;
	private final String turkishId;
	private String password;
	ArrayList<Account> accounts;
	public User(int id, String name, String surname, String turkishId, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.turkishId = turkishId;
		this.password = password;
		this.accounts = new SQLDataBase("root","admin").getUserAccounts(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public double getTotalMoney() {
		double total = 0;
		for(Account x : accounts) {
			total += x.getBalance();
		}
		return total;
	}
	@Override
	public String toString() {
		return (id + " " + name + " " + surname + " " + turkishId + " " + password);
	}
}
