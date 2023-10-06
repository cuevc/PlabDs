package doorState;

import baseNoStates.Door;

public abstract class DoorState {
    protected Door doorAttr;
    protected String name;

    public abstract void open();

    public abstract void close();

    public abstract void lock();

    public abstract void unlock();

    public abstract void propped();

}
