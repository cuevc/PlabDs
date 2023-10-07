package doorState;
import baseNoStates.Door;

public class UnlockedShortly extends DoorState{

    public UnlockedShortly(Door door){
        doorAttr=door;
        doorAttr.setClosed(false);
    }
    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

    @Override
    public void propped() {

    }
}
