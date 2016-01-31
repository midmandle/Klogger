import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SelectPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> bookSelectorCombo = new JComboBox<String>();
	
	public SelectPanel()
	{
		super();
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel pleaseChoose = new JLabel("Please choose your appointment book:");
		pleaseChoose.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton  buttonChooseBook = new JButton("Open");
		buttonChooseBook.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonChooseBook.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//TODO: Open CalendarPane for selected AppointmentBook
            }
        });
		
		//Damn errors...
		add(pleaseChoose);
		add(bookSelectorCombo);
		add(buttonChooseBook);
	}
	
	public void populateCombo(ArrayList<AppointmentBook> booksList)
	{
		bookSelectorCombo.removeAllItems();
		for(int i = 0; i < booksList.size(); i++)
		{
			//System.out.println(booksList.get(i).appointmentBookName);
			bookSelectorCombo.addItem(booksList.get(i).appointmentBookName);
		}
	}

}
