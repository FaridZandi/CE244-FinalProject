import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Ability extends GameObject {
    private boolean isCastable;


    private int level;


    private ArrayList<Price> AcquirePrices;
    private ArrayList<PrerequisiteAbility> prerequisiteAbilities;

    private boolean isBuffsReplacedEachLevel;
    private ArrayList<Buff> affectingBuffsAfterAcquiring;

    private String AbilityInfo;

    public Ability()
    {
        prerequisiteAbilities = new ArrayList<>();
        AcquirePrices = new ArrayList<>();
        affectingBuffsAfterAcquiring = new ArrayList<>();
    }


    public void acquire()
    {

    }

    public void describe()
    {

    }

    public void cast(Soldier target)
    {
        System.out.println("couldn't be cast");
    }

    public boolean isCastable() {
        return isCastable;
    }

    public void setCastable(boolean castable) {
        isCastable = castable;
    }

    public ArrayList<Price> getAcquirePrices() {
        return AcquirePrices;
    }


    public ArrayList<PrerequisiteAbility> getPrerequisiteAbilities() {
        return prerequisiteAbilities;
    }

    public String getAbilityInfo() {
        return AbilityInfo;
    }

    public void setAbilityInfo(String abilityInfo) {
        AbilityInfo = abilityInfo;
    }

    public ArrayList<Buff> getAffectingBuffsAfterAcquiring() {
        return affectingBuffsAfterAcquiring;
    }

    public void setBuffsReplacedEachLevel(boolean buffsReplacedEachLevel) {
        isBuffsReplacedEachLevel = buffsReplacedEachLevel;
    }

    public boolean isBuffsReplacedEachLevel() {
        return isBuffsReplacedEachLevel;
    }
}
