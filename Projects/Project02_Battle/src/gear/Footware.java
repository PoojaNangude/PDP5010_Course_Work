package gear;

/**
 * This class represents a Footware. It offers all the operations mandated by the
 * Gear interface.
 */
public class Footware extends AbstractGear {
  /**
   * Construct a Belt object.
   * The players' dexterity increases by 2 when user uses a potion.
   */
  public Footware() {
    super(GearCatalog.FOOT_WARE);
    this.effectOnDexterity = 2;
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
  protected boolean equalsFootware(Footware other) {
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
      return abstractGear.equalsFootware(this);
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
    return -1;
  }

  protected int compareBelts(Belts other) {
    return -1;
  }

  protected int compareFootware(Footware other) {
    return 0;
  }

  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear abstractQuestion = (AbstractGear) o;
      return abstractQuestion.compareFootware(this);
    }
    return 1;
  }
}
