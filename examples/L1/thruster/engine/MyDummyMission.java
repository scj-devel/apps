package privmem.thruster.engine;

import javax.safetycritical.Mission;

/**
 * This class defines the mission that should not be executed.
 *
 * @author Lilei Zhai
 *
 */
public class MyDummyMission extends Mission {

    @Override
    protected void initialize() {
        // This Mission shall not be executed,
        //System.out
        //        .println("TestCase 23: FAIL. This Mission shall not be executed.");
    }

    @Override
    public long missionMemorySize() {
        //System.out
        //        .println("TestCase 23: FAIL. This Mission shall not be executed.");
        return 0;
    }

    @Override
    protected void cleanUp() {
        //System.out
        //        .println("TestCase 23: FAIL. This Mission shall not be executed.");
    }

}
