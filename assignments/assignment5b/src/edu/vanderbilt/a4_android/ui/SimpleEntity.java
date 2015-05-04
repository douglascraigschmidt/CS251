package edu.vanderbilt.a4_android.ui;

import edu.vanderbilt.a4_android.ui.EntityVisitor;
import mikera.vectorz.Vector2;

public class SimpleEntity extends ImmobileEntity {

  /** The velocity of this entity. */
  // TODO: Fill in here, if necessary


  public SimpleEntity(String name, double mass, Vector2 pos, Vector2 vel) {
    // TODO: Fill in here, if necessary
  }

  /** Return the current velocity of this entity. */
  public Vector2 getVelocity () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** This class implements an inverse 'Memento' pattern,
   *  which provides a facility to update the state of the entity
   *  without having to make a copy to ensure consistency while
   *  the simulation is being updated.
   */
  class Memento implements Entity.Memento{

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
    @Override
    public Memento setVelocity (Vector2 vel) {
      // TODO: Fill in here, if necessary
      return null;
    }

    /** Provide an updated position. Note that we
     *  return a reference to allow method chaining.
     */
    @Override
    public Memento setPosition (Vector2 pos) {
      // TODO: Fill in here, if necessary
      return null;
    }

    /** Apply the updated position and velocity to
     *  the associated Entity.
     */
    @Override
    public void apply() {
      // TODO: Fill in here, if necessary
    }
  }

  /* (non-Javadoc)
   * @see edu.vanderbilt.a4_android.ui.Entity#update()
   */
  @Override
  public Entity.Memento update () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /* (non-Javadoc)
   * @see edu.vanderbilt.a4_android.ui.Entity#accept(edu.vanderbilt.a4_android.ui.EntityVisitor)
   */
  @Override
  public void accept (EntityVisitor visitor) {
    // TODO: Fill in here, if necessary
  }

  public String toString () {

	  return String.format ("[%s] [SIMPLE] [%e] [%e,%e] [%e,%e]",
			  getName(), getMass(), getVelocity().x,
			  getVelocity().y, getPosition().x, getPosition().y);
  }
}
