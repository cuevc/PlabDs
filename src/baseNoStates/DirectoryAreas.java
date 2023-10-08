package baseNoStates;

import java.util.ArrayList;

public class DirectoryAreas {
    private static ArrayList<Door> allDoors;
    private static Area rootArea;

    public static Area findAreaById(String areaId) {
        return null;
    }

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
        Space parking = new Space("parking",new ArrayList<Door>(){{add(initializer.findDoorById("D1"));add(initializer.findDoorById("D2"));}}, "basement");
        Space hall = new Space("hall",new ArrayList<Door>(){{add(initializer.findDoorById("D3"));add(initializer.findDoorById("D4"));}}, "ground floor");
        Space room1 = new Space("room1",new ArrayList<Door>(){{add(initializer.findDoorById("D5"));}}, "ground floor");
        Space room2 = new Space("room2",new ArrayList<Door>(){{add(initializer.findDoorById("D6"));}}, "ground floor");
        Space room3 = new Space("room3",new ArrayList<Door>(){{add(initializer.findDoorById("D8"));}}, "floor1");
        Space corridor = new Space("corridor",new ArrayList<Door>(){{add(initializer.findDoorById("D7"));}}, "floor1");
        Space IT = new Space("IT",new ArrayList<Door>(){{add(initializer.findDoorById("D9"));}}, "floor1");

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


        Partition basement = new Partition("basement", basementSpace, null, "building" );
        Partition groundFloor =  new Partition("ground floor", groundFloorSpace, null, "building" );
        Partition floor1 = new Partition("floor1", floor1Space, null, "building" );
        Partition stairs =  new Partition("stairs", null, null, "building" );
        Partition exterior =  new Partition("exterior", null, null, "building" );


        // Declaring the Root partitions.
        ArrayList<Partition> buildingPartitions = new ArrayList<Partition>();
        buildingPartitions.add(basement);
        buildingPartitions.add(groundFloor);
        buildingPartitions.add(floor1);
        buildingPartitions.add(stairs);
        buildingPartitions.add(exterior);

        //declaring rootArea
        rootArea =  new Partition("building", null, buildingPartitions, null );

    }

    public Door findDoorById(String id) {
        // We search the given Door on the ArrayList of allDoors.
        if (allDoors.contains(new Door(id))) {
            return new Door(id);
        }
        else {
            return null;
        }
        return null;
    }
}
