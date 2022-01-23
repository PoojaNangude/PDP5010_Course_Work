package treasure;

/**
 * Treasure interface that contains all operations requires for all types of treasures supported.
 */
public interface Treasure {

  /**
   * Function to get the treasure type of the single treasure.
   */
  TreasureCatalog getTreasureType();

  /**
   * Function to get the weight of the single treasure.
   */
  int getWeight();
}
