package sqlConnection;

import java.sql.*;
import java.util.ArrayList;

import data.Account;
import data.Transaction;
import data.User;
public class SQLDataBase{

	private Connection connect;
	private PreparedStatement statement;
	private ResultSet set;
	public SQLDataBase(String username,String password) throws Exception {
		connectDB(username,password);
	}
	private void connectDB(String username,String password) throws Exception {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb",username,password);
	}
	public void createUser(String name,String surname,String turkishId,String password) throws Exception {

		statement = connect.prepareStatement("INSERT INTO user(name,surname,turkish_id,password) values(?,?,?,?)");
		statement.setString(1, name);
		statement.setString(2, surname);
		statement.setString(3, turkishId);
		statement.setString(4, password);
		statement.executeUpdate();
		System.out.println("user added to database");
	}
	/**
	 * finds the user that matchs the given parameter 
	 * @throws SQLException 
	 */
	public User findUser(String turkishId,String password) throws SQLException {

		statement = connect.prepareStatement("SELECT * FROM user WHERE turkish_id = ? AND password = ? ");
		statement.setString(1, turkishId);
		statement.setString(2, password);
		set = statement.executeQuery();
		int userId;
		if(set.next()) {
			userId = set.getInt(1);
			return new User(userId,set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(userId));
		}
		else return null;

	}
	public User findUser(String turkishId) throws SQLException {

		statement = connect.prepareStatement("SELECT * FROM user WHERE turkish_id = ?");
		statement.setString(1, turkishId);
		set = statement.executeQuery();
		int userId = set.getInt(1); // getInt fonksyionunu iki kere çağıramadığım için buraya veriyi kaydettım
		if(set.next())
			return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(userId));
		else return null;

	}
	public User findUser(int id) throws SQLException {

		statement = connect.prepareStatement("SELECT * FROM user WHERE id = ?");
		statement.setInt(1, id);
		set = statement.executeQuery();
	
		if(set.next())
			return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(set.getInt(1)));
		else return null;

	}
	/**
	 * finds the user with the same id as the given parameter and modifies the user information
	 * @throws SQLException 
	 */
	public void updateUser(int id,String name,String surname,String turkishId,String password) throws SQLException { 

		statement = connect.prepareStatement("UPDATE user SET name= ?,surname = ?,turkish_id = ?,password = ? WHERE id = ?");
		statement.setString(1,name);
		statement.setString(2,surname);
		statement.setString(3,turkishId);
		statement.setString(4,password);
		statement.setInt(5,id);
		statement.executeUpdate();
		System.out.println( "user updated successfully " + "that has id : " + turkishId);

	}
	public void createAccount(int userId,double balance) throws SQLException {

		statement = connect.prepareStatement("INSERT INTO account(user_id,balance) values(?,?)");
		statement.setInt(1, userId);
		statement.setDouble(2, balance);
		statement.executeUpdate();
		System.out.println("account added to database");

	}
	public void deleteAccount(int accountId) throws SQLException {
	
		statement = connect.prepareStatement("DELETE FROM account WHERE id = ?");
		statement.setInt(1, accountId);
		statement.executeUpdate();
		System.out.println("account deleted from database, id : " + accountId);

	}
	public Account getAccount(int accountId) throws SQLException {

		statement = connect.prepareStatement("SELECT * FROM account WHERE id = ?");
		statement.setInt(1, accountId);
		set = statement.executeQuery();
		if(set.next())
			return (new Account(accountId,set.getInt(2),set.getDouble(3),getAccountTransactions(set.getInt(1))));
		else return null;

	}
	public ArrayList<Account> getUserAccounts(int userId) throws SQLException{

		statement = connect.prepareStatement("SELECT * FROM account WHERE user_id = ?");
		statement.setInt(1, userId);
		ArrayList<Account> accounts = new ArrayList<Account>();
		set = statement.executeQuery();
		while(set.next())
		{
			accounts.add(new Account(set.getInt(1), userId, set.getDouble(3),getAccountTransactions(set.getInt(1))));
		}
		return accounts;


	}
	public void createTransaction(int senderID,int receiverID,double amount) throws SQLException {
		statement = connect.prepareStatement("INSERT INTO transaction(receiver_id,sender_id,amount) values(?,?,?);");
		statement.setInt(1, receiverID);
		statement.setInt(2, senderID);
		statement.setDouble(3, amount);
		statement.executeUpdate();
		System.out.println("transaction created");
	}
	public ArrayList<Transaction> getAccountTransactions(int accountId) throws SQLException{

		statement = connect.prepareStatement("SELECT * FROM transaction WHERE receiver_id = ? or sender_id = ?;");
		statement.setInt(1, accountId);
		statement.setInt(2, accountId);
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ResultSet anotherSet = statement.executeQuery();
		while(anotherSet.next())
		{
			transactions.add(new Transaction(anotherSet.getInt(1),anotherSet.getInt(2),anotherSet.getInt(3),anotherSet.getDouble(4),anotherSet.getTimestamp(5)));
		}
		return transactions;

	}
	
	public void updateAccountBalance(int accountId,double newBalance) {
		// UPDATE account SET balance = 10000 where id = 19;
		try {
			statement = connect.prepareStatement("UPDATE account SET balance = ? where id = ?;");
			statement.setInt(2, accountId);
			statement.setDouble(1, newBalance);
			statement.executeUpdate();
			System.out.println("account updated successfully,account id : " + accountId + " new balance: " + newBalance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}