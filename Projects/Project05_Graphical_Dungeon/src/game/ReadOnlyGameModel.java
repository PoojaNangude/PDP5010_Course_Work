package game;

import dungeon.DungeonDump;
import dungeon.DungeonNode;
import dungeon.WrappingType;
import player.Player;

/**
 * ReadOnly interface of Game Model which will be used by the view for displaying the model.
 */
public interface ReadOnlyGameModel {

  /*
  Returns if the game is over or not.
   */
  boolean isGameOver();

  /*
  Function that returns the dungeon grid.
   */
  DungeonNode[][] getDungeonGrid();

  /*
  Function that returns the start point of the game.
   */
  int getStartPointLocationId();

  /*
  Function that returns the end point of the game.
   */
  int getEndPointLocationId();

  /*
  Function that returns tha player object and all the data associated with the player.
   */
  Player getPlayer();

  /*
  Function that returns the dungeon information in the form of a string.
   */
  String getDungeon();

  /*
  Function that returns the details of the location where the player is presently.
   */
  DungeonNode getPlayerPresentLocationDetails();

  /*
  Function that returns the location details based on the location id.
   */
  DungeonNode getLocationDetails(int locationId);

  /*
  Function that returns the number of rows in the dungeon.
   */
  int getRows();

  /*
  Function that returns the number of columns in the dungeon.
   */
  int getColumns();

  /*
  Function that returns all the information of the entire dungeon.
   */
  DungeonDump[][] getDungeonDump();

  /*
  Function that will create a new game if user wishes so.
   */
  void newGame(WrappingType wrappingType,
                      int rows, int columns, int interconnectivity,
                      int percentageOfCavesWithTreasure, int percentageOfCavesWithMonsters);

  /*
  Function that returns the result of the shoot action in the form of string.
   */
  String getShootResult();

  String getThiefInCave();

  String getPitFall();
}
