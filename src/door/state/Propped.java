package door.state;

import base.no.states.Door;


/**
 * Door State consequence of UnlockedShortly, just in case that the Door can't be locked passed the time due the Door is open.
 */
public class Propped extends DoorState {


  protected Propped(Door door) {
    doorAttr = door;
    name = "propped";
  }

  @Override
  public void open() {
    System.out.print("Door is Propped");
  }

  @Override
  public void close() {
    doorAttr.setClosed(false);
    System.out.print("Door is Propped and Locked");
    doorAttr.setDoorState(new Locked(doorAttr));
  }

  @Override
  public void lock() {
    if (doorAttr.isClosed()) {
      doorAttr.setDoorState(new Locked(doorAttr));
      System.out.println("Door Locked");

    } else
      System.out.println("Door isn't closed");
  }

  @Override
  public void unlock() {
    System.out.print("Door is Propped");
  }

  @Override
  public void propped() {
    System.out.print("Door is Propped");
  }

  @Override
  public void unlockedShortly() {
    System.out.println("Door is Prooped");
  }
}
