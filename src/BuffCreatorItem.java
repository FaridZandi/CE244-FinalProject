/**
 * Created by Y50 on 5/7/2016.
 */
public class BuffCreatorItem extends Item {
    @Override
    public void purchasedBy(Hero buyer) {
        super.purchasedBy(buyer);
        buyer.addBuff(getAffectingBuffAfterBuying());
    }
}
