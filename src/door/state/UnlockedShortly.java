package door.state;

import base.no.states.Door;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The UnlockedShortly DoorState (and Action) will unlock the Door for 10 seconds and
 * then lock it again. If past the time the Door is open, it will be in Propped
 * state, and whet its closed again, will be locked.
 * This class is an Observer of a Clock instance, which is an Obervable.
 */
public class UnlockedShortly extends DoorState implements Observer {
  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.UnlockedShortly");

  private static Clock clock;

  public UnlockedShortly(Door door) {
    clock = Clock.getInstance();
    logger.debug("Constructor UnlockedShortyl() => UnlockedShortly asked a Clock instance (getInstance()).");
    doorAttr = door;
    name = "unlocked_shortly";

    clock.addObserver(this);
    Thread threadClock = new Thread(clock);
    threadClock.start();
  }

  @Override
  public void open() {
    doorAttr.setClosed(false);
    logger.info("Door {} opened", this.doorAttr.getId());
    logger.debug("open() => Door {} opened", this.doorAttr.getId());
    //System.out.println("Door Opened");
  }

  @Override
  public void close() {
    doorAttr.setClosed(true);
    logger.info("Door {} Closed", this.doorAttr.getId());
    logger.debug("close() => Door {} Closed", this.doorAttr.getId());
    //System.out.println("Door Closed");
  }

  @Override
  public void lock() {
    doorAttr.setDoorState(new Locked(doorAttr));
    logger.info("Door {} Locked", this.doorAttr.getId());
    logger.debug("lock() => Door {} Locked", this.doorAttr.getId());
    //System.out.println("Door Locked");
  }

  @Override
  public void unlock() {
    doorAttr.setDoorState(new Unlocked(doorAttr));
    logger.info("Door Unlocked");
    logger.debug("unlock() => Door Unlocked");
    //System.out.println("Door Unlocked");
  }

  @Override
  public void propped() {
    logger.warn("propped() -> Door {} is Propped.", this.doorAttr.getId());

    doorAttr.setDoorState(new Propped(doorAttr));
    logger.info("Door {} is  Propped", this.doorAttr.getId());
    logger.debug("propped() => Door {} Propped", this.doorAttr.getId());
    //logger.warn("propped() => Door {} Propped", this.doorAttr.getId());
    //System.out.println("Door Propped");
  }

  @Override
  public void unlockedShortly() {
    logger.debug("unlockedShortyl() => Door {} already unlocked", this.doorAttr.getId());
    logger.info("Door {} already unlocked", this.doorAttr.getId());
    //System.out.println("Door already unlocked");
  }

  @Override
  public void update(Observable o, Object arg) {
    
    //System.out.println("Time runs out");
    logger.debug("update() => Time is running");
    //if the door is closed, it will be locked
    if (doorAttr.isClosed()) {
      logger.debug("update() => The door {} is closed", this.doorAttr.getId());
      lock();

    } else {
      logger.debug("update() => Door {} has not been closed on time, so now it's propped", this.doorAttr.getId());
      //logger.warn("propped() -> Door {} is Propped.", this.doorAttr.getId());

      //if door is not closed after 10 sec, It will be propped
      propped();
    }
  }
}
