import java.sql.*;
import java.util.Calendar;

public class DatabaseCommunicator {
	private Connection dbConnection = null;
	
	public DatabaseCommunicator()
	{
		setupComms();
	}
	
	private void setupComms()
	{
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
			String dbURL = "jdbc:sqlite:main.sqlite"; //Insert DB URL (jdbc:sqlite:<somename>.sqlite)
			dbConnection = DriverManager.getConnection(dbURL);
			
		}
		catch( SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void MakeRequest(String query) throws SQLException
	{
		Statement statement = dbConnection.createStatement();
		statement.execute(query);
		
		statement.close();
	}
	
	public void AddAppointmentToDatabase(String tableName, Appointment appointment)
	{
		int startDay = appointment.getStartDateTime().get(Calendar.DAY_OF_MONTH);
		int startMonth = appointment.getStartDateTime().get(Calendar.MONTH);
		int startYear = appointment.getStartDateTime().get(Calendar.YEAR);
		int endDay = appointment.getEndDateTime().get(Calendar.DAY_OF_MONTH);
		int endMonth = appointment.getEndDateTime().get(Calendar.MONTH);
		int endYear = appointment.getEndDateTime().get(Calendar.YEAR);
		
		String eventTitle = appointment.getEventTitle();
		String description = appointment.getEventTitle();
		String location = "null";
		String query = String.format("INSERT INTO "+tableName+" (dateTimeFrom, dateTimeTo, eventTitle, description, location) VALUES (\"%d/%d/%d\", \"%d/%d/%d\", \"%s\", \"%s\", \"%s\");", startDay, startMonth, startYear, endDay, endMonth, endYear, eventTitle, description, location);
		
		try {
			MakeRequest(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RemoveAppointmentFromDatabase(String tableName, Appointment appointment)
	{
		String query = "DELETE FROM Book1 WHERE title == \""+appointment.getEventTitle()+"\";";
		
		try {
			MakeRequest(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetAllAppointmentsFromDatabase(String tableName, Appointment appointment)
	{
		//Returns result.
	}
	
	public void GetSecificAppointmentFromDatabase(String tableName, Appointment appointment)
	{
		//Returns results;
	}
	
	public boolean CheckIfAppointmentBookExistsOnDatabase(String tableName)
	{
		
		return false;
	}
	
	public void SetupNewAppointmentBookForDatabase(String tableName)
	{
		String query = "CREATE TABLE IF NOT EXISTS "+ tableName +"(dateTimeFrom DATE NOT NULL, dateTimeTo DATE, eventTitle VARCHAR NOT NULL, description VARCHAR, location VARCHAR);";
		
		try {
			MakeRequest(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RemoveAppointmentBookFromDatabase(String tableName)
	{
		String query = "DROP TABLE \"main\".\""+tableName+"\";";
		
		try {
			MakeRequest(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//TODO: Need some kind of output formatting function.
}
