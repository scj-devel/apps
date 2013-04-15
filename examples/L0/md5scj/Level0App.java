// no error here

package privmem.md5scj;

import javax.realtime.RelativeTime;
import javax.realtime.PriorityParameters;
import javax.safetycritical.CyclicExecutive;
import javax.safetycritical.CyclicSchedule;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.*;

public class Level0App extends CyclicExecutive implements Safelet {

    MissionSequencer seq;

    public Level0App() {
        super();
    }

    @Override
    public CyclicSchedule getSchedule(PeriodicEventHandler[] handlers) {
        Frame[] frames = new Frame[1];
        frames[0] = new Frame(new RelativeTime(Constants.PERIOD, 0), handlers);
        CyclicSchedule schedule = new CyclicSchedule(frames);
        return schedule;
    }

    @Override
    public void initialize() {
        new MD5SCJ(Constants.PRIVATE_MEMORY, Constants.RUNS);
    }

    /**
     * A method to query the maximum amount of memory needed by this mission.
     *
     * @return the amount of memory needed
     */
    @Override
    public long missionMemorySize() {
        return Constants.MISSION_MEMORY;
    }


    @Override
    public long immortalMemorySize() {
	return 1000;
    }

    @Override
    public MissionSequencer getSequencer() {
		return this.seq;

    }

    public void setUp() {
    }

    public void tearDown() {
    }

    @Override
    public void cleanUp() {
    }

    @Override
    public void initializeApplication( )
    {
		this.seq = new LinearMissionSequencer(new PriorityParameters(13), new StorageParameters(1000000, null), false, this);
    }

    public static void main(String[] args) {
		JopSystem.startMission(new Level0App());
    }

}
