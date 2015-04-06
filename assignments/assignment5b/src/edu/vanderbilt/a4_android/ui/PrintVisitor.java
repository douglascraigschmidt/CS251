package edu.vanderbilt.a4_android.ui;

import edu.vanderbilt.a4_android.ui.EntityVisitor;

public class PrintVisitor implements EntityVisitor {
  /** Print the provided entity to standard output. */
  @Override
  public void visit (SimpleEntity ent) {
  }

  @Override
  public void visit(ImmobileEntity entity) {
    // TODO: Fill in here, if necessary
  }

  @Override
  public void visit(AggregateEntity entity) {
    // TODO: Fill in here, if necessary
  }
}
