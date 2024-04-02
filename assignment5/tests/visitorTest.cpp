/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include "Visitor.h"
#include "./testHelper.h"
#include "Object.h"
#include "ObjectFactory.h"
#include "Parser.h"
#include "Universe.h"
#include <gtest/gtest.h>
#include <memory>

// The fixture for testing the Visitor class.
class VisitorTest : public ::testing::Test {
};

TEST_F(VisitorTest, SimpleVisitor)
{
    std::stringstream stream;
    std::unique_ptr<Universe> univ(Universe::instance());
#ifdef GRADUATE
    Parser parser;
    parser.loadFile("../tests/visitorTest.txt");
#else
    ObjectFactory::makeObject("H");
    ObjectFactory::makeObject("e");
    ObjectFactory::makeObject("l");
    ObjectFactory::makeObject("l");
    ObjectFactory::makeObject("o");
#endif

    PrintVisitor printer(stream);
    for (Universe::iterator i = univ->begin(); i != univ->end(); ++i)
        (*i)->accept(printer);

    stream.flush();
    EXPECT_EQ(stream.str(), "Hello");
}
