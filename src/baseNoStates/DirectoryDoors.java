package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;

  public DirectoryDoors makeDoors() {
    // basement
    Door d1 = new Door("D1", null, null); // exterior, parking
    Door d2 = new Door("D2", null, null); // stairs, parking

    // ground floor
    Door d3 = new Door("D3", null, null); // exterior, hall
    Door d4 = new Door("D4", null, null); // stairs, hall
    Door d5 = new Door("D5", null, null); // hall, room1
    Door d6 = new Door("D6", null, null); // hall, room2

    // first floor
    Door d7 = new Door("D7", null, null); // stairs, corridor
    Door d8 = new Door("D8", null, null); // corridor, room3
    Door d9 = new Door("D9", null, null); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
    return this;
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

  /*
  public Door getDoor(String id){
    Door returned = new Door("null");
    if (found == true) {

    }
    }
    return Door;
  }

   */

}
