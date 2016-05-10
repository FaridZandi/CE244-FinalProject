/**
 * Created by Y50 on 5/7/2016.
 */
public class BuffCreatorItem extends Item {
    @Override
    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }
        BuffCreatorItem temp = (BuffCreatorItem)main.deepClone(this);
        buyer.addItem(temp);
        buyer.addBuff(getAffectingBuffAfterBuying());
    }
}
