import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				MainFrame f = new MainFrame();
				f.setLayout(new FlowLayout());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(600, 400);
				f.setVisible(true);
				
				AppointmentBook appointmentBook = new AppointmentBook("Book1");
			}
		});
		
	}

}
