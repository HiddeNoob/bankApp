package SQLConnection;

import MainApp.User;
import java.sql.*;
public class SQLDataBase{

	private Connection connect;
	private PreparedStatement statement;
	private ResultSet set;
	public SQLDataBase(String username,String password) {
		connectDB(username,password);
	}
	private void connectDB(String username,String password) {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb",username,password);
			System.out.println("Connected To Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createUser(String name,String surname,String turkishId,String password) {
		try {
			statement = connect.prepareStatement("INSERT INTO user(name,surname,turkish_id,password) values(?,?,?,?)");
			statement.setString(1, name);
			statement.setString(2, surname);
			statement.setString(3, turkishId);
			statement.setString(4, password);
			statement.executeUpdate();
			System.out.println("user has added to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * mySQL de bulunan kullanıcının tc ile eşleşen User classı döndürür
	 */
	public User isUserExist(String turkishId,String password) {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","admin");
			statement = connect.prepareStatement("SELECT * FROM user WHERE turkish_id = ? AND password = ? ");
			statement.setString(1, turkishId);
			statement.setString(2, password);
			set = statement.executeQuery();
			if(set.next())
				return new User(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}