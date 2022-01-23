package game;

import dungeon.WrappingType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * GameSwingView which implements the IGameView interface and also draws the basic layout
 * of the game.
 */
public class GameSwingView extends JFrame implements IGameView {
  private JTextField rowText;
  private JTextField columnText;
  private JTextField interconnectivityText;
  private JTextField wrappingTypeText;
  private JTextField treasureText;
  private JTextField monsterText;
  private JFrame jFrame;
  private JFrame parametersFrame;
  JScrollPane scrollPane;
  JMenu menu;
  private boolean shootActionSelected;
  private KeyEvent shootDirection;
  private KeyEvent shootingDistance;


  /**
   * GameSwingView constructor will get only the read only version of the model
   * fot the purpose of displaying the whole dungeon.
   *
   * @param model of type ReadOnlyGameModel
   */
  public GameSwingView(ReadOnlyGameModel model) {
    super("Adventure-Game");
    this.setTitle("Adventure-Game");
    this.shootActionSelected = false;
    this.shootDirection = null;
    this.shootingDistance = null;
    jFrame = new JFrame();

    // jframe for accepting parameters
    parametersFrame = new JFrame();
    parametersFrame.setSize(800, 200);
    parametersFrame.setLayout(new FlowLayout(5));

    JLabel rows;
    rows = new JLabel("Number of rows");
    parametersFrame.add(rows);

    rowText = new JTextField(2);
    parametersFrame.add(rowText);

    JLabel columns;
    columns = new JLabel("\nNumber of columns");
    parametersFrame.add(columns);

    columnText = new JTextField(2);
    parametersFrame.add(columnText);

    JLabel interconnectivity;
    interconnectivity = new JLabel("Interconnectivity");
    parametersFrame.add(interconnectivity);

    interconnectivityText = new JTextField(2);
    parametersFrame.add(interconnectivityText);

    JLabel wrappingType;
    wrappingType = new JLabel("Wrapping type (W/NW)");
    parametersFrame.add(wrappingType);

    wrappingTypeText = new JTextField(4);
    parametersFrame.add(wrappingTypeText);

    JLabel treasure;
    treasure = new JLabel("Percentage of caves with treasure");
    parametersFrame.add(treasure);

    treasureText = new JTextField(2);
    parametersFrame.add(treasureText);

    JLabel monsters;
    monsters = new JLabel("Percentage of caves with monsters");
    parametersFrame.add(monsters);

    monsterText = new JTextField(2);
    parametersFrame.add(monsterText);

    JButton set;
    set = new JButton("Set");
    set.setActionCommand("Set");
    parametersFrame.add(set);

    JButton clearAll;
    clearAll = new JButton("Clear All");
    clearAll.setActionCommand("Clear All");
    parametersFrame.add(clearAll);

    parametersFrame.setVisible(true);

    clearAll.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        wrappingTypeText.setText("");
        rowText.setText("");
        columnText.setText("");
        interconnectivityText.setText("");
        treasureText.setText("");
        monsterText.setText("");
      }
    });

    set.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        WrappingType wrappingType = null;
        if (Objects.equals(wrappingTypeText.getText(), "W")) {
          wrappingType = WrappingType.WRAPPING;
        } else if (Objects.equals(wrappingTypeText.getText(), "NW")) {
          wrappingType = WrappingType.NON_WRAPPING;
        }
        int r = Integer.parseInt(rowText.getText());
        int c = Integer.parseInt(columnText.getText());
        int i = Integer.parseInt(interconnectivityText.getText());
        int t = Integer.parseInt(treasureText.getText());
        int m = Integer.parseInt(monsterText.getText());

        JMenuBar mb;
        model.newGame(wrappingType, r, c, i, t, m);
        mb = new JMenuBar();
        menu = new JMenu("Menu");

        JMenuItem quitGame;
        quitGame = new JMenuItem("Quit");

        JMenuItem newGame;
        newGame = new JMenuItem("New Game");

        quitGame.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            System.exit(0);
          }
        });
        newGame.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            System.out.println("New Game");
            parametersFrame.setVisible(true);
          }
        });

        menu.add(quitGame);
        menu.add(newGame);
        mb.add(menu);
        jFrame.setJMenuBar(mb);

        jFrame.setSize(800, 800);
        jFrame.setLocation(100, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DungeonBoard dungeonBoard;
        dungeonBoard = new DungeonBoard(model);
        dungeonBoard.setPreferredSize(new Dimension(1000, 1000));

        scrollPane = new JScrollPane(dungeonBoard);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);

        jFrame.getContentPane().add(scrollPane);

        dungeonBoard.setLayout(new GridLayout(model.getRows(), model.getColumns()));
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.requestFocus();

      }
    });
  }

  @Override
  public void addClickListener(IDungeonGameController listener) {
    MouseAdapter mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("m" + mouseX + " " + mouseY);
        listener.handleCellClick(mouseX, mouseY);
      }
    };
    jFrame.addMouseListener(mouseAdapter);
  }

  @Override
  public void addKeyListener(IDungeonGameController listener) {
    KeyListener keyListener = new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        System.out.println("In keyTyped");
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 's') {
          shootActionSelected = true;
        } else if (shootActionSelected && (e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
          shootDirection = e;
        } else if (shootActionSelected && shootDirection != null && (e.getKeyChar() == '1'
                || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4'
                || e.getKeyChar() == '5' || e.getKeyChar() == '6' || e.getKeyChar() == '7'
                || e.getKeyChar() == '8' || e.getKeyChar() == '9')) {
          shootingDistance = e;

          listener.shoot(shootDirection, shootingDistance);
          shootActionSelected = false;
          shootDirection = null;
          shootingDistance = null;

        } else if (shootActionSelected && (e.getKeyCode() != KeyEvent.VK_UP
                || e.getKeyCode() != KeyEvent.VK_DOWN || e.getKeyCode() != KeyEvent.VK_LEFT
                || e.getKeyCode() != KeyEvent.VK_RIGHT)) {
          shootActionSelected = false;
          shootDirection = null;
          shootingDistance = null;
        } else if (shootActionSelected && shootDirection != null && (e.getKeyChar() != '1'
                || e.getKeyChar() != '2' || e.getKeyChar() != '3' || e.getKeyChar() != '4'
                || e.getKeyChar() != '5' || e.getKeyChar() != '6' || e.getKeyChar() != '7'
                || e.getKeyChar() != '8' || e.getKeyChar() != '9')) {
          shootActionSelected = false;
          shootDirection = null;
          shootingDistance = null;
        } else if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
                && (!shootActionSelected)) {
          listener.move(e);
        } else if (e.getKeyChar() == 't' || e.getKeyChar() == 'a') {
          listener.pick(e);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        System.out.println("In keyReleased");
      }
    };
    jFrame.addKeyListener(keyListener);
  }

  @Override
  public void refresh() {
    System.out.println("Refresh");
    jFrame.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  class MenuActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("Quit")) {
        System.out.println("Quit");
        System.exit(0);
      } else if (e.getActionCommand().equals("Restart")) {
        System.out.println("Restart");
      } else if (e.getActionCommand().equals("New Game")) {
        System.out.println("New Game");
      }
    }
  }
}
