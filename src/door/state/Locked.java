package door.state;

import base.no.states.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Locked DoorState doesn't let to open the door until its unlocked.
 */
public class Locked extends DoorState {
  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Locked");

  public Locked(Door door) {
    door.setClosed(true);
    doorAttr = door;
    name = "locked";
  }

  @Override
  public void open() {
    logger.info("Door could not be opened. It is Locked.");
    logger.debug("Locked open() -> Door could not be opened. It is Locked.");
    //System.out.println("Door could not be opened. It is Locked");
  }

  @Override
  public void close() {
    doorAttr.setClosed(true);
    logger.info("Door closed.");
    logger.debug("Locked close() -> Door closed.");
    //System.out.println("Door closed");


  }

  @Override
  public void lock() {
    logger.info("Door is already locked.");
    logger.debug("Locked lock() -> Door is already locked.");
    //System.out.println("Door is already locked.");
  }

  @Override
  public void unlock() {
    doorAttr.setDoorState(new Unlocked(doorAttr));
    logger.info("Door unlocked.");
    logger.debug("Locked unlocked() -> Door unlocked.");
    //System.out.println("Door unlocked.");
  }

  @Override
  public void propped() {
    logger.info("Door locked. It could not be Propped.");
    logger.debug("Locked propped() -> Door locked. It could not be Propped.");
    //System.out.println("Door locked. It could not be Propped.");
  }

  @Override
  public void unlockedShortly() {
    doorAttr.setDoorState(new UnlockedShortly(doorAttr));
    logger.info("Door Unlocked shortly.");
    logger.debug("Locked unlockedShortly() -> Door Unlocked shortly.");
    //System.out.println("Door Unlocked shortly.");
  }

}
