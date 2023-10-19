package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area {

    //deberia de ser una lista de areas una sobra
    //private ArrayList<Partition> partitionList;
    private ArrayList<Area> areaList;
    private String partition_name;
    private Area father;

    public Partition(String part_name, ArrayList<Area> areas, Area fatherNode){
        partition_name = part_name;
        areaList = areas;
        //partitionList = partitions;
        father = fatherNode;
    }

    void addArea(Area addingArea){
        this.areaList.add(addingArea);
    }

    @Override
    public void setFather(Area father) {
        this.father = father;
    }

    @Override // Used on the Space Class
    public Space findAreaById(String id) {
        return null;
    }

    @Override // Used on the Space Class
    public ArrayList<Door> getDoorsGivingAccess() {
        ArrayList<Door> recollectedDoors = new ArrayList<>();
        for(Area actualArea : this.areaList){
            actualArea.getDoorsGivingAccess();
            recollectedDoors.addAll(actualArea.getDoorsGivingAccess());

        }
        return recollectedDoors;
    }



    @Override
    public ArrayList<Partition> getPartitionlist() {
        return null;
    }

    @Override
    public ArrayList<Area> getAreaList() {
        return areaList;
    }
    @Override
    public void setAreaList(ArrayList<Area> areaLists) {
        this.areaList = areaLists;
    }

    @Override
    public void setPartitionlist(ArrayList<Partition> partitionlist) {

    }

    @Override
    public String getPartition_name(){return partition_name;}

    @Override
    public Area findPartitionById(String id, Area rootArea) {
        // We search the given Partition on the root.
        for(Area looking : rootArea.getAreaList()){
            if (looking.getPartition_name().equals(id)){
                return looking;
            }
        }
        return null;
    }

    @Override
    public void setPartFather(Partition father) {

    }

    @Override
    public void setPartFather(Area father) {

    }

}

