package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;
import java.util.Random;



// Implementaion of a periodic event handler

public class DDIR_Read_Sensor_A extends PeriodicEventHandler {
	Random generator = new Random();
	AperiodicEvent e;
	long interval;
	double res; 

	public DDIR_Read_Sensor_A(AperiodicEvent e1) {
		super(new PriorityParameters(PriorityScheduler.instance()
				.getNormPriority()), new PeriodicParameters(null,
				new RelativeTime(1, 0)), new StorageParameters(
				32768, null), 65536, "DDIR_Read_Sensor_A");
	e=e1;
	}

	public void handleAsyncEvent() {
		AbsoluteTime now =Clock.getRealtimeClock().getTime();
		interval = now.subtract(DDIR_PMMission.lastVentricleActivityTime).getMilliseconds();      
		if( (interval > DDIR_PMMission.PVARP) && (interval < (DDIR_PMMission.PaceInterval-DDIR_PMMission.AVI)) && (DDIR_PMMission.Activity_A_Occured==false)){
		    res = generator.nextDouble();
		    //System.out.println("Sensed A < 0.30");
		    if(res >= 0.3){
			System.out.println("Intrinsic activity sensed in A");	
			
			//Save intrinsic activity Time of Atrium 
		      	DDIR_PMMission.lastAtriumActivityTime.set(now.getMilliseconds(),now.getNanoseconds());
			DDIR_PMMission.Activity_A_Occured = true;

			//Reset Ventricle flag
			DDIR_PMMission.Activity_V_Occured =false;
			
		    }
		}else if((interval > (DDIR_PMMission.PaceInterval - DDIR_PMMission.AVI)) && DDIR_PMMission.Activity_A_Occured == false ){
		      
			//Set Atrium activity flag
			DDIR_PMMission.Activity_A_Occured = true;  
			e.fire();         
		      
		}
	}
}//end class 


