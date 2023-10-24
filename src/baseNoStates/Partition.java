package baseNoStates;

import java.util.ArrayList;

// Partition is a part of the Composite between Area (abstract), Partition and Space. A Partition instance can have a list of Partitions or Spaces.
public class Partition extends Area {

    private ArrayList<Area> areaList; // List of Partitions or Spaces in this instance of Partition.
    private String partition_name;  // Name of the Partition (used to find a Partition by Id).
    private Area father; // Father of this Partition, very useful to build the building Areas tree.

    public Partition(String part_name, ArrayList<Area> areas, Area fatherNode){
        partition_name = part_name;
        areaList = areas;
        //partitionList = partitions;
        father = fatherNode;
    }


    // =====================================================
    // ||              Setters and Getters                ||
    // =====================================================


    @Override
    public void setFather(Area father) {
        this.father = father;
    } // Sets the father of the current Partition, useful to make the building Areas tree.

    @Override
    public ArrayList<Area> getAreaList() {
        return areaList;
    } // Get the current Areas List

    @Override
    public String getPartitionName(){return partition_name;} // Get the name of this instance of Partition.

    // =====================================================
    // ||           Other methods of this class           ||
    // =====================================================


    void addArea(Area addingArea){
        this.areaList.add(addingArea);
    } // Adds an Area to the current AreasList.

    @Override // Used also in the Space Class
    public ArrayList<Door> getDoorsGivingAccess() { // Special Getter that gives all the Doors of the current Area. This method has a recursive call, so at the end the Space will give us its Doors.
        ArrayList<Door> recollectedDoors = new ArrayList<>();
        for(Area actualArea : this.areaList){
            actualArea.getDoorsGivingAccess();
            recollectedDoors.addAll(actualArea.getDoorsGivingAccess());

        }
        return recollectedDoors;
    }

    @Override
    public Area findPartitionById(String id, Area rootArea) {  // Searches an Area (Partition), and if it's in the given Area, we return the instance. Otherwise, we return null.
        // We search the given Partition on the root.
        for(Area looking : rootArea.getAreaList()){
            if (looking.getPartitionName().equals(id)){
                return looking;
            }
        }
        return null;
    }



}

