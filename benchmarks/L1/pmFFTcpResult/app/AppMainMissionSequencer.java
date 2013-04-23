package privmem.pmFFTcpResult.app;



import javax.realtime.PriorityParameters;
import javax.safetycritical.Mission;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.StorageParameters;


public class AppMainMissionSequencer extends MissionSequencer {
    public AppMainMissionSequencer(PriorityParameters priority,
			StorageParameters storage) {
		super(priority, storage);
	}

	public Mission getNextMission() {
        return new MainMission();
    }
}
