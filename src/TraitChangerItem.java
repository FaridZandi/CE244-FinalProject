/**
 * Created by Y50 on 5/7/2016.
 */
public class TraitChangerItem extends Item{
    @Override
    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }
        TraitChangerItem temp = (TraitChangerItem) main.deepClone(this);
        buyer.addBuff(getAffectingBuffAfterBuying());
    }
}
