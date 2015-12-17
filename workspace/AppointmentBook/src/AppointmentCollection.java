import java.util.ArrayList;


public class AppointmentCollection {
	ArrayList<Appointment> appointmentBook = new ArrayList<Appointment>();
	
	String appointmentBookName;
	String appointmentBookOwner;
	
	public AppointmentCollection()
	{
		appointmentBookName = "Default";
		appointmentBookOwner = "Default";
	}
	
	public AppointmentCollection(String name)
	{
		appointmentBookName = name;
		appointmentBookOwner = "Default";
	}
	
	public AppointmentCollection(String name, String owner)
	{
		appointmentBookName = name;
		appointmentBookOwner = owner;
	}
	
	public void addAppointment(Appointment appointment)
	{
		appointmentBook.add(appointment);
	}
	
	/*public String getAppointmentByName()
	{
		return "TODO";
	}
	
	public ArrayList<Appointment> getAppointmentByDate()
	{
		
	}*/
	
	public int removeAppointment(Appointment appointment)
	{
		if(appointmentBook.contains(appointment))
		{
			appointmentBook.remove(appointment);
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	public int alterAppointment(Appointment appointment)
	{
		if(appointmentBook.contains(appointment))
		{
			return 0;
		}
		else
			return -1;
	}
}
