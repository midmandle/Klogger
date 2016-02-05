import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JTable;


/**
 * Class to provide the bulk of the functionality of the 'grid style' calendar.
 * @author 14061121
 *
 */
public class CalendarTable {
	
	private myTableModel calendarModel;
	JTable jtbl;
	int month, year;
	AppointmentBook thisBook;
	JPanel parent;
	ArrayList<Appointment> thisMonthsAppointments;
	
	/**
	 * Standard constructor. Gets the details of the month to be viewed in the calendar.
	 * @param month the month to be viewed.
	 * @param year the year to be viewed.
	 * @param thisBook the AppointmentBook for which the calendar pertains to.
	 * @param parent the parent of the CalendarTable object. Used to access components in other areas of the component tree. Inefficient and needs re-thinking. Works for now.
	 */
	public CalendarTable(int month, int year, AppointmentBook thisBook, JPanel parent)
	{
		this.month = month;
		this.year = year;
		this.thisBook = thisBook;
		this.parent = parent;
		int daysInMonth = determineDaysInMonth();
		
		thisMonthsAppointments = findAppointmentsForMonth(thisBook);
		drawTable(daysInMonth);//, thisMonthsAppointments);
	}
	
	/**
	 * Method to determine the number of days in the month to be viewed.
	 * @return integer representing the number of days in the month to be viewed.
	 */
	private int determineDaysInMonth()
	{
		GregorianCalendar tmpCal = new GregorianCalendar(year, month, 1);
		int noOfDays = tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return noOfDays;
	}
	
	/**
	 * Method to determine the day of the week which the month starts on.
	 * @return returns a string representation of the day of the week the month starts on.
	 */
	private String determineStartDay()
	{
		String startDay = "";
		
		Calendar tmpCal = new GregorianCalendar(year, month, 1);
		int dayOfWeek = tmpCal.get(Calendar.DAY_OF_WEEK);
		
		switch(dayOfWeek)
		{
			case Calendar.MONDAY:
			{
				startDay = "Monday";
				break;
			}
			case Calendar.TUESDAY:
			{
				startDay = "Tuesday";
				break;
			}
			case Calendar.WEDNESDAY:
			{
				startDay = "Wednesday";
				break;
			}
			case Calendar.THURSDAY:
			{
				startDay = "Thursday";
				break;
			}
			case Calendar.FRIDAY:
			{
				startDay = "Friday";
				break;
			}
			case Calendar.SATURDAY:
			{
				startDay = "Saturday";
				break;
			}
			case Calendar.SUNDAY:
			{
				startDay = "Sunday";
				break;
			}
		}
		
		//System.out.println(startDay);
		return startDay;
	}
	
	/**
	 * Determines if there are Appointment objects which start in the month to be viewed in the calendar.
	 * @param thisBook the AppointmentBook which holds the relevant Appointment objects.
	 * @return an ArrayList of Appointment objects which start in the month to be viewed.
	 */
	private ArrayList<Appointment> findAppointmentsForMonth(AppointmentBook thisBook)
	{
		ArrayList<Appointment> thisMonthsAppointments = new ArrayList<Appointment>();
		Calendar tmpCal = new GregorianCalendar(year, month, 1);
		Calendar startOfMonth = tmpCal;
		Calendar endOfMonth = new GregorianCalendar(year, month, tmpCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//System.out.println(thisBook.appointmentList.size());
		
		for(int i = 0; i < thisBook.appointmentList.size(); i++)
		{
			boolean afterOrEqualToStartOfMonth = (thisBook.appointmentList.get(i).getStartDateTime().after(startOfMonth)) || (thisBook.appointmentList.get(i).getStartDateTime().equals(startOfMonth));
			boolean beforeOrEqualToEndOfMonth = (thisBook.appointmentList.get(i).getStartDateTime().before(endOfMonth)) || (thisBook.appointmentList.get(i).getStartDateTime().equals(endOfMonth));
			if((afterOrEqualToStartOfMonth) && (beforeOrEqualToEndOfMonth))
				thisMonthsAppointments.add(thisBook.appointmentList.get(i));
		}
		
		return thisMonthsAppointments;
	}
	
	/**
	 * Method to draw the JTable to the required size for the viewed month. Implements a MouseListener to act on any cells clicked in the grid and open the relevant day to view.
	 * @param daysInMonth the number of days in the month to be viewed.
	 */
	private void drawTable(int daysInMonth)//, ArrayList<Appointment> thisMonthsAppointments)
	{
		String[] columnNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		
		calendarModel = new myTableModel(columnNames, 6);
		jtbl = new JTable(calendarModel);
		jtbl.setName("jtbl");
		calendarModel.setRowCount(7);
		jtbl.setRowHeight(60);
		
		
		int startpoint = 0;
		if(!(determineStartDay().equalsIgnoreCase("Monday")))
		{
			String day = determineStartDay();
			switch(day)
			{
				case "Monday":
				{
					startpoint = 0;
					break;
				}
				case "Tuesday":
				{
					startpoint = 1;
					break;
				}
				case "Wednesday":
				{
					startpoint = 2;
					break;
				}
				case "Thursday":
				{
					startpoint = 3;
					break;
				}
				case "Friday":
				{
					startpoint = 4;
					break;
				}
				case "Saturday":
				{
					startpoint = 5;
					break;
				}
				case "Sunday":
				{
					startpoint = 6;
					break;
				}
			}
		}	
		
		//System.out.println("Startpoint: "+startpoint);
		
		int k = 0;
		
		for(int i = 0; i < 7; i++)
			for(int j = 0; j <= 6; j++)
				jtbl.setValueAt(null, i, j);
		
		for(int i = startpoint; i < 7; i++)
		{
			k++;
			
			if(hasAppointment(k))
			{
				jtbl.setValueAt(k+"*",  0, i);
			}
			else
				jtbl.setValueAt(k, 0, i);
			
			
		}
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 0; j <= 6; j++)
			{
				k++;
				if(k > daysInMonth)
					break;
				if(hasAppointment(k))
				{
					jtbl.setValueAt(k+"*",  i, j);
				}
				else
					jtbl.setValueAt(k,  i, j);
			}
			if(k > daysInMonth)
				break;
		}
		
		jtbl.addMouseListener(new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) {
		    	
		    	//Java swing magickry to pass back the date selected value to the next view.
		    	Component[] components = parent.getComponents();
				AddEditAppointmentPanel aeavp = null;
			  	for(int i = 0; i < components.length; i++)
			  	{
			  		//System.out.println(components[i].getName());
			  		if(components[i].getName().contentEquals("AddEditAppointmentPanel"))
			  			aeavp = (AddEditAppointmentPanel) components[i];
			  		
			  	}
		    	
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      int column = target.getSelectedColumn();
		      String dayVal = String.valueOf(jtbl.getValueAt(row, column));
		      dayVal = dayVal.replace("*", "");
		      //System.out.println(dayVal);
		      if(dayVal == "null")
		    	  return;
		      else
		      {
		    	  
		    	  aeavp.dayVal = Integer.valueOf(dayVal);
		    	  
		    	  aeavp.month = month;
		    	  
		    	  aeavp.year = year;
		    	  
		    	  aeavp.removeAll();
		    	  aeavp.updateUI();
		    	  
		    	  aeavp.repaint();
		    	  CardLayout cl = (CardLayout)parent.getLayout();
		    	  cl.show(parent, "Add Appointment View");
		      }
		    }	
		  }
		});
		

		
			
		
	}
	
	/**
	 * Method to check if a given day in the grid has any Appointment objects starting on it.
	 * @param k the day in the grid
	 * @return returns true if the day in the grid has an Appointment object associated with it.
	 */
	private boolean hasAppointment(int k)
	{
		
		for(int i = 0; i < thisMonthsAppointments.size(); i++)
		{
			int startDay = thisMonthsAppointments.get(i).getStartDateTime().get(Calendar.DATE);
			if(k == startDay)
				return true;
		}
		return false;
	}
}
