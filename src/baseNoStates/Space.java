package baseNoStates;

import java.util.List;

public class Space extends Area{
    private List<Door> doorList;
    private String space_name;
    private String father_name;

    public Space(String spa_name, List<Door> doors, String fathers_name){
        space_name = spa_name;
        doorList = doors;
        father_name = fathers_name;
    }


    @Override
    public Area findAreaById(String id) {
        return null;
    }

    @Override
    public Door[] getDoorsGivingAccess(){
        return null;
    }
}
