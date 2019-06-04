import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ElevatorManager implements Runnable{

    public ElevatorManager(){
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

    public void manage() throws InterruptedException{
        int i = Building.num_floor - 1;
        int d = 0; // distance
        int buff = 0;
        int target = 0;
        boolean running = true;
        while (running &&(i >= 0)) {
            if (!Building.floor[i].isQUpEmpty()) {
                d = 0;
                buff = Building.num_floor;
                for (int j = 0; j < Building.elevator.length ; j++) {
                    if(i==0){
                        if (Building.elevator[j].getState().equals("idle")||Building.elevator[j].getState().equals("down")) {
                            d = Building.elevator[j].FloornumPosi() - Building.floor[i].getFloornum();
                        }
                    } else {
                        if (Building.elevator[j].getState().equals("idle")||(Building.elevator[j].getState().equals("up")&& Building.elevator[j].FloornumPosi() < Building.floor[i].getFloornum() )) {
                            d = Building.floor[i].getFloornum() - Building.elevator[j].FloornumPosi();
                        }
                    }

                    if (d <= buff) {
                        buff = d;
                        target = j;
                    }
                }
                Building.elevator[target].addQueue(i + 1);
            }
            if (!Building.floor[i].isQDownEmpty()) {
                d = 0;
                buff = Building.num_floor;
                for (int j = 0; j < Building.elevator.length; j++) {
                    if(i==Building.num_floor-1){
                        if (Building.elevator[j].getState().equals("idle")||Building.elevator[j].getState().equals("up")) {
                            d = Building.floor[i].getFloornum() - Building.elevator[j].FloornumPosi();
                        }
                    } else {
                        if (Building.elevator[j].getState().equals("idle")||(Building.elevator[j].getState().equals("down")&&Building.elevator[j].FloornumPosi() > Building.floor[i].getFloornum() )) {
                            d = Building.elevator[j].FloornumPosi() - Building.floor[i].getFloornum();
                        }
                    }
                    if (d <= buff) {
                        buff = d;
                        target = j;
                    }
                }
                Building.elevator[target].addQueue(i + 1);
            }
            i--;
            if (i < 0) i = Building.num_floor - 1;
            TimeUnit.MICROSECONDS.sleep(2000000);
        }
    }

    public static void setServiceAllElevators(Elevator[] elevators,int floor) {
        for (int i = 0; i < elevators.length; i++) {
            if(elevators[i].getState().equals("idle")) elevators[i].addQueue(floor);
        }
    }

    public static void setServiceSomeElevators(Elevator[] elevators,int floor){
        for (int i = 0; i < elevators.length*3/4; i++) if(elevators[i].getState().equals("idle")) elevators[i].addQueue(floor);
    }

}