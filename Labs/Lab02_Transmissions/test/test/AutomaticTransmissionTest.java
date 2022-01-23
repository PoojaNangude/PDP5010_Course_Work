package test;

import org.junit.Before;
import org.junit.Test;

import transmission.AutomaticTransmission;
import transmission.Transmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Transmission represented as current speed of car and 5 speed
 * threshold for 6 different gears.
 */
public class AutomaticTransmissionTest {
  private Transmission car1;

  /**
   * Setting up some test data which will be used to test the implementations of
   * the interface methods and other methods in AutomaticTransmission.
   */
  @Before
  public void setUp() {
    car1 = transmission(10,20,30,40,50);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * Transmission object.
   *
   * @param v1 in miles per hour
   * @param v2 in miles per hour
   * @param v3 in miles per hour
   * @param v4 in miles per hour
   * @param v5 in miles per hour
   * @return a new instance of a Transmission object
   */
  protected Transmission transmission(int v1, int v2, int v3, int v4, int v5) {
    return new AutomaticTransmission(v1, v2, v3, v4, v5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidGearChangePoint() {
    transmission(10,200,30,40,50);
  }

  @Test
  public void testGetSpeed() {
    assertEquals(0,car1.getSpeed());
  }

  @Test
  public void testGetGear() {
    assertEquals(0, car1.getGear());
  }

  @Test
  public void testToString() {
    assertEquals("Transmission (speed = 0, gear = 0)",car1.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(car1.equals(car1));
    assertTrue(car1.equals(new AutomaticTransmission(10,20,30,40,50)));
  }

  @Test
  public void testHashCode() {
    assertEquals(Integer.hashCode(10 + 20 + 30 + 40 + 50),
            transmission(10,20,30,40,50).hashCode());
  }

  @Test
  public void testIncreaseSpeed() {
    assertEquals("Transmission (speed = 0, gear = 0)",car1.toString());
    car1.increaseSpeed();
    assertEquals("Transmission (speed = 1, gear = 1)",car1.toString());
    for (int i = 0 ; i < 10 ; i++) {
      car1.increaseSpeed();
    }
    assertEquals("Transmission (speed = 11, gear = 2)",car1.toString());
  }

  @Test
  public void testDecreaseSpeed() {
    assertEquals("Transmission (speed = 0, gear = 0)",car1.toString());
    for (int i = 0 ; i < 11 ; i++) {
      car1.increaseSpeed();
    }
    assertEquals("Transmission (speed = 11, gear = 2)",car1.toString());
    car1.decreaseSpeed();
    assertEquals("Transmission (speed = 10, gear = 2)",car1.toString());
  }
}