package privmem.thruster.engine;

import javax.realtime.PriorityParameters;
import javax.realtime.PriorityScheduler;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;

/**
 */
public class ThrusterControlSequencer extends MissionSequencer {

    private static final int NORM_PRIORITY = PriorityScheduler.instance()
            .getNormPriority();

    // singleton pattern
    private static ThrusterControlSequencer thrusterControlSequencer;

    private static final int NO_MISSION = 0;
    private static final int NORMAL_MISSION = 1;
    private static final int TERMINATOR_MISSION = 2;
    private static final int DUMMY_MISSION = 3;
    private static int curMissionNum = NO_MISSION;

    private ThrusterControlSequencer(PriorityParameters priority,
            StorageParameters storage) {
        super(priority, storage);
        // System.out.println("Sequencer created");
    }

    public static MissionSequencer getInstance() {
        // System.out.println("Thruster getInstance called");
        if (thrusterControlSequencer == null) {
            PriorityParameters myPriorityPar = new PriorityParameters(
                    NORM_PRIORITY);
            StorageParameters myStoragePar = new StorageParameters(100000L,
                    null);

            thrusterControlSequencer = new ThrusterControlSequencer(
                    myPriorityPar, myStoragePar);
            // System.out.println("Sequencer created");
        }

        // return null;
        return thrusterControlSequencer;
    }

    @Override
    protected Mission getNextMission() {
        /*
         * Use boolean instead of MyMission reference here, because Immortal
         * can't refer to Scoped
         */
        // System.out.println("TestCase 03: PASS. MissionSequencer.getNextMission() is executed.");
        switch (curMissionNum++) {
        case NO_MISSION:
            return new ThrusterMission();
        case NORMAL_MISSION:
            return new MyTerminatorMission();
        case TERMINATOR_MISSION:
            return new MyDummyMission();
        case DUMMY_MISSION:
            return null;
        default:
            // System.err.println("Error: invalid curMissionNum: "+curMissionNum);
            return null;
        }
    }

}
