package lookandsay;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/**
 * Class LookAndSayIterator which implements the operations of the RIterator interface.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private BigInteger seedValue;
  private final BigInteger endValue;
  private static final BigInteger DEFAULT_SEED = new BigInteger("9".repeat(100));
  private static final BigInteger DEFAULT_END = new BigInteger("1");
  Boolean isNextCalled;

  /**
   * Constructor which takes the starting seed value and end value as the input.
   */
  public LookAndSayIterator(BigInteger seedValue, BigInteger endValue) {
    if (seedValue.compareTo(new BigInteger("0")) <= 0 || seedValue.compareTo(endValue) >= 0
            || seedValue.toString().contains("0")) {
      throw new IllegalArgumentException();
    }
    this.seedValue = seedValue;
    this.endValue = endValue;
    this.isNextCalled = false;
  }

  /**
   * Constructor which takes only starting seed value as input and assigns the default maximum
   * limit as the end value.
   */
  public LookAndSayIterator(BigInteger seed) {
    this(seed, DEFAULT_SEED);
  }

  /**
   * Constructor with no arguments which assigns the default minimum and maximum values as
   * the starting seed value and value.
   */
  public LookAndSayIterator() {
    this(DEFAULT_END, DEFAULT_SEED);
  }

  @Override
  public BigInteger prev() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    if (this.isNextCalled) {
      this.seedValue = getPreviousNumber();
      this.isNextCalled = false;
    }
    if (this.seedValue.toString().contains("0")) {
      return new BigInteger("0");
    }
    this.seedValue = getPreviousNumber();
    if (this.seedValue.toString().contains("0")) {
      return new BigInteger("0");
    }
    return this.seedValue;
  }

  @Override
  public boolean hasPrevious() {
    return (this.seedValue.toString().length() % 2 == 0
            && getPreviousNumber().compareTo(this.endValue) < 0);
  }

  @Override
  public boolean hasNext() {
    return this.seedValue.compareTo(this.endValue) <= 0;
  }

  @Override
  public BigInteger next() throws NoSuchElementException {
    BigInteger presentValue = new BigInteger(this.seedValue.toString());
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    this.seedValue = getNextNumberInSequence();
    this.isNextCalled = true;
    return presentValue;
  }

  private BigInteger getNextNumberInSequence() {
    int frequency = 1;
    char number = this.seedValue.toString().charAt(0);
    StringBuilder nextNumber = new StringBuilder();
    if (this.seedValue.toString().length() == 1) {
      nextNumber.append(frequency).append(number);
    } else {
      for (int i = 1; i < this.seedValue.toString().length(); i++) {
        if (number == this.seedValue.toString().charAt(i)) {
          frequency++;
          if (i == this.seedValue.toString().length() - 1) {
            nextNumber.append(frequency).append(number);
            break;
          }
        } else {
          nextNumber.append(frequency).append(number);
          frequency = 1;
          number = this.seedValue.toString().charAt(i);
          if (i == this.seedValue.toString().length() - 1) {
            nextNumber.append(frequency).append(number);
            break;
          }
        }
      }
    }
    return new BigInteger(nextNumber.toString());
  }

  private BigInteger getPreviousNumber() {
    StringBuilder previousNumber = new StringBuilder();
    String seed = this.seedValue.toString();
    int i = 0;
    while (i < seed.length()) {
      int frequency = seed.charAt(i) - 48;
      char ch = seed.charAt(i + 1);
      for (int seq = 0; seq < frequency; seq ++) {
        previousNumber.append(ch);
      }
      i = i + 2;
    }
    return new BigInteger(previousNumber.toString());
  }
}