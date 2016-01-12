package vandy.cs251;

import vandy.cs251.CharList;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Test the CharList class.
 */
public class CharListTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_Constructor() {
        CharList tmp = new CharList(10);
        assertEquals(10, tmp.size());

        tmp = new CharList(15);
        assertEquals(15, tmp.size ());

        tmp = new CharList(0);
        assertEquals(0, tmp.size ());

        exception.expect(IndexOutOfBoundsException.class);
        tmp = new CharList(-1);
    }

    @Test
    public void test_DefaultValueConstructor() {
        CharList tmp = new CharList(10, 'b');

        for(int i = 0; i < 10; ++i) {
            assertEquals('b', tmp.get(i));
        }
    }

    @Test
    public void test_CopyConstructor() {
        CharList tmp = new CharList(10, 'b');

        CharList tmp_c = new CharList(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = new CharList(tmp);

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp_c.resize (11);

        assertEquals('b', tmp_c.get(10));

        tmp_c.resize (2);

        tmp = new CharList(tmp_c);
        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
    }

    @Test
    public void test_Clone() {
        CharList tmp = new CharList(10, 'b');

        CharList tmp_c = (CharList) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));

        tmp.resize(5);

        tmp_c = (CharList) tmp.clone ();

        assertEquals(tmp.size(), tmp_c.size());
        assertEquals(0, tmp.compareTo(tmp_c));
        assertEquals(0, tmp_c.compareTo(tmp));
    }


    @Test
    public void test_SetGet() {
        CharList tmp = new CharList(1);
        assertEquals('\u0000', tmp.get(0));

        tmp.set(0, 'a');
        assertEquals('a', tmp.get(0));

        tmp.set(0, 'b');
        assertEquals('b', tmp.get(0));
    }

    @Test
    public void test_SetExceptionPositive() {
        CharList tmp = new CharList(1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionPositive() {
        CharList tmp = new CharList(1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_GetExceptionNegative() {
        CharList tmp = new CharList(1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.get(-1);
    }

    @Test
    public void test_SetExceptionNegative() {
        CharList tmp = new CharList(1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.set(-1, 'a');
    }

    @Test
    public void test_SetExceptionResized() {
        CharList tmp = new CharList(10);
        tmp.resize (1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.set(1, 'a');
    }

    @Test
    public void test_GetExceptionResized() {
        CharList tmp = new CharList(10);
        tmp.resize (1);
        exception.expect(IndexOutOfBoundsException.class);
        tmp.get(1);
    }

    @Test
    public void test_Resize() {
        CharList tmp = new CharList(0);
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

        tmp = new CharList (3, 'a');
        assertEquals(3, tmp.size());
        tmp.set (1, 'b');
        tmp.set (2, 'c');
        tmp.resize (0);
        assertEquals(0, tmp.size());
        tmp.resize (1);
        assertEquals(1, tmp.size());
        assertEquals('a', tmp.get(0));
        tmp.resize (2);
        assertEquals(2, tmp.size());
        assertEquals('a', tmp.get(0));
        assertEquals('a', tmp.get(1));
        tmp.resize (3);
        assertEquals(3, tmp.size());
        assertEquals('a', tmp.get(0));
        assertEquals('a', tmp.get(1));
        assertEquals('a', tmp.get(2));
    }

    @Test
    public void test_CompareTo () {
        CharList a = new CharList (0, 'a');
        CharList b = new CharList (0, 'b');

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

        CharList bba = (CharList) b.clone ();

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

        a = new CharList (1, 'a');
        b = new CharList (2, 'a');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        b = new CharList (2, 'a');
        b.set (1, 'b');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.resize (3);
        a.set (1, 'b');
        a.set (2, 'c');

        b = (CharList) a.clone ();
        b.resize (4);
        b.set (3, 'd');

        assertTrue (a.compareTo (b) < 0);
        assertTrue (b.compareTo (a) > 0);

        a.set (1, 'z');

        assertTrue (a.compareTo (b) > 0);
        assertTrue (b.compareTo (a) < 0);
    }
}
