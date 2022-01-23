import org.junit.Before;
import org.junit.Test;

import dungeon.Directions;
import dungeon.WrappingType;
import game.Game;
import game.IGame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test case to check the outcomes of every move player makes until the player is
 * eventually defeated by falling into a hollow pit.
 */
public class GamePlayerLostPitfall {
  IGame game;

  @Before
  public void setUp() {
    game = new Game(WrappingType.NON_WRAPPING, 6, 6,
            12, 30, 20);
  }

  @Test
  public void testPlayerWins() {
    game.movePlayerInGivenDirection(Directions.NORTH);
    game.collectArrows();
    assertFalse(game.isGameOver());

    game.shootArrow(Directions.EAST, 1);
    game.shootArrow(Directions.EAST,1);
    assertFalse(game.isGameOver());

    game.movePlayerInGivenDirection(Directions.EAST);
    game.collectTreasure();
    assertFalse(game.isGameOver());

    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertFalse(game.isGameOver());

    game.collectTreasure();
    game.collectArrows();
    assertFalse(game.isGameOver());

    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertTrue(game.isGameOver());
  }
}