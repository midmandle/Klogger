import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ViewDatePopup {
	
	public static void display(AppointmentBook appointmentBook)
	{
		JPanel panel = new JPanel();
		
		
		int result = JOptionPane.showConfirmDialog(null, panel ,"View Date", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if(result == JOptionPane.OK_OPTION)
		{
			
		}
		else
		{
			System.out.println("Cancelled");
		}
	}
}
