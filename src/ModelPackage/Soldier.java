package ModelPackage;

import ViewPackage.View;

import java.util.ArrayList;

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
    public Soldier(SoldierType soldierType , Story story)
    {
        type = soldierType;
        abilities = new ArrayList<>();
        for (Ability ability : soldierType.getAbilities()) {
            this.getAbilities().add((Ability)Model.deepClone(ability));
        }
        buffs = new ArrayList<>();
        for (Buff buff : soldierType.getDefaultBuffs()) {
            this.addBuff((Buff)Model.deepClone(buff));
        }
        inventory = new ArrayList<>();
        this.story = story;
    }

    public ArrayList<Soldier> getArmy()
    {
         return story.getCurrentBattle().getTeam(this , true);
    }

    public ArrayList<Soldier> getOpponentArmy()
    {
        return story.getCurrentBattle().getTeam(this , false);
    }

    public void describe()
    {

    }

    public void addItem(Item item) {
        getInventory().add(item);
    }

    public void removeItem(String itemName)
    {
        for (Item item : inventory) {
            if(item.getName().equals(itemName))
            {
                inventory.remove(item);
                break;
            }
        }
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

    public void getHealed(int heal)
    {
        int cH = this.currentHealth;
        int mH = this.calculateMaximumHealth();
        cH += heal;
        if(cH < mH)
        {
            this.currentHealth = cH;
        }
        else
        {
            this.currentHealth = mH;
        }
    }

    public void getMagicPoint(int magicPoints) {
        int currentMagic = this.currentMagic;
        int maximumMagic = this.calculateMaximumMagic();
        currentMagic += magicPoints;
        if(currentMagic < maximumMagic)
        {
            this.currentMagic = currentMagic;
        }
        else
        {
            this.currentMagic = maximumMagic;
        }
    }


    public void getEnergyPoints(int EP)
    {
        this.energyPoints += EP;
    }

    public void revive()
    {
        //TODO : this must be called once again at the beginning of the battle for every soldier in the battle.
        currentHealth = calculateMaximumHealth();
        currentMagic = calculateMaximumMagic();
        energyPoints = calculateMaximumEnergyPoint();
    }

    public void timeBasedPutIntoEffect()
    {
        //health increase by passage of time
        int maximum , increase;

        maximum = calculateMaximumHealth();
        increase = maximum *(type.getHealthRefillRatePercentage());
        this.getHealed(increase);


        //magic increase by passage of time
        maximum = calculateMaximumMagic();
        increase = maximum * (type.getMagicRefillRatePercentage());
        this.getMagicPoint(increase);

        // energy points filled to maximum each time
        energyPoints = calculateMaximumEnergyPoint();

        //auto cast abilities must be cast here

        //cooldown of items and abilities must reduce here




    }

    public void addBuff(Buff buff)
    {
        int increase;
        int maximum;
        increase = buff.getMaximumHealthIncrease();
        if(increase != 0)
        {
            maximum = calculateMaximumHealth();
            double ratio = ((double)(maximum + increase)) / (maximum);
            currentHealth = (int) (ratio * currentHealth);
        }
        increase = buff.getMaximumMagicIncrease();
        if(increase != 0)
        {
            maximum = calculateMaximumMagic();
            double ratio = ((double)(maximum + increase)) / (maximum);
            currentMagic = (int) (ratio * currentMagic);
        }
        buffs.add(buff);
    }

    public void removeBuff(String buffName)
    {
        for (Buff buff : buffs) {
            if(buff.getName().equals(buffName))
            {
                int increase;
                int maximum;
                increase = buff.getMaximumHealthIncrease();
                if(increase != 0)
                {
                    maximum = calculateMaximumHealth();
                    double ratio = ((double)(maximum - increase)) / (maximum);
                    currentHealth = (int) (ratio * currentHealth);
                }
                increase = buff.getMaximumMagicIncrease();
                if(increase != 0)
                {
                    maximum = calculateMaximumMagic();
                    double ratio = ((double)(maximum - increase)) / (maximum);
                    currentMagic = (int) (ratio * currentMagic);
                }
                buffs.remove(buff);
                break;
            }
        }
    }

    public void attack(Soldier target , double attackMultiplier)
    {
        int attackPlus = 0;
        double criticalMultiTotal = 1 + attackMultiplier;
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




    private CastableAbility getCastableAbility(String abilityName) {
        CastableAbility castableAbility = null;
        Ability abilitySearchResult = findAbility(abilityName);
        if(abilitySearchResult == null)
        {
            View.show("Ability Not Found!");
            return null;
        }
        if(!abilitySearchResult.isCastable())
        {
            View.show("this ability is not castable,please try again");
            return null;
        }
        if(castableAbility.getLevel() == 0)
        {
            View.show("You haven't acquired this ability yet, please try again");
            return null;
        }
        if(castableAbility.getTurnsToUseAgain() > 0)
        {
            View.show("Your desired ability is still in cooldown");
            return null;
        }
        castableAbility = (CastableAbility)abilitySearchResult;
        return castableAbility;
    }

    public void cast(String abilityName)
    {
        CastableAbility castableAbility = getCastableAbility(abilityName);
        if (castableAbility == null) return;

        CastableData castableData = castableAbility.getCastableData().get(castableAbility.getLevel() - 1);
        if(! castableData.isGlobalEnemy() && !castableData.isGlobalFriendly())
        {
            View.show("You have a specify a target for this ability, please try again");
            return;
        }


        ArrayList<Soldier> target = new ArrayList<>();
        if(castableData.isGlobalEnemy())
        {
            //TODO : not sure if this works! it is supposed to add all of the members of caster's army to the target arraylist!
            target.addAll(this.getOpponentArmy());
        }
        if(castableData.isGlobalFriendly())
        {
            target.addAll(this.getArmy());
        }

        for (Soldier soldier : target) {
            castableAbility.cast(soldier , this);
        }
    }


    public void cast(String abilityName,String targetName)
    {
        ArrayList<Soldier> enemies = this.getOpponentArmy();
        ArrayList<Soldier> friendlies = this.getArmy();

        CastableAbility castableAbility = getCastableAbility(abilityName);
        if (castableAbility == null) return;

        Soldier target = null;
        Boolean isTargetInEnemyArmy = true;

        for (Soldier enemy : enemies) {
            if(enemy.getName().equals(targetName))
            {
                target = enemy;
            }
        }
        if(target == null)
        {
            for (Soldier friendly : friendlies) {
                if(friendly.getName().equals(targetName))
                {
                    target = friendly;
                    isTargetInEnemyArmy = false;
                }
            }
        }
        if(target == null)
        {
            View.show("Target not found, please try again.");
            return;
        }

        if(isTargetInEnemyArmy && !castableAbility.isCastableOnEnemies())
        {
            View.show("This ability can not be cast on an enemy soldier, please try again");
            return;
        }
        if(!isTargetInEnemyArmy && !castableAbility.isCastableOnFriendlies())
        {
            View.show("This ability can not be cast on a friendly soldier, please try again");
            return;
        }


        castableAbility.cast(target, this);
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

    public Ability findAbility(String word) {
        for (Ability ability : abilities) {
            if(ability.getName() == word)
            {
                return ability;
            }
        }
        return null;
    }

    public void addAbility(Ability ability)
    {
        this.abilities.add(ability);
    }

    public ArrayList<Ability> getAbilities(){ return this.abilities;}

    public int getMaximumHealth(){return this.type.getMaximumHealth();}

    public int getMaximumMagic(){ return  this.type.getMaximumMagic();}

    public SoldierType getType(){return this.type;}

    public Story getStory()
    {
        return story;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }
}