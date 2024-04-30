package sqlConnection;

import java.sql.*;
import java.util.ArrayList;

import data.Account;
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
	 */
	public User findUser(String turkishId,String password) {
		try {
			statement = connect.prepareStatement("SELECT * FROM user WHERE turkish_id = ? AND password = ? ");
			statement.setString(1, turkishId);
			statement.setString(2, password);
			set = statement.executeQuery();
			if(set.next())
				return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(set.getInt(1)));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public User findUser(String turkishId) {
		try {
			statement = connect.prepareStatement("SELECT * FROM user WHERE turkish_id = ?");
			statement.setString(1, turkishId);
			set = statement.executeQuery();
			if(set.next())
				return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(set.getInt(1)));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public User findUser(int id) {
		try {
			statement = connect.prepareStatement("SELECT * FROM user WHERE id = ?");
			statement.setInt(1, id);
			set = statement.executeQuery();
			if(set.next())
				return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),getUserAccounts(set.getInt(1)));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * finds the user with the same id as the given parameter and modifies the user information
	 */
	public void updateUser(int id,String name,String surname,String turkishId,String password) { // TODO make this method
		try {
			statement = connect.prepareStatement("UPDATE user SET name= ?,surname = ?,turkish_id = ?,password = ? WHERE id = ?");
			statement.setString(1,name);
			statement.setString(2,surname);
			statement.setString(3,turkishId);
			statement.setString(4,password);
			statement.setInt(5,id);
			statement.executeUpdate();
			System.out.println( "user updated successfully " + "that has id : " + turkishId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createAccount(int userId,double balance) {
		try {
			statement = connect.prepareStatement("INSERT INTO account(user_id,balance) values(?,?)");
			statement.setInt(1, userId);
			statement.setDouble(2, balance);
			statement.executeUpdate();
			System.out.println("account added to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteAccount(int accountId) {
		try {
			statement = connect.prepareStatement("DELETE FROM account WHERE id = ?");
			statement.setInt(1, accountId);
			statement.executeUpdate();
			System.out.println("account deleted from database, id : " + accountId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Account getAccount(int accountId) {
		try {
			statement = connect.prepareStatement("SELECT * FROM account WHERE id = ?");
			statement.setInt(1, accountId);
			set = statement.executeQuery();
			if(set.next())
				return (new Account(accountId,set.getInt(2),set.getDouble(3)));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Account> getUserAccounts(int userId){
		try { 
			statement = connect.prepareStatement("SELECT * FROM account WHERE user_id = ?");
			statement.setInt(1, userId);
			ArrayList<Account> accounts = new ArrayList<Account>();
			set = statement.executeQuery();
			while(set.next())
			{
				accounts.add(new Account(set.getInt(1), userId, set.getDouble(3)));
			}
			return accounts;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
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