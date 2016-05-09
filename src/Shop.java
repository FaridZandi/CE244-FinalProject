import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Shop {
    ArrayList<Item> buyableItems;

    private String category;

    public Shop()
    {
        buyableItems = new ArrayList<>();
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
