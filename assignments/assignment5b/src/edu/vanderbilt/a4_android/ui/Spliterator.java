package edu.vanderbilt.a4_android.ui;

import edu.vanderbilt.a4_android.ui.EntityVisitor;

interface Spliterator<T> {
  public long estimateSize();
  public boolean tryAdvance(EntityVisitor visitor);
  public Spliterator<T> trySplit();
}
