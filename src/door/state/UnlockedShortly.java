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
    logger.debug("UnlockedShortly asked a Clock instance (getInstance()).");
    doorAttr = door;
    name = "unlocked_shortly";

    clock.addObserver(this);
    Thread threadClock = new Thread(clock);
    threadClock.start();
  }

  @Override
  public void open() {
    doorAttr.setClosed(false);
    logger.info("Door opened");
    logger.debug("Door opened");
    //System.out.println("Door Opened");
  }

  @Override
  public void close() {
    doorAttr.setClosed(true);
    logger.info("Door Closed");
    logger.debug("Door Closed");
    //System.out.println("Door Closed");
  }

  @Override
  public void lock() {
    doorAttr.setDoorState(new Locked(doorAttr));
    logger.info("Door Locked");
    logger.debug("Door Locked");
    //System.out.println("Door Locked");
  }

  @Override
  public void unlock() {
    doorAttr.setDoorState(new Unlocked(doorAttr));
    logger.info("Door Unlocked");
    logger.debug("Door Unlocked");
    //System.out.println("Door Unlocked");
  }

  @Override
  public void propped() {
    doorAttr.setDoorState(new Propped(doorAttr));
    logger.info("Door Propped");
    logger.debug("Door Propped");
    //System.out.println("Door Propped");
  }

  @Override
  public void unlockedShortly() {
    logger.debug("Door already unlocked");
    logger.info("Door already unlocked");
    //System.out.println("Door already unlocked");
  }

  @Override
  public void update(Observable o, Object arg) {
    
    //System.out.println("Time runs out");
    logger.debug("Time is running");
    //if the door is closed, it will be locked
    if (doorAttr.isClosed()) {
      logger.debug("The door is closed");
      lock();

    } else {
      logger.debug("The doow has not been closed on time, so now it's propped");
      //if door is not closed after 10 sec, It will be propped
      propped();
    }
  }
}
