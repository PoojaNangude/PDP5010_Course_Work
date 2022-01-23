package game;

/**
 * IGameView interface which had functions which are used to listen to
 *   the inputs given by the user.
 */
public interface IGameView {

  void addClickListener(IDungeonGameController listener);

  void addKeyListener(IDungeonGameController listener);

  void refresh();

  void makeVisible();
}
