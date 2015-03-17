package vandy.cs251;

import java.util.ArrayList;
// Please see https://github.com/mikera/vectorz/blob/develop/src/main/java/mikera/vectorz/Vector2.java
import mikera.vectorz.Vector2;
import vandy.cs251.EntityVisitor;
import vandy.cs251.Spliterator;


class Universe {
  /** A reference to the singleton instance of the Universe object. */
  // TODO: Fill in here, if necessary

  /** A list of entities in the simulation */
  // TODO: Fill in here, if necessary

  private Universe () {
    // TODO: Fill in here, if necessary
  }

  /**
   *  Return a reference to the singleton instance of Universe, constructing
   *  a new one, if necessary.
   */
  public static Universe instance () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /**
   * Add a new entity to the simulation.
   */
  public void addEntity(Entity ent) {
    // TODO: Fill in here, if necessary
  }

  /**
   * Remove the identified entity from the simulation.
   */
  public void removeEntity(String name) {
    // TODO: Fill in here, if necessary
  }

  /**
   * Get the identified entity.
   */
  public void getEntity(String name) {
    // TODO: Fill in here, if necessary
  }

  class UniverseSpliterator implements Spliterator<Entity> {
    public long estimateSize() {
      // TODO: Fill in here, if necessary
      return 0;
    }

    public boolean tryAdvance(EntityVisitor visitor) {
      // TODO: Fill in here, if necessary
      return false;
    }

    public Spliterator<Entity> trySplit() {
      // TODO: Fill in here, if necessary
      return null;
    }
  }

  /**
   * Construct a spliterator covering the full range of all
   * entities in the simulation.
   */
  public Spliterator spliterator () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /**
   * Advance the simulation by the provided number of seconds.
   */
  public void stepSimulation (double delta) {
    // TODO: Fill in here, if necessary
  }

  /**
   * Calculates the force exerted on the 'base' by 'other'.
   */
  public static Vector2 getForce (Entity base, Entity other) {
    // To be provided, do not edit.
    return null;
  }

  /**
   * Calculates the change in velocity of a given Entity given
   * a net force acting on the object.
   */
  public static Vector2 calcVelocityDelta (Entity ent, Vector2 force) {
    // To be provided, do not edit.
    return null;
  }

  public static Vector2 calcPositionDelta (Vector2 position, Vector2 force, double time) {
    // To be provided, do not edit.
    return null;
  }
}
