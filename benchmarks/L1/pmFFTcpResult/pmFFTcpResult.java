
package privmem.pmFFTcpResult;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;

import javax.safetycritical.JopSystem;
import javax.safetycritical.Safelet;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

import privmem.pmFFTcpResult.app.MainMission;
import privmem.pmFFTcpResult.app.AppMainMissionSequencer;
import privmem.pmFFTcpResult.app.AppPeriodicEventHandler;



public class pmFFTcpResult implements Safelet {
	AppMainMissionSequencer seq;

	public void setUp() {}
	
    public void tearDown() {}

    public MissionSequencer getSequencer() {
        return this.seq;
    }

	public static void main(String[] args) {
		JopSystem.startMission(new pmFFTcpResult());		
	}

	@Override
	public long immortalMemorySize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeApplication( )
	{
		this.seq = new AppMainMissionSequencer(new PriorityParameters(10), new StorageParameters(100000L, null));		
	}

}


