package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import static tictactoe.TicTacToeSwingView.CANVAS_HEIGHT;
import static tictactoe.TicTacToeSwingView.CELL_HEIGHT;
import static tictactoe.TicTacToeSwingView.CELL_WIDTH;

class BoardPanel extends JPanel {
  private final ReadonlyTttModel model;

  public BoardPanel(ReadonlyTttModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    Graphics2D graphics2D = (Graphics2D) g;

    if (!model.isGameOver()) {
      Player presentPlayer = model.getTurn();
      graphics2D.drawString("Player " + presentPlayer + " play your turn", 210, 20);
    }

    graphics2D.drawLine(CELL_WIDTH, 0, CELL_WIDTH, CELL_HEIGHT * 3);
    graphics2D.drawLine(CELL_WIDTH * 2, 0, CELL_WIDTH * 2, CELL_HEIGHT * 3);
    graphics2D.drawLine(0, CELL_HEIGHT, CELL_WIDTH * 3, CELL_HEIGHT);
    graphics2D.drawLine(0, CELL_HEIGHT * 2, CELL_WIDTH * 3, CELL_HEIGHT * 2);
    graphics2D.setColor(Color.BLUE);

    Player[][] board = model.getBoard();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int midX = 0;
        int midY = 0;
        if (i == 0 && j == 0) {
          midX = CELL_WIDTH;
          midY = CELL_HEIGHT;
        } else if (i == 0 && j == 1) {
          midX = 2 * CELL_WIDTH;
          midY = CELL_HEIGHT;
        } else if (i == 0 && j == 2) {
          midX = 3 * CELL_WIDTH;
          midY = CELL_HEIGHT;
        } else if (i == 1 && j == 0) {
          midX = CELL_WIDTH;
          midY = 2 * CELL_HEIGHT;
        } else if (i == 1 && j == 1) {
          midX = 2 * CELL_WIDTH;
          midY = 2 * CELL_HEIGHT;
        } else if (i == 1 && j == 2) {
          midX = 3 * CELL_WIDTH;
          midY = 2 * CELL_HEIGHT;
        } else if (i == 2 && j == 0) {
          midX = CELL_WIDTH;
          midY = 3 * CELL_HEIGHT;
        } else if (i == 2 && j == 1) {
          midX = 2 * CELL_WIDTH;
          midY = 3 * CELL_HEIGHT;
        } else if (i == 2 && j == 2) {
          midX = 3 * CELL_WIDTH;
          midY = 3 * CELL_HEIGHT;
        }
        if (board[i][j] != null) {
          graphics2D.drawString(board[i][j].toString() + " ", midX - (CELL_WIDTH / 2),
                  midY - (CELL_HEIGHT / 2));
        } else {
          graphics2D.drawString("  ", midX - (CELL_WIDTH / 2), midY - (CELL_HEIGHT / 2));
        }
      }
    }

    if (model.isGameOver()) {
      Graphics2D message = (Graphics2D) g;
      Font myFont = new Font("Courier New", 1, 40);
      message.setFont(myFont);
      message.setColor(Color.RED);
      if (model.getWinner() == Player.X) {
        message.drawString("Player X won the game!!", 50, CANVAS_HEIGHT / 2);
      } else if (model.getWinner() == Player.O) {
        message.drawString("Player O won the game!!", 50, CANVAS_HEIGHT / 2);
      } else if (model.getWinner() == null) {
        message.setColor(Color.BLACK);
        message.drawString("Game ended in a tie!!", 50, CANVAS_HEIGHT / 2);
      }
    }
  }
}
