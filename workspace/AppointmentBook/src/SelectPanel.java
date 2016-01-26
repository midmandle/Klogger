import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SelectPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectPanel()
	{
		super();
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel pleaseChoose = new JLabel("Please choose your appointment book:");
		pleaseChoose.setAlignmentX(CENTER_ALIGNMENT);
		
		add(pleaseChoose);
	}

}
