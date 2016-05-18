package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class SoldierType extends GameObject{

    private ArrayList<Ability> abilities;
    private ArrayList<Buff> defaultBuffs;
    private int attackPower;
    private int maximumHealth;
    private int maximumMagic;
    private int inventorySize;
    private int energyPoints;
    private int healthRefillRatePercentage;
    private int magicRefillRatePercentage;


    public SoldierType()
    {
        abilities = new ArrayList<>();
        defaultBuffs = new ArrayList<>();
    }

    public void describe()
    {

    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public ArrayList<Buff> getDefaultBuffs() {
        return defaultBuffs;
    }

    public int getEnergyPoints()
    {
        return energyPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public int getMaximumMagic() {
        return maximumMagic;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public int getHealthRefillRatePercentage() {
        return healthRefillRatePercentage;
    }

    public int getMagicRefillRatePercentage() {
        return magicRefillRatePercentage;
    }
}

