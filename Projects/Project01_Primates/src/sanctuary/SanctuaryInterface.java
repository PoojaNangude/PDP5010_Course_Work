package sanctuary;

import java.util.ArrayList;

/**
 * A SanctuaryInterface represents the different functions which
 * are used to create a sanctuary and perform different actions based
 * on the user.
 */
public interface SanctuaryInterface {

  /**
   * Create a Sanctuary with n isolation rooms and m enclosures.
   */
  public void createSanctuary();

  /**
   * Extends the capacity of the sanctuary by n more isolations and m more eclosures.
   */
  public void extendSanctuary(int n, int m);

  /**
   * Get the list of all the species that are currently housed.
   * The list will be in alphabetical order and will contain the
   * species name and their location in the sanctuary (both in isolation
   * and enclosure).
   *
   * @return the array list of SpeciesHousingLocation
   */
  public ArrayList<SpeciesHousingLocation> speciesHoused();

  /**
   * Get the location of a particular species in the sanctuary.
   *
   * @return the species name and location (isolation room id and enclosure id).
   */
  public SpeciesHousingLocation lookupSpecies(String speciesName);

  /**
   * Get the sign of a particular enclosure based on enclosure id.
   *
   * @return an array list of EnclosureSign which will contain the name, sex and favourite food.
   */
  public ArrayList<EnclosureSign> getEnclosureSign(int enclosureId);

  /**
   * Get an alphabetical list(by name) of all monkeys that are housed
   * in the Sanctuary.
   *
   * @return an array list of MonkeyLocationByName which had species name and its location.
   */
  public ArrayList<MonkeyLocationByName> getMonkeyList();

  /**
   * Get a shopping list of the favourite foods of all the inhabitants of
   * the Sanctuary.
   *
   * @return an array list of ShoppingList which will contain the food item name and the quantity.
   */
  public ArrayList<ShoppingList> getShoppingList();
}
