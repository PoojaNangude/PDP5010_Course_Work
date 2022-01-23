package monster;

/**
 * The Otyug class represent one monster which will initiallly have full health.
 */
public class Otyugh {
  private int health;

  /**
   * Constructor that creates a monster will full health.
   * In this game 2 means full health, as monster should be hit twice to kill it.
   */
  public Otyugh() {
    this.health = 2;
  }

  public int getHealth() {
    int monsterHealth = this.health;
    return monsterHealth;
  }

  /**
   * Decrements the monsters' health.
   * This function will be called when the player successfully hits the monster with an arrow.
   */
  public void decrementHealth() {
    if (this.health > 0) {
      this.health = this.health - 1;
    }
  }

  @Override
  public String toString() {
    return String.format("(M %d )", this.health);
  }
}
