package baseNoStates;

import java.util.ArrayList;



/**
 * Area is the abstract class that forms part of the Composite of Partition,
 * Spaces and Areas, where a Partition can have a Partition or a
 * Space and a Space have Doors.
 */
public abstract class Area {

    // =====================================================
    // ||              Setters and Getters                ||
    // =====================================================

    // Sets the father of a Partition or Space instance.
    public abstract void setFather(Area father);

    // Returns the list of all the Doors in a certain
    // Area (Partition or Space)
    public abstract ArrayList<Door> getDoorsGivingAccess();

    // Get the Area list of a certain Area. Could be the
    // Partition list of a Partition or the Spaces list of a Partition.
    public abstract ArrayList<Area> getAreaList();

    // Get the name of the current Area (Partition or Space).
    public abstract String getPartitionName();

    // =====================================================
    // ||           Other methods of this class           ||
    // =====================================================
    // Searches an Area (Partition), and if it's in the given Area,
    // we return the instance. Otherwise, we return null.
    protected abstract Area findPartitionById(String id, Area rootArea);

}
