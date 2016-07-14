package ModelPackage;

import ViewPackage.View;
import java.io.Serializable;

/**
 * Created by Y50 on 5/1/2016.
 */
public abstract class Item extends GameObject{
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

    public void purchasedBy(Hero buyer)
    {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }

        int goldRequiredTotal = this.getPurchasePrice().getGoldPrice() + this.getPriceIncreaseRate() * this.getHowManyPurchased();
        Price totalPurchasePrice = new Price(goldRequiredTotal , this.getPurchasePrice().getXPPrice() , this.getPurchasePrice().getEPPrice() , this.getPurchasePrice().getMagicPrice() , this.getPurchasePrice().getHealthPrice());
        buyer.payPrice(totalPurchasePrice , true);

        if(isTakingSpaceInInventory)
        {
            Item temp = (Item)Model.deepClone(this);
            buyer.getInventory().add(temp);
        }
        if(getAffectingBuffAfterBuying() != null)
        {
            buyer.addBuff((Buff)Model.deepClone(getAffectingBuffAfterBuying()));
        }

        howManyPurchased += 1;
    }

    public boolean isEverythingOkToBuy(Hero buyer)
    {
        if(isTakingSpaceInInventory && !buyer.haveSpaceInInventory()) {
            View.show("inventory full!");
            return false;
        }
        int goldRequiredTotal = purchasePrice.getGoldPrice() + priceIncreaseRate * howManyPurchased;
        Price totalPurchasePrice = new Price(goldRequiredTotal , purchasePrice.getXPPrice() , purchasePrice.getEPPrice() , purchasePrice.getMagicPrice() , purchasePrice.getHealthPrice());
        if(!buyer.payPrice(totalPurchasePrice))
        {
            View.show("Not Enough Money!");
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

