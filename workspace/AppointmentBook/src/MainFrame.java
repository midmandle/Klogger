import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<AppointmentBook> booksList = new ArrayList<AppointmentBook>();
	JTabbedPane jtp = new JTabbedPane();
	
	public MainFrame()
	{
		super("Appointment Book");
		HelperMethods.InitialiseBooksFromDatabase(booksList);
		initialiseUI();
		//HelperMethods.SaveBooksToCSV("csv.csv", booksList.get(0));
		//HelperMethods.FetchBooksFromCSV("csv.csv", booksList.get(0));
		
	}
	
	private void initialiseUI()
	{	
		

		WelcomePanel welcomePanel = new WelcomePanel(booksList);
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
		jtp.addTab("Welcome", welcomePanel);
		setContentPane(jtp);
		
		createMenuBar();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setVisible(true);
	}
	
	private void createMenuBar()
	{
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem saveToCSV = new JMenuItem("Save as .CSV");
		JMenuItem loadFromCSV = new JMenuItem("Load from .CSV");
		JMenuItem forceSaveToDB = new JMenuItem("FORCE Save to DB");
		
		WelcomePanel welcomeP = (WelcomePanel) jtp.getComponent(0);
    	JPanel cardsP = (JPanel) welcomeP.getComponent(2);
    	final SelectPanel selectP = (SelectPanel) cardsP.getComponent(0);
		
		saveToCSV.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV FILES", "csv");
            	fileChooser.setFileFilter(fileFilter);
            	int retVal = fileChooser.showSaveDialog(selectP);
            	
            	 if (retVal == JFileChooser.APPROVE_OPTION) {
                     File file = fileChooser.getSelectedFile();
                     int confirm = JOptionPane.showConfirmDialog(selectP, "Saving "+ selectP.bookSelectorCombo.getSelectedItem()+" to "+file.getAbsolutePath());
                     if(confirm == JOptionPane.OK_OPTION)
                    	 HelperMethods.SaveBooksToCSV(file.getAbsolutePath(), booksList.get(selectP.bookSelectorCombo.getSelectedIndex()));
                     else
                    	 return;
            	 }
            }
        });
	
		loadFromCSV.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
            	FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CSV FILES", "csv");
            	fileChooser.setFileFilter(fileFilter);
            	
            	int retVal = fileChooser.showOpenDialog(selectP);
            	
	           	if (retVal == JFileChooser.APPROVE_OPTION) {
	                   File file = fileChooser.getSelectedFile();
	                   int confirm = JOptionPane.showConfirmDialog(selectP, "Loading "+ selectP.bookSelectorCombo.getSelectedItem()+" from "+file.getAbsolutePath());
	                   if(confirm == JOptionPane.OK_OPTION)
	                   {
	                	   HelperMethods.FetchBooksFromCSV(file.getAbsolutePath(), booksList);
	                	   selectP.populateCombo();
	                   }
	                   else
	                	   return;
	           	}
            }
        });
		
		forceSaveToDB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	HelperMethods.ForceSaveToDB(booksList.get(selectP.bookSelectorCombo.getSelectedIndex()));
            }
        });
		
		file.add(saveToCSV);
		file.add(loadFromCSV);
		file.add(forceSaveToDB);
		menu.add(file);
		setJMenuBar(menu);
	}
}
