package privmem.pacemakerScj;
import javax.realtime.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;


// Implementaion of a periodic event handler

public class DDIR_Handler_Pace_A extends AperiodicEventHandler {
	Actuator_A pm_A = new Actuator_A();
	long interval ;

	public DDIR_Handler_Pace_A() {
		super(new PriorityParameters(PriorityScheduler.instance().getMaxPriority()),
		null,
		new StorageParameters(32768, null),
		32768,
		"DDIR_Handler_Pace_A"); //when sensor event is fire
	
	}

	public void handleAsyncEvent() {
		AbsoluteTime now =Clock.getRealtimeClock().getTime();
		//interval = now.subtract(DDIR_PMMission.lastVentricleActivityTime).getMilliseconds();      
		//if(interval > (DDIR_PMMission.PaceInterval - DDIR_PMMission.AVI)){     
		//interval = now.subtract(DDIR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		//if(interval > (DDIR_PMMission.PVARP + DDIR_PMMission.AVI)){     
		interval = now.subtract(DDIR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		if(interval > (DDIR_PMMission.PaceInterval - DDIR_PMMission.AVI)){     


		pm_A.Pace_ON_A(); //Pace ON		
		Services.spin(new RelativeTime(DDIR_PMMission.PacingLength,0));  //sleep		
		pm_A.Pace_OFF_A(); //Pace OFF

				
		//Reset Ventricle flag
		DDIR_PMMission.Activity_V_Occured =false;
		
		//Save pacing Time of Atrium
		DDIR_PMMission.lastAtriumActivityTime.set(now.getMilliseconds(),now.getNanoseconds());
		
		}
	}
}//end class
