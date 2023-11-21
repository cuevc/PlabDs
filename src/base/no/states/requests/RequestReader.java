package base.no.states.requests;

import base.no.states.User;
import base.no.states.DirectoryDoors;
import base.no.states.DirectoryUsers;
import base.no.states.Door;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public ArrayList<String> getReasons() { return reasons; }
  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }

  public boolean setAllAddReason(boolean[] printable) {
    String[] reasons = {"This user can't make this action", "At this moment the user "
        + "doesn't have access. Try in a day/hour of your schedule.",
        "You don't access to this area"};
    boolean allFalse = true;
    for (int i = 0; i < printable.length; i++) {
      if (printable[i]) {
        addReason(reasons[i]);
      } else {
        allFalse = false;
      }
    }
    return allFalse;
  }

  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
        + "credential=" + credential
        + ", userName=" + userName
        + ", action=" + action
        + ", now=" + now
        + ", doorID=" + doorId
        + ", closed=" + doorClosed
        + ", authorized=" + authorized
        + ", reasons=" + reasons
        + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    User user = DirectoryUsers.findUserByCredential(credential);
    Door door = DirectoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    //Check if this user is not null
    if (user == null) {
      authorized = false;
      addReason("User doesn't exists");
    } else { //Check if this user has the action
      LocalDate date = now.toLocalDate();
      LocalTime hour = now.toLocalTime();
      authorized = user.hasAccess(door.getTo(), date, hour, this.action, this.reasons);
      //authorized = setAllAddReason(user.hasAccess(door.getTo(), date, hour, this.action));
      //TODO: get the who, where, when and what in order to decide, and if not
      // authorized add the reason(s)
    }
  }
}


