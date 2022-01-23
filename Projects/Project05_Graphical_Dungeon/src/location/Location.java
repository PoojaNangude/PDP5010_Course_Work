package location;

/**
 * Location class which represent a single co-ordinate or location
 * in terms of x and y co-ordinate value.
 */
public class Location {
  private int x;
  private int y;

  /**
   * Constructs a location which will have both x and y co-oridnate a value.
   *
   * @param x of type integer
   * @param y of type integer
   * @throws IllegalArgumentException when negative co-ordinate values are passed
   */
  public Location(int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid values of location");
    }
    this.x = x;
    this.y = y;
  }

  public int getX() {
    int xCoordinate = this.x;
    return xCoordinate;
  }

  public int getY() {
    int yCoordinate = this.y;
    return yCoordinate;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Location)) {
      return false;
    }
    Location that = (Location) other;
    return this.getX() == that.getX()
            && this.getY() == that.getY();
  }

  @Override
  public String toString() {
    return String.format("(x:%d, y:%d)", this.x, this.y);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.x + this.y);
  }
}
