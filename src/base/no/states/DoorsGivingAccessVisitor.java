package base.no.states;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class applies the Visitor design pattern.
 * Recursive call, so at the end the Space will give us its Doors.
 */
public class DoorsGivingAccessVisitor extends Visitor {
  ArrayList<Door> recollectedDoors;
  static Logger logger =
        LoggerFactory.getLogger("base.no.states.Visitor.DoorsGivingAccessVisitor");

    public DoorsGivingAccessVisitor() {
        this.recollectedDoors = new ArrayList<>();
    }

  @Override
  public void visitPartition(Partition partition) {
    for (Area actualArea : partition.getAreaList()) {
      actualArea.accept(this);
    }
    logger.debug("visitPartition() => Accepting in partition nodes the visitor");
  }

  @Override
  public void visitSpace(Space space) {
    recollectedDoors.addAll(space.getDoors());
    logger.debug("visitSpace() => Recollecting all Doors");
    logger.info("Visitor Giving access has recolected all doors from a partition");

  }

  public ArrayList<Door> getRecollectedDoors() {
    return recollectedDoors;
  }
}
