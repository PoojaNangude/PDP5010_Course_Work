import org.junit.Test;

import java.io.StringReader;

import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import game.GameController;
import game.IGame;

import static org.junit.Assert.assertEquals;

/**
 * To test of player is able to use the shoot arrow functionality properly.
 */
public class ControllerShootArrow {

  @Test
  public void testShootArrow() {
    StringReader input = new StringReader("s a 1 z N s 3 S q");
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
            + "How many caves to be travelled ?\n"
            + "Invalid input\n"
            + "How many caves to be travelled ?\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "Invalid value entered!\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "You hear a great howl in the distance. You injured an Otyugh!!\n"
            + "--------------------------------------------------"
            + "---------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 2 arrows\n"
            + "Treasure collected by player till now: DIAMOND:0 RUBY:0 SAPPHIRE:0 \n"
            + "\n"
            + "You find 2 arrows here\n"
            + "You find 1 DIAMOND 1 RUBY here\n"
            + "The player senses a terribly pungent odour\n"
            + "Doors lead to NORTH, SOUTH, EAST, WEST, \n"
            + "Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n"
            + "How many caves to be travelled ?\n"
            + "Enter the direction in which you want to shoot the arrow(N-S-E-W): \n"
            + "You shot the arrow into the darkness\n"
            + "----------------------------------------------------"
            + "-------------------------------------\n"
            + "You are in a CAVE\n"
            + "You are equipped with 1 arrows\n"
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
