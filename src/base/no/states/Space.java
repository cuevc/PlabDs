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

  // Get the name of this instance of Space.
  @Override
  public String getPartitionName() {
    return spaceName;
  }


  public ArrayList<Door> getDoors(){
    return this.doorList;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  @Override
  public void accept(Visitor v) {
    v.visitSpace(this);
  }
}
