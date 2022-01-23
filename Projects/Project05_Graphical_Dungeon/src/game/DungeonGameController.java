package game;

import dungeon.Directions;
import java.awt.event.KeyEvent;

/**
 * DungeonGameController which implements the interface of the controller for
 * the graphical game.
 */
public class DungeonGameController implements IDungeonGameController {
  private IGame model;
  private IGameView view;

  /**
   * The model and view are passed to the constructor of the controller.
   *
   * @param model of type IGame
   * @param view of type IGameView
   */
  public DungeonGameController(IGame model, IGameView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame(IGame m) {
    this.view.addClickListener(this);
    this.view.addKeyListener(this);

    while (!m.isGameOver()) {
      continue;
      //      this.view.refresh();
    }
  }

  @Override
  public void handleCellClick(int x, int y) {
    System.out.println("x " + x + "y" + y);
  }

  @Override
  public void move(KeyEvent e) {
    if (!model.isGameOver()) {
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        model.movePlayerInGivenDirection(Directions.NORTH);
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        model.movePlayerInGivenDirection(Directions.SOUTH);
      } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        model.movePlayerInGivenDirection(Directions.WEST);
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        model.movePlayerInGivenDirection(Directions.EAST);
      }
      this.view.refresh();
    }
  }

  @Override
  public void pick(KeyEvent e) {
    if (!model.isGameOver()) {
      if (e.getKeyChar() == 't') {
        model.collectTreasure();
      } else if (e.getKeyChar() == 'a') {
        model.collectArrows();
      }
      this.view.refresh();
    }
  }

  @Override
  public void shoot(KeyEvent direction, KeyEvent distance) {
    int d = distance.getKeyChar() - 48;
    if (!model.isGameOver()) {
      if (direction.getKeyCode() == KeyEvent.VK_UP) {
        System.out.println("NORTH" + d);
        model.shootArrow(Directions.NORTH, d);
      } else if (direction.getKeyCode() == KeyEvent.VK_DOWN) {
        System.out.println("SOUTH" + d);
        model.shootArrow(Directions.SOUTH, d);
      } else if (direction.getKeyCode() == KeyEvent.VK_RIGHT) {
        System.out.println("EAST" + d);
        model.shootArrow(Directions.EAST, d);
      } else if (direction.getKeyCode() == KeyEvent.VK_LEFT) {
        System.out.println("WEST" + d);
        model.shootArrow(Directions.WEST, d);
      }
      this.view.refresh();
    }
  }
}
