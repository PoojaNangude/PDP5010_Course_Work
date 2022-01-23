package treasure;

/**
 * This class represents a Ruby.
 */
public class Ruby extends AbstractTreasure {

  /**
   * Constructs a Ruby object.
   */
  public Ruby() {
    super(TreasureCatalog.RUBY, 5);
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
