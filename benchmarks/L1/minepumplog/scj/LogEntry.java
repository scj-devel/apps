package privmem.minepumplog.scj;

import javax.realtime.AbsoluteTime;

class LogEntry
{
	protected String message;
	//protected AbsoluteTime date;
	
	public LogEntry(String str)
	{
		this.message = str;
		//this.date = new AbsoluteTime();		
	}
}
