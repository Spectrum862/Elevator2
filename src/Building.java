
import java.util.concurrent.TimeUnit;

public class Building {
    public static int scale = 1;
    public static int timeframe = 10000;
    public static final int floorheight = 46;
    public static final int firstfloorPosi = 645;
    static int num_floor = 15;
    static int num_elevator = 3;
    static Elevator[] elevator;
    static Floor[] floor;
    static MainUI mainui;
    static HomeUI homeui;
    static Clock clock;
    static ElevatorManager eleva_mng;
    static int food_floor = 2;
    static Thread[] ele_thread;
    static Thread clock_thread;
    static Thread elemng_thread;
    private static Infoupdate updator;
    //first 690 -46

    public static void main(String[] args) throws InterruptedException {
        homeui = new HomeUI();
        homeui.setVisible(true);
    }

    public static void init(){
        elevator = new Elevator[num_elevator];
        floor = new Floor[num_floor];
        ele_thread = new Thread[num_elevator];   
        for(int i = 0;i<num_floor;i++){
            floor[i] = new Floor(i+1);
        }
        for(int i = 0;i<num_elevator;i++){
            elevator[i] = new Elevator(i+1);
            ele_thread[i] = new Thread(elevator[i]);
        }
        clock = new Clock(num_floor, food_floor);
        eleva_mng = new ElevatorManager();
        elemng_thread = new Thread(eleva_mng);
        clock_thread = new Thread(clock);
        updator = new Infoupdate();
        
        
    }
    
    public static void setTimeframe(int newtimeframe){
        timeframe = newtimeframe;
    }
    
    public static void run(){
        for(int i=0;i<num_elevator;i++){
            ele_thread[i].start();
        }
        elemng_thread.start();
        clock_thread.start();
        Thread update = new Thread(updator);
        update.start();
        
    }
    
    public static void stop() throws InterruptedException{
        scale = 0;
        clock.stop();
    }
    
    public static void unstop(){
        scale = 1;
        clock.unstop();
    }
    
    public static void setE_F_C(int e,int f, int c){
        num_elevator = e;
        num_floor = f;
        food_floor = c;
    }
    
    }
    

