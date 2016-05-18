package ModelPackage;

import ViewPackage.View;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Ability extends GameObject {
    private boolean isCastable;
    private int level ;

    private final int maximumNumberOfUpgrades;
    private ArrayList<Price> AcquirePrices;
    private ArrayList<PrerequisiteAbility> prerequisiteAbilities;

    private boolean isBuffsReplacedEachLevel;
    private ArrayList<Buff> affectingBuffsAfterAcquiring;
    private String AbilityInfo;
    //public Ability(){}

    public Ability()
    {
        this.level = 0;
        maximumNumberOfUpgrades = 3;
        AcquirePrices = new ArrayList<>();
        affectingBuffsAfterAcquiring = new ArrayList<>();
        prerequisiteAbilities = new ArrayList<>();
    }


    public void cast(Soldier target)
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
            for (Ability ability : hero.getAbilities())
            {
                if (ability.getName().equals(this.prerequisiteAbilities.get(this.level).getAbilityName()))
                {
                    if (ability.getLevel() < this.prerequisiteAbilities.get(this.level).getAbilityLevel())
                    {
                        View.show("Required abilities aren't acquired");
                        return ;
                    }
                    hero.payPrice(this.AcquirePrices.get(this.level),true);
                    if(this.level == 0)
                        View.show("%d acquired successfully,your current experience is: ",hero.getXP());
                    else
                        View.show("%d upgraded successfully,your current experience is: ",hero.getXP());
                    this.level += 1;
                }
            }
            return;
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

    public ArrayList<Price> getCastPrice(){return null;}

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


    public void addToCastPrices(Price price){}

    public void addToBuffs(Buff buff){this.affectingBuffsAfterAcquiring.add(buff);}

    public void describe()
    {

    }
}
