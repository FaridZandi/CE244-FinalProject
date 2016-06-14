package ModelPackage;

/**
 * Created by Y50 on 5/4/2016.
 */
public class CastableData{
    private int damage;
    private int heal;
    private int magic;
    private int energyPoint;
    private int coolDown;
    private double attackMultiplier;
    private boolean isGlobalFriendly;
    private boolean isGlobalEnemy;
    private boolean isAutoCast;
    private Buff affectingBuff;

    private int turnsToUseAgain;

    public CastableData(int damage, int heal, int magic, int energyPoint, int coolDown, double attackMultiplier, boolean isGlobalFriendly, boolean isGlobalEnemy, boolean autoCast , Buff affectingBuff) {
        this.damage = damage;
        this.heal = heal;
        this.magic = magic;
        this.energyPoint = energyPoint;
        this.coolDown = coolDown;
        this.attackMultiplier = attackMultiplier;
        this.isGlobalFriendly = isGlobalFriendly;
        this.isGlobalEnemy = isGlobalEnemy;
        this.isAutoCast = autoCast;
        this.affectingBuff = affectingBuff;
    }


    public int getDamage() {
        return damage;
    }

    public int getHeal() {
        return heal;
    }

    public int getMagic() {
        return magic;
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public boolean isGlobalFriendly() {
        return isGlobalFriendly;
    }

    public boolean isGlobalEnemy() {
        return isGlobalEnemy;
    }

    public boolean isAutoCast() {
        return isAutoCast;
    }

    public int getTurnsToUseAgain() {
        return turnsToUseAgain;
    }

    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    public Buff getAffectingBuff() {
        return affectingBuff;
    }
}
