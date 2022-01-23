package gear;

/**
 * This class represents a HeadGear. It offers all the operations mandated by the
 * Gear interface.
 */
public class HeadGear extends AbstractGear {

  /**
   * Construct a HeadGear object.
   * The players' constitution will increase 2 if player uses a head gear.
   */
  public HeadGear() {
    super(GearCatalog.HEAD_GEAR);
    this.effectOnConstitution = 2;
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
  protected boolean equalsHeadGear(HeadGear other) {
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
      return abstractGear.equalsHeadGear(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.effectOnCharisma + this.effectOnConstitution
            + this.effectOnDexterity + this.effectOnStrength);
  }

  @Override
  protected int compareHeadGear(HeadGear other) {
    return 0;
  }

  @Override
  protected int comparePotions(Potions other) {
    return 1;
  }

  @Override
  protected int compareBelts(Belts other) {
    return 1;
  }

  @Override
  protected int compareFootware(Footware other) {
    return 1;
  }

  @Override
  public int compareTo(Gear o) {
    if (o instanceof  AbstractGear) {
      AbstractGear abstractGear = (AbstractGear) o;
      return abstractGear.compareHeadGear(this);
    }
    return 1;
  }
}
