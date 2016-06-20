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

        String properInput = input.substring(0 , input.length() - 1).trim();
        GameObject searchResult;

        searchResult = control.getModel().getStory().getGameObjectsHolder().find(properInput);

        if(searchResult != null)
        {
            searchResult.describe();
            return;
        }

        String[] words = properInput.split(" ");
        if(words.length > 1)
        {
            String soldierPart = "";
            for (int i = 0; i < words.length - 1; i++) {
                soldierPart = soldierPart + " " + words[i];
                searchResult = control.getModel().getStory().getGameObjectsHolder().find(soldierPart);
                if(searchResult != null)
                {
                    if(searchResult instanceof Soldier || searchResult instanceof SoldierType)
                    {
                        String abilityPart = "";
                        for(int j = i+1 ; j < words.length ; j++)
                        {
                            abilityPart = abilityPart + " " +  words[j];
                        }
                        GameObject abilitySearchResult = control.getModel().getStory().getGameObjectsHolder().find(abilityPart);
                        if(abilitySearchResult != null)
                        {
                            abilitySearchResult.describe();
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("search had no results! please try again!");
    }
}
