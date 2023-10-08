package baseNoStates;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class Clock extends Observable implements Runnable{

    @Override
    public void run() {
        Timer timer= new Timer();
        TimerTask task= new TimerTask(){
            @Override
            public void run(){

                //the thread waits 10 seconds to notify doors
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                notifyObservers(true);
            };
        };

        timer.scheduleAtFixedRate(task, 10, 1000);
    }
}
