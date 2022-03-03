
public interface DataStorage {
	
	public void registerUser(String login, String password, String name, String school);
	public AccountLogin loginUser(String login, String password);
	public void post(String userID, String message, String datetime, String type, int parent);
	public boolean sendNotification(String sender, String receiver, String message, String dateTime, String type, String status);
	public boolean updateProfile(String userName, String name, String schoolName, String password, boolean flag);

}
