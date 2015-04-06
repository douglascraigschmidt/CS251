package edu.vanderbilt.a4_android.ui;

import java.util.ArrayList;
import java.util.Iterator;

import edu.vanderbilt.a4_android.ui.EntityVisitor;
import mikera.vectorz.Vector2;

public class StepSimulationVisitor implements EntityVisitor {
  /** Stores mementos generated during visitation of entities. */
  // TODO: Fill in here, if necessary

  /** The time delta which this visitor should use as the basis
   * for the simulation step.
   */
  // TODO: Fill in here, if necessary

  StepSimulationVisitor(double delta) {
    // TODO: Fill in here, if necessary
  }

  /** Update the position and velocity of the provided
   *  entity according to the internal simulation time delta.
   */
  @Override
  public void visit (SimpleEntity ent) {
    // TODO: Fill in here, if necessary
  }

  @Override
  public void visit(ImmobileEntity entity) {
    // TODO: Fill in here, if necessary.
    }

  @Override
  public void visit(AggregateEntity entity) {
    // TODO: Fill in here, if necessary.
  }

  @Override
  public void apply () {
    // TODO: Fill in here, if necessary.
  }
}
