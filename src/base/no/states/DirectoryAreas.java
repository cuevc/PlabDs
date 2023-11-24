package base.no.states;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryAreas {
  static Logger logger = LoggerFactory.getLogger("base.no.states.DirectoryAreas");
  private static ArrayList<Door> allDoors;
  private static Area rootArea;

  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  public static Area getRootArea() {
    return rootArea;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  public static Area findAreaById(String areaId, Area areaCurrent) {
    Area aux = null;

    if (areaCurrent.getPartitionName().equals(areaId)) {
      aux = areaCurrent;
    } else{
      GetAreaListVisitor getAreaListVisitor=new GetAreaListVisitor();
      areaCurrent.accept(getAreaListVisitor);
      if (getAreaListVisitor.getAreas() != null) {
        for (Area actualArea : getAreaListVisitor.getAreas()) {
          //General case: if reason is not empty and permissionConceded
          // is false, means that area has not been found
          if (aux == null) {
            aux = findAreaById(areaId, actualArea);
          } else {
            break;
          }
        }
      }
    }
    return aux;
  }

  public static void makeAreas() {

    DirectoryDoors initializer = new DirectoryDoors();
    initializer.makeDoors();  // Getting all Doors to assign them to the Spaces.
    allDoors = initializer.getAllDoors();

    // =====================================================
    // ||      Define the Spaces, the Partitions and      ||
    // ||        assign each Partition its Spaces.        ||
    // =====================================================
    Partition basement = new Partition("basement", new ArrayList<>(), null);

    Space parking = new Space("parking",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D1"));
            add(initializer.findDoorById("D2"));
          }
        }, null);
    basement.addArea(parking);

    Partition groundFloor = new Partition("ground_floor", new ArrayList<>(), null);

    Space hall = new Space("hall",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D3"));
            add(initializer.findDoorById("D4"));
          }
        }, null);
    groundFloor.addArea(hall);

    Space room1 = new Space("room1",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D5"));
          }
        }, null);
    groundFloor.addArea(room1);

    Space room2 = new Space("room2",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D6"));
          }
        }, null);
    groundFloor.addArea(room2);

    Partition floor1 = new Partition("floor1", new ArrayList<>(), null);

    Space room3 = new Space("room3",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D8"));
          }
        }, null);
    floor1.addArea(room3);

    Space corridor = new Space("corridor",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D7"));
          }
        }, null);
    floor1.addArea(corridor);

    Space spaceIt = new Space("IT",
        new ArrayList<Door>() {
          {
            add(initializer.findDoorById("D9"));
          }
        }, null);
    floor1.addArea(spaceIt);
    /*
    // Missing to declare: We will declare later, before its use.
    Partition stairs = new Partition("stairs", new ArrayList<>(), null);
    Partition exterior = new Partition("exterior", new ArrayList<>(), null);
     */

    // =====================================================
    // ||             Setting Spaces fathers              ||
    // =====================================================

    parking.setFather(basement);

    hall.setFather(groundFloor);
    room1.setFather(groundFloor);
    room2.setFather(groundFloor);

    room3.setFather(floor1);
    corridor.setFather(floor1);
    spaceIt.setFather(floor1);

    // =====================================================
    // ||          Declaring the Root partitions          ||
    // =====================================================

    // Declaring the Root partitions.
    ArrayList<Area> buildingPartitions = new ArrayList<Area>();
    buildingPartitions.add(basement);
    buildingPartitions.add(groundFloor);
    buildingPartitions.add(floor1);

    Partition stairs = new Partition("stairs", new ArrayList<>(), null);
    Partition exterior = new Partition("exterior", new ArrayList<>(), null);

    buildingPartitions.add(stairs);
    buildingPartitions.add(exterior);

    // =====================================================
    // ||          Setting From and To of Doors           ||
    // =====================================================

    allDoors.get(0).setFrom(exterior);
    allDoors.get(0).setTo(parking);

    allDoors.get(1).setFrom(stairs);
    allDoors.get(1).setTo(parking);

    allDoors.get(2).setFrom(exterior);
    allDoors.get(2).setTo(hall);

    allDoors.get(3).setFrom(stairs);
    allDoors.get(3).setTo(hall);

    allDoors.get(4).setFrom(hall);
    allDoors.get(4).setTo(room1);

    allDoors.get(5).setFrom(hall);
    allDoors.get(5).setTo(room2);

    allDoors.get(6).setFrom(stairs);
    allDoors.get(6).setTo(corridor);

    allDoors.get(7).setFrom(corridor);
    allDoors.get(7).setTo(room3);

    allDoors.get(8).setFrom(corridor);
    allDoors.get(8).setTo(spaceIt);

    // Declaring rootArea (the root of the building Areas tree)
    rootArea = new Partition("building", buildingPartitions, null);

    // =====================================================
    // ||          Setting "Partition's Father"           ||
    // =====================================================

    basement.setFather(rootArea);
    groundFloor.setFather(rootArea);
    floor1.setFather(rootArea);
    stairs.setFather(rootArea);
    exterior.setFather(rootArea);

    ArrayList<String> loggerNamePartitions = new ArrayList<>();
    for(Area p : buildingPartitions){
      loggerNamePartitions.add(p.getPartitionName());
    }

    logger.debug("makeAreas() => The areas were created: {}", loggerNamePartitions);
    logger.info("All Areas created successfully. Attribute rootArea created.");
  }

  // Find a determined Door by its Id. At this time we don't use the
  // method, but in the future may be useful, that's why we keep it.
  public Door findDoorById(String id) {
    // We search the given Door on the ArrayList of allDoors.
    if (allDoors.contains(new Door(id))) {
      // If allDoors contains the Door we are searching, we get it and return it.
      for (Door doorFounded : allDoors) {
        if (doorFounded.getId().equals(id)) {  // Searching through the id.
          return doorFounded;
        }
      }
    } else {
      return null;
    }
    return null;
  }


}
