package sanctuary;

/**
 * EnclosureSign class represented as air temperature, dew point, wind speed and total rain.
 */
public class EnclosureSign {
  private String name;
  private char sex;
  private MonkeyFavFood monkeyFavFood;

  /**
   * Constructs an EnclosureSign in terms of species name, species sex and the favourite food.
   *
   * @param name String
   * @param sex character
   * @param monkeyFavFood enum MonkeyFavFood
   */
  public EnclosureSign(String name, char sex, MonkeyFavFood monkeyFavFood) throws
          IllegalArgumentException {
    if (name == null || name.equals("") || name.trim().equals("") || (sex != 'm' && sex != 'f')) {
      throw new IllegalArgumentException("Invalid Values Provided!");
    }
    this.name = name;
    this.sex = sex;
    this.monkeyFavFood = monkeyFavFood;
  }

  public String getName() {
    return this.name;
  }

  public char getSex() {
    return this.sex;
  }

  public MonkeyFavFood getMonkeyFavFood() {
    return this.monkeyFavFood;
  }
}
