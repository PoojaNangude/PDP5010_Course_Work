package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    try {
      Integer r = null;
      Integer c = null;
      out.append(String.format("%s\n", m.toString()));
      out.append(String.format("Enter a move for %s:\n", m.getTurn().toString()));

      while (true) {
        String input = this.scan.next();

        if (input.equalsIgnoreCase("q")) {
          out.append(String.format("Game quit! Ending game state:\n%s", m.toString()));
          out.append("\n");
          break;
        } else if (!isValidInput(input, out)) {
          continue;
        } else {
          if (r == null) {
            r = Integer.parseInt(input);
          } else if (c == null) {
            c = Integer.parseInt(input);
            boolean moved = movedPlayer(m, r - 1, c - 1, out);
            if (moved) {
              if (m.isGameOver()) {
                Player winner = m.getWinner();
                out.append(String.format("%s\n", m.toString()));
                if (winner != null) {
                  out.append(String.format("Game is over! %s wins.\n", winner.toString()));
                  break;
                } else {
                  out.append("Game is over! Tie game.\n");
                  break;
                }
              } else {
                out.append(String.format("%s\n", m.toString()));
                out.append(String.format("Enter a move for %s:\n", m.getTurn().toString()));
                r = null;
                c = null;
              }
            } else {
              r = null;
              c = null;
              continue;
            }
          }
        }
      }

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  private boolean movedPlayer(TicTacToe m, int r, int c, Appendable out) throws IOException {
    try {
      m.move(r, c);
      return true;
    } catch (IllegalArgumentException ex) {
      out.append(String.format("Not a valid move: %d, %d\n", r + 1, c + 1));
      return false;
    }
  }

  private boolean isValidInput(String input, Appendable out) throws IOException {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException ex) {
      out.append(String.format("Not a valid number: %s\n", input));
      return false;
    }
  }
}
