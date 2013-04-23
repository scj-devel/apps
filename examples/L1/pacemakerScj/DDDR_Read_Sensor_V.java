package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;
import java.util.Random;



// Implementaion of a periodic event handler

public class DDDR_Read_Sensor_V extends PeriodicEventHandler {
	Random generator = new Random();
	AperiodicEvent e;
	DDDR_ModeChange fmc = new DDDR_ModeChange();
	long interval;
	double res;
	
	public DDDR_Read_Sensor_V(AperiodicEvent e1) {
		super(new PriorityParameters(PriorityScheduler.instance()
				.getNormPriority()), new PeriodicParameters(null,
				new RelativeTime(1, 0)), new StorageParameters(
				32768, null), 65536, "DDDR_Read_Sensor_A");
	e=e1;
	}

	public void handleAsyncEvent() {
		AbsoluteTime now =Clock.getRealtimeClock().getTime();		
      		interval = now.subtract(DDDR_PMMission.lastAtriumActivityTime).getMilliseconds();      
		if((interval <= DDDR_PMMission.AVI) && (DDDR_PMMission.Activity_V_Occured == false)) {
		    res = generator.nextDouble();
		    //System.out.println("Sensed V < 0.90");
		    if(res >= 0.9){
			System.out.println("Intrinsic activity sensed in V");
			
			//Save intrinsic activity Time of Ventricle
			DDDR_PMMission.lastVentricleActivityTime.set(Clock.getRealtimeClock().getTime().getMilliseconds(),Clock.getRealtimeClock().getTime().getNanoseconds());
			DDDR_PMMission.Activity_V_Occured=true;

			////Reset Atrium flag
			DDDR_PMMission.Activity_A_Occured=false;
			
			// mode update changing due to completion of one cycle (Atrium and Ventricle pacing or sensing)
			fmc.funModeChange(DDDR_PMMission.oldTime, now);
			DDDR_PMMission.oldTime.set(now.getMilliseconds(),now.getNanoseconds());
		    }
		}else if((interval > DDDR_PMMission.AVI) && (DDDR_PMMission.Activity_V_Occured == false)){
		//Set Ventricle activity flag
		DDDR_PMMission.Activity_V_Occured = true;

		      e.fire();	     
		

		// mode update changing due to completion of one cycle (Atrium and Ventricle pacing or sensing)
		fmc.funModeChange(DDDR_PMMission.oldTime, now);
		DDDR_PMMission.oldTime.set(now.getMilliseconds(),now.getNanoseconds());


		     
		}
	}
}//end class 
