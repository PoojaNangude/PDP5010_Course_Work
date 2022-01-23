package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import sanctuary.SpeciesHousingLocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * SpeciesHousingLocation represented as species name and two integer array list which store the
 * isolation ids and enclosure ids where the species is housed.
 */
public class SpeciesHousingLocationTest {
  SpeciesHousingLocation speciesLocation;

  @Before
  public void setUp() throws Exception {
    ArrayList<Integer> isolationIds = new ArrayList<>();
    isolationIds.add(1);
    isolationIds.add(2);

    ArrayList<Integer> enclosureIds = new ArrayList<>();
    enclosureIds.add(1);
    enclosureIds.add(2);

    speciesLocation = location("saki", isolationIds, enclosureIds);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * SpeciesHousingLocation object.
   *
   * @param name species name in String
   * @param isolationIds ArrayList of integers having the isolations where species is housed.
   * @param enclosureIds ArrayList of integers having the enclosures where species is housed
   *
   * @return a new instance of a SpeciesHousingLocation object
   */
  protected SpeciesHousingLocation location(String name, ArrayList<Integer> isolationIds,
                                            ArrayList<Integer> enclosureIds) {
    return new SpeciesHousingLocation(name, isolationIds, enclosureIds);
  }

  @Test
  public void testGetName() {
    assertEquals("saki", speciesLocation.getName());
  }

  @Test
  public void testGetIsolationIds() {
    assertSame(1, speciesLocation.getIsolationIds().get(0));
  }

  @Test
  public void testGetEnclosureIds() {
    assertSame(2, speciesLocation.getEnclosureIds().get(1));
  }
}