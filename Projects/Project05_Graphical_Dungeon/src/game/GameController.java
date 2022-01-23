package game;

/**
 * Represents a Controller for the dungeon game: handle user moves by
 * executing them using the model;
 * Convey move outcomes to the user in some form.
 */
public interface GameController {

  /**
   * Execute a single game of the given a game model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null tic tac toe Model
   */
  void playGame(IGame m);
}
