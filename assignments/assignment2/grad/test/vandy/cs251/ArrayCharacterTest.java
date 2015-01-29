package vandy.cs251;

import vandy.cs251.Array;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.ListIterator;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Test the Array<Character> class.
 */
public class ArrayCharacterTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_Constructor() {
        Array<Character> tmp = new Array<Character>(10);
        assertEquals(10, tmp.size());

        tmp = new Array<Character>(15);
        assertEquals(15, tmp.size ());

        tmp = new Array<Character>(0);
        assertEquals(0, tmp.size ());

        exception.expect(NegativeArraySizeException.class);
        tmp = new Array<Character>(-1);
    }

    @Test
    public void test_DefaultValueConstructor() {
        Array<Character> tmp = new Array<Character>(10, 'b');

        for(int i = 0; i < 10; ++i) {
          assertEquals('b', (char)tmp.get(i));
        }
    }

    @Test
    public void test_CopyConstructor() {
        Array<Character> tmp = new Array<Character>(10, 'b');

        Array<Character> tmp_c = new Array<Character>(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = new Array<Character>(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertFalse(tmp.capacity() == tmp_c.capacity());

        tmp_c.resize (11);

        assertEquals('b', (char) tmp_c.get(10));

        tmp_c.resize (2);

        tmp = new Array<Character> (tmp_c);
        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertEquals(2, tmp.capacity());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_Clone() {
        Array<Character> tmp = new Array<Character>(10, 'b');
        Array<Character> tmp_c = (Array<Character>) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = (Array<Character>) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertFalse(tmp.capacity() == tmp_c.capacity());
    }


    @Test
    public void test_SetGet() {
        Array<Character> tmp = new Array<Character>(1);
        assertEquals(null, tmp.get(0));

        tmp.set(0, 'a');
        assertEquals('a', (char) tmp.get(0));

        tmp.set(0, 'b');
        assertEquals('b', (char)tmp.get(0));
    }

    @Test
    public void test_SetExceptionPositive() {
        Array<Character> tmp = new Array<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionPositive() {
        Array<Character> tmp = new Array<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_GetExceptionNegative() {
        Array<Character> tmp = new Array<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(-1);
    }

    @Test
    public void test_SetExceptionNegative() {
        Array<Character> tmp = new Array<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(-1, 'a');
    }

    @Test
    public void test_SetExceptionResized() {
        Array<Character> tmp = new Array<Character>(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionResized() {
        Array<Character> tmp = new Array<Character>(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_Resize() {
        Array<Character> tmp = new Array<Character>(0);
        assertEquals(0, tmp.size());

        tmp.resize(0);
        assertEquals(0, tmp.size());

        tmp.resize(1);
        assertEquals(1, tmp.size());
        assertEquals(null, tmp.get(0));
        tmp.set(0, 'a');

        tmp.resize(1);
        assertEquals(1, tmp.size());
        assertEquals('a', (char) tmp.get(0));

        tmp.resize(2);
        assertEquals(2, tmp.size());
        assertEquals('a', (char) tmp.get(0));
        assertEquals(null, tmp.get(1));

        tmp = new Array<Character> (3, 'a');
        assertEquals(3, tmp.size());
        assertEquals(3, tmp.capacity());
        tmp.set (1, 'b');
        tmp.set (2, 'c');
        tmp.resize (0);
        assertEquals(0, tmp.size());
        assertEquals(3, tmp.capacity());
        tmp.resize (1);
        assertEquals(1, tmp.size());
        assertEquals(3, tmp.capacity());
        assertEquals('a', (char) tmp.get(0));
        tmp.resize (2);
        assertEquals(2, tmp.size());
        assertEquals(3, tmp.capacity());
        assertEquals('a', (char) tmp.get(0));
        assertEquals('a', (char) tmp.get(1));
        tmp.resize (3);
        assertEquals(3, tmp.size());
        assertEquals(3, tmp.capacity());
        assertEquals('a', (char) tmp.get(0));
        assertEquals('a', (char) tmp.get(1));
        assertEquals('a', (char) tmp.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_CompareTo () {
        Array<Character> a = new Array<Character> (0, 'a');
        Array<Character> b = new Array<Character> (0, 'b');

        assertEquals (0, a.compareTo (b));
        assertEquals (0, a.compareTo (a));
        assertEquals (0, b.compareTo (b));

        a.resize (1);

        assertTrue (a.compareTo (b) != 0);
        assertTrue (b.compareTo (a) != 0);

        b.resize(1);

        assertTrue (a.compareTo(b) != 0);
        assertTrue (b.compareTo(a) != 0);
        assertTrue (a.compareTo(b) < 0);
        assertTrue (b.compareTo(a) > 0);

        b.resize (2);

        assertTrue (a.compareTo(b) != 0);
        assertTrue (b.compareTo(a) != 0);
        assertTrue (a.compareTo(b) < 0);
        assertTrue (b.compareTo(a) > 0);

        @SuppressWarnings("unchecked")
        Array<Character> bba = (Array<Character>) b.clone ();

        assertEquals (0, b.compareTo (bba));

        bba.resize (3);
        bba.set (2, 'a');

        assertTrue (b.compareTo (bba) < 0);
        assertTrue (bba.compareTo (b) > 0);

        b.resize (10);
        b.resize (3);
        b.set (2, 'a');

        assertTrue (b.compareTo (bba) == 0);
        assertTrue (bba.compareTo (b) == 0);

        a = new Array<Character> (1, 'a');
        b = new Array<Character> (2, 'a');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        b = new Array<Character> (2, 'a');
        b.set (1, 'b');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.resize (3);
        a.set (1, 'b');
        a.set (2, 'c');

        b = (Array<Character>) a.clone ();
        b.resize (4);
        b.set (3, 'd');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.set (1, 'z');

        assertTrue (a.compareTo (b) > 0);
        assertTrue (b.compareTo (a) < 0);
    }

    @Test
    public void test_Iterator () {
        Array<Character> a = new Array<Character> (0, 'a');

        Iterator<Character> it = a.iterator ();

        assertTrue (it.hasNext () == false);

        a.resize (1);

        it = a.iterator ();
        assertTrue (it.hasNext ());
        assertTrue (it.next () == 'a');
        assertTrue (it.hasNext () == false);

        a.resize (2);

        it = a.iterator ();
        assertTrue (it.hasNext ());
        assertTrue (it.next () == 'a');
        assertTrue (it.hasNext ());
        assertTrue (it.next () == 'a');
        assertTrue (it.hasNext () == false);

        a.set (1, 'b');

        it = a.iterator ();
        assertTrue (it.hasNext ());
        assertTrue (it.next () == 'a');
        assertTrue (it.hasNext ());
        assertTrue (it.next () == 'b');
        assertTrue (it.hasNext () == false);

        exception.expect(ArrayIndexOutOfBoundsException.class);
        it.next ();
    }

  @Test
  public void test_IteratorRemove () {
    Array<Character> a = new Array<Character> (2, 'a');
    a.set (1, 'b');

    Iterator<Character> it = a.iterator ();

    char c = it.next ();
    assertEquals ('a', c);

    it.remove ();

    assertEquals (1, a.size ());
    assertEquals ('b', (char) a.get (0));

    c = it.next ();

    assertEquals ('b', c);

    it.remove ();

    assertEquals (0, a.size ());

    a.resize (2);

    it = a.iterator ();
    c = it.next ();
    it.remove ();

    exception.expect(IllegalStateException.class);
    it.remove ();
  }

  @Test
  public void test_IteratorRemoveEmpty () {
    Array<Character> a = new Array<Character> (2, 'a');
    Iterator<Character> it = a.iterator ();
    exception.expect(IllegalStateException.class);
    it.remove ();
  }

  @Test
  public void test_Spliterator () {
    Array<Integer> a = new Array<Integer> (10);

    for (int i = 0; i < 10; ++i) {
      a.set (i, i);
    }

    Spliterator<Integer> it = a.spliterator ();

    assertEquals (Spliterator.IMMUTABLE |
                  Spliterator.NONNULL |
                  Spliterator.ORDERED |
                  Spliterator.SIZED |
                  Spliterator.SUBSIZED, it.characteristics ());
    assertEquals (10, it.estimateSize ());

    Consumer<Integer> act = new Consumer<Integer> () {
        private int mSum = 0;

        @Override
        public void accept (Integer item) {
          mSum += item;
        }

        public int sum () {
          return mSum;
        }

        public void reset () {
          mSum = 0;
        }
      };

    int count = 0;

    while (it.tryAdvance (act)) {
      ++count;
    }

    assertEquals (10, count);
    try {
      assertEquals (45, act.getClass().getMethod("sum").invoke (act));

      act.getClass().getMethod("reset").invoke (act);
    } catch (Exception e) { fail ("should never happen"); }

    it = a.spliterator ();
    assertEquals (10, it.estimateSize ());

    Spliterator<Integer> split = it.trySplit (), split2 = split.trySplit ();

    assertEquals (5, it.estimateSize ());
    assertEquals (3, split.estimateSize ());
    assertEquals (2, split2.estimateSize ());

    count = 0;

    while (it.tryAdvance (act)) {
      ++count;
    }

    while (split.tryAdvance (act)) {
      ++count;
    }

    while (split2.tryAdvance (act)) {
      ++count;
    }

    assertEquals (10, count);
    try {
      assertEquals (45, act.getClass().getMethod("sum").invoke (act));
      act.getClass().getMethod("reset").invoke (act);
    } catch (Exception e) { fail ("should never happen"); }

    it = a.spliterator ();
    assertNotNull (split = it.trySplit ());
    assertNotNull (it.trySplit ());
    assertNotNull (it.trySplit ());
    assertNotNull (it.trySplit ());
    assertNull (it.trySplit ());
    assertNotNull (split.trySplit ());
    assertNotNull (split.trySplit ());
    assertNotNull (split.trySplit ());
    assertNull (split.trySplit ());

    ArrayList<Spliterator<Integer>> splits = new ArrayList<Spliterator<Integer>> ();
    splits.add (a.spliterator ());

    ListIterator<Spliterator<Integer>> splits_it = splits.listIterator ();

    while (splits_it.hasNext ()) {
      Spliterator<Integer> item = splits_it.next (), n = item.trySplit ();

      while (n != null) {
        splits_it.add (n);
        splits_it.previous ();
        n = item.trySplit ();
      }
    }

    splits_it = splits.listIterator ();

    while (splits_it.hasNext ()) {
      System.out.println (splits_it.next ().estimateSize ());
    }
    assertEquals (10, splits.size ());

    splits_it = splits.listIterator ();
    count = 0;

    while (splits_it.hasNext ()) {
      Spliterator<Integer> item = splits_it.next ();

      assertTrue (item.tryAdvance (act));
      assertFalse (item.tryAdvance (act));
      ++count;
    }

    assertEquals (10, count);
    try {
      assertEquals (45, act.getClass().getMethod("sum").invoke (act));
      act.getClass().getMethod("reset").invoke (act);
    } catch (Exception e) { fail ("should never happen"); }

  }
}
