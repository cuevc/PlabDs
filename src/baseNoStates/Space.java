package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class Space extends Area{
    private ArrayList<Door> doorList;
    private String space_name;
    private Area partFather;

    public Space(String spa_name, List<Door> doors, Partition partitionFather){
        space_name = spa_name;
        doorList = (ArrayList<Door>) doors;
        partFather = partitionFather;
    }
    @Override
    public void setPartFather(Partition father){partFather = father;}

    @Override
    public void setPartFather(Area father) {
        throw new UnsupportedOperationException("This method shouldn't be used.");

    }

    @Override
    public Area findAreaById(String id) {
        return null;
    }

    @Override
    public Partition findPartitionById(String id, Area rootArea) {
        return null;
    }
    @Override
    public ArrayList<Door> getDoorsGivingAccess(){return doorList;}

    @Override
    public ArrayList<Partition> getPartitionlist() {
        return null;
    }
    @Override
    public ArrayList<Area> getAreaList() {
        return null;
    }

    @Override
    public void setFather(Area father) {
        partFather = father;
    }


    @Override
    public void setAreaList(ArrayList<Area> areasList){}

    @Override
    public void setPartitionlist(ArrayList<Partition> partitionlist) {}
    @Override
    public String getPartition_name() {
        return null;
    }

}
