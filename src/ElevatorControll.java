
import java.util.concurrent.TimeUnit;


public class ElevatorControll implements Runnable {
    private Elevator elevator;

    public ElevatorControll(Elevator elevator) {
        this.elevator = elevator;
    }

    public void run() {

        while (true) {
            if ((elevator.getPosition() - elevator.getFisrt()) < 0) {
                elevator.setState("up");
                elevator.moveUp();
            }

            else  if ((elevator.getPosition() - elevator.getFisrt()) > 0) {
                        elevator.setState("down");
                        elevator.moveDown();
                    }
                    else {
                        elevator.setState("idle");
                    }
            try {

                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}


