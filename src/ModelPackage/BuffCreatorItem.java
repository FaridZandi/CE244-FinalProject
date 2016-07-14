package ModelPackage;

import ViewPackage.View;

/**
 * Created by Y50 on 5/7/2016.
 */
public class BuffCreatorItem extends Item {
    public BuffCreatorItem(String name, String category, Price purchasePrice, int priceIncreaseRate, Buff affectingBuffAfterBuying) {
        super(name, category, purchasePrice, priceIncreaseRate, affectingBuffAfterBuying);
    }
}
