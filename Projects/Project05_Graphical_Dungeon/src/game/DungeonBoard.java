package game;

import dungeon.DungeonDump;
import dungeon.DungeonNode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import location.Location;
import treasure.Treasure;
import treasure.TreasureCatalog;

/**
 * DungeonBoard class which will constructs the entire dungeon and also display other details
 * like player information and details of the player's present location.
 */
public class DungeonBoard extends JPanel {
  private final ReadOnlyGameModel model;

  public DungeonBoard(ReadOnlyGameModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    Map<String, String> list = new HashMap<>();
    list.put("E N S W ", "res/dungeon-images/NSEW.png");
    list.put("E N S ", "res/dungeon-images/NSE.png");
    list.put("N S W ", "res/dungeon-images/NSW.png");
    list.put("E S W ", "res/dungeon-images/SEW.png");
    list.put("E N W ", "res/dungeon-images/NEW.png");
    list.put("E N ", "res/dungeon-images/NE.png");
    list.put("E S ", "res/dungeon-images/SE.png");
    list.put("S W ", "res/dungeon-images/SW.png");
    list.put("N W ", "res/dungeon-images/NW.png");
    list.put("N S ", "res/dungeon-images/NS.png");
    list.put("E W ", "res/dungeon-images/EW.png");
    list.put("E ", "res/dungeon-images/E.png");
    list.put("W ", "res/dungeon-images/W.png");
    list.put("N ", "res/dungeon-images/N.png");
    list.put("S ", "res/dungeon-images/S.png");


    DungeonDump[][] dungeonDump;
    dungeonDump = model.getDungeonDump();
    Graphics2D g2d = (Graphics2D) g;


    Location playerLocation = model.getPlayerPresentLocationDetails().getLocation();
    int playerX = 0;
    int playerY = 0;
    int y = 80;
    int x = 0;
    BufferedImage img = null;
    BufferedImage player = null;
    BufferedImage treasure = null;
    BufferedImage arrows = null;
    BufferedImage monster = null;
    BufferedImage stench = null;
    BufferedImage thief = null;
    BufferedImage pit = null;
    BufferedImage wind = null;

    System.out.println("Start point: " + model.getStartPointLocationId());
    System.out.println("End point: " + model.getEndPointLocationId());

    // player details
    String treasureCollected = model.getPlayer().getTreasureCollectedSummary();
    int arrowCount = model.getPlayer().getArrowCount();
    g2d.drawString("PLAYER DETAILS: ", 80, 20);
    g2d.drawString("Treasure collected => " + treasureCollected, 80, 40);
    g2d.drawString("Arrow count: " + arrowCount, 80, 60);

    // player location details
    DungeonNode locationDetails = model.getPlayerPresentLocationDetails();
    if (locationDetails.getArrowCount() > 0 || locationDetails.getTreasureSummary().size() > 0) {
      g2d.drawString("LOCATION DETAILS: ", 460, 20);
    }
    if (locationDetails.getArrowCount() > 0) {
      g2d.drawString("The player sees " + locationDetails.getArrowCount() + " arrows.", 460, 40);
    }
    if (locationDetails.getTreasureSummary().size() > 0) {
      g2d.drawString("Player finds " + locationDetails.getTreasureSummary().toString(), 460, 60);
    }

    int pungentSmellLevel = model.getPlayer().getLevelOfPungentSmellDetected();
    if (pungentSmellLevel == 2) {
      g2d.drawString("The player senses a horribly pungent odour", 80, 80);
    } else if (pungentSmellLevel == 1) {
      g2d.drawString("The player senses a slightly pungent odour", 80, 80);
    }

    int windiness = model.getPlayer().getSenseOfWind();
    if (windiness == 2) {
      g2d.drawString("The player senses a strong current", 80, 100);
    } else if (windiness == 1) {
      g2d.drawString("The player senses a light current", 80, 100);
    }
    // Shooting arrows

    Graphics2D shootResult = (Graphics2D) g;
    Font shootFont = new Font("Courier New", 1, 30);
    shootResult.setFont(shootFont);
    shootResult.setColor(Color.RED);
    shootResult.drawString(model.getShootResult(), 80, 120);

    Graphics2D thiefInCave = (Graphics2D) g;
    Font thiefFont = new Font("Courier New", 1, 30);
    thiefInCave.setFont(thiefFont);
    thiefInCave.setColor(Color.RED);
    thiefInCave.drawString(model.getThiefInCave(), 80, 130);


    // display dungeon
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;
        String value = list.get(dungeonDump[i][j].getPossibleMoves());
        try {
          img = ImageIO.read(new File(value));
          if (playerLocation.getX() == i && playerLocation.getY() == j) {
            player = ImageIO.read(new File("res/dungeon-images/mario-player.png"));
            playerX = x;
            playerY = y;
          }
        } catch (Exception e) {
          System.out.println(e.toString());
        }

        if (dungeonDump[i][j].getVisited()) {
          g2d.drawImage(img, x, y, 80, 80, this);
        } else {
          try {
            img = ImageIO.read(new File("res/dungeon-images/blank.png"));
            g2d.drawImage(img, x, y, 80, 80, this);
          } catch (Exception e) {
            System.out.println(e.toString());
          }
        }
        // g2d.drawImage(img,x,y,80,80,this);
      }
    }


    // player smell sensation
    int pungentSmellIntensity = model.getPlayer().getLevelOfPungentSmellDetected();
    try {
      if (pungentSmellIntensity == 1) {
        stench = ImageIO.read(new File("res/dungeon-images/stench01.png"));
      } else if (pungentSmellIntensity == 2) {
        stench = ImageIO.read(new File("res/dungeon-images/stench02.png"));
      }
      g2d.drawImage(stench, playerX + 45, playerY + 25, 20, 20, this);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // player wind sensation
    int windSense = model.getPlayer().getSenseOfWind();
    try {
      if (windSense == 1) {
        wind = ImageIO.read(new File("res/dungeon-images/slow_wind.png"));
      } else if (windSense == 2) {
        wind = ImageIO.read(new File("res/dungeon-images/fast_wind.png"));
      }
      g2d.drawImage(wind, playerX + 45, playerY + 25, 40, 40, this);
    } catch (Exception e) {
      System.out.println(e.toString());
    }


    // display thieves
    y = 80;
    x = 0;
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;


        if (dungeonDump[i][j].getHasThief() && dungeonDump[i][j].getVisited()) {
          try {
            thief = ImageIO.read(new File("res/dungeon-images/thief.png"));
          } catch (Exception e) {
            System.out.println(e.toString());
          }
          g2d.drawImage(thief, x + 20, y + 20, 40, 40, this);
        }
      }
    }

    // display player
    g2d.drawImage(player, playerX + 20, playerY + 20, 40, 40, this);

    // display treasure
    y = 80;
    x = 0;
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;
        ArrayList<Treasure> treasureList = dungeonDump[i][j].getTreasure();
        try {
          for (int t = 0; t < treasureList.size(); t++) {
            if (treasureList.get(t).getTreasureType() == TreasureCatalog.DIAMOND) {
              treasure = ImageIO.read(new File("res/dungeon-images/diamond.png"));
            } else if (treasureList.get(t).getTreasureType() == TreasureCatalog.RUBY) {
              treasure = ImageIO.read(new File("res/dungeon-images/ruby.png"));
            } else if (treasureList.get(t).getTreasureType() == TreasureCatalog.SAPPHIRE) {
              treasure = ImageIO.read(new File("res/dungeon-images/sapphire.png"));
            }

            if (dungeonDump[i][j].getVisited()) {
              g2d.drawImage(treasure, x + 35, y + 35, 15, 15, this);
            } else {
              try {
                img = ImageIO.read(new File("res/dungeon-images/blank.png"));
                g2d.drawImage(img, x, y, 80, 80, this);
              } catch (Exception e) {
                System.out.println(e.toString());
              }
            }
            // g2d.drawImage(treasure, x + 35, y + 35, 15, 15, this);
          }
        } catch (Exception e) {
          System.out.println(e.toString());
        }
      }
    }

    // display arrows
    y = 80;
    x = 0;
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;
        if (dungeonDump[i][j].getArrowCount() > 0) {
          try {
            arrows = ImageIO.read(new File("res/dungeon-images/arrow-golden.png"));
          } catch (Exception e) {
            System.out.println(e.toString());
          }

          if (dungeonDump[i][j].getVisited()) {
            g2d.drawImage(arrows, x + 40, y + 40, 15, 15, this);
          } else {
            try {
              img = ImageIO.read(new File("res/dungeon-images/blank.png"));
              g2d.drawImage(img, x, y, 80, 80, this);
            } catch (Exception e) {
              System.out.println(e.toString());
            }
          }
          // g2d.drawImage(arrows, x + 40, y + 40, 15, 15, this);
        }
      }
    }

    //display monsters
    y = 80;
    x = 0;
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;
        if (dungeonDump[i][j].getMonster() != null
                && dungeonDump[i][j].getMonster().getHealth() > 0) {
          try {
            monster = ImageIO.read(new File("res/dungeon-images/otyugh.png"));
          } catch (Exception e) {
            System.out.println(e.toString());
          }

          if (dungeonDump[i][j].getVisited()) {
            g2d.drawImage(monster, x + 40, y + 40, 30, 30, this);
          } else {
            try {
              img = ImageIO.read(new File("res/dungeon-images/blank.png"));
              g2d.drawImage(img, x, y, 80, 80, this);
            } catch (Exception e) {
              System.out.println(e.toString());
            }
          }
          // g2d.drawImage(monster, x + 40, y + 40, 30, 30, this);
        }
      }
    }

    // display pits
    y = 80;
    x = 0;
    for (int i = 0; i < model.getRows(); i++) {
      y = y + 80;
      x = 0;
      for (int j = 0; j < model.getColumns(); j++) {
        x = x + 80;
        if (dungeonDump[i][j].getHasPit()) {
          try {
            pit = ImageIO.read(new File("res/dungeon-images/pit.png"));
          } catch (Exception e) {
            System.out.println(e.toString());
          }
          g2d.drawImage(pit, x + 20, y + 20, 40, 40, this);
        }
      }
    }

    if (model.isGameOver()) {
      Graphics2D message = (Graphics2D) g;
      Font myFont = new Font("Courier New", 1, 40);
      message.setFont(myFont);
      message.setColor(Color.RED);
      if (model.getPlayer().getIsAlive()) {
        g2d.drawString("GAME OVER! You won the game", 100, 140);
      } else {
        if (!model.getPitFall().equals("")) {
          g2d.drawString(model.getPitFall(), 100, 140);
        } else {
          g2d.drawString("You were eaten by a monster!!", 100, 140);
        }
      }
    }
  }
}
