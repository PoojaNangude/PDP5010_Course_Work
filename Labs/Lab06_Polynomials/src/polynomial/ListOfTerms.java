package polynomial;

/**
 * This interface represents a list of terms which make up a polynomial.
 */
public interface ListOfTerms {

  /**
   * Evaluates the degree of the polynomial.
   */
  int getDegree();

  /**
   * Evaluates the value of this polynomial for the provided variable value.
   */
  double evaluate(double x,double result);

  /**
   * Return the coefficient of the term with the given power.
   */
  int getCoefficient(int power);

  /**
   * Add a term to the polynomial with the given coefficient and power.
   */
  ListOfTerms addTerm(int coefficient, int power);

  /**
   * To check if two polynomials are equal.
   */
  boolean isSame(ListOfTerms other);
}