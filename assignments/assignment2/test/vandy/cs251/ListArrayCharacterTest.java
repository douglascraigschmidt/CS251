package vandy.cs251;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.ListIterator;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Test the ListArray<Character> class.
 */
public class ListArrayCharacterTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_Constructor() {
        ListArray<Character> tmp = new ListArray<Character>(10);
        assertEquals(10, tmp.size());

        tmp = new ListArray<Character>(15);
        assertEquals(15, tmp.size ());

        tmp = new ListArray<Character>(0);
        assertEquals(0, tmp.size ());

        exception.expect(NegativeArraySizeException.class);
        tmp = new ListArray<Character>(-1);
    }

    @Test
    public void test_DefaultValueConstructor() {
        ListArray<Character> tmp = new ListArray<Character>(10, 'b');

        for(int i = 0; i < 10; ++i) {
          assertEquals('b', (char)tmp.get(i));
        }
    }

    @Test
    public void test_CopyConstructor() {
        ListArray<Character> tmp = new ListArray<Character>(10, 'b');

        ListArray<Character> tmp_c = new ListArray<Character>(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = new ListArray<Character>(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp_c.resize(11);

        assertEquals('b', (char) tmp_c.get(10));

        tmp_c.resize(2);

        tmp = new ListArray<Character>(tmp_c);
        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
    }

    @Test
    public void test_SetGet() {
        ListArray<Character> tmp = new ListArray<Character>(1);
        assertEquals(null, tmp.get(0));

        tmp.set(0, 'a');
        assertEquals('a', (char) tmp.get(0));

        tmp.set(0, 'b');
        assertEquals('b', (char)tmp.get(0));
    }

    @Test
    public void test_SetExceptionPositive() {
        ListArray<Character> tmp = new ListArray<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionPositive() {
        ListArray<Character> tmp = new ListArray<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_GetExceptionNegative() {
        ListArray<Character> tmp = new ListArray<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(-1);
    }

    @Test
    public void test_SetExceptionNegative() {
        ListArray<Character> tmp = new ListArray<Character>(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(-1, 'a');
    }

    @Test
    public void test_SetExceptionResized() {
        ListArray<Character> tmp = new ListArray<Character>(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionResized() {
        ListArray<Character> tmp = new ListArray<Character>(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_Resize() {
        ListArray<Character> tmp = new ListArray<Character>(0);
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

        tmp = new ListArray<Character>(3, 'a');
        assertEquals(3, tmp.size());
        tmp.set (1, 'b');
        tmp.set (2, 'c');
        tmp.resize (0);
        assertEquals(0, tmp.size());
        tmp.resize (1);
        assertEquals(1, tmp.size());
        assertEquals('a', (char) tmp.get(0));
        tmp.resize (2);
        assertEquals(2, tmp.size());
        assertEquals('a', (char) tmp.get(0));
        assertEquals('a', (char) tmp.get(1));
        tmp.resize (3);
        assertEquals(3, tmp.size());
        assertEquals('a', (char) tmp.get(0));
        assertEquals('a', (char) tmp.get(1));
        assertEquals('a', (char) tmp.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_CompareTo () {
        ListArray<Character> a = new ListArray<Character>(0, 'a');
        ListArray<Character> b = new ListArray<Character>(0, 'b');

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
            ListArray<Character> bba = new ListArray<Character>(b);


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

        a = new ListArray<Character>(1, 'a');
        b = new ListArray<Character>(2, 'a');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        b = new ListArray<Character>(2, 'a');
        b.set (1, 'b');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.resize (3);
        a.set (1, 'b');
        a.set (2, 'c');


        b = new ListArray<Character> (a);

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
        ListArray<Character> a = new ListArray<Character>(0, 'a');

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
    ListArray<Character> a = new ListArray<Character>(2, 'a');
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
    ListArray<Character> a = new ListArray<Character>(2, 'a');
    Iterator<Character> it = a.iterator ();
    exception.expect(IllegalStateException.class);
    it.remove ();
  }
}
