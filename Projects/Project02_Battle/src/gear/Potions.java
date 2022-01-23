package gear;

/**
 * This class represents a Potion. It offers all the operations mandated by the
 * Gear interface.
 */
public class Potions extends AbstractGear {

  /**
   * Construct a Potions object.
   * The players' constitution and strength increases by 2 but
   * charisma decreases by 3 when user consumes potions.
   */
  public Potions() {
    super(GearCatalog.POTIONS);
    this.effectOnStrength = 2;
    this.effectOnConstitution = 2;
    this.effectOnCharisma = -3;
  }

  @Override
  public GearCatalog getGear() {
    return this.gearName;
  }

  @Override
  public int effectOnStrength() {
    return this.effectOnStrength;
  }

  @Override
  public int effectOnConstitution() {
    return this.effectOnConstitution;
  }

  @Override
  public int effectOnDexterity() {
    return this.effectOnDexterity;
  }

  @Override
  public int effectOnCharisma() {
    return this.effectOnCharisma;
  }

  @Override
  protected boolean equalsPotion(Potions other) {
    return this.gearName.equals(other.gearName)
            && this.effectOnStrength == other.effectOnStrength
            && this.effectOnConstitution == other.effectOnConstitution
            && this.effectOnDexterity == other.effectOnDexterity
            && this.effectOnCharisma == other.effectOnCharisma;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear abstractGear = (AbstractGear) other;
      return abstractGear.equalsPotion(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.effectOnCharisma + this.effectOnConstitution
            + this.effectOnDexterity + this.effectOnStrength);
  }

  protected int compareHeadGear(HeadGear other) {
    return -1;
  }

  protected int comparePotions(Potions other) {
    return 0;
  }

  protected int compareBelts(Belts other) {
    return 1;
  }

  protected int compareFootware(Footware other) {
    return 1;
  }

  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear abstractQuestion = (AbstractGear) o;
      return abstractQuestion.comparePotions(this);
    }
    return 1;
  }
}
