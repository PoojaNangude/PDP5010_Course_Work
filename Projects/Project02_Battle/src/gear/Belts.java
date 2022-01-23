package gear;

/**
 * This class represents a Belt. It offers all the operations mandated by the
 * Gear interface.
 */
public class Belts extends AbstractGear {
  /**
   * Enum type representing the sizes of Belts allowed in the game.
   */
  public enum size {
    SMALL,
    MEDIUM,
    LARGE
  }

  protected size beltSize;
  protected int beltUnitSize;

  /**
   * Construct a Belt object.
   * When user uses small belt constitution increases by 1 and dexterity increases by 1.
   * When user uses medium belt constitution increases by 2 and dexterity increases by 2.
   * When user uses large belt constitution increases by 3 and dexterity increases by 2
   */
  public Belts(size beltSize) {
    super(GearCatalog.BELTS);
    this.beltSize = beltSize;
    if (this.beltSize == size.SMALL) {
      this.effectOnConstitution = 1;
      this.effectOnDexterity = 1;
      this.beltUnitSize = 1;
    } else if (this.beltSize == size.MEDIUM) {
      this.effectOnConstitution = 2;
      this.effectOnDexterity = 2;
      this.beltUnitSize = 2;
    } else if (this.beltSize == size.LARGE) {
      this.effectOnConstitution = 3;
      this.effectOnDexterity = 3;
      this.beltUnitSize = 4;
    }
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
  public int getBeltUnitSize() {
    return this.beltUnitSize;
  }

  @Override
  protected boolean equalsBelts(Belts other) {
    return this.gearName.equals(other.gearName)
            && this.effectOnStrength == other.effectOnStrength
            && this.effectOnConstitution == other.effectOnConstitution
            && this.effectOnDexterity == other.effectOnDexterity
            && this.effectOnCharisma == other.effectOnCharisma
            && this.beltSize == other.beltSize;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear abstractGear = (AbstractGear) other;
      return abstractGear.equalsBelts(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.effectOnCharisma + this.effectOnConstitution
            + this.effectOnDexterity + this.effectOnStrength + this.beltUnitSize);
  }

  protected int compareHeadGear(HeadGear other) {
    return -1;
  }

  protected int comparePotions(Potions other) {
    return -1;
  }

  protected int compareBelts(Belts other) {
    if (this.beltSize == other.beltSize) {
      return 0;
    } else if (this.beltSize == size.SMALL && other.beltSize == size.MEDIUM) {
      return 1;
    } else if (this.beltSize == size.SMALL && other.beltSize == size.LARGE) {
      return 1;
    } else if (this.beltSize == size.MEDIUM && other.beltSize == size.SMALL) {
      return -1;
    } else if (this.beltSize == size.MEDIUM && other.beltSize == size.LARGE) {
      return 1;
    } else if (this.beltSize == size.LARGE && other.beltSize == size.SMALL) {
      return -1;
    } else if (this.beltSize == size.LARGE && other.beltSize == size.MEDIUM) {
      return -1;
    }
    return 0; //think about sort by size later
  }

  protected int compareFootware(Footware other) {
    return 1;
  }

  @Override
  public int compareTo(Gear o) {
    if (o instanceof AbstractGear) {
      AbstractGear abstractQuestion = (AbstractGear) o;
      return abstractQuestion.compareBelts(this);
    }
    return 1;
  }
}
