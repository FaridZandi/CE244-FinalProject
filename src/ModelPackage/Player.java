package ModelPackage;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Player {
    private ArrayList<Hero> heroes;
    private double locationX;
    private double locationY;
    private int gold;
    private int xp;
    private int immortalityPotions;
    private Battle currentBattle;
    private boolean isGameOver;
    public Player()
    {
        locationX = 4.0;
        locationY = 4.0;
        isGameOver = false;
        gold = 40;
        xp = 15;
        immortalityPotions = 3;
        heroes = new ArrayList<>();
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
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public void setImmortalityPotions(int immortalityPotions) {
        this.immortalityPotions = immortalityPotions;
    }

    public int getImmortalityPotions() {
        return immortalityPotions;
    }

    public Hero findHero(String heroName) {
        Hero searchResult = null;
        for (Hero hero : heroes) {
            if(hero.getName().equals(heroName))
            {
                return hero;
            }
        }
        return null;
    }

    public void showNameAndTypes() {
    }

    public void showAbilitiesAndLevels() {

    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }
}
