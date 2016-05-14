package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Player {
    private ArrayList<Hero> soldiers;

    private int gold;
    private int xp;
    private int immortalityPotions;
    private Battle currentBattle;

    public Player()
    {
        immortalityPotions = 3;
        soldiers = new ArrayList<>();
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }

    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    public ArrayList<Hero> getHeroes() {
        return soldiers;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.soldiers = heroes;
    }

    public void setImmortalityPotions(int immortalityPotions) {
        this.immortalityPotions = immortalityPotions;
    }

    public int getImmortalityPotions() {
        return immortalityPotions;
    }

    public Hero findHero(String acquirerName) {
        //TODO : write this:)
        return new Hero();
    }
}
