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



    public SoldierType(String name ,ArrayList<Ability> abilities, ArrayList<Buff> defaultBuffs, int attackPower, int maximumHealth, int maximumMagic, int inventorySize, int energyPoints, int healthRefillRatePercentage, int magicRefillRatePercentage) {
        setName(name);
        this.abilities = abilities;
        this.defaultBuffs = defaultBuffs;
        this.attackPower = attackPower;
        this.maximumHealth = maximumHealth;
        this.maximumMagic = maximumMagic;
        this.inventorySize = inventorySize;
        this.energyPoints = energyPoints;
        this.healthRefillRatePercentage = healthRefillRatePercentage;
        this.magicRefillRatePercentage = magicRefillRatePercentage;
    }

    public Ability findAbility(String word) {
        for (Ability ability : abilities) {
            if(ability.getName() == word)
            {
                return ability;
            }
        }
        return null;
    }

    public void describe()
    {
        System.out.println("this is a soldier type with name " + this.getName());
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

