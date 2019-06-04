
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Spectrum
 */
public class Infoupdate implements Runnable{

    @Override
    public void run() {
        while(true){
        try {
            TimeUnit.MICROSECONDS.sleep(2500);
            updateinfo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Infoupdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    private synchronized void updateinfo() {
        Building.mainui.setTime();
        Building.mainui.setInfo();
    }
    
}
