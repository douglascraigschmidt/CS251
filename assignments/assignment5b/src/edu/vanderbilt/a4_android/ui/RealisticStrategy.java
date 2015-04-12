package edu.vanderbilt.a4_android.ui;

import java.util.ArrayList;

public class RealisticStrategy implements AggregateEntity.StepStrategy {
  /**
   * Update the position and velocity of the aggregate entity in a realistic manner,
   * update the position of each member entity with respect to the rest of the universe.
   */

  @Override
  public ArrayList<Entity.Memento> stepSimulation(AggregateEntity ent, double seconds) {
    // TODO: Fill in here, if necessary
	return null;
  }
}
