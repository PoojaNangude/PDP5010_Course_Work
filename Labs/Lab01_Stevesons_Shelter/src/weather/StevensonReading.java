package weather;

/**
 * StevensonReading class represented as air temperature, dew point, wind speed and total rain.
 */
public final class StevensonReading implements WeatherReading {
  private final double airTemperature;
  private final double dewPoint;
  private final double windSpeed;
  private final double totalRain;

  /**
   * Constructs a StevensonReading in terms of its air temperature, dew point,
   * wind speed and total rain.
   *
   * @param airTemperature in Celsius
   * @param dewPoint       in Celsius
   * @param windSpeed      in miles per hour
   * @param totalRain      in last 24 hours in millimeters
   * @throws IllegalArgumentException if any argument is negative
   */
  public StevensonReading(double airTemperature, double dewPoint, double windSpeed,
                          double totalRain)
          throws IllegalArgumentException {
    if ((windSpeed < 0) || (totalRain < 0) || (dewPoint > airTemperature)) {
      throw new IllegalArgumentException(
              "Invalid values");
    }

    this.airTemperature = airTemperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed ;
    this.totalRain = totalRain;

  }

  @Override
  public int getTemperature() {
    return (int) Math.round(this.airTemperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(this.dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(this.windSpeed);
  }

  @Override
  public int getTotalRain() {
    return (int) Math.round(this.totalRain);
  }

  private double evaluateRelativeHumidity() {
    double t = this.airTemperature;
    double d = this.dewPoint;
    double relativeHumidity = 100 - (5 * (t - d));
    return relativeHumidity;
  }

  private double evaluateHeatIndex() {
    final double c1 = -8.78469475556;
    final double c2 = 1.61139411;
    final double c3 = 2.33854883889;
    final double c4 = -0.14611605;
    final double c5 = -0.012308094;
    final double c6 = -0.0164248277778;
    final double c7 = 0.002211732;
    final double c8 = 0.00072546;
    final double c9 = -0.000003582;
    double t = this.airTemperature;
    double r = evaluateRelativeHumidity();
    double heatIndex = (c1 + (c2 * t) + (c3 * r) + (c4 * t * r)
            + (c5 * t * t) + (c6 * r * r)
            + (c7 * t * t * r) + (c8 * t * r * r)
            + (c9 * t * t * r * r));
    return heatIndex;
  }

  private double evaluateWindChill() {
    double t = (this.airTemperature * 1.8) + 32;
    double v = this.windSpeed;
    double windChill = ((35.74 + (0.6215 * t) - (35.75 * Math.pow(v, 0.16))
            + (0.4275 * t * Math.pow(v, 0.16)))) ;
    return windChill;
  }

  @Override
  public int getRelativeHumidity() {
    return (int) Math.round(evaluateRelativeHumidity());
  }

  @Override
  public int getHeatIndex() {
    return (int) Math.round(evaluateHeatIndex());
  }

  @Override
  public int getWindChill() {
    return (int) Math.round(evaluateWindChill());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    /*
        return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", this.airTemperature,
                this.dewPoint, this.windSpeed, this.totalRain);
    */
    return sb.append("Reading: T = ").append( (int) this.getTemperature()).append(", D = ")
            .append( (int) this.getDewPoint()).append(", v = ").append( (int) this.getWindSpeed())
            .append(", rain = ").append( (int) this.getTotalRain()).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WeatherReading)) {
      return false;
    }
    WeatherReading that = (WeatherReading) o;
    return this.getTemperature() == that.getTemperature()
            && this.getWindSpeed() == that.getWindSpeed()
            && this.getDewPoint() == that.getDewPoint()
            && this.getTotalRain() == that.getTotalRain();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(this.getTemperature() + this.getWindSpeed()
            + this.getDewPoint() + this.getTotalRain());
  }
}