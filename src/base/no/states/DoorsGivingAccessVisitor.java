package base.no.states;

import java.util.ArrayList;

import static base.no.states.Partition.logger;

//recursive call, so at the end the Space will give us its Doors.
public class DoorsGivingAccessVisitor extends Visitor{
    ArrayList<Door> recollectedDoors;

    public DoorsGivingAccessVisitor() {
        this.recollectedDoors = new ArrayList<>();
    }

    @Override
    public void visitPartition(Partition partition) {
        for (Area actualArea : partition.getAreaList()) {
            actualArea.accept(this);
        }
        logger.debug("getDoorGivingAccess() => Getting all door which we have access");
    }

    @Override
    public void visitSpace(Space space) {
        recollectedDoors.addAll(space.getDoors());
    }

    public ArrayList<Door> getRecollectedDoors() {
        return recollectedDoors;
    }
}
