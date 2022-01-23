package sanctuary;

import java.util.ArrayList;

/**
 * Enclosure class represented as enclosureId, species name, area of the enclosure,
 * area occupied, total number of animals housed in the enclosure and an ArrayList of
 * Monkey class having the information of all the monkeys in the enclosure.
 */
public class Enclosure {
  public static int ENCLOSURE_ID = 1;

  private int enclosureId;
  private String species;
  private int area;
  private int consumedArea;
  private int animalCount;
  private ArrayList<Monkey> enclosureList;

  /**
   * Constructs an Enclosure and assigns every enclosure an enclosure id and
   * initialises species name, area, consumed area, animal count and
   * enclosure list to default values.
   */
  public Enclosure() {
    this.enclosureId = ENCLOSURE_ID++;
    this.species = "";
    this.area = 0;
    this.consumedArea = 0;
    this.animalCount = 0;
    this.enclosureList = new ArrayList<Monkey>();
  }

  public int getEnclosureId() {
    return this.enclosureId;
  }

  public String getSpecies() {
    return this.species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public int getArea() {
    return this.area;
  }

  public void setArea(int area) {
    this.area = area;
  }

  public int getConsumedArea() {
    return this.consumedArea;
  }

  public void setConsumedArea(int consumedArea) {
    this.consumedArea = consumedArea;
  }

  public int getAnimalCount() {
    return this.animalCount;
  }

  public void setAnimalCount(int animalCount) {
    this.animalCount = animalCount;
  }

  public ArrayList<Monkey> getEnclosureList() {
    return this.enclosureList;
  }

  public void setEnclosureList(ArrayList<Monkey> enclosureList) {
    this.enclosureList = enclosureList;
  }
}
