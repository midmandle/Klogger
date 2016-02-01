import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public CalendarPane(AppointmentBook book)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		thisBook = book;
		JCalendar jcal = new JCalendar();
		
		JPanel buttonPanel = new JPanel();
		
		JButton buttonAddAppointment = new JButton("Add");
		buttonAddAppointment.setAlignmentX(LEFT_ALIGNMENT);
		JButton buttonViewDate = new JButton("View");
		buttonViewDate.setAlignmentX(RIGHT_ALIGNMENT);
		
		buttonAddAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	CreateAppointmentPopup.display(thisBook);
            }
        });
		
		buttonViewDate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	ViewDatePopup.display(thisBook);
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
	}

	public void closeTab()
	{
		//Do stuff to close tab
		JTabbedPane jtp = (JTabbedPane)getParent();
		int i = jtp.getSelectedIndex();
		jtp.remove(i);
	}
}
