package doorState;

import baseNoStates.Door;

import java.util.Observable;
import java.util.Observer;

public class UnlockedShortly extends DoorState implements Observer {

    private static Clock clock;

    public UnlockedShortly(Door door){
        doorAttr=door;
        name="unlocked_shortly";
        clock= new Clock();
        clock.addObserver(this);
        Thread threadClock=new Thread(clock);
        threadClock.start();
    }
    @Override
    public void open() {
        doorAttr.setClosed(false);
        System.out.println("Door Opened");
    }

    @Override
    public void close() {
        doorAttr.setClosed(true);
        System.out.println("Door Closed");
    }

    @Override
    public void lock() {
        doorAttr.setDoorState(new Locked(doorAttr));
        System.out.println("Door Locked");
    }

    @Override
    public void unlock() {
        doorAttr.setDoorState(new Unlocked(doorAttr));
        System.out.println("Door Unlocked");
    }

    @Override
    public void propped() {
        doorAttr.setDoorState(new Propped(doorAttr));
        System.out.println("Door Propped");
    }

    @Override
    public void unlockedShortly() {
        System.out.println("Door already unlocked");
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Time runs out");

        //if the door is closed, it will be locked
        if(doorAttr.isClosed()){
            lock();

        }else{
            //if door is not closed after 10 sec, It will be propped
            propped();
        }
    }
}
