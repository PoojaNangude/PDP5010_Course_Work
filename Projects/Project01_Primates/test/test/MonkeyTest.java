package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.Monkey;
import sanctuary.MonkeyFavFood;
import sanctuary.MonkeyMedicalStatus;
import sanctuary.MonkeySize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Monkey class represented uniqueId, species, sex, size, weight, age, favourite food
 * and medical status.
 */
public class MonkeyTest {
  private Monkey monkey1;
  private Monkey monkey2;
  private Monkey monkey3;

  @Before
  public void setUp() throws Exception {
    monkey1 = mon();
    monkey1.setMonkey("Giggles", "drill",'m', 7,5,2, MonkeyFavFood.EGGS, MonkeyMedicalStatus.READY);

    monkey2 = mon();
    monkey2.setMonkey("Bradd", "howler",'f', 15,5,2, MonkeyFavFood.NUTS, MonkeyMedicalStatus.READY);

    monkey3 = mon();
    monkey3.setMonkey("Pitt", "saki",'f', 35,5,2, MonkeyFavFood.INSECTS, MonkeyMedicalStatus.READY);
  }

  protected Monkey mon() {
    return new Monkey();
  }

  @Test
  public void testGetName() {
    assertEquals("Giggles", monkey1.getName());
  }

  @Test
  public void testGetSpecies() {
    assertEquals("drill", monkey1.getSpecies());
  }

  @Test
  public void testGetSex() {
    assertEquals('m', monkey1.getSex());
  }

  @Test
  public void testGetSize() {
    assertEquals(7, monkey1.getSize());
  }

  @Test
  public void testGetWeight() {
    assertEquals(5, monkey1.getWeight());
  }

  @Test
  public void testGetAge() {
    assertEquals(2, monkey1.getAge());
  }

  @Test
  public void testGetMonkeyFavFood() {
    assertEquals(MonkeyFavFood.EGGS, monkey1.getMonkeyFavFood());
  }

  @Test
  public void testGetMedicalStatus() {
    assertEquals(MonkeyMedicalStatus.READY, monkey1.getMedicalStatus());
  }

  @Test
  public void testGetMonkeySize() {
    assertEquals(MonkeySize.SMALL, monkey1.getMonkeySize());
    assertEquals(MonkeySize.MEDIUM, monkey2.getMonkeySize());
    assertEquals(MonkeySize.LARGE, monkey3.getMonkeySize());
  }

  @Test
  public void testGetFoodQuantityRequired() {
    assertEquals(100, monkey1.getFoodQuantityRequired());
    assertEquals(250, monkey2.getFoodQuantityRequired());
    assertEquals(500, monkey3.getFoodQuantityRequired());
  }

  @Test
  public void testAreaRequiredByMonkey() {
    assertEquals(1, monkey1.areaRequiredByMonkey(monkey1.getMonkeySize()));
    assertEquals(5, monkey2.areaRequiredByMonkey(monkey2.getMonkeySize()));
    assertEquals(10,monkey3.areaRequiredByMonkey(monkey3.getMonkeySize()));
  }

  @Test
  public void testEquals() {
    assertTrue(monkey1.equals(monkey1));
    assertFalse(monkey1.equals(monkey3));
  }

  @Test
  public void testHashCode() {
    assertEquals(monkey1.hashCode(), monkey1.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("GigglesdrillmEGGS", monkey1.toString());
  }
}