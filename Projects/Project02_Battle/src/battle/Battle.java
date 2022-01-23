package battle;

import gear.Belts;
import gear.Footware;
import gear.Gear;
import gear.HeadGear;
import gear.Potions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import player.Player;
import weapon.Axes;
import weapon.Flails;
import weapon.Swords;
import weapon.Weapon;
import weapon.WeaponCatalog;

/**
 * Battle class which implements the Battle interface.
 */
public class Battle implements BattleInterface {
  private Player player1;
  private Player player2;
  private ArrayList<Weapon> armory;
  private ArrayList<Gear> equipmentBag;
  private Player strikingPlayer;
  private Player winningPlayer;

  @Override
  public void createBattleArena() {
    createPlayers();
    createEquipmentBag();
    assignEquipmentsToPlayer();
    createArmory();
    assignWeapon();
  }

  @Override
  public void playBattle() {
    getFirstStrikingPlayer();
    startBattle();
  }

  /**
   * creating a battle arena with know player ability values for the purpose of testing.
   */
  public void createBattleArenaForTesting() {
    createTestPlayers();
    createEquipmentBag();
    assignEquipmentsToPlayer();
    createArmory();
    assignWeapon();
  }

  public Player getWinningPlayer() {
    return this.winningPlayer;
  }

  public void setEquipmentBag(ArrayList<Gear> equipmentBag) {
    this.equipmentBag = equipmentBag;
  }

  /**
   * Starts the battle between 2 players in a loop.
   * The battle will end when one of the players health is zero or when its a draw.
   */
  public void startBattle() {
    int round = 1;
    ArrayList<Integer> damageRecords = new ArrayList();
    while (player1.getHealth() > 0 || player2.getHealth() > 0) {
      Player opponentPlayer = getOpponentPlayer();
      int strikingPowerOfAttackingPlayer = strikingPlayer.evaluateStrikingPower();
      int avoidanceAbilityOfOpponent = opponentPlayer.evaluateAvoidanceAbility();
      int potentialStrikingDamage = 0;
      int actualDamage = 0;
      int opponentPlayerHealth = 0;
      int updatedOpponentPlayerHealth = 0;
      boolean successfulHit = false;
      if (strikingPowerOfAttackingPlayer > avoidanceAbilityOfOpponent) {
        successfulHit = true;
        potentialStrikingDamage = this.strikingPlayer.evaluatePotentialStrikingDamage();
        actualDamage = potentialStrikingDamage - opponentPlayer.getConstitution();
        if (actualDamage > 0) {
          opponentPlayerHealth = opponentPlayer.getHealth();
          updatedOpponentPlayerHealth = opponentPlayerHealth - actualDamage;
          if (updatedOpponentPlayerHealth < 0) {
            updatedOpponentPlayerHealth = 0;
          }
          opponentPlayer.setHealth(updatedOpponentPlayerHealth);
        } else {
          actualDamage = 0;
        }
      }
      damageRecords.add(actualDamage);
      printEachRoundResult(new BattleRoundResult(round, this.strikingPlayer, opponentPlayer,
              successfulHit, actualDamage));
      round++;
      int damageRecordsSize = damageRecords.size();
      if (damageRecordsSize > 60) {
        int countOfSubsequentNoHits = 0;
        for (int i = damageRecordsSize - 1; i >= damageRecordsSize - 20; i--) {
          if (damageRecords.get(i) == 0) {
            countOfSubsequentNoHits = countOfSubsequentNoHits + 1;
          }
        }
        if (countOfSubsequentNoHits == 20) {
          this.winningPlayer = null;
          break;
        }
      }
      if (this.player1.getHealth() == 0 || this.player2.getHealth() == 0) {
        if (this.player1.getHealth() == 0) {
          this.winningPlayer = player2;
        } else {
          this.winningPlayer = player1;
        }
        break;
      }
      this.strikingPlayer = getNextStrikingPlayer();
    }
  }

  /**
   * Method to start a rematch between the same players by resetting their original health.
   */
  public void rematchBattle() {
    int resetPlayerHealth = player1.evaluatePlayerHealth();
    player1.setHealth(resetPlayerHealth);
    resetPlayerHealth = player2.evaluatePlayerHealth();
    player2.setHealth(resetPlayerHealth);
    getFirstStrikingPlayer();
    startBattle();
  }

  public Player getPlayer1() {
    return this.player1;
  }

  public Player getPlayer2() {
    return this.player2;
  }

  public Player getStrikingPlayer() {
    return this.strikingPlayer;
  }

  public ArrayList<Weapon> getArmory() {
    return this.armory;
  }

  private void createPlayers() {
    player1 = new Player();
    player2 = new Player();
  }

  private void createTestPlayers() {
    player1 = new Player();
    player1.setPlayer1();

    player2 = new Player();
    player2.setPlayer2();
  }

  /**
   * Method to create an armoury of weapons for the match.
   */
  public ArrayList<Weapon> createArmory() {
    armory = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      armory.add(new Axes());
    }
    for (int i = 0; i < 12; i++) {
      armory.add(new Flails());
    }
    for (int i = 0; i < 4; i++) {
      armory.add(new Swords(Swords.swordsType.KATANAS));
      armory.add(new Swords(Swords.swordsType.BROADSWORDS));
      armory.add(new Swords(Swords.swordsType.TWO_HANDED_SWORDS));
    }
    return armory;
  }

  /**
   * Method to create an equipment bag of weapons for the match.
   */
  public ArrayList<Gear> createEquipmentBag() {
    equipmentBag = new ArrayList<Gear>();
    for (int i = 0; i < 10; i++) {
      equipmentBag.add(new HeadGear());
    }

    for (int i = 0; i < 10; i++) {
      equipmentBag.add(new Footware());
    }

    for (int i = 0; i < 15; i++) {
      equipmentBag.add(new Potions());
    }

    for (int i = 0; i < 10; i++) {
      equipmentBag.add(new Belts(Belts.size.LARGE));
    }

    for (int i = 0; i < 10; i++) {
      equipmentBag.add(new Belts(Belts.size.MEDIUM));
    }

    for (int i = 0; i < 5; i++) {
      equipmentBag.add(new Belts(Belts.size.SMALL));
    }
    return equipmentBag;
  }

  private ArrayList<Gear> assignEquipmentsToPlayer(Player player) {
    Random random = new Random();
    while (player.getEquipmentsAvailable().size() != 20) {
      Gear singleEquipment = equipmentBag.get(random.nextInt(equipmentBag.size()));
      equipmentBag.remove(singleEquipment);
      ArrayList<Gear> bag = player.getEquipmentsAvailable();
      bag.add(singleEquipment);
      player.setEquipmentsAvailable(bag);
    }
    Collections.sort(player.getEquipmentsAvailable());
    return player.getEquipmentsAvailable();
  }

  private void assignEquipmentsToPlayer() {
    assignEquipmentsToPlayer(this.player1);
    assignEquipmentsToPlayer(this.player2);
    player1.equipPlayerWithAssignedEquipments();
    player2.equipPlayerWithAssignedEquipments();
  }

  public Player getFirstStrikingPlayer() {
    this.strikingPlayer = player1.getFirstStrikingPlayer(player2);
    return this.strikingPlayer;
  }

  /**
   * Method to return the next striking player.
   */
  public Player getNextStrikingPlayer() {
    if (this.strikingPlayer == this.player1) {
      this.strikingPlayer = this.player2;
    } else {
      this.strikingPlayer = this.player1;
    }
    return this.strikingPlayer;
  }

  /**
   * Method to return the opponent player.
   */
  public Player getOpponentPlayer() {
    if (this.strikingPlayer == this.player1) {
      return this.player2;
    } else {
      return this.player1;
    }
  }

  /**
   * Method to assign weapons to the player.
   */
  public void assignWeaponsToPlayers(Player player) {
    Random random = new Random();
    Weapon weapon = getArmory().get(random.nextInt(getArmory().size()));
    player.setWeaponInUse(weapon);
    player.setWeaponCount(1);
    this.armory.remove(weapon);
    if (weapon.getWeapon() == WeaponCatalog.SWORDS && weapon.getSwordType()
            == Swords.swordsType.KATANAS) {
      weapon = getArmory().get(random.nextInt(getArmory().size()));
      if (weapon.getWeapon() == WeaponCatalog.SWORDS && weapon.getSwordType()
              == Swords.swordsType.KATANAS) {
        player.setWeaponCount(2);
        this.armory.remove(weapon);
      }
    }
  }

  public void assignWeapon() {
    assignWeaponsToPlayers(this.player1);
    assignWeaponsToPlayers(this.player2);
  }

  /**
   * Method to print data of each round.
   */
  public void printEachRoundResult(BattleRoundResult battleRoundResult) {
    System.out.println("Round no: " + battleRoundResult.getRound());
    System.out.println("Striking Player: " + battleRoundResult.getStrikingPlayer());
    System.out.println("Opponent Player: " + battleRoundResult.getOpponentPlayer());
    System.out.println("Did striking player hit the opponent player: "
            + battleRoundResult.getIsSuccessfulHit());
    System.out.println("Damage done on oponent: " + battleRoundResult.getDamageToOpponent());
    System.out.println();
  }
}
