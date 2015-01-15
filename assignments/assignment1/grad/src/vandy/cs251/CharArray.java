package vandy.cs251;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Provides a wrapper facade around primitive char arrays, allowing
 * for dynamic resizing.
 */
public class CharArray implements Comparable<CharArray>,
                                  Iterable<Character>,
                                  Cloneable {
    /**
     * The underlying array.
     */
    // TODO - you fill in here

    /**
     * The current size of the array.
     */
    // TODO - you fill in here

    /**
     * Default value for elements in the array.
     */
    // TODO - you fill in here

    /**
     * Constructs an array of the given size.
     *
     * @param size Non-negative integer size of the desired array.
     */
    public CharArray(int size) {
        // TODO - you fill in here
    }

    /**
     * Constructs an array of the given size, filled with the provided
     * default value.
     *
     * @param size Nonnegative integer size of the desired array.
     * @param mDefaultvalue A default value for the array.
     */
    public CharArray(int size,
                     char mDefaultvalue) {
        // TODO - you fill in here
    }

    /**
     * Copy constructor; creates a deep copy of the provided CharArray.
     *
     * @param s The CharArray to be copied.
     */
    public CharArray(CharArray s) {
        // TODO - you fill in here
    }

    /**
     * Creates a deep copy of this CharArray.  Implements the
     * Prototype pattern.
     */
    @Override
    public Object clone() {
        // TODO - you fill in here (replace return null with right
        // implementation).
    	return null;
    }

    /**
     * @return The current size of the array.
     */
    public int size() {
        // TODO - you fill in here (replace return 0 with right
        // implementation).
    	return 0;
    }

    /**
     * @return The current maximum capacity of the array.
     */
    public int capacity() {
        // TODO - you fill in here (replace return 0 with right
        // implementation).
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
    public void resize(int size) {
        // TODO - you fill in here
    }

    /**
     * @return the element at the requested index.
     * @param index Nonnegative index of the requested element.
     * @throws ArrayIndexOutOfBoundsException If the requested index is outside the
     * current bounds of the array.
     */
    public char get(int index) {
        // TODO - you fill in here (replace return '\0' with right
        // implementation).
    	return '\0';
    }

    /**
     * Sets the element at the requested index with a provided value.
     * @param index Nonnegative index of the requested element.
     * @param value A provided value.
     * @throws ArrayIndexOutOfBoundsException If the requested index is outside the
     * current bounds of the array.
     */
    public void set(int index, char value) {
        // TODO - you fill in here
    }

    /**
     * Compares this array with another array.
     * <p>
     * This is a requirement of the Comparable interface.  It is used to provide
     * an ordering for CharArray elements.
     * @return a negative value if the provided array is "greater than" this array,
     * zero if the arrays are identical, and a positive value if the
     * provided array is "less than" this array. These arrays should be compred
     * lexicographically.
     */
    @Override
    public int compareTo(CharArray s) {
        // TODO - you fill in here (replace return 0 with right
        // implementation).
        return 0;
    }

    /**
     * Throws an exception if the index is out of bound.
     */
    private void rangeCheck(int index) {
        // TODO - you fill in here
    }

    /**
     * Define an Iterator over the CharArray.
     */
    public class CharArrayIterator
           implements java.util.Iterator<Character> {
        /**
         * Keeps track of how far along the iterator has progressed.
         */
        // TODO - you fill in here

        /**
         * Constructor.
         */
        public CharArrayIterator() {
            // TODO - you fill in here
        }

        /**
         * @return true if there are any remaining elements that
         * haven't been iterated through yet; else false.
         */
        @Override
        public boolean hasNext() {
            // TODO - you fill in here (replace return false with
            // right implementation)
            return false;
        }

        /**
         * @return The next element in the iteration.
         */
        @Override
        public Character next() {
            // TODO - you fill in here (replace return '\0' with right
            // implementation)
            return '\0';
        }
    }

    /**
     * Factory method that returns an Iterator.
     */
    public Iterator<Character> iterator () {
        // TODO - you fill in here (replace return null with right
        // implementation)
        return null;
    }
}
