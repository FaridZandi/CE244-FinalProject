package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Ability;
import ModelPackage.Hero;
import ModelPackage.Soldier;

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
        String acquirerName = input.substring(8 , forIndex - 1);
        String abilityName = input.substring( forIndex + 4);
        System.out.println(abilityName);

        Hero acquirer = control.getModel().getGameObjectsHolder().getPlayer().findHero(acquirerName);

        if(acquirer == null)
        {
            control.getView().show("no hero with that name was not found, try again.");
            return;
        }


        // TODO : make this right .
        //acquirer.acquire(abilityName);


    }
}
