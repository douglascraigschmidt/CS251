/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "Object.h"
#include "Universe.h"
#include "Visitor.h"

/**
 *  Initializes an object with the provided properties.
 */
Object::Object(const std::string& name, double mass, const vector2& pos, const vector2& vel)
  : name(name),
    mass(mass),
    position(pos),
    velocity(vel)
{
}

/**
 *  An entry point for a visitor.
 */
void Object::accept(Visitor& visitor)
{ 
  // TODO -- you fill in here.
}

/**
 *  Implementation of the prototype. Returns a dynamically allocated
 *  deep copy of this object.
 */
Object* Object::clone() const
{
  // TODO -- you fill in here.
}

/**
 *  Returns the mass.
 */
double Object::getMass() const noexcept
{
  return mass;
}

/**
 *  Returns the name.
 */
std::string Object::getName() const noexcept
{
  return name;
}

/**
 *  Returns the position vector.
 */
vector2 Object::getPosition() const noexcept
{
    return position;
}

/**
 *  Returns the velocity vector.
 */
vector2 Object::getVelocity() const noexcept
{
    return velocity;
}

/**
 *  Calculates the force vector between lhs and rhs. The direction of the
 *  result is as experienced by lhs. Negate the result to obtain force
 *  experienced by rhs.
 */
vector2 Object::getForce(const Object& rhs) const noexcept
{
  double distSq = (rhs.getPosition() - getPosition()).normSq();
  double mag = (Universe::G * getMass() * rhs.getMass()) / distSq;
  vector2 dir = (rhs.getPosition() - getPosition()).normalize();
  return mag * dir;
}

/**
 *  Sets the position vector.
 */
void Object::setPosition(const vector2& pos)
{
  position = pos;
}

/**
 *  Sets the velocity vector.
 */
void Object::setVelocity(const vector2& vel)
{
  velocity = vel;
}

/**
 *  Returns true if this object is member-wise equal to rhs.
 */
bool Object::operator==(const Object& rhs) const
{
  // TODO -- you fill in here.
}

/**
 *  Returns !(*this == rhs).
 */
bool Object::operator!=(const Object& rhs) const
{
  // TODO -- you fill in here.
}
