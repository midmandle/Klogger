import javax.swing.JLabel;
import javax.swing.JPanel;


public class CreatePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreatePanel()
	{
		super();
		
		JLabel pleaseCreate = new JLabel("Please create your appointment book:");
		pleaseCreate.setAlignmentX(CENTER_ALIGNMENT);
		
		add(pleaseCreate);
	}
}
