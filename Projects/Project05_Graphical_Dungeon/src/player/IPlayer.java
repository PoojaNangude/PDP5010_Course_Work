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

  /**
   * Function to return the level of pungent smell the player senses.
   */
  int getLevelOfPungentSmellDetected();

  /**
   * Function to set the players smell sense after it is evaluated after every move.
   */
  void setLevelOfPungentSmellDetected(int levelOfPungentSmellDetected);

  /**
   * Function to add arrows when he collects it from a location where is finds arrows.
   */
  void addArrows(int arrowCount);

  /**
   * Decrement the arrow count when player uses of his arrows during the game.
   */
  void decrementArrowCount();

  /**
   * Function to get the number of arrows the player is left with.
   */
  int getArrowCount();

  /**
   * Function which returns whether the player is alive or dead.
   * This is used after every move in the dungeon.
   */
  boolean getIsAlive();

  /**
   * Function that sets the status of the player.
   * This value is updated depending on every move made by the player throughout the game.
   */
  void setIsAlive(boolean isAlive);

  /**
   * Function to return the summary of all the treasure collected by the player uptil
   * the present state of the player.
   */
  String getTreasureCollectedSummary();

  /*
  Takes away all the treasure from the player when a thief is encountered st the same position.
   */
  void takeAwayTreasure();

  /*
  Returns the sense of wind the player gets at every location, depending on his distance from
  a pit nearby.
   */
  int getSenseOfWind();

  /*
  Sets the value of the sense of wind sensed by the player after every move.
   */
  void setSenseOfWind(int senseOfWind);
}
