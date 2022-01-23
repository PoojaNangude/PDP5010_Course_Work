package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import weather.WeatherReading;
import weather.StevensonReading;

import org.junit.Before;
import org.junit.Test;

/**
 * WeatherReading represented as air temperature, dew point, wind speed and total rain.
 */
public class WeatherReadingTest {
  private WeatherReading reading;

  /**
   * Expected outputs for test values.
   * Relative Humidity: 45
   * Heat Index: 25
   * Wind Chill: 76
   */
  @Before
  public void setUp() {
    reading = tdvr(23, 12, 3, 12);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * WeatherReading object.
   *
   * @param airTemperature in degree Celsius
   * @param dewPoint in degree Celsius
   * @param windSpeed in miles per hour
   * @param totalRain in total rain in last 24 hours in millimeter
   * @return a new instance of a WeatherReading object
   */
  protected WeatherReading tdvr(int airTemperature, int dewPoint, int windSpeed, int totalRain) {
    return new StevensonReading(airTemperature, dewPoint, windSpeed, totalRain);
  }

  @Test
  public void testGetTemperature() {
    assertEquals(reading.getTemperature(),23,2);
  }

  @Test
  public void testToString() {
    String expectedOutput = "Reading: T = 23, D = 12, v = 3, rain = 12";
    assertEquals(expectedOutput, reading.toString());
    assertEquals("Reading: T = 26, D = 15, v = 6, rain = 15", tdvr(26, 15, 6, 15).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidDewPoint() {
    tdvr(23, 42, 3, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidWindSpeed() {
    tdvr(23, 12, -3, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidTotalRain() {
    tdvr(23, 12, 3, -12);
  }

  @Test
  public void testEquals() {
    assertTrue(reading.equals(reading));
    assertTrue(reading.equals(new StevensonReading(23,12,3,12)));
    assertFalse((tdvr(23, 13, 3, 12)).equals(tdvr(23, 12, 3, 12)));
  }

  @Test
  public void testHashCode() {
    assertEquals(Integer.hashCode(23 + 12 + 3 + 12), tdvr(23, 12, 3,12).hashCode(),2);
  }

  @Test
  public void testGetRelativeHumidity() {
    assertEquals(45, reading.getRelativeHumidity(),2);
  }

  @Test
  public void testGetHeatIndex() {
    assertEquals(25, reading.getHeatIndex(),2);
  }

  @Test
  public void testGetWindChill() {
    assertEquals(76, reading.getWindChill(),2);
  }
}