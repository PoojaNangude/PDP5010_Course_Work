package player;

import location.Location;
import treasure.Treasure;

/**
 * This interface represents all the operations that are required to be performed on a
 * player which included updating its location is and location co-ordinates and also
 * adding treasure to his collected treasure list if he choosed to pick up the treasure
 * during the game.
 */
public interface IPlayer {
  /**
   * Function to get the current player co-ordinates.
   */
  Location getPlayerCoordinates();

  /**
   * Function to get the current player location id.
   */
  int getPlayerLocationId();

  /**
   * Function to add treasure to the list of player's collected treasure.
   */
  void addTreasure(Treasure treasure);

  /**
   * Function to set the current location coordinates of the player.
   */
  void setPlayerCoordinates(Location playerCoordinates);

  /**
   * Function to set the current player location id of the player.
   */
  void setPlayerLocationId(int playerLocationId);
}
