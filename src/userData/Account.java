package userData;

public class Account {
	private final int id;
	private final int userId;
	private double balance;
	public Account(int id, int userId, double balance) {
		this.id = id;
		this.userId = userId;
		this.balance = balance;
	}
	public void addBalance(double amount) {
		balance += amount;
	}
	public int getAccountId() {
		return this.id;
	}
	public int getUserId() {
		return this.userId;
	}
	public double getBalance() {
		return balance;
	}
	@Override
	public String toString() {
		return "Account id: " + id + " Balance: " + balance + "₺";
	}
}
