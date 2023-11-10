package doorState;

import baseNoStates.Door;


/**
 * The Locked DoorState doesn't let to open the door until its unlocked.
 */
public class Locked extends DoorState {

    public Locked(Door door){
        door.setClosed(true);
        doorAttr=door;
        name="locked";
    }
    @Override
    public void open(){
        System.out.println("Door could not be opened. It is Locked");
    }

    @Override
    public void close(){
        doorAttr.setClosed(true);
        System.out.println("Door closed");


    }

    @Override
    public void lock(){
        System.out.println("Door is already locked");
    }
    @Override
    public void unlock(){
        doorAttr.setDoorState(new Unlocked(doorAttr));
        System.out.println("Door unlocked");
    }

    @Override
    public void propped() {
        System.out.println("Door locked, It could not be Propped");
    }

    @Override
    public void unlockedShortly() {
        doorAttr.setDoorState(new UnlockedShortly(doorAttr));
        System.out.println("Door Unlocked shortly");
    }

}
