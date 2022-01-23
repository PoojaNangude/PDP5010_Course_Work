import org.junit.Test;
import dungeon.WrappingType;
import game.Game;
import game.IGame;
import location.Location;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests to check if a non wrapping dungeon is created
 * properly and player is able to move and collect treasure.
 */
public class NonWrappingGameTest {
  IGame game;

  @Test
  public void testMoveAndCollectTreasureFunctionality_NonWrapping() {
    game = new Game(WrappingType.NON_WRAPPING, 5, 6, 8);
    game.assignTreasure(20);
    game.initialiseGame();
    game.initialiseGameForTesting(2, 12);

    // Player is initially at node 2 (0,1) and with no treasure
    assertEquals(2, game.getPlayer().getPlayerLocationId());
    assertEquals(new Location(0, 1), game.getPlayer().getPlayerCoordinates());

    // Moving player to node 3 which is one of the neighbours of node 2
    game.movePlayer(3);
    assertEquals(3, game.getPlayer().getPlayerLocationId());
    Location playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,2), playerCoordinates);
    assertEquals("Location Id (3)Co-ordinates: (0,2)Treasure collected(DIAMOND:0 RUBY:0 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(3) Neighbours/possible moves(2 9 4 ) (CA) (treasure: D R )" ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());
    game.collectTreasure();
    assertEquals("Location Id (3)Co-ordinates: (0,2)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());

    // Moving player to node 9 which is one of the neighbours of node 3
    game.movePlayer(9);
    assertEquals(9, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,2), playerCoordinates);
    assertEquals("Location Id (9)Co-ordinates: (1,2)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(9) Neighbours/possible moves(3 15 8 10 ) (CA) ", game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());
    assertEquals("No treasure present", game.collectTreasure());

    // Moving player to node 10 which is one of the neighbours of node 9
    game.movePlayer(10);
    assertEquals(10, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,3), playerCoordinates);
    assertEquals("Location Id (10)Co-ordinates: (1,3)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )",game.getPlayer().toString() );
    assertEquals("ID(10) Neighbours/possible moves(4 16 9 11 ) (CA) ", game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());


    // Moving player to node 11 which is one of the neighbours of node 10
    game.movePlayer(11);
    assertEquals(11, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,4), playerCoordinates);
    assertEquals("Location Id (11)Co-ordinates: (1,4)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )",game.getPlayer().toString() );
    assertEquals("ID(11) Neighbours/possible moves(5 17 10 12 ) (CA) ", game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // Moving player to node 12 which is one of the neighbours of node 11
    String response = game.movePlayer(12);
    assertEquals("Player has reached the destination", response);
    assertEquals(12, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,5), playerCoordinates);
    assertEquals("Location Id (12)Co-ordinates: (1,5)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )",game.getPlayer().toString() );
    assertEquals("ID(12) Neighbours/possible moves(6 18 11 ) (CA) ", game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // Trying to move the player when player has already reached the destination
    assertEquals("Player has already reached the destination", game.movePlayer(18));
  }

}