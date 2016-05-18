package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Enemy;
import ModelPackage.Hero;
import ModelPackage.Soldier;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Seller implements CompoundMethod
{
    Control control;
    public Seller(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        int forIndex = input.indexOf("of");

        String buyerName = input.substring(4 , forIndex - 1);
        String itemName = input.substring((forIndex) + 3);

        Soldier seller = control.getModel().getStory().getCurrentBattle().findSoldier(buyerName);

        if(seller.getClass() == Enemy.class)
        {
            control.getView().show("cant buy an Item for an enemy");
            return;
        }

        if(seller == null)
        {
            control.getView().show("No Hero with that name was found, please try again.");
            return;
        }

        control.getModel().getStory().getShop().sell(itemName , (Hero) seller);
    }
}
