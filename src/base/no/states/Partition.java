package base.no.states;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * Partition is a part of the Composite between Area (abstract), Partition and Space.
 * A Partition instance can have a list of Partitions or Spaces.
 */
public class Partition extends Area {
  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Partition");

  private ArrayList<Area> areaList; // List of Partitions or Spaces in this instance of Partition.
  private String partitionName;  // Name of the Partition (used to find a Partition by Id).
  private Area father; // Father of this Partition, very useful to build the building Areas tree.

  public Partition(String partName, ArrayList<Area> areas, Area fatherNode) {
    partitionName = partName;
    areaList = areas;
    father = fatherNode;
  }

  // =====================================================
  // ||              Setters and Getters                ||
  // =====================================================

  // Sets the father of the current Partition, useful to make the building Areas tree.
  @Override
  public void setFather(Area father) {
    this.father = father;
  }

  // Get the current Areas List
  public ArrayList<Area> getAreaList() {
    return areaList;
  }

  // Get the name of this instance of Partition.
  @Override
  public String getPartitionName() {
    return partitionName;
  }

  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  // Adds an Area to the current AreasList.
  void addArea(Area addingArea) {
    this.areaList.add(addingArea);
  }

  @Override
  public void accept(Visitor v) {
    v.visitPartition(this);
  }


  public JSONObject toJson(int depth) {
    // for depth=1 only the root and children,
    // for recusive = all levels use Integer.MAX_VALUE
    JSONObject json = new JSONObject();
    json.put("class", "partition");
    json.put("id", partitionName);
    JSONArray jsonAreas = new JSONArray();
    if (depth > 0) {
      for (Area a : areaList) {
        jsonAreas.put(a.toJson(depth - 1));
      }
      json.put("areas", jsonAreas);
    }
    return json;
  }

}

