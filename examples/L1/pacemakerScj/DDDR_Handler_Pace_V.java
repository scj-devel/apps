package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;

// Implementaion of a periodic event handler

public class DDDR_Handler_Pace_V extends AperiodicEventHandler {
	Actuator_V pm_V= new Actuator_V();
	long interval ;
	
	public DDDR_Handler_Pace_V() {
		super(new PriorityParameters(PriorityScheduler.instance().getMaxPriority()),
		null,
		new StorageParameters(32768, null),
		32768,
		"DDDR_Handler_Pace_V"); //when sensor event is fire
	}

	public void handleAsyncEvent() {
		AbsoluteTime now = Clock.getRealtimeClock().getTime();
		//interval = now.subtract(DDDR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		//if(interval > DDDR_PMMission.AVI){

		interval = now.subtract(DDDR_PMMission.lastVentricleActivityTime).getMilliseconds();      
		if(interval > (DDDR_PMMission.PVARP + DDDR_PMMission.AVI)){     


		pm_V.Pace_ON_V();
		Services.spin(new RelativeTime(DDDR_PMMission.PacingLength,0));  //sleep
		pm_V.Pace_OFF_V();
		
	        //Reset Atrium activity flag
		DDDR_PMMission.Activity_A_Occured = false;
	      
		//Save pacing Time of Ventricle
		DDDR_PMMission.lastVentricleActivityTime.set(now.getMilliseconds(),now.getNanoseconds());
		 
   
		}
		
	}
}//end class
