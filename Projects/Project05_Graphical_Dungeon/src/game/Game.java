package game;

import dungeon.Directions;
import dungeon.Dungeon;
import dungeon.DungeonDump;
import dungeon.DungeonNode;
import dungeon.IDungeon;
import dungeon.LocationType;
import dungeon.WrappingType;
import java.util.ArrayList;
import location.Location;
import monster.Otyugh;
import pair.Pair;
import player.Player;
import random.RandomNumberGenerator;
import treasure.Treasure;

/**
 * Dungeon class which implements the IGame interface.
 * This class is used for getting the dungeon, player and the start
 * and end point all under one class, and also used to perform operations,
 * like move player and collect treasure from a dungeon node.
 */
public class Game implements IGame {
  private IDungeon dungeon;
  private Player player;
  private int startPointLocationId;
  private Location startPointCoordinates;
  private int endPointLocationId;
  private Location endPointCoordinates;

  private int rows;
  private int columns;
  private WrappingType wrappingType;
  private int interconnectivity;
  private int percentageOfCavesWithTreasure;
  private int percentageOfCavesWithMonsters;
  private int percentageOfNodesWithArrows;

  private String shootResult = "";
  private String thiefInCave = "";
  private String pitFall = "";

  /**
   * Constructs a dungeon using the Kruskal's algorithm and also adds monsters,
   * treasure and arrows to the dungeon.
   *
   * @param wrappingType                  of type enum - WRAPPING and NON_WRAPPING
   * @param rows                          of type integer
   * @param columns                       of type integer
   * @param interconnectivity             of type integer
   * @param percentageOfCavesWithTreasure of type integer
   * @param percentageOfCavesWithMonsters of type integer
   */
  public Game(WrappingType wrappingType,
              int rows, int columns, int interconnectivity,
              int percentageOfCavesWithTreasure, int percentageOfCavesWithMonsters) {

    dungeon = new Dungeon(wrappingType, rows, columns, interconnectivity,
            percentageOfCavesWithTreasure, percentageOfCavesWithMonsters);

    this.rows = rows;
    this.columns = columns;
    this.wrappingType = wrappingType;
    this.interconnectivity = interconnectivity;

    // initialise game
    initialiseGame(); // randomised
    //    initialiseGameForTesting(7, 18); // not randomised

    // add treasure
    this.percentageOfCavesWithTreasure = percentageOfCavesWithTreasure;
    assignTreasure(percentageOfCavesWithTreasure);

    // add monsters
    this.percentageOfCavesWithMonsters = percentageOfCavesWithMonsters;
    addMonsters(percentageOfCavesWithMonsters);

    // add arrows
    int percentageOfNodesWithArrows = percentageOfCavesWithTreasure;
    this.percentageOfNodesWithArrows = percentageOfNodesWithArrows;
    addArrows(percentageOfNodesWithArrows);
    addThieves(startPointLocationId);
    addPits(startPointLocationId);
  }

  @Override
  public void newGame(WrappingType wrappingType,
                      int rows, int columns, int interconnectivity,
                      int percentageOfCavesWithTreasure, int percentageOfCavesWithMonsters) {

    dungeon = new Dungeon(wrappingType, rows, columns, interconnectivity,
            percentageOfCavesWithTreasure, percentageOfCavesWithMonsters);
    this.rows = rows;
    this.columns = columns;
    this.wrappingType = wrappingType;
    this.interconnectivity = interconnectivity;

    // initialise game
    initialiseGame(); // randomised
    // initialiseGameForTesting(7, 18); // not randomised

    // add treasure
    this.percentageOfCavesWithTreasure = percentageOfCavesWithTreasure;
    assignTreasure(percentageOfCavesWithTreasure);

    // add monsters
    this.percentageOfCavesWithMonsters = percentageOfCavesWithMonsters;
    addMonsters(percentageOfCavesWithMonsters);

    // add arrows
    int percentageOfNodesWithArrows = percentageOfCavesWithTreasure;
    this.percentageOfNodesWithArrows = percentageOfNodesWithArrows;
    addArrows(percentageOfNodesWithArrows);
    addThieves(startPointLocationId);
    addPits(startPointLocationId);
  }

  @Override
  public void assignTreasure(int percentageOfCavesWithTreasure) {
    dungeon.assignTreasure(percentageOfCavesWithTreasure);
  }

  @Override
  public void addMonsters(int percentageOfCavesWithMonsters) {
    dungeon.addOtyughsMonsters(percentageOfCavesWithMonsters,
            this.endPointLocationId, this.startPointLocationId);
    getPlayerLevelOfPungentSmellDetected();
  }

  @Override
  public void addArrows(int percentageOfNodesToBeAddedWithArrows) {
    dungeon.addArrows(percentageOfNodesToBeAddedWithArrows);
  }

  @Override
  public void addThieves(int startPointLocationId) {
    dungeon.addThieves(startPointLocationId);
  }

  @Override
  public void addPits(int startPointLocationId) {
    dungeon.addPits(startPointLocationId);
    senseOfWind();
  }

  @Override
  public DungeonNode[][] getDungeonGrid() {
    DungeonNode[][] dunGrid = dungeon.getDungeonGrid();
    return dunGrid;
  }

  @Override
  public String getDungeon() {
    String dunGrid = dungeon.toString();
    return dunGrid;
  }

  @Override
  public void initialiseGame() {
    assignStartAndEndPoints();
    createPlayer();
    getPlayerLevelOfPungentSmellDetected();
    senseOfWind();
  }

  @Override
  public void initialiseGameForTesting(int start, int end) {
    this.startPointLocationId = start;
    this.endPointLocationId = end;
    this.startPointCoordinates = dungeon.getLocationFromLocationId(this.startPointLocationId);
    this.endPointCoordinates = dungeon.getLocationFromLocationId(this.endPointLocationId);
    createPlayer();
    getPlayerLevelOfPungentSmellDetected();
    senseOfWind();
  }


  private void assignStartAndEndPoints() {
    ArrayList<Integer> nodeSet = this.dungeon.getCaves();
    int randomIndex = RandomNumberGenerator.getRandomNumberInRange(0, nodeSet.size());
    int pathLength = 0;
    this.startPointLocationId = nodeSet.get(randomIndex);
    this.startPointCoordinates = dungeon.getLocationFromLocationId(this.startPointLocationId);
    nodeSet.remove(randomIndex);
    randomIndex = RandomNumberGenerator.getRandomNumberInRange(0, nodeSet.size());
    this.endPointLocationId = nodeSet.get(randomIndex);
    this.endPointCoordinates = dungeon.getLocationFromLocationId(this.endPointLocationId);
  }

  /**
   * To evaluate the distance between two points based on breadth first search.
   *
   * @param start integer value which will be the location id of the start point.
   * @param end   integer value which will be the location id of the end point.
   * @return length of the path.
   */
  public int distanceBetweenStartAndEndPoint(int start, int end) {
    int startVertex = start;
    int endVertex = end;

    ArrayList<Integer> queue = new ArrayList<>();
    ArrayList<Integer> visited = new ArrayList<>();

    visited.add(startVertex);
    int currentVertex = startVertex;

    while (currentVertex != endVertex) {
      Location location = dungeon.getLocationFromLocationId(currentVertex);
      ArrayList<Integer> adjacents = dungeon
              .getDungeonGrid()[location.getX()][location.getY()].getNeighbours();
      for (int i = 0; i < adjacents.size(); i++) {

        int neighbour = adjacents.get(i);
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
        }
      }
      currentVertex = queue.remove(0);
    }

    ArrayList<Integer> path = new ArrayList<>();
    path.add(currentVertex);
    while (currentVertex != startVertex) {
      Location location = dungeon.getLocationFromLocationId(currentVertex);
      ArrayList<Integer> neighbours = dungeon
              .getDungeonGrid()[location.getX()][location.getY()].getNeighbours();
      for (int i = 0; i < neighbours.size(); i++) {
        if (visited.contains(neighbours.get(i))) {
          path.add(neighbours.get(i));
          currentVertex = neighbours.get(i);
          break;
        }
      }
    }
    return path.size() - 1;
  }

  private void createPlayer() {
    this.player = new Player(this.startPointCoordinates, this.startPointLocationId);
    Location location = dungeon.getLocationFromLocationId(this.startPointLocationId);
    dungeon.getDungeonGrid()[location.getX()][location.getY()].setVisited(true);
  }

  public int getStartPointLocationId() {
    int startPoint = this.startPointLocationId;
    return startPoint;
  }

  public int getEndPointLocationId() {
    int endPoint = this.endPointLocationId;
    return endPoint;
  }

  public Player getPlayer() {
    Player gamePlayer = this.player;
    return gamePlayer;
  }

  @Override
  public String getShootResult() {
    return shootResult;
  }

  @Override
  public String getPitFall() {
    return pitFall;
  }

  @Override
  public String getThiefInCave() {
    return thiefInCave;
  }

  @Override
  public int movePlayerInGivenDirection(Directions direction) {
    shootResult = "";
    Location playerPresentLocation = this.player.getPlayerCoordinates();
    ArrayList<Pair> possibleMoves = dungeon.getDungeonGrid()[playerPresentLocation
            .getX()][playerPresentLocation.getY()].getPossibleMoves();
    for (int i = 0; i < possibleMoves.size(); i++) {
      if (possibleMoves.get(i).getDirectionFromPresentLocation() == direction) {
        this.player.setPlayerLocationId(possibleMoves.get(i).getLocationId());
        this.player.setPlayerCoordinates(dungeon
                .getLocationFromLocationId(possibleMoves.get(i).getLocationId()));
        Location location = dungeon.getLocationFromLocationId(possibleMoves.get(i).getLocationId());
        this.dungeon.getDungeonGrid()[location.getX()][location.getY()].setVisited(true);
        if (dungeon.getDungeonGrid()[location.getX()][location.getY()].getMonster() != null
                && dungeon.getDungeonGrid()[location.getX()][location.getY()]
                .getMonster().getHealth() > 0) {
          this.player.setIsAlive(false);
          /*
          Returns -1 when player is moved to a location which has a monster with full health and
          thus will be dead.
           */
          thiefInCave = "";
          return -1;
        }
        if (dungeon.getDungeonGrid()[location.getX()][location.getY()].getHasPit()) {
          pitFall = "Player fell into a pit!!";
          this.player.setIsAlive(false);
          return -1;
        }
        getPlayerLevelOfPungentSmellDetected();
        senseOfWind();
        if (this.dungeon.getDungeonGrid()[location.getX()][location.getY()].getHasThief()) {
          if (this.player.getTreasureCollected().size() > 0) {
            thiefInCave = "The thief stole you treasure";
            this.player.takeAwayTreasure();
          } else {
            thiefInCave = "";
          }
        }
        if (!this.dungeon.getDungeonGrid()[location.getX()][location.getY()].getHasThief()) {
          thiefInCave = "";
        }
        return 1; // return 1 when player is moved to the desired location
      }
    }
    thiefInCave = "";
    // return 0 when the direction specified is invalid
    return 0;
  }

  @Override
  public boolean isGameOver() {
    int playerLocationId = this.player.getPlayerLocationId();
    Location playerLocation = dungeon.getLocationFromLocationId(playerLocationId);
    if (!this.player.getIsAlive()) {
      return true;
    } else if (this.player.getPlayerLocationId() == this.endPointLocationId
            && dungeon
            .getDungeonGrid()[playerLocation.getX()][playerLocation.getY()]
            .getMonster().getHealth() == 0) {
      return true;
    }
    return false;
  }

  @Override
  public boolean doesPlayerSurvival() {
    int value = RandomNumberGenerator.getFakeRandomNumber(1); // not randomised
    //    int value = RandomNumberGenerator.getRandomNumberInRange(0,2); // randomised

    if (value == 1) {
      return true;
    } else {
      this.player.setIsAlive(false);
      return false;
    }
  }

  @Override
  public int shootArrow(Directions direction, int distance) throws IllegalArgumentException {
    if (distance <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.player.getArrowCount() == 0) {
      shootResult = "You are out of arrows. Explore the dungeon for more.";
      return -1; // return -1 when user is out of arrows
    } else {
      ArrayList<Integer> arrowPath = new ArrayList<>();
      int arrowLocationId = this.player.getPlayerLocationId();
      Location arrowLocation = this.dungeon.getLocationFromLocationId(arrowLocationId);
      LocationType arrowLocationType = this.dungeon
              .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getLocationType();
      boolean movePossible = false;
      int distanceTravelled = 0;

      ArrayList<Pair> possibleMoves = this.dungeon
              .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getPossibleMoves();

      for (int i = 0; i < possibleMoves.size(); i++) {
        if (possibleMoves.get(i).getDirectionFromPresentLocation() == direction) {
          movePossible = true;
          arrowPath.add(arrowLocationId);
          arrowPath.add(possibleMoves.get(i).getLocationId());
          arrowLocationId = possibleMoves.get(i).getLocationId();
          arrowLocation = this.dungeon.getLocationFromLocationId(arrowLocationId);
          arrowLocationType = this.dungeon
                  .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getLocationType();
          if (arrowLocationType == LocationType.CAVE) {
            distanceTravelled++;
          }
          break;
        }
      }

      if (!movePossible) {
        this.player.decrementArrowCount();
        shootResult = "You shot the arrow in darkness";
        return 0; // return 0 when direction specified is invalid
      } else { // start if else when move is valid

        while (distanceTravelled < distance) { //<=
          possibleMoves = this.dungeon
                  .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getPossibleMoves();
          movePossible = false;

          if (arrowLocationType == LocationType.TUNNEL) { // when present location is a tunnel
            // if it is a tunnel it can travel in a crooked manner
            // we have to update its other direction as the latest direction
            movePossible = true;
            ArrayList<Integer> neighboursOfPresentNode = new ArrayList<Integer>(dungeon
                    .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getNeighbours());
            int lastVisitedNode = arrowPath.get(arrowPath.size() - 2);
            neighboursOfPresentNode.remove(neighboursOfPresentNode.indexOf(lastVisitedNode));
            arrowLocationId = neighboursOfPresentNode.remove(0);
            arrowPath.add(arrowLocationId);
            arrowLocation = this.dungeon.getLocationFromLocationId(arrowLocationId);
            arrowLocationType = this.dungeon
                    .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()].getLocationType();
          } else { // when present location is a cave

            for (int i = 0; i < possibleMoves.size(); i++) {
              if (possibleMoves.get(i).getDirectionFromPresentLocation() == direction) {
                movePossible = true;
                arrowPath.add(possibleMoves.get(i).getLocationId());
                arrowLocationId = possibleMoves.get(i).getLocationId();
                arrowLocation = this.dungeon.getLocationFromLocationId(arrowLocationId);
                arrowLocationType = this.dungeon
                        .getDungeonGrid()[arrowLocation.getX()][arrowLocation.getY()]
                        .getLocationType();
                break;
              }
            } // end of for loop
            if (arrowLocationType == LocationType.CAVE) {
              distanceTravelled++;
            }
            if (distanceTravelled == distance) {
              break;
            }
          }
          if (!movePossible) {
            break;
          }
        } // end of while loop
      } // end of else when move is possible
      int lastLocationIdOfArrow = arrowPath.get(arrowPath.size() - 1);
      Location lastOfLocationOfArrow = dungeon.getLocationFromLocationId(lastLocationIdOfArrow);
      if (distanceTravelled == distance
              && dungeon
              .getDungeonGrid()[lastOfLocationOfArrow.getX()][lastOfLocationOfArrow.getY()]
              .getMonster() != null
              && dungeon
              .getDungeonGrid()[lastOfLocationOfArrow.getX()][lastOfLocationOfArrow.getY()]
              .getMonster().getHealth() > 0
      ) {
        dungeon.getDungeonGrid()[lastOfLocationOfArrow.getX()][lastOfLocationOfArrow.getY()]
                .getMonster().decrementHealth();
        int healthOfMonster = dungeon
                .getDungeonGrid()[lastOfLocationOfArrow.getX()][lastOfLocationOfArrow.getY()]
                .getMonster().getHealth();
        if (healthOfMonster == 0) {
          this.player.decrementArrowCount();
          shootResult = "You killed a monster";
          return 2; // return 2 when player is killed
        } else {
          shootResult = "You hears a howl. You injured a monster";
          this.player.decrementArrowCount();
          return 1; // return 1 when player is injured
        }
      } else {
        shootResult = "You shot an arrow in darkness";
        this.player.decrementArrowCount();
        return 0; // return 0 when player misses a shot
      }
    } // end of else which is executed when player has arrows
  }

  @Override
  public boolean collectTreasure() {
    shootResult = "";
    Location playerPresentLocation = this.player.getPlayerCoordinates();
    if (dungeon
            .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getTreasure().size() == 0
            || dungeon.getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getLocationType() == LocationType.TUNNEL) {
      return false;
    } else {
      ArrayList<Treasure> treasureList = dungeon
              .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
              .getTreasure();
      for (int i = 0; i < treasureList.size(); i++) {
        this.player.addTreasure(treasureList.get(i));
      }
      while (dungeon
              .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
              .getTreasure().size() != 0) {
        dungeon.getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
                .removeTreasure(0);
      }
      return true;
    }
  }

  @Override
  public DungeonNode getPlayerPresentLocationDetails() {
    int playerLocationId = this.player.getPlayerLocationId();
    Location location = dungeon.getLocationFromLocationId(playerLocationId);
    return this.dungeon.getDungeonGrid()[location.getX()][location.getY()];
  }

  @Override
  public DungeonNode getLocationDetails(int locationId) {
    Location location = dungeon.getLocationFromLocationId(locationId);
    return this.dungeon.getDungeonGrid()[location.getX()][location.getY()];
  }

  @Override
  public int getRows() {
    return this.dungeon.getRows();
  }

  @Override
  public int getColumns() {
    return this.dungeon.getColumns();
  }

  @Override
  public DungeonDump[][] getDungeonDump() {
    return dungeon.getDungeonDump();
  }

  @Override
  public boolean collectArrows() {
    shootResult = "";
    Location playerPresentLocation = this.player.getPlayerCoordinates();
    int countOfArrowsInDungeon = dungeon
            .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getArrowCount();
    if (countOfArrowsInDungeon == 0) {
      return false;
    } else {
      this.player.addArrows(countOfArrowsInDungeon);
      this.dungeon
              .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
              .setArrowCount(0);
      return true;
    }
  }

  /**
   * Function that returns the pungent smell intensity in the players' present location.
   * 0 means no smell, 1 means less pungent smell and 2 means more pungent smell.
   *
   * @return integer value based on the intensity of smell
   */
  public int getPlayerLevelOfPungentSmellDetected() {
    int pungentSmellIntensity = 0;
    int playerLocationId = this.player.getPlayerLocationId();
    Location location = dungeon.getLocationFromLocationId(playerLocationId);
    ArrayList<Pair> neighbours = dungeon
            .getDungeonGrid()[location.getX()][location.getY()]
            .getPossibleMoves();
    int monsterCountAtOneUnitDistance = 0;
    int monsterCountAtTwoUnitDistance = 0;

    for (int i = 0; i < neighbours.size(); i++) {
      int neighbourLocationId = neighbours.get(i).getLocationId();
      Location locationOfNeighbour = dungeon.getLocationFromLocationId(neighbourLocationId);
      Otyugh monster = dungeon
              .getDungeonGrid()[locationOfNeighbour.getX()][locationOfNeighbour.getY()]
              .getMonster();
      if (monster != null && monster.getHealth() != 0) {
        monsterCountAtOneUnitDistance++;
      }
    }

    for (int i = 0; i < neighbours.size(); i++) {
      int neighbourLocationId = neighbours.get(i).getLocationId();
      Location locationOfNeighbour = dungeon.getLocationFromLocationId(neighbourLocationId);
      Directions neighbourDirection = neighbours.get(i).getDirectionFromPresentLocation();
      ArrayList<Pair> neighboursOfNeighbours = dungeon
              .getDungeonGrid()[locationOfNeighbour.getX()][locationOfNeighbour.getY()]
              .getPossibleMoves();

      for (int j = 0; j < neighboursOfNeighbours.size(); j++) {
        int locationIdOfNeighbourOfNeighbour = neighboursOfNeighbours.get(j).getLocationId();

        Location locationOfNeighbourOfNeighbours = dungeon
                .getLocationFromLocationId(locationIdOfNeighbourOfNeighbour);
        if (neighboursOfNeighbours.get(j).getLocationId() != neighbourLocationId
                && dungeon
                .getDungeonGrid()
                [locationOfNeighbourOfNeighbours.getX()][locationOfNeighbourOfNeighbours.getY()]
                .getMonster() != null
                &&
                dungeon.getDungeonGrid()
                        [locationOfNeighbourOfNeighbours.getX()]
                        [locationOfNeighbourOfNeighbours.getY()]
                        .getMonster().getHealth() != 0
        ) {
          monsterCountAtTwoUnitDistance++;
          break;
        }
      }
    }

    int monsterCountWithinTwoUnitDistance = monsterCountAtOneUnitDistance
            + monsterCountAtTwoUnitDistance;
    if (monsterCountAtTwoUnitDistance == 1 && monsterCountAtOneUnitDistance == 0) {
      pungentSmellIntensity = 1;
    } else if (monsterCountAtOneUnitDistance == 1 || monsterCountWithinTwoUnitDistance > 0) {
      pungentSmellIntensity = 2;
    }
    player.setLevelOfPungentSmellDetected(pungentSmellIntensity);
    return pungentSmellIntensity;
  }

  /**
   * Function that returns the sense of wind in the present location.
   * 0 means no wind, 1 means less windy and 2 means more windy.
   *
   * @return integer value based on the intensity of the wind
   */
  public int senseOfWind() {
    int windSense = 0;
    int playerLocationId = this.player.getPlayerLocationId();
    Location location = dungeon.getLocationFromLocationId(playerLocationId);
    ArrayList<Pair> neighbours = dungeon
            .getDungeonGrid()[location.getX()][location.getY()]
            .getPossibleMoves();
    int pitCountAtOneUnitDistance = 0;
    int pitCountAtTwoUnitDistance = 0;

    for (int i = 0; i < neighbours.size(); i++) {
      int neighbourLocationId = neighbours.get(i).getLocationId();
      Location locationOfNeighbour = dungeon.getLocationFromLocationId(neighbourLocationId);
      if (dungeon
              .getDungeonGrid()[locationOfNeighbour
              .getX()][locationOfNeighbour.getY()].getHasPit()) {
        pitCountAtOneUnitDistance++;
      }
    }

    for (int i = 0; i < neighbours.size(); i++) {
      int neighbourLocationId = neighbours.get(i).getLocationId();
      Location locationOfNeighbour = dungeon.getLocationFromLocationId(neighbourLocationId);
      Directions neighbourDirection = neighbours.get(i).getDirectionFromPresentLocation();
      ArrayList<Pair> neighboursOfNeighbours = dungeon
              .getDungeonGrid()[locationOfNeighbour.getX()][locationOfNeighbour.getY()]
              .getPossibleMoves();

      for (int j = 0; j < neighboursOfNeighbours.size(); j++) {
        int locationIdOfNeighbourOfNeighbour = neighboursOfNeighbours.get(j).getLocationId();

        Location locationOfNeighbourOfNeighbours = dungeon
                .getLocationFromLocationId(locationIdOfNeighbourOfNeighbour);
        if (neighboursOfNeighbours.get(j).getLocationId() != neighbourLocationId
                && dungeon
                .getDungeonGrid()
                [locationOfNeighbourOfNeighbours.getX()][locationOfNeighbourOfNeighbours.getY()]
                .getHasPit()
        ) {
          pitCountAtTwoUnitDistance++;
          break;
        }
      }
    }

    int pitCountWithinTwoUnitDistance = pitCountAtOneUnitDistance
            + pitCountAtTwoUnitDistance;
    if (pitCountAtTwoUnitDistance == 1 && pitCountAtOneUnitDistance == 0) {
      windSense = 1;
    } else if (pitCountAtOneUnitDistance == 1 || pitCountWithinTwoUnitDistance > 0) {
      windSense = 2;
    }
    player.setSenseOfWind(windSense);
    return windSense;
  }

}
