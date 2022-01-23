package player;

import gear.Gear;
import gear.GearCatalog;
import java.util.ArrayList;
import java.util.Arrays;
import weapon.Swords;
import weapon.Weapon;
import weapon.WeaponCatalog;

/**
 * Player class represented as player and the operations on Player object.
 */
public class Player {
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private int health;
  private ArrayList<Gear> equipmentsInUse;
  private ArrayList<Gear> equipmentsAvailable;
  private Weapon weaponInUse;
  private int weaponCount;

  /**
   * Constructs a Player object and assigns it the basic abilities.
   */
  public Player() {
    this.strength = getPlayerAbility();
    this.constitution = getPlayerAbility();
    this.dexterity = getPlayerAbility();
    this.charisma = getPlayerAbility();
    this.health = this.strength + this.constitution + this.dexterity + this.charisma;
    this.equipmentsInUse = new ArrayList<Gear>();
    this.equipmentsAvailable = new ArrayList<Gear>();
    this.weaponInUse = null;
    this.weaponCount = 0;
  }

  /**
   * Constructs a Test Player object and assigns it the basic abilities.
   */
  public void setPlayer1() {
    this.strength = getPlayerAbility1();
    this.constitution = getPlayerAbility1();
    this.dexterity = getPlayerAbility1();
    this.charisma = getPlayerAbility1();
    this.health = this.strength + this.constitution + this.dexterity + this.charisma;
    this.equipmentsInUse = new ArrayList<Gear>();
    this.equipmentsAvailable = new ArrayList<Gear>();
    this.weaponInUse = null;
    this.weaponCount = 0;
  }

  /**
   * Constructs a Test Player object and assigns it the basic abilities.
   */
  public void setPlayer2() {
    this.strength = getPlayerAbility2();
    this.constitution = getPlayerAbility2();
    this.dexterity = getPlayerAbility2();
    this.charisma = getPlayerAbility2();
    this.health = this.strength + this.constitution + this.dexterity + this.charisma;
    this.equipmentsInUse = new ArrayList<Gear>();
    this.equipmentsAvailable = new ArrayList<Gear>();
    this.weaponInUse = null;
    this.weaponCount = 0;
  }

  public void setEquipmentsAvailable(ArrayList<Gear> equipmentsAvailable) {
    this.equipmentsAvailable = equipmentsAvailable;
  }

  private int rollDiceReturn2() {
    return 2;
  }

  private int rollDiceReturn5() {
    return 5;
  }


  private int getPlayerAbility1() {
    int[] diceRolls = new int[4];
    diceRolls[0] = rollDiceReturn2();
    diceRolls[1] = rollDiceReturn2();
    diceRolls[2] = rollDiceReturn2();
    diceRolls[3] = rollDiceReturn2();
    Arrays.sort(diceRolls);
    return diceRolls[1] + diceRolls[2] + diceRolls[3];
  }

  private int getPlayerAbility2() {
    int[] diceRolls = new int[4];
    diceRolls[0] = rollDiceReturn5();
    diceRolls[1] = rollDiceReturn5();
    diceRolls[2] = rollDiceReturn5();
    diceRolls[3] = rollDiceReturn5();
    Arrays.sort(diceRolls);
    return diceRolls[1] + diceRolls[2] + diceRolls[3];
  }

  public int getStrength() {
    return this.strength;
  }

  public int getConstitution() {
    return this.constitution;
  }

  public int getDexterity() {
    return this.dexterity;
  }

  public int getCharisma() {
    return this.charisma;
  }

  public int getHealth() {
    return this.health;
  }

  public ArrayList<Gear> getEquipmentsAvailable() {
    return this.equipmentsAvailable;
  }

  public ArrayList<Gear> getEquipmentsInUse() {
    return this.equipmentsInUse;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setWeaponInUse(Weapon weaponInUse) {
    this.weaponInUse = weaponInUse;
  }

  public Weapon getWeaponInUse() {
    return this.weaponInUse;
  }

  public int getWeaponCount() {
    return this.weaponCount;
  }

  /**
   * Sets weapon count of the weapon the player is using.
   */
  public void setWeaponCount(int weaponCount) throws IllegalArgumentException {
    if (weaponCount > this.weaponInUse.countAllowedAtOneTime()) {
      throw new IllegalArgumentException("Not allowed!!");
    }
    this.weaponCount = weaponCount;
  }

  private int rollDice() {
    int diceRoll = 1 + (int) (Math.random() * 6);
    if (diceRoll >= 2) {
      return diceRoll;
    } else {
      diceRoll = rollDice();
    }
    return diceRoll;
  }

  private int getPlayerAbility() {
    int[] diceRolls = new int[4];
    diceRolls[0] = rollDice();
    diceRolls[1] = rollDice();
    diceRolls[2] = rollDice();
    diceRolls[3] = rollDice();
    Arrays.sort(diceRolls);
    return diceRolls[1] + diceRolls[2] + diceRolls[3];
  }

  private int getRandomNumber(int lowerLimit, int upperLimit) {
    return lowerLimit + (int) (Math.random() * ((upperLimit - lowerLimit) + 1));
  }

  private int getDummyRandomNumber(int lowerLimit, int upperLimit) {
    return (int) ((lowerLimit + upperLimit) / 2);
  }

  public int evaluatePlayerHealth() {
    return this.strength + this.constitution + this.dexterity + this.charisma;
  }

  public int evaluateStrikingPower() {
    return this.strength + getRandomNumber(1, 10);
  }

  public int evaluateDummyStrikingPower() {
    return this.strength + getDummyRandomNumber(1, 10);
  }

  public int evaluateAvoidanceAbility() {
    return this.dexterity + getRandomNumber(1, 6);
  }

  public int evaluateDummyAvoidanceAbility() {
    return this.dexterity + getDummyRandomNumber(1, 6);
  }

  /**
   * Function to evaluate the potential striking of the player.
   */
  public int evaluatePotentialStrikingDamage() {
    Weapon weaponUsedByPlayer = this.getWeaponInUse();
    int countOfWeapon = this.getWeaponCount();
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.SWORDS
            && weaponUsedByPlayer.getSwordType() == Swords.swordsType.KATANAS) {
      return this.strength + (countOfWeapon * (getRandomNumber(
              this.getWeaponInUse().minimumDamage(), this.getWeaponInUse().maximumDamage())));
    }
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.SWORDS && weaponUsedByPlayer.getSwordType()
            == Swords.swordsType.TWO_HANDED_SWORDS) {
      if (this.strength > 14) {
        return this.strength + getRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage());
      } else {
        return this.strength + ((getRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage())) / 2);
      }
    }
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.FLAILS) {
      if (this.dexterity > 14) {
        return this.strength + getRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage());
      } else {
        return this.strength + ((getRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage())) / 2);
      }
    }
    return this.strength + getRandomNumber(this.getWeaponInUse().minimumDamage(),
            this.getWeaponInUse().maximumDamage());
  }

  /**
   * Function to evaluate the dummy potential damage.
   * Instead of a random number the mid value of the minimum and maximum value specified.
   */
  public int evaluateDummyPotentialStrikingDamage() {
    Weapon weaponUsedByPlayer = this.getWeaponInUse();
    int countOfWeapon = this.getWeaponCount();
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.SWORDS
            && weaponUsedByPlayer.getSwordType() == Swords.swordsType.KATANAS) {
      return this.strength + (countOfWeapon * (getDummyRandomNumber(
              this.getWeaponInUse().minimumDamage(), this.getWeaponInUse().maximumDamage())));
    }
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.SWORDS
            && weaponUsedByPlayer.getSwordType() == Swords.swordsType.TWO_HANDED_SWORDS) {
      if (this.strength > 14) {
        return this.strength + getDummyRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage());
      } else {
        return this.strength + ((getDummyRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage())) / 2);
      }
    }
    if (weaponUsedByPlayer.getWeapon() == WeaponCatalog.FLAILS) {
      if (this.dexterity > 14) {
        return this.strength + getDummyRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage());
      } else {
        return this.strength + ((getDummyRandomNumber(this.getWeaponInUse().minimumDamage(),
                this.getWeaponInUse().maximumDamage())) / 2);
      }
    }
    return this.strength + getDummyRandomNumber(this.getWeaponInUse().minimumDamage(),
            this.getWeaponInUse().maximumDamage());
  }

  /**
   * Function to evaluate the actual damage.
   */
  public int evaluateActualDamage(Player opponent) {
    return this.evaluatePotentialStrikingDamage() - opponent.getConstitution();
  }

  /**
   * Function to evaluate the dummy actual damage.
   * Instead of a random number the mid value of the minimum and maximum value specified.
   */
  public int evaluateDummyActualDamage(Player opponent) {
    return this.evaluateDummyPotentialStrikingDamage() - opponent.getConstitution();
  }

  /**
   * Function to equip the player with all the gears that can be used.
   * All the gears that are assigned to the player will be assigned to the player,
   * unless any of them cannot be used in combination with the other.
   */
  public void equipPlayerWithAssignedEquipments() {
    ArrayList<Gear> gearsAvailableForPlayer = this.equipmentsAvailable;
    for (int i = 0; i < gearsAvailableForPlayer.size(); i++) {
      this.useEquipment(gearsAvailableForPlayer.get(i));
    }
  }

  /**
   * Function to create a dummy list of gears that are assigned to player for testing the,
   * player class.
   */
  public void assignDummyEquipmentsToPlayer(ArrayList<Gear> equipmentsAvailable) {
    this.equipmentsAvailable = equipmentsAvailable;
  }

  /**
   * Function to assign a gear to a player one by one.
   * A player will be assigned the gear unless some gear cannot be used with a gear,
   * he is already assigned.
   */
  public boolean useEquipment(Gear gear) {
    if (this.getEquipmentsAvailable().contains(gear)) {
      if (gear.getGear() == GearCatalog.HEAD_GEAR) {
        boolean headGearAlreadyInUse = false;
        boolean headGearAdded = false;
        for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
          if (this.getEquipmentsInUse().get(i).getGear() == GearCatalog.HEAD_GEAR) {
            headGearAlreadyInUse = true;
            break;
          }
        }
        if (!headGearAlreadyInUse) {
          headGearAdded = true;
          this.equipmentsInUse.add(gear);
          this.equipmentsAvailable.remove(gear);
          this.strength = this.strength + gear.effectOnStrength();
          this.constitution = this.constitution + gear.effectOnConstitution();
          this.dexterity = this.dexterity + gear.effectOnDexterity();
          this.charisma = this.charisma + gear.effectOnCharisma();
          this.health = this.evaluatePlayerHealth();
        }
        return headGearAdded;
      } else if (gear.getGear() == GearCatalog.POTIONS) {
        for (int i = 0; i < this.getEquipmentsAvailable().size(); i++) {
          if (this.getEquipmentsAvailable().get(i).getGear() == GearCatalog.POTIONS) {
            this.equipmentsInUse.add(gear);
            this.equipmentsAvailable.remove(gear);
            this.strength = this.strength + gear.effectOnStrength();
            this.constitution = this.constitution + gear.effectOnConstitution();
            this.dexterity = this.dexterity + gear.effectOnDexterity();
            this.charisma = this.charisma + gear.effectOnCharisma();
            this.health = this.evaluatePlayerHealth();
            return true;
          }
        }
        return false;
      } else if (gear.getGear() == GearCatalog.FOOT_WARE) {
        boolean footwareAlreadyInUse = false;
        boolean footwareAdded = false;
        for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
          if (this.getEquipmentsInUse().get(i).getGear() == GearCatalog.FOOT_WARE) {
            footwareAlreadyInUse = true;
            break;
          }
        }
        if (!footwareAlreadyInUse) {
          footwareAdded = true;
          this.equipmentsInUse.add(gear);
          this.equipmentsAvailable.remove(gear);
          this.strength = this.strength + gear.effectOnStrength();
          this.constitution = this.constitution + gear.effectOnConstitution();
          this.dexterity = this.dexterity + gear.effectOnDexterity();
          this.charisma = this.charisma + gear.effectOnCharisma();
          this.health = this.evaluatePlayerHealth();
        }
        return footwareAdded;
      } else if (gear.getGear() == GearCatalog.BELTS) {
        int sumOfBeltUnitsInUse = 0;
        for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
          if (this.getEquipmentsInUse().get(i).getGear() == GearCatalog.BELTS) {
            sumOfBeltUnitsInUse = sumOfBeltUnitsInUse
                    + this.getEquipmentsInUse().get(i).getBeltUnitSize();
          }
        }
        if ((gear.getBeltUnitSize() + sumOfBeltUnitsInUse) <= 10) {
          this.equipmentsInUse.add(gear);
          this.equipmentsAvailable.remove(gear);
          this.strength = this.strength + gear.effectOnStrength();
          this.constitution = this.constitution + gear.effectOnConstitution();
          this.dexterity = this.dexterity + gear.effectOnDexterity();
          this.charisma = this.charisma + gear.effectOnCharisma();
          this.health = this.evaluatePlayerHealth();
          return true;
        }
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("Player strength: %d constitution: %d dexterity: %d "
                    + "charisma: %d health: %d",
            this.getStrength(), this.getConstitution(), this.getDexterity(),
            this.getCharisma(), this.getHealth());
  }

  /**
   * Function to evaluate the overall effect of all the gears on the strength of the player.
   */
  public int getEffectOfGearsOnStrength() {
    int effectOfGearsOnStrength = 0;
    for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
      effectOfGearsOnStrength = effectOfGearsOnStrength
              + this.getEquipmentsInUse().get(i).effectOnStrength();
    }
    return effectOfGearsOnStrength;
  }

  /**
   * Function to evaluate the overall effect of all the gears on the constitution of the player.
   */
  public int getEffectOfGearsOnConstitution() {
    int effectOfGearsOnConstitution = 0;
    for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
      effectOfGearsOnConstitution = effectOfGearsOnConstitution
              + this.getEquipmentsInUse().get(i).effectOnConstitution();
    }
    return effectOfGearsOnConstitution;
  }

  /**
   * Function to evaluate the overall effect of all the gears on the dexterity of the player.
   */
  public int getEffectOfGearsOnDexterity() {
    int effectOfGearsOnDexterity = 0;
    for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
      effectOfGearsOnDexterity = effectOfGearsOnDexterity
              + this.getEquipmentsInUse().get(i).effectOnDexterity();
    }
    return effectOfGearsOnDexterity;
  }

  /**
   * Function to evaluate the overall effect of all the gears on the charisma of the player.
   */
  public int getEffectOfGearsOnCharisma() {
    int effectOfGearsOnCharisma = 0;
    for (int i = 0; i < this.getEquipmentsInUse().size(); i++) {
      effectOfGearsOnCharisma = effectOfGearsOnCharisma
              + this.getEquipmentsInUse().get(i).effectOnCharisma();
    }
    return effectOfGearsOnCharisma;
  }

  /**
   * Function to find the first striking player based on which one has a higher charisma.
   * If both players have the same charisma the first striking player will be returned
   * based on toss.
   */
  public Player getFirstStrikingPlayer(Player other) {
    if ((this.getCharisma() + this.getEffectOfGearsOnCharisma())
            > (other.getCharisma() + other.getEffectOfGearsOnCharisma())) {
      return this;
    } else if ((other.getCharisma() + other.getEffectOfGearsOnCharisma())
            > (this.getCharisma() + this.getEffectOfGearsOnCharisma())) {
      return other;
    } else if ((this.getCharisma() + this.getEffectOfGearsOnCharisma())
            == (other.getCharisma() + other.getEffectOfGearsOnCharisma())) {
      int toss = 1 + (int) (Math.random() * 2);
      if (toss == 1) {
        return this;
      } else {
        return other;
      }
    }
    return this;
  }
}
