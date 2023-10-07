package doorState;

import baseNoStates.Door;

public class Propped extends DoorState{


    protected Propped(Door door) {
        doorAttr=door;
        doorAttr.setClosed(false);//if it is Propped, it could not be open.
    }

    @Override
    public void open() {
        System.out.print("Door is Propped");
    }

    @Override
    public void close() {
        System.out.print("Door could not be closed");
    }

    @Override
    public void lock() {
        System.out.print("Door could not be locked");
    }

    @Override
    public void unlock() {
        System.out.print("Door is Propped");
    }

    @Override
    public void propped(){
        System.out.print("Door is Propped");
    }
}
