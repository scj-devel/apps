package privmem.pacemakerScj;
import javax.safetycritical.JopSystem;
import javax.safetycritical.Safelet;

import privmem.pacemakerScj.PMSafelet;

public class main {
	public static void main(String[] args) {
		JopSystem.startMission(new PMSafelet());
	}
}
