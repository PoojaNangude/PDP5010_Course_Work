import org.junit.Before;
import org.junit.Test;

import dungeon.DungeonNode;
import dungeon.WrappingType;
import game.Game;
import game.IGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * To check if all the game parameters are being initialised properly.
 */
public class GameCreationTest {
  IGame game;

  @Before
  public void setUp() {
    game = new Game(WrappingType.WRAPPING, 6, 6,
            13, 30, 40);
  }

  @Test
  public void testMonsterAtEndAndStartLocation() {
    // make sure that the end location has a monster
    int endLocation = game.getEndPointLocationId();
    DungeonNode location = game.getLocationDetails(endLocation);
    assertTrue(location.getMonster() != null && location.getMonster().getHealth() == 2);

    // make sure that the start location does not have a monster
    int startLocation = game.getStartPointLocationId();
    location = game.getLocationDetails(startLocation);
    assertTrue(location.getMonster() == null);

    // make sure that the player initially has 3 arrows
    assertEquals(3, game.getPlayer().getArrowCount());
  }

}
