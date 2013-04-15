package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;

// Implementaion of a periodic event handler

public class DDIR_Handler_Pace_V extends AperiodicEventHandler {
	Actuator_V pm_V= new Actuator_V();
	long interval ;
	DDIR_ModeChange fmc = new DDIR_ModeChange();

	public DDIR_Handler_Pace_V() {
		super(new PriorityParameters(PriorityScheduler.instance().getMaxPriority()),
		null,
		new StorageParameters(32768, null),
		32768,
		"DDIR_Handler_Pace_V"); //when sensor event is fire
	}

	public void handleAsyncEvent() {
		AbsoluteTime now = Clock.getRealtimeClock().getTime();
		//interval = now.subtract(DDIR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		//if(interval > DDIR_PMMission.AVI){
		interval = now.subtract(DDIR_PMMission.lastVentricleActivityTime).getMilliseconds();      
		if(interval > (DDIR_PMMission.PVARP + DDIR_PMMission.AVI)){     

		pm_V.Pace_ON_V();		
		Services.spin(new RelativeTime(DDIR_PMMission.PacingLength,0));  //sleep		
		pm_V.Pace_OFF_V();
		
	      
		//Reset Atrium activity flag
		DDIR_PMMission.Activity_A_Occured = false;
	      
		//Save pacing Time of Ventricle
		DDIR_PMMission.lastVentricleActivityTime.set(now.getMilliseconds(),now.getNanoseconds());
		}
		
	}
}//end class
