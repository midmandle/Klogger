import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MonthViewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonthViewPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		//MonthForward
		JButton monthForward = new JButton(">>");
		monthForward.setAlignmentX(RIGHT_ALIGNMENT);
		//END
		
		//MonthBackwards
		JButton monthBackwards = new JButton("<<");
		monthBackwards.setAlignmentX(LEFT_ALIGNMENT);
		//END
		
		//CalendarOptions
		JPanel calendarOptions = new JPanel();
		calendarOptions.add(monthBackwards);
		calendarOptions.add(monthForward);
		//END
		
		add(calendarOptions);
	}
}
