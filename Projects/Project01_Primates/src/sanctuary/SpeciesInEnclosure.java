package sanctuary;

import java.util.ArrayList;

/**
 * SpeciesInEnclosure class represented as species name and their location in the sanctuary
 * in enclosures in the form of array list of integers which have the enclosure ids.
 */
public class SpeciesInEnclosure {
  String speciesName;
  ArrayList<Integer> enclosureIds;

  SpeciesInEnclosure(String speciesName, ArrayList<Integer> enclosureIds) {
    this.speciesName = speciesName;
    this.enclosureIds = enclosureIds;
  }

  public void setSpeciesName(String speciesName) {
    this.speciesName = speciesName;
  }

  public void setEnclosureIds(ArrayList<Integer> enclosureIds) {
    this.enclosureIds = enclosureIds;
  }

  public String getSpeciesName() {
    return this.speciesName;
  }

  public ArrayList<Integer> getEnclosureIds() {
    return this.enclosureIds;
  }
}
