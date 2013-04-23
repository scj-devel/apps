package privmem.pacemakerScj;

//Implementation of Atrium Actuator

public class Actuator_A{


	public Actuator_A() { }

	public synchronized void Pace_ON_A() {
		    System.out.println("Pace ON A");
	}

	
	public synchronized void Pace_OFF_A() {
		    System.out.println("Pace OFF A");
	}
}// end main Actuator class

