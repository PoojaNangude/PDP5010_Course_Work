package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 * TicTacToeSwingView implements the TicTacToeView interface methods and handles what has
 * to be sent to the controller depending on the area which is clicked by the user.
 */
public class TicTacToeSwingView extends JFrame implements TicTacToeView {

  private BoardPanel boardPanel;

  public static final int CANVAS_WIDTH = 600;
  public static final int CANVAS_HEIGHT = 600;
  public static final int CELL_WIDTH = CANVAS_WIDTH / 3;
  public static final int CELL_HEIGHT = CANVAS_HEIGHT / 3;

  /**
   * Constructor which takes in the read only model of tic-tac-toe and intialises the
   * user interface.
   *
   * @param model of type ReadonlyTttModel
   */
  public TicTacToeSwingView(ReadonlyTttModel model) {
    super("Tic-Tac-Toe");
    this.setSize(600, 600);
    boardPanel = new BoardPanel(model);
    add(boardPanel);
    this.setTitle("Tic-Tac-Toe");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    // create the MouseAdapter
    MouseAdapter clickAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        int mouseX = e.getX();
        int mouseY = e.getY();

        int gridX = -1;
        int gridY = -1;
        if (mouseX >= 0 && mouseX <= CELL_WIDTH
                && mouseY >= 0 && mouseY <= CELL_HEIGHT) {
          gridX = 0;
          gridY = 0;
        } else if (mouseX > CELL_WIDTH && mouseX <= 2 * CELL_WIDTH
                && mouseY >= 0 && mouseY <= CELL_HEIGHT) {
          gridX = 0;
          gridY = 1;
        } else if (mouseX > 2 * CELL_WIDTH && mouseX <= 3 * CELL_WIDTH
                && mouseY >= 0 && mouseY <= CELL_HEIGHT) {
          gridX = 0;
          gridY = 2;
        } else if (mouseX >= 0 && mouseX <= CELL_WIDTH
                && mouseY > 200 && mouseY <= 2 * CELL_HEIGHT) {
          gridX = 1;
          gridY = 0;
        } else if (mouseX > CELL_WIDTH && mouseX <= 2 * CELL_WIDTH
                && mouseY > 200 && mouseY <= 2 * CELL_HEIGHT) {
          gridX = 1;
          gridY = 1;
        } else if (mouseX > 2 * CELL_WIDTH && mouseX <= 3 * CELL_WIDTH
                && mouseY > 200 && mouseY <= 2 * CELL_HEIGHT) {
          gridX = 1;
          gridY = 2;
        } else if (mouseX >= 0 && mouseX <= CELL_WIDTH
                && mouseY > 400 && mouseY <= 3 * CELL_HEIGHT) {
          gridX = 2;
          gridY = 0;
        } else if (mouseX > CELL_WIDTH && mouseX <= 2 * CELL_WIDTH
                && mouseY > 400 && mouseY <= 3 * CELL_HEIGHT) {
          gridX = 2;
          gridY = 1;
        } else if (mouseX > 2 * CELL_WIDTH && mouseX <= 3 * CELL_WIDTH
                && mouseY > 400 && mouseY <= 3 * CELL_HEIGHT) {
          gridX = 2;
          gridY = 2;
        }
        try {
          listener.handleCellClick(gridX, gridY);
        } catch (IllegalArgumentException iae) {
          System.out.println("Message: " + iae.getMessage());
        }
      }
    };
    boardPanel.addMouseListener(clickAdapter);
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
