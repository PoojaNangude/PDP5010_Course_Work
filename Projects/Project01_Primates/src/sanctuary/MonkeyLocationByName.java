package sanctuary;

import java.util.Comparator;

/**
 * MonkeyLocationByName class represented as monkey name and location of the monkey
 * in the sanctuary.
 */
public class MonkeyLocationByName {
  private String monkeyName;
  private String locationOfMonkey;

  /**
   * Constructs an MonkeyLocationByName in terms of species name and location.
   *
   * @param monkeyName   String
   * @param locationOfMonkey  String
   */
  public MonkeyLocationByName(String monkeyName, String locationOfMonkey) {
    this.monkeyName = monkeyName;
    this.locationOfMonkey = locationOfMonkey;
  }

  public String getMonkeyName() {
    return this.monkeyName;
  }

  public String getLocationOfMonkey() {
    return this.locationOfMonkey;
  }

  public static Comparator<MonkeyLocationByName> MONKEY_NAME_COMPARATOR = new
          Comparator<MonkeyLocationByName>() {
    @Override
    public int compare(MonkeyLocationByName o1, MonkeyLocationByName o2) {
      return (o1.getMonkeyName().toUpperCase()).compareTo(o2.getMonkeyName().toUpperCase());
    }
  };
}