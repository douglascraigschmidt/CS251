/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include <gtest/gtest.h>

int main(int argc, char** argv)
{
    // Run the registered tests
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
