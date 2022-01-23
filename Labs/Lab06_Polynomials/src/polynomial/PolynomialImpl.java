package polynomial;

import java.util.Scanner;

/**
 * This is the implementation for a polynomial and the operations on polynomial.
 */
public class PolynomialImpl implements Polynomial {
  private ListOfTerms polynomial;


  /**
   * Constructor for PolynomialImpl for creating polynomial inpunt in string format.
   *
   * @param inputPolynomial takes a polynomial as input, in the form of a string.
   */
  public PolynomialImpl(String inputPolynomial)
          throws IllegalArgumentException {
    if (inputPolynomial == null || inputPolynomial.trim().equals("")) {
      throw new IllegalArgumentException("Input cannot be null or an empty string");
    }
    ListOfTerms poly = new TermEmptyNode();
    Scanner scanner = new Scanner(inputPolynomial);
    while (scanner.hasNext()) {
      String[] term = scanner.next().split("x\\^");
      if (term.length == 0) {
        throw new IllegalArgumentException("Invalid input");
      } else if (term.length == 1) {
        int coefficient = getIntegerValue(term[0]);
        poly = poly.addTerm(coefficient, 0);
      } else {
        int coefficient = getIntegerValue(term[0]);
        int power = getIntegerValue(term[1]);
        poly = poly.addTerm(coefficient, power);
      }
    }
    this.polynomial = poly;
  }

  /**
   * Default Constructor for the PolynomialImpl for creating a polynomial with no terms.
   */
  public PolynomialImpl() {
    this.polynomial = new TermEmptyNode();
  }

  private int getIntegerValue(String subStr) {
    if (!subStr.matches("^[+-]?\\d+$")) {
      throw new IllegalArgumentException("Invalid input");
    }
    int sign = getSign(subStr.charAt(0));
    int value = 0;
    if (sign == 0) {
      value = Integer.parseInt(subStr);
    } else {
      value = Integer.parseInt(subStr.substring(1)) * sign;
    }
    return value;
  }

  private int getSign(Character ch) {
    if (ch.equals('+')) {
      return 1;
    }
    if (ch.equals('-')) {
      return -1;
    }
    return 0;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException();
    }
    if (!(other instanceof Polynomial)) {
      throw new IllegalArgumentException();
    }
    Polynomial polynomial = new PolynomialImpl();
    polynomial = this.addPolynomialTerms(polynomial, this);
    polynomial = this.addPolynomialTerms(polynomial, other);
    return polynomial;
  }

  private Polynomial addPolynomialTerms(Polynomial polynomial1, Polynomial polynomial2) {
    for (int i = 0; i <= polynomial2.getDegree(); i++) {
      if (polynomial2.getCoefficient(i) != 0) {
        polynomial1.addTerm(polynomial2.getCoefficient(i), i);
      }
    }
    return polynomial1;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should not have a negative value");
    }
    polynomial = polynomial.addTerm(coefficient, power);
  }

  @Override
  public boolean isSame(Polynomial other) {
    if (!(other instanceof PolynomialImpl)) {
      return false;
    }
    if (this.getDegree() != other.getDegree()) {
      return false;
    }
    boolean isSame = polynomial.isSame(((PolynomialImpl) other).polynomial);
    return isSame;
  }

  @Override
  public double evaluate(double x) {
    return polynomial.evaluate(x, 0);
  }

  @Override
  public int getCoefficient(int power) {
    return polynomial.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return polynomial.getDegree();
  }

  @Override
  public String toString() {
    String polynomial = this.polynomial.toString();
    if (polynomial.charAt(0) == '+') {
      return polynomial.substring(1);
    }
    return polynomial;
  }
}
