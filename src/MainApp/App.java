package MainApp;
public class App{
	
	public static void main(String[] args) {	
		 dongu : do {
			System.out.println("1-Login");
			System.out.println("2-Register");
			System.out.println("3-Exit");
			byte input = Panel.getInputByte();
			switch (input) {
			case 1:
				Panel.login();
				break;
			case 2:
				Panel.register(); // TODO REGISTERI YAP LA
				break;
			case 3:
				break dongu;
			}
		} while (true);
	}
	
}