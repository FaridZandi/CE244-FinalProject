package ModelPackage;

/**
 * Created by Y50 on 5/7/2016.
 */
public class BuffCreatorItem extends Item {
    public BuffCreatorItem(String name, String category, Price purchasePrice, int priceIncreaseRate, Buff affectingBuffAfterBuying) {
        super(name, category, purchasePrice, priceIncreaseRate, affectingBuffAfterBuying);
    }

    @Override
    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }

        BuffCreatorItem temp = (BuffCreatorItem)Model.deepClone(this);
        buyer.addItem(temp);
        buyer.addBuff((Buff)Model.deepClone(getAffectingBuffAfterBuying()));
    }
}
