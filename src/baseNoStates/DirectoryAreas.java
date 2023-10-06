package baseNoStates;

import java.util.ArrayList;

public class DirectoryAreas {
    private ArrayList<Door> allDoors;
    private Area rootArea;

    public static Area findAreaById(String areaId) {
        return null;
    }

    public void makeAreas(){

    }

    public Door findDoorById(String id) {
        // We search the given Door on the ArrayList of allDoors.
        if (allDoors.contains(new Door(id))) {
            return new Door(id);
        }
        else {
            return null;
        }
    }
}
