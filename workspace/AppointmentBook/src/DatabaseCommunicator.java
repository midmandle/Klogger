import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatabaseCommunicator {
	private static Connection dbConnection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	private static void MakeRequest(String query)
	{
		try
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:main.sqlite"; //Insert DB URL (jdbc:sqlite:<somename>.sqlite)
				dbConnection = DriverManager.getConnection(dbURL);
				statement = dbConnection.createStatement();
				statement.execute(query);
			}
			finally
			{
				if(statement != null)
					statement.close();
				if(dbConnection != null)
					dbConnection.close();
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void MakeRequest(String query, ArrayList<ArrayList<?>> output)
	{
		try
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:main.sqlite"; //Insert DB URL (jdbc:sqlite:<somename>.sqlite)
				dbConnection = DriverManager.getConnection(dbURL);
				statement = dbConnection.createStatement();
				
				resultSet = statement.executeQuery(query);
				ResultSetMetaData resMet = resultSet.getMetaData();
				int numberOfColumns = resMet.getColumnCount();
				
				for(int i = 1; i <= numberOfColumns; i++)
				{
					int t = resMet.getColumnType(i);
					
					switch(t)
					{
						case Types.VARCHAR:
						{
							if(resMet.getColumnName(i).startsWith("date"))
							{
								output.add(new ArrayList<GregorianCalendar>());
							}
							else
								output.add(new ArrayList<String>());
						}
						default:
							output.add(new ArrayList<Object>());
					}
				}
				
				while(resultSet.next())
				{
					for(int i = 1; i <= numberOfColumns; i++) 
					{
						int t = resMet.getColumnType(i);
						
						switch(t)
						{
							case Types.VARCHAR:
							{
								if(resMet.getColumnName(i).startsWith("date")) //Its a date.
								{
									//retrieve dateTime string and parse to GregorianCal.
									GregorianCalendar calDateTimeFromDB = new GregorianCalendar();
									FormattedDateToGregorian(resultSet.getString(i), calDateTimeFromDB);
									
									ArrayList<GregorianCalendar> tempCalList = (ArrayList<GregorianCalendar>) output.get(i);
									tempCalList.add(calDateTimeFromDB);
									output.set(i, tempCalList);
								}
								else //Its a regular varchar
								{
									ArrayList<String> tempStringList = (ArrayList<String>) output.get(i);
									tempStringList.add(resultSet.getString(i));
									output.set(i, tempStringList); //ERROR
								}
								break;
							}
							default:
							{
								//System.out.println("Unexpected type.");
								break;
							}
						}
					}
				}
				
			}
			finally
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
				if(dbConnection != null)
					dbConnection.close();
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static String dateFormatter(GregorianCalendar cal)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setCalendar(cal);
		String output = sdf.format(cal.getTime());
		//System.out.println(output);
		return output;
	}
	
	private static void FormattedDateToGregorian(String formatted, GregorianCalendar output)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			java.util.Date dateTime = sdf.parse(formatted);
			output.setTime(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void AddAppointmentToDatabase(String tableName, Appointment appointment)
	{
		/*int startDay = appointment.getStartDateTime().get(Calendar.DAY_OF_MONTH);
		int startMonth = appointment.getStartDateTime().get(Calendar.MONTH);
		int startYear = appointment.getStartDateTime().get(Calendar.YEAR);
		int endDay = appointment.getEndDateTime().get(Calendar.DAY_OF_MONTH);			//DEPRECATED
		int endMonth = appointment.getEndDateTime().get(Calendar.MONTH);
		int endYear = appointment.getEndDateTime().get(Calendar.YEAR);*/
		
		String startDate = dateFormatter(appointment.getStartDateTime());
		String endDate = dateFormatter(appointment.getEndDateTime());
		
		String eventTitle = appointment.getEventTitle();
		String description = appointment.getEventTitle();
		String location = "null";
		String query = String.format("INSERT INTO "+tableName+" (dateTimeFrom, dateTimeTo, eventTitle, description, location) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\");", startDate, endDate, eventTitle, null, location);
		
		MakeRequest(query);
		
	}
	
	public static void RemoveAppointmentFromDatabase(String tableName, Appointment appointment)
	{
		String query = "DELETE FROM Book1 WHERE title == \""+appointment.getEventTitle()+"\";";
		
		MakeRequest(query);
		
	}
	
	public static void GetAllAppointmentsFromDatabase(String tableName)//, Appointment appointment)
	{
		String query = "SELECT * FROM "+tableName+";";
		ArrayList<ArrayList<?>> results = new ArrayList<ArrayList<?>>();
		MakeRequest(query, results); //TODO: Play with results list. Nested list thing.
		
		for(int i = 0 ; i < results.size(); i++)
			for(int j = 0; j < results.get(i).size(); j++)
			{
				if(results.get(i).get(j) instanceof GregorianCalendar)
				{
					GregorianCalendar date = (GregorianCalendar) results.get(i).get(j);
					System.out.println(date.get(Calendar.YEAR));
				}
			}
	
	}
	
	public static void GetSpecificAppointmentFromDatabase(String tableName, Appointment appointment)
	{
		//Returns results;
	}
	
	public static boolean CheckIfAppointmentBookExistsOnDatabase(String tableName)
	{
		String query = "SELECT * FROM sqlite_master;";
		ArrayList<ArrayList<?>> results = new ArrayList<ArrayList<?>>();
		MakeRequest(query, results);
		
		return false;
	}
	
	public static ArrayList<AppointmentBook> GenerateAppointmentBooksFromDatabase()
	{
		String query1 = "SELECT * FROM sqlite_master;";
		ArrayList<ArrayList<?>> results = new ArrayList<ArrayList<?>>();
		ArrayList<AppointmentBook> bookList = new ArrayList<AppointmentBook>();
		MakeRequest(query1, results); 
		
		for(int i = 0; i < results.size(); i++)
		{
			for(int j = 0; j < results.get(i).size(); j++)
			{
				if(i == 2)
					bookList.add(new AppointmentBook(results.get(i).get(j).toString()));
			}
		}
		
		//TODO: For each book, iterate through results and form appointments.
		
		return bookList;
	}
	
	public static void SetupNewAppointmentBookForDatabase(String tableName)
	{
		String query = "CREATE TABLE IF NOT EXISTS "+ tableName +"(dateTimeFrom TEXT NOT NULL, dateTimeTo TEXT, eventTitle VARCHAR NOT NULL, description VARCHAR, location VARCHAR);";
		
		MakeRequest(query);
		
	}
	
	public static void RemoveAppointmentBookFromDatabase(String tableName)
	{
		String query = "DROP TABLE \"main\".\""+tableName+"\";";
		
		MakeRequest(query);
		
	}

	
	//TODO: Need some kind of output formatting function.
}
