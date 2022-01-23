package polynomial;


/**
 * This represents a non-empty node of the polynomial.
 */
public class TermElementNode implements ListOfTerms {
  private final Term term;
  private ListOfTerms rest;

  /**
   * Constructor for TermElementNode.
   *
   * @param term the element at this node.
   * @param rest the rest of the list
   */
  public TermElementNode(Term term, ListOfTerms rest) {
    this.term = term;
    this.rest = rest;
  }

  @Override
  public int getDegree() {
    if (this.term.getPower() >= rest.getDegree()) {
      return this.term.getPower();
    } else {
      return rest.getDegree();
    }
  }

  @Override
  public double evaluate(double x,double result) {
    result = result + (term.getCoefficient() * Math.pow(x, term.getPower()));
    return rest.evaluate(x,result);
  }

  @Override
  public int getCoefficient(int power) {
    if (power == this.term.getPower()) {
      return this.term.getCoefficient();
    }
    return rest.getCoefficient(power);
  }

  @Override
  public ListOfTerms addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative");
    }
    if (power == term.getPower()) {
      int val = term.getCoefficient() + coefficient;
      if (val == 0) {
        return rest;
      }
      return new TermElementNode(new Term(val, term.getPower()), rest);
    }
    if (power > term.getPower()) {
      return new TermElementNode(new Term(coefficient, power), this);
    }
    this.rest = this.rest.addTerm(coefficient, power);
    return this;
  }

  @Override
  public boolean isSame(ListOfTerms other) {
    if (this.getCoefficient(this.getDegree()) != other.getCoefficient(other.getDegree())) {
      return false;
    } else {
      this.rest.isSame(other);
    }
    return true;
  }

  @Override
  public String toString() {
    String singleTerm = term.toString();
    String restTermList = rest.toString();
    if (restTermList.equals("0")) {
      return singleTerm;
    } else {
      return singleTerm + " " + restTermList;
    }
  }
}
