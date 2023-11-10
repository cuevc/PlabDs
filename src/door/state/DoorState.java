package door.state;

import base.no.states.Door;


/**
 * The DoorState class is the abstract father class of all the
 * possible Door States (Locked, Unlocked, UnlockedShortly, etc.)
 */
public abstract class DoorState {
  protected Door doorAttr; // The Door associated with this DoorState
  protected String name;  // Name of the DoorState (used to see
  // the colors associated to each state in the HTML)


  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  public String getName() {
    return name;
  }


  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  // This methods will we implemented on the sons of DoorSate Class (inheritance)
  public abstract void open();

  public abstract void close();

  public abstract void lock();

  public abstract void unlock();

  public abstract void propped();

  public abstract void unlockedShortly();

}
