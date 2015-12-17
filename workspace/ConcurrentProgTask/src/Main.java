
public class Main {

	public static void main(String[] args) {
		if(args.length != 2)
		{
			System.out.println("ERROR: You need to provide a numberOfCardHolders and a startingBalance. Please restart the program.");
			return;
		}

		//Java ConcurrentProgTask noOfAccountHolders balance
		
		int noOfCardHolders = Integer.parseInt(args[0]);
		double startingBalance = Integer.parseInt(args[1]);
		
		Account account = new Account(startingBalance);
		
		System.out.println(account.GetBalance());
		System.out.println("Depositing 10. Balance: "+account.deposit(10));
		System.out.println("Withdrawing 20. Balance: "+account.withdraw(20));
		
		
		for(int i = 0; i < noOfCardHolders; i++)
		{
			CardHolder card = new CardHolder(500, i, account);
			card.start();
		}
		
		System.out.println("Final balance: "+account.GetBalance());
	}

}
