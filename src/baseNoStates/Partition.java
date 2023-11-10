package baseNoStates;

import java.util.ArrayList;


/**
 * Partition is a part of the Composite between Area (abstract), Partition and Space.
 * A Partition instance can have a list of Partitions or Spaces.
 */
public class Partition extends Area {

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
    @Override
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

    // Special Getter that gives all the Doors of the current Area.
    // This method has a recursive call, so at the end the Space will give us its Doors.
    @Override // Used also in the Space Class
    public ArrayList<Door> getDoorsGivingAccess() {
        ArrayList<Door> recollectedDoors = new ArrayList<>();
        for (Area actualArea : this.areaList) {
            actualArea.getDoorsGivingAccess();
            recollectedDoors.addAll(actualArea.getDoorsGivingAccess());
        }
        return recollectedDoors;
    }

    // Searches an Area (Partition), and if it's in the given Area,
    // we return the instance. Otherwise, we return null.
    @Override
    public Area findPartitionById(String id, Area rootArea) {
        // We search the given Partition on the root.
        for (Area looking : rootArea.getAreaList()) {
            if (looking.getPartitionName().equals(id)) {
                return looking;
            }
        }
        return null;
    }



}

