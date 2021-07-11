// @author G. Hemingway, copyright 2018 - All rights reserved
//
#include "SimpleArray.h"
#include <gtest/gtest.h>

namespace {
// The fixture for testing SimpleArray class.
class SimpleArrayTest : public ::testing::Test {
};

TEST_F(SimpleArrayTest, Destructor)
{
    EXPECT_NO_THROW({ SimpleArray array(new AllocationTracker[100]); });
    EXPECT_NO_THROW({
        SimpleArray a1(new AllocationTracker[100]);
        SimpleArray a2(new AllocationTracker[100]);
        SimpleArray a3(new AllocationTracker[100]);
    });
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
}

TEST_F(SimpleArrayTest, LegalOps)
{
    AllocationTracker* directArray(new AllocationTracker[100]);
    const SimpleArray ca(directArray);
    EXPECT_EQ(ca.get(), directArray);
    EXPECT_TRUE(ca.isNonNull());
}

TEST_F(SimpleArrayTest, GetReleaseSwap)
{
    SimpleArray array1;
    EXPECT_EQ(array1.get(), nullptr);

    AllocationTracker* directArray1 = new AllocationTracker[100];
    array1.reset(directArray1);
    EXPECT_EQ(array1.get(), directArray1);

    AllocationTracker* directArray2 = new AllocationTracker[50];
    SimpleArray array2(directArray2);

    array1.swap(array2);
    EXPECT_EQ(array1.get(), directArray2);
    EXPECT_EQ(array2.get(), directArray1);

    array2.swap(array1);
    EXPECT_EQ(array1.get(), directArray1);
    EXPECT_EQ(array2.get(), directArray2);

    array1.swap(array1);
    EXPECT_EQ(array1.get(), directArray1);
    EXPECT_EQ(array1.release(), directArray1);
    EXPECT_EQ(array2.release(), directArray2);
    EXPECT_EQ(array1.get(), nullptr);
    EXPECT_EQ(array2.get(), nullptr);

    EXPECT_NO_THROW({
        delete[] directArray1;
        delete[] directArray2;
    });
}

TEST_F(SimpleArrayTest, Reset)
{
    SimpleArray array(new AllocationTracker[100]);
    EXPECT_EQ(AllocationTracker::getCount(), 100U);

    array.reset();
    EXPECT_EQ(array.get(), nullptr);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);

    AllocationTracker* directArray = new AllocationTracker[50];
    array.reset(directArray);
    EXPECT_EQ(array.get(), directArray);
    EXPECT_EQ(AllocationTracker::getCount(), 50U);

    array.reset();
    EXPECT_EQ(array.get(), nullptr);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
}

TEST_F(SimpleArrayTest, Operators)
{
    SimpleArray array;
    EXPECT_FALSE(array.isNonNull());

    AllocationTracker* directArray = new AllocationTracker[100];
    array.reset(directArray);
    EXPECT_TRUE(array.isNonNull());
    for (size_t i = 0; i < 100; ++i) {
        EXPECT_EQ(&array.getReference(i), &directArray[i]);
    }
}

/* Note: for this test, uncommenting any of the "incorrect" lines should result in a failure to
 * compile */
TEST_F(SimpleArrayTest, Compilation)
{
    SimpleArray a(new AllocationTracker[100]); // This line is correct
    SimpleArray b(nullptr); // This line is correct
    // SimpleArray c = new AllocationTracker[100]; // This line is incorrect
    // SimpleArray d = nullptr;           // This line is incorrect
    // SimpleArray d = nullptr;           // This line is incorrect
    // SimpleArray e(a);                  // This line is incorrect
    // SimpleArray f = a;                 // This line is incorrect
    // b = a;                             // This line is incorrect
    const SimpleArray ca(new AllocationTracker[100]); // This line is correct
    // ca.release();                      // This line is incorrect
    // ca.reset();                        // This line is incorrect
    // ca.swap(a);                        // This line is incorrect
}
}
