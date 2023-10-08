package baseNoStates;

import java.util.ArrayList;

public abstract class Area {


    protected abstract Area findPartitionById(String id, Area rootArea);

    public abstract Area findAreaById(String id);


    public abstract ArrayList<Door> getDoorsGivingAccess();


    public abstract ArrayList<Partition> getPartitionlist();

    public abstract ArrayList<Space> getSpacesList();

    public abstract void setSpacesList(ArrayList<Space> spaceList);

    public abstract void setPartitionlist(ArrayList<Partition> partitionlist);

    public abstract String getPartition_name();


}
