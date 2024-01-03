package base.no.states;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Space is the lowest level of the Composite between Area (abstract), Partition and Space.
 * A Space instance can have a list of Doors and its Father is a Partition.
 */
public class Space extends Area {
  private ArrayList<Door> doorList; // List of Doors in this instance of Space.
  private String spaceName; // The name of this Space
  private Area partFather;  // It's Father (it's a Partition)
  static Logger logger =
      LoggerFactory.getLogger("door.state.DoorState.Space");


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


  public ArrayList<Door> getDoors() {
    return this.doorList;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  @Override
  public void accept(Visitor v) {
    logger.debug("This space {} has accept a visitor", this.spaceName);
    v.visitSpace(this);
  }


  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", spaceName);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : doorList) {
      jsonDoors.put(d.toJson());
    }
    json.put("access_doors", jsonDoors);
    return json;
  }

}
