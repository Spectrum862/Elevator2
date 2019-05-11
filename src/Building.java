
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Building {
    public static final double scale = 1;
    static int num_floor;
    static int num_elevator;
    static Elevator[] elevator;
    static Floor[] floor;
    static private UI ui1;

    public static void main(String[] args) {
        elevator = new Elevator[3];
        elevator[0] = new Elevator(1);
        elevator[1] = new Elevator(2);
        elevator[2] = new Elevator(3);

        elevator[0].addQueue(7);
        elevator[1].addQueue(5);
        elevator[2].addQueue(10);

        Thread t1 = new Thread(new ElevatorControll(elevator[0]));
        Thread t2 = new Thread(new ElevatorControll(elevator[1]));
        Thread t3 = new Thread(new ElevatorControll(elevator[2]));
        Thread t4 = new Thread(new Display());
        t4.start();
        t1.start();
        t2.start();
        t3.start();

        ui1 = new UI();
        ui1.setVisible(true);
        
        
        
        

        while(true){
            try {
                TimeUnit.MICROSECONDS.sleep(5000);
                ui1.setTest(1);
                
                elevator[1].addQueue(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    

    }

    public static void init(int no_ele,int no_floor){
        elevator = new Elevator[no_ele];
        floor = new Floor[no_floor];
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
