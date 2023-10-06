package baseNoStates;

import java.util.List;

public class Space extends Area{
    private List<Door> doorList;

    @Override
    public Area findAreaById(String id) {
        return null;
    }

    @Override
    public Door[] getDoorsGivingAccess(){
        return null;
    }
}
