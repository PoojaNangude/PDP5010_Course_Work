package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.Enclosure;

import static org.junit.Assert.assertEquals;

/**
 * Enclosure represented as enclosure id, species name, enclosure area, consumed area,
 * animal count and an arraylist of monkey storing the monkeys housed in the sanctuary.
 */
public class EnclosureTest {
  private Enclosure enclosure1;
  private Enclosure enclosure2;

  @Before
  public void setUp() throws Exception {
    enclosure1 = encl();
    enclosure1.setSpecies("howler");
    enclosure1.setArea(300);

    enclosure2 = encl();
    enclosure2.setSpecies("tamarin");
    enclosure2.setArea(200);
  }

  protected Enclosure encl() {
    return new Enclosure();
  }

  @Test
  public void testGetSpecies() {
    assertEquals("howler", enclosure1.getSpecies());
    assertEquals("tamarin", enclosure2.getSpecies());
  }

  @Test
  public void testGetArea() {
    assertEquals(300, enclosure1.getArea());
    assertEquals(200,enclosure2.getArea());
  }

  @Test
  public void testGetConsumedArea() {
    assertEquals(0, enclosure1.getConsumedArea());
  }

  @Test
  public void testGetAnimalCount() {
    assertEquals(0,enclosure1.getAnimalCount());
  }

  @Test
  public void testGetEnclosureList() {
    assertEquals(0,enclosure1.getEnclosureList().size());
  }
}