package privmem.thruster.engine;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.StorageParameters;

/**
  *
  */
public class ThrusterMission extends Mission {

    // All the methods below execute in the Mission Memory Allocation Context
    @Override
    protected void initialize() {
        /*
         * This method may create and register 1. ManagedEventHandler 2.
         * ManagedThread 3. MissionSequencer that institute this Mission,
         * instantiate and/or initialize certain Mission-level data structure
         */
        // System.out.println("Mission.initialize() is executeding the current memory area is mission memory.");

        EngineControl engineControl = new EngineControl(priority, period,
                storage, memSize);
        engineControl.register();
    }

    /*
     * This method returns the desired size of the MissionMemory associated with
     * this Mission.
     */
    @Override
    public long missionMemorySize() {
        // System.out.println("TestCase 04: PASS. Mission.missionMemorySize() is executed.");
        return 0;
    }

    /*
     * The default implementation of this method does nothing. User-defined
     * subclasses may override its implementation.
     */
    @Override
    protected void cleanUp() {
        // System.out.println("TestCase 21: PASS. Mission.cleanup is executed.");
    }

    private PriorityParameters priority;
    private PeriodicParameters period;
    private StorageParameters storage;
    long memSize;
}
