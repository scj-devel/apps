package privmem.thruster.engine;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

public class EngineControl extends PeriodicEventHandler {

    private boolean engineStarted = false;
    private RelativeTime burnTime;
    private RelativeTime zero;
    private PeriodicParameters myPeriodicParams;

    public EngineControl(PriorityParameters priority,
            PeriodicParameters release, StorageParameters storage, long memSize) {
        super(priority, release, storage, 1000);
        //System.out.println("Engine Control handler constructor ");
        myPeriodicParams = release;
        zero = new RelativeTime(0, 0);
    }

    public synchronized void start(RelativeTime duration) {
        if (engineStarted)
            return;
        engineStarted = true;
        burnTime = new RelativeTime(duration);
        //System.out.println("Engine start");
    }

    public synchronized void stop() {
        engineStarted = false;
        //System.out.println("Engine stop");
    }

    // The following method executes in a fresh private memory area
    @Override
    public synchronized void handleAsyncEvent() {
        if (engineStarted) {
            if (burnTime.compareTo(zero) == 0) {
                stop();
            } else {
                burnTime.subtract(myPeriodicParams.getPeriod());
                //System.out.println("Engine Control");
                // adjust valve to ensure no mechanical drift
                // and, thereby, get an steady fuel flow
            }
        }
    }
}
