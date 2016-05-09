import java.io.Serializable;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Item extends GameObject implements Serializable{
    private boolean isCastable;
    private boolean takeSpaceInInventory;

    private String category;
    private Price purchasePrice;
    private int priceIncreaseRate;
    private int howManyPurchased;
    private Buff affectingBuffAfterBuying;

    public Item()
    {
        howManyPurchased = 0;
    }
    public void describe()
    {

    }

    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }
        CastableItem temp = (CastableItem)main.deepClone(this);
        buyer.addItem(temp);

    }

    public boolean isEverythingOkToBuy(Hero buyer)
    {
        if(takeSpaceInInventory && !buyer.haveSpaceInInventory())
            return false;
        //TODO : calculate the purchase price according to the base price and times it have been bought;
        if(!buyer.payPrice(purchasePrice))
        {
            return false;
        }
        return true;
    }

    public boolean isTakeSpaceInInventory() {
        return takeSpaceInInventory;
    }

    public boolean isCastable() {
        return isCastable;
    }

    public Buff getAffectingBuffAfterBuying() {
        return affectingBuffAfterBuying;
    }

    public void setHowManyPurchased(int howManyPurchased) {
        this.howManyPurchased = howManyPurchased;
    }
}

