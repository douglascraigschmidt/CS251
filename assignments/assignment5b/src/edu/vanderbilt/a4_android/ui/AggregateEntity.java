package edu.vanderbilt.a4_android.ui;

import java.util.Iterator;
import java.util.ArrayList;
import mikera.vectorz.Vector2;
import edu.vanderbilt.a4_android.ui.Entity;

public class AggregateEntity implements Entity {
  /**
   * Interface for strategeies that influence how this entity is processed for simulation steps.
   */
  public interface StepStrategy {
    public ArrayList<Entity.Memento> stepSimulation(AggregateEntity ent, double seconds);
  }

  /** The update strategy used by this entity. */
  // TODO: Filli n here, if necessary

  /** A container for children of the aggregate. */
  // TODO: Filli n here, if necessary

  /** The name of the aggregate. */
  // TODO: Filli n here, if necessary

  /** Cached value of the aggregate's current position,
   * which is defined as the center of mass for this object.
   */
  // TODO: Filli n here, if necessary

  /**
   * Cached value of the aggregate's current velocity,
   * which is defined as the average velocity of it's members.
   */
  // TODO: Filli n here, if necessary

  /**
   * Mass of the aggregate, defined as the sum of the mass
   * of all constituent elements.
   */
  // TODO: Filli n here, if necessary

  public AggregateEntity (String name, StepStrategy s) {
    // TODO: Fill in here, if necessary
  }

  /**
   * Provides an iterator to the children of this entity. 
   * @return
   */
  public Iterator<Entity> iterator () {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Add a new member to this aggregate. */
  public void add (Entity child) {
    // TODO: Fill in here, if necessary
  }

  /** Return the name of this entity. */
  @Override
  public String getName() {
    // TODO: Fill in here, if necessary
    return null;
  }

  /** Return the mass of this entity. */
  @Override
  public double getMass() {
    // TODO: Fill in here, if necessary
    return 0;
  }

  /** Return the velocity of this entity. */
  @Override
  public Vector2 getVelocity () {
    // TODO: Fill in here, if necessary
    // The average velocity is computed as the sum of:
    //    member_velocity * member_mass
    // That sum should then be divided by the total mass in the aggregate.
    return null;
  }

  /** Return the position of this entity. */
  @Override
  public Vector2 getPosition () {
    // TODO: Fill in here, if necessary
    // The position is defined as the center of mass of the aggregate.
    // This is calculated as the sum of:
    //  member_position * member_mass
    // That sum is divided by the total mass of the system.
    return null;
  }

  public void setStrategy(StepStrategy s) {
    // TODO: Fill in here, if necessary
  }

  public StepStrategy getStrategy () {
    // TODO: Fill in here, if necessary
    return null;
  }
  
  public class Memento implements Entity.Memento {

    @Override
    public Entity.Memento setPosition(Vector2 pos) {
      // TODO: Fill in here, if necessary
      return null;
    }

    @Override
    public Entity.Memento setVelocity(Vector2 vel) {
      // TODO: Fill in here, if necessary
      return null;
    }

    @Override
    public void apply() {
      // TODO: Fill in here, if necessary
    }
	  
  }

  @Override
  public Entity.Memento update() {
    // TODO: Fill in here, if necessary
    return null;
  }

  @Override
  public void accept(EntityVisitor visitor) {
    // TODO: Fill in here, if necessary
  }

  public String toString () {
    String ret = "[%s] [AGGREGATE] {\n";

    // TODO: Loop through the entities and add them to the ret string.

    return ret + "\n}";
    
  }
}