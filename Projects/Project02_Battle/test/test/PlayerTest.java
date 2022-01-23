package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import gear.Gear;
import gear.HeadGear;
import gear.Belts;
import gear.Footware;
import gear.Potions;
import player.Player;
import weapon.Axes;
import weapon.Flails;
import weapon.Swords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class contains all the unit tests for a Player.
 * For testing the calculations of various parameters where a random number is required a dummy,
 * random number generator has been used which returns the mid value of the minimum and maximum
 * value that can be used for that parameter evaluation.
 * The entire function is the same, expect that instead on an actual random number, a dummy random
 * number id returned whose value we know and thus we know what value is expected after evaluating
 * the parameter.
 */
public class PlayerTest {
  Player player1;
  Player player2;
  Player player3;
  Player player4;

  ArrayList<Gear> equipmentList;

  @Before
  public void setUp() throws Exception {
    // for testing purpose I have created a dummy dice roll function
    player1 = new Player();
    player1.setPlayer1();

    player2 = new Player();
    player2.setPlayer2();

    // creating players with the actual dice rolling requirements
    player3 = new Player();
    player4 = new Player();

    equipmentList = new ArrayList<>();
    equipmentList.add(new HeadGear());
    equipmentList.add(new HeadGear());
    equipmentList.add(new HeadGear());
    equipmentList.add(new Footware());
    equipmentList.add(new Footware());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Potions());
    equipmentList.add(new Belts(Belts.size.LARGE));
    equipmentList.add(new Belts(Belts.size.LARGE));
    equipmentList.add(new Belts(Belts.size.LARGE));
    equipmentList.add(new Belts(Belts.size.MEDIUM));
    equipmentList.add(new Belts(Belts.size.MEDIUM));
    equipmentList.add(new Belts(Belts.size.MEDIUM));
    equipmentList.add(new Belts(Belts.size.MEDIUM));
    equipmentList.add(new Belts(Belts.size.SMALL));
    equipmentList.add(new Belts(Belts.size.SMALL));

    player1.assignDummyEquipmentsToPlayer(equipmentList);
    player2.assignDummyEquipmentsToPlayer(equipmentList);
  }

  @Test
  public void testPlayerAbiltiesRanges() {
    assertTrue(player3.getStrength() >= 6 && player3.getStrength() <= 18);
    assertTrue(player3.getConstitution() >= 6 && player3.getConstitution() <= 18);
    assertTrue(player3.getDexterity() >= 6 && player3.getDexterity() <= 18);
    assertTrue(player3.getCharisma() >= 6 && player3.getCharisma() <= 18);
  }

  @Test
  public void testToString() {
    assertEquals("Player strength: 6 constitution: 6 dexterity: 6 charisma: 6 health: 24",
            player1.toString());
    assertEquals("Player strength: 15 constitution: 15 dexterity: 15 charisma: 15 health: 60",
            player2.toString());
  }

  @Test
  public void testPlayerHealthEvaluation() {
    assertEquals(24, player1.evaluatePlayerHealth());
    assertEquals(60, player2.evaluatePlayerHealth());
    player1.useEquipment(new Footware());
    assertEquals(26, player1.evaluatePlayerHealth());
  }

  @Test
  public void testStrikingPlayer() {
    assertEquals("Player strength: 15 constitution: 15 dexterity: 15 charisma: 15 health: 60",
            player1.getFirstStrikingPlayer(player2).toString());
  }

  @Test
  public void testPlayerStrikingPowerEvaluation() {
    assertEquals(11, player1.evaluateDummyStrikingPower());
    assertEquals(20, player2.evaluateDummyStrikingPower());

    player1.useEquipment(new Potions());
    assertEquals(13, player1.evaluateDummyStrikingPower());
    player2.useEquipment(new Potions());
    assertEquals(22, player2.evaluateDummyStrikingPower());
  }

  @Test
  public void testPlayerAvoidanceAbilityEvaluation() {
    assertEquals(9, player1.evaluateDummyAvoidanceAbility());
    assertEquals(18, player2.evaluateDummyAvoidanceAbility());

    player1.useEquipment(new Belts(Belts.size.LARGE));
    assertEquals(12, player1.evaluateDummyAvoidanceAbility());
    player2.useEquipment(new Footware());
    assertEquals(20, player2.evaluateDummyAvoidanceAbility());
  }

  @Test
  public void testPotentialStrikingDamageEvaluation() {
    player1.setWeaponInUse(new Swords(Swords.swordsType.KATANAS));
    player1.setWeaponCount(1);
    assertEquals(11, player1.evaluateDummyPotentialStrikingDamage());

    player1.setWeaponInUse(new Swords(Swords.swordsType.KATANAS));
    player1.setWeaponCount(2);
    assertEquals(16, player1.evaluateDummyPotentialStrikingDamage());

    player1.setWeaponInUse(new Swords(Swords.swordsType.BROADSWORDS));
    assertEquals(14,player1.evaluateDummyPotentialStrikingDamage());

    player1.setWeaponInUse(new Swords(Swords.swordsType.TWO_HANDED_SWORDS));
    assertEquals(11, player1.evaluateDummyPotentialStrikingDamage());

    player1.setWeaponInUse(new Axes());
    assertEquals(14, player1.evaluateDummyPotentialStrikingDamage());

    player1.setWeaponInUse(new Flails());
    assertEquals(11, player1.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Swords(Swords.swordsType.KATANAS));
    player2.setWeaponCount(1);
    assertEquals(20, player2.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Swords(Swords.swordsType.KATANAS));
    player2.setWeaponCount(2);
    assertEquals(25, player2.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Swords(Swords.swordsType.BROADSWORDS));
    assertEquals(23, player2.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Swords(Swords.swordsType.TWO_HANDED_SWORDS));
    assertEquals(25, player2.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Axes());
    assertEquals(23, player2.evaluateDummyPotentialStrikingDamage());

    player2.setWeaponInUse(new Flails());
    assertEquals(25, player2.evaluateDummyPotentialStrikingDamage());
  }

  @Test
  public void testActualDamageEvaluation() {
    player1.setWeaponInUse(new Flails());
    player2.setWeaponInUse(new Flails());
    assertEquals(-4, player1.evaluateDummyActualDamage(player2));
    assertEquals(19, player2.evaluateDummyActualDamage(player1));
  }

  @Test
  public void testEffectOfGearsOnStrength() {
    player1.useEquipment(new Potions());
    assertEquals(2, player1.getEffectOfGearsOnStrength());
    assertEquals(0, player2.getEffectOfGearsOnStrength());

    // now if player 1 and player 2 consume 1 potion each.
    // the overall effect all the gears used till now on strength should be returned accordingly.
    player1.useEquipment(new Potions());
    player2.useEquipment(new Potions());
    assertEquals(4, player1.getEffectOfGearsOnStrength());
    assertEquals(2, player2.getEffectOfGearsOnStrength());
  }

  @Test
  public void testEffectOfGearsOnConstitution() {
    player1.useEquipment(new HeadGear());
    player1.useEquipment(new Belts(Belts.size.MEDIUM));
    assertEquals(4, player1.getEffectOfGearsOnConstitution());
    player2.useEquipment(new Belts(Belts.size.LARGE));
    assertEquals(3, player2.getEffectOfGearsOnConstitution());

    /*
     * If player 1 uses a medium belt now, the overall effect all the gears used till
     * now on constitution should be returned accordingly.
     */
    player1.useEquipment(new Belts(Belts.size.MEDIUM));
    assertEquals(6, player1.getEffectOfGearsOnConstitution());
  }

  @Test
  public void testEffectOfGearsOnDexterity() {
    assertEquals(0, player1.getEffectOfGearsOnDexterity());
    player2.useEquipment(new Belts(Belts.size.LARGE));
    player2.useEquipment(new Belts(Belts.size.SMALL));
    assertEquals(4, player2.getEffectOfGearsOnDexterity());

    /*
     * If player 1 uses a footware and belt now, the overall effect all the gears
     * used till now on dexterity should be returned accordingly.
     */
    player1.useEquipment(new Footware());
    player1.useEquipment(new Belts(Belts.size.MEDIUM));
    assertEquals(4, player1.getEffectOfGearsOnDexterity());
  }

  @Test
  public void testEffectOfGearsOnCharisma() {
    player1.useEquipment(new Potions());
    assertEquals(-3, player1.getEffectOfGearsOnCharisma());
    assertEquals(0, player2.getEffectOfGearsOnCharisma());

    /*
     * If player one uses another potion now, the overall effect all the gears used till
     * now on charisma should be returned accordingly.
     */
    player1.useEquipment(new Potions());
    assertEquals(-6, player1.getEffectOfGearsOnCharisma());
  }

  @Test
  public void testUseEquipment() {
    player1.useEquipment(new HeadGear());
    player1.useEquipment(new Potions());
    player2.useEquipment(new Footware());
    player2.useEquipment(new Belts(Belts.size.LARGE));
    ArrayList<Gear> expectedListOfGearsInUsePlayer1 = new ArrayList<>();
    expectedListOfGearsInUsePlayer1.add(new HeadGear());
    expectedListOfGearsInUsePlayer1.add(new Potions());

    ArrayList<Gear> expectedListOfGearsInUsePlayer2 = new ArrayList<>();
    expectedListOfGearsInUsePlayer2.add(new Footware());
    expectedListOfGearsInUsePlayer2.add(new Belts(Belts.size.LARGE));
    //player 1 is has used a head gear and consumed a potion already.
    //player 2 has used a footware and a large belt already.
    assertEquals(expectedListOfGearsInUsePlayer1, player1.getEquipmentsInUse());
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    /*
     * Player 1 is already using a head gear, so when he tries to use another headgear
     * it should not be allowed
     */
    assertFalse(player1.useEquipment(new HeadGear()));
    assertEquals(expectedListOfGearsInUsePlayer1, player1.getEquipmentsInUse());
    //player 2 has not used a heard gear till now, so he will be allowed to use to.
    assertTrue(player2.useEquipment(new HeadGear()));
    expectedListOfGearsInUsePlayer2.add(new HeadGear());
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    //players can consume as many potions as they want.
    //Player 1 will be able to consume a potion though he has already consumed one already.
    assertTrue(player1.useEquipment(new Potions()));
    expectedListOfGearsInUsePlayer1.add(new Potions());
    assertEquals(expectedListOfGearsInUsePlayer1, player1.getEquipmentsInUse());
    // Similarly player 2 will also be able to consume one of more potions.
    assertTrue(player2.useEquipment(new Potions()));
    assertTrue(player2.useEquipment(new Potions()));
    expectedListOfGearsInUsePlayer2.add(new Potions());
    expectedListOfGearsInUsePlayer2.add(new Potions());
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    //one player can use only one foot ware at a time
    // player 1 has not used foot ware before, so he will be allowed to use it.
    assertTrue(player1.useEquipment(new Footware()));
    expectedListOfGearsInUsePlayer1.add(new Footware());
    assertEquals(expectedListOfGearsInUsePlayer1, player1.getEquipmentsInUse());
    // player 2 is already using a foot ware, so he will not be allowed to use another.
    assertFalse(player2.useEquipment(new Footware()));
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    /*
     * Each player has the ability to wear 10 units of belt.
     * to wear more belt units.
     * as player 1 has not worn any belts as of now, if he tries to use
     * 2 large and 1 medium he will be able to use.
     */
    assertTrue(player1.useEquipment(new Belts(Belts.size.LARGE)));
    assertTrue(player1.useEquipment(new Belts(Belts.size.LARGE)));
    assertTrue(player1.useEquipment(new Belts(Belts.size.MEDIUM)));
    expectedListOfGearsInUsePlayer1.add(new Belts(Belts.size.LARGE));
    expectedListOfGearsInUsePlayer1.add(new Belts(Belts.size.LARGE));
    expectedListOfGearsInUsePlayer1.add(new Belts(Belts.size.MEDIUM));
    assertEquals(expectedListOfGearsInUsePlayer1, player1.getEquipmentsInUse());

    /*
     * Now as player is already wearing 10 units of belts he will not be allowed
     * to wear more belt units.
     */
    assertFalse(player1.useEquipment(new Belts(Belts.size.MEDIUM)));

    /*
     * Player 2 is already wearing a belt of large size so 4 units of belts are already consumed.
     * Now if the user uses 2 mediums and 1 small belts it will come to 9 belt units which
     * will be allowed.
     */
    assertTrue(player2.useEquipment(new Belts(Belts.size.MEDIUM)));
    assertTrue(player2.useEquipment(new Belts(Belts.size.MEDIUM)));
    assertTrue(player2.useEquipment(new Belts(Belts.size.SMALL)));
    expectedListOfGearsInUsePlayer2.add(new Belts(Belts.size.MEDIUM));
    expectedListOfGearsInUsePlayer2.add(new Belts(Belts.size.MEDIUM));
    expectedListOfGearsInUsePlayer2.add(new Belts(Belts.size.SMALL));
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    /*
     * Now as player 2 is using 9 units of belts if he tries to use one more medium
     * he will not be allowed
     */
    assertFalse(player2.useEquipment(new Belts(Belts.size.MEDIUM)));
    assertEquals(expectedListOfGearsInUsePlayer2, player2.getEquipmentsInUse());

    /*
     * Players can consume as many potions as they want provided they have it in their equipment
     * bag. Player 1 has 4 more potions available. He can consume those, but after that id he
     * tries to consume it will not be allowed to do so.
     */
    assertTrue(player1.useEquipment(new Potions()));
    assertTrue(player1.useEquipment(new Potions()));
    assertTrue(player1.useEquipment(new Potions()));
    assertTrue(player1.useEquipment(new Potions()));
    assertFalse(player1.useEquipment(new Potions()));
  }
}