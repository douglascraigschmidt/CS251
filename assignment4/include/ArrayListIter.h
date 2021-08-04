// @author G. Hemingway, copyright 2020 - All rights reserved

#ifndef ARRAYLISTITER_H
#define ARRAYLISTITER_H

/**
 * A random access iterator to the ArrayList.
 */
template <typename T>
class ArrayListIterator : public std::iterator<std::random_access_iterator_tag, T> {
public:
    /**
     * Deny access to the default constructor - don't ever need this
     */
    ArrayListIterator() = delete;

    /**
     * Tests for iterator equality.
     * @param rhs The iterator to compare against.
     * @return True if *this and rhs point to the same element.
     */
    bool operator==(const ArrayListIterator<T>& rhs) const;

    /**
     * Tests for iterator inequality.
     * @param rhs The iterator to compare against.
     * @return False if *this and rhs point to the same element.
     */
    bool operator!=(const ArrayListIterator<T>& rhs) const;

    /**
     * Dereference operator.
     * @return A T & to the value pointed to by *this.
     */
    T& operator*();

    /**
     * Dereference operator.
     * @return A constant T & to the value pointed to by *this.
     */
    const T& operator*() const;

    /**
     * Dereference operator.
     * @return A pointer to the value pointed to by this.
     */
    T* operator->();

    /**
     * Dereference operator.
     * @return A pointer to the value pointed to by this.
     */
    const T* operator->() const;

    /**
     * Preincrement operator.
     * @return *this after the increment.
     */
    ArrayListIterator<T>& operator++();

    /**
     * Postincrement operator.
     * @return The iterator before the increment.
     */
    ArrayListIterator<T> operator++(int);

    /**
     * Predecrement operator.
     * @return *this after the decrement.
     */
    ArrayListIterator<T>& operator--();

    /**
     * Postdecrement operator.
     * @return The iterator before the decrement.
     */
    ArrayListIterator<T> operator--(int);

    /**
     * Returns an iterator offset elements forward
     * @param offset distance to move forward
     * @return the moved iterator.
     */
    ArrayListIterator<T> operator+(int offset) const;

    /**
     * Returns an iterator offset elements backwards
     * @param offset distance to move back
     * @return the moved iterator.
     */
    ArrayListIterator<T> operator-(int offset) const;

    /**
     * Iterator subtraction (equivalent to pointer subtraction).
     *
     * @param rhs Iterator to subtract
     * @return distance between iterators
     */
    int operator-(const ArrayListIterator<T>& rhs) const;

    /**
     * Increments this iterator by offset
     * @param offset distance to move forward
     * @return *this after the operation
     */
    ArrayListIterator<T>& operator+=(const int& offset);

    /**
     * Decrements this iterator by offset
     * @param offset distance to move backwards
     * @return *this after the operation.
     */
    ArrayListIterator<T>& operator-=(const int& offset);

    /**
     * Subscript operator.
     * Must call to const operator[].  Do not reimplement!!!
     * @param index offset from current position.
     * @return the T & to the value at the index offset from *this.
     */
    T& operator[](const int& index);

    /**
     * Subscript operator.
     * @param index offset from current position.
     * @return the const T & to the value at the index offset from *this.
     */
    const T& operator[](int index) const;

private:
    /**
     * Creates an iterator pointing to the same element as the provided pointer.
     * @param ptr
     */
    explicit ArrayListIterator(T* ptr);

    friend class ArrayList<T>;

    template <typename X>
    ArrayListIterator<X> operator+(int offset, const ArrayListIterator<X>& iter);

    /**
     * Pointer to the actual element.
     */
    T* mPtr;
};

/**
 * Free function to make arithmetic addition commutative.
 *
 * @param offset offset from current position
 * @param iter array list iterator to offset
 * @return an offset ArrayListIterator
 */
template <typename T> ArrayListIterator<T> operator+(int offset, const ArrayListIterator<T>& iter);

#include "../src/ArrayListIter.cpp"

#endif // ARRAYLISTITER_H
