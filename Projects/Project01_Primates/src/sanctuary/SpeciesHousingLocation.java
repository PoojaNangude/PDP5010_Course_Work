package sanctuary;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * SpeciesHousingLocation class represented as species name and their location in the santuary
 * both in isolation rooms and enclosures.
 */
public class SpeciesHousingLocation {
  private String name;
  private ArrayList<Integer> isolationIds;
  private ArrayList<Integer> enclosureIds;

  /**
   * Constructs a list of SpeciesHousingLocation in terms of species name and the location
   * of the species in isolations and enclosures.
   *
   * @param name enum String
   * @param isolationIds Integer arraylist having the isolation ids where the species is housed.
   * @param enclosureIds Integer arraylist having the enclosure ids where the species is housed.
   */
  public SpeciesHousingLocation(String name, ArrayList<Integer> isolationIds,
                                ArrayList<Integer> enclosureIds) {
    this.name = name;
    this.isolationIds = isolationIds;
    this.enclosureIds = enclosureIds;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Integer> getIsolationIds() {
    return this.isolationIds;
  }

  public void setIsolationIds(ArrayList<Integer> isolationIds) {
    this.isolationIds = isolationIds;
  }

  public ArrayList<Integer> getEnclosureIds() {
    return this.enclosureIds;
  }

  public void setEnclosureIds(ArrayList<Integer> enclosureIds) {
    this.enclosureIds = enclosureIds;
  }

  public static Comparator<SpeciesHousingLocation> SPECIES_NAME_COMPARATOR = new Comparator
          <SpeciesHousingLocation>() {
    @Override
    public int compare(SpeciesHousingLocation o1, SpeciesHousingLocation o2) {
      return (o1.getName().toUpperCase()).compareTo(o2.getName().toUpperCase());
    }
  };
}
