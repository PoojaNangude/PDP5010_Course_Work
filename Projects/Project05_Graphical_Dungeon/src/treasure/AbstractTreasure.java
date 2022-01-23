package treasure;

/**
 * An abstract class that contains all of the code that is shared by all types of treasures.
 */
public abstract class AbstractTreasure implements Treasure {
  protected TreasureCatalog treasureType;
  protected int weight;

  /**
   * Constructor to create a treasure based on the treasure type.
   *
   * @param treasureType of type enum defined in TreasureCatalog
   * @param weight of type int which is the weight the particular treasure carries
   */
  public AbstractTreasure(TreasureCatalog treasureType, int weight) {
    this.treasureType = treasureType;
    this.weight = weight;
  }
}
