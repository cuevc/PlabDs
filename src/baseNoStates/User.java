package baseNoStates;
import baseNoStates.requests.Request;
import baseNoStates.requests.RequestReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private Group userGroup;

  public User(String name, String credential, Group groupies) {
    this.name = name;
    this.credential = credential;
    this.userGroup = groupies;
  }

  public String getNameGroup() {return userGroup.getTypeGroup();}
  public boolean youHaveThisAction(String searchAction, RequestReader req){


    boolean result = userGroup.getActions().contains(searchAction);
    if (!result){
      req.addReason("This user can't make this Action: " + searchAction+ ". ");
    }
    return result;
  }

public boolean[] hasAccess(Door door, LocalDate date, LocalTime hour, String action){
    return userGroup.hasAccessToArea(door, date, hour, action);}
  public String getCredential() {
    return credential;
  }

  public Boolean hasPermision(String act){
    Boolean aux = userGroup.hasAction(act);
    return aux;
  }

  public boolean canBeInSpace(Door searchDoor)
  {
    boolean foundDoor = false;
    ArrayList<Area> myAreas = userGroup.getSpaces();
    for( Area currentArea : myAreas){
      //get me the list of spaces of the current area
      ArrayList<Area> spacesList = currentArea.getAreaList();
      if (spacesList != null)
      {
        for(Area eachSpace : spacesList){
          if (eachSpace != null){
            System.out.println(eachSpace);
          ArrayList<Door> doorsList = eachSpace.getDoorsGivingAccess();

            for(Door eachDoor : doorsList)
            {
              if(eachDoor.getId().equals(searchDoor.getId())){
                System.out.println("bicho pelao");
                foundDoor = true;
                return foundDoor;
              }
            }
          }
        }

      }
      // throw new RuntimeException("Not a Space or Door.");
    }
    return foundDoor;
  }

  public String getName() { return name;}
  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }


}
