package ModelPackage;

import ViewPackage.View;

import com.sun.org.apache.xpath.internal.operations.Mod;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Ability extends GameObject implements Serializable
{
    private boolean isCastable;
    private int level ;

    private final int maximumNumberOfUpgrades;
    private ArrayList<Price> AcquirePrices;
    private ArrayList<PrerequisiteAbility> prerequisiteAbilities;

    private boolean isBuffsReplacedEachLevel;
    private ArrayList<Buff> affectingBuffsAfterAcquiring;
    private String AbilityInfo;
    //public Ability(){}

    public Ability(String name,ArrayList<Price> acquirePrices, ArrayList<PrerequisiteAbility> prerequisiteAbilities, boolean isBuffsReplacedEachLevel, ArrayList<Buff> affectingBuffsAfterAcquiring, String abilityInfo) {
        setName(name);
        isCastable = false;
        this.level = 0;
        maximumNumberOfUpgrades = 3;
        AcquirePrices = acquirePrices;
        this.prerequisiteAbilities = prerequisiteAbilities;
        this.isBuffsReplacedEachLevel = isBuffsReplacedEachLevel;
        this.affectingBuffsAfterAcquiring = affectingBuffsAfterAcquiring;
        AbilityInfo = abilityInfo;
    }




    public void cast(Soldier target, Soldier soldier)
    {
        View.show("couldn't be cast");
    }

    public void acquire(Hero hero)
    {
        if( getLevel() >= maximumNumberOfUpgrades )
        {

            View.show("This ability cannot be upgraded anymore");
            return ;
        }
        if(hero.payPrice(this.AcquirePrices.get(this.level)))
        {
            if(this.prerequisiteAbilities != null) {
                if (prerequisiteAbilities.size() > this.level) {
                    if (this.getPrerequisiteAbilities().get(this.level) != null) {
                        PrerequisiteAbility temp = this.getPrerequisiteAbilities().get(this.level);

                        int prerequisiteLevel = hero.findAbility(temp.getAbilityName()).getLevel();
                        if (prerequisiteLevel < temp.getAbilityLevel()) {
                            View.show("You need level " + temp.getAbilityLevel() + " of "+ temp.getAbilityName() + " to acquire this level!");
                            return;
                        }
                    }
                }
            }
            hero.payPrice(this.AcquirePrices.get(this.level),true);
            if(this.level == 0)
                View.show(this.getName() +" was acquired for " + hero.getName() +" successfully," +
                        "your current experience is: " + hero.getXP());
            else
                View.show(this.getName() +" was upgraded to level " + (this.level + 1) + " for " + hero.getName() +" successfully," +
                        "your current experience is: " + hero.getXP());

            if(this.affectingBuffsAfterAcquiring != null)
            {
                if (this.affectingBuffsAfterAcquiring.size()> this.level) {
                    if (this.affectingBuffsAfterAcquiring.get(this.level) != null) {
                        if(this.isBuffsReplacedEachLevel)
                            if(this.level>0)
                                hero.removeBuff(this.affectingBuffsAfterAcquiring.get(this.level - 1).getName());
                        hero.addBuff((Buff)Model.deepClone(this.affectingBuffsAfterAcquiring.get(this.level)));
                    }
                }
            }
            this.level += 1;
        }
        else
        {
            View.show("Your experience is insufficient");
        }

    }
    public boolean isCastable() {
        return isCastable;
    }

    public ArrayList<PrerequisiteAbility> getPrerequisiteAbilities() {
        return prerequisiteAbilities;
    }

    public ArrayList<Price> getAcquirePrices() {
        return AcquirePrices;
    }

    public String getAbilityInfo() {
        return AbilityInfo;
    }

    public ArrayList<Price> getCastPrices(){return null;}

    public int getLevel(){ return level;}



    public void setAcquirePrices(Price price){ this.AcquirePrices.add(price);}

    public void setPrerequisiteAbilities(PrerequisiteAbility prerequisiteAbilities){ this.prerequisiteAbilities.add(prerequisiteAbilities);}

    public void setCastable(boolean castable) {
        isCastable = castable;
    }

    public void setLevel(int level){ this.level = level;}

    public void setAbilityInfo(String abilityInfo) {
        AbilityInfo = abilityInfo;
    }

    public void addToBuffs(Buff buff){this.affectingBuffsAfterAcquiring.add(buff);}

    public void describe()
    {
        System.out.println("asssoorakh");
    }


}
