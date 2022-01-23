package questions;

import java.util.Arrays;

/**
 * This class represents a MultipleSelect question type. It offers all the operations mandated
 * by the Question interface.
 */
public class MultipleSelect extends AbstractQuestion {
  protected String[] options;
  protected String answer;

  /**
   * Construct a MultipleSelect object using the given question, the
   * options and the answer.
   *
   * @param question      question of type string.
   * @param answer        answer to the question in String.
   * @param options       options for thw question in the form of String array.
   */
  public MultipleSelect(String question, String answer, String... options)
          throws IllegalArgumentException {
    super(question);
    if (options.length  < 3) {
      throw new IllegalArgumentException("The question must have at least 3 options");
    } else if (options.length > 8) {
      throw new IllegalArgumentException("The question must at most 8 options");
    }
    this.answer = answer;
    this.options = options;
  }

  @Override
  public String answer(String answer) {
    if (answer == null || answer.isEmpty()) {
      return INCORRECT;
    }
    String[] correctAnswer = (this.answer.replaceAll("\\s+", " ")).split(" ");
    String[] answerGiven =  (answer.replaceAll("\\s+", " ")).split(" ");

    Arrays.sort(correctAnswer);
    Arrays.sort(answerGiven);
    if (Arrays.equals(correctAnswer, answerGiven)) {
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
    return -1;
  }

  @Override
  protected int compareMultipleSelect(MultipleSelect other) {
    return other.getText().compareTo(this.getText());
  }

  @Override
  protected int compareMultipleChoice(MultipleChoice other) {
    return -1;
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareMultipleSelect(this);
    }
    return 1;
  }

  @Override
  public String toString() {
    String optionString = "";
    for (int i = 0; i < this.options.length; i++) {
      optionString = optionString + this.options[i] + " ";
    }
    return String.format("MultipleSelect, Question: %s Answer: %s Options: %s", this.getText(),
            this.answer, optionString);
  }
}
