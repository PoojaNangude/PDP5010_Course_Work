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
 * This class will test all the functionalities of the game class like,
 * collect treasure or arrows, move player or shoot arrow.
 */
public class GameFunctionalityTest {
  IGame game;

  @Before
  public void setUp() {
    game = new Game(WrappingType.WRAPPING, 6, 6,
            13, 30, 40);
  }



  @Test
  public void testGameFunctionalities() {

    /*
     Test if player is able to pick up treasure
     */
    // player details before collecting treasure
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 2)(arrows: 3)", game.getPlayer().toString());
    assertTrue(game.collectTreasure());
    // player details after collecting treasure
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 3)", game.getPlayer().toString());
    assertFalse(game.collectTreasure());
    // player details after trying to collect treasure from a location which has no treasure
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 3)", game.getPlayer().toString());


    /*
    Test if player is able to use the shoot arrow functionality properly
     */
    // when -1 is returned it means player is out of arrows
    // when 0 is returned when player missed a shot
    // when 1 is returned it means player injured a monster
    // when 2 is returned it means player killed a monster

    // test when player shoots arrow and injures a monster
    assertEquals(1, game.shootArrow(Directions.EAST, 1));

    // test when player shoots an arrow and kills a monster
    assertEquals(2, game.shootArrow(Directions.EAST,1));

    // test when player shoots an arrows but misses the shot
    assertEquals(0, game.shootArrow(Directions.SOUTH, 2));

    // test when player wants to shoot an arrow but is out of arrows
    assertEquals(-1, game.shootArrow(Directions.SOUTH, 3));

    // test when given distance is negative for shooting arrow
    try {
      game.shootArrow(Directions.NORTH, -1);
    } catch (Exception e) {
      assertEquals("java.lang.IllegalArgumentException", e.toString());
    }

    // test when given distance is zero for shooting arrow
    try {
      game.shootArrow(Directions.NORTH, 0);
    } catch (Exception e) {
      assertEquals("java.lang.IllegalArgumentException", e.toString());
    }

    /*
    Test if player is able to collect arrows from a location, if the location has arrows.
     */
    // arrow count before collecting treasure
    assertEquals(0, game.getPlayer().getArrowCount());
    assertTrue(game.collectArrows());
    // arrow count after collecting arrows from a location
    assertEquals(2, game.getPlayer().getArrowCount());
    // arrow count after trying to collect from a location which does not have arrows
    assertFalse(game.collectArrows());
    assertEquals(2, game.getPlayer().getArrowCount());

    /*
    Test if player is able to move by specifying the direction
     */

    // 1 is returned when player is successfully moved in the desired dorection
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals("ID(7) N(1NORTH 13SOUTH 8EAST 12WEST )(1 13 8 12 ) (CA)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.WEST));
    assertEquals("Location Id (12)Co-ordinates: (1,5)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.SOUTH));
    assertEquals("Location Id (13)Co-ordinates: (2,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.NORTH));
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
            + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 2)",
            game.getPlayer().toString());

    /*
     * Check if the smelling senses of the player are updated as he moves in different locations
     */
    // 0 means no smell
    // 1 means a less pungent smell
    // 2 means a more pungent smell

    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals(1, game.getPlayer().getLevelOfPungentSmellDetected());
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals(0, game.getPlayer().getLevelOfPungentSmellDetected());

    /*
    Test if player is able to move if he selects either North, South, East, West but there is no
    path in that direction
     */

    // as we can see below the at the present location player can move only north and south
    assertEquals("ID(25) N(19NORTH 31SOUTH )(19 31 ) (TU)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());

    // the function will return a 0 value when there is no path in the specified direction
    assertEquals(0, game.movePlayerInGivenDirection(Directions.EAST));
    assertEquals(0, game.movePlayerInGivenDirection(Directions.WEST));
  }
}