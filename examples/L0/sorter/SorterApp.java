package privmem.sorter;
// no error here

import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.CyclicExecutive;
import javax.safetycritical.CyclicSchedule;
import javax.safetycritical.PeriodicEventHandler;
import privmem.sorter.bench.BenchConf;
import javax.safetycritical.*;
import javax.safetycritical.StorageParameters;


public class SorterApp extends CyclicExecutive implements Safelet {

    MissionSequencer seq;

    public SorterApp() {
        super();
    }
 
    public void initialize() {
        new SorterHandler(BenchConf.HANDLER_SCOPE_SIZE);
    }

    @Override
    public long missionMemorySize() {
        return BenchConf.MISSION_SCOPE_SIZE;
    }

    @Override
    public long immortalMemorySize() {
        return 1000;
    }


    public void setUp() {
    }

    public void tearDown() {
        BenchConf.dump();
    }

    public static void main(String[] args) {
        JopSystem.startMission(new SorterApp());
    }

	@Override
	public void initializeApplication( )
	{
		this.seq = new LinearMissionSequencer(new PriorityParameters(10),
				new StorageParameters(BenchConf.MISSION_SCOPE_SIZE, null), 
				false, this);
	}


    @Override
    public MissionSequencer getSequencer()
    {
		return this.seq;
    }   


}
