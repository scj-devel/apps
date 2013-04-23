package privmem.thruster.engine;

import javax.safetycritical.MissionSequencer;
import javax.safetycritical.Safelet;
import javax.safetycritical.*;

/**
 * This is the application!
 *
 * @author plsek
 *
 */
public class ThrusterControlSystem implements Safelet {

    MissionSequencer seq;
    
    public ThrusterControlSystem() {
        // System.out.println("Safelet being created");
    }

    /**
     * This method returns the MissionSequencer of the application.
     * "myMissionSequencer" takes a reference of the MissionSequencer. If
     * "myMissionSequencer" is null, create a new MissionSequencer, otherwise
     * return the "myMissionSequencer" directly.
     */
    @Override
    public MissionSequencer getSequencer() {
        // System.out.println("MissionSequencer.getSequencer() is executed.");
        return this.seq;//ThrusterControlSequencer.getInstance();
    }

    /**
     * This method will be called before "getSequencer()". This method may
     * create objects in ImmortalMemoryArea, set up hardware interrupt handlers,
     * or initializing hardware devices.
     */
    public void setUp() {
        /*
         * This testing method doesn't contain any operation, just print
         * something to the console showing it is called.
         */
        // System.out
        // .println("TestCase 01: PASS. MissionSequencer.setUp() is executed.");
    }

    /**
     * This method will be called after the termination of the MissionSequencer.
     * It frees all resources used by the application.
     */
    public void tearDown() {
        /*
         * This testing method doesn't contain any operation, just print
         * something to the console showing it is called.
         */
        // System.out
        // .println("TestCase 24: PASS. MissionSequencer.tearDown() is executed.");
    }

    @Override
    public long immortalMemorySize() {
        return 1000;
    }

    public void initializeApplication( )
    {
	this.seq = ThrusterControlSequencer.getInstance();
    }

    public static void main(String[] args) {
	JopSystem.startMission(new ThrusterControlSystem());
    }

}
