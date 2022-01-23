package random;

/**
 * RandomNumberGenerator class to return a random number based on the minimum and maximum value.
 */
public class RandomNumberGenerator {

  public static int getRandomNumberInRange(int lowerLimit, int upperLimit) {
    return lowerLimit + (int) (Math.random() * ((upperLimit - lowerLimit)));
  }

  public static int getFakeRandomNumber(int n) {
    return n;
  }
}
