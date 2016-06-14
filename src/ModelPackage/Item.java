package ModelPackage;

import java.io.Serializable;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Item extends GameObject{
    private boolean isCastable;
    private boolean isTakingSpaceInInventory;
    private String category;
    private Price purchasePrice;
    private int priceIncreaseRate;
    private int howManyPurchased;
    private Buff affectingBuffAfterBuying;

    public Item(String name, String category, Price purchasePrice, int priceIncreaseRate, Buff affectingBuffAfterBuying) {
        this.setName(name);
        this.isCastable = false;
        this.isTakingSpaceInInventory = true;
        this.category = category;
        this.purchasePrice = purchasePrice;
        this.priceIncreaseRate = priceIncreaseRate;
        this.affectingBuffAfterBuying = affectingBuffAfterBuying;
        howManyPurchased = 0;
    }

    public void describe()
    {

    }

    public void purchasedBy(Hero buyer) {

    }

    public boolean isEverythingOkToBuy(Hero buyer)
    {
        if(isTakingSpaceInInventory && !buyer.haveSpaceInInventory())
            return false;
        int goldRequiredTotal = purchasePrice.getGoldPrice() + priceIncreaseRate * howManyPurchased;
        Price totalPurchasePrice = new Price(goldRequiredTotal , purchasePrice.getXPPrice() , purchasePrice.getEPPrice() , purchasePrice.getMagicPrice() , purchasePrice.getHealthPrice());
        if(!buyer.payPrice(totalPurchasePrice))
        {
            return false;
        }
        return true;
    }

    public boolean isCastable() {
        return isCastable;
    }

    public Buff getAffectingBuffAfterBuying() {
        return affectingBuffAfterBuying;
    }

    public String getCategory() {
        return category;
    }

    public Price getPurchasePrice() {
        return purchasePrice;
    }

    public int getPriceIncreaseRate() {
        return priceIncreaseRate;
    }

    public int getHowManyPurchased() {
        return howManyPurchased;
    }

    public void setHowManyPurchased(int howManyPurchased) {
        this.howManyPurchased = howManyPurchased;
    }

    public void setPurchasePrice(Price purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setCastable(boolean isCastable) {
        this.isCastable = isCastable;
    }

    public boolean isTakingSpaceInInventory() {
        return isTakingSpaceInInventory;
    }

    public void setTakingSpaceInInventory(boolean takingSpaceInInventory) {
        isTakingSpaceInInventory = takingSpaceInInventory;
    }
}

