package ModelPackage;

import com.sun.javafx.sg.prism.NGShape;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class CastableItem extends Item {

    private int charges;

    private int chargesLeft;

    private CastableData castableData;

    private int turnsToUseAgain;

    public void cast(Soldier target)
    {
        System.out.println("hello there");
    }

    public CastableItem(String name, String category, Price purchasePrice, int priceIncreaseRate, Buff affectingBuffAfterBuying, int charges) {
        super(name, category, purchasePrice, priceIncreaseRate, affectingBuffAfterBuying);
        this.setCastable(true);
        this.charges = charges;
    }

    @Override
    public void purchasedBy(Hero buyer) {
        if(!isEverythingOkToBuy(buyer))
        {
            return;
        }
        CastableItem temp = Model.deepClone(this , CastableItem.class);
        buyer.addItem(temp);
        buyer.addBuff(Model.deepClone(getAffectingBuffAfterBuying() , Buff.class));
    }

    public int getTurnsToUseAgain() {
        return turnsToUseAgain;
    }

    public void setTurnsToUseAgain(int turnsToUseAgain) {
        this.turnsToUseAgain = turnsToUseAgain;
    }

    public void setCastableData(CastableData castableData) {
        this.castableData = castableData;
    }
}
