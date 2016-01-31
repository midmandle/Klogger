import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<AppointmentBook> booksList = new ArrayList<AppointmentBook>();
	
	
	public MainFrame()
	{
		super("Appointment Book");
		HelperMethods.InitialiseBooksFromDatabase(booksList);
		initialiseUI();
		
	}
	
	private void initialiseUI()
	{	
		createMenuBar();
		
		JTabbedPane jtp = new JTabbedPane();
		
		WelcomePanel welcomePanel = new WelcomePanel(booksList);
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
		jtp.addTab("Welcome", welcomePanel);
		
		setContentPane(jtp);
		
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
}
