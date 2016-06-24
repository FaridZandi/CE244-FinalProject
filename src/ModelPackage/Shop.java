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
        buyableItems = gameObjectsHolder.getItemsWithCategory(category);
    }

    public void purchase(String itemName, Hero buyer)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().toLowerCase().equals(itemName.toLowerCase()))
            {
                buyableItem.purchasedBy(buyer);
                return;
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
