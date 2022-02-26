import java.util.Scanner;

public class AccountCreator {

	private DataStorage data;
	
	public AccountCreator(DataStorage d) {
		this.data = d;
	}
	
	
	public void register() {
		
		//declare the variables
		String loginID = "";
		String password = "";
		String name = "";
		String school = "";
		
		Scanner input = new Scanner(System.in);
		boolean loginReqLen = false;
		boolean loginReqLetter = false;
		boolean loginReqDigit = false;
		boolean loginReqSpecial = false;
		
		
		
		while(!loginReqLen || !loginReqLetter || !loginReqDigit || !loginReqSpecial) {
			System.out.println("Please enter your new login id (3-10 characters) for registration");
			loginID = input.nextLine();
			if(loginID.length() >= 3 && loginID.length() <= 10) {
				loginReqLen = true;
			}
			
			for(int i = 0; i < loginID.length(); i++) {
				char c = loginID.charAt(i);
				if(c == '#' || c == '?' || c == '*') {
					loginReqSpecial = true;
				}
				else if((c >= 48 && c <= 57)) {
					loginReqLetter = true;
				}
				else if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
					loginReqDigit = true;
				}
			}
			
			//password
			System.out.println("Please enter your password for registration");
			password = input.nextLine();
			
			while(password.equals(loginID)) {
				System.out.println("Your password should not be same as your loginID");
				System.out.println("Please enter your password for registration");
				password = input.nextLine();
			}
			
			//Name
			System.out.println("Please enter your Name for registration");
			name = input.nextLine();
			
			//School
			System.out.println("Please enter your School Name for registration");
			school = input.nextLine();
		}
		
		data.registerUser(loginID, password, name, school);
		
		input.close();
		
		
	}

}
