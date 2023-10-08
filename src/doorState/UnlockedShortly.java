package doorState;
import baseNoStates.Door;

public class UnlockedShortly extends DoorState{

    public UnlockedShortly(Door door){
        doorAttr=door;
        doorAttr.setClosed(false);
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
}
