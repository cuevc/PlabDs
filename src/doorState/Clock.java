package doorState;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Observable;

import static java.lang.Thread.sleep;

/**
 *
 * The 'Clock' class is responsible for counting the time that passes since it was called and notifying the 'Observer'
 * instance when 10 seconds have passed. For this reason, 'Clock' is an 'Observable' class and the 'UnlockedShortly'
 * class is the one that will be notified.
 */
public class Clock extends Observable implements Runnable{

    public Clock(){}

    @Override
    public void run() {
        Timer timer= new Timer();

        TimerTask task= new TimerTask(){
            @Override
            public void run(){

                System.out.println("NOTIFY to observers: ");
                setChanged();
                notifyObservers();
                timer.cancel();//stop the timertask, it will only be executed once
            };
        };
        timer.scheduleAtFixedRate(task, 10000, 10000);

    }

}
