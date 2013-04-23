package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;
import java.util.Random;



// Implementaion of a periodic event handler

public class DDDR_Read_Sensor_A extends PeriodicEventHandler {
	
	AperiodicEvent e;
	long interval;
	double res; 
        Random generator = new Random();

	public DDDR_Read_Sensor_A(AperiodicEvent e1) {
		super(new PriorityParameters(PriorityScheduler.instance()
				.getNormPriority()), new PeriodicParameters(null,
				new RelativeTime(1, 0)), new StorageParameters(
				32768, null), 65536, "DDDR_Read_Sensor_A");
	e=e1;
	}

	public void handleAsyncEvent() {
		AbsoluteTime now =Clock.getRealtimeClock().getTime();
		interval = now.subtract(DDDR_PMMission.lastVentricleActivityTime).getMilliseconds();      
		if( (interval > DDDR_PMMission.PVARP) && (interval < (DDDR_PMMission.PaceInterval-DDDR_PMMission.AVI)) && (DDDR_PMMission.Activity_A_Occured==false)){
		    res = generator.nextDouble();
		    //System.out.println("Sense A < 0.30");	
		    if(res >= 0.3){
    
			System.out.println("Intrinsic activity sensed in A");	
			
			//Save intrinsic activity Time of Atrium 
		      	DDDR_PMMission.lastAtriumActivityTime.set(now.getMilliseconds(),now.getNanoseconds());
			DDDR_PMMission.Activity_A_Occured = true;

			//Reset Ventricle flag
			DDDR_PMMission.Activity_V_Occured =false;
			
		    }
		}else if(interval >= (DDDR_PMMission.PaceInterval - DDDR_PMMission.AVI) && DDDR_PMMission.Activity_A_Occured == false ){

		      //Set Atrium activity flag
		      DDDR_PMMission.Activity_A_Occured = true;
		      e.fire();	           
		      
		}
	}
}//end class 

