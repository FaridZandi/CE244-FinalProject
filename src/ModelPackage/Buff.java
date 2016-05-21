package ModelPackage;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Buff {
    private String name;
    private boolean isPermanent;
    private int duration;
    private int attackPowerIncrease;
    private int maximumHealthIncrease;
    private int maximumMagicIncrease;
    private int energyPointIncrease;
    private int criticalDamageChance;
    private int criticalDamageMultiplier;
    private int damageSplashPercentage;

    private int howMuchLeftToEnd;

    public Buff(String name ,int attackPowerIncrease , int maximumHealthIncrease , int maximumMagicIncrease , int energyPointIncrease , int criticalDamageChance , int criticalDamageMultiplier , int damageSplashPercentage)
    {
        this.name = name;
        this.attackPowerIncrease = attackPowerIncrease;
        this.maximumHealthIncrease = maximumHealthIncrease;
        this.maximumMagicIncrease = maximumMagicIncrease;
        this.energyPointIncrease = energyPointIncrease;
        this.criticalDamageChance = criticalDamageChance;
        this.criticalDamageMultiplier = criticalDamageMultiplier;
        this.damageSplashPercentage = damageSplashPercentage;
    }

    public int getAttackPowerIncrease() {
        return attackPowerIncrease;
    }

    public int getMaximumHealthIncrease() {
        return maximumHealthIncrease;
    }

    public int getMaximumMagicIncrease() {
        return maximumMagicIncrease;
    }

    public int getEnergyPointIncrease() {
        return energyPointIncrease;
    }

    public int getCriticalDamageChance() {
        return criticalDamageChance;
    }

    public int getCriticalDamageMultiplier() {
        return criticalDamageMultiplier;
    }

    public int getDamageSplashPercentage() {
        return damageSplashPercentage;
    }

    public String getName() {
        return name;
    }

    public boolean getPermanent() {
        return isPermanent;
    }
}
