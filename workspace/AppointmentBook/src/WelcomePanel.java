import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class WelcomePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonSelect;
	private JButton buttonCreateNew;
	private JButton buttonDeleteBook;
	private JLabel welcomeInstructionsLabel;
	private JPanel buttonPanel;
	
	public WelcomePanel(final ArrayList<AppointmentBook> booksList)
	{
		super();
		setName("Welcome Panel");
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome"));
		
		welcomeInstructionsLabel = new JLabel("Please select an existing appointment book or create a new book.");
		welcomeInstructionsLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonSelect = new JButton("Select");
		buttonCreateNew = new JButton("Create");
		buttonDeleteBook = new JButton("Delete");
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		final JPanel cardsPanel = new JPanel();
		
		cardsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		final SelectPanel selectPanel = new SelectPanel(booksList);
		selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.PAGE_AXIS));
		selectPanel.populateCombo();
		
		CreatePanel createPanel = new CreatePanel(booksList);
		createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));
		
		cardsPanel.setLayout(new CardLayout());
		cardsPanel.add(selectPanel, "Select Panel");
		cardsPanel.add(createPanel, "Create Panel");
		
		buttonPanel.add(buttonSelect);
		buttonPanel.add(buttonDeleteBook);
		buttonPanel.add(buttonCreateNew);
		
		buttonSelect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectPanel.populateCombo();
            	CardLayout cl = (CardLayout)cardsPanel.getLayout();
            	cl.show(cardsPanel, "Select Panel");
            }
        });
		
		buttonCreateNew.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	CardLayout cl = (CardLayout)cardsPanel.getLayout(); 
            	cl.show(cardsPanel, "Create Panel");
            }
        });
		
		buttonDeleteBook.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	final SelectPanel selectP = (SelectPanel) cardsPanel.getComponent(0);
            	
            	if(selectP.bookSelectorCombo.getSelectedIndex() == -1)
            		return;
            	
            	DatabaseCommunicator.RemoveAppointmentBookFromDatabase(booksList.get(selectP.bookSelectorCombo.getSelectedIndex()).appointmentBookName);
            	booksList.remove(selectP.bookSelectorCombo.getSelectedIndex());
            	selectP.populateCombo();
            }
        });
		
		
		
		add(welcomeInstructionsLabel);
		add(Box.createVerticalGlue());
		add(cardsPanel);
		add(buttonPanel);
	}
}
