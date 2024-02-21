package MainApp;

public class User {
	
	final int id;
	String name;
	String surname;
	final String turkishId;
	String password;

	public User(int id, String name, String surname, String turkishId, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.turkishId = turkishId;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return (id + " " + name + " " + surname + " " + turkishId + " " + password);
	}
}
