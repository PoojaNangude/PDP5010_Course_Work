package treasure;

/**
 * This class represents a Sapphire.
 */
public class Sapphire extends AbstractTreasure {

  /**
   * Constructs a Sapphire object.
   */
  public Sapphire() {
    super(TreasureCatalog.SAPPHIRE, 1);
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
