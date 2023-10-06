package baseNoStates;

public abstract class DoorState {
    protected Door door;
    protected String name;
    protected abstract void DoorSate(Door door);

    public abstract void open();

    public abstract void close();

    public abstract void lock();

    public abstract void unlock();
}
