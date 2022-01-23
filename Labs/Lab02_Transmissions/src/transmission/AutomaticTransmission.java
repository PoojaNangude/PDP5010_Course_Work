package transmission;

/**
 * AutomaticTransmission class represented as current speed of car and 5 speed threshold
 * for 6 different gears.
 */
public class AutomaticTransmission implements Transmission {
  final private int v1;
  final private int v2;
  final private int v3;
  final private int v4;
  final private int v5;
  private int speed;

  /**
   * Constructs a AutomaticTransmission in terms of the 5 speed thresholds and
   * the current speed of the car.
   *
   * @param v1 in miles per hour
   * @param v2 in miles per hour
   * @param v3 in miles per hour
   * @param v4 in miles per hour
   * @param v5 in miles per hour
   * @throws IllegalArgumentException if any argument is invalid.
   */
  public AutomaticTransmission(int v1, int v2, int v3, int v4, int v5)
          throws IllegalArgumentException {
    if (v1 == 0 || v1 >= v2 || v2 >= v3 || v3 >= v4 || v4 >= v5 || v1 >= v5 || v1 < 0 || v2 < 0
            || v3 < 0 || v4  < 0 || v5 < 0) {
      throw new IllegalArgumentException("Illegal Values");
    }

    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.v5 = v5;
    this.speed = 0;
  }

  @Override
  public void increaseSpeed() {
    this.speed = this.speed + 1;
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (this.speed == 0) {
      throw new IllegalStateException("Speed cannot be reduced as vehicle is already at 0 speed");
    }
    this.speed = this.speed - 1;
    if (this.speed < 0) {
      this.speed = 0;
    }
  }

  @Override
  public int getSpeed() {
    return (int) Math.round(this.speed);
  }

  @Override
  public int getGear() {
    if (getSpeed() == 0) {
      return 0;
    } else if (this.speed > 0 && this.speed < this.v1) {
      return 1;
    } else if (this.speed >= this.v1 && getSpeed() < this.v2) {
      return 2;
    } else if (this.speed >= this.v2 && this.speed < this.v3) {
      return 3;
    } else if (this.speed >= this.v3 && this.speed < this.v4) {
      return 4;
    } else if (this.speed >= this.v4 && this.speed < this.v5) {
      return 5;
    } else if (this.speed >= this.v5) {
      return 6;
    }
    return 0;
  }

  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", getSpeed(), getGear());
  }


  public int getV1() {
    return this.v1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Transmission)) {
      return false;
    }
    Transmission that = (Transmission) o;
    return this.getSpeed() == that.getSpeed()
            && this.getGear() == that.getGear();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.v1 + this.v2 + this.v3 + this.v4 + this.v5);
  }
}
