package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import gear.Gear;
import gear.HeadGear;
import gear.Potions;
import gear.Belts;
import gear.Footware;
import gear.GearCatalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests for various kinds of Gears.
 */
public class GearTest {
  private Gear headGear1;
  private Gear headGear2;
  private Gear headGear3;

  private Gear potion1;
  private Gear potion2;

  private Gear footWare1;
  private Gear footWare2;

  private Gear belt1;
  private Gear belt2;
  private Gear belt3;
  private Gear belt4;
  private Gear belt5;
  private Gear belt6;

  private ArrayList<Gear> gearList;
  private ArrayList<Gear> sortedGearList;

  @Before
  public void setUp() throws Exception {
    headGear1 = new HeadGear();
    headGear2 = new HeadGear();

    potion1 = new Potions();
    potion2 = new Potions();

    footWare1 = new Footware();
    footWare2 = new Footware();

    belt1 = new Belts(Belts.size.SMALL);
    belt2 = new Belts(Belts.size.MEDIUM);
    belt3 = new Belts(Belts.size.LARGE);
    belt4 = new Belts(Belts.size.SMALL);
    belt5 = new Belts(Belts.size.MEDIUM);
    belt6 = new Belts(Belts.size.LARGE);

    gearList = new ArrayList<Gear>();
    sortedGearList = new ArrayList<Gear>();

    gearList.add(footWare1);
    gearList.add(belt1);
    gearList.add(potion1);
    gearList.add(headGear1);
    gearList.add(belt3);
    gearList.add(headGear2);
    gearList.add(potion2);
    gearList.add(belt2);
    gearList.add(footWare2);
    gearList.add(belt4);

    sortedGearList.add(headGear1);
    sortedGearList.add(headGear2);
    sortedGearList.add(potion1);
    sortedGearList.add(potion2);
    sortedGearList.add(belt1);
    sortedGearList.add(belt4);
    sortedGearList.add(belt2);
    sortedGearList.add(belt3);
    sortedGearList.add(footWare1);
    sortedGearList.add(footWare2);
  }

  @Test
  public void testGearObjectCreation() {

    assertEquals(new HeadGear(), headGear1);
    assertEquals(new Potions(), potion1);
    assertEquals(new Footware(), footWare1);
    assertEquals(new Belts(Belts.size.SMALL), belt1);
    assertEquals(new Belts(Belts.size.MEDIUM), belt2);
    assertEquals(new Belts(Belts.size.LARGE), belt3);

    assertEquals(GearCatalog.HEAD_GEAR,headGear1.getGear());
    assertEquals(2,headGear1.effectOnConstitution());

    assertEquals(2,potion1.effectOnStrength());
    assertEquals(-3, potion1.effectOnCharisma());
    assertEquals(2, potion1.effectOnStrength());

    assertEquals(0,belt2.effectOnCharisma());
    assertEquals(1, belt1.getBeltUnitSize());
    assertEquals(2, belt2.getBeltUnitSize());
    assertEquals(4, belt3.getBeltUnitSize());
  }

  @Test
  public void testCompareTo() {
    assertEquals(0, headGear1.compareTo(headGear1));
    assertEquals(1, potion1.compareTo(headGear1));
    assertEquals(1, belt2.compareTo(headGear2));
    assertEquals(1, footWare1.compareTo(headGear2));

    assertEquals(-1, headGear1.compareTo(potion1));
    assertEquals(0, potion1.compareTo(potion2));
    assertEquals(1, belt2.compareTo(potion2));
    assertEquals(1, footWare1.compareTo(potion2));

    assertEquals(-1, headGear1.compareTo(belt1));
    assertEquals(-1, potion1.compareTo(belt1));
    assertEquals(1, footWare1.compareTo(belt1));
    assertEquals(0, belt1.compareTo(belt4));
    assertEquals(-1, belt1.compareTo(belt2));
    assertEquals(-1, belt1.compareTo(belt3));
    assertEquals(1, belt2.compareTo(belt1));
    assertEquals(0, belt2.compareTo(belt5));
    assertEquals(-1, belt2.compareTo(belt3));
    assertEquals(1, belt3.compareTo(belt1));
    assertEquals(1, belt3.compareTo(belt2));
    assertEquals(0, belt3.compareTo(belt6));


    assertEquals(-1, headGear1.compareTo(footWare1));
    assertEquals(-1, potion1.compareTo(footWare1));
    assertEquals(-1, belt2.compareTo(footWare1));
    assertEquals(0, footWare1.compareTo(footWare2));
  }

  @Test
  public void testGearSorting() {
    // gear list before sorting has a random sequence of gears.
    Collections.sort(gearList);
    // after sorting the gears are ordered
    // First head gears
    // Second potions
    // Third belts - these will be also be in order of their size - small, medium, large
    // Lastly all the foot ware
    assertTrue(gearList.equals(sortedGearList));
  }
}