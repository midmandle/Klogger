import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JDateChooser;


public class AddEditAppointmentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dayVal = 0;
  	int month = 0;
  	int year = 0;

  	JPanel parent;
  	JLabel infoLabel;
  	JButton returnToCalendarViewButton;
  	Date selectedDate = new Date();
  	final AppointmentBook thisBook;
  	
  	
	public AddEditAppointmentPanel(final AppointmentBook thisBook, final JPanel parent)
	{
		setName("AddEditAppointmentPanel");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.parent = parent;
	  	this.thisBook = thisBook;

		//updateUI(); Call removed as it interferes with cardsLayout.
	}
	
	public void updateUI()
	{
		selectedDate = new GregorianCalendar(year, month, dayVal).getTime();
		
		//InfoLabel
		infoLabel = new JLabel("Please enter the details of the appointment.");
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
		//END
		
		returnToCalendarViewButton = new JButton("Back");
		returnToCalendarViewButton.setAlignmentX(CENTER_ALIGNMENT);
		returnToCalendarViewButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	CalendarPane calPane = (CalendarPane) parent.getParent().getComponent(1);
            	JPanel outerContainer = (JPanel) calPane.getComponent(0);
            	MonthViewPanel mvp = (MonthViewPanel) outerContainer.getComponent(0);
                mvp.updateCalendar();
            	CardLayout cl = (CardLayout)getParent().getLayout();
		    	  cl.show(getParent(), "Calendar View");
            }
        });
		
		
		final JComboBox<String> jcb = new JComboBox<String>();
		jcb.setAlignmentX(CENTER_ALIGNMENT);
		updateAppointmentsJlb(jcb);
		
		//AppointmentDetailsPanel START
		JLabel appointmentTitleLabel = new JLabel("Appointment Title:");
		appointmentTitleLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		final JTextField titleText = new JTextField();
		titleText.setAlignmentX(LEFT_ALIGNMENT);
		titleText.setMaximumSize(new Dimension(150, 20));
		
		JLabel startDateTimeLabel = new JLabel("Start:");
		startDateTimeLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		final JSpinner startTimeSpinner = new JSpinner( new SpinnerDateModel());
		//System.out.println(selectedDate);
		startTimeSpinner.setValue(selectedDate);
		startTimeSpinner.setMaximumSize(new Dimension(150, 20));
		startTimeSpinner.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel endDateTimeLabel = new JLabel("End:");
		endDateTimeLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		final JSpinner endTimeSpinner = new JSpinner( new SpinnerDateModel() );
		endTimeSpinner.setEditor(new JSpinner.DateEditor(endTimeSpinner, "HH.mm"));
		endTimeSpinner.setMaximumSize(new Dimension(150, 20));
		endTimeSpinner.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel appointmentDescriptionLabel = new JLabel("Description:");
		appointmentDescriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		final JTextField descriptionText = new JTextField();
		descriptionText.setAlignmentX(LEFT_ALIGNMENT);
		descriptionText.setMaximumSize(new Dimension(150, 20));
		
		JLabel appointmentLocationLabel = new JLabel("Location:");
		appointmentLocationLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		final JTextField locationText = new JTextField();
		locationText.setAlignmentX(LEFT_ALIGNMENT);
		locationText.setMaximumSize(new Dimension(150, 20));
		
		JButton addAppointmentButton = new JButton("Add");
		addAppointmentButton.setAlignmentX(LEFT_ALIGNMENT);
		addAppointmentButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Date startTimeDate = (Date) startTimeSpinner.getValue();
            	Date endTimeDate = (Date) endTimeSpinner.getValue();
            	Date tmpTimeDate = (Date) startTimeSpinner.getValue();
            	String eventTitle = titleText.getText();
            	String eventDescription = descriptionText.getText();
            	String eventLocation = locationText.getText();
            	
        		Calendar tmpStart = Calendar.getInstance();
        		Calendar tmpEnd = Calendar.getInstance();
        		Calendar tmp = Calendar.getInstance();
        		tmpStart.setTime(startTimeDate);
        		tmpEnd.setTime(endTimeDate);
        		tmp.setTime(tmpTimeDate);
        		
        		tmp.set(Calendar.HOUR_OF_DAY, tmpEnd.get(Calendar.HOUR_OF_DAY));
        		tmp.set(Calendar.MINUTE, tmpEnd.get(Calendar.MINUTE));
        		System.out.println(tmpEnd.getTime());
        		tmpEnd = tmp;
        		//System.out.println(tmpEnd.getTime());
        		if(tmpStart.after(tmpEnd))
        		{
        			//System.out.println(tmpEnd.getTime());
        			System.out.println("Cant end before it began!");
        			return;
        		}
  
        		Appointment tempAppointment = new Appointment((GregorianCalendar)tmpStart, (GregorianCalendar) tmpEnd, eventTitle);
        		if(eventDescription != null)
        			tempAppointment.setEventDescription(eventDescription);
        		if(eventLocation != null)
        			tempAppointment.setEventLocation(eventLocation);
        		//System.out.println(tmpGreg.get(Calendar.HOUR_OF_DAY)+":"+tmpGreg.get(Calendar.MINUTE));
        		int ret = thisBook.add(tempAppointment);
        		if(ret != 0)
        		{
        			if(ret == 1)
        			{
        				System.out.println("ALREADY EXISTS");
        				//DO popup indicating item already exists.
        			}
        			else if(ret == -1)
        			{
        				System.out.println("TIME_CLASH");
        				//Do popup indicating time clash.
        			}
        			else
        				System.out.println("OTHER??");
        			
        		}
        		else
        		{
        			updateAppointmentsJlb(jcb);
        		}
            }
        });
		
		JPanel appointmentDetailsPanel = new JPanel();
		appointmentDetailsPanel.setAlignmentX(LEFT_ALIGNMENT);
		appointmentDetailsPanel.setLayout(new BoxLayout(appointmentDetailsPanel, BoxLayout.PAGE_AXIS));
		appointmentDetailsPanel.add(appointmentTitleLabel);
		appointmentDetailsPanel.add(titleText);
		appointmentDetailsPanel.add(startDateTimeLabel);
		appointmentDetailsPanel.add(startTimeSpinner);
		appointmentDetailsPanel.add(endDateTimeLabel);
		appointmentDetailsPanel.add(endTimeSpinner);
		appointmentDetailsPanel.add(appointmentDescriptionLabel);
		appointmentDetailsPanel.add(descriptionText);
		appointmentDetailsPanel.add(appointmentLocationLabel);
		appointmentDetailsPanel.add(locationText);
		appointmentDetailsPanel.add(addAppointmentButton);
		//AppointmentDetailsPanel END
		
		//ViewEditAppointmentPanel START
		
		JPanel viewEditAppointmentPanel = new JPanel();
		viewEditAppointmentPanel.setLayout(new BoxLayout(viewEditAppointmentPanel, BoxLayout.PAGE_AXIS));
		viewEditAppointmentPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		
		JButton editAppointment = new JButton("Edit");
		editAppointment.setAlignmentX(CENTER_ALIGNMENT);
		editAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Date startTimeDate = (Date) startTimeSpinner.getValue();
            	Date endTimeDate = (Date) endTimeSpinner.getValue();
            	String eventTitle = titleText.getText();
            	String eventDescription = descriptionText.getText();
            	String eventLocation = locationText.getText();
            	
        		Calendar tmpStart = Calendar.getInstance();
        		Calendar tmpEnd = Calendar.getInstance();
        		tmpStart.setTime(startTimeDate);
        		tmpEnd.setTime(endTimeDate);
  
        		Appointment tempAppointment = new Appointment((GregorianCalendar)tmpStart, (GregorianCalendar) tmpEnd, eventTitle);
        		if(eventDescription != null)
        			tempAppointment.setEventDescription(eventDescription);
        		if(eventLocation != null)
        			tempAppointment.setEventLocation(eventLocation);
        		//System.out.println(tmpGreg.get(Calendar.HOUR_OF_DAY)+":"+tmpGreg.get(Calendar.MINUTE));
        		int ret = thisBook.add(tempAppointment);
        		if(ret != 0)
        		{
        			if(ret == 1)
        			{
        				System.out.println("ALREADY EXISTS");
        				//DO popup indicating item already exists are you sure you want to edit.
        			}
        			else if(ret == -1)
        			{
        				System.out.println("TIME_CLASH");
        				//Popup time clash with another item.
        			}
        			else
        				System.out.println("OTHER??");
        			
        		}
        		else
        		{
	        		thisBook.remove(thisBook.appointmentList.get(jcb.getSelectedIndex()));
	        		updateAppointmentsJlb(jcb);
        		}
        		
        		
            }
        });
		
		JButton deleteAppointment = new JButton("Delete");
		deleteAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(thisBook.appointmentList.size() == 0)
            		return;
            	
            	//System.out.println(jcb.getItemAt(jcb.getSelectedIndex()));
            	Appointment tmpAppointment = thisBook.findAndReturn(jcb.getItemAt(jcb.getSelectedIndex()));
            	if(tmpAppointment == null)
            	{
            		System.out.println("INVALID NAME. DB OUT OF SYNC!!!");
            		return; //Invalid appointment name. SHOULDNT HAPPEN
            	}
            	thisBook.remove(tmpAppointment);
        		updateAppointmentsJlb(jcb);
        		
            }
        });
		
		
		viewEditAppointmentPanel.add(jcb);
		viewEditAppointmentPanel.add(editAppointment);
		viewEditAppointmentPanel.add(deleteAppointment);
		
		//ViewEditAppointmentPanel END
		
		//OuterContainer START
		
		JPanel outerContainerPanel = new JPanel(new FlowLayout());
		outerContainerPanel.add(appointmentDetailsPanel);
		outerContainerPanel.add(viewEditAppointmentPanel);
		
		//OuterContainer END
		
		add(infoLabel);
		add(outerContainerPanel);
		add(returnToCalendarViewButton);
	}
	
	private void updateAppointmentsJlb(JComboBox<String> jcb)
	{	
		jcb.removeAllItems();
		
		if(thisBook == null)
			return;
		else
		{
			for(int i = 0; i < thisBook.appointmentList.size(); i++)
			{
				if((thisBook.appointmentList.get(i).getStartDateTime().get(Calendar.YEAR) == this.year))
					if((thisBook.appointmentList.get(i).getStartDateTime().get(Calendar.MONTH) == this.month))
						if((thisBook.appointmentList.get(i).getStartDateTime().get(Calendar.DATE) == dayVal))
							jcb.addItem(thisBook.appointmentList.get(i).toString());
			}
			
		}
		jcb.revalidate();
	}
}
