package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Enemy;
import ModelPackage.Hero;
import ModelPackage.Soldier;
import ViewPackage.View;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Buyer implements CompoundMethod
{
    Control control;
    public Buyer(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        int forIndex = input.indexOf("for");

        String itemName = input.substring(4 , forIndex - 1);
        String buyerName = input.substring((forIndex) + 4);

        Soldier buyer = control.getModel().getStory().getCurrentBattle().findSoldier(buyerName);

        if(buyer == null)
        {
            View.show("No Hero with that name was found, please try again.");
            return;
        }

        if(buyer.getClass() == Enemy.class)
        {
            View.show("cant buy an Item for an enemy");
            return;
        }
        if(buyer.getInventory().size() == buyer.getType().getInventorySize())
        {
            View.show("Your inventory is full. You can sell your items to free some space.");
            return;
        }


        control.getModel().getStory().getShop().purchase(itemName , (Hero) buyer);
    }
}
