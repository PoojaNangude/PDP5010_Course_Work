package questions;

/**
 * An abstract class that contains all the code that is shared by all types
 * of Question.
 */
public abstract class AbstractQuestion implements Question {
  protected String question;

  /**
   * Protected constructor for use by subclasses.
   *
   * @param question the initial reference for this Question
   */
  protected AbstractQuestion(String question) {
    this.question = question;
  }

  /**
   * Determine whether this question is equal to a Likert object.
   *
   * @param other the Likert object to which this question must be compared
   * @return 1 by default, subclasses may override
   */
  protected int compareLikert(Likert other) {
    return 1;
  }

  /**
   * Determine whether this question is equal to a TrueFalse object.
   *
   * @param other the TrueFalse object to which this question must be compared
   * @return 1 by default, subclasses may override
   */
  protected int compareTrueFalse(TrueFalse other) {
    return 1;
  }

  /**
   * Determine whether this question is equal to a MultiSelect object.
   *
   * @param other the MultiSelect object to which this question must be compared
   * @return 1 by default, subclasses may override
   */
  protected int compareMultipleSelect(MultipleSelect other) {
    return 1;
  }

  /**
   * Determine whether this question is equal to a MultipleChoice object.
   *
   * @param other the MultipleChoice object to which this question must be compared
   * @return 1 by default, subclasses may override
   */
  protected int compareMultipleChoice(MultipleChoice other) {
    return 1;
  }
}