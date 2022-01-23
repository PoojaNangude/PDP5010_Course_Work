package weapon;

/**
 * This class represents a Sword. It defines all the operations mandated by
 * the AbstractWeapon interface.
 */
public class Swords extends AbstractWeapon {

  /**
   * Enum type representing the type of belts in the game.
   */
  public enum swordsType {
    KATANAS,
    BROADSWORDS,
    TWO_HANDED_SWORDS
  }

  protected swordsType swordType;

  /**
   * Constructs a Sword with the allowed number of axes allowed at a time,
   * the minimum damage and maximum damage depending on the type of sword.
   */
  public Swords(swordsType swordType) {
    super(WeaponCatalog.SWORDS);
    if (swordType == swordsType.KATANAS) {
      this.setSwordType(swordsType.KATANAS);
      this.countAllowedAtOneTime = 2;
      this.minimumDamage = 4;
      this.maximumDamage = 6;
    } else if (swordType == swordsType.BROADSWORDS) {
      this.setSwordType(swordsType.BROADSWORDS);
      this.countAllowedAtOneTime = 1;
      this.minimumDamage = 6;
      this.maximumDamage = 10;
    } else if (swordType == swordsType.TWO_HANDED_SWORDS) {
      this.setSwordType(swordsType.TWO_HANDED_SWORDS);
      this.countAllowedAtOneTime = 1;
      this.minimumDamage = 8;
      this.maximumDamage = 12;
    }
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
  public swordsType getSwordType() {
    return this.swordType;
  }

  private void setSwordType(swordsType swordType) {
    this.swordType = swordType;
  }

  @Override
  protected boolean equalsSwords(Swords other) {
    return this.weaponName == other.weaponName
            && this.swordType == other.swordType
            && this.countAllowedAtOneTime == other.countAllowedAtOneTime
            && this.minimumDamage == other.minimumDamage
            && this.maximumDamage == other.maximumDamage;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractWeapon) {
      AbstractWeapon abstractWeapon = (AbstractWeapon) other;
      return abstractWeapon.equalsSwords(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.minimumDamage + this.maximumDamage
            + this.countAllowedAtOneTime);
  }
}
