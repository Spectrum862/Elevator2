
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Clock implements Runnable
{
	int hour = 7 ,minute = 00 ,day = 1;
	int maxfloor =0 ,foodfloor=0;
	
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
	
	@Override
	public void run() {
		try {
			this.CountClock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public String getTime() {
		if(this.minute-10<0) return "Day : "+ this.day+" | "+this.hour+" : "+"0"+this.minute;
		else return "Day : "+ this.day+" | "+this.hour+" : "+this.minute;
	}
	
	
	
	
	public void CountClock() throws InterruptedException 
	{
		boolean running = true;
		while (running)
		{

		// sleep for 1 seconds
		TimeUnit.SECONDS.sleep(1);
		if(hour == 23 && minute == 60) 
		{
			hour = 0;
			minute = 0;
			day = day +1;
		}
		else if( minute == 60)
		{
			hour = hour + 1;
			minute = 0;
		}
		
			if(minute % 10 == 0 ) {
			RandomNumber r = new RandomNumber();
			r.format(maxfloor, hour, minute, foodfloor);
		}
		
		minute = minute +1;
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
