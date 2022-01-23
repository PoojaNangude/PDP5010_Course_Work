package battle;

/**
 * Battle interface with functions required to create and start a battle.
 */
public interface BattleInterface {

  /**
   * Creates a battle arena in which players are assigned basic abilities, equipped with
   * gears, assigned weapons and the effect of gears on abilities is updates.
   */
  public void createBattleArena();

  /**
   * This  function starts the battle between 2 players and terminates when one of the
   * players health becomes zero or the players have not been able to give potential damage
   * to each other for a long time.
   */
  public void playBattle();

}
