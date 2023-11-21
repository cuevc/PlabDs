package base.no.states;

import base.no.states.requests.RequestReader;
import door.state.Actions;
import door.state.DoorState;
import door.state.Locked;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Door is placed on a Space. The Door also have States to control if
 * its Open, Closed, Locked, Unlocked, etc.
 * Also has the information of which Area is going to access, and from where.
 * <br/>
 * Door has some attributes:
 * <ul>
 *     <li><a href="Area">Area </a> to -> To which Area the door has access (or is placed).</li>
 *     <li><a href="Area">Area </a> from -> From which Area the door has access (or is placed).</li>
 *     <li><a href="DoorState">DoorState </a> doorState -> In order to determine the States
 *     we add a DoorState attribute.</li>
 * </ul>
 *
 * @author Pol Colomer Campoy
 * @author Gerard Josep Guarin Velez
 * @author Josias Micael Cueva Castro
 */
public class Door {
  private final String id;  // Useful to search a certain door by id.
  private boolean closed;  // If true, that means that the door is physically closed.
  private Area to;
  private Area from;

  // In order to determine the States we add a DoorState attribute.
  private DoorState doorState;

  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Door");


  public Door(String id) {
    this.id = id;
    this.to = null;
    this.from = null;

    // Initialize doorState attribute as Locked and closed.
    doorState = new Locked(this);
  }


  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  public void setTo(Area to) {
    this.to = to;
  }

  public void setFrom(Area from) {
    this.from = from;
  }

  public Area getTo() {
    return this.to;
  }

  public Area getFrom() {
    return this.from;
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return doorState.getName();
  }

  public DoorState getDoorState() {
    return doorState;
  }

  /**
   * Setter of the `doorState` attribute. May set the door in a Locked,
   * Unlocked, UnlockedShortly, etc. State.

   * @param doorState <a href="doorState.DoorState">DoorState</a> instance.
   * @see door.state.DoorState
   */
  public void setDoorState(DoorState doorState) {
    this.doorState = doorState;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public Boolean getClosed() {
    return this.closed;
  }


  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================
  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }


  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      //String reasonRequest = request.getReasons().get(0);
      logger.info("This user is not authorized to this door: {} . The reason is: {}" , id, request.getReasons());
      //System.out.println("not authorized");

    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        doorState.open();
        break;
      case Actions.CLOSE:
        doorState.close();
        break;
      case Actions.LOCK:
        // TODO
        doorState.lock();
        break;
      case Actions.UNLOCK:
        // TODO
        doorState.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        doorState.unlockedShortly();
        //System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }
}
