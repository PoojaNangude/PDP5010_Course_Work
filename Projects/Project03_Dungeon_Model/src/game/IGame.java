package game;

import dungeon.DungeonNode;
import player.Player;

/**
 * The IGame interface represents all the operations which will be done within
 * a game. This includes the dungeon getting created, the player getting created,
 * assigning the treasure and also the different operations in a game like moving
 * the player based on the neighbours and also picking up the treasure from a cave
 * in the dungeon.
 */
public interface IGame {

  /**
   * Function to assign the dungeon with treasure based on the input which will be
   * the percentage of caves which should be filled with treasure.
   */
  void assignTreasure(int percentageOfCavesWithTreasure);

  /**
   * Function to return the entire 2D dungeon for the purpose of representation.
   */
  DungeonNode[][] getDungeonGrid();

  /**
   * Function to initialise the game which includes assigning the start and end points,
   * and also creating a player.
   */
  void initialiseGame();

  /** Function to set the start and end point according to us instead of doing it randomly,
   * for the purpose of demonstrating the working in the driver classes.
   */
  void initialiseGameForTesting(int start, int end);

  /**
   * Function to get the start point locationId.
   */
  int getStartPointLocationId();

  /**
   * Function to get the end point locationId.
   */
  int getEndPointLocationId();

  /**
   * Function to get the player object which will contain all the
   * information like present location in grid and treasure collected.
   */
  Player getPlayer();

  /**
   * Function to move the player to the specified location id.
   * This will update the location id and its co-ordinates if it is a valid move.
   */
  String movePlayer(int newLocation);

  /**
   * Function to collect the treasure from a dungeon node,
   * if there is treasure at the location where the player is in the dungeon.
   */
  String collectTreasure();
}
