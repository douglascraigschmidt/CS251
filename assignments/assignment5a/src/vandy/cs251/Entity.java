package vandy.cs251;

// Please see https://github.com/mikera/vectorz/blob/develop/src/main/java/mikera/vectorz/Vector2.java
import mikera.vectorz.Vector2;
import vandy.cs251.EntityVisitor;

public class Entity {

  /** The name of this entity. */
  // TODO: Fill in here, if necessary

  /** The mass of this entity. */
  // TODO: Fill in here, if necessary

  /** The position of this entity. */
  // TODO: Fill in here, if necessary

  /** The velocity of this entity. */
  // TODO: Fill in here, if necessary


  public Entity(String name, double mass, Vector2 pos, Vector2 vel) {
    // TODO: Fill in here, if necessary
  }

  /** Return the current velocity of this entity. */
  Vector2 getVelocity () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Return the current position of this entity. */
  Vector2 getPosition () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Return the name of this entity. */
  String getName () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Return the mass of this entity. */
  double getMass () {
    // TODO: Fill in here, if necessary
    return 0;
  }

  /** This class implements an inverse 'Memento' pattern,
   *  which provides a facility to update the state of the entity
   *  without having to make a copy to ensure consistency while
   *  the simulation is being updated.
   */
  class Memento {

    /** The new velocity of the entity. */
    // TODO: Fill in here, if necessary

    /** The new position of the entity */
    // TODO: Fill in here, if necessary

    /** Construct a new memento object. */
    public Memento () {
      // TODO: Fill in here, if necessary
    }

    /** Provide an updated velocity. Note that we
     *  return a reference to allow method chaining.
     */
    public Memento setVelocity (Vector2 vel) {
      // TODO: Fill in here, if necessary
      return null;
    }

    /** Provide an updated position. Note that we
     *  return a reference to allow method chaining.
     */
    public Memento setPosition (Vector2 pos) {
      // TODO: Fill in here, if necessary
      return null;
    }

    /** Apply the updated position and velocity to
     *  the associated Entity.
     */
    public void apply() {
      // TODO: Fill in here, if necessary
    }
  }

  /** Construct a new memento object. */
  public Memento update () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Accept the provided visitor */
  public void accept (EntityVisitor visitor) {
    // TODO: Fill in here, if necessary
  }

  /** Create a string representation of this entity. */
  public String toString () {
    return String.format ("[%s] [%e] [%e,%e] [%e,%e]",
                         getName(), getMass(), getVelocity().x,
                         getVelocity().y, getPosition().x, getPosition().y);
  }
}
