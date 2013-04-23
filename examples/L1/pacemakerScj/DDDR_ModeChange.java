package privmem.pacemakerScj;
import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;


// Implementaion of a periodic event handler

public class DDDR_ModeChange{
static int P_Count=0;
long interval;

public void funModeChange(AbsoluteTime oldTime, AbsoluteTime newTime){
    interval = newTime.subtract(oldTime).getMilliseconds();
	if ( interval < DDDR_PMMission.MSR){
	      P_Count =  P_Count + 1;
	} else {	
	      P_Count =  0;
	  }

	// DDDR -> DDIR   
	if (P_Count==5 && MainPMMissionSequence.CMode.equals("DDDR"))   // x out of y algo x range (2 to7), we have considered 5 
	{
	      MainPMMissionSequence.CMode="DDIR";
	      Mission.getCurrentMission().requestTermination();
	      P_Count = 0;
	      System.out.println("Operating Mode: DDIR");
	}

    }
}//end class
