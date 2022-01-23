package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.MonkeyLocationByName;

import static org.junit.Assert.assertEquals;

/**
 * MonkeyLocationByName represented as name of the monkey and its location in the sanctuary.
 */
public class MonkeyLocationByNameTest {
  MonkeyLocationByName monkeyLocation;

  @Before
  public void setUp() throws Exception {
    monkeyLocation = monLoc("Gabbie", "ISOLATION ID 2");
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * MonkeyLocationByName object.
   *
   * @param monkeyName in String
   * @param locationOfMonkey in String
   *
   * @return a new instance of a MonkeyLocationByName object
   */
  protected MonkeyLocationByName monLoc(String monkeyName, String locationOfMonkey) {
    return new MonkeyLocationByName(monkeyName, locationOfMonkey);
  }

  @Test
  public void testGetMonkeyName() {
    assertEquals("Gabbie", monkeyLocation.getMonkeyName());
  }

  @Test
  public void getLocationOfMonkey() {
    assertEquals("ISOLATION ID 2", monkeyLocation.getLocationOfMonkey());
  }
}