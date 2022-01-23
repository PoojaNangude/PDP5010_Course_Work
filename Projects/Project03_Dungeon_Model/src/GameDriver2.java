import dungeon.LocationType;
import dungeon.WrappingType;
import game.Game;
import game.IGame;
import java.io.IOException;
import location.Location;

/**
 * Driver class the demonstrates the following cases:
 * (1) WRAPPING DUNGEON (2) PLAYER STARTS AT START POINT AND REACHES THE END POINT.
 */
public class GameDriver2 {
  /**
   * For testing purpose do the following:
   * TU RUN THE JAR FILE
   * On command line:
   * -> Navigate to /res/Project3_Dungeon_Game_Driver2_JAR
   * -> Enter this command to run the jar file: java -jar CS5010_Project3_Dungeon.jar W 5 6 8 30
   *<p></p>
   * TO RUN THE DRIVER:
   * On command line:
   * -> Navigate to /src
   * -> javac GameDriver2.java
   * -> java GameDriver2.java  W 5 6 8 30
   * (Note: You need to execute the driver file as mentioned above
   * to see all the example demonstrations)
   **/

  public static void main(String[] args) throws IOException {
    /*
    For testing purpose do the following:
    In Dungeon: give the final line number of the comments which need to be commented out
    On command line:
    -> Navigate to /src
    -> javac GameDriver2.java
    -> java GameDriver2.java  W 5 6 8 30
    (Note: You need to execute the driver file as mentioned to
    see all the example demonstrations)
    */


    if (args.length == 5) {
      if ((!args[0].equals("W") && !args[0].equals("NW")) || !isNumeric(args[1])
              || !isNumeric(args[2]) || !isNumeric(args[3]) || !isNumeric(args[4])) {
        throw new IllegalArgumentException("Invalid input format!!!");
      }
      WrappingType wrappingType = null;
      if (args[0].equals("W")) {
        wrappingType = WrappingType.WRAPPING;
      } else if (args[0].equals("NW")) {
        wrappingType = WrappingType.NON_WRAPPING;
      }
      int rows = Integer.parseInt(args[1]);
      int columns = Integer.parseInt(args[2]);
      int interconnectivity = Integer.parseInt(args[3]);

      System.out.println("DEMONSTRATION OF:");
      System.out.println("(1) WRAPPING DUNGEON (2)PLAYER VISITING EVERY NODE");

      IGame game = new Game(wrappingType, rows, columns, interconnectivity);
      System.out.println("IN THE GAME");
      System.out.println("Grid before assigning treasure to dungeon: ");
      printDungeonGame(rows, columns, game);
      int percentageOfCavesWithTreasure = Integer.parseInt(args[4]);
      System.out.println();
      game.assignTreasure(percentageOfCavesWithTreasure);
      System.out.println("Thus total number of caves to be assigned treasure: "
              + (int) Math.ceil(((
                      double) percentageOfCavesWithTreasure * totalNumberOfCaves(rows,
                      columns, game)) / 100));
      System.out.println("Grid after assigning " + percentageOfCavesWithTreasure
              + "% of treasure to the dungeon:");
      printDungeonGame(rows, columns, game);

      System.out.println();
      game.initialiseGame();
      game.initialiseGameForTesting(7, 6);
      System.out.println("Start point: " + game.getStartPointLocationId());
      System.out.println("Destination point: " + game.getEndPointLocationId());
      System.out.println();
      System.out.println("---------------");

      System.out.println();
      System.out.println("Player is initially at node 7 and with no treasure");
      System.out.println("Player: " + game.getPlayer().toString());
      Location playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 12 which is one of the neighbours "
              + "of node 7 - NOTE SHOWING WRAPPING MOVES");
      String move = game.movePlayer(12);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 18 which is one of the neighbours of node 12");
      move = game.movePlayer(18);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Trying to move the player to an invalid location/neighbour "
              + "like 17, as it has no path to reach 17");
      move = game.movePlayer(17);
      System.out.println(move);

      System.out.println();
      System.out.println("Moving player to node 24 which is one of the neighbours of node 18");
      move = game.movePlayer(24);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 30 which is one of the neighbours of node 24");
      move = game.movePlayer(30);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 24 which is one of the neighbours of node 30");
      move = game.movePlayer(24);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 18 which is one of the neighbours of node 24");
      move = game.movePlayer(18);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 12 which is one of the neighbours of node 18");
      move = game.movePlayer(12);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 11 which is one of the neighbours of node 12");
      move = game.movePlayer(11);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 17 which is one of the neighbours of node 11");
      move = game.movePlayer(17);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 23 which is one of the neighbours of node 17");
      move = game.movePlayer(23);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 29 which is one of the neighbours of node 23");
      move = game.movePlayer(29);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 23 which is one of the neighbours of node 29");
      move = game.movePlayer(23);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 17 which is one of the neighbours of node 23");
      move = game.movePlayer(17);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 11 which is one of the neighbours of node 17");
      move = game.movePlayer(11);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 10 which is one of the neighbours of node 11");
      move = game.movePlayer(10);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 16 which is one of the neighbours of node 10");
      move = game.movePlayer(16);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 12 which is one of the neighbours of node 16");
      move = game.movePlayer(22);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 28 which is one of the neighbours of node 22");
      move = game.movePlayer(28);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 22 which is one of the neighbours of node 28");
      move = game.movePlayer(22);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 16 which is one of the neighbours of node 22");
      move = game.movePlayer(16);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 10 which is one of the neighbours of node 16");
      move = game.movePlayer(10);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 9 which is one of the neighbours of node 10");
      move = game.movePlayer(9);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 15 which is one of the neighbours of node 9");
      move = game.movePlayer(15);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 21 which is one of the neighbours of node 15");
      move = game.movePlayer(21);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 27 which is one of the neighbours of node 21");
      move = game.movePlayer(27);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 21 which is one of the neighbours of node 27");
      move = game.movePlayer(21);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 15 which is one of the neighbours of node 21");
      move = game.movePlayer(15);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 9 which is one of the neighbours of node 15");
      move = game.movePlayer(9);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 8 which is one of the neighbours of node 9");
      move = game.movePlayer(8);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 14 which is one of the neighbours of node 8");
      move = game.movePlayer(14);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 20 which is one of the neighbours of node 14");
      move = game.movePlayer(20);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 26 which is one of the neighbours of node 20");
      move = game.movePlayer(26);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 20 which is one of the neighbours of node 26");
      move = game.movePlayer(20);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 14 which is one of the neighbours of node 20");
      move = game.movePlayer(14);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 13 which is one of the neighbours of node 14");
      move = game.movePlayer(13);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 19 which is one of the neighbours of node 13");
      move = game.movePlayer(19);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 25 which is one of the neighbours of node 19");
      move = game.movePlayer(25);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 19 which is one of the neighbours of node 25");
      move = game.movePlayer(19);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 13 which is one of the neighbours of node 19");
      move = game.movePlayer(13);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 7 which is one of the neighbours of node 13");
      move = game.movePlayer(7);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 12 which is one of the neighbours of node 7 - "
              + "NOTE SHOWING A WRAPPING MOVE");
      move = game.movePlayer(12);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 11 which is one of the neighbours of node 12");
      move = game.movePlayer(11);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 5 which is one of the neighbours of node 11");
      move = game.movePlayer(5);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      String treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 4 which is one of the neighbours of node 5");
      move = game.movePlayer(4);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 3 which is one of the neighbours of node 4");
      move = game.movePlayer(3);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 2 which is one of the neighbours of node 3");
      move = game.movePlayer(2);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 1 which is one of the neighbours of node 2");
      move = game.movePlayer(1);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 6 which is one of the neighbours of node 1");
      move = game.movePlayer(6);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Trying to move the player when player has already reached "
              + "the destination");
      move = game.movePlayer(12);
      System.out.println(move);
    } else {
      System.out.println("Invalid inpt format");
    }
  }

  /**
   * Function to check whether the input string is in integer format.
   */
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  /**
   * Function to print the dungeon for representation purpose.
   */
  public static void printDungeonGame(int rows, int columns, IGame game) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.print(game.getDungeonGrid()[i][j].toString() + "\t\t");
      }
      System.out.println();
    }
  }

  /**
   * Function to print the dungeon along with the location of the player.
   */
  public static void printDungeonWithPlayer(int rows, int columns,
                                            IGame game, int playerLocationId) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (game.getDungeonGrid()[i][j].getLocationId() == playerLocationId) {
          System.out.print("|P|" + "\t\t");
        } else {
          System.out.print(game.getDungeonGrid()[i][j].getLocationId() + "\t\t");
        }
      }
      System.out.println();
    }
  }

  /**
   * Function to return the total number of caves in the dungeon.
   */
  public static int totalNumberOfCaves(int rows, int columns, IGame game) {
    int caveCounter = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (game.getDungeonGrid()[i][j].getLocationType() == LocationType.CAVE) {
          caveCounter++;

        }
      }
    }
    return caveCounter;
  }
}
