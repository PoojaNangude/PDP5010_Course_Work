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
    game = new Game(WrappingType.WRAPPING, 6, 6,
            13, 30, 40);
  }

  @Test
  public void testPlayerWins() {
    /*
     Player and player location details at the start position
     */
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 2)(arrows: 3)",
            game.getPlayer().toString());
    assertEquals("ID(7) N(1NORTH 13SOUTH 8EAST 12WEST )(1 13 8 12 ) "
            + "(CA) (treasure: D R )  (Arrows: 2)",
            game.getPlayerPresentLocationDetails().toString());
    // player collects treasure
    game.collectTreasure();
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 3)",
            game.getPlayer().toString());
    // player collects arrows
    game.collectArrows();
    // player smell senses (2 means more pungent smell detected by the player)
    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    assertEquals(5, game.getPlayer().getArrowCount());
    // player shoots an arrow (1 is returned when player injures a monster)
    assertEquals(1, game.shootArrow(Directions.EAST, 2));
    // player shoots an arrow (2 is returned when player kills a monster)
    assertEquals(2, game.shootArrow(Directions.EAST,2 ));
    // player shoots an arrow (0 is returned when player misses a shot)
    assertEquals(0, game.shootArrow(Directions.SOUTH, 4));
    assertFalse(game.isGameOver());

    /*
    Player now moves to another location
     */
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals("Location Id (13)Co-ordinates: (2,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals("ID(13) N(7NORTH 19SOUTH 14EAST 18WEST )(7 19 14 18 ) (CA)   "
            + "(Arrows: 0)", game.getPlayerPresentLocationDetails().toString());
    // player smell senses (2 means more pungent smell detected by the player)
    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    assertFalse(game.isGameOver());

    /*
    Player now moves to another location
     */
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals("Location Id (19)Co-ordinates: (3,0)Treasure "
            + "collected(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 1)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals("ID(19) N(13NORTH 25SOUTH )(13 25 ) (TU)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());
    // player smell senses (1 means less pungent smell detected by the player)
    assertEquals(1, game.getPlayer().getLevelOfPungentSmellDetected());
    assertFalse(game.isGameOver());

    /*
    Player now moves to another location
     */
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals("Location Id (25)Co-ordinates: (4,0)Treasure "
            + "collected(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 0)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals("ID(25) N(19NORTH 31SOUTH )(19 31 ) (TU)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());
    // player smell senses (1 means no smell is detected by the player)
    assertEquals(0, game.getPlayer().getLevelOfPungentSmellDetected());
    assertFalse(game.isGameOver());

    /*
    Player navigating the dungeon
     */
    game.movePlayerInGivenDirection(Directions.NORTH);
    game.movePlayerInGivenDirection(Directions.NORTH);
    game.movePlayerInGivenDirection(Directions.EAST);
    assertEquals("Location Id (14)Co-ordinates: (2,1)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    // player smell senses (2 means more pungent smell detected by the player)
    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    // player shoots arrow
    assertEquals(1, game.shootArrow(Directions.NORTH, 1));
    assertEquals(2, game.shootArrow(Directions.NORTH, 1));
    assertFalse(game.isGameOver());

    /*
     * Player continuing to navigate in the dungeon
     */
    game.movePlayerInGivenDirection(Directions.NORTH);
    assertEquals("Location Id (8)Co-ordinates: (1,1)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 0)",
            game.getPlayer().toString());
    assertEquals("ID(8) N(2NORTH 14SOUTH 7WEST 9EAST )(2 14 7 9 ) (CA) "
            + "(treasure: D R ) (M 0 ) (Arrows: 2)",
            game.getPlayerPresentLocationDetails().toString());
    // -1 is returned when player is out of arrows
    assertEquals(-1, game.shootArrow(Directions.NORTH, 1));
    game.collectArrows();
    assertEquals(2, game.getPlayer().getArrowCount());
    game.collectTreasure();
    assertEquals("Location Id (8)Co-ordinates: (1,1)"
            + "Treasure collected(DIAMOND:2 RUBY:2 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertFalse(game.isGameOver());

    /*
    Player continues to navigate through the dungeon
     */
    game.movePlayerInGivenDirection(Directions.WEST);
    game.movePlayerInGivenDirection(Directions.WEST);
    game.shootArrow(Directions.SOUTH, 1);
    game.shootArrow(Directions.SOUTH, 1);
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertTrue(game.getPlayer().getIsAlive());
    assertTrue(game.getPlayerPresentLocationDetails()
            .getLocationId() == game.getEndPointLocationId());
    assertTrue(game.getPlayerPresentLocationDetails().getMonster().getHealth() == 0);
    assertTrue(game.isGameOver());
  }

}
