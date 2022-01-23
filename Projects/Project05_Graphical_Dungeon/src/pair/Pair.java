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

  /*
  Returns the location Id of the location in the dungeon.
   */
  public int getLocationId() {
    return this.locationId;
  }

  /*
  Returns the possible moves from the present location i.e. NORTH, SOUTH, EAST, WEST
   */
  public Directions getDirectionFromPresentLocation() {
    return this.directionFromPresentLocation;
  }

  /*
  Overrides the string function to return the location Id and possible moves from the location.
   */
  @Override
  public String toString() {
    return String.format("(%s %s)", this.locationId, this.directionFromPresentLocation.toString());
  }
}
