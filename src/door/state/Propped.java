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
    logger.info("Door {} is Propped.", this.doorAttr.getId());
    logger.debug("open() => Door {} is Propped.", this.doorAttr.getId());
    //System.out.print("Door is Propped");
  }

  @Override
  public void close() {
    doorAttr.setClosed(false);
    logger.info("Door {} was propped and now is Locked.", this.doorAttr.getId());
    logger.debug("close() => Door {} was propped and now is Locked.", this.doorAttr.getId());
    //System.out.print("Door is Propped and Locked");
    doorAttr.setDoorState(new Locked(doorAttr));
  }

  @Override
  public void lock() {
    if (doorAttr.isClosed()) {
      doorAttr.setDoorState(new Locked(doorAttr));
      logger.info("Door {} Locked.", this.doorAttr.getId());
      logger.debug("lock() => Trying to Lock: Door{} Locked.", this.doorAttr.getId());
      //System.out.println("Door Locked");

    } else {
      logger.info("Door {} isn't closed.", this.doorAttr.getId());
      logger.debug("lock() -> Trying to Lock: Can't lock the door {} is it open",
          this.doorAttr.getId());
      //logger.warn("lock() -> Trying to Lock: Door isn't closed. Can't lock the door.");
      //System.out.println("Door isn't closed");
    }
  }

  @Override
  public void unlock() {
    logger.info("Door{} is Propped.", this.doorAttr.getId());
    logger.debug("unlock() -> Door {} is Propped, unlock it's not possible", this.doorAttr.getId());
    //System.out.print("Door is Propped");
  }

  @Override
  public void propped() {
    logger.info("Door {} is Propped.", this.doorAttr.getId());
    logger.warn("propped() -> Door {} is Propped.", this.doorAttr.getId());
    //System.out.print("Door is Propped");
  }

  @Override
  public void unlockedShortly() {
    //System.out.println("Door is Prooped");
    logger.info("Door {} is Propped.", this.doorAttr.getId());
    logger.debug("unlockedShortly() -> Door {} is Propped, not unlock shortly "
        + "until close.", this.doorAttr.getId());
  }
}
