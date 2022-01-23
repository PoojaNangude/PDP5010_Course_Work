package dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import location.Location;
import monster.Otyugh;
import pair.Pair;
import treasure.Treasure;
import treasure.TreasureCatalog;

/**
 * DungeonNode class which represent the attributes of every node of the dungeon.
 * Every node in the dungeon be represented using an locationId, location, neighbours,
 * locationType (cave or tunnel) and treasure in the node.
 */
public class DungeonNode {
  private static int ID = 0;

  private int locationId;
  private final Location location;
  private ArrayList<Integer> neighbours;
  private LocationType locationType;
  private ArrayList<Treasure> treasure;
  private ArrayList<Pair> possibleMoves;
  private Otyugh monster;
  private int arrowCount;
  private boolean hasPit;
  private boolean thief;
  private boolean visited;

  /**
   * Constructor for creating a single node in the 2D representation of a dungeon.
   *
   * @param location takes in the coordinate values from the Dungeon and RandomDungeon class
   *                 and also assigns a unique location ID to every node.
   */
  public DungeonNode(Location location) {
    if (location.getX() == 0 && location.getY() == 0) {
      ID = 0;
    }
    this.locationId = ++ID;
    this.location = location;
    this.neighbours = new ArrayList<>();
    this.locationType = LocationType.CAVE;
    this.treasure = new ArrayList<>();
    this.possibleMoves = new ArrayList<>();
    this.monster = null;
    this.arrowCount = 0;
    this.visited = false;
    this.hasPit = false;
    this.thief = false;
  }

  public Location getLocation() {
    Location dunLocation = this.location;
    return dunLocation;
  }

  public int getLocationId() {
    int dunLocationId = this.locationId;
    return dunLocationId;
  }

  public ArrayList<Treasure> getTreasure() {
    ArrayList<Treasure> treasureList = this.treasure;
    return treasureList;
  }

  public ArrayList<Integer> getNeighbours() {
    ArrayList<Integer> nodeNeighbours = this.neighbours;
    return nodeNeighbours;
  }

  public LocationType getLocationType() {
    LocationType nodeLocationType = this.locationType;
    return nodeLocationType;
  }

  public ArrayList<Pair> getPossibleMoves() {
    return this.possibleMoves;
  }

  public Otyugh getMonster() {
    Otyugh dungeonNodeMonster = this.monster;
    return dungeonNodeMonster;
  }

  public int getArrowCount() {
    int numberOfArrows = this.arrowCount;
    return numberOfArrows;
  }

  public boolean getVisited() {
    return this.visited;
  }

  public boolean getHasThief() {
    return this.thief;
  }

  public boolean getHasPit() {
    return this.hasPit;
  }

  public void setLocationType(LocationType locationType) {
    this.locationType = locationType;
  }

  public void setArrowCount(int arrowCount) {
    this.arrowCount = arrowCount;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public void addNeighbours(int locationId) {
    this.neighbours.add(locationId);
  }

  public void addTreasure(Treasure treasure) {
    this.treasure.add(treasure);
  }

  public void removeTreasure(int index) {
    this.treasure.remove(index);
  }

  public void addPossibleMoves(Pair move) {
    this.possibleMoves.add(move);
  }

  public void addMonster() {
    this.monster = new Otyugh();
  }

  public void addThief() {
    this.thief = true;
  }

  public void addPit() {
    this.hasPit = true;
  }

  /**
  Function to get the summary of all the treasure present in the a location of the dungeon.
   */
  public Map<TreasureCatalog, Integer> getTreasureSummary() {
    Map<TreasureCatalog, Integer> map = new HashMap<TreasureCatalog, Integer>();
    for (TreasureCatalog treasure : TreasureCatalog.values()) {
      int count = 0;
      for (int i = 0; i < this.treasure.size(); i++) {
        if (this.treasure.get(i).getTreasureType() == treasure) {
          count++;
        }
      }
      if (count > 0) {
        map.put(treasure, count);
      }
    }
    return map;
  }

  /**
   * Function to return each node location id and neighbours in the form of string.
   */
  public String getNodeWithNeighbours() {
    String neighbours = "";
    for (int i = 0; i < this.neighbours.size(); i++) {
      neighbours = neighbours + this.neighbours.get(i) + " ";
    }
    String dungeonNode = String.format("ID(%d) N(%s)",
            this.locationId, neighbours);
    return dungeonNode;
  }

  @Override
  public String toString() {
    String neighbours1 = "";
    String neighbours2 = "";
    for (int i = 0; i < this.neighbours.size(); i++) {
      neighbours2 = neighbours2 + this.neighbours.get(i) + " ";
    }
    for (int i = 0; i < this.possibleMoves.size(); i++) {
      neighbours1 = neighbours1 + this.possibleMoves.get(i).getLocationId()
              + this.possibleMoves.get(i).getDirectionFromPresentLocation() + " ";
    }
    String treasure = "";
    if (this.locationType == LocationType.CAVE) {
      if (this.treasure.size() != 0) {
        treasure = treasure + "(treasure: ";
        for (int i = 0; i < this.treasure.size(); i++) {
          treasure = treasure + this.treasure.get(i).getTreasureType().toString().charAt(0) + " ";
        }
        treasure = treasure + ")";
      }
    }
    String monster = "";
    if (this.monster != null) {
      monster = monster + this.monster.toString();
    }
    String dungeonNode = String.format("ID(%d) N(%s)(%s) (%s) %s %s (Arrows: %s)",
            this.locationId, neighbours1, neighbours2, (this.locationType.toString())
                    .substring(0, 2), treasure, monster, this.arrowCount);
    return dungeonNode;
  }
}
