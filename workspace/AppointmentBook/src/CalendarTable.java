import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class CalendarTable {
	
	public CalendarTable(int month, int year, AppointmentBook thisBook)
	{
		int daysInMonth = determineDaysInMonth(month, year);
		determineStartDay(month, year);
		ArrayList<Appointment> thisMonthsAppointments = findAppointmentsForMonth(thisBook, month, year);
		drawTable(daysInMonth, thisMonthsAppointments);
	}
	
	private int determineDaysInMonth(int month, int year)
	{
		Calendar tmpCal = new GregorianCalendar(year, month, 1);
		int noOfDays = tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return noOfDays;
	}
	
	private String determineStartDay(int month, int year)
	{
		String startDay = "";
		
		Calendar tmpCal = new GregorianCalendar(year, month, 1);
		int dayOfWeek = tmpCal.getActualMinimum(Calendar.DAY_OF_WEEK);
		
		switch(dayOfWeek)
		{
			case 0:
			{
				startDay = "Monday";
				break;
			}
			case 1:
			{
				startDay = "Tuesday";
				break;
			}
			case 2:
			{
				startDay = "Wednesday";
				break;
			}
			case 3:
			{
				startDay = "Thursday";
				break;
			}
			case 4:
			{
				startDay = "Friday";
				break;
			}
			case 5:
			{
				startDay = "Saturday";
				break;
			}
			case 6:
			{
				startDay = "Sunday";
				break;
			}
		}
		
		return startDay;
	}
	
	private ArrayList<Appointment> findAppointmentsForMonth(AppointmentBook thisBook, int month, int year)
	{
		ArrayList<Appointment> thisMonthsAppointments = new ArrayList<Appointment>();
		Calendar tmpCal = new GregorianCalendar(year, month, 1);
		Calendar startOfMonth = tmpCal;
		Calendar endOfMonth = new GregorianCalendar(year, month, tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		for(int i = 0; i < thisBook.appointmentList.size(); i++)
		{
			boolean afterOrEqualToStartOfMonth = (thisBook.appointmentList.get(i).getStartDateTime().after(startOfMonth)) || (thisBook.appointmentList.get(i).getStartDateTime().equals(startOfMonth));
			boolean beforeOrEqualToEndOfMonth = (thisBook.appointmentList.get(i).getStartDateTime().before(endOfMonth)) || (thisBook.appointmentList.get(i).getStartDateTime().equals(endOfMonth));
			if((afterOrEqualToStartOfMonth) && (beforeOrEqualToEndOfMonth))
				thisMonthsAppointments.add(thisBook.appointmentList.get(i));
		}
		
		return thisMonthsAppointments;
	}
	
	private void drawTable(int daysInMonth, ArrayList<Appointment> thisMonthsAppointments)
	{
		
	}
}
