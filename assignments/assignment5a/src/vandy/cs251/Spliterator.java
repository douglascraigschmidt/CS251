package vandy.cs251;

import vandy.cs251.EntityVisitor;

interface Spliterator<T> {
  /**
   * Returns an estimate of the number of elements that would be
   * encountered by a full traversal.
   */
  public long estimateSize();

  /**
   * Attempts to apply the supplied visitor to the next element.
   * Returns true if successful, false if there are no more elements.
   */
  public boolean tryAdvance(EntityVisitor visitor);

  /**
   * If this spliterator can be partitioned, returns a Spliterator
   * covering elements, that will, upon return from this method, not
   * be covered by this Spliterator.
   */
  public Spliterator<T> trySplit();
}
