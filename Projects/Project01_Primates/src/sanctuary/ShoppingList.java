package sanctuary;

/**
 * ShoppingList class represented as food item name and the
 * food item quantity required to be shopped.
 */
public class ShoppingList {
  private MonkeyFavFood foodItem;
  private int foodItemQuantity;

  /**
   * Constructs an ShoppingList in terms of food item and the quantity of food item
   * to be shopped.
   *
   * @param foodItem enum MonkeyFavFood
   * @param foodItemQuantity integer type
   */
  public ShoppingList(MonkeyFavFood foodItem, int foodItemQuantity) {
    this.foodItem = foodItem;
    this.foodItemQuantity = foodItemQuantity;
  }

  public MonkeyFavFood getFoodItem() {
    return this.foodItem;
  }

  public int getFoodItemQuantity() {
    return this.foodItemQuantity;
  }

  public void setFoodItemQuantity(int foodItemQuantity) {
    this.foodItemQuantity = foodItemQuantity;
  }
}
