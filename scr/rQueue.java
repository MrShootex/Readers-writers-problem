class rQueue
{
	private boolean underWriting;
	private int maxReaders;
	private int curReaders;
	
	public rQueue(int maxReaders)
	{
		this.underWriting = false;
		this.maxReaders = maxReaders;
		this.curReaders = 0;
	}
	
	synchronized public boolean underWriting()
	{
		return this.underWriting;
	}
	
	public void setUnderWriting(boolean underWriting)
	{
		this.underWriting = underWriting;
	}
	
	synchronized public boolean join()
	{
		if(this.underWriting || this.curReaders == this.maxReaders)
			return false;
		else
		{
			this.curReaders++;
			return true;
		}
	}
	
	public void leave()
	{
		this.curReaders--;
	}
	
	public int getReaders()
	{
		return this.curReaders;
	}
}