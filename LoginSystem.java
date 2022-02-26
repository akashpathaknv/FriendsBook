import java.util.Scanner;

public class LoginSystem {
	
	private AccountLogin theLoginAccount;
	DataStorage data;
	
	public LoginSystem(DataStorage d) {
		this.data = d;
	}
	
	public void login()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String id="";
        String password="";
         
        
        //get the login info.
        System.out.println("Please enter your ID");
        id = input.nextLine();
        System.out.println("Please enter your password");
        password = input.nextLine();
        
        theLoginAccount = data.loginUser(id, password);
        if(theLoginAccount != null)
        {
            theLoginAccount.setData(data);
            theLoginAccount.welcome();
        }
        else
        {
            System.out.println("The login failed");
            System.out.println();
        }
         
    }

}
