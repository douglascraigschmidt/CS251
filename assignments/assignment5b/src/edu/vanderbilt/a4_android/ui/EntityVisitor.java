package edu.vanderbilt.a4_android.ui;

import edu.vanderbilt.a4_android.ui.SimpleEntity;

/** This class implements the Visitor design pattern for Entity objects. */
public interface EntityVisitor {
  /** Visit the provided Entity. */
  public void visit(SimpleEntity entity);
 
  public void visit(ImmobileEntity entity);

  public void visit(AggregateEntity entity);

}
