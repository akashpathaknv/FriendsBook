import java.util.Scanner;

public class AccountLogin {
	
	private DataStorage data;
	
	//data attributes
	private String userId;
	private String name;
	private String school;
	
	public AccountLogin(String id, String name, String school) {
		this.userId = id;
		this.name = name;
		this.school = school;
	}
	
	public AccountLogin(DataStorage d) {
		this.data = d;
	}
	
	public String getID() {
		return this.userId;
	}
	
	public void setID(String id) {
		this.userId = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSchool() {
		return this.school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}

	public void setData(DataStorage data) {
		this.data = data;
	}

	public void welcome() {
		System.out.println("Welcome " + this.getName() + " Below are the recent updates and Posts");
		// Call the updates and and top recent 3 posts and display here
		
		Scanner input = new Scanner(System.in);
		System.out.println("1. Select an update and post");
		System.out.println("2. Check notifications");
		System.out.println("3. Create a new post");
		System.out.println("4. Friends");
		System.out.println("5. Update profile");
		System.out.println("6. Send a message");
		System.out.println("7. Send a friend request");
		System.out.println("8. See Hashtags in trend");
		
		String sel = input.nextLine();
		
		if(sel.equals("1")) {
			//select a post to update or to add comment
		}
		else if(sel.equals("2")) {
			//Check notifications
		}
		else if(sel.equals("3")) {
			//Create new post
		}
		else if(sel.equals("4")) {
			//Check all friends
		}
		else if(sel.equals("5")) {
			//Update your profile
		}
		else if(sel.equals("6")) {
			//Send a message
		}
		else if(sel.equals("7")) {
			//Send a friend request
		}
		else if(sel.equals("8")) {
			//See Hashtags in trend
		}
		
	}
	
}
