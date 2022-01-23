package dungeon;

import java.util.ArrayList;
import location.Location;
import monster.Otyugh;
import treasure.Treasure;

/**
 * Class which is used to return the dump of the dungeon view.
 */
public class DungeonDump {
  private final int locationId;
  private final Location location;
  private final String possibleMoves;
  private final ArrayList<Treasure> treasure;
  private final int arrowCount;
  private final Otyugh monster;
  private final boolean visited;
  private final boolean hasThief;
  private final boolean hasPit;

  /**
   * Constructs the representation of a single location which will be used by the view.
   *
   * @param locationId type integer
   * @param location type Location class
   * @param possibleMoves type enum Location
   * @param treasure type enum TreasureCatalog
   * @param arrowCount type integer
   * @param monster type Otyugh class
   * @param visited type boolean
   */
  public DungeonDump(int locationId, Location location, String possibleMoves,
                     ArrayList<Treasure> treasure, int arrowCount, Otyugh monster,
                     boolean visited, boolean hasThief, boolean hasPit) {
    this.locationId = locationId;
    this.location = location;
    this.possibleMoves = possibleMoves;
    this.treasure = treasure;
    this.arrowCount = arrowCount;
    this.monster = monster;
    this.visited = visited;
    this.hasThief = hasThief;
    this.hasPit = hasPit;
  }

  public int getLocationId() {
    return locationId;
  }

  public Location getLocation() {
    return location;
  }

  public String getPossibleMoves() {
    return possibleMoves;
  }

  public ArrayList<Treasure> getTreasure() {
    return treasure;
  }

  public int getArrowCount() {
    return arrowCount;
  }

  public Otyugh getMonster() {
    return monster;
  }

  public boolean getVisited() {
    return visited;
  }

  public boolean getHasThief() {
    return this.hasThief;
  }

  public boolean getHasPit() {
    return this.hasPit;
  }
}
