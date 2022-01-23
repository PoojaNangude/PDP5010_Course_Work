import org.junit.Test;

import java.io.StringReader;

import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import game.GameController;
import game.IGame;

import static org.junit.Assert.assertEquals;

/**
 * Test case when player escapes an injured monster.
 */
public class ControllerEscapeMonster {

  @Test
  public void testEscapeMonster() {
    StringReader input = new StringReader(" m S m E s 1 N m N W q ");
    Appendable gameLog = new StringBuilder();
    IGame m1 = new Game(WrappingType.WRAPPING, 6, 6,
            13, 30, 40);
    GameController controller = new GameConsoleController(input, gameLog);
    controller.playGame(m1);
    assertEquals("WELCOME TO THE GAME!!\n"
            + "----------------------------------------------------"
            + "-------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 3 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), EAST(E), WEST(W), \n"
            + "Where do you want to go? \n"
            + "Player moved to desired location\n"
            + "--------------------------------------------------------"
            + "---------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 3 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), EAST(E), WEST(W), \n"
            + "Where do you want to go? \n"
            + "Player moved to desired location\n"
            + "-----------------------------------------------------"
            + "------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 3 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, WEST, EAST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "How many caves to be travelled ?\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "You hear a great howl in the distance. You injured an Otyugh!!\n"
            + "--------------------------------------------------------"
            + "---------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 2 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, WEST, EAST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), WEST(W), EAST(E), \n"
            + "Where do you want to go? \n"
            + "Player moved to desired location\n"
            + "You see an injured monster here\n"
            + "You try to run away\n"
            + "The monster is injured and you can escape. Decide a direction to move."
            + "Doors lead to NORTH(N), SOUTH(S), WEST(W), EAST(E), \n"
            + "Where do you want to go? \n"
            + "-----------------------------------------------------------"
            + "------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 2 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Player quit the game!!!\n", gameLog.toString());
  }
}
