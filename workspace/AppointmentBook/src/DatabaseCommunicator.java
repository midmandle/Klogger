import java.sql.*;

public class DatabaseCommunicator {
	
	private static Connection getDatabaseCommunicator()
	{
		Connection dbConnection = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			String dbURL = "jdbc:sqlite:main.sqlite"; //TODO: Insert URL (jdbc:sqlite:<somename>.sqlite)
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		}
		catch( SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	
	//TODO: SQL: Make request.
	public static ResultSet MakeRequest(String query) throws SQLException
	{
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//String query = "CREATE TABLE testTable(a INTEGER, b INTEGER, PRIMARY KEY (a ASC));";
		
		try
		{
			dbConnection = getDatabaseCommunicator();
			statement = dbConnection.createStatement();
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			return resultSet;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			
			if(resultSet != null)
				resultSet.close();
			if(resultSet != null)
				statement.close();
			if(dbConnection != null)
				dbConnection.close();
			
		}
		return resultSet;
	}
}
