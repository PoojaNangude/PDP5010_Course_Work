package questions;

/**
 * This class represents a TrueFalse question type. It offers all the operations mandated
 * by the Question interface.
 */
public class TrueFalse extends AbstractQuestion {
  private String answer;

  /**
   * Construct a TrueFalse object using the given question
   * and its answer.
   *
   * @param question      question of type string.
   * @param answer        answer to the question in String.
   */
  public TrueFalse(String question, String answer) throws IllegalArgumentException {
    super(question);
    if (!answer.equals("True") && !answer.equals("False")) {
      throw new IllegalArgumentException("The answer to the question must be True or False");
    }
    this.answer = answer;
  }

  @Override
  public String answer(String answer) {
    if (answer == null) {
      return INCORRECT;
    } else if (this.answer.equals(answer)) {
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
    return 1;
  }

  @Override
  protected int compareTrueFalse(TrueFalse other) {
    return other.getText().compareTo(this.getText());
  }

  @Override
  protected int compareMultipleSelect(MultipleSelect other) {
    return 1;
  }

  @Override
  protected int compareMultipleChoice(MultipleChoice other) {
    return 1;
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareTrueFalse(this);
    }
    return 1;
  }

  @Override
  public String toString() {
    return String.format("TrueFalse, Question: %s Answer: %s", this.getText(),
            this.answer);
  }
}
