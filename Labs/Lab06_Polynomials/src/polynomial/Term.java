package polynomial;

/**
 * This class represents a term in a polynomial.
 */
public class Term {

  private final int coefficient;
  private final int power;

  /**
   * Construct a Polynomial term object.
   *
   * @param coefficient the coefficient of the polynomial term.
   * @param power the power of the polynomial term.
   */
  public Term(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("The power should not be negative");
    }
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Get the coefficient of the polynomial.
   */
  public int getCoefficient() {
    return coefficient;
  }

  /**
   * Get the power of the term.
   */
  public int getPower() {
    return power;
  }

  @Override
  public String toString() {
    if (power == 0) {
      if (coefficient > 0) {
        return String.format("+%d", coefficient);
      }
      return String.valueOf(coefficient);
    }
    if (coefficient > 0) {
      return String.format("+%dx^%d", coefficient, power);
    }
    return String.format("%dx^%d", coefficient, power);
  }
}
