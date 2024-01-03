package base.no.states;

import static base.no.states.Partition.logger;


public class FindPartitionByIdVistor extends Visitor {

  private String id;
  private Area looking;
  static Logger logger =
      LoggerFactory.getLogger("base.no.states.Visitor.FindPartitionByIdVistor");

  public FindPartitionByIdVistor(String id) {
    logger.debug("FindPartitionByIdVistor() =>  A new FindPartitionByIdVistor has been created");
    this.id = id;
  }

  @Override
  public void visitPartition(Partition partition) {
    for (Area looking : partition.getAreaList()) {
      if (looking.getPartitionName().equals(this.id)) {
        logger.debug("findPartitionById() => The partition has been found.");
        this.looking = looking;
        break;
      }
    }
    if (this.looking == null) {
      logger.warn("None partition has been found");
    }
  }

  @Override
  public void visitSpace(Space space) {
    this.looking = null;
  }

  public Area getLooking() {
    return looking;
  }

  public void setId(String id) {
    this.id = id;
  }
}
