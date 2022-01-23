package dungeon;

import java.util.ArrayList;
import location.Location;
import treasure.Treasure;

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

  /**
   * Constructor for creating a single node in the 2D representation of a dungeon.
   *
   * @param location takes in the coordinate values from the Dungeon and RandomDungeon class
   *                 and also assigns a unique location ID to every node.
   */
  public DungeonNode(Location location) {
    this.locationId = ++ID;
    this.location = location;
    this.neighbours = new ArrayList<>();
    this.locationType = LocationType.CAVE;
    this.treasure = new ArrayList<>();
  }

  public Location getLocation() {
    Location dLocation = this.location;
    return dLocation;
  }

  public int getLocationId() {
    int dLocationId = this.locationId;
    return dLocationId;
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

  public void setLocationType(LocationType locationType) {
    this.locationType = locationType;
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
    String neighbours = "";
    for (int i = 0; i < this.neighbours.size(); i++) {
      neighbours = neighbours + this.neighbours.get(i) + " ";
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
    String dungeonNode = String.format("ID(%d) Neighbours/possible moves(%s) (%s) %s",
            this.locationId, neighbours, (this.locationType.toString()).substring(0, 2), treasure);
    return dungeonNode;
  }
}
