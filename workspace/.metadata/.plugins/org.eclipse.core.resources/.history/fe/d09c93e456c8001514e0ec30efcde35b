import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class HelperMethods {
	
	//Save current appointmentBooks as CSV
	
	public static void SaveBooksToCSV(String location, ArrayList<AppointmentBook> appointmentBooks)
	{
		try
		{
			File file = new File(location);
			FileWriter writer = new FileWriter(file);
			
			for(int i = 0; i < appointmentBooks.size(); i++)
			{
				writer.append(appointmentBooks.get(i).appointmentBookName);
				writer.append(',');
				writer.append(String.valueOf(appointmentBooks.get(i).appointmentList.size()));
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
	
	public static void FetchBooksFromCSV(String location, ArrayList<AppointmentBook> appointmentBooks)
	{
		File file = new File(location);
		BufferedReader br = null;
		String line = "";
		String splitOn = ",";
		
		try
		{
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null)
			{
				String[] books = line.split(splitOn);
				
				appointmentBooks.add(new AppointmentBook(books[0]));
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
		DatabaseCommunicator.GetAllAppointmentBooks(booksList);
		
		for(int i = 0; i < booksList.size(); i++)
		{
			booksList.get(i).intialiseAppointmentBookFromDatabase();
		}
	}
}
