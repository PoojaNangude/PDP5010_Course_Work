package treasure;

/**
 * This class represents a Diamond.
 */
public class Diamond extends AbstractTreasure {

  /**
   * Constructs a Diamond object.
   */
  public Diamond() {
    super(TreasureCatalog.DIAMOND, 10);
  }

  @Override
  public TreasureCatalog getTreasureType() {
    return this.treasureType;
  }

  @Override
  public int getWeight() {
    return this.weight;
  }
}
