import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.toedter.calendar.JCalendar;


public class CalendarPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AppointmentBook thisBook;
	JCalendar jcal;
	
	
	public CalendarPane(AppointmentBook book)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		thisBook = book;
		jcal = new JCalendar();
		
		JPanel buttonPanel = new JPanel();
		
		JButton buttonAddAppointment = new JButton("Add");
		buttonAddAppointment.setAlignmentX(LEFT_ALIGNMENT);
		JButton buttonViewDate = new JButton("View");
		buttonViewDate.setAlignmentX(RIGHT_ALIGNMENT);
		
		buttonAddAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	CreateAppointmentPopup.display(thisBook, jcal);
            }
        });
		
		buttonViewDate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	ViewDatePopup.display(thisBook, jcal);
            }
        });
		
		buttonPanel.add(buttonAddAppointment);
		buttonPanel.add(buttonViewDate);
		
		JButton buttonExitCalendarPane = new JButton("Close");
		buttonExitCalendarPane.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonExitCalendarPane.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	closeTab();
            }
        });
		
		add(jcal);
		add(buttonPanel);
		add(buttonExitCalendarPane);
		paintCalendar();
	}

	public void closeTab()
	{
		//Do stuff to close this tab
		JTabbedPane jtp = (JTabbedPane)getParent();
		int i = jtp.getSelectedIndex();
		jtp.remove(i);
	}
	
	public void paintCalendar()
	{
		jcal.getDayChooser().getDayPanel().getComponent(35).setBackground(Color.GREEN);
	}
}
