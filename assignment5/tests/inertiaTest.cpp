/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "./testHelper.h"
#include "Object.h"
#include "ObjectFactory.h"
#include "Parser.h"
#include "Universe.h"
#include "Visitor.h"
#include <gtest/gtest.h>
#include <memory>

// The fixture for testing the Inertia math.
class InertiaTest : public ::testing::Test {
};

TEST_F(InertiaTest, FreeBodyTest)
{
    std::unique_ptr<Universe> univ(Universe::instance());
    // Create a sun with zero mass, stationary at origin
    ObjectFactory::makeObject("sun", 0);
    // Make an object with mass=100, positioned at 100,100, with a velocity of
    // 100,0
    vector2 velocity = makeVector2(100, 0);
    vector2 position = makeVector2(100, 100);
    ObjectFactory::makeObject("obj", 100, position, velocity);
    // Lets run the simulation forward 50 time steps
    for (int step = 0; step < 50; ++step) {
        const Object& obj = **(++(univ->begin()));
        assertVector(obj.getPosition(), position);
        univ->stepSimulation(1);
        position += velocity;
    }
}
