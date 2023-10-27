package baseNoStates;


import java.time.LocalDate;
import java.time.LocalTime;


import java.util.ArrayList;


/**
 * The User class contains the crucial information of a worker.
 */
public class User {
  private final String name;  // The name of the worker.
  private final String credential;  // The credential or Id given to this worker by the company.
  private Group userGroup;  // The group of this User. The group contains information about the group where this worker belongs in the company.(Permissions, actions, schedule, etc).

  public User(String name, String credential, Group groupies) {
    this.name = name;
    this.credential = credential;
    this.userGroup = groupies;
  }

  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  public String getCredential() {
    return credential;
  }

  public String getName() { return name;}

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  public boolean hasAccess(Area areaToAcces,LocalDate date, LocalTime hour, String action, ArrayList<String> reason)
  {
    //This function is calling another action from Group
    //This checks if the destination area is inside the users group area list
    //At the beginning of the first iteration of this function is null, because once it's executed it will get the first
    //element of the areas tree.
    boolean access = false;

    //For those users that doesn't have access to areas (Blank). They have access to no area (their areas tree is null)
    //so we have to check it before trying to iterate.
    if(userGroup.getSpaces() != null)
    {
      for(Area actualArea : this.userGroup.getSpaces()) {
        if(reason.isEmpty() && !access){
          access =  userGroup.hasAccessToArea(areaToAcces.getPartitionName(), actualArea ,date, hour, action, reason);
        }
        else {
          break;
        }
      }
    }
    //If reason is still empty and access is false it means that area has not been found, so the User doesn't have acces to this Area.
    if(reason.isEmpty() && !access){
      reason.add("You don't have access to this area");
    }
    return access;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }


}
