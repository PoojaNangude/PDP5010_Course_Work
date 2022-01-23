package weapon;

/**
 * This class represents a Axe. It defines all the operations mandated by
 * the AbstractWeapon interface
 */
public class Axes extends AbstractWeapon {

  /**
   * Constructs a Axes with the allowed number of axes allowed at a time,
   * the minimum damage and maximum damage.
   */
  public Axes() {
    super(WeaponCatalog.AXES);
    this.countAllowedAtOneTime = 1;
    this.minimumDamage = 6;
    this.maximumDamage = 10;
  }

  @Override
  public WeaponCatalog getWeapon() {
    return this.weaponName;
  }

  @Override
  public int countAllowedAtOneTime() {
    return this.countAllowedAtOneTime;
  }

  @Override
  public int minimumDamage() {
    return this.minimumDamage;
  }

  @Override
  public int maximumDamage() {
    return this.maximumDamage;
  }

  @Override
  protected boolean equalsAxes(Axes other) {
    return this.weaponName == other.weaponName
            && this.countAllowedAtOneTime == other.countAllowedAtOneTime
            && this.minimumDamage == other.minimumDamage
            && this.maximumDamage == other.maximumDamage;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractWeapon) {
      AbstractWeapon abstractWeapon = (AbstractWeapon) other;
      return abstractWeapon.equalsAxes(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.minimumDamage + this.maximumDamage
            + this.countAllowedAtOneTime);
  }
}
