package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;

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

    }
}
