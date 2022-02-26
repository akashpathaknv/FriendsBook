
public interface DataStorage {
	
	public void registerUser(String login, String password, String name, String school);
	public AccountLogin loginUser(String login, String password);

}
