package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import sanctuary.EnclosureSign;
import sanctuary.MonkeyFavFood;

import static org.junit.Assert.assertSame;

/**
 * EnclosureSign represented as monkey name, sex and favourite food.
 */
public class EnclosureSignTest {
  private EnclosureSign enclosureMonkeySign;

  @Before
  public void setUp() throws Exception {
    enclosureMonkeySign = ems("Garry",'m',MonkeyFavFood.FRUITS);
  }

  protected EnclosureSign ems(String name, char sex, MonkeyFavFood monkeyFavFood) {
    return new EnclosureSign(name, sex, monkeyFavFood);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName_Null() {
    ems(null, 'm',MonkeyFavFood.NUTS );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName_EmptyString() {
    ems("", 'f',MonkeyFavFood.TREE_SAP );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName_WhiteSpace() {
    ems("     ", 'f',MonkeyFavFood.INSECTS );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSexValue() {
    ems("Bonnie", 'k',MonkeyFavFood.EGGS );
  }

  @Test
  public void testGetName() {
    assertEquals("Garry", enclosureMonkeySign.getName() );
  }

  @Test
  public void tesGetSex() {
    assertEquals('m', enclosureMonkeySign.getSex());
  }

  @Test
  public void testGetMonkeyFavFood() {
    assertSame(MonkeyFavFood.FRUITS, enclosureMonkeySign.getMonkeyFavFood());
  }
}