import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dungeon.WrappingType;
import game.DungeonGameController;
import game.Game;
import game.IDungeonGameController;
import game.IGame;
import static org.junit.Assert.assertEquals;

/**
 * Test to check id the appropriate functions are being called by the controller by mocking
 * the view.
 */
public class ControllerTest {
  IGame model;
  MockGameSwingView view;

  @Test
  public void testClickListener() {
    List<String> log = new ArrayList<>();
    model = new Game(WrappingType.WRAPPING, 6, 7, 10,
            30, 40);
    view = new MockGameSwingView(log);
    IDungeonGameController controller = new DungeonGameController(model, view);
    view.addClickListener(controller);
    assertEquals(Arrays.asList("addClickListener was called"), log);
  }

  @Test
  public void testKeyListener() {
    List<String> log = new ArrayList<>();
    model = new Game(WrappingType.WRAPPING, 6, 7, 10,
            30, 40);
    view = new MockGameSwingView(log);
    IDungeonGameController controller = new DungeonGameController(model, view);
    view.addKeyListener(controller);
    assertEquals(Arrays.asList("addKeyListener was called"), log);
  }

  @Test
  public void testRefresh() {
    List<String> log = new ArrayList<>();
    model = new Game(WrappingType.WRAPPING, 6, 7, 10,
            30, 40);
    view = new MockGameSwingView(log);
    IDungeonGameController controller = new DungeonGameController(model, view);
    view.refresh();
    assertEquals(Arrays.asList("refresh was called"), log);
  }

  @Test
  public void testMakeVisible() {
    List<String> log = new ArrayList<>();
    model = new Game(WrappingType.WRAPPING, 6, 7, 10,
            30, 40);
    view = new MockGameSwingView(log);
    IDungeonGameController controller = new DungeonGameController(model, view);
    view.makeVisible();
    assertEquals(Arrays.asList("makeVisible was called"), log);
  }
}
