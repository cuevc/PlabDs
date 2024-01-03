package base.no.states;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetAreaListVisitor extends Visitor {

  ArrayList<Area> areas;
  static Logger logger =
      LoggerFactory.getLogger("base.no.states.Visitor.GetAreaListVisitor");

  public GetAreaListVisitor() {
    logger.debug("GetAreaListVisitor() => A new GetAreaListVisitor has been created");
    this.areas = new ArrayList<>();
  }

  @Override
  public void visitPartition(Partition partition) {
    areas = partition.getAreaList();
  }

  @Override
  public void visitSpace(Space space) {
    areas = null;
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

}
