package ControlPackage.CompoundMethods;


import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.*;

/**
 * Created by Y50 on 5/12/2016.
 */

public class Describer implements CompoundMethod
{
    Control control;
    public Describer(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input)
    {

        String properInput = input.substring(0 , input.length() - 1);
        System.out.println(properInput);
        properInput = properInput.trim();
        String[] words = properInput.split(" ");
        GameObject searchResult = control.getModel().getStory().getGameObjectsHolder().find(words[0]);
        if(words.length == 1)
        {
            if (searchResult == null) {
                control.getView().show("no such thing was found, please try again.");
                return;
            }
            searchResult.describe();
        }
        else
        {
            if(searchResult.getClass() == Hero.class || searchResult.getClass() == Enemy.class)
            {
                Soldier searchResultSoldier = (Soldier)searchResult;
                Ability searchResultAbility = searchResultSoldier.findAbility(words[1]);
                if(searchResultAbility != null)
                {
                    searchResultAbility.describe();
                }
            }
        }
    }
}
