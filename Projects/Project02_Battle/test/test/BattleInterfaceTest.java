package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import battle.Battle;
import gear.Gear;
import gear.GearCatalog;
import weapon.Axes;
import weapon.Flails;
import weapon.Swords;
import weapon.Weapon;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * This class contains all the unit tests for the battle interface.
 */
public class BattleInterfaceTest {
  Battle battle;

  @Before
  public void setUp() throws Exception {
    battle = new Battle();
  }

  /**
   * Testing whether 25% of the items in the created equipment bag are having negative
   * effect on the player abilities.
   */
  @Test
  public void testCreateEquipmentBag() {
    ArrayList<Gear> equipmentBag = battle.createEquipmentBag();
    int countOfGearsHavingDiminishingEffects = 0;
    for (int i = 0; i < equipmentBag.size(); i++) {
      if (equipmentBag.get(i).effectOnStrength() < 0
              || equipmentBag.get(i).effectOnConstitution() < 0
              || equipmentBag.get(i).effectOnDexterity() < 0
              || equipmentBag.get(i).effectOnCharisma() < 0) {
        countOfGearsHavingDiminishingEffects++;
      }
    }
    assertEquals(25, (countOfGearsHavingDiminishingEffects * 100 / equipmentBag.size()));
  }

  /**
   * Testing whether the created equipment bag has the minimum number of each gear as specified.
   */
  @Test
  public void testCreateEquipmentBagMinimumRequirements() {
    ArrayList<Gear> equipmentBag = battle.createEquipmentBag();

    int countOfHeadGears = 0;
    for (int i = 0; i < equipmentBag.size(); i++) {
      if (equipmentBag.get(i).getGear() == GearCatalog.HEAD_GEAR) {
        countOfHeadGears++;
      }
    }
    assertTrue(countOfHeadGears >= 5);

    int countOfFootware = 0;
    for (int i = 0; i < equipmentBag.size(); i++) {
      if (equipmentBag.get(i).getGear() == GearCatalog.FOOT_WARE) {
        countOfFootware++;
      }
    }
    assertTrue(countOfFootware >= 5);

    int countOfBelts = 0;
    for (int i = 0; i < equipmentBag.size(); i++) {
      if (equipmentBag.get(i).getGear() == GearCatalog.BELTS) {
        countOfBelts++;
      }
    }
    assertTrue(countOfBelts >= 15);

    int countOfPotions = 0;
    for (int i = 0; i < equipmentBag.size(); i++) {
      if (equipmentBag.get(i).getGear() == GearCatalog.POTIONS) {
        countOfPotions++;
      }
    }
    assertTrue(countOfPotions >= 15);
  }

  @Test
  public void testCreateArmoury() {
    // making sure the armoury created has at least 1 of each type of weapon
    ArrayList<Weapon> armoury = battle.createArmory();
    assertTrue(armoury.contains(new Swords(Swords.swordsType.KATANAS)));
    assertTrue(armoury.contains(new Swords(Swords.swordsType.BROADSWORDS)));
    assertTrue(armoury.contains(new Swords(Swords.swordsType.TWO_HANDED_SWORDS)));
    assertTrue(armoury.contains(new Axes()));
    assertTrue(armoury.contains(new Flails()));
  }

  @Test
  public void testGetNextStrikingPlayer() {
    battle.createBattleArenaForTesting();
    // player with higher charisma will be given the strike
    assertEquals(battle.getPlayer2(), battle.getFirstStrikingPlayer());
    assertEquals(battle.getPlayer1(), battle.getNextStrikingPlayer());
    assertEquals(battle.getPlayer2(), battle.getNextStrikingPlayer());
    assertEquals(battle.getPlayer1(), battle.getNextStrikingPlayer());
  }

  @Test
  public void testGetOpponentPlayer() {
    battle.createBattleArenaForTesting();
    // player with higher charisma will be given the strike
    assertEquals(battle.getPlayer2(), battle.getFirstStrikingPlayer());
    assertEquals(battle.getPlayer1(), battle.getOpponentPlayer());

    assertEquals(battle.getPlayer1(), battle.getNextStrikingPlayer());
    assertEquals(battle.getPlayer2(), battle.getOpponentPlayer());
  }

}