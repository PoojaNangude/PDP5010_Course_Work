package pair;

import dungeon.Directions;

/**
 * A single pair is used for representing a locations neighbour and the direction of the neighbour
 * from the present location.
 */
public class Pair {
  private int locationId;
  private Directions directionFromPresentLocation;

  public Pair(int locationId, Directions directionFromPresentLocation) {
    this.locationId = locationId;
    this.directionFromPresentLocation = directionFromPresentLocation;
  }

  public int getLocationId() {
    return this.locationId;
  }

  public Directions getDirectionFromPresentLocation() {
    return this.directionFromPresentLocation;
  }

  @Override
  public String toString() {
    return String.format("(%s %s)", this.locationId, this.directionFromPresentLocation.toString());
  }
}
