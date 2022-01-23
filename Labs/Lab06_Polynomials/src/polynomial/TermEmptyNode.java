package polynomial;

/**
 * This class represents an empty node in a
 * polynomial.
 */
public class TermEmptyNode implements ListOfTerms {

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public double evaluate(double x,double result) {
    return result;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public ListOfTerms addTerm(int coefficient, int power) {
    return new TermElementNode(new Term(coefficient, power), this);
  }

  @Override
  public boolean isSame(ListOfTerms other) {
    return true;
  }

  @Override
  public String toString() {
    return "0";
  }
}
