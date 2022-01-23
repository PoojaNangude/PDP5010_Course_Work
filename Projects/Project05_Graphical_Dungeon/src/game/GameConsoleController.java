package game;

import dungeon.Directions;
import dungeon.DungeonNode;
import dungeon.LocationType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import pair.Pair;
import treasure.TreasureCatalog;

/**
 * GameConsoleController which implements the GameController interface and implements the
 * controller which is used to use the model features based on the user input.
 */
public class GameConsoleController implements GameController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the game controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public GameConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(IGame m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    try {
      out.append("WELCOME TO THE GAME!!\n");
      out.append("----------------------------------------"
              + "-------------------------------------------------\n");

      printPlayerLocationDetails(m);
      out.append("Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n");
      String input = this.scan.next();
      while (true) {
        if (input.equalsIgnoreCase("q")) {
          out.append(String.format("Player quit the game!!!\n"));
          break;
        } else if (!isValidChoice(input, out)) {
          continue;
        } else {
          if (input.equalsIgnoreCase("m")) {
            DungeonNode playerPresentLocationDetails = m.getPlayerPresentLocationDetails();
            ArrayList<Pair> possibleMoves = playerPresentLocationDetails.getPossibleMoves();
            out.append("Doors lead to ");
            for (int i = 0; i < possibleMoves.size(); i++) {
              out.append(String.format("%s(%s), ",
                      possibleMoves.get(i).getDirectionFromPresentLocation().toString(),
                      possibleMoves.get(i).getDirectionFromPresentLocation().toString().charAt(0)));
            }
            while (true) {
              out.append("\nWhere do you want to go? \n");
              String dir = this.scan.next();
              Directions direction = null;

              if (dir.equalsIgnoreCase("N") || dir.equalsIgnoreCase("S")
                      || dir.equalsIgnoreCase("E") || dir.equalsIgnoreCase("W")) {
                if (dir.equalsIgnoreCase("N")) {
                  direction = Directions.NORTH;
                } else if (dir.equalsIgnoreCase("S")) {
                  direction = Directions.SOUTH;
                } else if (dir.equalsIgnoreCase("E")) {
                  direction = Directions.EAST;
                } else if (dir.equalsIgnoreCase("W")) {
                  direction = Directions.WEST;
                }
                int moveOutcome = m.movePlayerInGivenDirection(direction);
                if (moveOutcome == 0) {
                  out.append("Invalid move specified\n");
                } else if (moveOutcome == 1) {
                  out.append("Player moved to desired location\n");
                  out.append(m.getThiefInCave());
                  // check if the player's present location has an injured monster
                  while (true) {
                    playerPresentLocationDetails = m.getPlayerPresentLocationDetails();
                    if (playerPresentLocationDetails.getMonster() != null
                            && playerPresentLocationDetails.getMonster().getHealth() == 1) {
                      /*
                      If the player is at a location where there is an injured monster decide the
                      faith of th player as he has 50% of escaping.
                       */
                      out.append("You see an injured monster here\n");
                      out.append("You try to run away\n");
                      boolean survived = m.doesPlayerSurvival();
                      if (survived) {
                        out.append("The monster is injured and you can escape. "
                                + "Decide a direction to move.");
                        playerPresentLocationDetails = m.getPlayerPresentLocationDetails();
                        possibleMoves = playerPresentLocationDetails.getPossibleMoves();
                        out.append("Doors lead to ");
                        for (int i = 0; i < possibleMoves.size(); i++) {
                          out.append(String.format("%s(%s), ", possibleMoves.get(i)
                                          .getDirectionFromPresentLocation().toString(),
                                  possibleMoves.get(i).getDirectionFromPresentLocation()
                                          .toString().charAt(0)));
                        }

                        while (true) {
                          out.append("\nWhere do you want to go? \n");
                          String secondChoice = scan.next();
                          Directions secondDirection = null;
                          if (secondChoice.equalsIgnoreCase("N")
                                  || secondChoice.equalsIgnoreCase("S")
                                  || secondChoice.equalsIgnoreCase("E")
                                  || secondChoice.equalsIgnoreCase("W")) {
                            if (secondChoice.equalsIgnoreCase("N")) {
                              secondDirection = Directions.NORTH;
                            } else if (secondChoice.equalsIgnoreCase("S")) {
                              secondDirection = Directions.SOUTH;
                            } else if (secondChoice.equalsIgnoreCase("E")) {
                              secondDirection = Directions.EAST;
                            } else if (secondChoice.equalsIgnoreCase("W")) {
                              secondDirection = Directions.WEST;
                            }
                            int secondMoveOutcome = m.movePlayerInGivenDirection(secondDirection);
                            if (secondMoveOutcome == 0) {
                              /*
                               User specified a direction which does not have a path in
                               that direction.
                               */
                              continue;
                            } else {
                              break;
                            }
                          } else {
                            out.append("Invalid value entered!\n");
                            continue;
                          }
                        }
                      } else {
                        out.append("You could not escape the monster.");
                        break;
                      }
                    }
                    playerPresentLocationDetails = m.getPlayerPresentLocationDetails();
                    if (playerPresentLocationDetails.getMonster() != null
                            && playerPresentLocationDetails.getMonster().getHealth() == 1) {
                      continue;
                    } else {
                      break;
                    }
                  }
                }

                break;
              } else {
                out.append("Invalid value entered!\n");
                continue;
              }
            }
            // end of if which handles move
          } else if (input.equalsIgnoreCase("p")) {
            while (true) {
              out.append("What do you want to collect (arrows(A)/ treasure(T))\n");
              String pick = this.scan.next();
              if (pick.equalsIgnoreCase("A") || pick.equalsIgnoreCase("T")) {

                if (pick.equalsIgnoreCase("A")) {
                  boolean collectResult = m.collectArrows();
                  if (!collectResult) {
                    out.append("No arrows present to collect\n");
                  } else {
                    out.append("Arrows collected\n");
                  }
                  break;
                } else if (pick.equalsIgnoreCase("T")) {
                  boolean collectResult = m.collectTreasure();
                  if (!collectResult) {
                    out.append("No treasure is present\n");
                  } else {
                    out.append("Treasure collected\n");
                  }
                  break;
                }

              } else {
                out.append("Invalid value entered!\n");
                continue;
              }

            }
          } else if (input.equalsIgnoreCase("s")) {
            Directions direction = null;
            int distance;
            while (true) {
              out.append("How many caves to be travelled ?\n");
              String d = this.scan.next();
              if (isInteger(d, out) && Integer.parseInt(d) > 0) {
                distance = Integer.parseInt(d);
                break;
              } else {
                out.append("Invalid input\n");
                continue;
              }
            }

            while (true) {
              out.append("Enter the direction in which you want "
                      + "to shoot the arrow(N-S-E-W): \n");
              String dir = this.scan.next();
              if (dir.equalsIgnoreCase("N")
                      || dir.equalsIgnoreCase("S")
                      || dir.equalsIgnoreCase("E")
                      || dir.equalsIgnoreCase("W")) {
                if (dir.equalsIgnoreCase("N")) {
                  direction = Directions.NORTH;
                } else if (dir.equalsIgnoreCase("S")) {
                  direction = Directions.SOUTH;
                } else if (dir.equalsIgnoreCase("E")) {
                  direction = Directions.EAST;
                } else if (dir.equalsIgnoreCase("W")) {
                  direction = Directions.WEST;
                }
                int shootResult = m.shootArrow(direction, distance);
                if (shootResult == -1) {
                  out.append("You are out of arrows. Explore the dungeon to collect more.\n");
                } else if (shootResult == 0) {
                  out.append("You shot the arrow into the darkness\n");
                } else if (shootResult == 1) {
                  out.append("You hear a great howl in the distance. "
                          + "You injured an Otyugh!!\n");
                } else if (shootResult == 2) {
                  out.append("Wow!! You just killed a monster.\n");
                }
                break;
              } else {
                out.append("Invalid value entered!\n");
                continue;
              }
            }
          } // end of if which handles shoot
        }

        out.append("----------------------------------------"
                + "-------------------------------------------------\n");
        if (m.isGameOver()) {
          DungeonNode playerPresentLocationDetails = m.getPlayerPresentLocationDetails();
          if (playerPresentLocationDetails.getMonster() != null
                  && playerPresentLocationDetails.getMonster().getHealth() == 2) {
            out.append("Game over. You were eaten by a monster!!!\n");
          } else if (playerPresentLocationDetails.getLocationId() == m.getEndPointLocationId()
                  && playerPresentLocationDetails.getMonster().getHealth() == 0) {
            out.append("Wohoo!! You won the game!!\n");
          } else if (playerPresentLocationDetails.getHasPit()) {
            out.append("The player fell into a pit! Better luck next time!!!\n");
          }
          break;
        }
        printPlayerLocationDetails(m);
        out.append("Move player, Pickup treasure/arrow or Shoot arrow (m-p-s)?\n");
        input = this.scan.next();
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

  }

  private boolean isValidChoice(String input, Appendable out) {
    return (input.equalsIgnoreCase("m")
            || input.equalsIgnoreCase("p")
            || input.equalsIgnoreCase("s"));
  }

  private void printPlayerLocationDetails(IGame m) {
    try {
      DungeonNode playerPresentLocationDetails = m.getPlayerPresentLocationDetails();

      // type of location
      out.append(String.format("You are in a %s\n",
              playerPresentLocationDetails.getLocationType()));

      // display only when there is a dead monster in the cave
      if (playerPresentLocationDetails.getLocationType() == LocationType.CAVE) {
        if (playerPresentLocationDetails.getMonster() != null) {
          if (playerPresentLocationDetails.getMonster().getHealth() == 0) {
            out.append("You entered a cave with a dead monster\n");
          }
        }
      }

      // number of arrows user is equipped with
      out.append(String.format("You are equipped with %d arrows\n",
              m.getPlayer().getArrowCount()));

      // total treasure that has been collected by the user till now
      out.append(String.format("Treasure collected by player till now: %s\n",
              m.getPlayer().getTreasureCollectedSummary()));

      out.append("\n");
      // print count of arrows if any arrows present in cave
      if (playerPresentLocationDetails.getArrowCount() > 0) {
        out.append(String.format("You find %d arrows here\n",
                playerPresentLocationDetails.getArrowCount()));
      }

      // print treasure summary if the location has any treasure
      Map<TreasureCatalog, Integer> treasureSummary =
              playerPresentLocationDetails.getTreasureSummary();
      boolean isTreasurePresent = false;
      for (Map.Entry<TreasureCatalog, Integer> pair : treasureSummary.entrySet()) {
        if (pair.getValue() > 0) {
          isTreasurePresent = true;
          break;
        }
      }
      if (isTreasurePresent) {
        out.append("You find ");
        for (Map.Entry<TreasureCatalog, Integer> pair : treasureSummary.entrySet()) {
          if (pair.getValue() > 0) {
            out.append(String.format("%s %s ", pair.getValue(), pair.getKey()));
          }
        }
        out.append("here\n");
      }

      // print if player is getting a pungent smell
      int levelOfPungentSmell = m.getPlayer().getLevelOfPungentSmellDetected();
      if (levelOfPungentSmell != 0) {
        if (levelOfPungentSmell == 1) {
          out.append(String.format("The player senses a slight pungent odour\n"));
        } else if (levelOfPungentSmell == 2) {
          out.append(String.format("The player senses a terribly pungent odour\n"));
        }
      }

      // show the directions the player can move to from the present location
      out.append("Doors lead to ");
      ArrayList<Pair> possibleMoves = playerPresentLocationDetails.getPossibleMoves();
      for (int i = 0; i < possibleMoves.size(); i++) {
        out.append(String.format("%s, ", possibleMoves.get(i).getDirectionFromPresentLocation()));
      }
      out.append("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  private boolean isInteger(String input, Appendable out) throws IOException {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
}
