package baseNoStates;

import java.util.ArrayList;

public abstract class Area {

    protected abstract Area findPartitionById(String id, Area rootArea);

    public abstract void setPartFather(Partition father);

    public abstract void setPartFather(Area father);

    public abstract Area findAreaById(String id);


    public abstract ArrayList<Door> getDoorsGivingAccess();

    public abstract ArrayList<Partition> getPartitionlist();

    public abstract ArrayList<Area> getAreaList();

    //THIS ONE IS USED BY PARTITION
    public abstract void setFather(Area father);

    public abstract void setAreaList(ArrayList<Area> spaceList);

    public abstract void setPartitionlist(ArrayList<Partition> partitionlist);

    public abstract String getPartition_name();



}
