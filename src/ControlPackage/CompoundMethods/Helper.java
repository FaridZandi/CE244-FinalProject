package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ControlPackage.InputType;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Helper implements CompoundMethod
{
    Control control;
    public Helper(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        boolean isInBattle = true;
        boolean isInshop = true;
        for (InputType inputType : control.getInputHandler().getInputTypes()) {
            if(inputType.getValidSituations().contains(control.getInputHandler().getCurrentSituation()) || inputType.getValidSituations().contains("everywhere"))
            {
                control.getView().show(inputType.getInputString());
            }
        }
    }
}
