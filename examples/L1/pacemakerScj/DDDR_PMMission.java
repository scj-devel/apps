package privmem.pacemakerScj;
// Implementaion of a Mission
import javax.safetycritical.*;
import javax.realtime.*;
import javax.safetycritical.Mission;
import javax.realtime.AbsoluteTime;
import javax.realtime.RelativeTime;
import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.PriorityScheduler;
import javax.realtime.MemoryArea;
import javax.safetycritical.InterruptHandler;

public class DDDR_PMMission extends Mission {
    
public volatile static int reactionTime = 30000; //10-50 sec. 30 sec=30000 ms.
	public volatile static int recoveryTime = 300000; //2-16 min. 5 min = 300000 ms.
	public volatile static int Slop = 8; //1-16 
	public volatile static int PVARP=270; 
	public volatile static int MSR = 500; //50-175 bpm 120 bpm = 500 ms.
	public volatile static int PaceInterval=1000;
	public volatile static int AVI=150;
	public volatile static int PacingLength=2;

	public volatile static boolean Activity_A_Occured = false; 
	public volatile static boolean Activity_V_Occured = false; 
	
	
	public volatile static AbsoluteTime lastAtriumActivityTime = Clock.getRealtimeClock().getTime();
	public volatile static AbsoluteTime lastVentricleActivityTime = Clock.getRealtimeClock().getTime();
	public volatile static AbsoluteTime oldTime = Clock.getRealtimeClock().getTime(); //time for recording one cycle  



	    protected void initialize() {
	    // Aperiodic Event Handler for pacing in Atrium 
	    DDDR_Handler_Pace_A handleA = new DDDR_Handler_Pace_A();
	    AperiodicEvent eA = new AperiodicEvent(handleA);

	    // Aperiodic Event Handler for pacing in Ventricle  
	    DDDR_Handler_Pace_V handleV = new DDDR_Handler_Pace_V();
	    AperiodicEvent eV = new AperiodicEvent(handleV);

	    // Periodic Event Handler for sensing in Atrium 
	    new DDDR_Read_Sensor_A(eA);
	    
	    // Periodic Event Handler for sensing in Ventricle 
	    new DDDR_Read_Sensor_V(eV);

	    // Periodic Event Handler for sensing motion activities  
	    new DDDR_Handler_MotionSensor();
	}


	protected void cleanup() {
	}


	public long missionMemorySize() {
		return 1000000;
	}

}//end class
