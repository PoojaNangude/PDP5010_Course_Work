import dungeon.LocationType;
import dungeon.WrappingType;
import game.Game;
import game.IGame;
import java.io.IOException;
import location.Location;

/**
 * Driver class the demonstrates the following cases:
 * (1) NON WRAPPING DUNGEON (2) PLAYER STARTS AT START POINT AND REACHES THE END POINT.
 */
public class GameDriver1 {

  /**
   * For testing purpose do the following:
   * TO RUN THE JAR FILE
   * On command line:
   * -> Navigate to /res/Project3_Dungeon_Game_Driver1_JAR
   * -> Enter this command to run the jar file: java -jar CS5010_Project3_Dungeon.jar NW 5 6 8 20
   *<p></p>
   * TO RUN THE DRIVER
   * On command line:
   * -> Navigate to /src
   * -> javac GameDriver1.java
   * -> java GameDriver1.java  NW 5 6 8 20
   * (Note: You need to execute the driver file as mentioned
   *  to see all the example demonstrations)
   */
  public static void main(String[] args) throws IOException {
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
      System.out.println("(1) NON WRAPPING DUNGEON (2)PLAYER STARTS "
              + "AT START POINT AND REACHES THE END POINT");

      IGame game = new Game(wrappingType, rows, columns, interconnectivity);
      System.out.println("IN THE GAME");
      System.out.println("Grid before assigning treasure to dungeon: ");
      printDungeonGame(rows, columns, game);

      int percentageOfCavesWithTreasure = Integer.parseInt(args[4]);
      System.out.println();
      game.assignTreasure(percentageOfCavesWithTreasure);
      System.out.println("Total number of caves: " + totalNumberOfCaves(rows, columns, game));
      System.out.println("Thus total number of caves to be assigned treasure: "
              + (int) Math.ceil(((
                      double) percentageOfCavesWithTreasure * totalNumberOfCaves(rows,
                      columns, game)) / 100));
      System.out.println("Grid after assigning " + percentageOfCavesWithTreasure
              + "% of treasure to the dungeon:");
      printDungeonGame(rows, columns, game);

      System.out.println();
      game.initialiseGame();
      game.initialiseGameForTesting(2, 12);
      System.out.println("Start point: " + game.getStartPointLocationId());
      System.out.println("Destination point: " + game.getEndPointLocationId());
      System.out.println();
      System.out.println("---------------");


      System.out.println();
      System.out.println("Player is initially at node 2 and with no treasure");
      System.out.println("Player: " + game.getPlayer().toString());
      Location playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: "
              + game.getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());


      System.out.println();
      System.out.println("Moving player to node 3 which is one of the neighbours of node 2");
      String move = game.movePlayer(3);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      String treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 9 which is one of the neighbours of node 3");
      move = game.movePlayer(9);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());
      System.out.println("Collecting the treasure in the cave");
      treasure = game.collectTreasure();
      System.out.println(treasure);
      System.out.println("Player: " + game.getPlayer().toString());

      System.out.println();
      System.out.println("Moving player to node 10 which is one of the neighbours of node 9");
      move = game.movePlayer(10);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 11 which is one of the neighbours of node 10");
      move = game.movePlayer(11);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Moving player to node 12 which is one of the neighbours of node 11");
      move = game.movePlayer(12);
      System.out.println(move);
      System.out.println("Player: " + game.getPlayer().toString());
      playerCoordinates = game.getPlayer().getPlayerCoordinates();
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      printDungeonWithPlayer(rows, columns, game, game.getPlayer().getPlayerLocationId());

      System.out.println();
      System.out.println("Player: " + game.getPlayer().toString());
      System.out.println("Player Location details: " + game
              .getDungeonGrid()[playerCoordinates.getX()][playerCoordinates.getY()]);
      System.out.println("Trying to move the player when player has "
              + "already reached the destination");
      move = game.movePlayer(18);
      System.out.println(move);
    } else {
      System.out.println("Invalid input format");
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
