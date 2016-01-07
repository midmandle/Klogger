import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				MainFrame f = new MainFrame();
				f.setLayout(new FlowLayout());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(600, 400);
				f.setVisible(true);
				
				//DatabaseCommunicator dbComms = new DatabaseCommunicator();
				try {
					ResultSet res = null;
					//res = DatabaseCommunicator.MakeRequest("INSERT INTO testTable (a, b) VALUES (80, 4);");
					res = DatabaseCommunicator.MakeRequest("SELECT * FROM testTable;");
					while(res.next())//TODO: BUG: At this point res seems to still be null
					{
						System.out.println("LOL"); //Never executes...
						System.out.println(res.absolute(0));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

}
