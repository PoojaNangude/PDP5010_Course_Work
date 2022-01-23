import org.junit.Before;
import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is test class for testing all the polynomial operations.
 */
public class PolynomialTest {
  private Polynomial polynomial1;
  private Polynomial polynomial2;
  private Polynomial polynomial3;
  private Polynomial polynomial4;
  private Polynomial polynomial5;
  private Polynomial polynomial6;
  private Polynomial polynomial7;
  private Polynomial polynomial8;
  private Polynomial polynomial9;
  private Polynomial polynomial10;
  private Polynomial polynomial11;
  private Polynomial polynomial12;
  private Polynomial polynomial13;
  private Polynomial polynomial14;
  private Polynomial polynomial15;
  private Polynomial polynomial16;
  private Polynomial polynomial17;

  @Before
  public void setUp() {
    polynomial1 = new PolynomialImpl("4x^3 -3x^2 +2x^1 -8");
    polynomial2 = new PolynomialImpl("6x^4 -1x^3 -2x^2 +9x^1 -4");
    polynomial3 = new PolynomialImpl("4x^3 -3x^2 +2x^1 -8");
    polynomial4 = new PolynomialImpl("8x^3 -6x^2 +4x^1 -16");
    polynomial5 = new PolynomialImpl("6x^4 +3x^3 -5x^2 +11x^1 -12");
    polynomial6 = new PolynomialImpl("6x^4 +3x^3 -5x^2 +11x^1 -122");
    polynomial7 = new PolynomialImpl();
    polynomial8 = new PolynomialImpl("102");
    polynomial9 = new PolynomialImpl("+6x^4 +3x^3 -5x^2 +11x^1 -122");
    polynomial10 = new PolynomialImpl("4x^3 +3x^2 +2x^1 -8");
    polynomial11 = new PolynomialImpl("10x^6 -8");
    polynomial12 = new PolynomialImpl("-3x^2 +2x^1 -8 +4x^3");
    polynomial13 = new PolynomialImpl("6x^4 -1x^3 -2x^2 +9x^1 -4 -2x^3");
    polynomial14 = new PolynomialImpl("4x^3 -3x^2 +2x^1 -8");
    polynomial15 = new PolynomialImpl("4x^6 -3x^5 +2x^4");
    polynomial16 = new PolynomialImpl();
    polynomial17 = new PolynomialImpl("4x^3 +3x^2 +2x^1 -8 -2x^2 +1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPolynomialInput1() {
    new PolynomialImpl("6x^4 -x^3 -2x^2 +9x^1 -4");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPolynomialInput2() {
    new PolynomialImpl("6x^-4 -x^3 -2x^2 +9x^1 -4");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPolynomialInput3() {
    new PolynomialImpl("6x^-4 -x^3 -2x^2 + 2.5x^1 -4");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPolynomialInputWithNull() {
    new PolynomialImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyStringInput1() {
    new PolynomialImpl("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyStringInput2() {
    new PolynomialImpl("    ");
  }

  @Test
  public void testIsSame() {
    assertTrue(polynomial1.isSame(polynomial3));
    assertTrue(polynomial6.isSame(polynomial9));
    assertFalse(polynomial1.isSame(polynomial2));
    assertFalse(polynomial14.isSame(polynomial15));

    assertTrue(polynomial7.isSame(polynomial16));
    assertFalse(polynomial1.isSame(polynomial7));

  }

  @Test
  public void testToString() {
    assertEquals("6x^4 +3x^3 -5x^2 +11x^1 -122", polynomial6.toString());
    assertEquals("6x^4 -1x^3 -2x^2 +9x^1 -4", polynomial2.toString());
    assertEquals("4x^3 +1x^2 +2x^1 -7", polynomial17.toString());
  }

  @Test
  public void testAdd() {
    assertEquals("8x^3 -6x^2 +4x^1 -16", polynomial1.add(polynomial3).toString());
    assertEquals("8x^3 -6x^2 +4x^1 -16", polynomial1.add(polynomial12).toString());

    assertEquals("6x^4 +3x^3 -5x^2 +11x^1 -12", polynomial1.add(polynomial2).toString());

    assertEquals("8x^3 +4x^1 -16", polynomial1.add(polynomial10).toString());

    assertEquals("4x^6 -3x^5 +2x^4 +4x^3 -3x^2 +2x^1 -8",
            polynomial14.add(polynomial15).toString());

    assertEquals("12x^3 -9x^2 +6x^1 -24", polynomial1.add(polynomial4).toString());

    assertEquals("4x^3 -3x^2 +2x^1 -8", polynomial1.add(polynomial7).toString());
    assertEquals("0", polynomial7.add(polynomial16).toString());

    assertNotEquals("6x^4 +3x^3 -5x^2 +11x^1 -122", polynomial1.add(polynomial2).toString());
  }

  @Test
  public void testNoArgumentConstructor() {
    assertEquals("0", polynomial7.toString());
    assertEquals(0, polynomial7.getDegree());
  }

  @Test
  public void testPolynomialConstructor() {
    assertEquals("4x^3 -3x^2 +2x^1 -8", polynomial1.toString());
    assertEquals("6x^4 +3x^3 -5x^2 +11x^1 -122", polynomial9.toString());
    assertEquals("6x^4 -3x^3 -2x^2 +9x^1 -4", polynomial13.toString());
  }

  @Test
  public void testConstantPolynomial() {
    assertEquals("102", polynomial8.toString());
    assertEquals(0, polynomial8.getDegree());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddTermInput() {
    polynomial9.addTerm(2,-2);
  }

  @Test
  public void testAddTerm() {
    polynomial4.addTerm(4, 2);
    assertEquals("8x^3 -2x^2 +4x^1 -16", polynomial4.toString());

    polynomial15.addTerm(6, 7);
    assertEquals("6x^7 +4x^6 -3x^5 +2x^4", polynomial15.toString());

    polynomial1.addTerm(-2, 1);
    assertEquals("4x^3 -3x^2 -8", polynomial1.toString());

    polynomial2.addTerm(4,0);
    assertEquals("6x^4 -1x^3 -2x^2 +9x^1", polynomial2.toString());

    polynomial10.addTerm(10, 0);
    assertEquals("4x^3 +3x^2 +2x^1 +2", polynomial10.toString());
  }

  @Test
  public void testEvaluate() {
    assertEquals(-5, polynomial1.evaluate(1), 0.2);
    assertEquals(224.75, polynomial2.evaluate(2.5), 0.2);
    assertEquals(-17, polynomial1.evaluate(-1), 0.2);
  }

  @Test
  public void testDegree() {
    assertEquals(4, polynomial5.getDegree());
    assertEquals(6, polynomial11.getDegree());
    assertEquals(0, polynomial7.getDegree());
    assertEquals(0, polynomial8.getDegree());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(-5, polynomial6.getCoefficient(2));
    assertEquals(0, polynomial11.getCoefficient(3));
  }
}