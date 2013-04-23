package privmem.pacemakerScj;
     // Implementaion of a MissionSeuquence

import javax.safetycritical.PriorityScheduler;
import javax.realtime.*;
import javax.safetycritical.*;


public class MainPMMissionSequence extends MissionSequencer {
	public volatile static String CMode="DDDR"; 
	
	public MainPMMissionSequence() {

		super(new PriorityParameters(PriorityScheduler.instance()
				.getMaxPriority()), new StorageParameters(131072L, null));
	}

	public Mission getNextMission() {
		if (CMode.equals("DDDR")) {
		System.out.println("\n\n\nDDDR Mode..........................................DDDR Mode\n");
			return new DDDR_PMMission();  			 
			
		} else {
		System.out.println("\n\n\nDDIR Mode..........................................DDIR Mode\n");
			return new DDIR_PMMission();  

		}
	}

		


}
