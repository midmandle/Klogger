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


public class SelectPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> bookSelectorCombo = new JComboBox<String>();
	ArrayList<AppointmentBook> booksList;
	
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
