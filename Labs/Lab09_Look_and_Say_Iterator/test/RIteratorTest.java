import org.junit.Test;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * RIteratorTest has the unit test cases to show that the implemented functions
 * are working as expected.
 */
public class RIteratorTest {

  // two argument constructor
  // checks if IllegalArgumentException is thrown if negative seed value is passed
  @Test(expected = IllegalArgumentException.class)
  public void negativeSeedValueToTwoArgsConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("-1"), new BigInteger("1121"));
  }

  // two argument constructor
  // checks if IllegalArgumentException is thrown if the seed in larger than the end value
  @Test(expected = IllegalArgumentException.class)
  public void invalidSeedValueToTwoArgsConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("1121"), new BigInteger("11"));
  }

  // single argument constructor
  // checks if IllegalArgumentException is thrown if negative seed value is passed
  @Test(expected = IllegalArgumentException.class)
  public void negativeSeedValueToSingleArgsConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("-1"));
  }

  // single argument constructor
  // checks if IllegalArgumentException is thrown if the seed in larger than the end value
  @Test(expected = IllegalArgumentException.class)
  public void invalidSeedValueToSingleArgsConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("9".repeat(101)));

  }

  // checks if the next() function is returning the correct value with every call
  @Test
  public void testNext() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("21"), new BigInteger("112111"));
    assertEquals(new BigInteger("21"), iterator.next());
    assertEquals(new BigInteger("1211"), iterator.next());
    assertEquals(new BigInteger("111221"), iterator.next());
  }

  /*
   check if  the next() function throws a NoSuchElementException when
   no next value exists in the sequence
   (i.e. when the next value in sequence exceeds the end value specified)
   */
  @Test(expected = NoSuchElementException.class)
  public void testNextWhenNoNextValue() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("21"), new BigInteger("110000"));
    // returns "21" which is less than end value
    iterator.next();
    // returns "1211" which is less than end value
    iterator.next();
    /*
     returns "111221" which is greater than the given end value so NoSuchElementException
     will be thrown
     */
    iterator.next();
  }

  // checks if the prev() function is returning the correct value with every call
  @Test
  public void testPrev() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("111221"), new BigInteger("11234411"));
    assertEquals(new BigInteger("1211"), iterator.prev());
    assertEquals(new BigInteger("21"), iterator.prev());
    assertEquals(new BigInteger("11"), iterator.prev());
    assertEquals(new BigInteger("1"), iterator.prev());
  }

  /*
   check if  the prev() function throws a NoSuchElementException when no previous value
   exists in the sequence
   */
  @Test(expected = NoSuchElementException.class)
  public void testPrevWhenNoPrevValue() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("111221"), new BigInteger("11234411"));
    iterator.prev(); // returns "1221"
    iterator.prev(); // returns "21"
    iterator.prev(); //returns "11"
    iterator.prev(); // returns "1"
    iterator.prev(); // will throw an exception as previous does not exit
  }

  // check if hasNext() works properly when next value exists
  @Test
  public void hasNextPositive() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("21"), new BigInteger("110000"));
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
  }

  // check if hasNext() works properly when next value does not exist
  @Test
  public void hasNextNegative() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("21"), new BigInteger("110000"));
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertFalse(iterator.hasNext());
  }

  // check if hasPrevious() works properly when previous value exists
  @Test
  public void hasPreviousPositive() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("111221"), new BigInteger("11234411"));
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
  }

  // check if hasPrevious() works properly if no previous value exists
  @Test
  public void hasPreviousNegative() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("111221"), new BigInteger("11234411"));
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertTrue(iterator.hasPrevious());
    iterator.prev();
    assertFalse(iterator.hasPrevious());
  }
}