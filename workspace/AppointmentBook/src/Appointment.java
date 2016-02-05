import java.util.GregorianCalendar;

import javax.naming.event.EventDirContext;

public class Appointment {
	private GregorianCalendar startDateTime;
	private GregorianCalendar endDateTime;
	private String eventTitle;
	private String description;
	private String location;
	
	public Appointment()
	{
		startDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		endDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		eventTitle = "EmptyAppointment";
		description = "NoDescription";
		location = "NoLocation";
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
	
	public void setEventLocation(String location)
	{
		this.location = location;
	}
	
	public String getEventLocation()
	{
		return location;
	}
	
	public void setEventDescription(String description)
	{
		this.description = description;
	}
	
	public String getEventDescription()
	{
		return description;
	}
	
	
	public String toString()
	{
		return eventTitle+" Starts: "+startDateTime.getTime()+" Finishes: "+endDateTime.getTime();//+" Description: "+description+" Location: "+location;
	}
}
