import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
             
             //insert a record into user Table
             
             statement.executeUpdate("insert into post(userID, content, datetime, type, parent) values "
                     + "('" + userID + "', '" + message + "', '" 
                     + datetime + "', '" + type + "', '" + parent + "')");
             
             connection.commit();
             connection.setAutoCommit(true);
             
              //display message
            System.out.println("*** Your post has been successfully created ***");
            
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


}
