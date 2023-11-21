package door.state;

// import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The 'Clock' class is responsible for counting the time that passes since it was called and
 * notifying the 'Observer' instance when 10 seconds have passed. For this reason, 'Clock' is
 * an 'Observable' class and the 'UnlockedShortly' class is the one that will be notified.
 * Singleton pattern lazy version is implemented on Clock, in order to have just one instance of it.
 */
public class Clock extends Observable implements Runnable {
  // This attribute will control the creation of just one instance
  private static Clock uniqueClock = null;
  static Logger logger = LoggerFactory.getLogger("door.state.Observable.Clock");
  static Logger logger2milestone = LoggerFactory.getLogger("milestone2.Observable.Clock");

  private Clock() {
  }

  // Public accessible method for instance in a lazy way the Clock instance
  public static Clock getInstance() {
    if (uniqueClock == null) {
      uniqueClock = new Clock();
      logger2milestone.debug("Clock getInstance() => Created first instance of Clock.");
    }
    return uniqueClock;
  }

  @Override
  public void run() {
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
      @Override
      public void run() {

        logger.debug("Clock run() => Clock NOTIFIES its observers");
        //System.out.println("NOTIFY to observers: ");
        setChanged();
        notifyObservers();
        timer.cancel();  // Stop the timer-task, it will only be executed once
      }

      ;
    };
    timer.scheduleAtFixedRate(task, 10000, 10000);

  }

}
