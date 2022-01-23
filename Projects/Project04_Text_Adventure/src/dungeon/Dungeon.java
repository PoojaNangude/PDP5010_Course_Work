package dungeon;

import java.util.ArrayList;
import location.Location;
import pair.Pair;
import random.RandomNumberGenerator;
import treasure.Diamond;
import treasure.Ruby;
import treasure.Sapphire;
import treasure.Treasure;
import treasure.TreasureCatalog;

/**
 * Dungeon class which implements the IDungeon interface.
 * This class implements all the methods required to construct a 2D dungeon.
 * The only difference between Dungeon and RandomDungeon is that in Dungeon the
 * edges are getting selected deterministically and also treasure is assigned deterministically,
 * so we know what the graph would look like.
 * In case of edges, this class will continuously pop the first element in the edge list,
 * till edge list is empty, so we know how each vertex and its neighbours would be.
 */
public class Dungeon implements IDungeon {
  private final WrappingType wrappingType;
  private final int rows;
  private final int columns;
  private final int interconnectivity;
  private int edgeCountInDungeon;
  private DungeonNode[][] dungeonGrid;
  private ArrayList<ArrayList<Integer>> edgesList;
  private ArrayList<ArrayList<Integer>> remainingEdgesList;
  private ArrayList<ArrayList<Integer>> locationSets;

  /**
   * Constructor for assigning the parameters required to create a dungeon.
   *
   * @param wrappingType takes in the wrapping type of the dungeon.
   * @param rows         takes in the number of rows in the dungeon.
   * @param columns      takes in the number of columns in the dungeon.
   */
  public Dungeon(WrappingType wrappingType, int rows, int columns, int interconnectivity,
                 int percentageOfCavesWithTreasure, int percentageOfCavesWithMonsters)
          throws IllegalArgumentException {

    if (rows < 0 || columns < 0 || rows > 100 || columns > 100 || interconnectivity < 0
            || percentageOfCavesWithTreasure < 0 || percentageOfCavesWithTreasure > 100
            || percentageOfCavesWithMonsters < 0 || percentageOfCavesWithMonsters > 100) {
      throw new IllegalArgumentException("Invalid configuration values provided.");
    }
    this.wrappingType = wrappingType;
    this.rows = rows;
    this.columns = columns;
    this.interconnectivity = interconnectivity;

    this.edgeCountInDungeon = 0;
    this.dungeonGrid = new DungeonNode[rows][columns];
    this.edgesList = new ArrayList<>();
    this.remainingEdgesList = new ArrayList<>();
    this.locationSets = new ArrayList<>();
    createDungeon();
  } // constructor ends

  private void createDungeon() {
    // assign each point on the grid with a location which is its row and column number.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Location location = new Location(i, j);
        this.dungeonGrid[i][j] = new DungeonNode(location);
      }
    }

    // loop to initialise the dungeon grid and other required data for building the grid.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Location location = this.dungeonGrid[i][j].getLocation();
        getInitialLocationSet(i, j);
        getEdgesForSingleNode(i, j, wrappingType, location);
      }
    }

    // get dungeon with connectivity 0 using Kruskal's algorithm
    createGridWithZeroConnectivity();

    /* throw an exception if the interconnectivity specified is more than the
    number of remaining edges.
     */
    if (interconnectivity > this.remainingEdgesList.size()) {
      throw new IllegalArgumentException("Invalid configuration values provided. "
              + "Interconnectivity value is invalid.");
    }

    //  create a grid with the interconnectivity value given by the user
    createGridWithGivenConnectivityValue(interconnectivity);

    /* finally after we get all the neighbours of all the nodes in the dungeon
    grid, the location type is assigned.
     */
    assignLocationTypeToEveryNode();
  }

  @Override
  public void assignTreasure(int percentageOfCavesWithTreasure) throws IllegalArgumentException {
    if (percentageOfCavesWithTreasure < 0 || percentageOfCavesWithTreasure > 100) {
      throw new IllegalArgumentException("Invalid percentage value.");
    }
    // get total number of caves in the dungeon
    int cavesCount = getCavesCountInDungeon();

    // get total number of tunnels in the dungeon
    int tunnelsCount = getTunnelsCountInDungeon();

    int numberOfCavesToBeFilledWithTreasure = (int) Math.ceil(
            ((double) percentageOfCavesWithTreasure * cavesCount) / 100);
    assignTreasureToCaves(numberOfCavesToBeFilledWithTreasure);
  }

  @Override
  public void addArrows(int percentageOfNodesToBeAddedWithArrows) {
    if (percentageOfNodesToBeAddedWithArrows < 0 || percentageOfNodesToBeAddedWithArrows > 100) {
      throw new IllegalArgumentException("Invalid percentage value.");
    }

    int totalNumberOfNodes = this.rows * this.columns;

    int numberOfNodesToBeFilledWithArrows = (int) Math.ceil(
            ((double) percentageOfNodesToBeAddedWithArrows * totalNumberOfNodes) / 100);
    addArrowsToDungeon(numberOfNodesToBeFilledWithArrows);
  }

  @Override
  public void addOtyughsMonsters(int percentageOfCavesWithMonsters,
                                 int endPointLocationId, int startPointLocationId) {
    if (percentageOfCavesWithMonsters < 0 || percentageOfCavesWithMonsters > 100) {
      throw new IllegalArgumentException("Invalid percentage value.");
    }

    // get total number of caves in the dungeon
    int cavesCount = getCavesCountInDungeon();

    int numberOfCavesToBeHousedWithMonsters = 0;

    if (percentageOfCavesWithMonsters == 0) {
      numberOfCavesToBeHousedWithMonsters = 1;
    } else {
      numberOfCavesToBeHousedWithMonsters = (int) Math.ceil(
              ((double) percentageOfCavesWithMonsters * cavesCount) / 100);
    }

    addOtyughsMonstersToCaves(numberOfCavesToBeHousedWithMonsters,
            endPointLocationId, startPointLocationId);
  }

  @Override
  public DungeonNode[][] getDungeonGrid() {
    DungeonNode[][] dunGrid = this.dungeonGrid;
    return dunGrid;
  }

  @Override
  public int getEdgeCountInDungeon() {
    int edgeCount = this.edgeCountInDungeon;
    return edgeCount;
  }

  private int getPositionOfNodeInLocationSets(Integer node) {
    for (int i = 0; i < locationSets.size(); i++) {
      for (int j = 0; j < locationSets.get(i).size(); j++) {
        if (node.equals(locationSets.get(i).get(j))) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public Location getLocationFromLocationId(int locationId) {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocationId() == locationId) {
          Location location = this.dungeonGrid[i][j].getLocation();
          return location;
        }
      }
    }
    return new Location(-1, -1);
  }

  @Override
  public int getDungeonSize() {
    int dunSize = this.rows * this.columns;
    return dunSize;
  }

  @Override
  public ArrayList<Integer> getCaves() {
    ArrayList<Integer> cavesList = new ArrayList<>();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocationType() == LocationType.CAVE) {
          cavesList.add(this.dungeonGrid[i][j].getLocationId());
        }
      }
    }
    return cavesList;
  }

  private int getCavesCountInDungeon() {
    int cavesCount = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocationType() == LocationType.CAVE) {
          cavesCount++;
        }
      }
    }
    return cavesCount;
  }

  private int getTunnelsCountInDungeon() {
    int tunnelsCount = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.dungeonGrid[i][j].getLocationType() == LocationType.TUNNEL) {
          tunnelsCount++;
        }
      }
    }
    return tunnelsCount;
  }

  private void getInitialLocationSet(int i, int j) {
    // creating a set of location. Initially all sets will have one location stored
    ArrayList<Integer> locationsSubSet = new ArrayList<>();
    locationsSubSet.add(this.dungeonGrid[i][j].getLocationId());
    this.locationSets.add(locationsSubSet);
  }

  private void getEdgesForSingleNode(int i, int j, WrappingType wrappingType, Location location) {
    // code to create the possible edges with the present location
    if (location.getX() + 1 < this.rows) {
      ArrayList<Integer> edgeSubSet = new ArrayList<>();
      edgeSubSet.add(this.dungeonGrid[i][j].getLocationId());
      edgeSubSet.add(this.dungeonGrid[i + 1][j].getLocationId());
      edgesList.add(edgeSubSet);
    }
    if (location.getY() + 1 < this.columns) {
      ArrayList<Integer> edgeSubSet = new ArrayList<>();
      edgeSubSet.add(this.dungeonGrid[i][j].getLocationId());
      edgeSubSet.add(this.dungeonGrid[i][j + 1].getLocationId());
      edgesList.add(edgeSubSet);
    }

    // getting the possible edges when the dungeon is wrapping
    if (wrappingType == WrappingType.WRAPPING) {
      if (location.getX() + 1 == this.rows) {
        ArrayList<Integer> edgeSubSet = new ArrayList<>();
        edgeSubSet.add(this.dungeonGrid[i][j].getLocationId());
        edgeSubSet.add(this.dungeonGrid[0][j].getLocationId());
        edgesList.add(edgeSubSet);
      }
      if (location.getY() + 1 == this.columns) {
        ArrayList<Integer> edgeSubSet = new ArrayList<>();
        edgeSubSet.add(this.dungeonGrid[i][j].getLocationId());
        edgeSubSet.add(this.dungeonGrid[i][0].getLocationId());
        edgesList.add(edgeSubSet);
      }
    }
  }

  private void createGridWithZeroConnectivity() {
    // actual implementation of the Kruskal's algorithm
    while (this.edgesList.size() != 0) {

      //            int randomEdge = getRandomNumber(0, this.edgesList.size());
      //            ArrayList<Integer> singleEdge = edgesList.remove(randomEdge);  // randomised

      int mockRandomNumber = RandomNumberGenerator.getFakeRandomNumber(0);
      ArrayList<Integer> singleEdge = edgesList.remove(mockRandomNumber); // not randomised

      int node1 = singleEdge.get(0);
      int node2 = singleEdge.get(1);

      int node1LocationInLocationSets = getPositionOfNodeInLocationSets(node1);
      int node2LocationInLocationSets = getPositionOfNodeInLocationSets(node2);

      // when the nodes of the edge selected are not in the same location set
      if (node1LocationInLocationSets != node2LocationInLocationSets) {
        this.edgeCountInDungeon++;
        Location node1Coordinates = getLocationFromLocationId(node1);
        Location node2Coordinates = getLocationFromLocationId(node2);

        //For node 1 add node 2 as the neighbour
        this.dungeonGrid[node1Coordinates.getX()][node1Coordinates.getY()].addNeighbours(node2);
        Pair possibleMove = getPossibleMove(node2, node1Coordinates, node2Coordinates);
        if (possibleMove != null) {
          this.dungeonGrid[node1Coordinates.getX()][node1Coordinates.getY()]
                  .addPossibleMoves(possibleMove);
        }

        //For node 2 add node 1 as the neighbour
        this.dungeonGrid[node2Coordinates.getX()][node2Coordinates.getY()].addNeighbours(node1);
        possibleMove = getPossibleMove(node1, node2Coordinates, node1Coordinates);
        if (possibleMove != null) {
          this.dungeonGrid[node2Coordinates.getX()][node2Coordinates.getY()]
                  .addPossibleMoves(possibleMove);
        }

        // we need to merge the 2 sets in which node 1 and node 2 are located
        this.locationSets.get(node1LocationInLocationSets).addAll(
                this.locationSets.get(node2LocationInLocationSets));
        this.locationSets.remove(this.locationSets.get(node2LocationInLocationSets));

      } else {
        // when the nodes of the edge selected are in the same location set
        this.remainingEdgesList.add(singleEdge);
      }
    }
    // completed implemented of Kruskal's algorithm. Got a MST with degree of connectivity 0.
  }

  private Pair getPossibleMove(int node, Location node1Coordinates, Location node2Coordinates) {
    int xcDiff = node1Coordinates.getX() - node2Coordinates.getX();
    int ycDiff = node1Coordinates.getY() - node2Coordinates.getY();

    if (xcDiff == 0) {
      if ((ycDiff == -1) || (this.wrappingType == WrappingType.WRAPPING
              && ycDiff == this.columns - 1)) {
        return (new Pair(node, Directions.EAST));
      } else if ((ycDiff == 1) || (this.wrappingType == WrappingType.WRAPPING
              && ycDiff == (-1 * (this.columns - 1)))) {
        return (new Pair(node, Directions.WEST));
      }
    } else if (ycDiff == 0) {
      if ((xcDiff == 1) || (this.wrappingType == WrappingType.WRAPPING
              && xcDiff == (-1 * (this.rows - 1)))) {
        return (new Pair(node, Directions.NORTH));
      } else if ((xcDiff == -1) || (this.wrappingType == WrappingType.WRAPPING
              && xcDiff == this.rows - 1)) {
        return (new Pair(node, Directions.SOUTH));
      }
    }
    return null;
  }

  private void createGridWithGivenConnectivityValue(int interconnectivity) {
    // loop to get the grid with the desired interconnectivity

    for (int i = 0; i < interconnectivity; i++) {

      // int randomEdge = getRandomNumber(0, this.remainingEdgesList.size());
      // ArrayList<Integer> singleEdge = this.remainingEdgesList.remove(randomEdge); // randomised

      int mockRandom = RandomNumberGenerator.getFakeRandomNumber(0);
      ArrayList<Integer> singleEdge = this.remainingEdgesList.remove(mockRandom); // not randomised

      this.edgeCountInDungeon++;
      int node1 = singleEdge.get(0);
      int node2 = singleEdge.get(1);

      Location node1Coordinates = getLocationFromLocationId(node1);
      Location node2Coordinates = getLocationFromLocationId(node2);

      //For node 1 add node 2 as the neighbour
      this.dungeonGrid[node1Coordinates.getX()][node1Coordinates.getY()].addNeighbours(node2);
      Pair possibleMove = getPossibleMove(node2, node1Coordinates, node2Coordinates);
      if (possibleMove != null) {
        this.dungeonGrid[node1Coordinates.getX()][node1Coordinates.getY()]
                .addPossibleMoves(possibleMove);
      }

      //For node 2 add node 1 as the neighbour
      this.dungeonGrid[node2Coordinates.getX()][node2Coordinates.getY()].addNeighbours(node1);
      possibleMove = getPossibleMove(node1, node2Coordinates, node1Coordinates);
      if (possibleMove != null) {
        this.dungeonGrid[node2Coordinates.getX()][node2Coordinates.getY()]
                .addPossibleMoves(possibleMove);
      }

    }
    // end of for loop which constructs the grid with the desired interconnectivity
  }

  private void assignLocationTypeToEveryNode() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        int numberOfEntrances = this.dungeonGrid[i][j].getNeighbours().size();
        if (numberOfEntrances == 2) {
          this.dungeonGrid[i][j].setLocationType(LocationType.TUNNEL);
        } else {
          this.dungeonGrid[i][j].setLocationType(LocationType.CAVE);
        }
      }
    }
  }

  private void addArrowsToDungeon(int numberOfNodesToBeFilledWithArrows) {
    ArrayList<Integer> nodeSet = new ArrayList<>();
    for (int i = 1; i <= (this.rows * this.columns); i++) {
      nodeSet.add(i);
    }

    int countOfNodesAddedWithArrows = 0;
    while (countOfNodesAddedWithArrows != numberOfNodesToBeFilledWithArrows) {

      // int randomNodeIndex = getRandomNumber(0, nodeSet.size());
      // int randomNode = nodeSet.remove(randomNodeIndex); // randomised

      int mockRandom = RandomNumberGenerator.getFakeRandomNumber(0);
      int randomNode = nodeSet.remove(mockRandom); // not randomised

      Location locationOfRandomNode = getLocationFromLocationId(randomNode);
      // int arrowCount = getRandomNumber(0, 4); // randomised
      int arrowCount = RandomNumberGenerator.getFakeRandomNumber(2); // not randomised

      this.dungeonGrid[locationOfRandomNode.getX()][locationOfRandomNode.getY()]
              .setArrowCount(arrowCount);
      countOfNodesAddedWithArrows++;
    }

  }

  private void assignTreasureToCaves(int numberOfCavesToBeFilledWithTreasure) {
    ArrayList<Integer> nodeSet = new ArrayList<>();
    for (int i = 1; i <= (this.rows * this.columns); i++) {
      nodeSet.add(i);
    }

    int countOfCavesAssignedWithTreasure = 0;
    while (countOfCavesAssignedWithTreasure != numberOfCavesToBeFilledWithTreasure) {

      // int randomNodeIndex = getRandomNumber(0, nodeSet.size());
      //  int randomNode = nodeSet.remove(randomNodeIndex); // randomised

      int mockRandom = RandomNumberGenerator.getFakeRandomNumber(0);
      int randomNode = nodeSet.remove(mockRandom); // not randomised

      Location locationOfRandomNode = getLocationFromLocationId(randomNode);
      if (this.dungeonGrid[locationOfRandomNode.getX()][locationOfRandomNode.getY()]
              .getLocationType() == LocationType.CAVE) {

        //get list of the type of treasures in the treasury
        ArrayList<TreasureCatalog> treasureCatalog = new ArrayList<>();
        for (TreasureCatalog treasure : TreasureCatalog.values()) {
          treasureCatalog.add(treasure);
        }

        // pick any 2 types of treasure from treasure catalog
        for (int i = 0; i < 2; i++) {
          // int randomTreasureIndex = getRandomNumber(0, treasureCatalog.size());
          // TreasureCatalog treasure = treasureCatalog.remove(randomTreasureIndex); // randomised

          mockRandom = RandomNumberGenerator.getFakeRandomNumber(0);
          TreasureCatalog treasure = treasureCatalog.remove(mockRandom); // not randomised

          Treasure treasureToBeAdded = null;
          if (treasure == TreasureCatalog.DIAMOND) {
            treasureToBeAdded = new Diamond();
          } else if (treasure == TreasureCatalog.RUBY) {
            treasureToBeAdded = new Ruby();
          } else if (treasure == TreasureCatalog.SAPPHIRE) {
            treasureToBeAdded = new Sapphire();
          }
          this.dungeonGrid
                  [locationOfRandomNode.getX()][locationOfRandomNode.getY()]
                  .addTreasure(treasureToBeAdded);
        }
        countOfCavesAssignedWithTreasure++;
      } else {
        continue;
      }
    }
  }

  private void addOtyughsMonstersToCaves(int numberOfCavesToBeHousedWithMonsters,
                                         int endPointLocationId, int startPointLocationId) {
    ArrayList<Integer> nodeSet = new ArrayList<>();
    for (int i = 1; i <= (this.rows * this.columns); i++) {
      nodeSet.add(i);
    }

    // add monster to end cave
    Location endNode = getLocationFromLocationId(endPointLocationId);
    this.dungeonGrid[endNode.getX()][endNode.getY()].addMonster();


    int countOfCavesAddedWithMonster = 1;
    while (countOfCavesAddedWithMonster != numberOfCavesToBeHousedWithMonsters) {

      int mockRandom = RandomNumberGenerator.getFakeRandomNumber(0);
      int randomNode = nodeSet.remove(mockRandom); // not randomised

      // int randomNodeIndex = RandomNumberGenerator.getRandomNumberInRange(0, nodeSet.size());
      // int randomNode = nodeSet.remove(randomNodeIndex); // randomised

      Location locationOfRandomNode = getLocationFromLocationId(randomNode);
      if (this.dungeonGrid[locationOfRandomNode.getX()][locationOfRandomNode.getY()]
              .getLocationType() == LocationType.CAVE && randomNode != startPointLocationId) {
        this.dungeonGrid[locationOfRandomNode.getX()][locationOfRandomNode.getY()].addMonster();
      }
      countOfCavesAddedWithMonster++;
    }

  }

  private int getRandomNumber(int lowerLimit, int upperLimit) {
    return lowerLimit + (int) (Math.random() * ((upperLimit - lowerLimit)));
  }

  @Override
  public boolean hasPathBetweenStartAndEndPoint(int start, int end) {
    int startVertex = start;
    int endVertex = end;

    ArrayList<Integer> queue = new ArrayList<>();
    ArrayList<Integer> visited = new ArrayList<>();

    visited.add(startVertex);
    int currentVertex = startVertex;

    while (currentVertex != endVertex) {
      Location location = getLocationFromLocationId(currentVertex);
      ArrayList<Integer> adjacents = getDungeonGrid()
              [location.getX()][location.getY()]
              .getNeighbours();
      for (int i = 0; i < adjacents.size(); i++) {

        int neighbour = adjacents.get(i);
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
        }
      }
      currentVertex = queue.remove(0);
    }
    return visited.contains(start) && visited.contains(end);
  }

  @Override
  public String getNeighboursInformation() {
    String dungeon = "";
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        dungeon = dungeon + this.dungeonGrid[i][j].getNodeWithNeighbours() + " ";
      }
      dungeon = dungeon + "\n";
    }
    return dungeon;
  }

  @Override
  public String toString() {
    String dungeon = "";
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        dungeon = dungeon + this.dungeonGrid[i][j].toString() + " ";
      }
      dungeon = dungeon + "\n";
    }
    return dungeon;
  }
}
