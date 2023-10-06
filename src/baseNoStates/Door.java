package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;


public class Door {
  private String id;
  private boolean closed; // physically

  // In order to determine the States we add a DoorState attribute.
  private DoorState doorState;

  // From and To: to know from where to where the Door has access.
  private String from;
  private String to;


  public Door(String door_id, String door_from, String door_to){
    id = door_id;
    from = door_from;
    to = door_to;
    closed = true;
    doorState = new Unlocked();
  }

  public Door(String id) {
    this.id = id;
    closed = true;
    doorState = new Unlocked(); // Initialize doorState attribute as Unlocked.
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if (closed) {
          closed = false;
        } else {
          System.out.println("Can't open door " + id + " because it's already open");
        }
        break;
      case Actions.CLOSE:
        if (closed) {
          System.out.println("Can't close door " + id + " because it's already closed");
        } else {
          closed = true;
        }
        break;
      case Actions.LOCK:
        // TODO
        // fall through
      case Actions.UNLOCK:
        // TODO
        // fall through
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return "unlocked";
  }

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

  // GETTERS AND SETTERS
  public DoorState getDoorState() {
    return doorState;
  }
  public void setDoorState(DoorState doorState) {
    this.doorState = doorState;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public String gitId() {
    return null;
  }
}
