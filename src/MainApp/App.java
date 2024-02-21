package MainApp;
import java.util.Scanner;
import SQLConnection.SQLDataBase;

public class App{
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.println("1-Login");
		System.out.println("2-Register");
		byte input = stdin.nextByte();
		switch(input) {
			case 1:
				login(stdin);
				break;
			case 2:
				register(stdin);
				break;
		}
	}
	private static void login(Scanner stdin) {
		SQLDataBase sql = new SQLDataBase("root","admin");
		String tc_no = stdin.next();
		String password = stdin.next();
		User user = sql.isUserExist(tc_no, password);
		if(user == null) return;
		else {
			System.out.println(user);
		}
		
	}
	private static void register(Scanner stdin) {
		String tc_no = stdin.next();
		String password = stdin.next();	
	}
} 