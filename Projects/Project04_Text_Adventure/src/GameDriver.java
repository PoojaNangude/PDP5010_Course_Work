import dungeon.WrappingType;
import game.Game;
import game.GameConsoleController;
import java.io.InputStreamReader;

/**
 * The driver class which will take the parameters of the dungeon from the command line and
 * call the dungeon constructor which is responsible for creating the dungeon.
 */
public class GameDriver {

  /**
   * Main method which is used to call the controller and also pass the created dungeon to it as
   * a model.
   */
  public static void main(String[] args) {
    if (args.length == 6) {
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
      int percentageOfCavesWithTreasure = Integer.parseInt(args[4]);
      int percentageOfCavesWithMonsters = Integer.parseInt(args[5]);

      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      new GameConsoleController(input, output).playGame(new Game(wrappingType,
              rows, columns, interconnectivity,
              percentageOfCavesWithTreasure, percentageOfCavesWithMonsters));
    } else {
      System.out.println("Invalid parameters");
    }
  }

  /**
   * Function to check if the input parameters given using the command line are in integer format.
   */
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}