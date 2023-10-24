package baseNoStates;

import java.util.ArrayList;

// Area is the abstract class that forms part of the composite of Partition, Spaces and Areas, where a Partition can have a Partition or an
// Space and a Space have Doors.
public abstract class Area {

    // =====================================================
    // ||              Setters and Getters                ||
    // =====================================================


    public abstract void setFather(Area father); // Sets the father of a Partition or Space instance.

    public abstract ArrayList<Door> getDoorsGivingAccess(); // Returns the list of all the Doors in a certain Area (Partition or Space)

    public abstract ArrayList<Area> getAreaList(); // Get the Area list of a certain Area. Could be the Partition list of a Partition or the Spaces list of a Partition.

    public abstract String getPartitionName(); // Get the name of the current Area (Partition or Space).

    // =====================================================
    // ||           Other methods of this class           ||
    // =====================================================

    protected abstract Area findPartitionById(String id, Area rootArea); // Searches an Area (Partition), and if it's in the given Area, we return the instance. Otherwise, we return null.

}
