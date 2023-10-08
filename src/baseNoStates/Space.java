package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class Space extends Area{
    private List<Door> doorList;
    private String space_name;
    private String father_name;

    public Space(String spa_name, List<Door> doors, String fathers_name){
        space_name = spa_name;
        doorList = (ArrayList<Door>) doors;
        father_name = fathers_name;
    }


    @Override
    public Area findAreaById(String id) {
        return null;
    }
    @Override
    public ArrayList<Door> getDoorsGivingAccess(){return doorList;}

    @Override
    public ArrayList<Partition> getPartitionlist() {
        return null;
    }
    @Override
    public ArrayList<Space> getSpacesList() {
        return null;
    }

    @Override
    public void setSpacesList(ArrayList<Space> spaceList) {

    }

    @Override
    public void setPartitionlist(ArrayList<Partition> partitionlist) {

    }

    @Override
    public String getPartition_name() {
        return null;
    }

}
