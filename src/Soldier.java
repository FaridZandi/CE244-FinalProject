import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Soldier extends GameObject{

    private int currentHealth;
    private int currentMagic;
    private int energyPoints;
    private SoldierType type;
    private ArrayList<Buff> buffs;
    private ArrayList<Item> inventory;
    private Story story;

    private ArrayList<Ability> abilities;
    public Soldier()
    {
        abilities = new ArrayList<>();
        buffs = new ArrayList<>();
        inventory = new ArrayList<>();
    }

    public ArrayList<Soldier> getArmy()
    {
         return story.getCurrentBattle().getTeam(this);
    }


    public void describe()
    {

    }

    public void getAttacked(int Damage)
    {
        int cH = this.currentHealth;
        cH -= Damage;
        if(cH > 0)
        {
            this.currentHealth = cH;
        }
        else
        {
            this.currentHealth = 0;
        }
    }

    public void revive()
    {

    }

    public void addBuff(Buff buff)
    {
        buffs.add(buff);
    }

    public void removeBuff(String buffName)
    {
        for (Buff buff : buffs) {
            if(buff.getName().equals(buffName))
            {
                buffs.remove(buff);
                break;
            }
        }
    }

    public void attack(Soldier target , int attackMultiplier)
    {
        int attackPlus = 0;
        int criticalMultiTotal = 1 + attackMultiplier;
        int splashPercentageTotal = 0;

        for (Buff buff:buffs)
        {
            attackPlus += buff.getAttackPowerIncrease();
            int randomNumber = (int)(Math.random()*100 + 1);
            if(randomNumber < buff.getCriticalDamageChance())
            {
                criticalMultiTotal += buff.getCriticalDamageMultiplier();
            }
            splashPercentageTotal+=buff.getDamageSplashPercentage();
        }
        int attackDamage = this.type.getAttackPower()+attackPlus;
        attackDamage*=criticalMultiTotal;

        int splashFreeAttackDamage = ((100 - splashPercentageTotal)/100) * attackDamage;
        int splashedDamage = attackDamage - splashFreeAttackDamage;
        target.getAttacked(splashFreeAttackDamage);


        if(splashPercentageTotal != 0) {
            ArrayList<Soldier> opponents = target.getArmy();
            for (Soldier opponent : opponents)
            {
                opponent.getAttacked(splashedDamage);
            }
        }
    }

    public void cast(String abilityName,String soldierName)
    {
        ArrayList<Soldier> enemies = this.story.getCurrentBattle().getTeam(this , false);
        ArrayList<Soldier> friendlies = this.story.getCurrentBattle().getTeam(this , true);

        Soldier target = null;

        //TODO : Handle the global enemy and global friendly and self situations

        for (Soldier enemy : enemies)
        {
            if(enemy.getName().equals(soldierName))
            {
                target = enemy;
            }
        }
        if (target == null)
        {
            for (Soldier friendly : friendlies)
            {
                if (friendly.getName().equals(soldierName))
                {
                    target = friendly;
                }
            }
        }

        for(Ability ability : abilities)
        {
            if(ability.getName().equals(abilityName))
            {
                if (ability.isCastable())
                {
                    ability.cast(target);
                }
            }
        }
    }




    public int calculateMaximumMagic()
    {
        int maximumMagic = 0;
        for(Buff buff : buffs)
        {
            maximumMagic += buff.getMaximumMagicIncrease();
        }
        maximumMagic += this.type.getMaximumMagic();
        return maximumMagic;
    }

    public int calculateMaximumHealth()
    {
        int maximumHealth = 0;
        for(Buff buff : buffs)
        {
            maximumHealth += buff.getMaximumHealthIncrease();
        }
        maximumHealth += this.type.getMaximumHealth();
        return maximumHealth;
    }

    public int calculateMaximumEnergyPoint()
    {
        int maximumEP = 0;
        for(Buff buff : buffs)
        {
            maximumEP += buff.getEnergyPointIncrease();
        }
        maximumEP += this.type.getEnergyPoints();
        return maximumEP;
    }

    public boolean haveSpaceInInventory()
    {
        int totalCapacity = this.type.getInventorySize();
        int currentInventorySpaceFilled = inventory.size();
        if(totalCapacity - currentInventorySpaceFilled >0)
        {
            return true;
        }
        return false;
    }


    public int getEnergyPoints() {
        return energyPoints;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }

    public void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}