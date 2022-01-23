import org.junit.Before;
import org.junit.Test;

import polynomial.ListOfTerms;
import polynomial.Term;
import polynomial.TermElementNode;
import polynomial.TermEmptyNode;

import static org.junit.Assert.assertEquals;

/**
 * This is test class for testing all operations on list of terms.
 */
public class ListOfTermsTest {
  private ListOfTerms listOfTerms1;
  private ListOfTerms listOfTerms2;
  private ListOfTerms listOfTerms3;

  @Before
  public void setUp() {
    Term p1Term1 = new Term(4, 3);
    Term p1Term2 = new Term(-3, 2);
    Term p1Term3 = new Term(2,1);
    Term p1Term4 = new Term(-8,0);

    Term p2Term1 = new Term(6, 4);
    Term p2Term2 = new Term(-1, 3);
    Term p2Term3 = new Term(-2, 2);
    Term p2Term4 = new Term(9, 1);
    Term p2Term5 = new Term(4, 0);

    listOfTerms1 = new TermElementNode(p1Term1, new TermElementNode(p1Term2,
            new TermElementNode(p1Term3, new TermElementNode(p1Term4, new TermEmptyNode()))));

    listOfTerms2 = new TermElementNode(p2Term1, new TermElementNode(p2Term2,
            new TermElementNode(p2Term3, new TermElementNode(p2Term4,
                    new TermElementNode(p2Term5, new TermEmptyNode())))));

    listOfTerms3 = new TermEmptyNode();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTerm() {
    new Term(4, -1);
  }


  @Test
  public void testGetDegree() {
    assertEquals(4, listOfTerms2.getDegree());
    assertEquals(3, listOfTerms1.getDegree());
    assertEquals(0, listOfTerms3.getDegree());
  }

  @Test
  public void testEvaluate() {
    assertEquals(-5, listOfTerms1.evaluate(1, 0), 0.2);
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(-3, listOfTerms1.getCoefficient(2));
  }

  @Test
  public void testAddTerm() {
    assertEquals("+7x^3 -3x^2 +2x^1 -8", listOfTerms1.addTerm(3, 3).toString());
    assertEquals("+6x^4 -1x^3 -2x^2 +4", listOfTerms2.addTerm(-9, 1).toString());
  }
}