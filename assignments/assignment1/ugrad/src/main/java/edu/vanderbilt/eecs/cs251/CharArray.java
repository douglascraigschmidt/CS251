package edu.vanderbilt.eecs.cs251;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Provides a wrapper around primitive char arrays, allowing for dynamic resizing.
 */
public class CharArray implements Comparable<CharArray>, Cloneable {
    /**
     * The underlying array.
     */

    /**
     * The current size of the array.
     */

    /**
     * Default value for elements in the array.
     */

    /**
     * Constructs an array of the given size.
     * @param size Nonnegative integer size of the desired array.
     */
    public CharArray(int size) {
    }

    /**
     * Constructs an array of the given size, filled with the provided
     * default value.
     * @param size Nonnegative integer size of the desired array.
     * @param mDefaultvalue A default value for the array.
     */
    public CharArray(int size, char mDefaultvalue) {
    }

    /**
     * Copy constructor; creates a deep copy of the provided array.
     * @param s The array to be copied.
     */
    public CharArray(CharArray s) {
    }

    @Override
    public Object clone () {
        return null;
    }

    /**
     * @return The current size of the array.
     */
    public int size() {
        return 0;
    }

    /**
     * @return The current maximum capacity of the array withough
     */
    public int capacity() {
        return 0;
    }

    /**
     * Resizes the array to the requested size.
     *
     * Changes the capacity of this array to hold the requested number of elements.
     * Note the following optimizations/implementation details:
     * <ul>
     *   <li> If the requests size is smaller than the current maximum capacity, new memory
     *   is not allocated.
     *   <li> If the array was constructed with a default value, it is used to populate
     *   uninitialized fields in the array.
     * </ul>
     * @param size Nonnegative requested new size.
     */
    public void resize (int size) {
    }

    /**
     * @return the element at the requested index.
     * @param index Nonnegative index of the requested element.
     * @throws ArrayIndexOutOfBoundsException If the requested index is outside the
     * current bounds of the array.
     */
    public char get(int index) {
        return 0;
    }

    /**
     * Sets the element at the requested index with a provided value.
     * @param index Nonnegative index of the requested element.
     * @param value A provided value.
     * @throws ArrayIndexOutOfBoundsException If the requested index is outside the
     * current bounds of the array.
     */
    public void set(int index, char value) {
    }

    /**
     * Compares this array with another array.
     * <p>
     * This is a requirement of the Comparable interface.  It is used to provide
     * an ordering for CharArray elements.
     * @return a negative value if the provided array is "less than" this array,
     * zero if the arrays are identical, and a positive value if the
     * provided array is "greater than" this array.
     */
    @Override
    public int compareTo(CharArray s) {
        return 0;
    }

    /** Throws an exception if the index is out of bound. */
    private void rangeCheck (int index) {
    }
}
