// @author G. Hemingway, copyright 2020 - All rights reserved

#ifndef ARRAYLIST_CPP
#define ARRAYLIST_CPP

#include <algorithm>
#include <stdexcept>

/**
 * Default constructor
 */
template <typename T>
ArrayList<T>::ArrayList()
{
}

/**
 * Creates an ArrayList of the provided size and fills it with the provided
 * value.
 * @param size size of the ArrayList to create
 * @param value value used to fill the ArrayList
 */
template <typename T>
ArrayList<T>::ArrayList(uint32_t size, const T& value)
{
}

/**
 * Creates a deep copy of the provided ArrayList
 * @param src ArrayList to copy
 */
template <typename T>
ArrayList<T>::ArrayList(const ArrayList<T>& src)
{
}

/**
 * Performs move constructor semantics on the provided ArrayList
 * @param src ArrayList to move
 */
template <typename T>
ArrayList<T>::ArrayList(ArrayList<T>&& src) noexcept
{
}

/**
 * Makes *this a deep copy of the provided ArrayList.
 * @param src ArrayList to copy
 * @return *this for chaining
 */
template <typename T> ArrayList<T>& ArrayList<T>::operator=(const ArrayList<T>& src)
{
}

/**
 * Performs move assignment semantics on the provided ArrayList.
 * @param src ArrayList to move
 * @return *this for chaining
 */
template <typename T> ArrayList<T>& ArrayList<T>::operator=(ArrayList<T>&& src) noexcept
{
}

/**
 * Adds the provided element to the end of this ArrayList.
 * @param value value to add
 */
template <typename T> uint32_t ArrayList<T>::add(const T& value)
{
}

/**
 * Inserts the specified value into this ArrayList at the specified index.
 * The object is inserted before any previous element at the specified
 * location. If this ArrayList needs to be enlarged, default values are used
 * to fill the gaps up to mSize.
 * @param index location at which to insert the new element
 * @param value the element to insert
 */
template <typename T> uint32_t ArrayList<T>::add(uint32_t index, const T& value)
{
}

/**
 * Clears this ArrayList, leaving it empty.
 */
template <typename T> void ArrayList<T>::clear()
{
}

/**
 * Returns a const T & to the element stored at the specified index.
 * If the index is out of bounds, std::out_of_range is thrown with the index
 * as its message.
 * @param index the desired location
 * @return a const T & to the desired element.
 */
template <typename T> const T &ArrayList<T>::get(uint32_t index) const
{
}

/**
 * Returns a T & to the element stored at the specified index.
 * If the index is out of bounds, std::out_of_range is thrown with the index
 * as its message.
 * @param index the desired location
 * @return a T & to the desired element.
 */
template <typename T> T& ArrayList<T>::get(uint32_t index)
{
}

/**
 * Returns a T & to the element stored at the specified index.
 * No range checking is performed.
 * @param index the desired location
 * @return a T & to the desired element.
 */
template <typename T> T& ArrayList<T>::operator[](uint32_t index)
{
}

/**
 * Returns a const T & to the element stored at the specified index.
 * No range checking is performed.
 * @param index the desired location
 * @return a const T & to the desired element.
 */
template <typename T> const T& ArrayList<T>::operator[](uint32_t index) const
{
}

/**
 * Empty check.
 * @return True if this ArrayList is empty and false otherwise.
 */
template <typename T> bool ArrayList<T>::isEmpty() const
{
}

/**
 * Returns iterator to the beginning; in this case, a random access iterator
 * @return an iterator to the beginning of this ArrayList.
 */
template <typename T> ArrayListIterator<T> ArrayList<T>::begin()
{
}

/**
 * Returns the past-the-end iterator of this ArrayList.
 * @return a past-the-end iterator of this ArrayList.
 */
template <typename T> ArrayListIterator<T> ArrayList<T>::end()
{
}

/**
 * Returns const iterator to the beginning; in this case, a random access
 * iterator
 * @return an const iterator to the beginning of this ArrayList.
 */
template <typename T> typename ArrayList<T>::const_iterator ArrayList<T>::begin() const
{
}

/**
 * Returns the past-the-end const iterator of this ArrayList.
 * @return a past-the-end const iterator of this ArrayList.
 */
template <typename T> typename ArrayList<T>::const_iterator ArrayList<T>::end() const
{
}

/**
 * Removes an element at the specified location from this ArrayList and
 * returns it. Elements following index are shifted down. If index is out of
 * range, std::out_of_range is thrown with index as its message.
 * @param index the desired location
 * @return a copy of the removed element.
 */
template <typename T> void ArrayList<T>::remove(uint32_t index)
{
}

/**
 * Sets the element at the desired location to the specified value. If index
 * is out of range, std::out_of_range is thrown with index as its message.
 * @param index the location to change
 * @param value the new value of the specified element.
 */
template <typename T> void ArrayList<T>::set(uint32_t index, const T& value)
{
}

/**
 * Returns the size of this ArrayList.
 * @return the size of this ArrayList.
 */
template <typename T> uint32_t ArrayList<T>::size() const
{
}

/**
 * Perform an exception-safe swap of the contents of *this with src.
 */
template <typename T> void
ArrayList<T>::swap(ArrayList<T> &src) noexcept {
}

#endif // ARRAYLIST_CPP
