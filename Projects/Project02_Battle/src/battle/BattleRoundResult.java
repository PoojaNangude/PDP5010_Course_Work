package battle;

import player.Player;

/**
 * Class BattleRoundResult used to represent the data of every round of the battle.
 */
public class BattleRoundResult {
  private int round;
  private Player strikingPlayer;
  private Player opponentPlayer;
  private boolean successfulHit;
  private int damageToOpponent;


  /**
   * Constructs a record of every battle round.
   *
   * @param round             integer
   * @param strikingPlayer    of type Player
   * @param opponentPlayer    of type Player
   * @param successfulHit     boolean
   * @param damageToOpponent  integer
   */
  public BattleRoundResult(int round, Player strikingPlayer, Player opponentPlayer,
                           boolean successfulHit, int damageToOpponent) {
    this.round = round;
    this.strikingPlayer = strikingPlayer;
    this.opponentPlayer = opponentPlayer;
    this.successfulHit = successfulHit;
    this.damageToOpponent = damageToOpponent;
  }

  public int getRound() {
    return this.round;
  }

  public Player getStrikingPlayer() {
    return this.strikingPlayer;
  }

  public Player getOpponentPlayer() {
    return this.opponentPlayer;
  }

  public boolean getIsSuccessfulHit() {
    return this.successfulHit;
  }

  public int getDamageToOpponent() {
    return this.damageToOpponent;
  }
}
