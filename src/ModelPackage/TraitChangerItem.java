package ModelPackage;

/**
 * Created by Y50 on 5/7/2016.
 */
public class TraitChangerItem extends Item{

    public TraitChangerItem(String name, String category, Price purchasePrice, int priceIncreaseRate, Buff affectingBuffAfterBuying) {
        super(name, category, purchasePrice, priceIncreaseRate, affectingBuffAfterBuying);
        setTakingSpaceInInventory(false);
    }

    @Override
    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }
        TraitChangerItem temp = (TraitChangerItem)Model.deepClone(this);
        buyer.addBuff((Buff)Model.deepClone(getAffectingBuffAfterBuying()));
    }
}
