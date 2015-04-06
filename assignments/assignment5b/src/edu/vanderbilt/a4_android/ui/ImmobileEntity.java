package edu.vanderbilt.a4_android.ui;

import edu.vanderbilt.a4_android.ui.Entity;
import mikera.vectorz.Vector2;


public class ImmobileEntity implements Entity{

  /** The name of this entity. */
  // TODO: Fill in here, if necessary

  /** The mass of this entity. */
  // TODO: Fill in here, if necessary

  /** The position of this entity. */
  // TODO: Fill in here, if necessary

  public ImmobileEntity(String name, double mass, Vector2 position) {
    // TODO: Fill in here, if necessary
  }

  /** Return the name of this entity. */
  public String getName() {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Return the mass of this entity. */
  public double getMass() {
    // TODO: Fill in here, if necessary
    return null;
  }

  public Vector2 getVelocity () {
    // TODO: Fill in here, if necessary
    return null;
  }

  public Vector2 getPosition () {
    // TODO: Fill in here, if necessary
  }

  public class Memento implements Entity.Memento {
    public Memento setPosition(Vector2 pos) {
      // TODO: Fill in here, if necessary
      return this;
    }

    public Entity.Memento setVelocity(Vector2 vel) {
      // TODO: Fill in here, if necessary
      return this;
    }

    public void apply() {
      // TODO: Fill in here, if necessary
    }
  }

  public Entity.Memento update() {
    // TODO: Fill in here, if necessary
    return null;
  }

  public void accept(EntityVisitor visitor) {
    // TODO: Fill in here, if necessary
  }
}