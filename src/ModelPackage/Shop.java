package ModelPackage;

import ViewPackage.View;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Shop extends GameMapCell {
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
                View.show("item " + buyableItem.getName() + " was purchased for "+  buyer.getName() + ". your current gold is : " + buyer.getPlayer().getGold());
                return;
            }
        }
    }

    public void sell(String itemName, Hero seller)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().toLowerCase().equals(itemName.toLowerCase()))
            {
                seller.sell(itemName);
            }
        }
    }

    @Override
    public void draw(int cornerX, int cornerY, Graphics2D g2d) {
        super.drawTile(cornerX, cornerY, g2d, GameMapCell.shopMapCellImage, true);
    }
}
