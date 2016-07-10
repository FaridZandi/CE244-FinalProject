package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Soldier;
import ViewPackage.View;

/**
 * Created by Y50 on 5/12/2016.
 */
public class User implements CompoundMethod
{
    Control control;
    public User(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        int useIndex = input.indexOf("use");
        int onIndex = input.indexOf("on");

        String itemName;
        String targetName;
        String userName = input.substring(0 , useIndex - 1);

        Soldier user = control.getModel().getStory().getCurrentBattle().findSoldier(userName);
        if(user == null)
        {
            View.show("user not found, try again.");
            return;
        }

        if(onIndex == -1) {
//            itemName = input.substring(useIndex+4);
//            user.cast(itemName);
        }
        else
        {
            itemName = input.substring(useIndex + 5 , onIndex - 1);
            targetName = input.substring(onIndex + 3);
            user.cast(itemName , targetName);
        }

        if(!control.getModel().getStory().getCurrentBattle().isAnyEnemyAlive())
        {
            control.getModel().getStory().getCurrentBattle().setBattleFinished(true);
            control.getModel().getStory().proceedToNextStage();
        }
    }
}
