package privmem.pmFFTcpResult.app;

import javax.realtime.PriorityParameters;
import javax.realtime.PeriodicParameters;

import javax.safetycritical.ManagedMemory;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;

public class AppPeriodicEventHandler extends PeriodicEventHandler
{
	ManagedMemory phpm;
	Complex[] input;
	Complex[] result;
	Complex[] myResult;
	
	public AppPeriodicEventHandler(PriorityParameters priority, 
	                                        PeriodicParameters parameters,
	                                        StorageParameters storage,
	                                        int memorySize	                                        
	                                        )
	{
		super(priority, parameters, storage, memorySize);		
		Complex a = new Complex(5.0, 6.0);
		this.input = new Complex[] { a, a, a, a, a, a, a, a};
	}

	public void handleAsyncEvent() 
	{		
								
		ManagedMemory.enterPrivateMemory(20, new FooRunnable(this, this.input));				
		System.out.print(this.result);
	}
	
	public void saveResult(Complex[] produced)
	{
		System.arraycopy(produced, 0, this.result, 0, produced.length);

	}
}

class FooRunnable implements Runnable{
	
	Complex[] input;
	PeriodicEventHandler peh;
	
	public FooRunnable(PeriodicEventHandler peh, Complex[] input)
	{
		this.input = input;
		this.peh = peh;
	}
	
        public void run() {
    		Complex[] result = FFT.fft(input);
		((AppPeriodicEventHandler)this.peh).saveResult(result);		
    	}
}

