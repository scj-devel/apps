package privmem.thruster.engine;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

/**
 * This class terminates the mission sequencer once it is released.
 *
 * @author Lilei Zhai
 *
 */
public class MyTerminatorPeriodicEventHandler extends PeriodicEventHandler {

    public MyTerminatorPeriodicEventHandler(PriorityParameters priority,
            PeriodicParameters release, StorageParameters storage,
            long memSize, String name) {
        super(priority, release, storage, 1000, name);
    }

    @Override
    public void handleAsyncEvent() {
        // System.out.println("TestCase 22: PASS. the terminator PEH of terminator Mission is released.");
        Mission.getCurrentMission().requestSequenceTermination();
    }

    @Override
    public void cleanUp() {
    }

}
