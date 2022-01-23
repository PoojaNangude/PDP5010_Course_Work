package tictactoe;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final TicTacToe model;
  private final TicTacToeView view;

  /**
   * Constructor for the controller.
   *
   * @param model  the source to read from
   * @param view the target to print to
   */
  public TicTacToeConsoleController(TicTacToe model, TicTacToeView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame(TicTacToe m) {
    this.view.addClickListener(this);
    while (!m.isGameOver()) {
      this.view.refresh();
    }
  }

  @Override
  public void handleCellClick(int row, int col) {
    model.move(row, col);
  }
}
