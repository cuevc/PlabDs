package baseNoStates;

public abstract class Area {


    public abstract Area findAreaById(String id);


    public abstract ArrayList<Door> getDoorsGivingAccess();


    public abstract ArrayList<Partition> getPartitionlist();

    public abstract ArrayList<Space> getSpacesList();

    public abstract void setSpacesList(ArrayList<Space> spaceList);

    public abstract void setPartitionlist(ArrayList<Partition> partitionlist);

    public abstract String getPartition_name();


}
