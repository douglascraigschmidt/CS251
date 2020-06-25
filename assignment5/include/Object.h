/* @author G. Hemingway, copyright 2020 - All rights reserved */
#ifndef OBJECT_H
#define OBJECT_H

#include <string>

#include "vector2.h"

// Forward declaration.
class Visitor;
class ObjectFactory;

/**
 *  Representation of objects suitable for use in the simulation. For
 *  this assignment, this will be the only allowable type.
 */
class Object {
public:
  /**
   *  Destroys this object.
   */
  virtual ~Object() = default;

  /**
   *  An entry point for a visitor.
   */
  virtual void accept(Visitor& visitor);

  /**
   *  Implementation of the prototype. Returns a dynamically allocated
   *  deep copy of this object.
   */
  virtual Object* clone() const;

  /**
   *  Returns the mass.
   */
  virtual double getMass() const noexcept;

  /**
   *  Returns the name.
   */
  virtual std::string getName() const noexcept;

  /**
   *  Returns the position vector.
   */
  virtual vector2 getPosition() const noexcept;

  /**
   *  Returns the velocity vector.
   */
  virtual vector2 getVelocity() const noexcept;

  /**
   *  Calculates the force vector between lhs and rhs. The direction
   *  of the result is as experienced by lhs. Negate the result to
   *  obtain force experienced by rhs.
   */
  virtual vector2 getForce(const Object& rhs) const noexcept;

  /**
   *  Sets the position vector.
   */
  virtual void setPosition(const vector2& pos);

  /**
   *  Sets the velocity vector.
   */
  virtual void setVelocity(const vector2& vel);

  /**
   *  Returns true if this object is member-wise equal to rhs.
   */
  bool operator==(const Object& rhs) const;

  /**
   *  Returns !(*this == rhs).
   */
  bool operator!=(const Object& rhs) const;

private:
  friend class ObjectFactory;

  /**
   * Initializes an object with the provided properties. Should only
   * be called by the ObjectFactory.
   */
  Object(const std::string& name, double mass, const vector2& pos, const vector2& vel);

  /**
   *  Name of the object.
   */
  std::string name;

  /**
   *  Mass of the object in kilograms.
   */
  double mass;

  /**
   *  Position vector of the object in meters.
   */
  vector2 position;

  /**
   *  Velocity vector of the object in meters/second.
   */
  vector2 velocity;
};

#endif // OBJECT_H
