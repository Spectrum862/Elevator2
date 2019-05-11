import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Display extends JPanel implements Runnable {
    public void run(){
        while (true){
           Building.display();
//            try {
//                Building.display();
//                TimeUnit.SECONDS.sleep(1);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
