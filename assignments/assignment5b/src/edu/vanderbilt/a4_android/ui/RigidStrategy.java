package edu.vanderbilt.a4_android.ui;

import java.util.ArrayList;

public class RigidStrategy implements AggregateEntity.StepStrategy {
  /**
   * Update the position and velocity of the aggregate object in a rigid manner,
   * treating the aggregate as a single object.
   */
  @Override
  public ArrayList<AggregateEntity.Memento> stepSimulation(AggregateEntity ent, double seconds) {
    // TODO: Fill in here, if necessary
	  return null;
  }
}
