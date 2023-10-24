package baseNoStates;

import java.util.ArrayList;
import java.util.List;

// Space is the lowest level of the Composite between Area (abstract), Partition and Space. A Space instance can have a list of Doors and its Father is a Partition.
public class Space extends Area{
    private ArrayList<Door> doorList; // List of Doors in this instance of Space.
    private String spaceName; // The name of this Space
    private Area partFather;  // Its Father (its a Partition)

    public Space(String mySpaceName, List<Door> doors, Partition partitionFather){
        spaceName = mySpaceName;
        doorList = (ArrayList<Door>) doors;
        partFather = partitionFather;
    }


    // =====================================================
    // ||              Setters and Getters                ||
    // =====================================================

    @Override
    public void setFather(Area father) {
        partFather = father;
    } // Sets the father of the current Space, useful to make the building Areas tree.

    @Override
    public ArrayList<Area> getAreaList() {return null;} // Get the current Areas List

    @Override
    public String getPartitionName() {
        return spaceName;
    } // Get the name of this instance of Space.


    @Override
    public ArrayList<Door> getDoorsGivingAccess(){return doorList;}  // In this case, this is not a special getter method because Space has a DoorList that we can return without any recursive call.

    // =====================================================
    // ||           Other methods of this class           ||
    // =====================================================

    @Override
    public Partition findPartitionById(String id, Area rootArea) {
        return null;
    }  // Due to the fact that Space doesn't have Spaces or Partitions, we don't use this method with an Space instance, but we have to implement it somehow because Area (the father class of Space) has it.

}
