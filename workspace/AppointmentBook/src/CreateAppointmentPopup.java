import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;


public class CreateAppointmentPopup {

	public static void display(AppointmentBook appointmentBook, JCalendar jcal)
	{
		JPanel panel = new JPanel();
		
		
		int result = JOptionPane.showConfirmDialog(null, panel ,"Add Appointment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(result == JOptionPane.OK_OPTION)
		{
			System.out.println(jcal.getDayChooser().getDay()+" "+jcal.getMonthChooser().getMonth()+" "+jcal.getYearChooser().getYear());
		}
		else
		{
			System.out.println("Cancelled");
		}
	}
}
