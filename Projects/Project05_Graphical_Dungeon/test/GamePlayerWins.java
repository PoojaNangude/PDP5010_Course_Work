import org.junit.Before;
import org.junit.Test;

import dungeon.Directions;
import dungeon.WrappingType;
import game.Game;
import game.IGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test case to check the outcomes of every move player makes until he wins the game.
 */
public class GamePlayerWins {
  IGame game;

  @Before
  public void setUp() {
    game = new Game(WrappingType.NON_WRAPPING, 6, 6,
            12, 30, 20);
  }

  @Test
  public void testPlayerWins() {
    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.shootArrow(Directions.SOUTH,1));
    assertFalse(game.isGameOver());

    assertEquals(2, game.shootArrow(Directions.SOUTH,1));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.SOUTH));
    assertTrue(game.isGameOver());
    assertTrue(game.getPlayer().getIsAlive());
  }

}
