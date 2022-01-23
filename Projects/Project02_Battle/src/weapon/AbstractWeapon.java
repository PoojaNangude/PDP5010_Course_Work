package weapon;

/**
 * An abstract class that contains all of the code that is shared by all types of Weapons.
 */
public abstract class AbstractWeapon implements Weapon {
  protected WeaponCatalog weaponName;
  protected int countAllowedAtOneTime;
  protected int minimumDamage;
  protected int maximumDamage;

  /**
   *  Constructor for use by subclasses.
   */
  public AbstractWeapon(WeaponCatalog weaponName) {
    this.weaponName = weaponName;
    this.countAllowedAtOneTime = countAllowedAtOneTime;
    this.minimumDamage = 0;
    this.maximumDamage = 0;
  }

  protected void setCountAllowedAtOneTime(int countAllowedAtOneTime) {
    this.countAllowedAtOneTime = countAllowedAtOneTime;
  }

  protected void setMinimumDamage(int minimumDamage) {
    this.minimumDamage = minimumDamage;
  }

  protected void setMaximumDamage(int minimumDamage) {
    this.minimumDamage = maximumDamage;
  }

  protected boolean equalsAxes(Axes other) {
    return false;
  }

  protected boolean equalsFlails(Flails other) {
    return false;
  }

  protected boolean equalsSwords(Swords other) {
    return false;
  }

  public int getBeltUnitSize() {
    return 0;
  }

  public Swords.swordsType getSwordType() {
    return null;
  }
}
