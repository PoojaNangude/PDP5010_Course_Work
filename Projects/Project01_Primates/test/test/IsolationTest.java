package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.Isolation;
import sanctuary.Monkey;
import sanctuary.MonkeyFavFood;
import sanctuary.MonkeyMedicalStatus;

import static org.junit.Assert.assertTrue;

/**
 * ShoppingList represented as the isolation id and monkey which is houses the isolation.
 */
public class IsolationTest {
  private Isolation isolation1;

  @Before
  public void setUp() throws Exception {
    isolation1 = isol();

    Monkey monkey = new Monkey();
    monkey.setMonkey("Giggles", "saki",'m', 7,5,2,MonkeyFavFood.EGGS,MonkeyMedicalStatus.READY);
    isolation1.setMonkey(monkey);
  }

  protected Isolation isol() {
    return new Isolation();
  }

  @Test
  public void testGetMonkey() {
    assertTrue(isolation1.getMonkey().getName().equals("Giggles"));
  }
}