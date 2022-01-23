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
            12, 30, 20);
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

    game.movePlayerInGivenDirection(Directions.EAST);
    // test when player shoots arrow and injures a monster
    assertEquals(1, game.shootArrow(Directions.NORTH, 1));

    // test when player shoots an arrow and kills a monster
    assertEquals(2, game.shootArrow(Directions.NORTH,1));

    // test when player shoots an arrows but misses the shot
    assertEquals(0, game.shootArrow(Directions.SOUTH, 2));


    // test when player wants to shoot an arrow but is out of arrows
    assertEquals(-1, game.shootArrow(Directions.SOUTH, 3));

    game.collectArrows();

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
    game.movePlayerInGivenDirection(Directions.EAST);
    assertEquals(2, game.getPlayer().getArrowCount());
    assertTrue(game.collectArrows());
    // arrow count after collecting arrows from a location
    assertEquals(4, game.getPlayer().getArrowCount());
    // arrow count after trying to collect from a location which does not have arrows
    assertFalse(game.collectArrows());
    assertEquals(4, game.getPlayer().getArrowCount());

    /*
    Test if player is able to move by specifying the direction
     */

    // 1 is returned when player is successfully moved in the desired dorection
    assertEquals("Location Id (9)Co-ordinates: (1,2)Treasure collected"
                    + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());
    assertEquals("ID(9) N(3NORTH 15SOUTH 8WEST 10EAST )(3 15 8 10 ) (CA)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.WEST));
    assertEquals("Location Id (8)Co-ordinates: (1,1)Treasure collected"
                    + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.EAST));
    assertEquals("Location Id (9)Co-ordinates: (1,2)Treasure collected"
                    + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.WEST));
    assertEquals(1, game.movePlayerInGivenDirection(Directions.WEST));
    assertEquals(1, game.movePlayerInGivenDirection(Directions.SOUTH));
    assertEquals("Location Id (13)Co-ordinates: (2,0)Treasure collected"
                    + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 1)(arrows: 4)",
            game.getPlayer().toString());
    assertEquals(1, game.movePlayerInGivenDirection(Directions.NORTH));
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
                    + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());

    /*
     * Check if the smelling senses of the player are updated as he moves in different locations
     */
    // 0 means no smell
    // 1 means a less pungent smell
    // 2 means a more pungent smell

    assertEquals(2, game.getPlayer().getLevelOfPungentSmellDetected());
    game.movePlayerInGivenDirection(Directions.EAST);
    game.movePlayerInGivenDirection(Directions.EAST);
    game.movePlayerInGivenDirection(Directions.EAST);
    game.movePlayerInGivenDirection(Directions.EAST);
    game.movePlayerInGivenDirection(Directions.SOUTH);
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals(1, game.getPlayer().getLevelOfPungentSmellDetected());
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals(0, game.getPlayer().getLevelOfPungentSmellDetected());

    /*
    Test if player is able to move if he selects either North, South, East, West but there is no
    path in that direction
     */

    // as we can see below the at the present location player can move only north and south
    assertEquals("ID(29) N(23NORTH 35SOUTH )(23 35 ) (TU)   (Arrows: 0)",
            game.getPlayerPresentLocationDetails().toString());

    // the function will return a 0 value when there is no path in the specified direction
    assertEquals(0, game.movePlayerInGivenDirection(Directions.EAST));
    assertEquals(0, game.movePlayerInGivenDirection(Directions.WEST));

    /*
    Check if player is able to get the windy sensation when he is around a pit.
     */
    // 0 means not windy - no pits around
    // 1 means less windy - only one pit within 2 unit distance of player
    // 2 means more windy - one pit within 1 unite distance or multiple pits within 2 unit distance

    assertEquals(0, game.getPlayer().getSenseOfWind());
    game.movePlayerInGivenDirection(Directions.NORTH);
    game.movePlayerInGivenDirection(Directions.NORTH);
    assertEquals(1, game.getPlayer().getSenseOfWind());
    game.movePlayerInGivenDirection(Directions.WEST);
    assertEquals(2, game.getPlayer().getSenseOfWind());

    /*
    Check if the player treasure collection is made empty when he encounters a thief in the dungeon
     */
    assertEquals("Location Id (16)Co-ordinates: (2,3)Treasure collected"
                    + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());
    game.movePlayerInGivenDirection(Directions.NORTH);
    game.movePlayerInGivenDirection(Directions.WEST);
    game.movePlayerInGivenDirection(Directions.WEST);
    game.collectTreasure();
    game.movePlayerInGivenDirection(Directions.WEST);
    assertEquals("Location Id (7)Co-ordinates: (1,0)Treasure collected"
                    + "(DIAMOND:1 RUBY:1 SAPPHIRE:0 )(smell: 2)(arrows: 4)",
            game.getPlayer().toString());
    game.movePlayerInGivenDirection(Directions.SOUTH);
    assertEquals("Location Id (13)Co-ordinates: (2,0)Treasure collected"
                    + "(DIAMOND:0 RUBY:0 SAPPHIRE:0 )(smell: 1)(arrows: 4)",
            game.getPlayer().toString());
  }
}