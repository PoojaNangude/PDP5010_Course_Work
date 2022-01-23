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
  private int levelOfPungentSmellDetected;
  private int senseOfWind;
  private int arrowCount;
  private boolean isAlive;

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
    this.levelOfPungentSmellDetected = 0;
    this.senseOfWind = 0;
    this.arrowCount = 3;
    this.isAlive = true;
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
  public int getLevelOfPungentSmellDetected() {
    int levelOfPungentSmell = this.levelOfPungentSmellDetected;
    return levelOfPungentSmell;
  }

  @Override
  public int getArrowCount() {
    int numberOfArrowsOwned = this.arrowCount;
    return numberOfArrowsOwned;
  }

  @Override
  public boolean getIsAlive() {
    boolean isPlayerAlive = this.isAlive;
    return isPlayerAlive;
  }

  @Override
  public int getSenseOfWind() {
    return this.senseOfWind;
  }

  @Override
  public void setIsAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  @Override
  public void setSenseOfWind(int senseOfWind) {
    this.senseOfWind = senseOfWind;
  }

  @Override
  public void addTreasure(Treasure treasure) {
    this.treasureCollected.add(treasure);
  }

  @Override
  public void addArrows(int arrowCount) {
    this.arrowCount = this.arrowCount + arrowCount;
  }

  @Override
  public void decrementArrowCount() {
    this.arrowCount = this.arrowCount - 1;
  }

  @Override
  public void takeAwayTreasure() {
    while (this.treasureCollected.size() != 0) {
      this.treasureCollected.remove(0);
    }
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
  public void setLevelOfPungentSmellDetected(int levelOfPungentSmellDetected) {
    this.levelOfPungentSmellDetected = levelOfPungentSmellDetected;
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
    player = player + "(smell: " + this.levelOfPungentSmellDetected + ")";
    player = player + "(arrows: " + this.arrowCount + ")";
    return player;
  }

  @Override
  public String getTreasureCollectedSummary() {
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
