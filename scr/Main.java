import java.io.*;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		_println("Press Ctrl-C to exit");
		int readersNum, writersNum, maxReadersNum;
		if(args.length != 3)
		{
			_print("Enter amount of readers: ");
			readersNum = readInt();
			_print("Enter amount of writers: ");
			writersNum = readInt();
			_print("Enter max amount of readers can read at the same time: ");
			maxReadersNum = readInt();
		}
		else
		{
			readersNum = Integer.parseInt(args[0]);
			writersNum = Integer.parseInt(args[1]);
			maxReadersNum = Integer.parseInt(args[2]);
		}
		
		
		rQueue rQ = new rQueue(maxReadersNum);
		wQueue wQ = new wQueue(rQ); 
		
		ThreadReader readers[] = new ThreadReader[readersNum];
		for(int i=0; i<readersNum; ++i)
		{
			readers[i] = new ThreadReader(i, rQ);
		}
		
		ThreadWriter writers[] = new ThreadWriter[writersNum];
		for(int i=0; i<writersNum; ++i)
		{
			writers[i] = new ThreadWriter(i, wQ);
		}	
		
		try 
		{
			for(int i=0; i<readersNum; ++i)
			{				
				readers[i].join();				
			}			
			for(int i=0; i<writersNum; ++i)
			{
				writers[i].join();
			}	
		} catch (InterruptedException e) 
			{
			e.printStackTrace();
			}
		_println("FUCK YOU!!!");
	}
	
	public static int readInt() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = Integer.parseInt(s);
        return n;
    }

	public static String readStr() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        return s;
    }
	
	public static void _println(String s)
	{
		System.out.println(s);
	}
	
	public static void _print(String s)
	{
		System.out.print(s);
	}
}
