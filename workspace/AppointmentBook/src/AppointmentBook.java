import java.util.ArrayList;
import java.util.Calendar;

public class AppointmentBook {
	int NOTFOUND = 0;
	int FOUND = 1;
	
	String appointmentBookName;
	
	ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public AppointmentBook(String appointmentBookName)
	{
		this.appointmentBookName = appointmentBookName;
		if(!DatabaseCommunicator.CheckIfAppointmentBookExistsOnDatabase(appointmentBookName));
			DatabaseCommunicator.SetupNewAppointmentBookForDatabase(appointmentBookName);
	}
	
	public void add(Appointment newAppointment)
	{
		if(isInBook(newAppointment))
			return; //TODO: Throw exception: "ITEM ALREADY EXISTS"
		else
		{
			appointmentList.add(newAppointment);
			
			DatabaseCommunicator.AddAppointmentToDatabase(appointmentBookName, newAppointment);
		}
	}
	
	public ArrayList<Appointment> getAllAppointments()
	{
		return appointmentList;
	}
	
	public void showAllAppointments()
	{
		System.out.println(appointmentList.size()+" appointments:");
		for(int i = 0; i < appointmentList.size(); i++)
		{		
			int startDay = 0;
			int startMonth = 0;
			int startYear = 0;
			
			int endDay = 0;
			int endMonth = 0;
			int endYear = 0;
			
			String title = null;
			
			startDay = appointmentList.get(i).getStartDateTime().get(Calendar.DAY_OF_MONTH);
			startMonth = appointmentList.get(i).getStartDateTime().get(Calendar.MONTH);
			startYear = appointmentList.get(i).getStartDateTime().get(Calendar.YEAR);
			
			endDay = appointmentList.get(i).getEndDateTime().get(Calendar.DAY_OF_MONTH);
			endMonth = appointmentList.get(i).getEndDateTime().get(Calendar.MONTH);
			endYear = appointmentList.get(i).getEndDateTime().get(Calendar.YEAR);
			
			title = appointmentList.get(i).getEventTitle();
			
			String output = String.format("%s Starts: %d/%d/%d Ends: %d/%d/%d", title, startDay, startMonth, startYear, endDay, endMonth, endYear);
			
			System.out.println(output);
			
		}
			
	}
	
	public int find(Appointment appointmentToFind)
	{
		for(int i = 0; i < appointmentList.size(); i++)
			if(appointmentList.get(i).getEventTitle() == appointmentToFind.getEventTitle())
				return FOUND;
		return NOTFOUND;
	}
	
	public void remove(Appointment appointmentToRemove)
	{
		if(isInBook(appointmentToRemove))
			appointmentList.remove(appointmentToRemove);
		else
			return;//TODO: Throw exception: "ITEM DOESNT EXIST"
	}
	
	public boolean isInBook(Appointment appointmentToCheck)
	{
		if(find(appointmentToCheck) > 0)
			return true;
		return false;
	}
	
	public void saveAppointmentsToDatabase()
	{
		
	}
}
