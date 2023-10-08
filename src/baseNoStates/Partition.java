package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area {

    private ArrayList<Partition> partitionList;
    private ArrayList<Space> spacesList;
    private String partition_name;
    private String father_name;

    public Partition(String part_name, ArrayList<Space> spaces, ArrayList<Partition> partitions, String fathers_name){
        partition_name = part_name;
        spacesList = spaces;
        partitionList = partitions;
        father_name = fathers_name;
    }

    @Override
    public Space findAreaById(String id) {
        return null;
    }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        throw new UnsupportedOperationException("This method shouldn't be used.");
    }

    @Override
    public ArrayList<Partition> getPartitionlist() {
        return partitionList;
    }
    @Override
    public ArrayList<Space> getSpacesList() {
        return spacesList;
    }
    @Override
    public void setSpacesList(ArrayList<Space> spaceList) {
        this.spacesList = spaceList;
    }
    @Override
    public void setPartitionlist(ArrayList<Partition> partitionlist) {
        this.partitionList = partitionlist;
    }
    @Override
    public String getPartition_name(){return partition_name;}

    @Override
    public Partition findPartitionById(String id, Area rootArea) {
        // We search the given Partition on the root.
        for(Partition looking : rootArea.getPartitionlist()){
            if (looking.getPartition_name().equals(id)){
                return looking;
            }
        }
        return null;
    }


}

