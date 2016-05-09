import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class CastableItem extends Item {

    private ArrayList<CastableData> castableData;

    private int turnsToUseAgain;

    public void cast(Soldier target)
    {
        System.out.println("hello there");
    }

    @Override
    public void purchasedBy(Hero buyer) {
        super.purchasedBy(buyer);
        buyer.addBuff(getAffectingBuffAfterBuying());
    }
}
