package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Shop {
    ArrayList<Item> buyableItems;
    GameObjectsHolder gameObjectsHolder;
    private String category;

    public Shop(GameObjectsHolder gameObjectsHolder, String category)
    {
        this.gameObjectsHolder = gameObjectsHolder;
        buyableItems = gameObjectsHolder.getItems(category);
    }

    public void purchase(String itemName, Hero buyer)
    {
        for (Item buyableItem : buyableItems) {
            if(buyableItem.getName().equals(itemName))
            {
                buyableItem.purchasedBy(buyer);
            }
        }
    }
}
