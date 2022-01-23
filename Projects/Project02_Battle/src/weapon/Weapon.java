package weapon;

/**
 * Weapon interface that contains all operations that all types of Weapons should
 * support.
 */
public interface Weapon {

  /**
   * Returns the type of the weapon object.
   * Types of weapons: Swords, Flails, Axes.
   */
  WeaponCatalog getWeapon();

  /**
   * Returns how many of the same weapon the use can yield at a time.
   */
  int countAllowedAtOneTime();

  /**
   * Returns the minimum damage the weapon can cause on strking the opponent.
   */
  int minimumDamage();

  /**
   * Returns the maximum damage the weapon can cause on strking the opponent.
   */
  int maximumDamage();

  /**
   * Returns the sword type id weapon is a sword.
   * Sword types: Katanas, broadswords, two handed swords.
   */
  Swords.swordsType getSwordType();
}
