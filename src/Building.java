
import java.util.concurrent.TimeUnit;

public class Building {
    public static int scale = 1;
    public static int timeframe = 10000;
    public static final int floorheight = 46;
    public static final int firstfloorPosi = 645;
    static int num_floor;
    static int num_elevator;
    static Elevator[] elevator;
    static Floor[] floor;
    static MainUI mainui;
    static HomeUI homeui;
    static Clock clock;
    static ElevatorManager eleva_mng;
    static int food_floor;
    static Thread[] ele_thread;
    static Thread clock_thread;
    static Thread elemng_thread;
    private static Infoupdate updator;
    //first 690 -46

    public static void main(String[] args) throws InterruptedException {
        
        num_floor = 15;
        num_elevator = 3;
        food_floor = 2;
        init();

//        ele_thread[0].start();
//        Human h1 = new Human("up", 5);
//        Human h3 = new Human("up", 7);
//        floor[0].addHumanUp(h1);
//        floor[0].addHumanUp(h1);
//        floor[0].addHumanUp(h1);
//        floor[0].addHumanUp(h3);
//        floor[0].addHumanUp(h3);
//        floor[0].addHumanUp(h1);
//        floor[0].addHumanUp(h1);
        
        

        


        
        
        homeui = new HomeUI();
        homeui.setVisible(true);
        while(true){
            TimeUnit.SECONDS.sleep(1);
            floor[0].displayQueueup();
            System.out.println(clock.getTime());
        }
        
//        
//        while(true){
//            try {
//                TimeUnit.MICROSECONDS.sleep(timeframe);
//                mainui.setElevaposition();
//                
//                elevator[1].addQueue(1);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }    

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
        clock_thread = new Thread(clock);
        elemng_thread = new Thread(eleva_mng);
        updator = new Infoupdate();
        
        
    }
    
    public static void setTimeframe(int newtimeframe){
        timeframe = newtimeframe;
    }
    
    public static void run(){
        for(int i=0;i<num_elevator;i++){
            ele_thread[i].start();
        }
        clock_thread.start();
//        elemng_thread.start();
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
    
    }
    

