package vandy.cs251;

import vandy.cs251.CharArray;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Test the CharArray class.
 */
public class CharArrayTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_Constructor() {
        CharArray tmp = new CharArray(10);
        assertEquals(10, tmp.size());

        tmp = new CharArray(15);
        assertEquals(15, tmp.size ());

        tmp = new CharArray(0);
        assertEquals(0, tmp.size ());

        exception.expect(NegativeArraySizeException.class);
        tmp = new CharArray(-1);
    }

    @Test
    public void test_DefaultValueConstructor() {
        CharArray tmp = new CharArray(10, 'b');

        for(int i = 0; i < 10; ++i) {
            assertEquals('b', tmp.get(i));
        }
    }

    @Test
    public void test_CopyConstructor() {
        CharArray tmp = new CharArray(10, 'b');

        CharArray tmp_c = new CharArray(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = new CharArray(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertFalse(tmp.capacity() == tmp_c.capacity());

        tmp_c.resize (11);

        assertEquals('b', tmp_c.get(10));

        tmp_c.resize (2);

        tmp = new CharArray(tmp_c);
        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertEquals(2, tmp.capacity());
    }

    @Test
    public void test_Clone() {
        CharArray tmp = new CharArray(10, 'b');

        CharArray tmp_c = (CharArray) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = (CharArray) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
        assertFalse(tmp.capacity() == tmp_c.capacity());
    }


    @Test
    public void test_SetGet() {
        CharArray tmp = new CharArray(1);
        assertEquals('\u0000', tmp.get(0));

        tmp.set(0, 'a');
        assertEquals('a', tmp.get(0));

        tmp.set(0, 'b');
        assertEquals('b', tmp.get(0));
    }

    @Test
    public void test_SetExceptionPositive() {
        CharArray tmp = new CharArray(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionPositive() {
        CharArray tmp = new CharArray(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_GetExceptionNegative() {
        CharArray tmp = new CharArray(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(-1);
    }

    @Test
    public void test_SetExceptionNegative() {
        CharArray tmp = new CharArray(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(-1, 'a');
    }

    @Test
    public void test_SetExceptionResized() {
        CharArray tmp = new CharArray(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionResized() {
        CharArray tmp = new CharArray(10);
        tmp.resize (1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_Resize() {
        CharArray tmp = new CharArray(0);
        assertEquals(0, tmp.size());

        tmp.resize(0);
        assertEquals(0, tmp.size());

        tmp.resize(1);
        assertEquals(1, tmp.size());
        assertEquals('\u0000', tmp.get(0));
        tmp.set(0, 'a');

        tmp.resize(1);
        assertEquals(1, tmp.size());
        assertEquals('a', tmp.get(0));

        tmp.resize(2);
        assertEquals(2, tmp.size());
        assertEquals('a', tmp.get(0));
        assertEquals('\u0000', tmp.get(1));

        tmp = new CharArray (3, 'a');
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
        assertEquals('a', tmp.get(0));
        tmp.resize (2);
        assertEquals(2, tmp.size());
        assertEquals(3, tmp.capacity());
        assertEquals('a', tmp.get(0));
        assertEquals('a', tmp.get(1));
        tmp.resize (3);
        assertEquals(3, tmp.size());
        assertEquals(3, tmp.capacity());
        assertEquals('a', tmp.get(0));
        assertEquals('a', tmp.get(1));
        assertEquals('a', tmp.get(2));
    }

    @Test
    public void test_CompareTo () {
        CharArray a = new CharArray (0, 'a');
        CharArray b = new CharArray (0, 'b');

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

        CharArray bba = (CharArray) b.clone ();

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

        a = new CharArray (1, 'a');
        b = new CharArray (2, 'a');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        b = new CharArray (2, 'a');
        b.set (1, 'b');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.resize (3);
        a.set (1, 'b');
        a.set (2, 'c');

        b = (CharArray) a.clone ();
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
        CharArray a = new CharArray (0, 'a');

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
}
