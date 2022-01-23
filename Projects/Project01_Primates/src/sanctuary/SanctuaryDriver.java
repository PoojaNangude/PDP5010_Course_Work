package sanctuary;

import java.util.ArrayList;

/**
 * Driver class for Sanctuary.
 */
public class SanctuaryDriver {

  /**
   * A driver program for Sanctuary to show how all the classes and the functions
   * can be used.
   */
  public static void main(String[] args) {
    int n = 5;
    int m = 3;

    // Initialise a sanctuary with 5 isolation rooms and 3 enclosures
    Sanctuary sanctuary = new Sanctuary(n, m);
    sanctuary.createSanctuary();

    /*
     * Assign different area value to every enclosure and intially configuring it
     * to house a particular species
     */
    int randomArea = 20;
    String[] speciesArray = new String[] {"drill", "guereza", "howler"};
    for (int i = 0; i < m; i++) {
      sanctuary.getEnclosures().get(i).setSpecies(speciesArray[i]);
      sanctuary.getEnclosures().get(i).setArea(randomArea);
      randomArea = randomArea + 20;
    }

    System.out.println("-----ISOLATIONS AND ENCLOSURES AFTER CREATING THEM-----");
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();
    System.out.println();

    // creating some monkey objects for testing purpose
    Monkey monkey1 = new Monkey();
    monkey1.setMonkey("Connie", "drill", 'f', 15, 23, 3, MonkeyFavFood.FRUITS,
            MonkeyMedicalStatus.READY);

    Monkey monkey2 = new Monkey();
    monkey2.setMonkey("Booger", "howler", 'm', 5, 12, 1, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);

    Monkey monkey3 = new Monkey();
    monkey3.setMonkey("Quinn", "drill", 'm', 25, 45, 6, MonkeyFavFood.NUTS,
            MonkeyMedicalStatus.READY);

    Monkey monkey4 = new Monkey();
    monkey4.setMonkey("Cuddle", "saki", 'f', 23, 45, 9, MonkeyFavFood.LEAVES,
            MonkeyMedicalStatus.READY);

    Monkey monkey5 = new Monkey();
    monkey5.setMonkey("Bonnie", "drill", 'f', 35, 23, 3, MonkeyFavFood.TREE_SAP,
            MonkeyMedicalStatus.READY);

    Monkey monkey6 = new Monkey();
    monkey6.setMonkey("Pickles", "drill", 'm', 40, 39, 5,
            MonkeyFavFood.LEAVES, MonkeyMedicalStatus.READY);

    Monkey monkey7 = new Monkey();
    monkey7.setMonkey("Giggles", "tamarin", 'm', 7, 5, 2,
            MonkeyFavFood.EGGS, MonkeyMedicalStatus.READY);

    Monkey monkey8 = new Monkey();
    monkey8.setMonkey("Willy", "tamarin", 'm', 15, 9, 4,
            MonkeyFavFood.FRUITS, MonkeyMedicalStatus.NEW);

    Monkey monkey9 = new Monkey();
    monkey9.setMonkey("Tury", "drill", 'f', 35, 13, 5,
            MonkeyFavFood.SEEDS, MonkeyMedicalStatus.READY);


    System.out.println("-----Add a monkey to Isolation-----");
    sanctuary.addToIsolation(monkey1);
    sanctuary.printIsolationsList();
    System.out.println();

    System.out.println("-----Add another monkey to Isolation-----");
    sanctuary.addToIsolation(monkey2);
    sanctuary.printIsolationsList();
    System.out.println();

    System.out.println("-----Add 3 more monkeys to isolation-----");
    sanctuary.addToIsolation(monkey3);
    sanctuary.addToIsolation(monkey4);
    sanctuary.addToIsolation(monkey5);
    sanctuary.printIsolationsList();
    System.out.println();

    System.out.println("-----Trying to add another monkey to isolation when all the isolation "
            + "rooms are occupied-----");
    sanctuary.addToIsolation(monkey6);
    System.out.println();

    System.out.println("-----Send a monkey that is presently in isolation to an available"
            + " enclosure-----");
    System.out.println("** When we move a monkey from isolation to enclosure, it must be removed "
            + "from the isolation room it occupies");
    System.out.println("After moving monkey1 from isolation to enclosure, its entry will be "
            + "removed from isolation list.");
    sanctuary.addToEnclosure(monkey1);

    System.out.println();
    System.out.println("Removed from isolation");
    sanctuary.printIsolationsList();

    System.out.println();
    System.out.println("Added to Enclosure");
    System.out.println("**Also as the monkey comes under medium category it will occupy 5 meter "
            + "square which is accordingly updated**");
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----Trying to move a monkey from isolation to enclosure when it is not"
            + " completed it medical treatment-----");
    sanctuary.addToIsolation(monkey8);
    sanctuary.addToEnclosure(monkey8);
    System.out.println("Monkey8 added to isolation but was not able to be moved to enclosure");
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();


    System.out.println();
    System.out.println("-----Trying to add a monkey to enclosure which has not be assigned to a"
            + " isolation yet-----");
    sanctuary.addToEnclosure(monkey7);

    System.out.println();
    System.out.println("-----Updating medical status of monkey to READY and then add it"
            + " to an available enclosure-----");
    System.out.println("**NOTE: When I am adding monkey8 from isolation to enclosure, "
            + "none of the enclosures have been assigned to 'tamarin' species, but as there are "
            + "enclosures which are completely empty it is repurposed to house tamarin species**");
    monkey8.setMedicalStatus(MonkeyMedicalStatus.READY);
    sanctuary.addToEnclosure(monkey8);
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();

    System.out.println("-----Moving some more monkeys from isolation to enclosure-----");
    System.out.println("**NOTE: According to the size of the monket i.e. SMALL, MEDIUM OR "
            + "LARGE the consumed area attribute will be accordingly updated**");
    sanctuary.addToEnclosure(monkey2);
    sanctuary.addToEnclosure(monkey3);
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----Moving a monkey from isolation to enclosure when the monkey we are"
            + " moving is of species which has not been assigned an enclosure and all enclosures"
            + " are housing some other species currently");
    sanctuary.addToEnclosure(monkey4);

    System.out.println();
    System.out.println("-----Adding some more monkeys to isolation-----");
    sanctuary.addToIsolation(monkey6);
    sanctuary.addToIsolation(monkey7);
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----Trying to move monkey5 which is of 'drill' species to enclosure."
            + " Though there is an enclosure assigned to it wont be added as its is a LARGE"
            + " monkey and will require 10 meter square and only 5 meter square is left");
    sanctuary.addToEnclosure(monkey5);

    System.out.println();
    System.out.println("SPECIES HOUSED IN THE SANCTUARY (ARRANGED IN ALPHABETICAL "
            + "ORDER BY SPECIES NAME): ");
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    System.out.printf("%10s %30s %20s", "SPECIES NAME", "ISOLATION IDs", "ENCLOSURE IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    ArrayList<SpeciesHousingLocation> speciesHoused = sanctuary.speciesHoused();
    for (int i = 0; i < speciesHoused.size(); i++) {
      System.out.format("%10s %30s %20s",
              speciesHoused.get(i).getName(), speciesHoused.get(i).getIsolationIds(),
              speciesHoused.get(i).getEnclosureIds());
      System.out.println();
    }
    System.out.println();

    System.out.println();
    System.out.println("SIGN OF AN ENCLOSURE BASED ON THE ENCLOSURE ID PROVIDED (ID = 1)");
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    System.out.printf("%10s %20s %30s", "MONKEY NAME", "SEX", "FAVOUROTE FOOD");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    ArrayList<EnclosureSign> enclosureSign = sanctuary.getEnclosureSign(1);
    for (int i = 0; i < enclosureSign.size(); i++) {
      System.out.format("%10s %20s %30s", enclosureSign.get(i).getName(),
              enclosureSign.get(i).getSex(),
              enclosureSign.get(i).getMonkeyFavFood());
      System.out.println();
    }

    System.out.println();
    System.out.println("SHOPPING LIST FOR THE SANCTUARY: ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s", "ITEM NAME", "QUANTITY (in grams)");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    ArrayList<ShoppingList> shoppingList = sanctuary.getShoppingList();
    for (int i = 0; i < shoppingList.size(); i++) {
      System.out.format("%10s %30s",
              shoppingList.get(i).getFoodItem(), shoppingList.get(i).getFoodItemQuantity());
      System.out.println();
    }
    System.out.println();

    System.out.println();
    System.out.println("LIST OF ALL MONKEYS IN ALPHABETICAL ORDER WITH THEIR LOCATIONS: ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s", "MONKEY NAME", "LOCATION");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    ArrayList<MonkeyLocationByName> monkeyList = sanctuary.getMonkeyList();
    for (int i = 0; i < monkeyList.size(); i++) {
      System.out.format("%10s %30s", monkeyList.get(i).getMonkeyName(),
              monkeyList.get(i).getLocationOfMonkey() + "\n");
    }

    System.out.println();
    System.out.println("LOOKUP SPECIES BY SPECIES NAME: (species: tamarin) ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s %30s", "MONKEY NAME", "ISOLATIONS ROOM IDs", "ENCLOSURE ROOM IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    SpeciesHousingLocation speciesLocation = sanctuary.lookupSpecies("tamarin");
    if (!speciesLocation.getName().equals("")) {
      System.out.format("%10s %30s %30s", speciesLocation.getName(),
              speciesLocation.getIsolationIds(),
              speciesLocation.getEnclosureIds());
      System.out.println();
    } else {
      System.out.println("No such speices is currenly housed in the sancutary.");
    }

    System.out.println();
    System.out.println("LOOKUP SPECIES BY SPECIES NAME: (species: ape) ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s %30s", "MONKEY NAME", "ISOLATIONS ROOM IDs", "ENCLOSURE ROOM IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    speciesLocation = sanctuary.lookupSpecies("ape");
    if (!speciesLocation.getName().equals("")) {
      System.out.format("%10s %30s %30s", speciesLocation.getName(),
              speciesLocation.getIsolationIds(),
              speciesLocation.getEnclosureIds());
    } else {
      System.out.println("No such species is currently housed in the sanctuary.");
    }

    System.out.println();
    System.out.println("-----RECORDS BEFORE MOVING A MONKEY FROM ENCLOSURE TO ISOLATION-----");
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();
    System.out.println();
    System.out.println("-----Move monkey Quinn from enclosure to isolation for treatment "
            + "purpose-----");
    sanctuary.moveFromEnclosureToIsolation(monkey3);
    System.out.println();
    System.out.println("-----RECORDS AFTER moving monkey Quinn from enclosure to isolation");
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("------TRYING TO MOVE A MONKEY FROM ENCLOSURE TO ISOLATION WHEN NONE OF "
            + "THE ISOLATION ROOMS HAVE A VACANCY-----");
    sanctuary.moveFromEnclosureToIsolation(monkey2);

    System.out.println();
    System.out.println("-----The sanctuary has now received considerable funding and has added 3 "
            + "isolations and 2 enclosures-----");
    sanctuary.extendSanctuary(3, 2);
    sanctuary.printIsolationsList();

    System.out.println();
    System.out.println("-----For the two new enclosures, assigning it an area and the species"
            + " it can house");
    sanctuary.getEnclosures().get(3).setSpecies("tamarin");
    sanctuary.getEnclosures().get(3).setArea(90);
    sanctuary.getEnclosures().get(4).setSpecies("drill");
    sanctuary.getEnclosures().get(4).setArea(75);
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----Adding a new monkey to isolation after the sanctuary has "
            + "been resized-----");
    sanctuary.addToIsolation(monkey9);
    sanctuary.printIsolationsList();

    System.out.println();
    System.out.println("-----Moving some of the monkeys from isolation to enclosures-----");
    System.out.println("-----Adding 3 monkeys of the same species from isolation to enclosures."
            + "When an enclosure housing a species will get full, a search will be made for the"
            + "available enclosure that can house the species.");
    sanctuary.addToEnclosure(monkey6);
    sanctuary.addToEnclosure(monkey3);
    sanctuary.addToEnclosure(monkey9);
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----Now adding moving monkey4 which belongs to species 'saki' from "
            + "isolation to enclosure-----");
    System.out.println("NOTE: None of the enclosures have been assigned to house a monkey "
            + "belonging to the 'saki' species. But as the enclosure which can house 'tamarin'"
            + " species is empty it is reconfigured to house 'saki' species.");
    sanctuary.addToEnclosure(monkey4);
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("-----THE ISOLATIONN AND ENCLOSURE LIST UPTIL NOW-----");
    sanctuary.printIsolationsList();
    sanctuary.printEnclosureList();

    System.out.println();
    System.out.println("SPECIES HOUSED IN THE SANCTUARY (ARRANGED IN ALPHABETICAL "
            + "ORDER BY SPECIES NAME): ");
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    System.out.printf("%10s %30s %20s", "SPECIES NAME", "ISOLATION IDs", "ENCLOSURE IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    speciesHoused = sanctuary.speciesHoused();
    for (int i = 0; i < speciesHoused.size(); i++) {
      System.out.format("%10s %30s %20s",
              speciesHoused.get(i).getName(), speciesHoused.get(i).getIsolationIds(),
              speciesHoused.get(i).getEnclosureIds());
      System.out.println();
    }
    System.out.println();

    System.out.println();
    System.out.println("LOOKUP SPECIES BY SPECIES NAME: (species: Tamarin) ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s %30s", "MONKEY NAME", "ISOLATIONS ROOM IDs", "ENCLOSURE ROOM IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    speciesLocation = sanctuary.lookupSpecies("Tamarin");
    if (!speciesLocation.getName().equals("")) {
      System.out.format("%10s %30s %30s", speciesLocation.getName(),
              speciesLocation.getIsolationIds(),
              speciesLocation.getEnclosureIds());
      System.out.println();
    } else {
      System.out.println("No such speices is currenly housed in the sancutary.");
    }

    System.out.println();
    System.out.println("LOOKUP SPECIES BY SPECIES NAME: (species: ape) ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s %30s", "MONKEY NAME", "ISOLATIONS ROOM IDs", "ENCLOSURE ROOM IDs");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    speciesLocation = sanctuary.lookupSpecies("ape");
    if (!speciesLocation.getName().equals("")) {
      System.out.format("%10s %30s %30s", speciesLocation.getName(),
              speciesLocation.getIsolationIds(),
              speciesLocation.getEnclosureIds());
    } else {
      System.out.println("No such species is currently housed in the sanctuary.");
    }

    System.out.println();
    System.out.println("SIGN OF AN ENCLOSURE BASED ON THE ENCLOSURE ID PROVIDED (ID = 1)");
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    System.out.printf("%10s %20s %30s", "MONKEY NAME", "SEX", "FAVOUROTE FOOD");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    enclosureSign = sanctuary.getEnclosureSign(1);
    for (int i = 0; i < enclosureSign.size(); i++) {
      System.out.format("%10s %20s %30s", enclosureSign.get(i).getName(),
              enclosureSign.get(i).getSex(),
              enclosureSign.get(i).getMonkeyFavFood());
      System.out.println();
    }

    System.out.println();
    System.out.println("LIST OF ALL MONKEYS IN ALPHABETICAL ORDER WITH THEIR LOCATIONS: ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s", "MONKEY NAME", "LOCATION");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    monkeyList = sanctuary.getMonkeyList();
    for (int i = 0; i < monkeyList.size(); i++) {
      System.out.format("%10s %30s", monkeyList.get(i).getMonkeyName(),
              monkeyList.get(i).getLocationOfMonkey() + "\n");
    }

    System.out.println();
    System.out.println("SHOPPING LIST FOR THE SANCTUARY: ");
    System.out.println("-------------------------------------------------------------------------"
            + "----");
    System.out.printf("%10s %30s", "ITEM NAME", "QUANTITY (in grams)");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    shoppingList = sanctuary.getShoppingList();
    for (int i = 0; i < shoppingList.size(); i++) {
      System.out.format("%10s %30s",
              shoppingList.get(i).getFoodItem(), shoppingList.get(i).getFoodItemQuantity());
      System.out.println();
    }
    System.out.println();
  }
}
