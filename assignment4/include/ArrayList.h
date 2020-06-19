// @author G. Hemingway, copyright 2020 - All rights reserved

#ifndef ARRAYLIST_H
#define ARRAYLIST_H

#include "ScopedArray.h"
#include <cstdint>
#include <iterator>

// Forward declarations
template <typename T> class ArrayListIterator;

/**
 * An array-backed list implementation that must provide strong exception safety guarantees on all
 * methods. The memory model of this class guarantees that all elements in the ArrayList are stored
 * in a contiguous block of memory. This class assumes that the parametrized type has a default and
 * a copy constructor, an assignment operator, and a destructor, the last of which never throws an
 * exception. For simplicity, destructors of the objects stored in the ArrayList are not guaranteed
 * to be called until the ArrayList object's destructor is triggered.
 */

template <typename T> class ArrayList {
public:
    // Useful traits
    typedef ArrayListIterator<T> iterator;

    /**
     * Creates an ArrayList of size 0.
     */
    ArrayList();

    /**
     * Creates an ArrayList of the provided size and fills it with the provided
     * value - default to the default value of the template type.
     * @param size size of the ArrayList to create
     * @param value value used to fill the ArrayList
     */
    explicit ArrayList(const uint32_t& size, const T& value = T());

    /**
     * Creates a deep copy of the provided ArrayList
     * @param src ArrayList to copy
     */
    ArrayList(const ArrayList<T>& src);

    /**
     * Performs move constructor semantics on the provided ArrayList
     * @param src ArrayList to move
     */
    ArrayList(ArrayList<T>&& src) noexcept;

    /**
     * Makes *this a deep copy of the provided ArrayList.
     * @param src ArrayList to copy
     * @return *this for chaining
     */
    ArrayList<T>& operator=(const ArrayList<T>& src);

    /**
     * Performs move assignment semantics on the provided ArrayList.
     * @param src ArrayList to move
     * @return *this for chaining
     */
    ArrayList<T>& operator=(ArrayList<T>&& src) noexcept;

    /**
     * Adds the provided element to the end of this ArrayList.  If the ArrayList needs to be
     * enlarged, double the capacity from the current capacity, or go from zero to one.
     * @param value value to add
     * @return total array capacity
     */
    const uint32_t& add(const T& value);

    /**
     * Inserts the specified value into this ArrayList at the specified index.
     * The object is inserted before any previous element at the specified
     * location. If the ArrayList needs to be enlarged, continue doubling the capacity
     * from the current capacity until the desired index is in range.  Fill any empty
     * elements up to the new element's index with the default value for the template type.
     * @param index location at which to insert the new element
     * @param value the element to insert
     * @return total array capacity
     */
    const uint32_t& add(const uint32_t& index, const T& value);

    /**
     * Clears this ArrayList, leaving it empty.
     */
    void clear();

    /**
     * Returns a const T & to the element stored at the specified index.
     * If the index is out of bounds, std::out_of_range is thrown with the index
     * as its message.
     * @param index the desired location
     * @return a const T & to the desired element.
     */
    const T& get(const uint32_t& index) const;

    /**
     * Returns a T & to the element stored at the specified index.
     * If the index is out of bounds, std::out_of_range is thrown with the index
     * as its message.
     * @param index the desired location
     * @return a T & to the desired element.
     */
    T& get(const uint32_t& index);

    /**
     * Returns a T & to the element stored at the specified index.
     * No range checking is performed.
     * @param index the desired location
     * @return a T & to the desired element.
     */
    T& operator[](const uint32_t& index);

    /**
     * Returns a const T & to the element stored at the specified index.
     * No range checking is performed.
     * @param index the desired location
     * @return a const T & to the desired element.
     */
    const T& operator[](const uint32_t& index) const;

    /**
     * Empty check.
     * @return True if this ArrayList is empty and false otherwise.
     */
    bool isEmpty() const;

    /**
     * Returns iterator to the beginning; in this case, a random access iterator
     * @return an iterator to the beginning of this ArrayList.
     */
    iterator begin();

    /**
     * Returns the past-the-end iterator of this ArrayList.
     * @return a past-the-end iterator of this ArrayList.
     */
    iterator end();

    /**
     * Removes an element at the specified location from this ArrayList and
     * returns it. Elements following index are shifted down. If index is out of
     * range, std::out_of_range is thrown with index as its message.
     * @param index the desired location
     * @return a copy of the removed element.
     */
    T remove(const uint32_t& index);

    /**
     * Sets the element at the desired location to the specified value. If index
     * is out of range, std::out_of_range is thrown with index as its message.
     * @param index the location to change
     * @param value the new value of the specified element.
     */
    void set(const uint32_t& index, const T& value);

    /**
     * Returns the size of this ArrayList.
     * @return the size of this ArrayList.
     */
    [[nodiscard]] uint32_t size() const;

    /**
     * Perform an exception-safe swap of the contents of *this with
     * src.
     */
    void swap(ArrayList<T> &src) noexcept;

private:
    /**
     * Wrapper around our physical buffer.
     */
    ScopedArray<T> mArray;

    /**
     * The logical size of this ArrayList.
     */
    uint32_t mSize;

    /**
     * The maximum capacity of the physical buffer.
     */
    uint32_t mCapacity;
};

#include "../src/ArrayList.cpp"
#include "ArrayListIter.h"

#endif // ARRAYLIST_H
