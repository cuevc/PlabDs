package baseNoStates;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;


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
  public boolean youHaveThisAction(String searchAction){return adjecentGroup.getActions().contains(searchAction);}
  public boolean isOntime() {
    boolean a = adjecentGroup.getSchedules().isOnTime(LocalTime.now());
    boolean b = adjecentGroup.getSchedules().isOnDate(LocalDate.now());
    boolean c = adjecentGroup.getSchedules().getWorkDays().contains(LocalDate.now().getDayOfWeek()); // Check if the actual day is on the work days.
    return a && b && c;
  }

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
