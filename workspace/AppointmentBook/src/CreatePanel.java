import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class CreatePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreatePanel(ArrayList<AppointmentBook> booksList)
	{
		super();
		
		JLabel pleaseCreate = new JLabel("Please create your appointment book:");
		pleaseCreate.setAlignmentX(CENTER_ALIGNMENT);
		
		AppointmentBook appointmentBook1 = new AppointmentBook("Book1"); //CREATE APPOINTMENT BOOK
		AppointmentBook appointmentBook2 = new AppointmentBook("Book2");
		
		GregorianCalendar startDate = new GregorianCalendar(2016, 1, 1); //CREATE NECESSARY DATES
		GregorianCalendar endDate = new GregorianCalendar(2016, 1, 2);
		
		Appointment appointment = new Appointment(startDate, endDate, "Appointment1"); //ADD TO APPOINTMENT BOOK
		appointmentBook1.add(appointment);
		
		booksList.add(appointmentBook1);
		booksList.add(appointmentBook2);
		
		add(pleaseCreate);
	}
}
