package privmem.pacemakerScj;
import javax.safetycritical.Safelet;
import javax.safetycritical.MissionSequencer;
import javax.safetycritical.annotate.Level;

// Implementaion of a Safelet

public class PMSafelet implements Safelet {

	MissionSequencer seq;

	/*public Level getLevel() {
		Level l = new Level(1);
		return l;
	}*/

	public void setup() {
	}

	public MissionSequencer getSequencer() {
		return this.seq;
	}

	public void teardown() {
	}

	@Override
	public long immortalMemorySize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeApplication( )
	{
		this.seq = new MainPMMissionSequence();
	}
}
