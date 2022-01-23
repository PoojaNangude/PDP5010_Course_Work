package game;

import dungeon.Directions;

/**
 * The IGame interface represents all the operations which will be done within
 * a game. This includes the dungeon getting created, the player getting created,
 * assigning the treasure and also the different operations in a game like moving
 * the player based on the neighbours and also picking up the treasure from a cave
 * in the dungeon.
 */
public interface IGame extends ReadOnlyGameModel {

  /**
   * Function to assign the dungeon with treasure based on the input which will be
   * the percentage of caves which should be filled with treasure.
   */
  void assignTreasure(int percentageOfCavesWithTreasure);

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
   * Function to collect the treasure from a dungeon node,
   * if there is treasure at the location where the player is in the dungeon.
   */
  boolean collectTreasure();

  /*
  Function to move the player in one of the 4 directions i.e. EAST, WEST, NORTH, SOUTH.
   */
  int movePlayerInGivenDirection(Directions direction);

  /*
  Function to add monsters to the dungeon caves based on the percentage value given.
   */
  void addMonsters(int percentageOfCavesWithMonsters);

  /*
  Function to add arrows to the dungeon randomly.
   */
  void addArrows(int percentageOfNodesToBeAddedWithArrows);

  /*
  Function that will help the player collect the arrows if they are present in
  the present location of the player.
   */
  boolean collectArrows();

  /*
  Function that will help the player shoot the arrow in the specified direction and distance.
   */
  int shootArrow(Directions direction, int distance);

  /*
  Function that decided whether the player will survive or not when he enters a cave
  with an injured monster.
   */
  boolean doesPlayerSurvival();

  void addThieves(int startPointLocationId);

  void addPits(int startPointLocationId);
}
