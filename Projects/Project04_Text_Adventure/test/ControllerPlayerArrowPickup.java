import org.junit.Test;

import java.io.StringReader;

import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import game.GameController;
import game.IGame;

import static org.junit.Assert.assertEquals;

/**
 * Test case to check if the player is able to pick up arrows.
 */
public class ControllerPlayerArrowPickup {

  @Test
  public void testCollectArrow() {
    StringReader input = new StringReader("p A p A q");
    Appendable gameLog = new StringBuilder();
    IGame m1 = new Game(WrappingType.WRAPPING, 6, 6,
            13, 30, 40);
    GameController controller = new GameConsoleController(input, gameLog);
    controller.playGame(m1);
    assertEquals("WELCOME TO THE GAME!!\n"
            + "------------------------------------------------"
            + "-----------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 3 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "What do you want to collect (arrows(A)/ treasure(T))\n"
            + "Arrows collected\n"
            + "---------------------------------------------------"
            + "--------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 5 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "What do you want to collect (arrows(A)/ treasure(T))\n"
            + "No arrows present to collect\n"
            + "---------------------------------------------------"
            + "--------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 5 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Player quit the game!!!\n", gameLog.toString());
  }
}
