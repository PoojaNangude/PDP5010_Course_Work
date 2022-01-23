import org.junit.Test;

import java.io.StringReader;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  // TODO: Implement your own tests cases for the controller

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringReader input = new StringReader("2 2");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = null;
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
  }

  @Test
  public void invalidRow() {
    StringReader input = new StringReader("one 1 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid number: one\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void invalidColumn() {
    StringReader input = new StringReader("1 two 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid number: two\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testRowOutOfBound() {
    StringReader input = new StringReader("4 1 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 4, 1\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testColumnOutOfBound() {
    StringReader input = new StringReader("1 5 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 1, 5\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testQuitAtRow() {
    StringReader input = new StringReader("q 1 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testQuitAtColumn() {
    StringReader input = new StringReader("1 q 1 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testValidMoves() {
    StringReader input = new StringReader("1 1 2 2 1 2 2 1 1 3");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | X |  \n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | X |  \n"
            + "-----------\n"
            + " O | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | X | X\n"
            + "-----------\n"
            + " O | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Game is over! X wins.\n", gameLog.toString());
  }

  @Test
  public void testMoveAtOccupiedSpace() {
    StringReader input = new StringReader("2 2 2 2 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Not a valid move: 2, 2\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testWorkingAfterInvalidMove() {
    StringReader input = new StringReader("2 6 2 1 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 2, 6\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  @Test
  public void testXWins() {
    StringReader input = new StringReader("1 1 2 2 1 2 2 1 1 3");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X |   |  \n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | X |  \n"
            + "-----------\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for O:\n"
            + " X | X |  \n"
            + "-----------\n"
            + " O | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + " X | X | X\n"
            + "-----------\n"
            + " O | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Game is over! X wins.\n", gameLog.toString());

  }

  @Test
  public void testOWins() {
    StringReader input = new StringReader("3 1 1 2 2 2 1 3 2 1 1 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for X:\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + "   | O | O\n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for X:\n"
            + "   | O | O\n"
            + "-----------\n"
            + " X | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + " O | O | O\n"
            + "-----------\n"
            + " X | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Game is over! O wins.\n", gameLog.toString());

  }

  @Test
  public void testTie() {
    StringReader input = new StringReader("3 1 1 2 2 2 2 1 2 3 1 3 1 1 3 3 3 2");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeConsoleController(input, gameLog);
    controller.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for X:\n"
            + "   | O |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + "   | O |  \n"
            + "-----------\n"
            + " O | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for X:\n"
            + "   | O |  \n"
            + "-----------\n"
            + " O | X | X\n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + "   | O | O\n"
            + "-----------\n"
            + " O | X | X\n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for X:\n"
            + " X | O | O\n"
            + "-----------\n"
            + " O | X | X\n"
            + "-----------\n"
            + " X |   |  \n"
            + "Enter a move for O:\n"
            + " X | O | O\n"
            + "-----------\n"
            + " O | X | X\n"
            + "-----------\n"
            + " X |   | O\n"
            + "Enter a move for X:\n"
            + " X | O | O\n"
            + "-----------\n"
            + " O | X | X\n"
            + "-----------\n"
            + " X | X | O\n"
            + "Game is over! Tie game.\n", gameLog.toString());

  }
}
