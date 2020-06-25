/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "Universe.h"
#include "Object.h"

Universe* Universe::inst = nullptr;

/**
 *  Returns the only (singleton) instance of the Universe.
 */
Universe* Universe::instance()
{
  // TODO -- you fill in here to implement the singleton pattern.
}

/**
 *  Releases all the dynamic objects still registered with the Universe.
 */
Universe::~Universe()
{
  // TODO -- you fill in here.
}

/**
 *  Registers an Object with the Universe and returns the ptr to this
 *  Object. The Universe will clean up this object when it deems
 *  necessary.
 */
Object* Universe::addObject(Object* ptr)
{
  // TODO -- you fill in here.
}

/**
 *  Returns the begin iterator to the actual Objects. The order of
 *  itetarion will be the same as that over getSnapshot()'s result as
 *  long as no new objects are added to either of the containers.
 */
Universe::iterator Universe::begin()
{
  // TODO -- you fill in here.
}

/**
 *  Returns the begin iterator to the actual Objects. The order of itetarion
 *  will be the same as that over getSnapshot()'s result as long as no new
 *  objects are added to either of the containers.
 */
Universe::const_iterator Universe::begin() const
{
  // TODO -- you fill in here.
}

/**
 *  Returns the end iterator to the actual Objects. The order of itetarion
 *  will be the same as that over getSnapshot()'s result as long as no new
 *  objects are added to either of the containers.
 */
Universe::iterator Universe::end()
{
  // TODO -- you fill in here.
}

/**
 *  Returns the end iterator to the actual Objects. The order of itetarion
 *  will be the same as that over getSnapshot()'s result as long as no new
 *  objects are added to either of the containers.
 */
Universe::const_iterator Universe::end() const
{
  // TODO -- you fill in here.
}

/**
 *  Returns a container of copies of all the Objects registered with the
 *  Universe. This snapshot should be used as the source of data for
 *  computing the next step in the simulation.
 */
ArrayList<Object*> Universe::getSnapshot() const
{
  // TODO -- you fill in here by iterating through the array of
  // objects and cloning each object into the corresponding element in
  // copy.
}

/**
 *  Advances the simulation by the provided time step. For this
 *  assignment, you must assume that the first registered object is a
 *  "sun" and its position should not be affected by any of the other
 *  objects.
 */
void Universe::stepSimulation(const double& timeSec)
{
  ArrayList<Object*> copy (getSnapshot());

  for (size_t obj1 = 1; obj1 < objects.size(); ++obj1) {
    // Calculate a new force vector for each entity
    vector2 force;

    for (size_t obj2 = 0; obj2 < objects.size(); ++obj2) {
      if (obj1 != obj2)
        force += objects[obj1]->getForce(*objects[obj2]);
    }

    vector2 accel = force / objects[obj1]->getMass();
    vector2 oldPos = objects[obj1]->getPosition();
    vector2 oldVel = objects[obj1]->getVelocity();
    vector2 newPos = oldPos + oldVel * timeSec;
    vector2 newVel = oldVel + accel * timeSec;

    copy[obj1]->setVelocity(newVel);
    copy[obj1]->setPosition(newPos);
  }

  swap(copy);
}

/**
 *  Swap the contents of the provided container with the Universe's
 *  Object store and release the old Objects snapshot.
 */
void Universe::swap(ArrayList<Object*>& snapshot)
{
  // TODO -- you fill in here.
}

/**
 *  Call delete on each pointer and remove it from the container.
 */
void Universe::release(ArrayList<Object*>& objects)
{
  // TODO -- you fill in here.
}
