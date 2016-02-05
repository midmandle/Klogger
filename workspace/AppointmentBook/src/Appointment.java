import java.util.GregorianCalendar;

/**
 * Base class for implementing an Appointment structure. This is the individual
 * appointment.
 * 
 * @author 14061121
 *
 */

public class Appointment {
	
	private GregorianCalendar startDateTime; 
	private GregorianCalendar endDateTime;
	private String eventTitle;
	private String description;
	private String location;
	
	/**
	 *  Standard constructor. Creates an empty Appointment object starting and finishing 1/1/1970.
	 */
	public Appointment()
	{
		startDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		endDateTime = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
		eventTitle = "EmptyAppointment";
		description = "NoDescription";
		location = "NoLocation";
	}
	
	/**
	 * Constructor for initialising the required not null variables of an Appointment object.
	 * @param start The start date/time for the appointment.
	 * @param end The end date/time for the appointment.
	 * @param title The title of the appointment.
	 */
	public Appointment(GregorianCalendar start, GregorianCalendar end, String title)
	{
		startDateTime = start;
		endDateTime = end;
		eventTitle = title;
	}
	
	/**
	 * GET method for the protection of the startDateTime private variable.
	 * @return startDateTime
	 */
	public GregorianCalendar getStartDateTime()
	{
		return startDateTime;
	}
	
	/**
	 * SET method for the protection of the startDateTime private variable.
	 * @param dateTime The desired dateTime value to be set to.
	 */
	public void setStartDateTime(GregorianCalendar dateTime)
	{
		startDateTime = dateTime;
	}
	
	/**
	 * GET method for the protection of the endDateTime private variable.
	 * @return endDateTime
	 */
	public GregorianCalendar getEndDateTime()
	{
		return endDateTime;
	}
	
	/**
	 * SET method for the protection of the endDateTime private variable.
	 * @param dateTime The desired dateTime value to be set to.
	 */
	public void setEndDateTime(GregorianCalendar dateTime)
	{
		endDateTime = dateTime;
	}
	
	/**
	 * GET method for the protection of the eventTitle private variable.
	 * @return eventTitle
	 */
	public String getEventTitle()
	{
		return eventTitle;
	}
	
	/**
	 * SET method for the protection of the eventTitle private variable.
	 * @param title The desired eventTitle value to be set to.
	 */
	public void setEventTitle(String title)
	{
		eventTitle = title;
	}
	
	/**
	 * SET method for the protection of the location private variable.
	 * @param location The desired location value to be set to.
	 */
	public void setEventLocation(String location)
	{
		this.location = location;
	}
	
	/**
	 * GET method for the protection of the location private variable.
	 * @return location 
	 */
	public String getEventLocation()
	{
		return location;
	}
	
	/**
	 * SET method for the protection of the location private variable.
	 * @param description The desired description value to be set to.
	 */
	public void setEventDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * GET method for the protection of the description private variable.
	 * @return description
	 */
	public String getEventDescription()
	{
		return description;
	}
	
	/**
	 * The toString method returns a String representation of the Appointmnet.
	 */
	public String toString()
	{
		return eventTitle+" Starts: "+startDateTime.getTime()+" Finishes: "+endDateTime.getTime();//+" Description: "+description+" Location: "+location;
	}
}
