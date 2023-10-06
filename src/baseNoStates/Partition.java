package baseNoStates;

import java.util.List;

public class Partition extends Area {
    private List<Partition> partitionlist;
    private List<Area> areaList;

    @Override
    public Area findAreaById(String id){
        return null;
    }

    public List<Partition> getPartitionlist() {
        return partitionlist;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public void setPartitionlist(List<Partition> partitionlist) {
        this.partitionlist = partitionlist;
    }
}

