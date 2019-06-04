
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingConstants;

public class ElevatorManager implements Runnable {

    private int stopflag = 1;

    public ElevatorManager() {
        // Constructor
    }

    @Override
    public void run() {
        try {
            this.manage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stopflag = 0;
    }

    public void unstop() {
        this.stopflag = 1;
    }

    public void manage() throws InterruptedException {
        int i = Building.num_floor - 1;
        int d = 0; // distance
        int buff = 0;
        int target = 0;
        boolean running = true;
        while (running) {
//            System.out.println("...");
//            System.out.println(Building.floor[i].isQUpEmpty());
//            System.out.println(Building.floor[i].isQDownEmpty());
            if (Building.floor[i].isQUpEmpty() == false) {
//                System.out.println("UP");
                d = 0;
                buff = Building.num_floor;
                for (int j = 0; j < Building.elevator.length; j++) {
//                    System.out.println("j = " + j);
                    if (Building.elevator[j].getState().equals("idle") || (Building.elevator[j].getState().equals("up") && Building.elevator[j].FloornumPosi() < Building.floor[i].getFloornum())) {
                        d = Math.abs(Building.floor[i].getFloornum() - Building.elevator[j].FloornumPosi());
                        if (d < buff) {
//                            System.out.println("d < buff");
                            buff = d;
                            target = j;
                        }
                    }
                    if ((j == Building.elevator.length - 1) && (buff != Building.num_floor)) {
//                        System.out.println("add queue");
                        if (d > 0) {
                            Building.elevator[target].addQueue(i + 1);
                        }
                    }
                }
            }
            if (Building.floor[i].isQDownEmpty() == false) {
//                System.out.println("DOWN");
                d = 0;
                buff = Building.num_floor;
                for (int j = 0; j < Building.elevator.length; j++) {
//                    System.out.println("j = " + j);
                    if (Building.elevator[j].getState().equals("idle") || (Building.elevator[j].getState().equals("down") && Building.elevator[j].FloornumPosi() > Building.floor[i].getFloornum())) {
                        d = Math.abs(Building.elevator[j].FloornumPosi() - Building.floor[i].getFloornum());
                        if (d < buff) {
//                            System.out.println("d < buff");
                            buff = d;
                            target = j;
                        }
                    }
                    if ((j == Building.elevator.length - 1) && (buff < Building.num_floor)) {
//                        System.out.println("add queue");
                        if (d > 0) {
                            Building.elevator[target].addQueue(i + 1);
                        }
                    }
                }
            }
            i--;
            if (i < 0) {
                i = Building.num_floor - 1;
            }
            TimeUnit.MICROSECONDS.sleep(20000);
            while (stopflag == 0) {
                System.out.print("");
            };
        }
    }

    public static void setServiceAllElevators(Elevator[] elevators, int floor) {
        for (int i = 0; i < elevators.length; i++) {
            if (elevators[i].getState().equals("idle")) {
                elevators[i].addQueue(floor);
            }
        }
    }

    public static void setServiceSomeElevators(Elevator[] elevators, int floor) {
        for (int i = 0; i < elevators.length * 3 / 4; i++) {
            if (elevators[i].getState().equals("idle")) {
                elevators[i].addQueue(floor);
            }
        }
    }

}
