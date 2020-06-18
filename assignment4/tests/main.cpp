// @author G. Hemingway, copyright 2020 - All rights reserved

/**
 *  Test driver. Through its associated tests, this driver aims to exercise all
 * of the
 *  functionality of the ArrayList class in a clear and consistent way so that
 *  the class's interface and implementation can be easily inferred.
 */

#include <gtest/gtest.h>

int main(int argc, char** argv)
{
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
