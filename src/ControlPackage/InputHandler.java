/**
 * Created by Y50 on 5/11/2016.
 */

package ControlPackage;

import java.util.ArrayList;

import ControlPackage.CompoundMethods.*;

public class InputHandler
{

    private ArrayList<InputType> inputTypes;
    private ArrayList<String> keyWords;
    private Control control;


    public InputHandler(Control control)
    {

        this.control = control;
        inputTypes = new ArrayList<>();
        inputTypes.add(new InputType("(Class name) + “?”", "shop battle abilityAcquireStage other", "(Class description)", new Describer(control) ));

        inputTypes.add(new InputType("(Hero name) + “?”" , "shop battle abilityAcquireStage other" , "(Hero description)", new Describer(control) ));

        inputTypes.add(new InputType("(enemy name) + “?”" , "battle" , "(enemy description)", new Describer(control) ));

        inputTypes.add(new InputType("(ability name)" , "shop battle abilityAcquireStage other" , "(ability description)", new Describer(control) ));

        inputTypes.add(new InputType("(item name) + “?”" , "shop abilityAcquireStage battle other" , "(Item description)", new Describer(control)));

        inputTypes.add(new InputType("(hero name) + “ “ +(ability name) + “?”" , "shop battle other  abilityAcquireStage" , "(ability description) + “\\n“ + (this upgrade\\n" +
                "description) + “\\n” + “You need “ + (required xp)", new Describer(control)));

        inputTypes.add(new InputType("“Acquire “ + (ability name) + “ for “ + (hero name)" , " abilityAcquireStage" , "(ability name) (“acquired”/”upgraded”) + “ successfully, your current experience is: ” +\n" +
                "(current xp)\n" +
                "Or:\n" +
                "“Your experience is insufficient”\n" +
                "Or:\n" +
                "“This ability cannot be upgraded anymore”\n" +
                "Or:\n" +
                "“Required abilities aren’t acquired”", new Acquirer(control)));




        inputTypes.add(new InputType("“Buy “ + (item name) + “ for “ + (hero name)" , "shop" , "(item name) “ bought successfully, your current wealth is: ” + (current money)\n" +
                "Or:\n" +
                "“You don’t have enough money”\n" +
                "Or:\n" +
                "(hero name) +“’s inventory is full”", new Buyer(control)));

        inputTypes.add(new InputType("“Sell “ + (item name) + “ of” + (hero name)" , "shop" , "(item name) + “ successfully sold, your current wealth is: “ + (current money)", new Seller(control)));


        inputTypes.add(new InputType("(hero name) + “ cast “ + (ability name) + “ on “ + (hero name / enemy name and id)" , "battle" , "(ability success message)\n" +
                "Or:\n" +
                "“You don’t have enough magic points”\n" +
                "Or:\n" +
                "“You don’t have enough energy points”\n" +
                "Or:\n" +
                "“Your desired ability is still in cooldown”\n" +
                "Or:\n" +
                "“You have not yet acquired this ability”", new Caster(control)));

        inputTypes.add(new InputType("(hero name) + “ cast “ + (ability name)" , "battle" , "(ability success message)\n" +
                "Or:\n" +
                "“You don’t have enough magic points”\n" +
                "Or:\n" +
                "“You don’t have enough energy points”\n" +
                "Or:\n" +
                "“Your desired ability needs a target”\n" +
                "Or:\n" +
                "“Your desired ability is still in cooldown”\n" +
                "Or:\n" +
                "“You have not yet acquired this ability”", new Caster(control)));

        inputTypes.add(new InputType("(hero name) + “ use “ + (item name) + “ on “ + (hero name / enemy name and id)" , "battle" , "(item success message)\n" +
                "Or:\n" +
                "“You don’t have enough magic points”\n" +
                "Or:\n" +
                "“You don’t have enough energy points”\n" +
                "Or:\n" +
                "“Your desired item is still in cooldown”\n" +
                "Or:\n" +
                "“You don’t have this item”", new User(control)));

        inputTypes.add(new InputType("(hero name) + “ use “ + (item name)" , "battle" , "(item success message)\n" +
                "Or:\n" +
                "“You don’t have enough magic points”\n" +
                "Or:\n" +
                "“You don’t have enough energy points”\n" +
                "Or:\n" +
                "“Your desired Item needs to be used on a target”\n" +
                "Or:\n" +
                "“Your desired item is still in cooldown”\n" +
                "Or:\n" +
                "“You don’t have this item”", new User(control)));

        inputTypes.add(new InputType("(hero name) + “ attack “ + (enemy name and id)" , "battle" , "(hero name) + “ has successfully attacked “ + (enemy name and id) + “ with “ + (attack\n" +
                "power) + “ power”\n" +
                "Or:\n" +
                "“You don’t have enough energy points”", new Attacker(control)));


        inputTypes.add(new InputType("help" , "shop battle other abilityAcquireStage" , "Shows the list of valid commands.", new Helper(control)));

        inputTypes.add(new InputType("again" , "shop battle other abilityAcquireStage" , "Shows current stage data one more time." , new Againer(control)));

        inputTypes.add(new InputType("done" , "shop battle other abilityAcquireStage" , "Proceeds to the next stage." , new Doner(control)));

        keyWords = new ArrayList<>();
        keyWords.add("help");
        keyWords.add("acquire");
        keyWords.add("for");
        keyWords.add("buy");
        keyWords.add("sell");
        keyWords.add("of");
        keyWords.add("attack");
        keyWords.add("cast");
        keyWords.add("use");
        keyWords.add("on");


    }


    public void handleInput(String input)
    {
        input = input.replaceAll("[ ]{2,}" , " ");
        input = input.trim();
        input = input.toLowerCase();

        String generalInput = createGeneralInput(input);
        InputType inputType = findMatchingInputType(generalInput);

        if (inputType == null) {
            control.getView().show("No matching command was found, please try again.");
            return;
        }
        String currentSituation = getCurrentSituation();
        if(!inputType.getValidSituations().contains(currentSituation))
        {
            control.getView().show("Command not valid here. Use \"help\" to see valid Commands");
        }
        inputType.getCompoundMethod().performMethod(input);
        control.getModel().getStory().checkGameOver();
    }

    public String getCurrentSituation()
    {
//        boolean isInBattle = control.getModel().getStory().isCurrentlyInBattle();
//        boolean isInShop =  control.getModel().getStory().getCurrentBattle().isInShop();
//        boolean isInAbilityAcquiringStage =  control.getModel().getStory().getCurrentBattle()
        boolean isInBattle = true;
        boolean isInShop = true;
        boolean isInAbilityAcquiringStage = false;
        if(isInBattle && isInAbilityAcquiringStage)
        {
            return "abilityAcquireStage";
        }
        if(isInBattle && isInShop)
        {
            return "shop";
        }
        if(isInBattle)
        {
            return "battle";
        }
        return "other";
    }

    private String createGeneralInput(String input)
    {
        String generalInput = new String();
        String[] words = input.split(" ");
        boolean flag = true;
        for (int i = 0; i < words.length; i++) {
            if(keyWords.contains(words[i]))
            {
                generalInput += words[i];
                flag = true;
            }
            else
            {
                if(flag)
                {
                    generalInput += "input";
                    flag = false;
                }

            }
            generalInput += " ";
        }
        generalInput = generalInput.replaceAll("[ ]{2,}" , " ");
        generalInput = generalInput.trim();
        return generalInput;
    }

    private InputType findMatchingInputType(String input)
    {
        for (InputType inputType : inputTypes) {
            if(inputType.getGeneralInput().equals(input))

            {
                System.out.println(inputType.getOutputString());

                return inputType;
            }
        }
        return null;
    }

    public ArrayList<InputType> getInputTypes() {
        return inputTypes;
    }
}
