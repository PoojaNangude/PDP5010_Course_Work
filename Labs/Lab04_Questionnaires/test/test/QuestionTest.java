package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;

/**
 * This class contains all the unit tests for various kinds of Questions.
 */
public class QuestionTest {
  private Question likert1;
  private Question likert2;
  private Question likert3;
  private Question likert4;

  private Question trueFalse1;
  private Question trueFalse2;
  private Question trueFalse3;
  private Question trueFalse4;

  private Question multipleSelect1;
  private Question multipleSelect2;
  private Question multipleSelect3;
  private Question multipleSelect4;
  private Question multipleSelect5;

  private Question multipleChoice1;
  private Question multipleChoice2;
  private Question multipleChoice3;
  private Question multipleChoice4;
  private Question multipleChoice5;

  private Question[] questionnaire = new Question[7];
  private Question[] sortedQuestionnaire = new Question[7];

  /** Setting up objects for all the unit tests. */
  @Before
  public void setUp() throws Exception {
    likert1 = new Likert("The projects in the coursework help you clear "
            + "the core OOPS concepts?");
    likert2 = new Likert("The coursework helped you to gain skills required "
            + "for interviews?");
    likert3 = new Likert("Time alloted for assignments was more that sufficient?");
    likert4 = new Likert("The projects in the coursework help you clear "
            + "the core OOPS concepts?");

    trueFalse1 = new TrueFalse("OOPS and functional programming are the same", "False");
    trueFalse2 = new TrueFalse("HTTPS is secure.", "True");
    trueFalse3 = new TrueFalse("CSS stands for Cascading Style Sheets", "True");
    trueFalse4 = new TrueFalse("OOPS and functional programming are the same", "False");

    multipleSelect1 = new MultipleSelect("Which are OOPS concepts", "1 2 4", "1. Abstraction",
            "2. Inheritance", "3. Rectification", "4. Polymorphism");
    multipleSelect2 = new MultipleSelect("What can be used for front-end", "1 2 4", "1. React",
            "2. Angular", "3. Python", "4. JavaScript", "5. Flask");
    multipleSelect3 = new MultipleSelect("Which are multiples of 10", "2 3", "1. 25",
            "2. 30", "3. 40", "4. 78", "5. 62", "6. 99");
    multipleSelect4 = new MultipleSelect("Which are OOPS concepts", "1 2 4", "1. Abstraction",
            "2. Inheritance", "3. Rectification", "4. Polymorphism");
    multipleSelect5 = new MultipleSelect("Which is not OOPS concept", "3", "1. Abstraction",
            "2. Inheritance", "3. Rectification", "4. Polymorphism");


    multipleChoice1 = new MultipleChoice("Which number is even?", "3", "1. 55",
            "2. 67", "3. 80", "4. 91");
    multipleChoice2 = new MultipleChoice("Which number is prime?", "4", "1. 24",
            "2. 66", "3. 15", "4. 89", "5. 100");
    multipleChoice3 = new MultipleChoice("Which is multiple of 5", "6", "1. 61",
            "2. 59", "3. 27", "4. 86", "5. 554", "6. 80");
    multipleChoice4 = new MultipleChoice("Which number is even?", "3", "1. 55",
            "2. 67", "3. 80", "4. 91");
    multipleChoice5 = new MultipleChoice("Which is not OOPS concept", "3", "1. Abstraction",
            "2. Inheritance", "3. Rectification", "4. Polymorphism");

    questionnaire[0] = likert1;
    questionnaire[1] = trueFalse2;
    questionnaire[2] = multipleSelect5;
    questionnaire[3] = multipleSelect3;
    questionnaire[4] = multipleChoice4;
    questionnaire[5] = likert3;
    questionnaire[6] = trueFalse4;

    sortedQuestionnaire[0] = trueFalse2;
    sortedQuestionnaire[1] = trueFalse4;
    sortedQuestionnaire[2] = multipleChoice4;
    sortedQuestionnaire[3] = multipleSelect3;
    sortedQuestionnaire[4] = multipleSelect5;
    sortedQuestionnaire[5] = likert1;
    sortedQuestionnaire[6] = likert3;
  }

  /**
   * Tests whether objects have been created properly. It does this by using their
   * toString methods.
   */
  @Test
  public void testObjectData() {
    assertEquals("Likert, Question: The projects in the coursework help you clear the core OOPS "
            + "concepts?", likert1.toString());
    assertEquals("TrueFalse, Question: HTTPS is secure. Answer: True", trueFalse2.toString());
    assertEquals("MultipleSelect, Question: Which are multiples of 10 Answer: 2 3 Options: "
            + "1. 25 2. 30 3. 40 4. 78 5. 62 6. 99 ", multipleSelect3.toString());
    assertEquals("MultipleChoice, Question: Which number is even? Answer: 3 Options: 1. 55 2. "
            + "67 3. 80 4. 91 ", multipleChoice1.toString());
  }

  //test that an error is thrown if number of options in MultipleSelect is less than 3
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectOptionsCount1() {
    new MultipleSelect("Which are OOPS concepts", "1 2 4", "1. Abstraction",
            "2. Inheritance");
  }

  //test that an error is thrown if number of options in MultipleSelect is greater than 8
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleSelectOptionsCount2() {
    new MultipleSelect("Which are OOPS concepts", "1 2 4", "1. Option1",
            "2. Option3", "3. Option3", "4. Option4", "5. Option5", "6. Option6",
            "7. Option7", "8. Option8", "9. Option9");
  }

  //test that an error is thrown if number of options in MultipleChoice is less than 3
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceOptionsCount1() {
    new MultipleChoice("Which number is even?", "3", "1. 55", "2. 67");
  }

  //test that an error is thrown if number of options in MultipleChoice is greater than 8
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleChoiceOptionsCount2() {
    new MultipleChoice("Which are OOPS concepts", "3", "1. Option1",
            "2. Option3", "3. Option3", "4. Option4", "5. Option5", "6. Option6",
            "7. Option7", "8. Option8", "9. Option9");
  }

  //  test that an error is thrown if answer to a True/False question is given something other
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnswerForTrueFalseQuestion() {
    new TrueFalse("HTTPS is secure.", "HTTP");
  }

  //  test that an error is thrown if more than one answer given for Multiple Choice question
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnswerForMultipleChoiceQuestion() {
    new MultipleChoice("Which is not OOPS concept", "3 4 1", "1. Abstraction",
            "2. Inheritance", "3. Rectification", "4. Polymorphism");
  }

  /**
   * Test if getText returns the question text for every question type.
   */
  @Test
  public void testGetText() {
    assertEquals("Time alloted for assignments was more that sufficient?", likert3.getText());
    assertEquals("OOPS and functional programming are the same", trueFalse4.getText());
    assertEquals("Which are OOPS concepts", multipleSelect4.getText());
    assertEquals("Which number is even?", multipleChoice4.getText());
  }

  /**
   * Test if testAnswer returns the correctness of a question's answer by providing the
   * user's answers.
   */
  @Test
  public void testAnswer() {
    assertEquals("Correct", likert2.answer("1"));
    assertEquals("Correct", likert2.answer("2"));
    assertEquals("Correct", likert2.answer("3"));
    assertEquals("Correct", likert2.answer("4"));
    assertEquals("Correct", likert2.answer("5"));
    assertEquals("Incorrect", likert4.answer("7"));
    assertEquals("Incorrect", likert4.answer(null));
    assertEquals("Incorrect", likert4.answer(""));

    assertEquals("Correct", trueFalse1.answer("False"));
    assertEquals("Incorrect", trueFalse4.answer("True"));
    assertEquals("Incorrect", trueFalse2.answer("HTTPS"));
    assertEquals("Incorrect", trueFalse2.answer(null));
    assertEquals("Incorrect", trueFalse2.answer(""));

    assertEquals("Correct", multipleSelect2.answer("1 2 4"));
    assertEquals("Correct", multipleSelect2.answer("1 2  4     "));
    assertEquals("Correct", multipleSelect2.answer("4 1 2"));
    assertEquals("Incorrect", multipleSelect2.answer("1 2 4 5"));
    assertEquals("Incorrect", multipleSelect3.answer("2"));
    assertEquals("Incorrect", multipleSelect5.answer("1 1"));
    assertEquals("Incorrect", multipleSelect5.answer(null));
    assertEquals("Incorrect", multipleSelect4.answer(""));

    assertEquals("Correct", multipleChoice5.answer("3"));
    assertEquals("Incorrect", multipleChoice3.answer("5"));
    assertEquals("Incorrect", multipleChoice1.answer(null));
    assertEquals("Incorrect", multipleChoice2.answer(""));
    assertEquals("Incorrect", multipleChoice2.answer("2 3"));
  }

  /**
   * Test the working of compareTo function for all question types.
   */
  @Test
  public void testQuestionOrdering() {
    //comparing questions of different type with likert type
    assertEquals(13, likert1.compareTo(likert2));
    assertEquals(-13, likert2.compareTo(likert1));
    assertEquals(0, likert1.compareTo(likert1));
    assertEquals(-1, trueFalse1.compareTo(likert1));
    assertEquals(-1, multipleSelect1.compareTo(likert1));
    assertEquals(-1, multipleChoice1.compareTo(likert1));

    // comparing questions of different type with true and false type
    assertEquals(1, likert1.compareTo(trueFalse1));
    assertEquals(7, trueFalse1.compareTo(trueFalse2));
    assertEquals(-7, trueFalse2.compareTo(trueFalse1));
    assertEquals(0, trueFalse3.compareTo(trueFalse3));
    assertEquals(1, multipleSelect1.compareTo(trueFalse1));
    assertEquals(1, multipleChoice1.compareTo(trueFalse1));

    // comparing questions of different type with multiple select type
    assertEquals(1, likert1.compareTo(multipleSelect1));
    assertEquals(-1, trueFalse1.compareTo(multipleSelect1));
    assertEquals(-8, multipleSelect2.compareTo(multipleSelect1));
    assertEquals(8, multipleSelect1.compareTo(multipleSelect2));
    assertEquals(0, multipleSelect4.compareTo(multipleSelect4));
    assertEquals(-1, multipleChoice1.compareTo(multipleSelect1));

    // comparing questions of different type with multiple choice type
    assertEquals(1, likert1.compareTo(multipleChoice1));
    assertEquals(-1, trueFalse1.compareTo(multipleChoice1));
    assertEquals(1, multipleSelect2.compareTo(multipleChoice1));
    assertEquals(-11, multipleChoice1.compareTo(multipleChoice2));
    assertEquals(11, multipleChoice2.compareTo(multipleChoice1));
    assertEquals(0, multipleChoice5.compareTo(multipleChoice5));
  }

  /**
   * Test the working of sort function on an array of Questions
   * which has a mixture of the 4 questions types.
   */
  @Test
  public void testQuestionSorting() {
    Arrays.sort(questionnaire);
    assertArrayEquals(sortedQuestionnaire, questionnaire);
  }
}