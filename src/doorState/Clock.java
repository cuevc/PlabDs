package doorState;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Observable;

import static java.lang.Thread.sleep;

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
