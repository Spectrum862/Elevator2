
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clock implements Runnable
{
	int hour = 6 ,minute = 59 ,day = 1;
	int maxfloor =0 ,foodfloor=0;
        private int stopflag=1;
        private static String time;

	
	public Clock() {
		// TODO Auto-generated constructor stub
	}
	
	public Clock(int maxf,int foodf) {
		 maxfloor = maxf;
		 foodfloor = foodf;
	}
	
	public Clock(int hour, int minute, int day)
	{
		this.hour = hour;
		this.minute = minute;
		this.day = day;
	}
	
	public int gethour() {
		return hour;
	}
	public int getminute() {
		return minute;
	}
	public int getday() {
		return day;
	}
	
        public synchronized String getTimev() {
		return time;
	}
	
	public synchronized String getTime() {
		if(this.minute-10<0) return "Day : "+ this.day+" | "+this.hour+" : "+"0"+this.minute;
		else return "Day : "+ this.day+" | "+this.hour+" : "+this.minute;
	}
        
        public void stop(){
            this.stopflag = 0;
        }
        
        public void unstop(){
            this.stopflag=1;
        }        
	
        @Override
	public void run()
	{
		boolean running = true;
		while (running)
		{   
                    time = this.getTime();
                    if(hour == 23 && minute == 59) 
                    {
                            hour = 0;
                            minute = 0;
                            day = day +1;
                    }
                    else if( minute == 59)
                    {
                            hour = hour + 1;
                            minute = 0;
                    }
                        else  minute = minute +1;

                    if(minute % 10 == 0 ) {
                        RandomNumber r = new RandomNumber();
                        r.format(maxfloor, hour, minute, foodfloor);
                    }
                   
                    try {
                        TimeUnit.MICROSECONDS.sleep(Building.timeframe*200);
                        
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    while(stopflag==0) {System.out.println("");};
                    
		}
		
	}
	

//	public void CountClocktest(int maxfloor,int foodfloor) throws InterruptedException 
//	{
//		boolean running = true;
//		while (running)
//		{
//
//		// sleep for 1 seconds
//		TimeUnit.SECONDS.sleep(1);
//		minute = minute +1;
//		
//		if(hour == 23 && minute == 60) 
//		{
//			hour = 0;
//			minute = 0;
//			day = day +1;
//		}
//		else if( minute == 60)
//		{
//			hour = hour + 1;
//			minute = 0;
//		}
//		
//	
//		System.out.printf("\n Execution time : day %2d : hour %4d : minute %4d " ,day ,hour,minute );
//		
//			if(minute % 10 == 0 ) {
//			randomnumber r = new randomnumber();
//			r.format(maxfloor, hour, minute, foodfloor);
//		}
//		
//		
//		}
//		
//	}
	
}
