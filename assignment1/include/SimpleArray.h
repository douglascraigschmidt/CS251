// @author G. Hemingway, copyright 2018 - All rights reserved
//
#ifndef SIMPLE_ARRAY_H
#define SIMPLE_ARRAY_H

// Just include the special AllocationTracker class
#include "AllocationTracker.h"

class SimpleArray {
public:
    explicit SimpleArray(AllocationTracker* ptr = nullptr);
    ~SimpleArray();

    // Accessors
    AllocationTracker* get() const;
    bool isNonNull() const;

    /**
     *  getReference() is const and returns a non-const type in order
     *  to mimic dereferencing raw pointers through the subscript
     *  operator as closely as possible. A pointer-that-is-const is
     *  permitted to alter the data where it points, but may never
     *  point to a different address; the same should be true of
     *  SimpleArrays-that-are-const.
     */
    AllocationTracker& getReference(const uint32_t i) const;

    // Mutators
    AllocationTracker* release();
    void reset(AllocationTracker* rhs = nullptr);
    void swap(SimpleArray& rhs);

private:
    // Copy construction and copy assignment are forbidden.
    SimpleArray(const SimpleArray&) = delete;
    const SimpleArray& operator=(const SimpleArray&) = delete;

    // Data members
    AllocationTracker* mArray;
};

#endif // SIMPLE_ARRAY_H
