package player;

import java.util.ArrayList;
import location.Location;
import treasure.Treasure;
import treasure.TreasureCatalog;

/**
 * Player class which represents a single player.
 * A single player is represented by the treasure collected,
 * and its location in terms of locationId and the location co-ordinates.
 */
public class Player implements IPlayer {
  private ArrayList<Treasure> treasureCollected;
  private Location playerCoordinates;
  private int playerLocationId;

  /**
   * Constructor to create a player when the start point is assigned in the game.
   *
   * @param playerCoordinates of type Location
   * @param playerLocationId of type integer
   */
  public Player(Location playerCoordinates, int playerLocationId) {
    this.treasureCollected = new ArrayList<>();
    this.playerCoordinates = playerCoordinates;
    this.playerLocationId = playerLocationId;
  }

  public ArrayList<Treasure> getTreasureCollected() {
    ArrayList<Treasure> treasureCollected = this.treasureCollected;
    return treasureCollected;
  }

  @Override
  public Location getPlayerCoordinates() {
    Location playerCoordinates = this.playerCoordinates;
    return playerCoordinates;
  }

  @Override
  public int getPlayerLocationId() {
    int locationId = this.playerLocationId;
    return locationId;
  }

  @Override
  public void addTreasure(Treasure treasure) {
    this.treasureCollected.add(treasure);
  }

  @Override
  public void setPlayerCoordinates(Location playerCoordinates) {
    this.playerCoordinates = playerCoordinates;
  }

  @Override
  public void setPlayerLocationId(int playerLocationId) {
    this.playerLocationId = playerLocationId;
  }

  @Override
  public String toString() {
    String player = "";
    player = player + "Location Id (" + this.playerLocationId + ")"
            + "Co-ordinates: (" + this.playerCoordinates.getX() + ","
            + this.playerCoordinates.getY() + ")";
    player = player + "Treasure collected(";
    player = player + getTreasureCollectedSummary();
    player = player + ")";
    return player;
  }

  private String getTreasureCollectedSummary() {
    String treasureSummary = "";
    for (TreasureCatalog treasure : TreasureCatalog.values()) {
      int treasureCounter = 0;
      for (int i = 0; i < this.treasureCollected.size(); i++) {
        if (treasure.toString() == this.treasureCollected.get(i).getTreasureType().toString()) {
          treasureCounter++;
        }
      }
      treasureSummary = treasureSummary + treasure.toString() + ":" + treasureCounter + " ";
    }
    return treasureSummary;
  }
}
