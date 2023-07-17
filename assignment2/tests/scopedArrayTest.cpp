// @author G. Hemingway, copyright 2020 - All rights reserved
//
#include "ScopedArray.h"
#include "AllocationTracker.h"
#include <gtest/gtest.h>

namespace {
// The fixture for testing our class.
class ScopedArrayTest : public ::testing::Test {
};

TEST_F(ScopedArrayTest, ConstructorDestructorGet)
{
    EXPECT_NO_THROW({
        const ScopedArray<AllocationTracker> array;
        EXPECT_EQ(array.get(), nullptr);
        EXPECT_EQ(AllocationTracker::getArrayCount(), 0U);
        EXPECT_EQ(AllocationTracker::getCount(), 0U);
    });
    EXPECT_NO_THROW({
        auto direct = new AllocationTracker[100];
        const ScopedArray<AllocationTracker> a(direct);
        EXPECT_EQ(a.get(), direct);
        EXPECT_EQ(AllocationTracker::getArrayCount(), 1U);
        EXPECT_EQ(AllocationTracker::getCount(), 100U);
    });
    EXPECT_EQ(AllocationTracker::getArrayCount(), 0U);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
}

TEST_F(ScopedArrayTest, ReleaseSwap)
{
    auto directArray1 = new AllocationTracker[100];
    ScopedArray<AllocationTracker> array1(directArray1);
    EXPECT_EQ(array1.get(), directArray1);

    auto directArray2 = new AllocationTracker[50];
    ScopedArray<AllocationTracker> array2(directArray2);
    EXPECT_EQ(array2.get(), directArray2);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 2U);
    EXPECT_EQ(AllocationTracker::getCount(), 150U);

    array1.swap(array2);
    EXPECT_EQ(array1.get(), directArray2);
    EXPECT_EQ(array2.get(), directArray1);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 2U);
    EXPECT_EQ(AllocationTracker::getCount(), 150U);

    array2.swap(array1);
    EXPECT_EQ(array1.get(), directArray1);
    EXPECT_EQ(array2.get(), directArray2);

    array1.swap(array1);
    EXPECT_EQ(array1.get(), directArray1);
    EXPECT_EQ(array1.release(), directArray1);
    EXPECT_EQ(array2.release(), directArray2);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 2U);
    EXPECT_EQ(AllocationTracker::getCount(), 150U);
    EXPECT_EQ(array1.get(), nullptr);
    EXPECT_EQ(array2.get(), nullptr);
    // Do some cleanup
    delete[] directArray1;
    delete[] directArray2;
}

TEST_F(ScopedArrayTest, Reset)
{
    ScopedArray<AllocationTracker> array(new AllocationTracker[100]);
    EXPECT_EQ(AllocationTracker::getCount(), 100U);

    EXPECT_NO_THROW({ array.reset(); });

    EXPECT_EQ(array.get(), nullptr);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 0U);

    auto directArray = new AllocationTracker[50];
    array.reset(directArray);

    EXPECT_EQ(array.get(), directArray);
    EXPECT_EQ(AllocationTracker::getCount(), 50U);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 1U);

    array.reset();
    EXPECT_EQ(array.get(), nullptr);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 0U);

    array.reset();
    EXPECT_EQ(array.get(), nullptr);
    EXPECT_EQ(AllocationTracker::getCount(), 0U);
    EXPECT_EQ(AllocationTracker::getArrayCount(), 0U);

    ScopedArray<AllocationTracker> finalArray(new AllocationTracker[100]);
    EXPECT_NO_THROW({ finalArray.reset(finalArray.get()); });
}

TEST_F(ScopedArrayTest, Operators)
{
    const ScopedArray<AllocationTracker> array1;
    bool isNonNull(array1);
    bool isNull = !array1;

    EXPECT_FALSE(isNonNull);
    EXPECT_TRUE(isNull);
    EXPECT_FALSE(array1);

    auto directArray2 = new AllocationTracker[100];
    const ScopedArray<AllocationTracker> array2(directArray2);

    EXPECT_TRUE(array2);
    for (uint32_t i = 0; i < 99; ++i) {
        EXPECT_EQ(&array2[i], &directArray2[i]);
        // array2[i].nonConstMethod();      // This line is incorrect!!!
    }

    auto directArray3 = new AllocationTracker[100];
    ScopedArray<AllocationTracker> array3(directArray3);
    for (uint32_t i = 0; i < 99; ++i) {
        EXPECT_EQ(&array3[i], &directArray3[i]);
        array3[i].nonConstMethod(); // This line is correct
    }
}

/* Note: uncommenting any of the "incorrect" lines should result in a failure to compile */
TEST_F(ScopedArrayTest, Compilation)
{
    ScopedArray<int> a(new int[100]); // This line is correct
    ScopedArray<int> b(nullptr); // This line is correct
    // ScopedArray<int> c = new int[100];  // This line is incorrect
    // ScopedArray<int> d = nullptr;       // This line is incorrect
    // ScopedArray<int> e(a);              // This line is incorrect
    // ScopedArray<int> f = a;             // This line is incorrect
    // b = a;                              // This line is incorrect
    // bool willFail = a;                  // This line is incorrect

    const ScopedArray<int> ca(new int[100]); // This line is correct
    // ca.release();                       // This line is incorrect
    // ca.reset();                         // This line is incorrect
    // ca.swap(a);                         // This line is incorrect
}
}
