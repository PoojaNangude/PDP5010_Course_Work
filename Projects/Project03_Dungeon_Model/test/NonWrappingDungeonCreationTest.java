import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.IDungeon;
import dungeon.LocationType;
import dungeon.WrappingType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests to check if a non wrapping dungeon is created
 * properly by creating a deterministic dungeon.
 */
public class NonWrappingDungeonCreationTest {
  IDungeon dungeon;

  @Before
  public void setUp() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, 5, 6, 8);
  }

  @Test
  public void testCreateDungeon() {
    // testing for non-wrapping dungeon
    String expectedDungeon = "ID(1) N(7 2 ) ID(2) N(1 8 3 ) ID(3) N(2 9 4 ) ID(4) N(3 10 5 ) "
            + "ID(5) N(4 11 6 ) ID(6) N(5 12 ) \n"
            + "ID(7) N(1 13 8 ) ID(8) N(2 14 7 9 ) ID(9) N(3 15 8 10 ) ID(10) N(4 16 9 11 ) "
            + "ID(11) N(5 17 10 12 ) ID(12) N(6 18 11 ) \n"
            + "ID(13) N(7 19 14 ) ID(14) N(8 20 13 15 ) ID(15) N(9 21 14 16 ) ID(16) N(10 22 15 ) "
            + "ID(17) N(11 23 ) ID(18) N(12 24 ) \n"
            + "ID(19) N(13 25 ) ID(20) N(14 26 ) ID(21) N(15 27 ) ID(22) N(16 28 ) ID(23) "
            + "N(17 29 ) ID(24) N(18 30 ) \n"
            + "ID(25) N(19 ) ID(26) N(20 ) ID(27) N(21 ) ID(28) N(22 ) ID(29) "
            + "N(23 ) ID(30) N(24 ) \n";
    assertEquals(expectedDungeon, dungeon.getNeighboursInformation());

    //test interconnectivity
    assertEquals(37, dungeon.getEdgeCountInDungeon());

    //test presence of path between any two nodes
    for (int i = 1; i <= 5; i++) {
      for (int j = 1; j <= 6; j++) {
        assertTrue(dungeon.hasPathBetweenStartAndEndPoint(i, j));
      }
    }

    // test if correct number of caves have been assigned th treasure
    dungeon.assignTreasure(20);
    int caveCount = dungeon.getCaves().size();
    int numberOfCavesToBeFilledWithTreasure = (int) Math.ceil(((double) 20 * caveCount) / 100);
    int countOfCavesWithTreasure = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (dungeon.getDungeonGrid()[i][j].getTreasure().size() > 0
                && dungeon.getDungeonGrid()[i][j].getLocationType() == LocationType.CAVE) {
          countOfCavesWithTreasure++;
        }
      }
    }
    assertEquals(numberOfCavesToBeFilledWithTreasure, countOfCavesWithTreasure);
  }
}