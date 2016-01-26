import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
	private JLabel welcomeInstructionsLabel;
	private JPanel buttonPanel;
	
	public WelcomePanel()
	{
		super();
		setName("Welcome Panel");
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Welcome"));
		
		welcomeInstructionsLabel = new JLabel("Please select an existing appointment book or create a new book.");
		welcomeInstructionsLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonSelect = new JButton("Select");
		buttonCreateNew = new JButton("Create");
		
		buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

		buttonPanel.add(buttonSelect);
		buttonPanel.add(buttonCreateNew);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		SelectPanel selectPanel = new SelectPanel();
		selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.PAGE_AXIS));
		
		CreatePanel createPanel = new CreatePanel();
		createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));
		
		cardPanel.setLayout(new CardLayout());
		cardPanel.add(selectPanel, "Select Panel");
		cardPanel.add(createPanel, "CreatePanel");
		
		
		add(welcomeInstructionsLabel);
		add(Box.createVerticalGlue());
		add(cardPanel);
		add(buttonPanel);
	}
}