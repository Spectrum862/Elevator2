
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Building {
    public static final int scale = 1;
    public static final int timeframe = 5000;
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
        
        
    }
    
    public static void run(){
        for(int i=0;i<num_elevator;i++){
            ele_thread[i].start();
        }
        clock_thread.start();
        elemng_thread.start();
        
    }

    public static void display() {
        double elev1_pos = elevator[0].getPosition();
        double elev2_pos = elevator[1].getPosition();
        double elev3_pos = elevator[2].getPosition();
        System.out.println("------------------------------------");
        for (double i = 15; i >= 1; i = i - 1) {
            System.out.print("Position"+i);
            if (elevator[0].getPosition() == i) {
                System.out.printf("%5s", "x");
            }
            else{
                System.out.printf("%5s", " ");
            }
            if (elevator[1].getPosition() == i) {
                System.out.printf("%5s", "x");
            }
            else{
                System.out.printf("%5s", " ");
            }
            if (elevator[2].getPosition() == i) {
                System.out.printf("%5s", "x");
            }
            else{
                System.out.printf("%5s", " ");
            }
            System.out.println("");

        }
        System.out.println("------------------------------------");
    }


}
