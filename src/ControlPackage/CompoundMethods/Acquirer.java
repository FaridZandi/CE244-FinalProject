package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Ability;
import ModelPackage.GameObject;
import ModelPackage.Hero;
import ModelPackage.Soldier;
import ViewPackage.View;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Acquirer implements CompoundMethod
{
    Control control;
    public Acquirer(Control control)
    {
        this.control = control;
    }

    @Override
    public void performMethod(String input) {
        int forIndex = input.indexOf("for");
        String abilityName = input.substring(8 , forIndex - 1);
        String acquirerName = input.substring( forIndex + 4);

        GameObject result = control.getModel().getStory().getGameObjectsHolder().find(acquirerName);

        if(result== null || result.getClass() != Hero.class)
        {
            View.show("no hero with that name was not found, try again.");
            return;
        }
        Hero acquirer = (Hero)result;
        Ability acquiringAbility  = acquirer.findAbility(abilityName);
        if(acquiringAbility == null)
        {
            View.show("This hero doesn't have this ability, try again");
            return;
        }
        acquiringAbility.acquire(acquirer);
    }
}
