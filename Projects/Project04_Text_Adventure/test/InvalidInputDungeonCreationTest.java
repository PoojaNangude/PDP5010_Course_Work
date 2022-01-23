import org.junit.Test;
import dungeon.IDungeon;
import dungeon.Dungeon;
import dungeon.WrappingType;

/**
 * This class contains all the unit tests for invalid inputs for dungeon creation.
 */
public class InvalidInputDungeonCreationTest {
  IDungeon dungeon;

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUserInput_InvalidRowNumber() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, -1, 5, 6, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUserInput_InvalidColumnNumber() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, -1, 5, 6, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUserInput_InvalidInterconnectivity() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, -1, 5, 6, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUserInput_InvalidTreasurePercentageValue1() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, -1, 5, 6, 30, 40);
    dungeon.assignTreasure(-90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidUserInput_InvalidTreasurePercentageValue2() {
    dungeon = new Dungeon(WrappingType.NON_WRAPPING, -1, 5, 6, 30, 40);
    dungeon.assignTreasure(103);
  }

}