package door.state;

import base.no.states.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Door State consequence of UnlockedShortly, just in case that the Door can't
 * be locked passed the time due the Door is open.
 */
public class Propped extends DoorState {

  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Propped");

  protected Propped(Door door) {
    doorAttr = door;
    name = "propped";
  }

  @Override
  public void open() {
    logger.info("Door is Propped.");
    logger.debug("Locked propped() -> Door is Propped.");
    //System.out.print("Door is Propped");
  }

  @Override
  public void close() {
    doorAttr.setClosed(false);
    logger.info("Door is Propped and Locked.");
    logger.debug("Propped close() -> Door is Propped and Locked.");
    //System.out.print("Door is Propped and Locked");
    doorAttr.setDoorState(new Locked(doorAttr));
  }

  @Override
  public void lock() {
    if (doorAttr.isClosed()) {
      doorAttr.setDoorState(new Locked(doorAttr));
      logger.info("Door Locked.");
      logger.debug("Propped lock() -> Trying to Lock: Door Locked.");
      //System.out.println("Door Locked");

    } else {
      logger.info("Door isn't closed.");
      logger.debug("Propped lock() -> Trying to Lock: Can't lock the door.");
      logger.warn("Propped lock() -> Trying to Lock: Door isn't closed. Can't lock the door.");
      //System.out.println("Door isn't closed");
    }
  }

  @Override
  public void unlock() {
    logger.info("Door is Propped.");
    logger.debug("Propped unlock() -> Door is Propped.");
    //System.out.print("Door is Propped");
  }

  @Override
  public void propped() {
    logger.info("Door is Propped.");
    logger.debug("Propped propped() -> Door is Propped.");
    //System.out.print("Door is Propped");
  }

  @Override
  public void unlockedShortly() {
    //System.out.println("Door is Prooped");
    logger.info("Door is Propped.");
    logger.debug("Propped unlockedShortly() -> Door is Propped.");
  }
}
