import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * SelectPanel adds the GUI and functionality to access currently tracked AppointmentBook objects.
 * @author 14061121
 *
 */
public class SelectPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JComboBox<String> bookSelectorCombo = new JComboBox<String>();
	ArrayList<AppointmentBook> booksList;
	
	/**
	 * Constructor to set up the interface and implement the ActionListener which listens for the "Open" button.
	 * On actionPerformed the contents of a JComboBox are retrieved and used to open up the relevant AppointmentBook in a new Tab.
	 * @param booksList the globally accessible ArrayList of AppointmentBook objects currently being tracked.
	 */
	public SelectPanel(final ArrayList<AppointmentBook> booksList)
	{
		super();
		this.booksList = booksList;
		
		bookSelectorCombo.setMaximumSize(new Dimension(150, 20));
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel pleaseChoose = new JLabel("Please choose your appointment book:");
		pleaseChoose.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton  buttonOpenBook = new JButton("Open");
		buttonOpenBook.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonOpenBook.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Open CalendarPane for selected AppointmentBook
            	JTabbedPane jtp = (JTabbedPane) getParent().getParent().getParent();
            	if(bookSelectorCombo.getSelectedIndex() == -1)
            		return;
            	CalendarPane calPane = new CalendarPane(booksList.get(bookSelectorCombo.getSelectedIndex()));
            	jtp.addTab((String) bookSelectorCombo.getSelectedItem(), calPane);
            	
            }
        });
		
		add(pleaseChoose);
		add(bookSelectorCombo);
		add(buttonOpenBook);
	}
	
	/**
	 * Method to refresh/populate the contents of the JComboBox.
	 */
	public void populateCombo()
	{
		bookSelectorCombo.removeAllItems();
		for(int i = 0; i < booksList.size(); i++)
		{
			System.out.println(booksList.get(i).appointmentBookName);
			bookSelectorCombo.addItem(booksList.get(i).appointmentBookName);
		}
		bookSelectorCombo.revalidate();
	}

}
