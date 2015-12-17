
public class CardHolder extends Thread {
	private double localBalance = 0;
	private Account account;
	
	public CardHolder(double localBalance, Account account)
	{
		if(localBalance <= 0)
			this.localBalance = 0;
		else
			this.localBalance = localBalance;
		
		this.account = account;
	}
	
	public CardHolder(double localBalance, int threadID, Account account)
	{
		super(Integer.toString(threadID));
		if(localBalance <= 0)
			this.localBalance = 0;
		else
			this.localBalance = localBalance;
		
		this.account = account;
	}
	
	public double GetLocalBalance()
	{
		return this.localBalance;
	}
	
	public void SetLocalBalance(double value)
	{
		this.localBalance = value;
	}
	
	public void run()
	{
		//System.out.println(this.getName());
		
		//System.out.println("Depositing 10. Balance: "+account.deposit(10));
		//System.out.println("Withdrawing 10. Balance: "+account.withdraw(10));
		double sumRemoved = 0;
		for(int i = 0; i < 20; i++)
		{
			if(Math.random() > 0.5)
			{
				double amount = (int)(Math.random() * 10);
				//account.withdraw(Math.random() * 10);
				
				System.out.println("THREAD "+this.getName()+" WITHDRAWING: " + amount);
				account.withdraw(amount);
				sumRemoved += amount;
			}
			else
			{
				double amount = (int)(Math.random() * 10);
				//account.deposit(Math.random() * 10);
				
				System.out.println("THREAD "+this.getName()+" DEPOSITING: " + amount);
				account.deposit(amount);
				sumRemoved -= amount;
			}
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("THREAD COMPLETED: "+this.getName()+" SUMREMOVED: "+sumRemoved);
		
		System.out.println("THREAD COMPLETED: "+this.getName()+" Account Balance: "+account.GetBalance());
	}
}
