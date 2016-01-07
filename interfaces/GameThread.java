package interfaces;

import java.lang.reflect.* ;

public class GameThread extends Thread
{
	
	private final Object   object ;
	private final String   methodName ;
	private       boolean  encore ;
	private       long     period ;
	
	public GameThread(Object object,String methodName)
	{
		this.object     = object ;
		this.methodName = methodName ;
		this.encore     = true ;
		this.period     = 1000L ;
	}
	
	public final void endThread()
	{
		encore = false ;
	}
		
	public final void setPeriod(long period)
	{
		if (period < 0) {
			period = 0L ;
		} else if (period > 1000000000L) {
			period = 1000000000L ;
		}
		this.period = period ;
	}
	
	public final long getPeriod()
	{
		return period ;
	}
	
	public void run()
	{
		Method method ;
		try {
			method = object.getClass().getMethod(methodName) ;
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		
		while (encore) {
			try {
				method.invoke(object) ;
				Thread.sleep(period) ;
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		}
	}

}
