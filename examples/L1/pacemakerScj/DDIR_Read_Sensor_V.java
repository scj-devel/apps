package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;
import java.util.Random;



// Implementaion of a periodic event handler

public class DDIR_Read_Sensor_V extends PeriodicEventHandler {
	Random generator = new Random();
	AperiodicEvent e;
	DDIR_ModeChange fmc = new DDIR_ModeChange();
	
	long interval;
	double res;
	
	public DDIR_Read_Sensor_V(AperiodicEvent e1) {
		super(new PriorityParameters(PriorityScheduler.instance()
				.getNormPriority()), new PeriodicParameters(null,
				new RelativeTime(1, 0)), new StorageParameters(
				32768, null), 65536, "DDIR_Read_Sensor_A");
	e=e1;
	}

	public void handleAsyncEvent() {
		AbsoluteTime now =Clock.getRealtimeClock().getTime();		
      		interval = now.subtract(DDIR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		if((interval <= DDIR_PMMission.PaceInterval) && (DDIR_PMMission.Activity_V_Occured == false)) {
		    res = (double) generator.nextDouble();
		    //System.out.println("Sensed V < 0.90");
		    if(res >= 0.9){
			System.out.println("Intrinsic activity sensed in V");
			
			//Save intrinsic activity Time of Ventricle
			DDIR_PMMission.lastVentricleActivityTime.set(Clock.getRealtimeClock().getTime().getMilliseconds(),Clock.getRealtimeClock().getTime().getNanoseconds());
			DDIR_PMMission.Activity_V_Occured=true;

			////Reset Atrium flag
			DDIR_PMMission.Activity_A_Occured=false;
			
			// mode update changing due to completion of one cycle (Atrium and Ventricle pacing or sensing)
			fmc.funModeChange(DDIR_PMMission.oldTime, now);
			DDIR_PMMission.oldTime.set(now.getMilliseconds(),now.getNanoseconds());
		    }
		}else if((interval > DDIR_PMMission.PaceInterval) && (DDIR_PMMission.Activity_V_Occured == false)){
			//Set Ventricle activity flag
			DDIR_PMMission.Activity_V_Occured = true;
		
			e.fire();	     

			// mode update changing due to completion of one cycle (Atrium and Ventricle pacing or sensing)
			fmc.funModeChange(DDIR_PMMission.oldTime, now);
			DDIR_PMMission.oldTime.set(now.getMilliseconds(),now.getNanoseconds());
		      
		}
	}
}//end class 

