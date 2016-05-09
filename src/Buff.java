/**
 * Created by Y50 on 5/1/2016.
 */
public class Buff {
    public int attackPowerIncrease;
    public int maximumHealthIncrease;
    public int maximumMagicIncrease;
    public int energyPointIncrease;
    public int criticalDamageChance;
    public int criticalDamageMultiplier;
    public int damageSplashPercentage;

    public Buff(int attackPowerIncrease , int maximumHealthIncrease , int maximumMagicIncrease , int energyPointIncrease , int criticalDamageChance , int criticalDamageMultiplier , int damageSplashPercentage)
    {
        this.attackPowerIncrease = attackPowerIncrease;
        this.maximumHealthIncrease = maximumHealthIncrease;
        this.maximumMagicIncrease = maximumMagicIncrease;
        this.energyPointIncrease = energyPointIncrease;
        this.criticalDamageChance = criticalDamageChance;
        this.criticalDamageMultiplier = criticalDamageMultiplier;
        this.damageSplashPercentage = damageSplashPercentage;
    }
}
