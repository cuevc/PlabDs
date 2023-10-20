package baseNoStates;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private Group adjecentGroup;

  public User(String name, String credential, Group groupies) {
    this.name = name;
    this.credential = credential;
    this.adjecentGroup = groupies;
  }

  public String getNameGroup() {return adjecentGroup.getTypeGroup();}

=======
  public boolean hasAccess(Area areaToAcces,LocalDate date, LocalTime hour, String action, ArrayList<String> reason)
  {
    //This function is calling another action from Group
    //This checks if the destination area is inside the users group area list
    //At the beggining of the first iteration of this function is null, because once it's executed it will get the first
    //element of the areas tree.
    boolean access = false;
    for(Area actualArea : this.userGroup.getSpaces()) {
      if(reason.isEmpty() && !access){
        access =  userGroup.hasAccessToArea(areaToAcces.getPartition_name(), actualArea ,date, hour, action, reason);
      }
      else {
        break;
      }
    }

    //If reason is still empty and permission is fall it means that area has not been found
    if(reason.isEmpty() && !access){
      reason.add("You don't have access to this area");
    }

    return access;
  }
>>>>>>> parent of ed36531 (We correct null tree and ground_floor)
  public String getCredential() {
    return credential;
  }

  public Boolean hasPermision(String act){
    Boolean aux = adjecentGroup.hasAction(act);
    return aux;
  }

  public boolean canBeInSpace(Door searchDoor)
  {
    boolean foundDoor = false;
    ArrayList<Area> myAreas = adjecentGroup.getSpaces();
    for( Area currentArea : myAreas){
      //get me the list of spaces of the current area
      ArrayList<Space> spacesList = currentArea.getSpacesList();
      if (spacesList != null)
      {
        for(Space eachSpace : spacesList){
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
