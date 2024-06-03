package data;

import java.util.ArrayList;

import java.sql.Timestamp;


public class Transaction {
	private int id;
	private int receiver_id;
	private int sender_id;
	private double amount;
	private Timestamp date;
	public Transaction(int id, int receiver_id, int sender_id, double amount, Timestamp date) {
		super();
		this.id = id;
		this.receiver_id = receiver_id;
		this.sender_id = sender_id;
		this.amount = amount;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public int getReceiverId() {
		return receiver_id;
	}
	public int getSenderId() {
		return sender_id;
	}
	public double getAmount() {
		return amount;
	}
	public Timestamp getDate() {
		return date;
	}

	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", receiver_id=" + receiver_id + ", sender_id=" + sender_id + ", amount="
				+ amount + ", date=" + date + "]";
	}
	
	
}
