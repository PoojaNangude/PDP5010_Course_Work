package sanctuary;

/**
 * Isolation class represented as the isolation room id and the
 * Monkey object which has the information of the monkey housed in
 * the isolation.
 */
public class Isolation {
  public static int ISOLATION_ID = 1;

  private int isolationId;
  private Monkey monkey;

  /**
   * Constructs an Isolation and assigns every an enclosure id and initialises it with
   * an empty Monkey object.
   */
  public Isolation() {
    this.isolationId = ISOLATION_ID++;
    this.monkey = new Monkey();
  }

  public int getIsolationId() {
    return this.isolationId;
  }

  public Monkey getMonkey() {
    return this.monkey;
  }

  public void setMonkey(Monkey monkey) {
    this.monkey = monkey;
  }

}
