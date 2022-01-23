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
 * Test case to check the outcomes of every move player makes until the player is
 * eventually defeated by a monster.
 */
public class GamePlayerLostMonster {
  IGame game;

  @Before
  public void setUp() {
    game = new Game(WrappingType.NON_WRAPPING, 6, 6,
            12, 30, 20);
  }

  @Test
  public void testPlayerLost() {
    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertFalse(game.isGameOver());

    assertEquals(1, game.movePlayerInGivenDirection(Directions.NORTH));
    assertTrue(game.isGameOver());
  }
}
