package vandy.cs251;

import vandy.cs251.Entity;

/** This class impelments the Visitor design pattern for Entity objects. */
public interface EntityVisitor {
  /** Visit the provided Entity. */
  public void visit(Entity entity);
  public void visit(OtherEntity entity);
  public void visit(MyCoolOtherEntity entity);
}
