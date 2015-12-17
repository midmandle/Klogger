import java.io.*;
/**
 * Author: Dr. Andy Nisbet
 * Last Modified:  7/10/07
 * Now creates a variable number of threads, answer calculated depends on the number of threads 
 * ie it changes the length of the initial array that is shared between threads

 * Purpose: Teaching Resources for Elementary Thread Programming
 * Test is a class that extends Thread. The work of a for loop is to be shared across
 * a number of different threads.
 * The state information includes: 
 * <ul>
 * <li> The logical thread number.
 * <li> The lowerbound of the for loop.
 * <li> The upperbound of the for loop.
 * <li> An object reference to Data that stores the information used in the calculation.
 * </ul>
*/
   

class Test extends Thread {
	private int lowerBound; // lower bound of the for loop
	private int upperBound; // upper bound of the for loop
	private int threadNo;   // logical thread id
	private Data obj; // a shared object reference
	Test(int id, int lb, int ub,Data o) {
		super(); // as we extend a class, we need to call its constructor
		threadNo = id;
		obj = o;
		lowerBound = lb;
		upperBound = ub;
	}
	public void run() {
		int sum = 0;
		/* Essentially the method merely sums up a portion of the
		 * iteration space of the original (see Data class and sum method) 
		 * for loop. The idea is that each thread will perform a portion
		 * of the work
		 */
		for(int i = lowerBound; i < upperBound;i++)	{
			sum = sum + obj.A[i];
		}
		obj.partialAnswers[threadNo] = sum; // store the partial answer
	}
};

class Data {
	protected int A[];
	protected int partialAnswers[];
	private Test threads[];
	Data(int size) {
		A = new int[size];
		for(int i =0 ; i < size;i++)	{
			A[i] = i; // initialise the contents of A
				    // the actual values arent important
		}
	}
	public int sum() {
		int value = 0;
		for(int i = 0; i < A.length; i++) {
			value = value + A[i];
			//System.out.println(value);
		}
		return value;
	}
public static void main(String args[])	{
	int totalThreads = 0;
	String input = null;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	while(totalThreads == 0) {
		try {
			System.out.println("Enter the number of threads !!!");
			input = reader.readLine();
			System.out.println(input);
			if(input.compareTo(new String("")) == 0) continue;
			if(input != null) totalThreads  = Integer.parseInt(input);
			if(totalThreads < 0) {
				System.err.println(" A positive number of Total Threads  PLEASE!");
				totalThreads = 0;
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			continue;
		}
	}
	System.out.println("Total threads " + totalThreads);
	Data x = new Data(totalThreads*128); // create Data object
	System.out.println("CORRECT ANSWER OUTPUT " + x.sum());
	x.partialAnswers = new int[totalThreads];


	/* This section of code calculates the low and high portions of the 
	Data array that will be accessed by the thread and then creates the
	thread and starts it --- Why must we pass in object x to the constructor */
	for(int i = 0; i < totalThreads;i++)	{
		int lb,ub;
		lb = (int)(i * (128.0*totalThreads/totalThreads));
		ub = (int)((i+1) * (128.0*totalThreads/totalThreads));
		if(ub > 128*totalThreads) ub = 128*totalThreads;
		if(i == (totalThreads -1)) ub =128*totalThreads;
		Test tobj = new Test(i,lb,ub,x);
		tobj.start();
	}
	// UNCOMMENT this LINE
	while(Thread.activeCount() != 1); // synchronisation
	// UNCOMMENT this LINE
	int sanity = 0;
	for(int i = 0; i < totalThreads;i++)	{
		sanity = sanity + x.partialAnswers[i];
	}
	System.out.println("Im only sane if this is right " +  sanity);
	System.out.println("Try googling the term what is a race condition with thread programming");
	System.out.println("Try to work out why this program has a race condition");
	System.out.println("HINTS: If the number did not match try uncommenting the line delimited by ... UNCOMMENT ");
	
}
};

