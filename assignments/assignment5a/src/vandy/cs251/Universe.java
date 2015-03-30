package vandy.cs251;

import java.util.ArrayList;
// Please see https://github.com/mikera/vectorz/blob/develop/src/main/java/mikera/vectorz/Vector2.java
import mikera.vectorz.Vector2;
import java.util.Iterator;
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
   * Release the universe, so instance () will return a new Universe.
   */
  public static void release () {
    // TODO: Fill in here, if necessary
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
  public Entity getEntity(String name) {
    // TODO: Fill in here, if necessary
  }

  class UniverseSpliterator implements Spliterator<Entity> {
    // TODO: You will need to add constructor(s) and data member(s)
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

  public Iterator<Entity> iterator () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /**
   * Advance the simulation by the provided number of seconds.
   */
  public void stepSimulation (double delta) {
    // TODO: Fill in here, if necessary
  }

  private static final double mGravitationalConstant = 6.67428e-11;

  /**
   * Calculates the force exerted on the 'base' by 'other'.
   */
  public static Vector2 getForce (Entity base, Entity other) {
    // To be provided, do not edit.
    if (other.getPosition ().equals (base.getPosition ())) return new Vector2 ();
    Vector2 direction = other.getPosition ().clone ();
    direction.sub (base.getPosition ());
    Vector2 result = direction.toNormal ();
    result.scaleAdd ((other.getMass () * base.getMass ()) / direction.magnitudeSquared (), 0);
    result.scaleAdd (mGravitationalConstant, 0);
    return result;
  }

  /**
   * Calculates the change in velocity of a given Entity given
   * a net force acting on the object.
   */
  public static Vector2 calcVelocityDelta (Entity ent, Vector2 force) {
    // To be provided, do not edit.
    Vector2 result = force.clone ();
    result.scaleAdd (1/ent.getMass (), 0);
    return result;
  }

  public static Vector2 calcPositionDelta (Vector2 position, Vector2 velocity, double time) {
    // To be provided, do not edit.
    Vector2 result = position.clone ();
    result.addMultiple (velocity, time);
    return result;
  }
}
