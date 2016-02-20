import java.util.Random;

class ThreadWriter extends Thread
{
	private int number;
	private wQueue q;
	public ThreadWriter(int number, wQueue q)
	{
		super("Writer " + number);
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
					Main._println("Writer " + this.number + " wants to write | Resource is or will be under writing");
				else
					Main._println("Writer " + this.number + " wants to write | Resource is read by " + q.getReaders() + " readers");
						
				q.writerComing();
				while(!q.join());
				Main._println("Writer " + this.number + " started writing |  Resource is under writing");
				Thread.sleep(random(3000));
				Main._println("Writer " + this.number + " ended writing | Resource is free");
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