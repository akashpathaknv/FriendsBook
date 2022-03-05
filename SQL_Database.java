import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL_Database implements DataStorage{
	
	final String DATABASE_URL = 
            "jdbc:mysql://mis-sql.uhcl.edu/pathaka6088?useSSL=false";
	 final String db_id = "pathaka6088";
	 final String db_psw = "2002166";
    
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet resultSet = null;
	 
	@Override
	public void registerUser(String login, String password, String name, String school) {
		try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "pathaka6088", "2002166");
             //create a statement
             statement = connection.createStatement();
             
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //insert a record into user Table
             
             statement.executeUpdate("insert into user values "
                     + "('" + login + "', '" + password + "', '" 
                     + name + "', '" + school + "')");
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display message
            System.out.println("*** New user has been registered!***");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("User registration failed");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
                 //resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
		
	}
	@Override
	public AccountLogin loginUser(String login, String password) {

		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select id, name, school from user "
                    + "where id = '" + login + "' AND password = '" + password + "'");
            
            if(resultSet.next())
            {
            	return new AccountLogin(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
            else
            {
            	return null;
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
		
	}
	@Override
	public void post(String userID, String message, String datetime, String type, int parent) {
		
		try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "pathaka6088", "2002166");
             //create a statement
             statement = connection.createStatement();
             
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //insert a record into post Table
             
             statement.executeUpdate("insert into post(userID, content, datetime, type, parent) values "
                     + "('" + userID + "', '" + message + "', '" 
                     + datetime + "', '" + type + "', '" + parent + "')");
             resultSet = statement.executeQuery("select id from post order by id desc limit 1");
             
             if(resultSet.next()) {
            	 statement.executeUpdate("insert into updates(userID, type, postID) values('" + userID + "', '" + type + "', " + resultSet.getInt(1) + ")");
             }
             
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display message
            System.out.println("*** Your post/comment has been successfully created ***");
            
            System.out.println();
             
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Post Creation has failed");
             e.printStackTrace();
         }
         finally
         {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	}
	@Override
	public boolean sendNotification(String sender, String receiver, String message, String dateTime, String type,
			String status) {
		
		try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "pathaka6088", "2002166");
             //create a statement
             statement = connection.createStatement();
             
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //insert a record into notification Table
             
             statement.executeUpdate("insert into notification(userid1, userid2, message, datetime, type, status) values "
                     + "('" + sender + "', '"+ receiver +  "', '" + message + "', '" 
                     + dateTime + "', '" + type + "', '" + status + "')");
             
             
             //insert into friend table only if type = n (notification)
             
             if(type.equals("n")) {
            	 
            	 //Status u - friend request not accepted
            	 statement.executeUpdate("insert into friend(id1, id2, status) values" + "('" + sender + "', '" + receiver + "', '" + status + "')");
             }
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display message
            if(type.equals("n")) {
            	System.out.println("*** Your friend request has been succesfully sent ***");
            }
            else {
            	System.out.println("*** Your message has been succesfully sent ***");
            }
            
            
            System.out.println();
            return true;
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Notification Creation has failed");
             e.printStackTrace();
             return false;
         }
         finally
         {
             //close the database
             try
             {
                 //resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	}
	@Override
	public boolean updateProfile(String userName, String name, String schoolName, String password, boolean flag) {
		
		try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "pathaka6088", "2002166");
             //create a statement
             statement = connection.createStatement();
             
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //update a record into user Table
             
             if(flag) {
            	 statement.executeUpdate("update user set name = '" + name + "', school = '" + schoolName + "', password = '" + password + "'  where id = '" + userName + "'");
             }
             else {
            	 statement.executeUpdate("update user set name = '" + name + "', school = '" + schoolName + "'  where id = '" + userName + "'");
            	 int id = -1;
            	 String type = "update";
            	 
            	 //insert into updates table since user updated the profile
            	 statement.executeUpdate("insert into updates(userID, type, postID) values('" + userName + "', '" + type + "', " + id + ")");
             }
             
             System.out.println("*** Your friend request has been succesfully sent ***");
             connection.commit();
             connection.setAutoCommit(true);
            
            System.out.println();
            return true;
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Notification Creation has failed");
             e.printStackTrace();
             return false;
         }
         finally
         {
             //close the database
             try
             {
                 //resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }
	}
	@Override
	public ArrayList<String> getFriendRequest(String userName) {
		ArrayList<String> friendList = new ArrayList<String>();
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select id2 from friend where status = 'u'");
            
            while(resultSet.next()) {
            	friendList.add(resultSet.getString(1));
            }
            
            return friendList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}
	@Override
	public boolean acceptFriendRequest(String user1, String user2) {
		
		try
        {
             //connect to the database
             connection = DriverManager.getConnection(DATABASE_URL, 
                     "pathaka6088", "2002166");
             //create a statement
             statement = connection.createStatement();
             
             
             //rolled back to here if anything wrong
             connection.setAutoCommit(false);
             
             //update a record into user Table
             
             statement.executeUpdate("update friend set status = 'a' WHERE id1 = '" + user1 + "' AND id2 = '" + user2 + "'" );
             String status = "x";
             statement.executeUpdate("insert into friend(id1, id2, status) values" + "('" + user2 + "', '" + user1 + "', '" + status + "')"); 
             
             connection.commit();
             connection.setAutoCommit(true);
            
            System.out.println();
            return true;
         }
         catch(SQLException e)
         {
             //handle the exceptions
             System.out.println("Friend request acceptance failed");
             e.printStackTrace();
             return false;
         }
         finally
         {
             //close the database
             try
             {
                 //resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }

	}
	@Override
	public ArrayList<String[]> displayMessages(String id) {
		
		ArrayList<String[]> messageList = new ArrayList<String[]>();
		Statement statement1 = null;
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select userid1, userid2, message from notification where type = 'm' AND userid2 = '" + id + "' AND status = 'u'" );
            
            while(resultSet.next()) {
            	messageList.add(new String[] {resultSet.getString(1), resultSet.getString(3)});
            	statement1 = connection.createStatement();
            	statement1.executeUpdate("UPDATE notification SET status = 'r' WHERE userid1 = '" + resultSet.getString(1) + "' AND userid2 = '" + resultSet.getString(2) + "'" );
            	
            }
            
            return messageList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
            	if(resultSet.next()) {
            		resultSet.close();
            		statement1.close();
            	}
            	
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}
	@Override
	public ArrayList<String> displayFriends(String id) {
		
		ArrayList<String> friendList = new ArrayList<String>();
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select id2 from friend where id1 = '" + id + "'" );
            
            while(resultSet.next()) {
            	friendList.add(resultSet.getString(1));
            	
            }
            
            return friendList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
            	if(resultSet.next()) {
            		resultSet.close();	
            	}
            	
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}
	@Override
	public ArrayList<String> displayUpdates(String id) {
		
		ArrayList<String> friendList = new ArrayList<String>();
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select userID from updates where userID IN (select id2 from friend where id1 = '" + id + "' OR id2) ORDER BY id DESC LIMIT 3" );
            
            while(resultSet.next()) {
            	friendList.add(resultSet.getString(1));
            	
            }
            
            return friendList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
            	if(resultSet.next()) {
            		resultSet.close();	
            	}
            	
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}
	@Override
	public ArrayList<String[]> displayPosts(String id) {
		
		ArrayList<String[]> postList = new ArrayList<String[]>();
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select id, userID, content from post where userID IN (select id2 from friend where id1 = '" + id + "') AND type = 'post' ORDER BY id DESC LIMIT 3" );
            
            while(resultSet.next()) {
            	postList.add(new String[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)});
            	
            }
            
            return postList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
            	if(resultSet.next()) {
            		resultSet.close();	
            	}
            	
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}
	@Override
	public ArrayList<String> displayComments(int parent) {
		
		ArrayList<String> commentList = new ArrayList<String>();
		
		try
        {
            
            //connect to the database
			connection = DriverManager.getConnection(DATABASE_URL, 
                  "pathaka6088", "2002166");
            //create statement
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select content from post where parent = " + parent );
            
            while(resultSet.next()) {
            	commentList.add(resultSet.getString(1));
            	
            }
            
            return commentList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
            	if(resultSet.next()) {
            		resultSet.close();	
            	}
            	
                connection.close();
                statement.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
	}


}
