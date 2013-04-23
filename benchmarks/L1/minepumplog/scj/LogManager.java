package privmem.minepumplog.scj;

public class LogManager
{
	private static final int MAX_ARRAY_SIZE = 100;
	private LogEntry[] logArray = new LogEntry[MAX_ARRAY_SIZE];
	private LogEntry logField;
	private int counter = 0;
	
	public void addEntry(LogEntry entry)
	{
		if (this.counter < MAX_ARRAY_SIZE) {
			this.logArray[this.counter] = entry;
			this.counter++;
		} else {
			try {
				throw new Exception("LogArray full");
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}
		
}
