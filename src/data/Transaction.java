package data;

import java.util.Date;

public class Transaction {
	private int id;
	private int receiver_id;
	private int sender_id;
	private int amount;
	private Date date;
	public Transaction(int id, int receiver_id, int sender_id, int amount, Date date) {
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
	public int getAmount() {
		return amount;
	}
	public Date getDate() {
		return date;
	}
	
	
	
}
