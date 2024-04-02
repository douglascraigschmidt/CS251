// @author G. Hemingway, copyright 2020 - All rights reserved

#ifndef SCOPEDARRAY_H
#define SCOPEDARRAY_H

#include <cstdint>
#include <exception>
#include <iostream>

template <typename T> class ScopedArray {
public:
    /*
     * Deny access to copy-constructor and assignment operator
     */
    ScopedArray(const ScopedArray<T>& rhs) = delete;
    ScopedArray<T>& operator=(const ScopedArray<T>& rhs) = delete;

    /*
     * Create a new ScopedArray
     * @param rhs Pointer to array of objects to hold
     */
    explicit ScopedArray(T* rhs = nullptr);

    /**
     * Default destructor
     */
    ~ScopedArray();

    /**
     * Getter for the underlying memory object
     * @return Pointer to underlying memory object
     */
    T* get() const;

    /**
     * Const subscript operator to get specified element - no bounds checking
     * @param pos Index of specified element
     * @return Const ref to specified element
     */
    const T& operator[](uint32_t pos) const;

    /**
     * Subscript operator to get specified element - no bounds checking
     * @param pos Index of specified element
     * @return Ref to specified element
     */
    T& operator[](uint32_t pos);

    /**
     * Implicit bool operator to check if array is empty (points to nullptr)
     * @return True if pointing to valid array
     */
    operator bool() const;

    /**
     * Swap the underlying pointers
     * @param rhs ScopedArray to swap pointers with
     */
    void swap(ScopedArray& rhs);

    /**
     * Set array pointer to nullptr and return prior value
     * @return Prior value of the array pointer
     */
    T* release();

    /**
     * Set internal array pointer to new value.  Delete old pointer.
     * Should be a no-op if reset to current pointer (i.e. set to self)
     * @param rhs Pointer to new array (nullptr by default)
     */
    void reset(T* rhs = nullptr);

private:
    /**
     * Pointer to physical memory object
     */
    T* array;
};

// Include the class definition
#include "../src/ScopedArray.cpp"

#endif // SCOPEDARRAY_H
