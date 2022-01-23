package game;

import java.awt.event.KeyEvent;

/**
 * Interface for graphical based game controller that states all the functions that
 * will be needed to handle user inputs during the game.
 */
public interface IDungeonGameController {

  /*
  Function that continues the game until the player dies or wins the game.
   */
  void playGame(IGame m);

  /*
  Function to handle a cell click for movement of the player
   */
  void handleCellClick(int x, int y);

  /*
  Function to move the player based on the arrow key typed/
   */
  void move(KeyEvent d);

  /*
  Function to pick the treasure or arrows based on the user choice.
   */
  void pick(KeyEvent e);

  /*
  Function to shoot the player based on the direction and distance entered by the player.
   */
  void shoot(KeyEvent direction, KeyEvent distance);
}
