// @author G. Hemingway, copyright 2020 - All rights reserved

#include "ArrayList.h"
#include <gtest/gtest.h>

namespace {
// The fixture for testing ArrayListIterator.
class ArrayListIteratorTest : public ::testing::Test {
};

TEST_F(ArrayListIteratorTest, Begin)
{
    ArrayList<int> a(20, 17);
    ArrayList<int>::iterator i = a.begin();
    const ArrayList<int>::iterator j = a.begin();
    EXPECT_TRUE(i == j);
    EXPECT_FALSE(i != j);
    EXPECT_EQ(*i, *j);
}

TEST_F(ArrayListIteratorTest, Dereference)
{
    ArrayList<int> a(20, 17);
    ArrayList<int>::iterator i = a.begin();
    *i = 2;

    // The next two statements, if both uncommented, should not compile
    //    const ArrayList<int>::iterator j = a.begin();
    //    *j = 3;
}

TEST_F(ArrayListIteratorTest, Arrow)
{
    ArrayList<std::string> a(20, "Test");
    for (auto i = a.begin(); i != a.end(); ++i) {
        EXPECT_EQ(i->length(), 4U);
    }
}

TEST_F(ArrayListIteratorTest, ConstArrow)
{
    ArrayList<std::string> a(20, "Test");
    const auto iter = a.begin();
    EXPECT_EQ(iter->length(), 4U);
}

TEST_F(ArrayListIteratorTest, IntIncrement)
{
    ArrayList<std::string> a(21, "Test");
    auto i = a.begin();
    EXPECT_EQ(i->length(), 4U);
    auto check = 3 + i;
    i += 3;
    EXPECT_EQ(check, i);
}

TEST_F(ArrayListIteratorTest, ConstIntIncrement)
{
    ArrayList<std::string> a(21, "Test a const iter");
    const auto i = a.begin();
    EXPECT_EQ(i->length(), 17U);
    auto check = 3 + i;
    check -= 3;
    EXPECT_EQ(check, i);
}

TEST_F(ArrayListIteratorTest, IncrementDecrement)
{
    ArrayList<int> a(20, 17);
    ArrayList<int>::iterator i = a.begin();
    const ArrayList<int>::iterator j = a.begin();
    i++;
    EXPECT_FALSE(i == j);
    i--;
    EXPECT_TRUE(i == j);
    ++i;
    EXPECT_FALSE(i == j);
    --i;
    EXPECT_TRUE(i == j);
    i = i + 2;
    EXPECT_FALSE(i == j);
    i = i - 2;
    EXPECT_TRUE(i == j);
    i += 2;
    EXPECT_FALSE(i == j);
    i -= 2;
    EXPECT_TRUE(i == j);
    i[2] = 12345;
    EXPECT_EQ(a[2], 12345);
}

TEST_F(ArrayListIteratorTest, BasicArithmetic)
{
    // Test for operator+ and operator-
    ArrayList<int> a(20, 17);
    auto begin = a.begin();
    auto end = a.end();
    EXPECT_EQ(end - begin, 20);
    auto middle = begin + 10;
    EXPECT_EQ(end - middle, 10);
}

TEST_F(ArrayListIteratorTest, End)
{
    ArrayList<int> a(20, 17);
    ArrayList<int>::iterator i = a.begin();
    uint32_t count = 0;
    for (; i != a.end(); ++i) {
        count++;
        EXPECT_EQ(*i, 17);
    }
    EXPECT_EQ(count, 20UL);
}
}