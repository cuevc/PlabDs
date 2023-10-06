package baseNoStates;

import java.util.List;

public class Partition extends Area {

    private List<Partition> partitionList;
    private List<Area> areaList;
    private String partition_name;
    private String father_name;

    public Partition(String part_name, List<Area> areas, List<Partition> partitions, String fathers_name){
        partition_name = part_name;
        areaList = areas;
        partitionList = partitions;
        father_name = fathers_name;
    }

    @Override
    public Area findAreaById(String id){
        return null;
    }

    @Override
    public Door[] getDoorsGivingAccess() {
        return new Door[0];
    }

    public List<Partition> getPartitionlist() {
        return partitionList;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public void setPartitionlist(List<Partition> partitionlist) {
        this.partitionList = partitionlist;
    }
}

