package privmem.minepumplog.scj;

import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;

import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

import privmem.minepumplog.sensors.MethaneSensor;
import privmem.minepumplog.actuators.WaterpumpActuator;


public class PeriodicMethaneDetectionEventHandler extends PeriodicEventHandler
{
	protected MethaneSensor methaneSensor;
	protected WaterpumpActuator waterpumpActuator;
	protected LogManager log;

	public PeriodicMethaneDetectionEventHandler(PriorityParameters priority, 
	                                        PeriodicParameters parameters,
	                                        StorageParameters storage,
	                                        int memorySize,
	                                        MethaneSensor methaneSensor,
	                                        WaterpumpActuator waterpumpActuator,
	                                        LogManager log)
	{
		super(priority, parameters, storage, memorySize);
		this.methaneSensor = methaneSensor;
		this.waterpumpActuator = waterpumpActuator;
		this.log = log;
	}

	public void handleAsyncEvent() 
	{		
		if (methaneSensor.isCriticalMethaneLevelReached()) {
			log.addEntry(new LogEntry("Waterpump was emergency stopped"));
	      	waterpumpActuator.emergencyStop(true);
		}
		else {
			waterpumpActuator.emergencyStop(false);
		}
	}
}
