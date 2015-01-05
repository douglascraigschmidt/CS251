package edu.vanderbilt.eecs.cs251;

import edu.vanderbilt.eecs.cs251.CharArray;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @class CharArrayTest
 *
 * @brief ...
 */
public class CharArrayTest {
  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void test_Constructor () {
    CharArray tmp = new CharArray (10);
    assertEquals (10, tmp.size ());

    tmp = new CharArray (15);
    assertEquals (15, tmp.size ());

    tmp = new CharArray (0);
    assertEquals (0, tmp.size ());

    exception.expect (NegativeArraySizeException.class);
    tmp = new CharArray (-1);
  }

  @Test
  public void test_DefaultValueConstructor() {
    CharArray tmp = new CharArray (10, 'b');

    for (int i = 0; i < 10; ++i) {
      assertEquals ('b', tmp.get (i));
    }
  }

  @Test
  public void test_CopyConstructor () {
    CharArray tmp = new CharArray (10, 'b');

    CharArray tmp_c = new CharArray (tmp);

    assertEquals (tmp.size (), tmp_c.size ());
    assertEquals (0, tmp.compareTo (tmp_c));
    assertEquals (0, tmp_c.compareTo (tmp));

    tmp.resize (5);

    tmp_c = new CharArray (tmp);

    assertEquals (tmp.size (), tmp_c.size ());
    assertEquals (0, tmp.compareTo (tmp_c));
    assertEquals (0, tmp_c.compareTo (tmp));
    assertFalse (tmp.capacity () == tmp_c.capacity ());
  }


  @Test
  public void test_SetGet () {
    CharArray tmp = new CharArray(1);
    assertEquals ('\u0000', tmp.get (0));

    tmp.set (0, 'a');
    assertEquals ('a', tmp.get (0));

    tmp.set (0, 'b');
    assertEquals ('b', tmp.get (0));
  }

  @Test
  public void test_SetExceptionPositive () {
    CharArray tmp = new CharArray(1);
    exception.expect (ArrayIndexOutOfBoundsException.class);
    tmp.set (1, 'a');
  }

  @Test
  public void test_GetExceptionPositive () {
    CharArray tmp = new CharArray(1);
    exception.expect (ArrayIndexOutOfBoundsException.class);
    tmp.get (1);
  }

  @Test
  public void test_GetExceptionNegative () {
    CharArray tmp = new CharArray(1);
    exception.expect (ArrayIndexOutOfBoundsException.class);
    tmp.get (-1);
  }

  @Test
  public void test_SetExceptionNegative () {
    CharArray tmp = new CharArray(1);
    exception.expect (ArrayIndexOutOfBoundsException.class);
    tmp.set (-1, 'a');
  }

  @Test
  public void test_Resize () {
    CharArray tmp = new CharArray(0);
    assertEquals (0, tmp.size ());

    tmp.resize (0);
    assertEquals (0, tmp.size ());

    tmp.resize (1);
    assertEquals (1, tmp.size ());
    assertEquals ('\u0000', tmp.get (0));
    tmp.set (0, 'a');

    tmp.resize (1);
    assertEquals (1, tmp.size ());
    assertEquals ('a', tmp.get (0));

    tmp.resize (2);
    assertEquals (2, tmp.size ());
    assertEquals ('a', tmp.get (0));
    assertEquals ('\u0000', tmp.get (1));
  }
}
