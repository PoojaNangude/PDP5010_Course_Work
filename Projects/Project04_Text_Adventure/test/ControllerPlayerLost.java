import org.junit.Test;

import java.io.StringReader;

import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import game.GameController;
import game.IGame;

import static org.junit.Assert.assertEquals;

/**
 * To test if the controller works as expected when player makes moves and actions
 * and eventually looses by getting eaten up by a monster.
 */
public class ControllerPlayerLost {

  @Test
  public void testPlayerLost() {
    StringReader input = new StringReader("p T s 2 E s 2 E m S m E m N s 1 E s 1 E m "
            + "E m S m E m E ");
    Appendable gameLog = new StringBuilder();
    IGame m1 = new Game(WrappingType.WRAPPING, 6, 6, 13,
            30, 40);
    GameController controller = new GameConsoleController(input, gameLog);
    controller.playGame(m1);
    assertEquals("WELCOME TO THE GAME!!\n"
            + "-----------------------------------------------------"
            + "------------------------------------\n"
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
            + "Treasure collected\n"
            + "---------------------------------------------"
            + "--------------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 3 arrows\n"
            + "Treasure collected by player till now: DIAMOND:1 RUBY:1 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "How many caves to be travelled ?\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "You hear a great howl in the distance. You injured an Otyugh!!\n"
            + "----------------------------------------------"
            + "-------------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 2 arrows\n"
            + "Treasure collected by player till now: DIAMOND:1 RUBY:1 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "How many caves to be travelled ?\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "Wow!! You just killed a monster.\n"
            + "------------------------------------------"
            + "-----------------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 1 arrows\n"
            + "Treasure collected by player till now: DIAMOND:1 RUBY:1 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), EAST(E), WEST(W), \n"
            + "Where do you want to go? \n"
            + "Player moved to desired location\n"
            + "--------------------------------------------"
            + "---------------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 1 arrows\n"
            + "Treasure collected by player till now: DIAMOND:1 RUBY:1 SAPPHIRE:0 \n"
            + "\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), EAST(E), WEST(W), \n"
            + "Where do you want to go? \n"
            + "Player moved to desired location\n"
            + "--------------------------------------------"
            + "---------------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 1 arrows\n"
            + "Treasure collected by player till now: DIAMOND:1 RUBY:1 SAPPHIRE:0 \n"
            + "\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, WEST, EAST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "Doors lead to NORTH(N), SOUTH(S), WEST(W), EAST(E), \n"
            + "Where do you want to go? \n"
            + "-------------------------------------------------"
            + "----------------------------------------\n"
            + "Game over. You were eaten by a monster!!!\n", gameLog.toString());
  }
}
