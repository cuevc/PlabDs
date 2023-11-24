package base.no.states;

import java.util.ArrayList;

public abstract class Visitor {
    public abstract void visitPartition(Partition partition);
    public abstract void visitSpace(Space space);
}
