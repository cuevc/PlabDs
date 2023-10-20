package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryAreas {
    private static ArrayList<Door> allDoors;
    private static Area rootArea;

    public static Area findAreaById(String areaId, Area areaCurrent) {
        Area aux = null;

        if(areaCurrent.getPartition_name().equals(areaId)) {
            aux = areaCurrent;
        }
        else if (areaCurrent.getAreaList() != null){
            for(Area actualArea : areaCurrent.getAreaList()){
                //General case: if reason is not empty and permissionConced is false, means that area has not been found
                if (aux == null)
                {
                    aux = findAreaById(areaId, actualArea);
                }
                else{
                    break;
                }
            }
        }
        return aux;

    }

    public static Area getRootArea (){return rootArea;}

    public static void makeAreas(){
        DirectoryDoors initializer = new DirectoryDoors();
        initializer.makeDoors();
        allDoors = initializer.getAllDoors();
        //--------------------------------//
        //Partition parking = new Partition("parking", areas, partitionForSpace, "building");


        // ==============================================================
        //              HARDCODED --- Modify in the future
        // ==============================================================

        // Spaces Definition


        Space parking = new Space("parking",new ArrayList<Door>(){{add(initializer.findDoorById("D1"));add(initializer.findDoorById("D2"));}}, null);
        Space hall = new Space("hall",new ArrayList<Door>(){{add(initializer.findDoorById("D3"));add(initializer.findDoorById("D4"));}}, null);
        Space room1 = new Space("room1",new ArrayList<Door>(){{add(initializer.findDoorById("D5"));}}, null);
        Space room2 = new Space("room2",new ArrayList<Door>(){{add(initializer.findDoorById("D6"));}}, null);
        Space room3 = new Space("room3",new ArrayList<Door>(){{add(initializer.findDoorById("D8"));}}, null);
        Space corridor = new Space("corridor",new ArrayList<Door>(){{add(initializer.findDoorById("D7"));}}, null);
        Space IT = new Space("IT",new ArrayList<Door>(){{add(initializer.findDoorById("D9"));}}, null);

        // Partitions Definition
        ArrayList<Space> basementSpace = new ArrayList<Space>();
        basementSpace.add(parking);

        ArrayList<Space> groundFloorSpace = new ArrayList<Space>();
        groundFloorSpace.add(hall);
        groundFloorSpace.add(room1);
        groundFloorSpace.add(room2);

        ArrayList<Space> floor1Space = new ArrayList<Space>();
        floor1Space.add(room3);
        floor1Space.add(corridor);
        floor1Space.add(IT);


        Partition basement = new Partition("basement",new ArrayList<>(), null );
        Partition groundFloor =  new Partition("ground_floor", new ArrayList<>(), null );
        Partition floor1 = new Partition("floor1", new ArrayList<>(), null );
        Partition stairs =  new Partition("stairs", new ArrayList<>(), null );
        Partition exterior =  new Partition("exterior",  new ArrayList<>(), null );

        basement.addArea(parking);

        groundFloor.addArea(hall);
        groundFloor.addArea(room1);
        groundFloor.addArea(room2);

        floor1.addArea(room3);
        floor1.addArea(corridor);
        floor1.addArea(IT);

        //Setting Space's Fathers
        parking.setFather(basement);

        hall.setFather(groundFloor);
        room1.setFather(groundFloor);
        room2.setFather(groundFloor);

        room3.setFather(floor1);
        corridor.setFather(floor1);
        IT.setFather(floor1);



        // Declaring the Root partitions.
        ArrayList<Area> buildingPartitions = new ArrayList<Area>();
        buildingPartitions.add(basement);
        buildingPartitions.add(groundFloor);
        buildingPartitions.add(floor1);
        buildingPartitions.add(stairs);
        buildingPartitions.add(exterior);


        // Setting From and To of doors:
        allDoors.get(0).setFrom(exterior); allDoors.get(0).setTo(parking);
        allDoors.get(1).setFrom(stairs); allDoors.get(1).setTo(parking);
        allDoors.get(2).setFrom(exterior); allDoors.get(2).setTo(hall);
        allDoors.get(3).setFrom(stairs); allDoors.get(3).setTo(hall);
        allDoors.get(4).setFrom(hall); allDoors.get(4).setTo(room1);
        allDoors.get(5).setFrom(hall); allDoors.get(5).setTo(room2);
        allDoors.get(6).setFrom(stairs); allDoors.get(6).setTo(corridor);
        allDoors.get(7).setFrom(corridor); allDoors.get(7).setTo(room3);
        allDoors.get(8).setFrom(corridor); allDoors.get(8).setTo(IT);


        //declaring rootArea
        rootArea =  new Partition("building", buildingPartitions, null );

        // Setting "Partition's Father"
        basement.setFather(rootArea);
        groundFloor.setFather(rootArea);
        floor1.setFather(rootArea);
        stairs.setFather(rootArea);
        exterior.setFather(rootArea);
    }

    public Door findDoorById(String id) {
        // We search the given Door on the ArrayList of allDoors.
        if (allDoors.contains(new Door(id, null, null))) {
            // If allDoors contains the Door we are searching, we get it and return it.
            for (Door doorFounded:allDoors) {
                if (doorFounded.getId().equals(id)){  // Searching through the id.
                    return doorFounded;
                }
            }
        }
        else {
            return null;
        }
        return null;
    }
    



}
