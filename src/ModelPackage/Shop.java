package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Shop {
    ArrayList<Item> buyableItems;
    private String category;

    public Shop(GameObjectsHolder gameObjectsHolder, String category)
    {
        buyableItems = gameObjectsHolder.getItems(category);
    }

    public void purchase(String itemName, Hero buyer)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().equals(itemName))
            {
                buyableItem.purchasedBy(buyer);
            }
        }
    }

    public void sell(String itemName, Hero seller)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().equals(itemName))
            {
                seller.sell(itemName);
            }
        }
    }
}
