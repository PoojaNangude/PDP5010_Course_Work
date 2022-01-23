package test;

import org.junit.Before;
import org.junit.Test;

import weapon.Weapon;
import weapon.Axes;
import weapon.Flails;
import weapon.Swords;
import weapon.WeaponCatalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class contains all the unit tests for various kinds of Weapons.
 */
public class WeaponTest {
  private Weapon axe1;
  private Weapon axe2;

  private Weapon flail1;
  private Weapon flail2;

  private Weapon sword1;
  private Weapon sword2;
  private Weapon sword3;
  private Weapon sword4;
  private Weapon sword5;
  private Weapon sword6;

  @Before
  public void setUp() throws Exception {
    axe1 = new Axes();
    axe2 = new Axes();

    flail1 = new Flails();
    flail2 = new Flails();

    sword1 = new Swords(Swords.swordsType.KATANAS);
    sword2 = new Swords(Swords.swordsType.KATANAS);
    sword3 = new Swords(Swords.swordsType.BROADSWORDS);
    sword4 = new Swords(Swords.swordsType.BROADSWORDS);
    sword5 = new Swords(Swords.swordsType.TWO_HANDED_SWORDS);
    sword6 = new Swords(Swords.swordsType.TWO_HANDED_SWORDS);
  }

  @Test
  public void testEquals() {
    assertTrue(axe1.equals(axe2));
    assertTrue(flail1.equals(flail2));
    assertTrue(sword1.equals(sword2));
    assertTrue(sword3.equals(sword4));
    assertTrue(sword5.equals(sword6));
    assertFalse(sword1.equals(sword4));
    assertFalse(sword1.equals(sword5));
    assertFalse(sword3.equals(sword1));
    assertFalse(sword3.equals(sword6));
    assertFalse(sword5.equals(sword1));
    assertFalse(sword6.equals(sword3));
  }

  @Test
  public void testWeaponObjects() {
    assertEquals(WeaponCatalog.AXES, axe1.getWeapon());
    assertEquals(1, axe1.countAllowedAtOneTime());
    assertEquals(6, axe1.minimumDamage());
    assertEquals(10, axe1.maximumDamage());

    assertEquals(WeaponCatalog.FLAILS, flail1.getWeapon());
    assertEquals(1, flail1.countAllowedAtOneTime());
    assertEquals(8, flail1.minimumDamage());
    assertEquals(12, flail1.maximumDamage());

    assertEquals(WeaponCatalog.SWORDS, sword1.getWeapon());
    assertEquals(Swords.swordsType.KATANAS, sword1.getSwordType());
    assertEquals(2, sword1.countAllowedAtOneTime());
    assertEquals(4, sword1.minimumDamage());
    assertEquals(6, sword1.maximumDamage());

    assertEquals(WeaponCatalog.SWORDS, sword3.getWeapon());
    assertEquals(Swords.swordsType.BROADSWORDS, sword3.getSwordType());
    assertEquals(1, sword3.countAllowedAtOneTime());
    assertEquals(6, sword3.minimumDamage());
    assertEquals(10, sword3.maximumDamage());

    assertEquals(WeaponCatalog.SWORDS, sword3.getWeapon());
    assertEquals(Swords.swordsType.TWO_HANDED_SWORDS, sword5.getSwordType());
    assertEquals(1, sword5.countAllowedAtOneTime());
    assertEquals(8, sword5.minimumDamage());
    assertEquals(12, sword5.maximumDamage());
  }
}