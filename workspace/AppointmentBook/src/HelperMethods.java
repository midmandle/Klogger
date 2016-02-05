import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class HelperMethods {
	
	//Save current appointmentBooks as CSV
	
	public static void SaveBooksToCSV(String location, AppointmentBook appointmentBook)
	{
		try
		{
			File file = new File(location);
			FileWriter writer = new FileWriter(file);
			
			for(int i = 0; i < appointmentBook.appointmentList.size(); i++)
			{
				writer.append(appointmentBook.appointmentList.get(i).getEventTitle());
				writer.append(',');
				writer.append((CharSequence) appointmentBook.appointmentList.get(i).getStartDateTime().getTime().toString());
				writer.append(',');
				writer.append((CharSequence) appointmentBook.appointmentList.get(i).getEndDateTime().getTime().toString());
				writer.append(',');
				writer.append(appointmentBook.appointmentList.get(i).getEventDescription());
				writer.append(',');
				writer.append(appointmentBook.appointmentList.get(i).getEventLocation());
				writer.append(',');
				writer.append('\n');
			}
			
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Save current appointmentBooks as XML
	
	
	//Fetch current appointmentBooks from CSV
	
	public static void FetchBooksFromCSV(String location, ArrayList<AppointmentBook> booksList)
	{
		File file = new File(location);
		BufferedReader br = null;
		String line = "";
		String splitOn = ",";
		
		String filename;
		int i = location.lastIndexOf("/");
		
		filename = location.substring(i+1, location.length());
		
		System.out.println(filename);
		
		AppointmentBook appointmentBook = new AppointmentBook(filename);
		booksList.add(appointmentBook);
		
		try
		{
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null)
			{
				String[] appointment = line.split(splitOn);
				Appointment appointmentAdder = new Appointment();
				
				DateFormat df = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
				Date dStart = null;
				Date dFinish = null;
				try {
					dStart = df.parse(appointment[1]);
					dFinish = df.parse(appointment[2]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(dStart);
				GregorianCalendar gregStart = (GregorianCalendar) cal;
				cal.setTime(dFinish);
				GregorianCalendar gregFinish = (GregorianCalendar) cal;
				
				appointmentAdder.setEventTitle(appointment[0]);
				appointmentAdder.setStartDateTime(gregStart);
				appointmentAdder.setEndDateTime(gregFinish);
				if(appointment.length > 3)
				{
					appointmentAdder.setEventDescription(appointment[3]);
					if(appointment.length > 4)
						appointmentAdder.setEventLocation(appointment[4]);
				}
				
				
				appointmentBook.add(appointmentAdder);
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(br != null)
			{
				try{
					br.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	//Fetch current appointmentBooks from XML
	
	
	//Fetch current Books from Database;
	public static void InitialiseBooksFromDatabase(ArrayList<AppointmentBook> booksList)
	{
		ArrayList<String> bookNames = new ArrayList<String>();
		DatabaseCommunicator.GetAllAppointmentBooks(bookNames);
		
		for(int i = 0; i < bookNames.size(); i++)
		{
			booksList.add(new AppointmentBook(bookNames.get(i)));
		}
		
		for(int i = 0; i < booksList.size(); i++)
		{
			booksList.get(i).intialiseAppointmentBookFromDatabase();
		}
	}
	
	//Whatever the current state of the AppointmentBook selected from the comboBox on the Welcome tab it will be saved in that form to the DB.
	public static void ForceSaveToDB(AppointmentBook book)
	{
		DatabaseCommunicator.RemoveAppointmentBookFromDatabase(book.appointmentBookName);
		DatabaseCommunicator.SetupNewAppointmentBookForDatabase(book.appointmentBookName);
		book.saveAppointmentsToDatabase();
	}
}
