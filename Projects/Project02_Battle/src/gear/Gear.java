package gear;

/**
 * Gear interface that contains all operations that all types of gears should
 * support.
 */
public interface Gear extends Comparable<Gear> {

  /**
   * Returns the type of the gear - type enum.
   * Gear types: headgear, belt, foot ware or potions.
   */
  GearCatalog getGear();

  /**
   * Returns the effect of the gear on the strength of the player.
   */
  int effectOnStrength();

  /**
   * Returns the effect of the gear on the constitution of the player.
   */
  int effectOnConstitution();

  /**
   * Returns the effect of the gear on the dexterity of the player.
   */
  int effectOnDexterity();

  /**
   * Returns the effect of the gear on the charisma of the player.
   */
  int effectOnCharisma();

  /**
   * Returns the unit size of the belt based on the size of the belt.
   * Sizes of belt - small, medium, large.
   */
  int getBeltUnitSize();
}
