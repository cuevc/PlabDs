package doorState;
import baseNoStates.Door;

public class UnlockedShortly extends DoorState{

    private static Clock clock;

    public UnlockedShortly(Door door){
        doorAttr=door;
        doorAttr.setClosed(true);
        name="Unlocked shortly";
        clock= new Clock();
        clock.addObserver(doorAttr);

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
}
