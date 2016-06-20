package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class CastableAbility extends Ability {

    private ArrayList<Price> castPrices;
    private ArrayList<CastableData> castableData;
    private String successMessage;
    private Boolean isCastableOnEnemies;
    private Boolean isCastableOnFriendlies;

    private int turnsToUseAgain;

    @Override
    public void describe() {
        System.out.println("asshole");
    }

    public CastableAbility(String name, ArrayList<Price> acquirePrices, ArrayList<PrerequisiteAbility> prerequisiteAbilities, boolean isBuffsReplacedEachLevel, ArrayList<Buff> affectingBuffsAfterAcquiring, String abilityInfo, ArrayList<Price> castPrices, ArrayList<CastableData> castableData, String successMessage, Boolean isCastableOnEnemies, Boolean isCastableOnFriendlies) {
        super(name, acquirePrices, prerequisiteAbilities, isBuffsReplacedEachLevel, affectingBuffsAfterAcquiring, abilityInfo);
        this.castPrices = castPrices;
        this.castableData = castableData;
        this.successMessage = successMessage;
        this.isCastableOnEnemies = isCastableOnEnemies;
        this.isCastableOnFriendlies = isCastableOnFriendlies;
        this.turnsToUseAgain = 0;
        setCastable(true);
    }

    public void cast(Soldier target, Soldier caster)
    {
        CastableData castableData = this.getCastableData().get(this.getLevel() - 1);

        if(castableData.getAttackMultiplier() > 0)
        {
            caster.attack(target , castableData.getAttackMultiplier());
        }
        if(castableData.getDamage() > 0)
        {
            target.getAttacked(castableData.getDamage());
        }



        if(castableData.getHeal() > 0)
        {
            target.getHealed(castableData.getHeal());
        }
        if(castableData.getEnergyPoint() != 0)
        {
            target.setEnergyPoints(target.getEnergyPoints() + castableData.getEnergyPoint());
        }
        if(castableData.getMagic() > 0)
        {
            target.getMagicPoint(castableData.getMagic());
        }



        if(castableData.getAffectingBuff() != null)
        {
            target.addBuff(Model.deepClone(castableData.getAffectingBuff() , Buff.class));
        }

        this.setTurnsToUseAgain(castableData.getCoolDown());
    }


    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public ArrayList<Price> getCastPrices() {
        return castPrices;
    }

    public ArrayList<CastableData> getCastableData() {
        return castableData;
    }

    public boolean isCastableOnEnemies() {
        return isCastableOnEnemies;
    }

    public boolean isCastableOnFriendlies() {
        return isCastableOnFriendlies;
    }

    public int getTurnsToUseAgain() {
        return turnsToUseAgain;
    }

    public void setTurnsToUseAgain(int turnsToUseAgain) {
        this.turnsToUseAgain = turnsToUseAgain;
    }
}
