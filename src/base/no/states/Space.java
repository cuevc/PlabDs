package base.no.states;

import java.util.ArrayList;
import java.util.List;

/**
 * Space is the lowest level of the Composite between Area (abstract), Partition and Space.
 * A Space instance can have a list of Doors and its Father is a Partition.
 */
public class Space extends Area {
  private ArrayList<Door> doorList; // List of Doors in this instance of Space.
  private String spaceName; // The name of this Space
  private Area partFather;  // It's Father (it's a Partition)

  public Space(String mySpaceName, List<Door> doors, Partition partitionFather) {
    spaceName = mySpaceName;
    doorList = (ArrayList<Door>) doors;
    partFather = partitionFather;
  }


  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  // Sets the father of the current Space, useful to make the building Areas tree.
  @Override
  public void setFather(Area father) {
    partFather = father;
  }

  // Get the current Areas List
  @Override
  public ArrayList<Area> getAreaList() {
    return null;
  }

  // Get the name of this instance of Space.
  @Override
  public String getPartitionName() {
    return spaceName;
  }

  // In this case, this is not a special getter method because Space
  // has a DoorList that we can return without any recursive call.
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doorList;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  // Due to the fact that Space doesn't have Spaces or Partitions, we don't use this method
  // with an Space instance, but we have to implement it somehow because Area
  // (the father class of Space) has it.
  @Override
  public Partition findPartitionById(String id, Area rootArea) {
    return null;
  }

}
