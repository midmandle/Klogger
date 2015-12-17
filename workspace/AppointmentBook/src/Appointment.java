import java.util.GregorianCalendar;


public class Appointment {

	private String appointmentName;
	private GregorianCalendar appointmentDateTime = new GregorianCalendar();
	
	public Appointment(String name)
	{
		appointmentName = name;
		
		appointmentDateTime.set(0, 0, 0, 0, 0); //null value appointment.
	}
	
	public Appointment(String name, int day, int month, int year)
	{
		appointmentName = name;
		
		appointmentDateTime.set(year, month, day); //null time
	}
	
	public Appointment(String name, int day, int month, int year, int hour, int minute)
	{
		appointmentName = name;
		
		appointmentDateTime.set(year, month, day, hour, minute);
	}
	
	public Appointment(String name, String location, int day, int month, int year)
	{
		appointmentName = name;
		
		appointmentDateTime.set(year, month, day);
	}
	
	public void setAppointmentName(String name)
	{
		this.appointmentName = name;
	}
	
	public String getAppointmentName()
	{
		return this.appointmentName;
	}
	
	public GregorianCalendar getDate()
	{
		
		return this.appointmentDateTime;
	}
}
