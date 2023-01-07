/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "./testHelper.h"
#include "Object.h"
#include "ObjectFactory.h"
#include "Parser.h"
#include "Universe.h"
#include "Visitor.h"
#include <fstream>
#include <gtest/gtest.h>
#include <memory>

/**
 *  Returns the next vector in the file.
 */
vector2 getNextVector(std::ifstream& is)
{
    vector2 v;
    is >> v[0] >> v[1];
    return v;
}

// The fixture for testing Uniform Circular Motion math.
class UMCTest : public ::testing::Test {
};

TEST_F(UMCTest, YearlongTest)
{
    std::ifstream file("../tests/testData.txt", std::ifstream::in);
    if (file.fail()) {
        std::cout << "Error: " << strerror(errno) << std::endl;
        std::cout << "UMCTest not able to open file: ../tests/testData.txt." << std::endl;
        exit(-1);
    } else {
        std::cout << "UMCTest opened file." << std::endl;
    }

    FileCloser closer(file); // closer will automatically close our file
    const double step = 1;
    const int io = 100;
    std::unique_ptr<Universe> u(Universe::instance());

    ObjectFactory::makeObject("sun", 1.98892e30);
    vector2 position = makeVector2(149597870700.0, 0);
    vector2 velocity = makeVector2(0, 29788.4676);
    ObjectFactory::makeObject("earth", 5.9742e24, position, velocity);

    const double year_s = 31554195.932106005998594489072144;

    int ioCount = 0;
    for (double time = 0; time < year_s; time += step) {
      if (ioCount == io) {
        Object& object = **(++(u->begin()));
        vector2 pos = object.getPosition();
        vector2 check = getNextVector(file);
        assertVector(pos, check, 1000000.0);
        ioCount = 0;
      }
      ioCount++;
      u->stepSimulation(step);
    }
}
