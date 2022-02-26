import java.util.Scanner;

public class FriendsBook {
	
	

	public static void main(String[] args) {
		
		DataStorage data = new SQL_Database();
		Scanner input = new Scanner(System.in);
		String sel = "";
		
		while(!sel.equals("x"))
		{
			System.out.println();
			System.out.println("Welcome to FriendsBook");
			System.out.println("1: Register");
			System.out.println("2: Login");
			System.out.println("x: Leave");
			sel = input.nextLine();
			
			if(sel == "x") {
				break;
			}
			else if(sel.equals("1")) {
				//Register
				new AccountCreator(data).register();
				
			}
			else if(sel.equals("2")) {
				//Login
				new LoginSystem(data).login();
			}
			
		}
		
		input.close();

	}

}
