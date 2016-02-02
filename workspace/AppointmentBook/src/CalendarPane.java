import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.toedter.calendar.JCalendar;


public class CalendarPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AppointmentBook thisBook;
	//JCalendar jcal;
	GregorianCalendar todayDate = new GregorianCalendar();
	
	
	public CalendarPane(AppointmentBook thisBook)
	{
		drawCalendar();
		
		JButton buttonExitCalendarPane = new JButton("Close");
		buttonExitCalendarPane.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonExitCalendarPane.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	closeTab();
            }
        });
		
		add(buttonExitCalendarPane);
	}
	
	public void drawCalendar()
	{
		JTable table = new JTable(5, 7);
		
		table.setRowHeight(50);
		int k = 0;
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 7; j++)
			{
				k++;
				table.setValueAt(k, i, j);
				if(k == todayDate.get(Calendar.DATE));
					
			}
				
		add(table);
	}
	
	/*public CalendarPane(AppointmentBook book)
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
	}*/

	public void closeTab()
	{
		//Do stuff to close this tab
		JTabbedPane jtp = (JTabbedPane)getParent();
		int i = jtp.getSelectedIndex();
		jtp.remove(i);
	}
	
	/*public void paintCalendar()
	{
		jcal.getDayChooser().getDayPanel().getComponent(7).setBackground(Color.GREEN);
		
		System.out.println(jcal.getDayChooser().getDayPanel().getComponentCount());
		
		int gridPlaces = jcal.getDayChooser().getDayPanel().getComponentCount();
		
		for(int i = 0; i < gridPlaces; i++)
		{
			if(i%2 == 0)
			{
				System.out.println(jcal.getDayChooser().getDay(i));
				jcal.getDayChooser().getDayPanel().getComponent(i).setBackground(Color.GREEN);
			}
			else
			{
				jcal.getDayChooser().getDayPanel().getComponent(i).setBackground(Color.BLUE);
			}
		}
	}*/
}
