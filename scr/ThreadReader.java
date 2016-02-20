import java.util.Random;

class ThreadReader extends Thread
{
	private int number;
	private rQueue q;
	public ThreadReader(int number, rQueue q)
	{
		super("Reader " + number);
		this.number = number;
		this.q = q;
		System.out.println("Thread created: " + this);
		start();
	}
	
	public void run()
	{		
		// run body
		try
		{
			while(true)
			{
				Thread.sleep(random(5000));
				if(q.underWriting())			
					Main._println("Reader " + this.number + " wants to read | Resource is or will be under writing");					
				else
					Main._println("Reader " + this.number + " wants to read | Resource is read by " + q.getReaders() + " readers");
				
				while(!q.join());
				Main._println("Reader " + this.number + " started reading | Resource is read by " + q.getReaders() + " readers");
				Thread.sleep(random(3000));
				Main._println("Reader " + this.number + " ended reading | Resource is read by " + (q.getReaders()-1) + " readers");
				q.leave();
			}
		} catch(InterruptedException e)
			{
				System.out.println(this + " interrupted");
				e.printStackTrace();
			}				
		System.out.println(this + " ended");
	}
	
	public static int random(int randMax)
	{
		Random randomGenerator = new Random();
		return (randomGenerator.nextInt(randMax) + 1);
	}
}