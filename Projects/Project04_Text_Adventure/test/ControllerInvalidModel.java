import org.junit.Test;

import java.io.StringReader;

import game.GameConsoleController;
import game.GameController;
import game.IGame;

/**
 * Test to check if IllegalArgumentException is thrown if invalid model is passed to constructor.
 */
public class ControllerInvalidModel {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("m S m N s 3 e q");
    Appendable gameLog = new StringBuilder();
    IGame m = null;
    GameController controller = new GameConsoleController(input, gameLog);
    controller.playGame(m);
  }
}
