package test;

import org.junit.Before;
import org.junit.Test;

import sanctuary.MonkeyFavFood;
import sanctuary.ShoppingList;

import static org.junit.Assert.assertEquals;

/**
 * ShoppingList represented as food item name and the food item quantity.
 */
public class ShoppingListTest {
  ShoppingList shoppingItem;

  @Before
  public void setUp() throws Exception {
    shoppingItem = shop(MonkeyFavFood.FRUITS, 500);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * ShoppingList object.
   *
   * @param foodItem enum MonkeyFavFood
   * @param foodItemQuantity integer type
   *
   * @return a new instance of a ShoppingList object
   */
  protected ShoppingList shop(MonkeyFavFood foodItem, Integer foodItemQuantity) {
    return new ShoppingList(foodItem, foodItemQuantity);
  }

  @Test
  public void testGetFoodItem() {
    assertEquals(MonkeyFavFood.FRUITS, shoppingItem.getFoodItem());
  }

  @Test
  public void testGetFoodItemQuantity() {
    assertEquals(500, shoppingItem.getFoodItemQuantity());
    shoppingItem.setFoodItemQuantity(shoppingItem.getFoodItemQuantity() + 500);
    assertEquals(1000, shoppingItem.getFoodItemQuantity());
  }
}