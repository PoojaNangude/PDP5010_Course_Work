package battle;

import gear.GearCatalog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import player.Player;
import weapon.WeaponCatalog;

/**
 * Driver class for Battle.
 */
public class BattleDriver {
  /**
   * Driver class to simulate the battle between 2 players.
   */
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to the battle arena!!!!");
    /* Creating a new battle which will create players only with their basic abilities and
    also equips them with 20 random equipments */
    Battle battle = new Battle();
    battle.createBattleArena();

    System.out.println("Player abilities after equipping themselves with Gears: ");
    System.out.println("-----Player 1-----");
    printPlayerInformation(battle.getPlayer1());
    System.out.println();
    System.out.println("-----Player 2-----");
    printPlayerInformation(battle.getPlayer2());
    System.out.println();
    System.out.println("Striking Player: "
            + battle.getPlayer1().getFirstStrikingPlayer(battle.getPlayer2()));
    battle.playBattle();
    if (battle.getWinningPlayer() == null) {
      System.out.println("The players have not done any damage to each other in the last 20 hits. "
              + "So it has been declared a tie.");
    } else {
      System.out.println("Winning Player: " + battle.getWinningPlayer());
    }

    char playAgain = 'y';
    while (playAgain != 'n') {
      System.out.println("Does you want a rematch (y/n): ");
      playAgain = sc.nextLine().charAt(0);
      try {
        if (playAgain == 'y') {
          battle.rematchBattle();
          if (battle.getWinningPlayer() == null) {
            System.out.println("The players have not done any damage to "
                    + "each other in the last 20 hits. So it has been declared a tie.");
          } else {
            System.out.println("Winning Player: " + battle.getWinningPlayer());
          }
        } else {
          break;
        }
      } catch (Exception e) {
        System.out.println("Exeception: " + e.toString());
      }
    }
  }

  /**
   * Method to print player information.
   */
  public static void printPlayerInformation(Player player) {
    System.out.println("Strength: " + player.getStrength());
    System.out.println("Constitution: " + player.getConstitution());
    System.out.println("Dexterity: " + player.getDexterity());
    System.out.println("Charisma: " + player.getCharisma());
    System.out.println("Health: " + player.getHealth());
    System.out.println();
    System.out.print("Weapon yielded by player: ");
    if (player.getWeaponInUse() != null) {
      if (player.getWeaponInUse().getWeapon() == WeaponCatalog.SWORDS) {
        System.out.print(player.getWeaponInUse().getWeapon() + " "
                + player.getWeaponInUse().getSwordType());
      } else {
        System.out.print(player.getWeaponInUse().getWeapon());
      }
    }
    System.out.println();
    System.out.print("Gears in Use: ");
    for (int i = 0; i < player.getEquipmentsInUse().size(); i++) {
      if (player.getEquipmentsInUse().get(i).getGear() == GearCatalog.BELTS) {
        System.out.print(player.getEquipmentsInUse().get(i).getGear() + " "
                + player.getEquipmentsInUse().get(i).getBeltUnitSize() + ", ");
      } else {
        System.out.print(player.getEquipmentsInUse().get(i).getGear() + ", ");
      }
    }
    System.out.println();
    System.out.println("Effect of gears on the player abilities: ");
    System.out.println("Effect on strength: " + player.getEffectOfGearsOnStrength()
            + ", Effect on constitution: " + player.getEffectOfGearsOnConstitution()
            + ", Effect on dexterity " + player.getEffectOfGearsOnDexterity()
            + ", Effect on charisma: " + player.getEffectOfGearsOnCharisma());
    System.out.println();
  }
}
