package questions;

/**
 * This class represents a Likert question type. It offers all the operations mandated
 * by the Question interface.
 */
public class Likert extends AbstractQuestion {

  /**
   * Construct a Likert object using the given question.
   *
   * @param question      question of type string.
   */
  public Likert(String question) {
    super(question);
  }

  @Override
  public String answer(String answer) {
    if (answer == null) {
      return INCORRECT;
    } else if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")
            || answer.equals("5")) {
      return CORRECT;
    }
    return INCORRECT;
  }

  @Override
  public String getText() {
    return this.question;
  }

  @Override
  protected int compareLikert(Likert other) {
    return other.getText().compareTo(this.getText());
  }

  @Override
  protected int compareTrueFalse(TrueFalse other) {
    return -1;
  }

  @Override
  protected int compareMultipleSelect(MultipleSelect other) {
    return -1;
  }

  @Override
  protected int compareMultipleChoice(MultipleChoice other) {
    return -1;
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareLikert(this);
    }
    return 1;
  }

  @Override
  public String toString() {
    return String.format("Likert, Question: %s", this.getText());
  }
}
