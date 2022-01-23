import org.junit.Test;

import java.io.StringReader;

import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import game.GameController;
import game.IGame;

/**
 * Testing when something goes wrong with the Appendable.
 * Here we are passing it a mock of an Appendable that always fails
 */

public class ControllerFailingAppendable {

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    StringReader input = new StringReader("m w s 1 S s 1 S m s");
    Appendable gameLog = new FailingAppendable();
    IGame m1 = new Game(WrappingType.WRAPPING, 6, 6, 13,
            30, 40);
    GameController controller = new GameConsoleController(input, gameLog);
    controller.playGame(m1);
  }
}
