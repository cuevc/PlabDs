package base.no.states;

import java.util.ArrayList;

/**
 * Visitor pattern design abstract (or interface) class part.
 */
public abstract class Visitor {

  public abstract void visitPartition(Partition partition);

  public abstract void visitSpace(Space space);
}
