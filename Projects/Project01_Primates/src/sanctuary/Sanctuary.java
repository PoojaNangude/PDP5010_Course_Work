package sanctuary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Sanctuary class represented as ArrayList of Isolation and Enclosure class.
 */
public class Sanctuary implements SanctuaryInterface {
  private int numberOfIsolationRooms;
  private int numberOfEnclosures;

  private ArrayList<Isolation> isolations;
  private ArrayList<Enclosure> enclosures;

  public Sanctuary(int n, int m) {
    this.numberOfIsolationRooms = n;
    this.numberOfEnclosures = m;
  }

  @Override
  public void createSanctuary() {
    isolations = new ArrayList<>();
    for (int i = 0; i < numberOfIsolationRooms; i++) {
      isolations.add(new Isolation());
    }

    enclosures = new ArrayList<>();
    for (int i = 0; i < numberOfEnclosures; i++) {
      enclosures.add(new Enclosure());
    }
  }

  @Override
  public void extendSanctuary(int numberOfIsolationRoomsAdded, int numberOfEnclosuresAdded) {
    for (int i = 0; i < numberOfIsolationRoomsAdded; i++) {
      isolations.add(new Isolation());
      this.numberOfIsolationRooms = this.numberOfIsolationRooms + 1;
    }
    for (int j = 0; j < numberOfEnclosuresAdded; j++) {
      enclosures.add(new Enclosure());
      this.numberOfEnclosures = this.numberOfEnclosures + 1;
    }
  }

  public ArrayList<Isolation> getIsolations() {
    return this.isolations;
  }

  public ArrayList<Enclosure> getEnclosures() {
    return this.enclosures;
  }

  public int getIsolationsCount() {
    return this.isolations.size();
  }

  public int getEnclosuresCount() {
    return this.enclosures.size();
  }

  @Override
  public ArrayList<SpeciesHousingLocation> speciesHoused() {
    ArrayList<SpeciesHousingLocation> speciesHoused = new ArrayList<>();
    ArrayList<Integer> isolationIds = new ArrayList<>();
    ArrayList<Integer> enclosureIds = new ArrayList<>();
    int k = 0;
    while (getIsolations().get(k).getMonkey().getName().equals("")) {
      k++;
    }
    isolationIds.add(getIsolations().get(k).getIsolationId());
    speciesHoused.add(new SpeciesHousingLocation(getIsolations().get(k).getMonkey().getSpecies(),
            isolationIds, enclosureIds));

    for (int i = k + 1; i < numberOfIsolationRooms; i++) {
      String speciesIsolation = getIsolations().get(i).getMonkey().getSpecies();
      if (!speciesIsolation.equals("")) {
        Iterator itr = speciesHoused.iterator();
        boolean speciesAlreadyPresent = false;
        while (itr.hasNext()) {
          SpeciesHousingLocation housed = (SpeciesHousingLocation) itr.next();
          if (speciesIsolation.equalsIgnoreCase(housed.getName())) {
            housed.getIsolationIds().add(getIsolations().get(i).getIsolationId());
            speciesAlreadyPresent = true;
            break;
          }
        }
        if (!speciesAlreadyPresent) {
          isolationIds = new ArrayList<>();
          isolationIds.add(getIsolations().get(i).getIsolationId());
          speciesHoused.add(new SpeciesHousingLocation(
                  getIsolations().get(i).getMonkey().getSpecies(), isolationIds, enclosureIds));
        }
      }
    }

    for (int i = 0; i < numberOfEnclosures; i++) {
      String speciesEnclosure = getEnclosures().get(i).getSpecies();
      ArrayList<Monkey> enclosureList = getEnclosures().get(i).getEnclosureList();
      if (speciesEnclosure.equals("") && enclosureList.size() != 0) {
        Iterator itr = speciesHoused.iterator();
        boolean speciesAlreadyPresent = false;

        for (int j = 0; j < speciesHoused.size(); j++) {
          if  (speciesHoused.get(j).getName().equalsIgnoreCase(
                  getEnclosures().get(i).getSpecies())) {
            speciesAlreadyPresent = true;
            break;
          }
        }

        if (!speciesAlreadyPresent) {
          enclosureIds = new ArrayList<>();
          enclosureIds.add(getEnclosures().get(i).getEnclosureId());
          isolationIds = new ArrayList<>();
          speciesHoused.add(new SpeciesHousingLocation(getEnclosures().get(i).getSpecies(),
                  isolationIds, enclosureIds));
        }
      }
    }

    ArrayList<SpeciesInEnclosure> speciesInEnclosures = new ArrayList<>();
    ArrayList<Enclosure> enclosureList = getEnclosures();
    String speciesName = enclosureList.get(0).getSpecies();
    int enclId = enclosureList.get(0).getEnclosureId();
    ArrayList<Integer> enIds = new ArrayList<>();
    enIds.add(enclId);
    speciesInEnclosures.add(new SpeciesInEnclosure(speciesName, enIds));

    for (int loop = 1; loop < enclosureList.size(); loop++) {
      speciesName = enclosureList.get(loop).getSpecies();
      enclId = enclosureList.get(loop).getEnclosureId();

      boolean presentInSpeciesInEnclosure = false;
      for (int r = 0; r < speciesInEnclosures.size(); r++) {
        if (speciesInEnclosures.get(r).getSpeciesName().equalsIgnoreCase(speciesName)) {
          enIds = speciesInEnclosures.get(r).getEnclosureIds();
          enIds.add(enclId);
          speciesInEnclosures.get(r).setEnclosureIds(enIds);
          presentInSpeciesInEnclosure = true;
          break;
        }
      }
      if (!presentInSpeciesInEnclosure) {
        enIds = new ArrayList<>();
        enIds.add(enclId);
        speciesInEnclosures.add(new SpeciesInEnclosure(speciesName, enIds));
      }
    }

    for (int i = 0; i < speciesHoused.size(); i++) {
      for (int j = 0; j < speciesInEnclosures.size(); j++) {
        if (speciesHoused.get(i).getName() == speciesInEnclosures.get(j).getSpeciesName()) {
          speciesHoused.get(i).setEnclosureIds(speciesInEnclosures.get(j).getEnclosureIds());
          break;
        }
      }
    }

    Collections.sort(speciesHoused, SpeciesHousingLocation.SPECIES_NAME_COMPARATOR);
    return speciesHoused;
  }

  @Override
  public ArrayList<EnclosureSign> getEnclosureSign(int enclosureId) {
    ArrayList<Monkey> enclosure = new ArrayList<>();
    ArrayList<EnclosureSign> enclosureSign = new ArrayList<>();
    for (int i = 0; i < numberOfEnclosures; i++) {
      if (getEnclosures().get(i).getEnclosureId() == enclosureId) {
        enclosure = getEnclosures().get(i).getEnclosureList();
        break;
      }
    }

    if (enclosure.size() > 0) {
      for (int i = 0; i < enclosure.size(); i++) {
        EnclosureSign record = new EnclosureSign(enclosure.get(i).getName(),
                enclosure.get(i).getSex(), enclosure.get(i).getMonkeyFavFood());
        enclosureSign.add(record);
      }
    }
    return enclosureSign;
  }

  @Override
  public ArrayList<ShoppingList> getShoppingList() {
    ArrayList<ShoppingList> shoppingList = new ArrayList<>();
    for (MonkeyFavFood foodItem : MonkeyFavFood.values()) {
      shoppingList.add(new ShoppingList(foodItem, 0));
    }

    for (int i = 0; i < numberOfIsolationRooms; i++) {
      if (!getIsolations().get(i).getMonkey().getName().equals("")) {
        int foodQuantity = getIsolations().get(i).getMonkey().getFoodQuantityRequired();
        for (int j = 0; j < shoppingList.size(); j++) {
          if (shoppingList.get(j).getFoodItem()
                  == getIsolations().get(i).getMonkey().getMonkeyFavFood()) {
            int presentFoodQuantity = shoppingList.get(j).getFoodItemQuantity();
            shoppingList.get(j).setFoodItemQuantity(presentFoodQuantity + foodQuantity);
            break;
          }
        }
      }
    }

    for (int i = 0; i < numberOfEnclosures; i++) {
      if (!getEnclosures().get(i).getSpecies().equals("")
              && getEnclosures().get(i).getEnclosureList().size() != 0) {
        ArrayList<Monkey> enclosureList = getEnclosures().get(i).getEnclosureList();
        for (int l = 0; l < enclosureList.size(); l++) {
          int foodQuantity = enclosureList.get(l).getFoodQuantityRequired();
          for (int j = 0; j < shoppingList.size(); j++) {
            if (shoppingList.get(j).getFoodItem() == enclosureList.get(l).getMonkeyFavFood()) {
              int presentFoodQuantity = shoppingList.get(j).getFoodItemQuantity();
              shoppingList.get(j).setFoodItemQuantity(presentFoodQuantity + foodQuantity);
              break;
            }
          }
        }
      }
    }
    return shoppingList;
  }

  @Override
  public ArrayList<MonkeyLocationByName> getMonkeyList() {
    ArrayList<MonkeyLocationByName> monkeyList = new ArrayList<>();

    for (int i = 0; i < numberOfIsolationRooms; i++) {
      if (!getIsolations().get(i).getMonkey().getName().equals("")) {
        String name = getIsolations().get(i).getMonkey().getName();
        String location = "ISOLATION ID " + getIsolations().get(i).getIsolationId();
        monkeyList.add(new MonkeyLocationByName(name, location));
      }
    }

    for (int i = 0; i < numberOfEnclosures; i++) {
      if (!getEnclosures().get(i).getSpecies().equals("")
              && getEnclosures().get(i).getEnclosureList().size() != 0) {
        for (int j = 0; j < getEnclosures().get(i).getEnclosureList().size(); j++) {
          String name = getEnclosures().get(i).getEnclosureList().get(j).getName();
          String location = "ENCLOSURE ID " + getEnclosures().get(i).getEnclosureId();
          monkeyList.add(new MonkeyLocationByName(name, location));
        }
      }
    }
    Collections.sort(monkeyList, MonkeyLocationByName.MONKEY_NAME_COMPARATOR);
    return monkeyList;
  }

  @Override
  public SpeciesHousingLocation lookupSpecies(String speciesName) {
    ArrayList<SpeciesHousingLocation> speciesHoused = speciesHoused();
    SpeciesHousingLocation speciesLocation = new SpeciesHousingLocation(
            "", new ArrayList<Integer>(), new ArrayList<Integer>());
    for (int i = 0; i < speciesHoused.size(); i++) {
      if ((speciesHoused.get(i).getName()).equalsIgnoreCase(speciesName)) {
        speciesLocation = new SpeciesHousingLocation(
                speciesHoused().get(i).getName(), speciesHoused.get(i).getIsolationIds(),
                speciesHoused.get(i).getEnclosureIds());
      }
    }
    return speciesLocation;
  }

  /**
   * Prints the list of all isolations and the monkeys which are added to the isolation rooms.
   */
  public void printIsolationsList() {
    System.out.println();
    System.out.println("ISOLATION ROOMS INFORMATION");
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    System.out.printf("%10s %50s", "ISOLATION ROOM ID", "MONKEY HOUSED");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----");
    for (int i = 0; i < getIsolationsCount(); i++) {
      if (!getIsolations().get(i).getMonkey().getName().equals("")) {
        System.out.format("%10s %60s",
                getIsolations().get(i).getIsolationId(),
                "uniqueId: " + getIsolations().get(i).getMonkey().getUniqueId()
                        + " ,name: " + getIsolations().get(i).getMonkey().getName()
                        + " ,species: " + getIsolations().get(i).getMonkey().getSpecies()
        );
      } else {
        System.out.format("%10s %40s",
                getIsolations().get(i).getIsolationId(),
                "EMPTY"
        );
      }
      System.out.println();
    }
  }

  /**
   * Prints the list of all enclosure and the monkeys which are added to the enclosures.
   */
  public void printEnclosureList() {
    System.out.println();
    System.out.println("ENCLOSURE ROOMS INFORMATION");
    System.out.println("------------------------------------------------------------------------"
            + "-----------------------------------");
    System.out.printf("%10s %10s %20s %20s %20s",
            "ENCLOSURE ID", "SPECIES", "TOTAL CAPACITY", "CONSUMED CAPACITY", "MONKEYS HOUSED");
    System.out.println();
    System.out.println("------------------------------------------------------------------------"
            + "-----------------------------------");
    for (int i = 0; i < getEnclosuresCount(); i++) {
      System.out.format("%10s %10s %20s %20s", getEnclosures().get(i).getEnclosureId(),
              getEnclosures().get(i).getSpecies(), getEnclosures().get(i).getArea(),
              getEnclosures().get(i).getConsumedArea());
      ArrayList<Monkey> encl = getEnclosures().get(i).getEnclosureList();
      for (int j = 0; j < encl.size(); j++) {
        System.out.format("%10s", encl.get(j).getName() + " ");
      }
      System.out.println();
    }
  }

  /**
   * Add a monkey to an available isolation.
   * Prints an appropriate message if none of the isolation rooms are available.
   */
  public boolean addToIsolation(Monkey monkey) {
    boolean addedToIsolation = false;
    for (int i = 0; i < numberOfIsolationRooms; i++) {
      if (getIsolations().get(i).getMonkey().getName().equals("")) {
        getIsolations().get(i).setMonkey(monkey);
        addedToIsolation = true;
        break;
      }
    }
    if (!addedToIsolation) {
      System.out.println("Could not add monkey to isolation. "
              + "All the isolations are occupied");
    }
    return addedToIsolation;
  }

  /**
   * Deletes a monkey from isolation so that the room is made free for other new additions.
   * Monkey has to be deleted from isolation when it is added to an enclosure in the sanctuary.
   */
  public boolean removeFromIsolation(Monkey monkey) {
    boolean removedFromIsolation = false;
    for (int i = 0; i < numberOfIsolationRooms; i++) {
      if (getIsolations().get(i).getMonkey().equals(monkey)) {
        getIsolations().get(i).setMonkey(new Monkey());
        removedFromIsolation = true;
      }
    }
    if (!removedFromIsolation) {
      System.out.println("Monkey could not be deleted as no such monkey exists in Isolation");
    }
    return removedFromIsolation;
  }

  /**
   * Adds a monkey to an available enclosure of the same species type.
   * If enclosures are not available an appropriate message is printed.
   */
  public boolean addToEnclosure(Monkey monkey) {
    boolean monkeyAddedToIsolation = false;
    boolean addedToEnclosure = false;

    for (int i = 0; i < numberOfIsolationRooms; i++) {
      Monkey monkeyInIsolation = getIsolations().get(i).getMonkey();
      if (monkeyInIsolation.equals(monkey)) {
        monkeyAddedToIsolation = true;
        break;
      }
    }
    if (monkeyAddedToIsolation && monkey.getMedicalStatus() == MonkeyMedicalStatus.READY) {
      MonkeySize monkeySize = monkey.getMonkeySize();
      int areaRequiredByMonkey = monkey.areaRequiredByMonkey(monkeySize);
      for (int i = 0; i < numberOfEnclosures; i++) {
        int totalEnclosureArea = getEnclosures().get(i).getArea();
        int totalConsumedArea = getEnclosures().get(i).getConsumedArea();
        int availableEnclosureArea = totalEnclosureArea - totalConsumedArea;
        int animalCount = getEnclosures().get(i).getAnimalCount();
        ArrayList<Monkey> enclosureList = getEnclosures().get(i).getEnclosureList();
        if ((monkey.getSpecies()).equalsIgnoreCase(getEnclosures().get(i).getSpecies())) {
          if (areaRequiredByMonkey <= availableEnclosureArea
                  && monkey.getMedicalStatus() == MonkeyMedicalStatus.READY
          ) {
            getEnclosures().get(i).setConsumedArea(totalConsumedArea + areaRequiredByMonkey);
            getEnclosures().get(i).setAnimalCount(animalCount + 1);
            enclosureList.add(monkey);
            getEnclosures().get(i).setEnclosureList(enclosureList);
            removeFromIsolation(monkey);
            addedToEnclosure = true;
            break;
          }
        }
      }
      if (!addedToEnclosure) {
        for (int i = 0; i < numberOfEnclosures; i++) {
          if (getEnclosures().get(i).getConsumedArea() == 0) {
            getEnclosures().get(i).setSpecies(monkey.getSpecies());
            int totalEnclosureArea = getEnclosures().get(i).getArea();
            int totalConsumedArea = getEnclosures().get(i).getConsumedArea();
            int availableEnclosureArea = totalEnclosureArea - totalConsumedArea;
            int animalCount = getEnclosures().get(i).getAnimalCount();
            ArrayList<Monkey> enclosureList = getEnclosures().get(i).getEnclosureList();
            if (areaRequiredByMonkey <= availableEnclosureArea
                    && monkey.getMedicalStatus() == MonkeyMedicalStatus.READY
            ) {
              getEnclosures().get(i).setConsumedArea(totalConsumedArea + areaRequiredByMonkey);
              getEnclosures().get(i).setAnimalCount(animalCount + 1);
              enclosureList.add(monkey);
              getEnclosures().get(i).setEnclosureList(enclosureList);
              addedToEnclosure = true;
              removeFromIsolation(monkey);
              break;
            }
            break;
          }
        }
        if (!addedToEnclosure) {
          System.out.println("Monkey could not be added to enclosure as none of the "
                  + "enclosures have any vacancy. Send it to another sanctuary.");
        }

      }
    } else {
      if (!monkeyAddedToIsolation) {
        System.out.println("Monkey cannot be added to enclosure as it is not present "
                + "in any of the isolation rooms.");
      } else if (monkey.getMedicalStatus() != MonkeyMedicalStatus.READY) {
        System.out.println("The monkey cannot be added to an enclosure as it is "
                + "still undergoing medical treatment.");
      }
    }
    return addedToEnclosure;
  }

  /**
   * Moves a monkey from enclosure to an isolation.
   */
  public boolean moveFromEnclosureToIsolation(Monkey monkey) {
    boolean movedFromEnclosureToIsolation = false;
    ArrayList<Enclosure> enclosureList = getEnclosures();
    for (int i = 0; i < enclosureList.size(); i++) {
      ArrayList<Monkey> enclosure = enclosureList.get(i).getEnclosureList();
      for (int j = 0; j  < enclosure.size(); j++) {
        if (monkey.equals(enclosure.get(j))) {
          boolean addedToIsolation = addToIsolation(monkey);
          if (addedToIsolation) {
            monkey.setMedicalStatus(MonkeyMedicalStatus.TREATMENT);
            enclosure.remove(monkey);
            int presentConsumedArea = getEnclosures().get(i).getConsumedArea();
            MonkeySize monkeySize = monkey.getMonkeySize();
            int areaOccupiedByMonkey = monkey.areaRequiredByMonkey(monkeySize);
            getEnclosures().get(i).setConsumedArea(presentConsumedArea - areaOccupiedByMonkey);
            getEnclosures().get(i).setEnclosureList(enclosure);
            int animalCount = getEnclosures().get(i).getAnimalCount();
            getEnclosures().get(i).setAnimalCount(animalCount - 1);
            movedFromEnclosureToIsolation = true;
          }
          break;
        }
      }
    }
    return  movedFromEnclosureToIsolation;
  }
}
