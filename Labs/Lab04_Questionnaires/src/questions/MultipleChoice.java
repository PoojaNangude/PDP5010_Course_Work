package questions;

/**
 * This class represents a MultipleChoice question. Since a MultipleChoice is a special instance of
 * MultipleSelect, this class extends the {@link MultipleSelect} class. However in the context of
 * the application, a MultipleChoice question is a separate kind of question, and therefore a
 * MultipleChoice may not be deemed as equal to a MultipleSelect of identical parameters.
 */
public class MultipleChoice extends MultipleSelect {

  /**
   * Constructor.
   *
   * @param question    the question in String
   * @param answer      the answer to the question in String
   * @param options     the options the question in a String array
   */
  public MultipleChoice(String question, String answer, String... options)
          throws IllegalArgumentException {
    super(question, answer, options);
    if (answer.split(" ").length != 1) {
      throw new IllegalArgumentException("Multiple choice questions must have a single answer.");
    }
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
    return 1;
  }

  @Override
  protected int compareMultipleChoice(MultipleChoice other) {
    return other.getText().compareTo(this.getText());
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof AbstractQuestion) {
      AbstractQuestion abstractQuestion = (AbstractQuestion) o;
      return abstractQuestion.compareMultipleChoice(this);
    }
    return 1;
  }

  @Override
  public String toString() {
    String optionString = "";
    for (int i = 0; i < this.options.length; i++) {
      optionString = optionString + this.options[i] + " ";
    }
    return String.format("MultipleChoice, Question: %s Answer: %s Options: %s", this.getText(),
            this.answer, optionString);
  }
}
