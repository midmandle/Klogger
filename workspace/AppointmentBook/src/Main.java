public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				MainFrame f = new MainFrame();
				
				/*AppointmentBook appointmentBook1 = new AppointmentBook("Book1"); //CREATE APPOINTMENT BOOK
				AppointmentBook appointmentBook2 = new AppointmentBook("Book2");
				
				GregorianCalendar startDate = new GregorianCalendar(2016, 1, 1); //CREATE NECESSARY DATES
				GregorianCalendar endDate = new GregorianCalendar(2016, 1, 2);
				
				Appointment appointment = new Appointment(startDate, endDate, "Appointment1"); //ADD TO APPOINTMENT BOOK
				appointmentBook1.add(appointment);
				
				//------------------THE ABOVE NEEDS TO BE DONE IN THIS ORDER------------------------------------------------
				
				//ppointmentBook1.showAllAppointments();
				
				GregorianCalendar startDate2 = new GregorianCalendar(2016, 1, 3);
				GregorianCalendar endDate2 = new GregorianCalendar(2016, 1, 4);
				Appointment appointment2 = new Appointment(startDate2, endDate2, "Appointment2");
				appointmentBook2.add(appointment2);
				
				AppointmentBook appointmentBook3 = new AppointmentBook("Book3");
				appointmentBook3.appointmentList = DatabaseCommunicator.GetAllAppointmentsFromDatabase("Book1");
				appointmentBook3.saveAppointmentsToDatabase();*/
				
			}
		});
		
	}

}
