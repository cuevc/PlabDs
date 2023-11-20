package base.no.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * This class contains Users in a Group. Each group has determined
 * permissions, access areas and Schedules.
 */
public class Group {
  static Logger logger = LoggerFactory.getLogger("base.no.states.Group");

  // The name of this type of Group
  private String typeGroup;
  // The list of Users that belong to this Group. (who)
  private ArrayList<User> users;
  // The determined schedules of the Users of this Group (when they can access).
  private Schedule schedules;
  // The determined actions that the Users of this Group can make (what).
  private ArrayList<String> actions;
  // The determined areas that the Users of this Group can access (where).
  private ArrayList<Area> accesAreas;

  public Group(String name, Schedule setSchedule,
               ArrayList<String> operativeActions, ArrayList<Area> myArea) {
    typeGroup = name;
    schedules = setSchedule;
    actions = operativeActions;
    users = new ArrayList<>();
    accesAreas = myArea;
  }

  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  ArrayList<Area> getSpaces() {
    return accesAreas;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  //Looking for user with the same credential and same name.
  // If already exists then return true, otherwise false.
  public boolean isOnUsers(User searchUser) {
    for (User actualUser : users) {
      if (actualUser.getCredential().equals(searchUser.getCredential())
          && actualUser.getName().equals(searchUser.getName())) {
        return true;
      }
    }
    return false;
  }

  // Search for a user with the same credential and return it if it's found
  // (Instance of user); otherwise, a null value will be returned.
  public User searchUser(String userCredential) {
    for (User actualUser : users) {
      if (userCredential.equals(actualUser.getCredential())) {
        return actualUser;
      }
    }
    //System.out.println("The user with credential: "
    //    + userCredential + " has not been found");
    return null;
  }

  // Add a User to the current userList of this Group.
  public void addUser(User newUser) {
    // Check if the User has actually been added. In this case,
    // we won't add it to the list. Otherwise, we will.
    String thisUser = newUser.toString();
    if (!isOnUsers(newUser)) {
      users.add(newUser);

      logger.info("{} this user has been added succesfully", thisUser);
      logger.debug("{} this user has been added succesfully", thisUser);

      //System.out.println(newUser.toString() + " added successfully");
    } else {
      logger.debug("{} is already on the list." , thisUser);
      logger.info("{} is already on the list." , thisUser);
      //System.out.println(newUser.toString() + " is already on the list");
    }
  }

  // Method that returns true if the user have access to an
  // Area (correct: Action, Date, Time, and current Area).
  public boolean hasAccessToArea(String areaToAccess, Area currentArea, LocalDate date,
                                 LocalTime hour, String action, ArrayList<String> reason) {
    boolean permissionConceded = false;
    if (currentArea.getPartitionName().equals(areaToAccess)) {
      // In this 'if' we check if the user can do the
      // action required and if is on time and date
      if (this.actions.contains(action)) {
        if (this.schedules.isOnTime(hour)) {
          if (this.schedules.isOnDate(date)) {
            permissionConceded = true;
          } else {
            reason.add("This user is not on date");
          }
        } else {
          reason.add("This user is not on hour");
        }
      } else {
        reason.add("This user can't do the action: " + action);
      }
      //If we are in a Space class and does exist partition list it
      // will return null.
    } else if (currentArea.getAreaList() != null) {

      for (Area actualArea : currentArea.getAreaList()) {
        //General case: if reason is not empty and permissionConceded
        // is false, means that area has not been found
        if (reason.isEmpty() && !permissionConceded) {
          permissionConceded = hasAccessToArea(areaToAccess,
              actualArea, date, hour, action, reason);
        } else {
          break;
        }
      }
    }
    logger.debug("The value of the permission is: {}" , permissionConceded);
    return permissionConceded; // If true then the user have
    // access to this area else, the user doesn't have access.
  }
}
