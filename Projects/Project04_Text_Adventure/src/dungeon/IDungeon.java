package dungeon;

import java.util.ArrayList;
import location.Location;

/**
 * This interface represents all the operations required for creation of a dungeon.
 * The methods mainly include functions which would help in creation of the 2D,
 * matrix representation of the dungeon based on the input given.
 */
public interface IDungeon {

  /**
   * Function to assign the dungeon with treasure based on the input which will be
   * the percentage of caves which should be filled with treasure.
   */
  void assignTreasure(int percentageOfCavesWithTreasure);

  /**
   * Function to return the co-ordinated of a node based on the locationId of the node.
   * Location Ids are used for the purpose of easier representation of of the 2D matrix.
   * The co-ordinates are also maintained for calculations and evaluations in the model.
   */
  Location getLocationFromLocationId(int locationId);

  /**
   * Function that returns the entire 2d dungeon grid.
   */
  DungeonNode[][] getDungeonGrid();

  /**
   * Function that returns the location ids of all the caves in the tunnel..
   */
  ArrayList<Integer> getCaves();

  /**
   * Function that returns the dungeon size, which is the number of nodes in the 2D dungeon.
   */
  int getDungeonSize();

  /**
   * Function that returns the entire 2d dungeon grid.
   */
  int getEdgeCountInDungeon();

  /**
   * Function that verifies if there is a path between 2 points.
   */
  boolean hasPathBetweenStartAndEndPoint(int start, int end);

  /**
   * Function to get the dungeon with the nodes with neighbours.
   */
  String getNeighboursInformation();

  /**
   * Function to add monsters to the dungeon, based on the percentage of caves to be
   * added with monsters.
   */
  public void addOtyughsMonsters(int percentageOfCavesWithMonsters,
                                 int endPointLocationId, int startPointLocationId);

  /**
   * Function to add arrows randomly to the dungeon both in tunnels and caves.
   */
  void addArrows(int percentageOfNodesToBeAddedWithArrows);
}
