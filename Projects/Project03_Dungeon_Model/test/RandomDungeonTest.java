import org.junit.Before;
import org.junit.Test;

import dungeon.IDungeon;
import dungeon.LocationType;
import dungeon.RandomDungeon;
import dungeon.WrappingType;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests to check if a random dungeon is created properly.
 */
public class RandomDungeonTest {
  IDungeon dungeon;

  @Before
  public void setUp() {
    dungeon = new RandomDungeon(WrappingType.NON_WRAPPING, 5,6,8);
  }

  @Test
  public void testCreateDungeon() {
    /*
    We don't know which nodes are caves and which tunnels or where the treasure wil be assigned.
    We can do basic test cases on a randomised created cave.
     */

    //test interconnectivity
    assertEquals(37, dungeon.getEdgeCountInDungeon());

    // test if correct number of caves have been assigned treasure
    dungeon.assignTreasure(20);
    int caveCount = dungeon.getCaves().size();
    int numberOfCavesToBeFilledWithTreasure = (int) Math.ceil(((double) 20 * caveCount) / 100);
    int countOfCavesWithTreasure = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6; j++) {
        if (dungeon.getDungeonGrid()[i][j].getTreasure().size() > 0
                && dungeon.getDungeonGrid()[i][j].getLocationType() == LocationType.CAVE) {
          countOfCavesWithTreasure++;
        }
      }
    }
    assertEquals(numberOfCavesToBeFilledWithTreasure, countOfCavesWithTreasure);
  }

}