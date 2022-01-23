import org.junit.Test;
import dungeon.WrappingType;
import game.Game;
import game.IGame;
import location.Location;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests to check if a wrapping dungeon is created
 * properly and player is able to move and collect treasure.
 */
public class WrappingGameTest {
  IGame game;

  @Test
  public void testMoveAndCollectTreasureFunctionality_Wrapping() {
    game = new Game(WrappingType.WRAPPING, 5, 6, 8);
    game.assignTreasure(20);
    game.initialiseGame();
    game.initialiseGameForTesting(2, 12);

    // Player is initially at node 2 (0,1) and with no treasure
    // The start point location id and the player's location id are the same initially
    assertEquals(2, game.getPlayer().getPlayerLocationId());
    assertEquals(new Location(0, 1), game.getPlayer().getPlayerCoordinates());
    assertEquals(game.getStartPointLocationId(), game.getPlayer().getPlayerLocationId());

    // moving the player to node 1 which is one of the neighbours of node 2
    game.movePlayer(1);
    assertEquals(1, game.getPlayer().getPlayerLocationId());
    Location playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,0), playerCoordinates);
    assertEquals("Location Id (1)Co-ordinates: (0,0)Treasure collected(DIAMOND:0 RUBY:0 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(1) Neighbours/possible moves(7 2 6 ) (CA) (treasure: D R )" ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());
    game.collectTreasure();
    assertEquals("Location Id (1)Co-ordinates: (0,0)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());

    /* Moving the player to node 6 which is one of the neighbours of node 1 -
    NOTE THIS IS A WRAPPING MOVE
     */
    game.movePlayer(6);
    assertEquals(6, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,5), playerCoordinates);
    assertEquals("Location Id (6)Co-ordinates: (0,5)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(6) Neighbours/possible moves(5 12 1 ) (CA) " ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // moving player to node 5 which is one of the neighbours of node 6
    game.movePlayer(5);
    assertEquals(5, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,4), playerCoordinates);

    // moving the player to the node 11 which is one of the neighbours of node 5
    game.movePlayer(11);
    assertEquals(11, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,4), playerCoordinates);

    // moving the player to the node 17 which is one of the neighbours of node 11
    game.movePlayer(17);
    assertEquals(17, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(2,4), playerCoordinates);

    // trying to move a player to an adjacent node which does not have a path - NOTE INVALID MOVE
    assertEquals("Player cannot be moved as it as no such path to such specified location",
            game.movePlayer(16));

    // moving the player to the node 11 which is one of the neighbours of node 17
    game.movePlayer(11);
    assertEquals(11, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,4), playerCoordinates);

    // moving the player to the node 10 which is one of the neighbours of node 11
    game.movePlayer(10);
    assertEquals(10, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,3), playerCoordinates);

    // moving the player to the node 9 which is one of the neighbours of node 10
    game.movePlayer(9);
    assertEquals(9, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,2), playerCoordinates);

    /* moving the player to the node 3 which is one of the neighbours of node 9 -
    ALSO COLLECTING TREASURE
    */
    game.movePlayer(3);
    assertEquals(3, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,2), playerCoordinates);
    assertEquals("Location Id (3)Co-ordinates: (0,2)Treasure collected(DIAMOND:1 RUBY:1 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(3) Neighbours/possible moves(2 9 4 ) (CA) (treasure: D R )" ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());
    game.collectTreasure();
    assertEquals("Location Id (3)Co-ordinates: (0,2)Treasure collected(DIAMOND:2 RUBY:2 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());


    // moving the player to the node 4 which is one of the neighbours of node 3
    game.movePlayer(4);
    assertEquals(4, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,3), playerCoordinates);
    assertEquals("Location Id (4)Co-ordinates: (0,3)Treasure collected(DIAMOND:2 RUBY:2 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(4) Neighbours/possible moves(3 10 5 ) (CA) (treasure: D R )" ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());
    game.collectTreasure();
    assertEquals("Location Id (4)Co-ordinates: (0,3)Treasure collected(DIAMOND:3 RUBY:3 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    // after collecting treasure from the cave, the cave will be made empty
    assertEquals("ID(4) Neighbours/possible moves(3 10 5 ) (CA) " ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // moving the player to the node 5 which is one of the neighbours of node 4
    game.movePlayer(5);
    assertEquals(5, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,4), playerCoordinates);
    assertEquals("Location Id (5)Co-ordinates: (0,4)Treasure collected(DIAMOND:3 RUBY:3 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(5) Neighbours/possible moves(4 11 6 ) (CA) " ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // moving the player to the node 6 which is one of the neighbours of node 5
    game.movePlayer(6);
    assertEquals(6, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(0,5), playerCoordinates);
    assertEquals("Location Id (6)Co-ordinates: (0,5)Treasure collected(DIAMOND:3 RUBY:3 "
            + "SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(6) Neighbours/possible moves(5 12 1 ) (CA) " ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // moving the player to the node 12 which is one of the neighbours of node 6
    String response = game.movePlayer(12);
    assertEquals("Player has reached the destination", response);
    assertEquals(12, game.getPlayer().getPlayerLocationId());
    playerCoordinates = game.getPlayer().getPlayerCoordinates();
    assertEquals(new Location(1,5), playerCoordinates);
    assertEquals("Location Id (12)Co-ordinates: (1,5)Treasure "
            + "collected(DIAMOND:3 RUBY:3 SAPPHIRE:0 )", game.getPlayer().toString());
    assertEquals("ID(12) Neighbours/possible moves(6 18 11 7 ) (CA) " ,game
            .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()].toString());

    // trying to move a player he is already reached the destination
    assertEquals("Player has already reached the destination", game.movePlayer(11));
  }
}
