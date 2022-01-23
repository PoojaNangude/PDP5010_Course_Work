package sanctuary;

/**
 * Enclosure class represented as unique id of monkey, monkey name, monkey species,
 * sex, size, weight, age, favourite food and medical status of the monkey.
 */
public class Monkey {
  public static int MONKEY_ID = 1;

  private int uniqueId;
  private String name;
  private String species;
  private char sex;
  private int size;
  private int weight;
  private int age;
  private MonkeyFavFood monkeyFavFood;
  private MonkeyMedicalStatus medicalStatus;

  /**
   * Constructs a Monkey and assigns every monkey object with default values.
   */
  public Monkey() {
    this.name = "";
    this.species = "";
    this.sex = '\u0000';
    this.size = 0;
    this.weight = 0;
    this.age = 0;
    this.monkeyFavFood = null;
    this.medicalStatus = null;
  }

  /**
   * Constructs a Monkey in terms of its unique id, name, species, sex, size, weight, age,
   * favourite food and medical status of the monkey.
   *
   * @param name          in String
   * @param species       in String
   * @param sex           in char
   * @param size          in centimeters
   * @param weight        in kgs
   * @param age           in integer
   * @param monkeyFavFood enum MonkeyFavFood
   * @param medicalStatus enum MonkeyMedicalStatus
   * @throws IllegalArgumentException for any values that are invalid
   */
  public void setMonkey(String name, String species, char sex, int size, int weight, int age,
                        MonkeyFavFood monkeyFavFood, MonkeyMedicalStatus medicalStatus) {
    this.uniqueId = MONKEY_ID++;
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.age = age;
    this.monkeyFavFood = monkeyFavFood;
    this.medicalStatus = medicalStatus;
  }

  public int getUniqueId() {
    return this.uniqueId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpecies() {
    return this.species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public char getSex() {
    return this.sex;
  }

  public void setSex(char sex) {
    this.sex = sex;
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public MonkeyFavFood getMonkeyFavFood() {
    return this.monkeyFavFood;
  }

  public void setMonkeyFavFood(MonkeyFavFood monkeyFavFood) {
    this.monkeyFavFood = monkeyFavFood;
  }

  public MonkeyMedicalStatus getMedicalStatus() {
    return this.medicalStatus;
  }

  public void setMedicalStatus(MonkeyMedicalStatus medicalStatus) {
    this.medicalStatus = medicalStatus;
  }

  /**
   * Returns the monkey size based on the size given in cm.
   *
   * @return monkey size of type enum MonkeySize
   */
  public MonkeySize getMonkeySize() {
    int size = getSize();
    MonkeySize monkeySize = null;
    if (size < 10) {
      monkeySize = MonkeySize.SMALL;
    } else if (size >= 10 && size < 20) {
      monkeySize = MonkeySize.MEDIUM;
    } else if (size >= 20) {
      monkeySize = MonkeySize.LARGE;
    }
    return monkeySize;
  }

  /**
   * Returns the quantity of food required by the monkey based on the monkey size.
   *
   * @return the food quantity
   */
  public int getFoodQuantityRequired() {
    MonkeySize size = getMonkeySize();
    if (size == MonkeySize.SMALL) {
      return 100;
    } else if (size == MonkeySize.MEDIUM) {
      return 250;
    } else if (size == MonkeySize.LARGE) {
      return 500;
    }
    return 0;
  }

  /**
   * Returns the area required by the monkey based on the size pf the monkey.
   *
   * @return the area required in meter square
   */
  public int areaRequiredByMonkey(MonkeySize monkeySize) {
    int areaRequired = 0;
    if (monkeySize == MonkeySize.SMALL) {
      areaRequired = 1;
    } else if (monkeySize == MonkeySize.MEDIUM) {
      areaRequired = 5;
    } else if (monkeySize == MonkeySize.LARGE) {
      areaRequired = 10;
    }
    return areaRequired;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enclosure)) {
      return false;
    }
    Monkey that = (Monkey) o;
    return this.getName() == that.getName()
            && this.getSpecies() == that.getSpecies()
            && this.getSex() == that.getSex()
            && this.getSize() == that.getSize()
            && this.getWeight() == that.getWeight()
            && this.getAge() == that.getAge()
            && this.getMonkeyFavFood() == that.getMonkeyFavFood()
            && this.getMedicalStatus() == that.getMedicalStatus();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.size + this.weight + this.age);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    return sb.append(this.getName()).append(this.getSpecies())
            .append(this.getSex()).append(this.getMonkeyFavFood()).toString();
  }
}
