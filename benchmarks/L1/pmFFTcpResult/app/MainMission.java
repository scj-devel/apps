package privmem.pmFFTcpResult.app;

import javax.realtime.RelativeTime;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.realtime.PeriodicParameters;
import javax.realtime.AperiodicParameters;
import javax.safetycritical.JopSystem;

import javax.safetycritical.ManagedMemory;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;



public class MainMission extends Mission {

    public long missionMemorySize() { return 10000; }
    
     
	protected void initialize() {
				
		AppPeriodicEventHandler peh = new AppPeriodicEventHandler(
			new PriorityParameters(11),
			new PeriodicParameters(new RelativeTime(0,0), new RelativeTime(500,0)),
			new StorageParameters(100000L, null), 
			1000 
				);
		peh.register();
	}
}



