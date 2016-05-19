package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Soldier;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Caster implements CompoundMethod
{

    Control control;
    public Caster(Control control)
    {
        this.control = control;
    }

    @Override
    public void performMethod(String input) {
        int castIndex = input.indexOf("cast");
        int onIndex = input.indexOf("on");

        String abilityName;
        String targetName;
        String casterName = input.substring(0 , castIndex - 1);

        Soldier caster = control.getModel().getStory().getCurrentBattle().findSoldier(casterName);
        if(caster == null)
        {
            control.getView().show("caster not found,please try again.");
            return;
        }

        if(onIndex == -1) {
            abilityName = input.substring(castIndex+5);
            caster.cast(abilityName);
        }
        else
        {
            abilityName = input.substring(castIndex + 5 , onIndex - 1);
            targetName = input.substring(onIndex + 3);
            caster.cast(abilityName , targetName);
        }

    }
}
