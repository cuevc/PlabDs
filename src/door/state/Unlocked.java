package door.state;

import base.no.states.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Unlocked Class refers to the Door State that allows the door to be opened.
 */
public class Unlocked extends DoorState {
  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Unlocked");
  public Unlocked(Door door) {
    doorAttr = door;
    door.setClosed(true);
    name = "unlocked";
  }

  @Override
  public void open() {
    if (doorAttr.getStateName().equals(States.UNLOCKED)) {
      // We can open the Door freely.
      if (doorAttr.isClosed()) {
        doorAttr.setClosed(false);
      }
    }
  }

  @Override
  public void close() {
    // We can close the Door freely.
    doorAttr.setClosed(true);
    logger.info("Door closed.");
    logger.debug("Unlocked close() -> Door closed.");
  }

  @Override
  public void lock() {

    //only if Door is closed, it could be locked
    if (doorAttr.isClosed()) {
      doorAttr.setDoorState(new Locked(doorAttr));
      logger.info("Door locked");
      logger.debug("Unlocked lock() -> Door is closed. It can be locked. Door is locked.");
      //System.out.println("Door locked");

    } else {
      // Can't lock the Door. The Door is open/propped.
      logger.info("Can't lock the door.");
      logger.debug("Unlocked lock() -> Door can't be locked. Door is open/propped.");
      logger.warn("Unlocked lock() -> Trying to lock: Door can't be locked. Door is open/propped.");
      //System.out.println("Door can't be locked. Door is open/propped.");
    }

  }

  @Override
  public void unlock() {
    logger.info("Door already unlocked.");
    logger.debug("Unlocked unlock() -> Door already unlocked.");
    //System.out.println("Door already unlocked");

  }

  @Override
  public void propped() {
    doorAttr.setDoorState(new Propped(doorAttr));
    logger.info("Door Propped.");
    logger.info("Unlocked propped() -> Door Propped.");
    //System.out.println("Door Propped");

  }

  @Override
  public void unlockedShortly() {
    logger.info("Door already unlocked");
    logger.debug("Unlock unlockedShortly() -> Door already unlocked");
    System.out.println("Door already unlocked");
  }

}
