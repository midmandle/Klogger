import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;


public class CalendarPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AppointmentBook thisBook;
	
	public CalendarPane(AppointmentBook book)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		thisBook = book;
		JCalendar jcal = new JCalendar();
		
		add(jcal);
	}

	public void closeTab()
	{
		
	}
}
