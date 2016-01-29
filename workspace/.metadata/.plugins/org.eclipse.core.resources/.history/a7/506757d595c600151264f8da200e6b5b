import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainFrame()
	{
		super("Appointment Book");
		initialiseUI();
	}
	
	private void initialiseUI()
	{	
		createMenuBar();
		createOpeningPanel();
		pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setVisible(true);
	}
	
	private void createMenuBar()
	{
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		
		menu.add(file);
		setJMenuBar(menu);
	}
	
	private void createOpeningPanel()
	{
		
		
		WelcomePanel welcomePanel = new WelcomePanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
		
		
		setContentPane(welcomePanel);
	}
}
