
public class Account {
	private double balance = 0.0;
	
	public Account(double balance)
	{
		if(balance <= 0)
			this.balance = 0;
		else
			this.balance = balance;
	}
	
	public double GetBalance()
	{
		return this.balance;
	}
	
	public double deposit(double value)
	{
		this.balance += value;
		return this.balance;
	}
	
	public double withdraw(double value)
	{
		if(value > this.balance)
			return 0;
		else
			this.balance -= value;
		
		return this.balance;
	}
	
	
}
