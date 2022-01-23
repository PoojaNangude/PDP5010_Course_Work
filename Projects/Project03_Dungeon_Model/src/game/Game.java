package game;

import dungeon.Dungeon;
import dungeon.DungeonNode;
import dungeon.IDungeon;
import dungeon.LocationType;
import dungeon.WrappingType;
import java.util.ArrayList;
import location.Location;
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

  public Game(WrappingType wrappingType,
              int rows, int columns, int interconnectivity) {
    dungeon = new Dungeon(wrappingType, rows, columns, interconnectivity);
  }

  @Override
  public void assignTreasure(int percentageOfCavesWithTreasure) {
    dungeon.assignTreasure(percentageOfCavesWithTreasure);
  }

  @Override
  public DungeonNode[][] getDungeonGrid() {
    DungeonNode[][] dGrid = dungeon.getDungeonGrid();
    return dGrid;
  }

  @Override
  public void initialiseGame() {
    assignStartAndEndPoints();
    createPlayer();
  }

  @Override
  public void initialiseGameForTesting(int start, int end) {
    this.startPointLocationId = start;
    this.endPointLocationId = end;
    this.startPointCoordinates = dungeon.getLocationFromLocationId(this.startPointLocationId);
    this.endPointCoordinates = dungeon.getLocationFromLocationId(this.endPointLocationId);
    createPlayer();
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
   * @param end integer value which will be the location id of the end point.
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
      System.out.println(adjacents);
      for (int i = 0; i < adjacents.size(); i++) {

        int neighbour = adjacents.get(i);
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
        }
      }
      System.out.println(visited);
      currentVertex = queue.remove(0);
      System.out.println("current" + currentVertex);
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
    System.out.println(path);
    return path.size() - 1;
  }

  private void createPlayer() {
    this.player = new Player(this.startPointCoordinates, this.startPointLocationId);
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
    Player gPlayer = this.player;
    return gPlayer;
  }

  @Override
  public String movePlayer(int newLocation) {
    Location playerPresentLocation = this.player.getPlayerCoordinates();
    if (playerPresentLocation == this.endPointCoordinates) {
      return "Player has already reached the destination";
    }
    ArrayList<Integer> neighbours = dungeon
            .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getNeighbours();
    if (neighbours.contains(newLocation)) {
      this.player.setPlayerLocationId(newLocation);
      this.player.setPlayerCoordinates(dungeon.getLocationFromLocationId(newLocation));
      if (this.player.getPlayerLocationId() == this.endPointLocationId) {
        return "Player has reached the destination";
      } else {
        return "Player moved to desired location";
      }
    } else {
      return "Player cannot be moved as it as no such path to such specified location";
    }
  }

  @Override
  public String collectTreasure() {
    Location playerPresentLocation = this.player.getPlayerCoordinates();
    if (dungeon
            .getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getTreasure().size() == 0
            || dungeon.getDungeonGrid()[playerPresentLocation.getX()][playerPresentLocation.getY()]
            .getLocationType() == LocationType.TUNNEL) {
      return "No treasure present";
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
      return "Treasure added";
    }
  }
}
