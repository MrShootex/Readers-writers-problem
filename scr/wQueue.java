class wQueue
{
	private rQueue rQ;
	private int writers;
	private boolean underWriting;
	
	public wQueue(rQueue rQ)
	{
		this.rQ = rQ;
		this.writers = 0;
		this.underWriting = false;
	}
	
	public void writerComing()
	{
		this.rQ.setUnderWriting(true);	
		this.writers++;
	}
	
	synchronized public boolean join()
	{
		if(this.underWriting || this.getReaders() != 0)
		{
			return false;
		}
		else
		{
			this.underWriting = true;
			return true;
		}
	}
	
	public void leave()
	{
		this.underWriting = false;
		this.writers--;
		if(writers == 0)
			this.rQ.setUnderWriting(false);	
	}
	
	synchronized public boolean underWriting()
	{
		return this.underWriting;
	}
	
	public int getReaders()
	{
		return this.rQ.getReaders();
	}
}