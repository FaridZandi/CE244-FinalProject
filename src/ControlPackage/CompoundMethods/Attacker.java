package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Soldier;
import ViewPackage.View;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Attacker implements CompoundMethod
{
    Control control;
    public Attacker(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        int attackIndex = input.indexOf("attack");
        String attackerName = input.substring(0 , attackIndex - 1);
        String targetName = input.substring(attackIndex + 7);

        Soldier attacker = control.getModel().getStory().getCurrentBattle().findSoldier(attackerName);
        Soldier target = control.getModel().getStory().getCurrentBattle().findSoldier(targetName);

        if(attacker == null && target == null)
        {
            View.show("No soldier with this name was found, please try again.");
            return;
        }


        if(control.getModel().getStory().getCurrentBattle().getTeam(attacker , true).contains(target))
        {
            View.show("Cannot attack a unit from own team");
        }

        attacker.attack(target , 0);
    }
}
