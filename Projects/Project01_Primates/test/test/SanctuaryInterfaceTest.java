package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.Monkey;
import sanctuary.MonkeyFavFood;
import sanctuary.MonkeyMedicalStatus;
import sanctuary.Sanctuary;
import sanctuary.SanctuaryInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Sanctuary class represented as the number of isolations, number of enclosures and
 * array list of enclosures and isolations.
 */
public class SanctuaryInterfaceTest {
  private Sanctuary primateSanctuary;

  @Before
  public void setUp() throws Exception {
    primateSanctuary = new Sanctuary(3,2);
    primateSanctuary.createSanctuary();

    int randomArea = 20;
    for (int i = 0; i < 2; i++) {
      primateSanctuary.getEnclosures().get(i).setSpecies("sp" + (i + 1));
      primateSanctuary.getEnclosures().get(i).setArea(randomArea);
      randomArea = randomArea + 20;
    }
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * SanctuaryInterface object.
   *
   * @param numberOfIsolationRooms integer
   * @param numberOfEnclosures integer
   *
   * @return a new instance of a WeatherReading object
   */
  protected SanctuaryInterface sanctuary(int numberOfIsolationRooms, int numberOfEnclosures) {
    return new Sanctuary(numberOfIsolationRooms, numberOfEnclosures);
  }

  @Test
  public void testGetIsolationsCount() {
    assertEquals(3, primateSanctuary.getIsolationsCount());
  }

  @Test
  public void testGetEnclosuresCount() {
    assertEquals(2, primateSanctuary.getEnclosuresCount());
  }

  @Test
  public void testAddToIsolation() {
    Monkey monkey1 = new Monkey();
    monkey1.setMonkey("Connie", "spider", 'f', 15, 23, 3, MonkeyFavFood.FRUITS,
            MonkeyMedicalStatus.READY);
    Monkey monkey2 = new Monkey();
    monkey2.setMonkey("Booger", "tamarin", 'm', 5, 12, 1, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);
    Monkey monkey3 = new Monkey();
    monkey3.setMonkey("Quinn", "saki", 'm', 25, 45, 6, MonkeyFavFood.NUTS,
            MonkeyMedicalStatus.TREATMENT);
    Monkey monkey4 = new Monkey();
    monkey4.setMonkey("Cuddle", "fff", 'f', 23, 45, 9, MonkeyFavFood.LEAVES,
            MonkeyMedicalStatus.READY);

    boolean added = primateSanctuary.addToIsolation(monkey1);
    assertTrue(added);
    assertTrue(monkey1.equals(primateSanctuary.getIsolations().get(0).getMonkey()));

    added = primateSanctuary.addToIsolation(monkey2);
    assertTrue(added);
    assertTrue(monkey2.equals(primateSanctuary.getIsolations().get(1).getMonkey()));

    added = primateSanctuary.addToIsolation(monkey3);
    assertTrue(added);
    assertTrue(monkey3.equals(primateSanctuary.getIsolations().get(2).getMonkey()));

    added = primateSanctuary.addToIsolation(monkey4);
    assertFalse(added);
  }

  @Test
  public void testRemoveFromIsolation() {
    Monkey monkey1 = new Monkey();
    monkey1.setMonkey("Connie", "spider", 'f', 15, 23, 3, MonkeyFavFood.FRUITS,
            MonkeyMedicalStatus.READY);
    Monkey monkey2 = new Monkey();
    monkey2.setMonkey("Booger", "tamarin", 'm', 5, 12, 1, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);

    boolean added = primateSanctuary.addToIsolation(monkey1);
    assertTrue(added);
    assertTrue(monkey1.equals(primateSanctuary.getIsolations().get(0).getMonkey()));

    added = primateSanctuary.addToIsolation(monkey2);
    assertTrue(added);
    assertTrue(monkey2.equals(primateSanctuary.getIsolations().get(1).getMonkey()));

    boolean removed = primateSanctuary.removeFromIsolation(monkey1);
    assertTrue(removed);
  }

  @Test
  public void testAddToEnclosure() {
    Monkey monkey1 = new Monkey();
    monkey1.setMonkey("Connie", "spider", 'f', 15, 23, 3, MonkeyFavFood.FRUITS,
            MonkeyMedicalStatus.READY);
    Monkey monkey2 = new Monkey();
    monkey2.setMonkey("Booger", "tamarin", 'm', 5, 12, 1, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);
    Monkey monkey3 = new Monkey();
    monkey3.setMonkey("Quinn", "saki", 'm', 25, 45, 6, MonkeyFavFood.NUTS,
            MonkeyMedicalStatus.TREATMENT);
    Monkey monkey4 = new Monkey();
    monkey4.setMonkey("Cuddle", "spider", 'f', 23, 45, 9, MonkeyFavFood.LEAVES,
            MonkeyMedicalStatus.READY);
    Monkey monkey5 = new Monkey();
    monkey5.setMonkey("Bonnie", "squirrel", 'f', 35, 23, 3, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);

    boolean addedToIsolation = primateSanctuary.addToIsolation(monkey1);
    assertTrue(addedToIsolation);
    boolean addedToEnclosure = primateSanctuary.addToEnclosure(monkey1);
    assertTrue(addedToEnclosure);

    addedToEnclosure = primateSanctuary.addToEnclosure(monkey2);
    assertFalse(addedToEnclosure);

    addedToIsolation = primateSanctuary.addToIsolation(monkey3);
    assertTrue(addedToIsolation);
    addedToEnclosure = primateSanctuary.addToEnclosure(monkey3);
    assertFalse(addedToEnclosure);

    addedToIsolation = primateSanctuary.addToIsolation(monkey4);
    assertTrue(addedToIsolation);
    addedToEnclosure = primateSanctuary.addToEnclosure(monkey4);
    assertTrue(addedToEnclosure);
  }
}