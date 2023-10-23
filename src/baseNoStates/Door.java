package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.Observer;
import java.util.Observable;


public class Door implements Observer {
  private final String id;
  private boolean closed; // physically
  private Area to;
  private Area from;

  // In order to determine the States we add a DoorState attribute.
  private DoorState doorState;

  public Door(String id){
    this.id = id;
    this.to = null;
    this.from = null;

    // Initialize doorState attribute as Locked and closed.
    closed = true;
    doorState = new Locked(this);
  }

  public Door(String id, Area to, Area from) {
    this.id = id;
    this.to = to;
    this.from = from;

    // Initialize doorState attribute as Locked and closed.
    closed = true;
    doorState = new Locked(this);
  }
  public Door(Door door) {
    this.id = door.id;

    // Initialize doorState attribute as Locked and closed.
    closed = door.getClosed();
    doorState = door.getDoorState();
  }

  public void setTo(Area to){this.to = to;}
  public void setFrom(Area from){this.from = from;}

  public Area getTo(){return this.to;}
  public Area getFrom(){return this.from;}


  @Override
  public void update(Observable o, Object arg) {

    //if the door is closed, it will be locked
    if(getClosed()){
      getDoorState().lock();

    }else{

      getDoorState().propped();
    }
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
  public Boolean getClosed(){ return this.closed; }

}
