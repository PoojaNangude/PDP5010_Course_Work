import java.util.List;

import game.IDungeonGameController;
import game.IGameView;

/**
 * Mock view which implements the Game View interface for the purpose of testing.
 */
public class MockGameSwingView implements IGameView {
  private List<String> log;

  public MockGameSwingView(List<String> log) {
    this.log = log;
  }

  @Override
  public void addClickListener(IDungeonGameController listener) {
    log.add("addClickListener was called");
  }

  @Override
  public void addKeyListener(IDungeonGameController listener) {
    log.add("addKeyListener was called");
  }

  @Override
  public void refresh() {
    log.add("refresh was called");
  }

  @Override
  public void makeVisible() {
    log.add("makeVisible was called");
  }
}
