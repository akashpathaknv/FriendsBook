import java.time.LocalDateTime;
import java.util.ArrayList;
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
		System.out.println("press x to logout");
		
		String sel = input.nextLine();
		
		if(sel.equals("1")) {
			//select a post to update or to add comment
			System.out.println("Press 1 for checking posts");
			System.out.println("Press 2 for checking updates");
			System.out.println("Press x to exit");
			sel = input.nextLine();
			if(sel.equals("x")) {
				return;
			}
			else if(sel.equals("1")) {
				showPosts();
			}
			else if(sel.equals("2")) {
				showUpdates();
			}
		}
		else if(sel.equals("2")) {
			//Check notifications
			System.out.println("Press 1 for checking friend request's");
			System.out.println("Press 2 for checking Messages");
			System.out.println("Press x to exit");
			sel = input.nextLine();
			if(sel.equals("x")) {
				return;
			}
			else if(sel.equals("1")) {
				showFriendRequest();
			}
			else if(sel.equals("2")) {
				showMessage();
			}
			
		}
		else if(sel.equals("3")) {
			//Create new post
			post();
		}
		else if(sel.equals("4")) {
			//Check all friends
			showFriends();
		}
		else if(sel.equals("5")) {
			//Update your profile
			updateProfile();
		}
		else if(sel.equals("6")) {
			//Send a message
			sendMessage();
		}
		else if(sel.equals("7")) {
			//Send a friend request
			sendFriendRequest();
		}
		else if(sel.equals("8")) {
			//See Hashtags in trend
		}
		else if(sel.equals("x")) {
			return;
		}
		
	}
	
	public void post() {
		
		System.out.println("Please provide the following details for the post");
		System.out.println();
		Scanner input = new Scanner(System.in);
		
		
		String message;
		System.out.println("Enter the message you need to post");
		message = input.nextLine();
		
		
		String type = "post";
		int parentID = -1;
		
		LocalDateTime myObj = LocalDateTime.now();
		String dateTime = myObj.toString();
		
		data.post(this.getID(), message, dateTime, type, parentID);
		
		
	}
	
	public void comment(int parent) {
		
		System.out.println();
		Scanner input = new Scanner(System.in);
		
		
		String message;
		System.out.println("Enter your comment");
		message = input.nextLine();
		
		
		String type = "comment";
		int parentID = parent;
		
		LocalDateTime myObj = LocalDateTime.now();
		String dateTime = myObj.toString();
		
		data.post(this.getID(), message, dateTime, type, parentID);
		
		
	}
	
	
	public void sendFriendRequest() {
		
		System.out.println("Enter your friend's username:");
		System.out.println();
		Scanner input = new Scanner(System.in);
		
		String username = input.nextLine();
		LocalDateTime myObj = LocalDateTime.now();
		String dateTime = myObj.toString();
		String message = "Hello!, I would like to be your friend";
		
		//Type n = notification, status u = unread
		String type = "n";
		String status = "u";
		if(data.sendNotification(this.userId, username, message, dateTime, type, status)) {
			System.out.println("Friend request has been successfully sent!!");
		}
		else {
			System.out.println("Friend request failed");
		}
	}
	
	public void sendMessage() {
		
		System.out.println("Enter your friend's username:");
		System.out.println();
		Scanner input = new Scanner(System.in);
		
		String username = input.nextLine();
		LocalDateTime myObj = LocalDateTime.now();
		String dateTime = myObj.toString();
		System.out.println("Enter your message: ");
		System.out.println();
		String message = input.nextLine();
		
		//Type m = message, status u = unread
		String type = "m";
		String status = "u";
		if(data.sendNotification(this.userId, username, message, dateTime, type, status)) {
			System.out.println("Message sent!!");
		}
		else {
			System.out.println("Message delivery failed.");
		}
	}
	
	public void sendMessage(String receiver) {
		
		Scanner input = new Scanner(System.in);
		
		LocalDateTime myObj = LocalDateTime.now();
		String dateTime = myObj.toString();
		System.out.println("Enter your message: ");
		System.out.println();
		String message = input.nextLine();
		
		//Type m = message, status u = unread
		String type = "m";
		String status = "u";
		if(data.sendNotification(this.userId, receiver, message, dateTime, type, status)) {
			System.out.println("Message sent!!");
		}
		else {
			System.out.println("Message delivery failed.");
		}
	}

	
	public void updateProfile() {
		
		System.out.println("Please select the option you want to update:");
		System.out.println("1. Name");
		System.out.println("2. School");
		System.out.println("3. Password");
		System.out.println("x. Exit");
		
		Scanner input = new Scanner(System.in);
		String sel = input.nextLine();
		String password = "";
		boolean flag = false;
		if(sel.equals("x")) {
			return;
		}
		else if(sel.equals("1")) {
			System.out.println("Enter you new name:");
			 this.setName(input.nextLine());
		}
		else if(sel.equals("2")) {
			System.out.println("Enter you new School name:");
			this.setSchool(input.nextLine());
		}
		else if(sel.equals("3")) {
			System.out.println("Enter you new Password:");
			password = input.nextLine();
			flag = true;
		}
		
		if(data.updateProfile(this.getID(), this.getName(), this.getSchool(), password, flag)) {
			System.out.println("User has succesfully updated the profile");
		}
		else {
			System.out.println("Profile was not updated");
		}
		input.close();
	}

	
	public void showFriendRequest() {
		
		ArrayList<String> friendList = this.data.getFriendRequest(this.userId);
		ArrayList<String> acceptedRequest = new ArrayList<String>();
		
		int i = 1;
		Scanner input = new Scanner(System.in);
		String sel;
		for(String s : friendList) {
			System.out.println(i++ +". " + s + "Sent you friend request.");
			System.out.println("press y to accept the request");
			sel = input.nextLine();
			if(sel.equals("y")) {
				acceptedRequest.add(s);
			}
		}
		
		for(String s: acceptedRequest) {
			//System.out.println("Friend request for " + s + " has been accepted " + this.getID());
			if(data.acceptFriendRequest(this.getID(), s)) {
				System.out.println("Friend request for " + s + " has been accepted");
			}
		}
		
	}

	public void showMessage() {
		ArrayList<String[]> messages = new ArrayList<String[]>();
		Scanner input = new Scanner(System.in);
		String sel;
		messages = this.data.displayMessages(this.getID());
		
		for(String[] message : messages) {
			System.out.println(message[0] + " : " + message[1]);
			System.out.println();
			System.out.println("Press y to respond to " + message[0]);
			sel = input.nextLine();
			if(sel.equals("y")) {
				this.sendMessage(message[0]);
			}
		}
	}

	public void showFriends() {
		ArrayList<String> friends = new ArrayList<String>();
		friends = this.data.displayFriends(this.getID());
		System.out.println("Here are your friends:");
		System.out.println();
		for(String friend : friends) {
			System.out.println(friend);
		}
	}

	public void showUpdates() {
		ArrayList<String> updates = new ArrayList<String>();
		updates = this.data.displayUpdates(this.getID());
		
		for(String update : updates) {
			System.out.println(update + " updated profile");
		}
	}
	
	public void showPosts() {
		ArrayList<String[]> posts = new ArrayList<String[]>();
		
		posts = this.data.displayPosts(this.getID());
		int i = 1;
		for(String[] post : posts) {
			System.out.println(i + " " + post[1] + " : " + post[2]);
			displayComments(Integer.valueOf(post[0]));
			System.out.println("Do you want to add comment? (y/n) ");
			Scanner input = new Scanner(System.in);
			String sel = input.nextLine();
			if(sel.equals("y")) {
				comment(Integer.valueOf(post[0]));
			}
		}
	}

	public void displayComments(int parent) {

		ArrayList<String> comments = new ArrayList<String>();
		comments = this.data.displayComments(parent);
		for(String comment : comments) {
			System.out.println("	" + comment);
		}
		
	}
}
