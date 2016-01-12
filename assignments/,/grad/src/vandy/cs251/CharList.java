package vandy.cs251;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Iterator;

/**
 * Provides a wrapper facade around primitive char arrays, allowing
 * for dynamic resizing.
 */
public class CharList implements Comparable<CharList>,
                                  Iterable<Character>,
                                  Cloneable {
    /**
     * The head of the list.
     */
    // TODO - you fill in here

    /**
     * The current size of the array.
     */
    // TODO - you fill in here

    /**
     * Default mValue for elements in the array.
     */
    // TODO - you fill in here

    /**
     * Constructs an array of the given size.
     *
     * @param size Non-negative integer size of the desired array.
     */
    public CharList(int size) {
        // TODO - you fill in here
    }

    /**
     * Constructs an array of the given size, filled with the provided
     * default mValue.
     *
     * @param size Nonnegative integer size of the desired array.
     * @param defaultValue A default mValue for the array.
     */
    public CharList(int size,
                    char defaultValue) {
        // TODO - you fill in here
    }

    /**
     * Copy constructor; creates a deep copy of the provided CharList.
     *
     * @param s The CharList to be copied.
     */
    public CharList(CharList s) {
        // TODO - you fill in here
    }

    /**
     * Creates a deep copy of this CharList.  Implements the
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
     * Resizes the array to the requested size.
     *
     * Changes the capacity of this array to hold the requested number of elements.
     * Note the following optimizations/implementation details:
     * <ul>
     *   <li> If the requests size is smaller than the current maximum capacity, new memory
     *   is not allocated.
     *   <li> If the array was constructed with a default mValue, it is used to populate
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
     * Sets the element at the requested index with a provided mValue.
     * @param index Nonnegative index of the requested element.
     * @param value A provided mValue.
     * @throws ArrayIndexOutOfBoundsException If the requested index is outside the
     * current bounds of the array.
     */
    public void set(int index, char value) {
        // TODO - you fill in here
    }

    private Node seek(int index) {
	    // TODO - you fill in here
	    return null;
    }

    /**
     * Compares this array with another array.
     * <p>
     * This is a requirement of the Comparable interface.  It is used to provide
     * an ordering for CharList elements.
     * @return a negative mValue if the provided array is "greater than" this array,
     * zero if the arrays are identical, and a positive mValue if the
     * provided array is "less than" this array. These arrays should be compred
     * lexicographically.
     */
    @Override
    public int compareTo(CharList s) {
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

    private class Node {
	/**
	 * The value strored at this node. 
	 */
	// TODO - you fill in here

	/** 
	 * The next node in the list, if present.
	 */
	// TODO - you fill in here

        Node() {
        }

        Node(Node prev) {
	    // TODO - you fill in here
        }

        Node(char value_p, Node prev) {
	    // TODO - you fill in here
        }

        /**
         * Ensures that all subsequent nodes are properly deallocated.
         */
        void prune() {
            // Leaving the list fully linked could *potentially* cause a
            // pathological performance issue for the garbage collector.

	    // TODO - you fill in here
        }
    }

    /**
     * Define an Iterator over the CharList.
     */
    public class CharListIterator
           implements java.util.Iterator<Character> {
        /**
         * Keeps track of how far along the iterator has progressed.
         */
        // TODO - you fill in here
        private Node mNode;

        /**
         * Constructor.
         */
        public CharListIterator() {
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
