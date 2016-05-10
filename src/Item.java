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

    }

    public boolean isEverythingOkToBuy(Hero buyer)
    {
        if(takeSpaceInInventory && !buyer.haveSpaceInInventory())
            return false;
        int goldRequiredTotal = purchasePrice.getGoldPrice() + priceIncreaseRate * howManyPurchased;
        Price totalPurchasePrice = new Price(goldRequiredTotal , purchasePrice.getXPPrice() , purchasePrice.getEPPrice() , purchasePrice.getMagicPrice() , purchasePrice.getHealthPrice());
        if(!buyer.payPrice(totalPurchasePrice))
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

