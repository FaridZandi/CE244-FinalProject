package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;

/**
 * Created by Y50 on 5/18/2016.
 */
public class Doner implements CompoundMethod
{
    private Control control;
    public Doner(Control control) {
        this.control = control;
    }

    @Override
    public void performMethod(String input) {
        control.getModel().getStory().proceedToNextStage();
    }
}
