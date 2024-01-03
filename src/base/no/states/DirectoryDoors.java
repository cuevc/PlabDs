package base.no.states;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public final class DirectoryDoors {
  static Logger logger = LoggerFactory.getLogger("base.no.states.DirectoryDoors");

  private static ArrayList<Door> allDoors;

  // Declare all the Doors and put all them in the allDoors ArrayList.
  public DirectoryDoors makeDoors() {

    // =====================================================
    // ||               Declaring the Doors               ||
    // =====================================================

    // basement
    Door d1 = new Door("D1"); // exterior, parking
    Door d2 = new Door("D2"); // stairs, parking

    // ground floor
    Door d3 = new Door("D3"); // exterior, hall
    Door d4 = new Door("D4"); // stairs, hall
    Door d5 = new Door("D5"); // hall, room1
    Door d6 = new Door("D6"); // hall, room2

    // first floor
    Door d7 = new Door("D7"); // stairs, corridor
    Door d8 = new Door("D8"); // corridor, room3
    Door d9 = new Door("D9"); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

    logger.debug("makeDoor() => This doors were created: {}", allDoors);
    logger.info("All doors created successfully and added to the allDoors attribute.");
    return this;
  }

  public static Door findDoorById(String id) {  // Finds a Door in the allDoors list by its Id.
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    logger.warn("Door with id {} not found.", id);
    // System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    //System.out.println(allDoors);
    return allDoors;
  }



}
