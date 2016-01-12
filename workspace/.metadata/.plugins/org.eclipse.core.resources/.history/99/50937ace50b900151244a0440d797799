import java.util.GregorianCalendar;

public class Appointment {
	GregorianCalendar startDateTime;
	GregorianCalendar endDateTime;
	String eventTitle;
	
	public Appointment()
	{
		startDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		endDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		eventTitle = "EmptyAppointment";
	}
	
	public Appointment(GregorianCalendar start, GregorianCalendar end, String title)
	{
		startDateTime = start;
		endDateTime = end;
		eventTitle = title;
	}
	
	public GregorianCalendar getStartDateTime()
	{
		return startDateTime;
	}
	
	public void setStartDateTime(GregorianCalendar dateTime)
	{
		startDateTime = dateTime;
	}
	
	public GregorianCalendar getEndDateTime()
	{
		return endDateTime;
	}
	
	public void setEndDateTime(GregorianCalendar dateTime)
	{
		endDateTime = dateTime;
	}
	
	public String getEventTitle()
	{
		return eventTitle;
	}
	
	public void setEventTitle(String title)
	{
		eventTitle = title;
	}
	
	public String toString()
	{
		return eventTitle+" Starts: "+startDateTime+" Finishes: "+endDateTime;
	}
}
