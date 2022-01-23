package gear;

/**
 * An abstract class that contains all of the code that is shared by all types
 * of Gears.
 */
public abstract class AbstractGear implements Gear {
  protected GearCatalog gearName;
  protected int effectOnStrength;
  protected int effectOnConstitution;
  protected int effectOnDexterity;
  protected int effectOnCharisma;

  protected AbstractGear(GearCatalog gearName) {
    this.gearName = gearName;
    this.effectOnStrength = 0;
    this.effectOnConstitution = 0;
    this.effectOnDexterity = 0;
    this.effectOnCharisma = 0;
  }

  protected boolean equalsHeadGear(HeadGear other) {
    return false;
  }

  protected boolean equalsPotion(Potions other) {
    return false;
  }

  protected boolean equalsFootware(Footware footware) {
    return false;
  }

  protected boolean equalsBelts(Belts other) {
    return false;
  }

  protected int compareHeadGear(HeadGear other) {
    return 1;
  }

  protected int comparePotions(Potions other) {
    return 1;
  }

  protected int compareBelts(Belts other) {
    return 1;
  }

  protected int compareFootware(Footware other) {
    return 1;
  }

  public int getBeltUnitSize() {
    return 0;
  }
}
