package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;


// Implementaion of a periodic event handler

public class DDIR_ModeChange{
static int P_Count=0;
long interval;

public void funModeChange(AbsoluteTime oldTime, AbsoluteTime newTime){
    interval = newTime.subtract(oldTime).getMilliseconds();

	if ( interval < DDIR_PMMission.MSR){
	      P_Count =  P_Count + 1;
	} else {
	      P_Count =  0;
	}

	// DDIR -> DDDR   
	if (P_Count==8 && MainPMMissionSequence.CMode.equals("DDIR"))   // x out of y algo x range (2 to7), we have considered 8 (to return back to previous mode DDDR)
	{
	      MainPMMissionSequence.CMode="DDDR";
	      Mission.getCurrentMission().requestTermination();
	      P_Count = 0;
	      System.out.println("Operating Mode: DDDR");
	}
    }
}//end class
